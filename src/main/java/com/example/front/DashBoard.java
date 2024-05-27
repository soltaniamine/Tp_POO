package com.example.front;
import com.example.back.*;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.time.LocalDateTime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DashBoard implements Initializable {

    @FXML
    Button dashBoard ;
    @FXML
    Button myPatients ;
    @FXML
    Button myAppointments ;
    @FXML
    Button tests ;
    @FXML
    Button settings ;
    @FXML
    Button payments ;
    @FXML
    Button notifications;
    @FXML
    Button downloadData ;
    @FXML
    Button logOut ;
    @FXML
    Text nextPatientName ;
    @FXML
    Text doctorName ;
    @FXML
    Text index1 ;
    @FXML
    Text index2 ;
    @FXML
    Text index3 ;

    public void initialize (URL url , ResourceBundle rb) {

        //fill doctor name
        String orthoEmail = FilesHandlingFunctions.readLinesFromFile("src/main/java/com/example/front/current.txt").get(0);
        ArrayList<String> orthoData = FilesHandlingFunctions.readLinesFromFile(orthoEmail+"/doctor_data") ;
        Ortho doctor = new Ortho(orthoData.get(1),orthoData.get(0),orthoData.get(3),orthoData.get(2),orthoData.get(4),orthoData.get(5)) ;

        DataSingleton.getInstance().setOrtho(doctor);
        Patient pat1 = new Patient(doctor,"soltani","mohamed",LocalDateTime.now(),"Alger","Bouraoui",true);
        Question q1 = new Question("how do you feel today",Categorie.LANGUAGE_DEVELOPMENT,TypeQuestion.LIBRE);
        Exercice exrecice = new Exercice("Describe what you see","Picture");
        doctor.ajouterExercice("Describe what do you see","Book");
        doctor.ajouterQuestion("how do you feel today",Categorie.LANGUAGE_DEVELOPMENT,TypeQuestion.LIBRE);





        // Serialize the doctor object
        try (FileOutputStream fileOut = new FileOutputStream("doc.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(doctor);
            System.out.println("Serialized data is saved in doc.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

        // Deserialize the doctor object


        doctorName.setText("Dr. " + orthoData.get(1) + " " + orthoData.get(0));
        doctorName.setStyle("-fx-fill: #0282c2; -fx-font-weight: bold; -fx-text-alignment: left;");
        // fill statistics
        Statistics stats = FilesHandlingFunctions.readStatisticsFromFile(orthoEmail + "/statistiques.bin");
        index1.setText(String.valueOf(stats.getIndex1()));
        index1.setStyle("-fx-fill: #ffffff; -fx-font-family: Product Sans Bold ; -fx-font-weight: bold;");
        index2.setText(String.valueOf(stats.getIndex2()));
        index2.setStyle("-fx-fill: #ffffff; -fx-font-family: Product Sans Bold ; -fx-font-weight: bold;");
        index3.setText(String.valueOf(stats.getIndex3()));
        index3.setStyle("-fx-fill: #ffffff; -fx-font-family: Product Sans Bold ; -fx-font-weight: bold;");
        // fill charts

    }

    private void changeScene(Event event, String fxmlFileName) {
        try {
            Parent newView = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene newScene = new Scene(newView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDashBoardButtonClick(Event event) {
        changeScene(event, "/com/example/tppoo/dashboard.fxml");
    }

    @FXML
    public void handleAppointmentsButtonClick(Event event) {
        changeScene(event, "/com/example/tppoo/appoitments.fxml");
    }

    @FXML
    public void handlePatientsButtonClick(Event event) {
        changeScene(event, "/com/example/tppoo/patients.fxml");
    }

    @FXML
    public void handleTestsButtonClick(Event event) {
        changeScene(event, "/com/example/tppoo/tests_anamneses.fxml");
    }
}
