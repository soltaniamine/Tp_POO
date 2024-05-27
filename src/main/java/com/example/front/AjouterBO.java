package com.example.front;

import com.example.back.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.back.Patient;
import com.example.front.AjouterBO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterBO implements Initializable {

    @FXML
    private TableView<Question> questionsTable;

    @FXML
    private TableColumn<Question, String> questionColumn;

    @FXML
    private TableColumn<Question, String> categoryColumn;

    @FXML
    private TableView<Exercice> exercisesTable;

    @FXML
    private TableColumn<Exercice, String> exerciseColumn;

    @FXML
    private TableColumn<Exercice, Integer> scoreColumn;

    @FXML
    private Button button_editPersonalDetails;

    @FXML
    private TextField tf_trouble;

    @FXML
    private ChoiceBox<CategorieTrouble> cb_categorie;

    @FXML
    private Button button_confirmProjet;

    @FXML
    private Button button_confirm;

    @FXML
    private TextField tf_demarche;

    private Ortho doctor;
    private Patient patient;
    private BO bo;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the columns
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("prompt"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("consigne"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("materiel"));

        // Allow multiple selection
        questionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        exercisesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Assuming you have a method to get the doctor instance
        doctor = getDoctorInstance();

        // Populate the tables
        questionsTable.getItems().setAll(doctor.getListeQuestions());
        exercisesTable.getItems().setAll(doctor.getListeExercices());
        cb_categorie.getItems().addAll(CategorieTrouble.values());

        // Set button click event
        button_editPersonalDetails.setOnAction(event -> handleNextStep());
        button_confirm.setOnAction(event->handleConfirmTrouble());
        button_confirmProjet.setOnAction(event->handleConfirmProjet());



    }
    public void ajouterBo(Patient patient){
        BO bo1 = new BO(doctor,patient);
        this.patient=patient;
        this.bo=bo1;
    }


    private Ortho getDoctorInstance() {
        // Deserialize the doctor object
        Ortho doct1 = null;
        try (FileInputStream fileIn = new FileInputStream("doc.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            doct1 = (Ortho) in.readObject();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Ortho class not found");
            c.printStackTrace();
        }
        return doct1;
    }

    private void handleNextStep() {
        List<com.example.back.Question> selectedQuestions = questionsTable.getSelectionModel().getSelectedItems();
        List<Exercice> selectedExercises = exercisesTable.getSelectionModel().getSelectedItems();

        if (selectedQuestions.isEmpty() && selectedExercises.isEmpty()) {
            // Handle no questions and exercises selected
            return;
        }

        // Create a TestQuestionnaire and TestExercice with the selected questions and exercises
        TestQuestionnaire testQuestionnaire = null;
        TestExercice testExercice = null;

        if (!selectedQuestions.isEmpty()) {
            testQuestionnaire = new TestQuestionnaire(patient, "Test Name", selectedQuestions);
        }

        if (!selectedExercises.isEmpty()) {
            testExercice = new TestExercice(patient, "Test Name", selectedExercises);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tppoo/question-view.fxml"));
            Parent root = loader.load();

            QuestionViewController controller = loader.getController();
            controller.setBO(bo);
            if (testQuestionnaire != null) {
                controller.setTestQuestionnaire(testQuestionnaire);
            }
            if (testExercice != null) {
                controller.setTestExercice(testExercice);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void handleConfirmTrouble() {
        String nomTrouble = tf_trouble.getText();
        CategorieTrouble categorieTrouble = cb_categorie.getValue();

        if (nomTrouble != null && !nomTrouble.isEmpty() && categorieTrouble != null) {
            ajouterDiagnostic(nomTrouble, categorieTrouble);
        } else {
            // Handle validation errors
            System.out.println("Please provide both Trouble name and Category.");
        }
    }

    public void ajouterDiagnostic(String nomTrouble, CategorieTrouble categorieTrouble) {
        Trouble trouble = new Trouble(nomTrouble, categorieTrouble);
        Diagnostic diagnostic = new Diagnostic(trouble, patient);
        bo.ajouterDiagnostic(diagnostic); // Assuming this method exists in BO
        System.out.println("diagnostic added");
        System.out.println(bo.getEpreuvesClinique().getFirst().getScore());
    }

    @FXML
    private void handleConfirmProjet() {
        String demarche = tf_demarche.getText();

        if (demarche != null && !demarche.isEmpty()) {
            bo.ajouterProjet(demarche);
            System.out.println("Projet added");
        } else {
            // Handle validation errors
            System.out.println("Please provide the Demarche.");
        }
    }

}