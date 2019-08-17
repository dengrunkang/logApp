package com.kang.logApp.desk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogApp {
    private static final Logger logger = LoggerFactory.getLogger(LogApp.class);//日志

    private static final Map<String, Integer> errorCount;// 每种错误标记统计
    private static int timeOutCount = 0;//超时统计
    private static long logAnalyseTimeConsume = 0;// 日志分析统计总耗时
    private static int totalFilesCount = 0; //日志文件的总数

    private static String srcDirLoc = ""; //日志路径
    private static String saveDirLoc = ""; //存储路径
    private static int timeOut = 8000; //交易超时毫秒
    private static String timeOutBeginLoc = ""; //超时数字开始标记位
    private static String timeOutEndLoc = ""; //超时数字结束标记位
    private static String ERROR = ""; //统计的字符串
    private static String tradeBeginLoc = ""; //交易开始标记位
    private static String tradeEndLoc = ""; //交易结束标记位
    private static String encoding = "gb2312"; //日志文件的编码

    //    private final static int corePoolSize = 15;
//    private final static int maximumPoolSize = 50;
//    private final static long keepAliveTime = 30;
//    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(50), new ThreadPoolExecutor.DiscardPolicy());
    private static ExecutorService threadPool;
    private static int fixPoolSize = 5;

    static {
        errorCount = new ConcurrentHashMap<>();
    }

    public static void logMain(String srcLoc, String saveLoc, String timeOutMilli,
                               String timeOutBeginStr, String timeOutEndStr, String errorStr,
                               String tradeBeginStr, String tradeEndStr, String logEncoding,String poolSize) throws Exception {
        srcDirLoc = srcLoc;
        saveDirLoc = saveLoc;
        timeOut = Integer.valueOf(timeOutMilli);
        timeOutBeginLoc = timeOutBeginStr;
        timeOutEndLoc = timeOutEndStr;
        ERROR = errorStr;
        tradeBeginLoc = tradeBeginStr;
        tradeEndLoc = tradeEndStr;
        encoding = logEncoding;
        fixPoolSize = Integer.valueOf(poolSize);

        errorCount.clear();
        logAnalyseTimeConsume = 0;
        totalFilesCount = 0;
        timeOutCount = 0;

        long start = System.currentTimeMillis();
        threadPool = Executors.newFixedThreadPool(fixPoolSize);
        File srcDir = new File(srcDirLoc);
        if (!srcDir.exists()) {
            throw new Exception("输入的文件夹路径错误");
        }
        //递归处理所有文件夹
        dealDir(srcDir);

        threadPool.shutdown();
        long waitTem = 0;
        long waitEnd = 0;
        waitEnd = System.currentTimeMillis();
        logAnalyseTimeConsume = waitEnd - start;
        while (true) {
            if (threadPool.isTerminated()) {
                long end = System.currentTimeMillis();
                logAnalyseTimeConsume = end - start;
                logger.info("日志分析统计完成,用时:" + (logAnalyseTimeConsume / 1000) + " 秒,日志文件总数:" + totalFilesCount + "，超时订单总数：" + timeOutCount);
                //打印分析统计汇总文件
                printErrorCountToFile();
                errorCount.clear();
                Desktop.getDesktop().open(new File(saveLoc));
                JOptionPane.showMessageDialog(null, "亲亲，FS日志分析统计完成！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                logAnalyseTimeConsume = 0;
                totalFilesCount = 0;
                timeOutCount = 0;
                fixPoolSize = 5;
                break;
            }
            waitEnd = System.currentTimeMillis();
            waitTem = waitEnd - start;
            if ((waitTem - logAnalyseTimeConsume) >= 15000) {
                logger.info("亲，程序还在跑，请亲耐心等待！稍后会通知你。");
                logAnalyseTimeConsume = waitTem;
            }
            Thread.sleep(500L);
        }

    }


    private static void dealDir(File srcDir) {
        try {
            File[] listFiles = srcDir.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                logger.info("文件夹内无内容");
                return;
            }
            for (final File file : listFiles) {
                if (file.isFile()) {
                    totalFilesCount++;
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            // 处理日志分析统计业务逻辑
                            try {
                                long begin = System.currentTimeMillis();
                                logger.info(Thread.currentThread().getName() + ",[开始]," + file.getAbsolutePath().substring(srcDirLoc.length()));
                                analyseLogFile(file);
                                long singleFileTimeConsume = System.currentTimeMillis() - begin;
                                logger.info(Thread.currentThread().getName() + ",[完成]" + (singleFileTimeConsume / 1000) + "秒," + file.getAbsolutePath().substring(srcDirLoc.length()));
                            } catch (Exception e) {
                                e.printStackTrace();
                                logger.info(e.getMessage());
                            }
                        }
                    });

                } else {
                    dealDir(file);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private static void analyseLogFile(File srcFile) throws Exception {
        RandomAccessFile rf = new RandomAccessFile(srcFile, "r");
        StringBuilder sb = new StringBuilder();
        StringBuilder printSb = new StringBuilder();
        String line = "";
        int serverFlag = 0;
        int newErrorFlag = 0;
        while ((line = rf.readLine()) != null) {
            line = new String(line.getBytes("ISO-8859-1"), encoding);
            // 处理一个server的分析统计逻辑
            if (line.contains(ERROR)) {
                String errorMessage = line.substring(line.indexOf(ERROR));
                if (errorCount.get(errorMessage) != null) {
                    errorCount.put(errorMessage, errorCount.get(errorMessage) + 1);
                } else {
                    newErrorFlag += 1;
                    errorCount.put(errorMessage, 1);
                }
            }
            // 交易超时统计
            if (line.contains(timeOutBeginLoc) && line.contains(timeOutEndLoc)) {
                int firstIndexOfTimeConsuming = (line.indexOf(timeOutBeginLoc)) + 11;
                int lastIndexOfTimeConsuming = (line.indexOf(timeOutEndLoc));
                int timeConsuming = Integer.valueOf(line.substring(firstIndexOfTimeConsuming, lastIndexOfTimeConsuming));
                if (timeConsuming >= timeOut) {
                    timeOutCount++;
                }
            }
            // 记录一个server的详细日志
            if (line.contains(tradeBeginLoc)) {
                serverFlag = 1;
            }
            if (serverFlag == 1) {
                sb.append(line + "\r\n");
            }
            if (line.contains(tradeEndLoc)) {
                serverFlag = 0;
            }
            if (serverFlag == 0) {
                //打印
                if (newErrorFlag > 0) {
                    printSb.append(sb.toString());
                }
                newErrorFlag = 0;
                //清空字符串缓存
                sb.delete(0, sb.length());
            }
        }
        // 打印错误明细
        printErrorToFile(srcFile, printSb.toString());
    }

    private static void printErrorCountToFile() throws Exception {
        File destDir = new File(saveDirLoc);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        String destFileName = destDir + File.separator + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "FS日志统计分析汇总文件.txt";
        String timeOutAnalyse = "日志分析总耗时毫秒数：" + logAnalyseTimeConsume + "，日志文件总数：" + totalFilesCount + "\r\n" + "订单超时毫秒数：" + timeOut + "，超时订单总数：" + "  ------>  " + timeOutCount + "\r\n";
        FileOutputStream fos = new FileOutputStream(destFileName);
        fos.write(timeOutAnalyse.getBytes());
        String content = "";
        Set<String> keySet = errorCount.keySet();
        for (String key : keySet) {
            content = key + "  ------>  " + errorCount.get(key) + "\r\n";
            fos.write(content.getBytes());
        }
        fos.close();
    }

    private static void printErrorToFile(File file, String content) throws Exception {
        File destDir = new File(saveDirLoc + File.separator + "FS错误去重精简与流水查找日志汇总");
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        if (content != null && !"".equals(content)) {
            String destFileName = destDir + File.separator + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + file.getName();
            FileOutputStream fos = new FileOutputStream(destFileName);
            fos.write(content.getBytes());
            fos.close();
        }
    }

}
