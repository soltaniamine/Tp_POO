package com.example.front;

import com.example.back.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ScoreObservationController {

    @FXML
    private Label totalScoreLabel;

    @FXML
    private TextArea observationTextArea;

    private BO bo;
    private TestQuestionnaire tq;
    private TestExercice te;


    private int totalScore;

    public void setTotalScore(int score) {
        this.totalScore = score;
        totalScoreLabel.setText(String.valueOf(score));
    }

    public void setBO (BO bo){
        this.bo = bo;
    }

    public void setTestQuestionnaire(TestQuestionnaire tq){
        this.tq=tq;

    }

    public void setTestExercice(TestExercice te){
        this.te=te;
    }



    @FXML
    private void handleSubmit() {
        String observation = observationTextArea.getText();
        // Save or process the observation as needed
        System.out.println("Observation: " + observation);
        ObservationClinique oc = new ObservationClinique(observation);

        EpreuveClinique ec = new EpreuveClinique(bo,tq,te,oc,totalScore);



        // Close the stage after submission
        Stage stage = (Stage) observationTextArea.getScene().getWindow();
        stage.close();
    }
}
