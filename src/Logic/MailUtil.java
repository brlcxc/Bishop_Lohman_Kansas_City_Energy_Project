package Logic;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//need mail.jar in classpath
//https://www.google.com/settings/security/lesssecureapps
//access to less secure apps must be turned on in gmail account settings of sender

//this class was taken from the code example but it is not yet working with this program
public class MailUtil {
    private String SMTP_HOST = "smtp.gmail.com";
private String FROM_ADDRESS = "lohmanbishop@gmail.com";
    private String PASSWORD = "";
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

//            InternetAddress[] bccAddresses = new InternetAddress[bccRecipients.length];
//            for (int j = 0; j < bccRecipients.length; j++) {
//                bccAddresses[j] = new InternetAddress(bccRecipients[j]);
//            }
//            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);

            msg.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "invoice.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            msg.setContent(multipart );
            Transport.send(msg);
            System.out.println("Sent message successfully....");
            return true;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;

        } catch (MessagingException ex) {
//            ex.printStackTrace();
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