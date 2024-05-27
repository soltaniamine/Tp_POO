package com.example.front;

import com.example.back.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAppointment implements Initializable {

    @FXML
    private ChoiceBox<String> appointmentType;
    @FXML
    private ChoiceBox<String> patientType;
    @FXML
    private ChoiceBox<String> freeCreneaux;
    @FXML
    private TextField nom;

    @FXML
    private String date;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField numeroDossier;
    @FXML
    private ChoiceBox<String> isOnline;
    @FXML
    private ChoiceBox<String> listePatients;
    @FXML
    private TextField thematique;
    @FXML
    private Button add;
    @FXML
    private Text indication;
    @FXML
    private AnchorPane anchorPane;
    private String[] cPeriods;
    private String[] startPeriods;
    private String[] endPeriods;
    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cPeriods = new String[]{"13:00 - 14:30", "13:00 - 15:30"};
        startPeriods = new String[]{"08-00", "09-30", "11-00", "16-00", "17-00", "18-00"};
        endPeriods = new String[]{"09-00", "10-30", "12-00", "17-00", "18-00", "19-00"};

        appointmentType.getItems().addAll("1- Consultation", "2- Suivie", "3- Atelier");
        appointmentType.setValue("1- Consultation");
        patientType.getItems().addAll("Adulte", "Enfant");
        patientType.setValue("Adulte");

        Ortho ortho = DataSingleton.getInstance().getOrtho();
        // fill free periods
        Consultation consultation = Consultation.readFromFile(DataSingleton.getInstance().getData() + "/Consultation.bin");
        // if file is empty that means we have a single period
        if (consultation == null) {
            freeCreneaux.getItems().addAll("13-00"); // will change for children
        } else {
            indication.setText("Pas de creneau");
            add.setDisable(true);
        }

        // Set event handlers for the ChoiceBoxes
        appointmentType.setOnAction(this::handleAppointmentTypeSelection);
        patientType.setOnAction(this::handlePatientTypeSelection);
    }

    public void setPatient(Patient patient){
        this.patient=patient;
    }

    private void handleAppointmentTypeSelection(Event event) {
        String choice = appointmentType.getValue();
        double x = 0;
        double y = 0;
        if (choice.equals("1- Consultation")) {
            nom.setPromptText("Nom");
            prenom.setPromptText("Prenom");
            prenom.setVisible(true);
            age.setPromptText("Age");
            age.setVisible(true);
            if (isOnline != null) {
                isOnline.setVisible(false);
            }
            return;
        }
        if (choice.equals("2- Suivie")) {
            nom.setPromptText("Numero de dossier");
            prenom.setVisible(false);
            age.setVisible(false);
            x = prenom.getLayoutX();
            y = prenom.getLayoutY();
            if (isOnline == null) {
                isOnline = new ChoiceBox<>();
                isOnline.getItems().addAll("en ligne", "en presentiel");
                isOnline.setValue("en ligne");
                anchorPane.getChildren().add(isOnline);
                isOnline.setLayoutX(x);
                isOnline.setLayoutY(y);
                copyStyles(appointmentType, isOnline);
                add.setDisable(false);
                return;
            }
            isOnline.setVisible(true);
            return;
        }

        nom.setPromptText("Thematique");
        prenom.setVisible(true);
        age.setVisible(false);
        prenom.setPromptText(" numero Dossiers des patients ");
        if (isOnline != null) {
            isOnline.setVisible(false);
        }
    }

    public static void copyStyles(Node source, Node target) {
        // Copy style classes
        target.getStyleClass().clear();
        target.getStyleClass().addAll(source.getStyleClass());

        // Copy inline styles
        target.setStyle(source.getStyle());
    }

    private void handlePatientTypeSelection(Event event) {
        String choice = appointmentType.getValue();
        if (choice.equals("1- Consultation")) {
            // handle logic here
        }
    }

    @FXML
    public void handleAddButtonClicked(Event event) {
        String choice = appointmentType.getValue();
        if (choice.equals("1- Consultation")) {
            String nomPatient = nom.getText();
            String prenomPatient = prenom.getText();
            int agePatient = Integer.parseInt(age.getText());
            String date = DataSingleton.getInstance().getDate() + "-13-00";
            Consultation consultation = new Consultation(DataSingleton.getInstance().getOrtho(), parseDateTime(date), "", nomPatient, prenomPatient, agePatient);
            consultation.writeToFile(DataSingleton.getInstance().getData() + "/Consultation.bin");
        } else if (choice.equals("2- Suivie")) {
            String sessionDecision = isOnline.getValue();
            boolean isItOnline = sessionDecision.equals("en ligne");
            int numero = Integer.parseInt(nom.getText());


            // Debugging: Print the list of patients
            Ortho ortho = DataSingleton.getInstance().getOrtho();
            patient = ortho.getPatient(numero); // Assuming getPatients() method exists


            String startTime = freeCreneaux.getValue();
            LocalDateTime date = parseDateTime(DataSingleton.getInstance().getDate() + "-" + startTime);// Ensure this method exists
            if (patient != null) {
                Suivie suivie = new Suivie(DataSingleton.getInstance().getOrtho(), patient, date, "", isItOnline); // Pass the patient
                suivie.writeToFile(DataSingleton.getInstance().getData() + "/Suivie.bin");
                for(RendezVous rdv : patient.getListRendezVous()){
                    System.out.println(rdv.getDate());
                }
            } else {
                System.err.println("Patient with numero " + numero + " not found.");
            }
        }
    }


    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date time format. Please use 'yyyy-MM-dd-HH-mm'.");
            return null;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }
}
