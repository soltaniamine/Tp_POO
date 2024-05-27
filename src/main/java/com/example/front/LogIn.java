package com.example.front;
import com.example.back.*;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogIn {

    @FXML
    AnchorPane anchorPane1 ;
    @FXML
    AnchorPane anchorPane2 ;
    @FXML
    TextField email ;
    @FXML
    PasswordField password ;
    @FXML
    Text emailNotExisting ;
    @FXML
    Text wrongPassword ;


    public void handleConnectionButton (Event event) throws IOException {
        String userEmail = email.getText();
        String userPassword = password.getText();
        overwriteFile("src/main/java/com/example/front/current.txt", userEmail);

        if (FilesHandlingFunctions.doesUserExist("accounts.txt",userEmail)){
            if (FilesHandlingFunctions.passwordEquals("accounts.txt", userEmail,userPassword)) {
                emailNotExisting.setText("");
                wrongPassword.setText("");
                try {
                    ArrayList<String> doctorData = FilesHandlingFunctions.readLinesFromFile(userEmail+"/doctor_data");
                    Ortho ortho = new Ortho(doctorData.get(1),doctorData.get(0),doctorData.get(3),doctorData.get(2),doctorData.get(4),doctorData.get(5));
                    DataSingleton.getInstance().setOrtho(ortho);

                    // Load the Second side
                    Parent secondView = FXMLLoader.load(getClass().getResource("/com/example/tppoo/dashboard.fxml"));
                    Scene secondScene = new Scene(secondView);

                    // Get the current stage (window) using the event's source
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                    // Set the new scene on the current stage
                    window.setScene(secondScene);
                    window.setTitle("Log In");
                    window.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                emailNotExisting.setText("");
                wrongPassword.setText("Le mot de passe n'est pas correct");
                wrongPassword.setStyle("-fx-fill: #de0914;" +
                        "-fx-font-family: 'Product Sans Bold';" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14;");
            }
        }
        else {
            // handle user doesn't exist
            wrongPassword.setText("");
            emailNotExisting.setText("Email n'existe pas");
            emailNotExisting.setStyle("-fx-fill: #de0914;" +
                    "-fx-font-family: 'Product Sans Bold';" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 18;");
        }

    }

    public static void overwriteFile(String filePath, String content) {
        try {
            Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSignUpLink (Event event) throws IOException {
        try {
            // Load the Second side
            Parent secondView = FXMLLoader.load(getClass().getResource("/com/example/tppoo/sign-up.fxml"));
            Scene secondScene = new Scene(secondView);

            // Get the current stage (window) using the event's source
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            window.setScene(secondScene);
            window.setTitle("Sign Up");
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
