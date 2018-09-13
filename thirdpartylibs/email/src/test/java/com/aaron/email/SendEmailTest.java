package com.aaron.email;

import org.apache.commons.mail.*;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 参考 http://commons.apache.org/proper/commons-email/userguide.html
 */
public class SendEmailTest {

    @Test
    public void simpleEmailTest() throws EmailException, InterruptedException {
//        for(int i=0;i<10;i++) {
            SimpleEmail simpleEmail = new SimpleEmail();
//            simpleEmail.setSslSmtpPort("465");
            simpleEmail.setSmtpPort(465);
            simpleEmail.setSocketConnectionTimeout(5000);
            simpleEmail.setSocketTimeout(5000);
            simpleEmail.setSSLOnConnect(true);
            simpleEmail.setSSLCheckServerIdentity(true);
            simpleEmail.setHostName("");
            simpleEmail.setAuthentication("", "");
            simpleEmail.setFrom("");
            simpleEmail.setSubject("测试");
            simpleEmail.setMsg("test邮件666");
            simpleEmail.addTo("", "aaron");
//            simpleEmail.addTo("Richard.zhan@allchips.com", "Richard");
            simpleEmail.setDebug(true);
            simpleEmail.send();
//            Thread.sleep(200);
//        }
    }

    @Test
    public void simpleEmailTest2() throws EmailException {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("E:\\aaron\\document\\周报\\九月第一周-技术部.doc");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("周报.doc");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
//            simpleEmail.setSslSmtpPort("465");
        email.setSmtpPort(465);
        email.setSocketConnectionTimeout(5000);
        email.setSocketTimeout(5000);
        email.setSSLOnConnect(true);
        email.setSSLCheckServerIdentity(true);
        email.setHostName("");
        email.setAuthentication("", "");
        email.setFrom("");
        email.setSubject("测试");
        email.setMsg("test邮件666");
        email.addTo("", "aaron");
//            simpleEmail.addTo("Richard.zhan@allchips.com", "Richard");
        email.setDebug(true);

        // add the attachment
        email.attach(attachment);

        email.send();


    }

    @Test
    public void simpleEmailTest3() throws EmailException, MalformedURLException {
// Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setSmtpPort(465);
        email.setSocketConnectionTimeout(5000);
        email.setSocketTimeout(5000);
        email.setSSLOnConnect(true);
        email.setSSLCheckServerIdentity(true);
        email.setHostName("");
        email.setAuthentication("", "");
        email.setFrom("");
        email.setSubject("Test email with inline image");
        email.setMsg("test邮件666");
        email.addTo("", "aaron");
//            simpleEmail.addTo("Richard.zhan@allchips.com", "Richard");
        email.setDebug(true);

        // embed the image and get the content id
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");

        // set the html message
//        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

        // set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");

        // send the email
        email.send();
    }

}
