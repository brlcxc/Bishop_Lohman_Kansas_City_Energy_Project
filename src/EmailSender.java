//this is just a test class I used for figuring out how to send emails
public class EmailSender {
    EmailSender(){
        String[] recepients = {"lohmanbishop@gmail.com"};
        String subject = "Monthly Invoice";
        String message = "This is your monthly invoice.";
        new MailUtil().sendMail(recepients, subject, message);
    }
    public static void main(String[] args) {
        new EmailSender();
    }


}