/**
 * Copyright &copy; 2018
 * <a href="https://gitee.com/hackempire/patch-generator-desk">patch-generator-desk</a>
 * All rights reserved.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kang.logApp.desk;

import com.kang.logApp.desk.service.LogApp;
import com.kang.logApp.desk.utils.MainFrameConsoleUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 桌面增量打包神器主代码
 *
 * @author Aaron
 */
public class LogAppMainJFrame extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogAppMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame mainJFrame = new LogAppMainJFrame();
                MainFrameConsoleUtil.initConsole(((LogAppMainJFrame) mainJFrame).LogAppBaseConsleTextArea, ((LogAppMainJFrame) mainJFrame).globalConsolejTextPane);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                //设置软件图标
                URL url = LogAppMainJFrame.class.getResource("/patch_desk.png");
                ImageIcon icon = new ImageIcon(url);
                //mainJFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("patch_desk.png"));
                mainJFrame.setIconImage(icon.getImage());
                mainJFrame.setLocation((screen.width - mainJFrame.getSize().width) / 2, (screen.height - mainJFrame.getSize().height) / 2);//使启动窗口居中显示
                mainJFrame.setVisible(true);
                LOGGER.info("感谢你使用FS日志分析统计小神器！");
                LOGGER.info("-- [错误统计] -- [流水查找] -- [精简日志] -- [一步到位]！");
                LOGGER.info("欢迎提建议到邮箱：dengrunkang@chinasofti.com");
            }
        });
    }

    /**
     * Creates new form LogAppMainJFrame
     */
    public LogAppMainJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        LogAppBasePanel = new javax.swing.JPanel();
        LogFileDirLabel = new javax.swing.JLabel();
        LogFileDirTextField = new javax.swing.JTextField();
        SaveFileDirLabel = new javax.swing.JLabel();
        SaveFileDirTextField = new javax.swing.JTextField();
        SaveFileDirButton = new javax.swing.JButton();
        TimeOutLabel = new javax.swing.JLabel();
        TimeOutField = new javax.swing.JTextField("8000");
        TimeOutButton = new javax.swing.JButton();
        LogFileDirTextButton = new javax.swing.JButton();
        LogAppBaseConsleTextArea = new java.awt.TextArea();
        LogAppBaseConsleLabel = new javax.swing.JLabel();
        globalConsolejScrollPane = new javax.swing.JScrollPane();
        globalConsolejTextPane = new javax.swing.JTextPane();
        globalConsoleClearjButton = new javax.swing.JButton();
        beginButton = new javax.swing.JButton();
        AuthorLabel = new javax.swing.JLabel();
        timeOutBeginLocLabel = new javax.swing.JLabel();
        timeOutBeginLocField = new javax.swing.JTextField("consuming [");
        timeOutEndLocLabel = new javax.swing.JLabel();
        timeOutEndLocField = new javax.swing.JTextField("] millisecond");
        ErrorLocLabel = new javax.swing.JLabel();
        ErrorLocField = new javax.swing.JTextField("ERROR");
        tradeBeginLocLabel = new javax.swing.JLabel();
        tradeBeginLocField = new javax.swing.JTextField("server uuid");
        tradeEndLocLabel = new javax.swing.JLabel();
        tradeEndLocField = new javax.swing.JTextField("server platform execute");
        logEncodingLabel = new javax.swing.JLabel();
        logEncodingField = new javax.swing.JTextField("gb2312");
        logThreadPoolSizeLabel = new javax.swing.JLabel();
        logThreadPoolSizeField = new javax.swing.JTextField("5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FS日志分析统计小神器V1.0.0");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(Toolkit.getDefaultToolkit().getImage("favicon-20180430115456304.ico"));
        setLocation(new java.awt.Point(0, 0));
        setName("mainframe"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(300, 200));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogAppBasePanel.setPreferredSize(new java.awt.Dimension(0, 0));
        LogAppBasePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogFileDirLabel.setText("日志文件夹：");
        LogAppBasePanel.add(LogFileDirLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 25));

        LogFileDirTextField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(LogFileDirTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 420, 25));

        LogFileDirTextButton.setText("浏览");
        LogFileDirTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String LogFileDirText = LogFileDirTextField.getText();
                //设置只能选择目录
                doJfileChooseAction(JFileChooser.DIRECTORIES_ONLY, LogFileDirTextField, LogFileDirText, null);
            }
        });
        LogAppBasePanel.add(LogFileDirTextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, 25));

        SaveFileDirLabel.setText("储存文件夹：");
        LogAppBasePanel.add(SaveFileDirLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 25));

        SaveFileDirTextField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(SaveFileDirTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 420, 25));

        SaveFileDirButton.setText("浏览");
        SaveFileDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String SaveFileDirText = SaveFileDirTextField.getText();
                //设置只能选择目录
                doJfileChooseAction(JFileChooser.DIRECTORIES_ONLY, SaveFileDirTextField, SaveFileDirText, null);
            }
        });
        LogAppBasePanel.add(SaveFileDirButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, 25));

        TimeOutLabel.setText("超时毫秒数：");
        LogAppBasePanel.add(TimeOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 90, 25));

        TimeOutField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(TimeOutField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 420, 25));

//        TimeOutButton.setText("浏览");
//        TimeOutButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                GITSERVERPATHjButtonActionPerformed(evt);
//            }
//        });
//        LogAppBasePanel.add(TimeOutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, 20));

        timeOutBeginLocLabel.setText("超时开始位：");
        LogAppBasePanel.add(timeOutBeginLocLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, 25));

        timeOutBeginLocField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(timeOutBeginLocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 420, 25));

        timeOutEndLocLabel.setText("超时结束位：");
        LogAppBasePanel.add(timeOutEndLocLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 90, 25));

        timeOutEndLocField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(timeOutEndLocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 420, 25));

        ErrorLocLabel.setText("统计标记位：");
        LogAppBasePanel.add(ErrorLocLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 90, 25));

        ErrorLocField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(ErrorLocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 420, 25));

        tradeBeginLocLabel.setText("交易开始位：");
        LogAppBasePanel.add(tradeBeginLocLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, 25));

        tradeBeginLocField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(tradeBeginLocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 420, 25));

        tradeEndLocLabel.setText("交易结束位：");
        LogAppBasePanel.add(tradeEndLocLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 90, 25));

        tradeEndLocField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(tradeEndLocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 420, 25));

        logEncodingLabel.setText("日志编码：");
        LogAppBasePanel.add(logEncodingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, 25));

        logEncodingField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(logEncodingField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 420, 25));

        logThreadPoolSizeLabel.setText("固定线程数：");
        LogAppBasePanel.add(logThreadPoolSizeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 90, 25));

        logThreadPoolSizeField.setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N
        LogAppBasePanel.add(logThreadPoolSizeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 420, 25));

        getContentPane().add(LogAppBasePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 320));

        LogAppBaseConsleTextArea.setEditable(false);
        LogAppBaseConsleTextArea.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        LogAppBaseConsleTextArea.setVisible(false);
        getContentPane().add(LogAppBaseConsleTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 620, 50));

        LogAppBaseConsleLabel.setFont(new java.awt.Font("宋体", 0, 13)); // NOI18N
        LogAppBaseConsleLabel.setText("  控制台");
        getContentPane().add(LogAppBaseConsleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, 20));

        globalConsolejTextPane.setDragEnabled(true);
        globalConsolejTextPane.setDropMode(javax.swing.DropMode.INSERT);
        globalConsolejScrollPane.setViewportView(globalConsolejTextPane);

        getContentPane().add(globalConsolejScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 620, 230));

        globalConsoleClearjButton.setText("清空");
        globalConsoleClearjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalConsoleClearjButtonActionPerformed(evt);
            }
        });
        getContentPane().add(globalConsoleClearjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 325, 60, 25));

        beginButton.setText("开始");
        beginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginAnalyse(evt);
            }
        });
        getContentPane().add(beginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 325, 60, 25));

        AuthorLabel.setFont(new java.awt.Font("宋体", 0, 13)); // NOI18N
        AuthorLabel.setText("©logApp for FS created by drk in 2019");
        AuthorLabel.setAutoscrolls(true);
        AuthorLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(AuthorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 470, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 点击清空控制台的内容
     *
     * @param evt
     */
    private void globalConsoleClearjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_globalConsoleClearjButtonActionPerformed
        globalConsolejTextPane.setText("");
    }//GEN-LAST:event_globalConsoleClearjButtonActionPerformed

    /**
     * 开始分析
     *
     * @param evt
     */
    private void beginAnalyse(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_globalConsoleClearjButtonActionPerformed
        // 日志的路径
        String chooseDir = LogFileDirTextField.getText();
        // 存储的路径
        String saveDir = SaveFileDirTextField.getText();
        // 存储的路径
        String timeOut = TimeOutField.getText();
        //超时数字开始标记位
        String timeOutBeginLoc = timeOutBeginLocField.getText();
        //超时数字结束标记位
        String timeOutEndLoc = timeOutEndLocField.getText();
        //统计的字符串
        String ErrorLoc = ErrorLocField.getText();
        //交易开始标记位
        String tradeBeginLoc = tradeBeginLocField.getText();
        //交易结束标记位
        String tradeEndLoc = tradeEndLocField.getText();
        //日志文件的编码
        String logEncoding = logEncodingField.getText();
        //日志文件的编码
        String logThreadPoolSize = logThreadPoolSizeField.getText();
        // 判断用户参数非空
        if (chooseDir == null || "".equals(chooseDir) || saveDir == null || "".equals(saveDir)
                || timeOut == null || "".equals(timeOut) || timeOutBeginLoc == null || "".equals(timeOutBeginLoc)
                || timeOutEndLoc == null || "".equals(timeOutEndLoc) || ErrorLoc == null || "".equals(ErrorLoc)
                || tradeBeginLoc == null || "".equals(tradeBeginLoc) || tradeEndLoc == null || "".equals(tradeEndLoc)
                || logEncoding == null || "".equals(logEncoding)|| logThreadPoolSize == null || "".equals(logThreadPoolSize)) {
            // 弹出提示窗口
            JOptionPane.showMessageDialog(null, "亲亲，请填写齐全信息哦！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
        } else {
            LOGGER.info("日志分析统计【开始】，请亲耐心等待！");
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogApp.logMain(chooseDir, saveDir, timeOut, timeOutBeginLoc, timeOutEndLoc, ErrorLoc, tradeBeginLoc, tradeEndLoc, logEncoding,logThreadPoolSize);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * @param jFileChooserType FILES_ONLY = 0| DIRECTORIES_ONLY =
     *                         1|FILES_AND_DIRECTORIES = 2
     * @param jTextField
     */
    private String doJfileChooseAction(int jFileChooserType, javax.swing.JTextField jTextField, String configPath, String fileSuffix) {
        globaljFileChooser.setFileSelectionMode(jFileChooserType);//只能选择目录
        globaljFileChooser.resetChoosableFileFilters();
        globaljFileChooser.setApproveButtonText("保存");
        globaljFileChooser.setDialogTitle("选择");
        if (StringUtils.isNotBlank(fileSuffix)) {
            globaljFileChooser.setFileFilter(new FileSuffixFilter(fileSuffix));
        }
        if (StringUtils.isNotBlank(configPath)) {
            File confPath = new File(configPath);
            if (confPath.isDirectory()) {
                globaljFileChooser.setCurrentDirectory(confPath);
            } else if (confPath.isFile()) {
                globaljFileChooser.setSelectedFile(confPath);
            }
        } else {
            globaljFileChooser.setSelectedFile(null);
        }
        int i = globaljFileChooser.showSaveDialog(this);

        String path = null;
        if (i == JFileChooser.APPROVE_OPTION) { //打开文件
            path = globaljFileChooser.getSelectedFile().getAbsolutePath();
            String name = globaljFileChooser.getSelectedFile().getName();
            LOGGER.info("当前文件路径：" + path + ";当前文件名：" + name);
            if (jTextField != null) {
                jTextField.setText(path);
            }
        } else {
            LOGGER.info("没有选中文件");
        }
        return path;
    }

    JFileChooser globaljFileChooser = new javax.swing.JFileChooser();
    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAppMainJFrame.class);
    private javax.swing.JLabel LogAppBaseConsleLabel;
    private java.awt.TextArea LogAppBaseConsleTextArea;
    private javax.swing.JButton TimeOutButton;
    private javax.swing.JLabel TimeOutLabel;
    private javax.swing.JTextField TimeOutField;
    private javax.swing.JButton LogFileDirTextButton;
    private javax.swing.JLabel LogFileDirLabel;
    private javax.swing.JTextField LogFileDirTextField;
    private javax.swing.JTextField SaveFileDirTextField;
    private javax.swing.JButton SaveFileDirButton;
    private javax.swing.JLabel SaveFileDirLabel;
    private javax.swing.JPanel LogAppBasePanel;
    private javax.swing.JLabel AuthorLabel;
    private javax.swing.JButton globalConsoleClearjButton;
    private javax.swing.JButton beginButton;
    private javax.swing.JScrollPane globalConsolejScrollPane;
    private javax.swing.JTextPane globalConsolejTextPane;
    private javax.swing.JLabel timeOutBeginLocLabel;
    private javax.swing.JTextField timeOutBeginLocField;
    private javax.swing.JLabel timeOutEndLocLabel;
    private javax.swing.JTextField timeOutEndLocField;
    private javax.swing.JLabel ErrorLocLabel;
    private javax.swing.JTextField ErrorLocField;
    private javax.swing.JLabel tradeBeginLocLabel;
    private javax.swing.JTextField tradeBeginLocField;
    private javax.swing.JLabel tradeEndLocLabel;
    private javax.swing.JTextField tradeEndLocField;
    private javax.swing.JLabel logEncodingLabel;
    private javax.swing.JTextField logEncodingField;
    private javax.swing.JLabel logThreadPoolSizeLabel;
    private javax.swing.JTextField logThreadPoolSizeField;


    class FileSuffixFilter extends FileFilter {

        private final static String DESCRIPTION = "FILESUFFIX";
        private String fileSuffix = "xml";

        public FileSuffixFilter() {
        }

        public FileSuffixFilter(String fileSuffix) {
            this.fileSuffix = fileSuffix;
        }

        @Override
        public boolean accept(File f) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            boolean result = true;
            if (!f.isDirectory()) {
                String fileName = f.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                result = fileSuffix.equals(suffix);
            }
            return result;
        }

        @Override
        public String getDescription() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return DESCRIPTION;
        }
    }
}