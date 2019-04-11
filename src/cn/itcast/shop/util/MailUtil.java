package cn.itcast.shop.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具
 */
public class MailUtil {

    /**
     * 发送邮件方法
     * @param to    ：收件人
     * @param code  ：激活码
     */
    public static void sendMail(String to, String code){
        /**
         * 1、获得一个Session对象
         * 2、创建一个代表邮件的对象Message
         * 3、发送邮件Transport
         */
        //1、获得连接对象
        Properties props = new Properties();
        props.setProperty("mail.host","localhost");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service@shop.com","111");
            }
        });
        //2、创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress("service@shop.com"));
            message.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(to));
            /* 抄送：message.addRecipient(MimeMessage.RecipientType.CC,new InternetAddress(toCC));*/
            message.setSubject("来自购物天堂的官方激活邮件！");
            message.setContent("<h1>购物天堂官方激活邮件，点击激活</h1><h3><a href='http://192.168.1.37:8080/shop/user_active.action?code="+code+"'>http://192.168.1.37:8080/shop/user_active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
            //3、发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }



}
