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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SignUp {

    @FXML
    TextField firstName ;
    @FXML
    TextField lastName ;
    @FXML
    TextField phoneNumber ;
    @FXML
    TextField address ;
    @FXML
    TextField email ;
    @FXML
    PasswordField password ;
    @FXML
    Text emailRemark ;
    @FXML
    Text fillAllFields ;


    public void handleConnectionButton (Event event) throws IOException {
        String userFirstName = firstName.getText();
        String userLastName = lastName.getText();
        String userPhoneNumber = phoneNumber.getText();
        String userAddress = address.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();
        if (!userFirstName.isEmpty() && !userLastName.isEmpty() && !userPhoneNumber.isEmpty() && !userAddress.isEmpty() && !userEmail.isEmpty() && !userPassword.isEmpty()){
            if (FilesHandlingFunctions.doesUserExist("accounts.txt" , userEmail)){
                emailRemark.setText("Email deja existant");
                emailRemark.setStyle("-fx-fill: #de0914;" +
                        "-fx-font-family: 'Product Sans Bold';" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14;");
            }
            else {
                fillAllFields.setText("");
                emailRemark.setText("");
                ArrayList<String> account = new ArrayList<String>();
                account.add(userEmail);
                account.add(userPassword);
                FilesHandlingFunctions.writeLinesToFile("accounts.txt",account);
                account.remove(0);
                account.remove(0);
                if (FilesHandlingFunctions.createFolderIfNotExists(userEmail)){
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/doctor_data");
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/statistiques.bin");
                    Statistics initialStatistics = new Statistics(0,0,0) ;
                    // Write object of statistics
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(userEmail+"/statistiques.bin"))) {
                        writer.write(initialStatistics.getIndex1());
                        writer.newLine();
                        writer.write(initialStatistics.getIndex2());
                        writer.newLine();
                        writer.write(initialStatistics.getIndex3());
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Ortho ortho = new Ortho(userLastName,userFirstName,userAddress,userPhoneNumber,userEmail,userPassword) ;
                    DataSingleton.getInstance().setOrtho(ortho);
                    account.add(userFirstName);
                    account.add(userLastName);
                    account.add(userPhoneNumber);
                    account.add(userAddress);
                    account.add(userEmail);
                    account.add(userPassword);
                    FilesHandlingFunctions.writeLinesToFile(userEmail+"/doctor_data",account);
                    FilesHandlingFunctions.createFolderIfNotExists(userEmail+"/rendez-vous-historique");
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/anamneses.bin");
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/question_libre.bin") ;
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/qcu.bin");
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/qcm.bin");
                    FilesHandlingFunctions.createFileIfNotExists(userEmail+"/exos.bin");
                    // Loop to get the next 3 years as strings
                    // Get the current year
                    Calendar calendar = Calendar.getInstance();
                    int currentYear = calendar.get(Calendar.YEAR);
                    for (int i = 0; i < 3; i++) {
                        int year = currentYear + i;
                        String yearString = Integer.toString(year);
                        FilesHandlingFunctions.createFolderIfNotExists(userEmail+"/rendez-vous-historique/"+yearString) ;
                        for (int j = 1 ; j<=12; j++) {
                            String monthString = Integer.toString(j);
                            FilesHandlingFunctions.createFolderIfNotExists(userEmail+"/rendez-vous-historique/"+yearString+"/"+monthString);
                            int daysNumber = getDaysInMonth(year,j) ;
                            for (int k = 1 ; k<= daysNumber ; k++) {
                                String dayString = Integer.toString(k);
                                FilesHandlingFunctions.createFolderIfNotExists(userEmail+"/rendez-vous-historique/"+yearString+"/"+monthString+"/"+dayString) ;
                                FilesHandlingFunctions.createFileIfNotExists(userEmail+"/rendez-vous-historique/"+yearString+"/"+monthString+"/"+dayString+"/Consultation.bin");
                                FilesHandlingFunctions.createFileIfNotExists(userEmail+"/rendez-vous-historique/"+yearString+"/"+monthString+"/"+dayString+"/Suivie.bin");
                                FilesHandlingFunctions.createFileIfNotExists(userEmail+"/rendez-vous-historique/"+yearString+"/"+monthString+"/"+dayString+"/Atelier.bin");

                            }

                        }
                    }
                }
                try {
                    // Load the Second side
                    Parent secondView = FXMLLoader.load(getClass().getResource("/com/example/tppoo/dashboard.fxml"));
                    Scene secondScene = new Scene(secondView);

                    // Get the current stage (window) using the event's source
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                    // Set the new scene on the current stage
                    window.setScene(secondScene);
                    window.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            fillAllFields.setText("Remplir tous les champs");
            fillAllFields.setStyle("-fx-fill: #de0914;" +
                    "-fx-font-family: 'Product Sans Bold';" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 18;");
        }

    }

    public static int getDaysInMonth(int year, int month) {
        // Create a Calendar instance and set the year and month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Subtract 1 because months are 0-based in Calendar

        // Get the number of days in the month
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public void handleLogInLink (Event event) throws IOException {
        try {
            // Load the Second side
            Parent secondView = FXMLLoader.load(getClass().getResource("/com/example/tppoo/log-in.fxml"));
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
}
