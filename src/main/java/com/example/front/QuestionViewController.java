package com.example.front;

import com.example.back.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class QuestionViewController {

    @FXML
    private Label questionLabel;

    @FXML
    private TextField responseField;

    @FXML
    private TextField scoreField;

    private TestQuestionnaire testQuestionnaire;
    private TestExercice testExercice;
    private List<QuestionPatient> questionPatients;
    private List<ExercicePatient> exercicePatients;
    private int currentIndex;
    private boolean isQuestionMode = true;
    private BO bo;

    public void setTestQuestionnaire(TestQuestionnaire testQuestionnaire) {
        this.testQuestionnaire = testQuestionnaire;
        this.questionPatients = testQuestionnaire.getQuestions();
        this.currentIndex = 0;
        showQuestion();
    }

    public void setBO(BO bo){
        this.bo=bo;
    }

    public void setTestExercice(TestExercice testExercice) {
        this.testExercice = testExercice;
        this.exercicePatients = testExercice.getExercices();
    }

    private void showQuestion() {
        if (questionPatients != null && !questionPatients.isEmpty() && currentIndex < questionPatients.size()) {
            QuestionPatient currentQuestion = questionPatients.get(currentIndex);
            questionLabel.setText(currentQuestion.getQuestion());
            responseField.setText(currentQuestion.getRepensePatient());
            scoreField.setText(String.valueOf(currentQuestion.getScore()));
        } else {
            isQuestionMode = false;
            currentIndex = 0;
            showExercise();
        }
    }

    private void showExercise() {
        if (exercicePatients != null && !exercicePatients.isEmpty() && currentIndex < exercicePatients.size()) {
            ExercicePatient currentExercise = exercicePatients.get(currentIndex);
            questionLabel.setText(currentExercise.getConsigne());
            responseField.setText(currentExercise.getReponse());
            scoreField.setText(String.valueOf(currentExercise.getScore()));
        } else {
            showScoreAndObservation();
        }
    }

    private void showScoreAndObservation() {
        int totalScore = testQuestionnaire.calculerScoreTotal() + testExercice.calculerScoreTotal();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tppoo/score_observation.fxml"));
            Parent root = loader.load();

            ScoreObservationController controller = loader.getController();
            controller.setTotalScore(totalScore);
            controller.setBO(bo);
            controller.setTestExercice(testExercice);
            controller.setTestQuestionnaire(testQuestionnaire);


            Stage stage = new Stage();
            stage.setTitle("Total Score and Observation");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) questionLabel.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNext() {
        saveCurrentResponse();

        if (isQuestionMode) {
            if (currentIndex < questionPatients.size() - 1) {
                currentIndex++;
                showQuestion();
            } else {
                currentIndex = 0;
                isQuestionMode = false;
                showExercise();
            }
        } else {
            if (currentIndex < exercicePatients.size() - 1) {
                currentIndex++;
                showExercise();
            } else {
                showScoreAndObservation();
            }
        }
    }

    @FXML
    private void handlePrevious() {
        saveCurrentResponse();

        if (isQuestionMode) {
            if (currentIndex > 0) {
                currentIndex--;
                showQuestion();
            }
        } else {
            if (currentIndex > 0) {
                currentIndex--;
                showExercise();
            } else if (questionPatients != null && !questionPatients.isEmpty()) {
                currentIndex = questionPatients.size() - 1;
                isQuestionMode = true;
                showQuestion();
            }
        }
    }

    private void saveCurrentResponse() {
        if (isQuestionMode && questionPatients != null && !questionPatients.isEmpty()) {
            QuestionPatient currentQuestion = questionPatients.get(currentIndex);
            currentQuestion.setRepensePatient(responseField.getText());
            try {
                int score = Integer.parseInt(scoreField.getText());
                currentQuestion.setScore(score);
            } catch (NumberFormatException e) {
                currentQuestion.setScore(0);
            }
        } else if (!isQuestionMode && exercicePatients != null && !exercicePatients.isEmpty()) {
            ExercicePatient currentExercise = exercicePatients.get(currentIndex);
            currentExercise.setReponse(responseField.getText());
            try {
                int score = Integer.parseInt(scoreField.getText());
                currentExercise.setScore(score);
            } catch (NumberFormatException e) {
                currentExercise.setScore(0);
            }
        }
    }
}
