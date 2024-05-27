package com.example.front;

import com.example.back.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FicheDeSuivieController implements Initializable {

    @FXML
    private TableView<Objectif> questionsTable;

    @FXML
    private TableColumn<Objectif, String> questionColumn;

    @FXML
    private TableColumn<Objectif, String> categoryColumn;

    @FXML
    private TextField tf_trouble;

    @FXML
    private ChoiceBox<String> cb_categorie;

    @FXML
    private Button button_confirm;

    @FXML
    private Button button_editPersonalDetails;

    private Ortho orthophonist;
    private Patient patient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Deserialize the Ortho object
        try (FileInputStream fileIn = new FileInputStream("doc.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            orthophonist = (Ortho) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Initialize the columns
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        // Set the selection mode to MULTIPLE
        questionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Populate the choice box with categories (assuming you have a method to get categories)
        cb_categorie.setItems(FXCollections.observableArrayList("Court", "Moyen", "Long"));

        // Set button click events
        button_editPersonalDetails.setOnAction(event -> handleNextStep());
        button_confirm.setOnAction(event -> handleConfirm());
    }

    public void setPatient(Patient patient) {
        this.patient = patient;

        if (patient.getFicheDeSuivie() == null) {
            FicheDeSuivie ficheDeSuivie = new FicheDeSuivie(orthophonist, patient, FXCollections.observableArrayList());
            patient.setFicheDeSuivie(ficheDeSuivie);
        }
        // Load patient objectives into the table
        List<Objectif> objectifs = patient.getFicheDeSuivie().getListeObjectifs(); // Adjust this if necessary
        ObservableList<Objectif> objectifsData = FXCollections.observableArrayList(objectifs);
        questionsTable.setItems(objectifsData);
    }

    private void handleNextStep() {
        List<Objectif> selectedObjectifs = questionsTable.getSelectionModel().getSelectedItems();

        if (selectedObjectifs.isEmpty()) {
            // Handle no objectives selected
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tppoo/evaluation.fxml"));
            Parent root = loader.load();

            EvaluationController controller = loader.getController();
            controller.setObjectifs(selectedObjectifs);
            controller.getPatient(patient);

            Stage stage = new Stage();
            stage.setTitle("Evaluation");
            stage.setScene(new Scene(root, 700, 600));  // Adjust size as needed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConfirm() {
        String nomTrouble = tf_trouble.getText();
        ObjectifCategorie categorieTrouble = ObjectifCategorie.valueOf(cb_categorie.getValue().toUpperCase());


        if (nomTrouble != null && !nomTrouble.isEmpty() && categorieTrouble.toString() != null) {
            Objectif objectif = new Objectif(nomTrouble, categorieTrouble);
            patient.getFicheDeSuivie().getListeObjectifs().add(objectif); // Assuming this method exists
        } else {
            // Handle validation errors
            System.out.println("Please provide both Objectif name and Category.");
        }
    }
}
