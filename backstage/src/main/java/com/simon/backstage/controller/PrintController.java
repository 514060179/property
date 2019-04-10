package com.simon.backstage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author fengtianying
 * @date 2019/2/26 10:45
 */
@RestController
@RequestMapping("print")
public class PrintController {

    @Autowired
    private JavaMailSender mailSender;

    public static void print() {
        JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
        int state = fileChooser.showOpenDialog(null);
        if (state == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // 获取选择的文件
            // 构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            // 设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            // 查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            // 定位默认的打印服务
            PrintService defaultService = PrintServiceLookup
                    .lookupDefaultPrintService();
            // 显示打印对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // 创建打印作业
                    FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printjpg(String getfile,String fensu)
    {
        //构造一个文件选择器，默认为当前目录

        File file = new File(getfile);//获取选择的文件
        //构建打印请求属性集
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        //设置打印格式，因为未确定文件类型，这里选择AUTOSENSE
        DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;
        //查找所有的可用打印服务
        //PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        //定位默认的打印服务
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //显示打印对话框
        long j=Integer.parseInt(fensu);
        for(int i=0;i<j;i++)
        {
            try {
                DocPrintJob job = defaultService.createPrintJob(); //创建打印作业
                FileInputStream fis = new FileInputStream(file); //构造待打印的文件流
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(fis, flavor, das); //建立打印文件格式
                job.print(doc, pras); //进行文件的打印
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2245702722@qq.com");
        message.setTo("514060179@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    public void testSendHtml() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("2245702722@qq.com");
            helper.setTo("514060179@qq.com");
            helper.setSubject("密碼重置");

            StringBuffer sb = new StringBuffer();
            sb.append("<h2 text-align:center >bms物業管理系統APP密碼重置</h2>")
//                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:left'>   您的重置密碼為:123456</p>").append("<p style='text-align:right'>bms物業管理系統</p>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);
    }
    @RequestMapping("test")
    public void test(){
//        printjpg("C:\\project\\dist\\favicon.ico", "1");
        print();
    }
    @RequestMapping("mail")
    public void mail(){
        try {
            sendSimpleMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testSendHtml();
    }
    public static void main(String  args[])
    {
//        printjpg("C:\\project\\dist\\favicon.ico", "1");
//        print();
    }
}
