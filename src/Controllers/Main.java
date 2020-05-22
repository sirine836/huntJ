/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author h^
 */
public class Main extends Application {
    public static User fos_user;
    public static int user_id;
    
    @Override
        public void start
        (Stage stage) throws Exception {
            
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("/Images/logo.png"));
            stage.setTitle("T-HUNT");
            stage.setScene(scene);
            stage.show();
        }

    public static void main(String[] args) throws SQLException {

        
        launch(args);
       System.out.println(fos_user);
        System.out.println(user_id);

   }

}