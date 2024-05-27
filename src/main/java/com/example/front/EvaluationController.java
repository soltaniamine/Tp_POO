package com.example.front;

import com.example.back.FicheDeSuivie;
import com.example.back.Objectif;
import com.example.back.ObjectifPatient;
import com.example.back.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EvaluationController implements Initializable {

    @FXML
    private VBox vboxEvaluation;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnFinish;

    private List<Objectif> objectifs;
    private List<ObjectifPatient> objectifPatients = new ArrayList<>();
    private Patient patient;
    private int currentIndex = 0;

    public void getPatient(Patient patient){
        this.patient=patient;
    }

    public void setObjectifs(List<Objectif> objectifs) {
        this.objectifs = objectifs;
        if (objectifs != null && !objectifs.isEmpty()) {
            showObjective(currentIndex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize method left empty intentionally
    }

    @FXML
    private void handlePrevious() {
        if (currentIndex > 0) {
            saveCurrentObjective();
            currentIndex--;
            showObjective(currentIndex);
        }
    }

    @FXML
    private void handleNext() {
        if (currentIndex < objectifs.size() - 1) {
            saveCurrentObjective();
            currentIndex++;
            showObjective(currentIndex);
        }
    }

    @FXML
    private void handleFinish() {
        saveCurrentObjective();
        // Process the final list of ObjectifPatient as needed
        System.out.println("Evaluation finished. Process the results here.");
        for (ObjectifPatient objPat : objectifPatients) {
            System.out.println("Objective: " + objPat.getNom() + ", Category: " + objPat.getCategorie() + ", Value: " + objPat.getScore());
        }
        FicheDeSuivie fs= new FicheDeSuivie(patient,objectifPatients);
        // Close the evaluation window if necessary
        btnFinish.getScene().getWindow().hide();
    }

    private void showObjective(int index) {
        vboxEvaluation.getChildren().clear();
        Objectif objectif = objectifs.get(index);
        Label label = new Label(objectif.getNom());
        TextField textField = new TextField();
        textField.setText(findValueForObjectif(objectif));
        vboxEvaluation.getChildren().addAll(label, textField);

        btnPrevious.setDisable(index == 0);
        btnNext.setDisable(index == objectifs.size() - 1);
    }

    private void saveCurrentObjective() {
        if (!vboxEvaluation.getChildren().isEmpty()) {
            Label label = (Label) vboxEvaluation.getChildren().get(0);
            TextField textField = (TextField) vboxEvaluation.getChildren().get(1);
            String objectiveName = label.getText();
            int value = Integer.parseInt(textField.getText());

            for (ObjectifPatient objPat : objectifPatients) {
                if (objPat.getNom().equals(objectiveName)) {
                    objPat.setScore(value);
                    return;
                }
            }

            Objectif currentObjectif = objectifs.get(currentIndex);
            ObjectifPatient newObjPat = new ObjectifPatient(currentObjectif.getNom(), currentObjectif.getCategorie(), value);
            objectifPatients.add(newObjPat);
        }
    }

    private String findValueForObjectif(Objectif objectif) {
        for (ObjectifPatient objPat : objectifPatients) {
            if (objPat.getNom().equals(objectif.getNom())) {
                return String.valueOf(objPat.getScore());
            }
        }
        return "";
    }
}
