package com.example.front;


import com.example.back.Ortho;
import com.example.back.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import com.example.back.*;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Patients implements Initializable{
    @FXML
    private TableView<PatientWrapper> patientsTableView;
    @FXML
    private TableColumn<PatientWrapper, String> nomColumn;
    @FXML
    private TableColumn<PatientWrapper, String> prenomColumn;
    @FXML
    private TableColumn<PatientWrapper, String> dateDeNaissanceColumn;
    @FXML
    private TableColumn<PatientWrapper, String> numPatientColumn;
    @FXML
    private TableColumn<PatientWrapper, Void> actionColumn;  // The new column for buttons


    private Ortho orthophonist;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Deserialize the Ortho object
        try (FileInputStream fileIn = new FileInputStream("doc.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            orthophonist = (Ortho) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Initialize the TableView columns
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateDeNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateDeNaissance"));
        numPatientColumn.setCellValueFactory(new PropertyValueFactory<>("numPatient"));

        // Initialize actionColumn with two buttons
        actionColumn.setCellFactory(col -> new TableCell<PatientWrapper, Void>() {
            private final Button detailsButton = new Button("Details du patient");
            private final Button anotherButton = new Button("Ajouter un BO");
            private final Button ficheDeSuivieButton = new Button("Ajouter une Fiche de Suivie");

            {
                detailsButton.setOnAction(event -> {
                    PatientWrapper patientWrapper = getTableView().getItems().get(getIndex());
                    showPatientDetails(patientWrapper.getPatient());
                });

                anotherButton.setOnAction(event -> {
                    PatientWrapper patientWrapper = getTableView().getItems().get(getIndex());
                    showAnotherWindow(patientWrapper.getPatient());
                });
                ficheDeSuivieButton.setOnAction(event ->{
                    PatientWrapper patientWrapper = getTableView().getItems().get(getIndex());
                    ficheDeSuivieWindow(patientWrapper.getPatient());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(detailsButton, anotherButton,ficheDeSuivieButton);
                    hBox.setSpacing(10);  // Optional: spacing between buttons
                    setGraphic(hBox);
                }
            }
        });

        // Load patients data
        if (orthophonist != null) {
            ObservableList<PatientWrapper> patientsList = FXCollections.observableArrayList(
                    orthophonist.getPatients().stream().map(PatientWrapper::new).collect(Collectors.toList())
            );
            patientsTableView.setItems(patientsList);
        }

        // Clear and add columns to the TableView to avoid duplication
        patientsTableView.getColumns().clear();
        patientsTableView.getColumns().addAll(nomColumn, prenomColumn, dateDeNaissanceColumn, numPatientColumn, actionColumn);

        // Double click to show patient details
        patientsTableView.setRowFactory(tv -> {
            TableRow<PatientWrapper> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    PatientWrapper rowData = row.getItem();
                    showPatientDetails(rowData.getPatient());
                }
            });
            return row;
        });
    }

    private void showAnotherWindow(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tppoo/ajouter-BO.fxml"));
            Parent root = loader.load();

            AjouterBO controller = loader.getController();
            controller.ajouterBo(patient);  // Pass the patient to the controller

            Stage stage = new Stage();
            stage.setTitle("Ajouter BO");
            stage.setScene(new Scene(root, 700, 600));  // Adjust size as needed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void ficheDeSuivieWindow(Patient patient){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tppoo/fiche-suivie.fxml"));
            Parent root = loader.load();

            FicheDeSuivieController controller = loader.getController();
            controller.setPatient(patient);  // Pass the patient to the controller

            Stage stage = new Stage();
            stage.setTitle("Ajouter BO");
            stage.setScene(new Scene(root, 700, 600));  // Adjust size as needed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






    private void showPatientDetails(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tppoo/patientDetails.fxml"));
            Parent root = loader.load();

            PatientDetailsController controller = loader.getController();
            controller.setPatientDetails(patient);

            Stage stage = new Stage();
            stage.setTitle("Patient Details");
            stage.setScene(new Scene(root, 886, 587.5));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
