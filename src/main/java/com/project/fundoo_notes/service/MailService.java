package com.project.fundoo_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class MailService
{

//    @Autowired
//    private TokenUtil tokenutil;//jayashree@4mail@4

    //all the content of the mail is taken as parameter to this given method of the java mail service
    public void send(String toEmail, String subject, String body)
    {
        System.out.println(toEmail);

        final String fromEmail = "projectjayashreesnt@gmail.com";
// requires valid gmail id
        final String password ="jayashree@4mail@4"

                ; // correct password for gmail id
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
// SMTP Host
        props.put("mail.smtp.port", "587");
// TLS Port
        props.put("mail.smtp.auth", "true");
// enable authentication
        props.put("mail.smtp.starttls.enable", "true");
// enable STARTTLS
// to check email sender credentials are valid or not
        Authenticator auth = new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(fromEmail, password);

            }
        };
        javax.mail.Session session = Session.getInstance(props, auth);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply"));
            msg.setReplyTo(InternetAddress.parse("swatirangari44@gmail.com", false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
            System.out.println("Email Sent Successfully........."); }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //body for the given user link is created here
//    public String getLink(String link,long id)
//    {
//        return link+tokenutil.createToken(id);
//    }


}