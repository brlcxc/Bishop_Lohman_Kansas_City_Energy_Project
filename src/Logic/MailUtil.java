package Logic;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
    private String SMTP_HOST = "smtp.gmail.com";
    private String FROM_ADDRESS = "lohmanbishop@gmail.com";
    private String PASSWORD = "qwqe ygic ihoc ymjg";
    private String FROM_NAME = "Bishop";
    public boolean sendMail(String[] recipients, String subject, String message) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "false");
            props.put("mail.smtp.ssl.enable", "true");

            Session session = Session.getInstance(props, new SocialAuth());
            Message msg = new MimeMessage(session);

            InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
            msg.setFrom(from);
            InternetAddress[] toAddresses = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                toAddresses[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("Your bill is now available\n");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "invoice.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            msg.setContent(multipart );
            Transport.send(msg);
            System.out.println("Sent message successfully...");
            return true;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;

        } catch (MessagingException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return false;
        }
    }

    class SocialAuth extends Authenticator {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);
        }
    }
}