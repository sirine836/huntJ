
package Controllers;

import com.email.durgesh.Email;
import com.jfoenix.controls.JFXTextField;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;

/**
 *
 * @author MONDHER
 */
public class SendMailController {

    @FXML
    private JFXTextField SubjectInput;
    @FXML
    private JFXTextField ReceiverAdress;
    @FXML
    private TextArea Message;
    @FXML
    private Button SendMail;
    @FXML
    private Button Refresh;

    @FXML
    private void Send(ActionEvent event) { try { //Envoi mail pour client **DONE**
        
        Email email = new Email ("huntkingdom.store@gmail.com","pidevesprit2020"); //Email Adress Sender + password
        
        //FROM
        email.setFrom("huntkingdom.store@gmail.com" , "Huntkindgom Store");
        
        //Subject
        
        email.setSubject(SubjectInput.getText());
        
        //Message
        
        email.setContent(Message.getText() , "text/html");
        
        //TO
        
        email.addRecipient(ReceiverAdress.getText());
        
        //Send
        
        if(ValidateMail(ReceiverAdress.getText())==true){
            email.send();
            Image notif = new Image("/Images/Done.png");
            Notifications notificationBuilder = Notifications.create()
            .title("SEND E_MAIL")
            .text("Email sent Successfully")
            .graphic(new ImageView(notif))
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT)
            .onAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Clicked on Notification");
            }
            
            });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }else{
            Image notif = new Image("/Images/Error.png");
            Notifications notificationBuilder = Notifications.create()
            .title("SEND E_MAIL")
            .text("Error In Sending")
            .graphic(new ImageView(notif))
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT)
            .onAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Clicked on Notification");
            }
            
            });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            } 
        } catch (MessagingException ex) {
            System.out.println(ex);
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        }
            }
    
           //Avant d'envoyer .. il faut activer le paramètre "Autoriser les applications moins sécurisées"
           
           //au niveau de l'adresse source
           
           //lien : https://myaccount.google.com/lesssecureapps
           
          /* -------------------------------------------------------- */
         
    @FXML
    private void RefreshAct(ActionEvent event) {
         SubjectInput.clear();
         ReceiverAdress.clear();
         Message.clear();    
    }
    
    public static boolean ValidateMail(String Input){
        String emailReg = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" ;
        Pattern emailPat = Pattern.compile(emailReg , Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(Input);
        return matcher.find();
    }
     
    }
    

