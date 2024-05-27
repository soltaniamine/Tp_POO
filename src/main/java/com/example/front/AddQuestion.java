package com.example.front;

import com.example.back.Ortho;
import com.example.back.Question;
import com.example.back.Categorie;
import com.example.back.TypeQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AddQuestion {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabLibre;

    @FXML
    private TextField libreQuestionField;

    @FXML
    private Button libreAddButton;

    @FXML
    private Tab tabQCM;
    @FXML
    private TextField qcmQuestionField;

    @FXML
    private TextField qcmOption1Field;

    @FXML
    private TextField qcmOption2Field;

    @FXML
    private TextField qcmOption3Field;

    @FXML
    private TextField qcmOption4Field;

    @FXML
    private TextField qcmOption5Field;

    @FXML
    private TextField qcmOption6Field;
    @FXML
    private TextField qcmReponse1Field;

    @FXML
    private TextField qcmReponse2Field;

    @FXML
    private TextField qcmReponse3Field;

    @FXML
    private TextField qcmReponse4Field;

    @FXML
    private TextField qcmReponse5Field;

    @FXML
    private TextField qcmReponse6Field;

    @FXML
    private Button qcmAddButton;

    @FXML
    private Tab tabQCU;


    @FXML
    private TextField qcuQuestionField;

    @FXML
    private TextField qcuOption1Field;

    @FXML
    private TextField qcuOption2Field;

    @FXML
    private TextField qcuOption3Field;

    @FXML
    private TextField qcuOption4Field;

    @FXML
    private TextField qcuOption5Field;

    @FXML
    private TextField qcuOption6Field;

    @FXML
    private TextField qcuReponseField;

    @FXML
    private Button qcuAddButton;

    private ArrayList<Question> questionList = new ArrayList<>();

    @FXML
    private void initialize() {
        if (DataSingleton.getInstance().getQuestion() != null) {
            Ortho ortho = DataSingleton.getInstance().getOrtho();

            if (DataSingleton.getInstance().getQuestion().getTypes().equals("LIBRE")) {
                libreQuestionField.setText(DataSingleton.getInstance().getQuestion().getQuestions());
                String questionText = libreQuestionField.getText();
                questionList = Question.readFromFile(ortho.getEmail() + "/question_libre.bin");
                removeQuestionFromList(questionText, questionList);
                Question.writeToFile(questionList, ortho.getEmail() + "/question_libre.bin");
            }

            if (DataSingleton.getInstance().getQuestion().getTypes().equals("QCM")) {
                qcmQuestionField.setText(DataSingleton.getInstance().getQuestion().getQuestions());
                String questionText = qcmQuestionField.getText();
                questionList = Question.readFromFile(ortho.getEmail() + "/qcm.bin");
                removeQuestionFromList(questionText, questionList);
                Question.writeToFile(questionList, ortho.getEmail() + "/qcm.bin");
            }

            if (DataSingleton.getInstance().getQuestion().getTypes().equals("QCU")) {
                qcuQuestionField.setText(DataSingleton.getInstance().getQuestion().getQuestions());
                String questionText = qcuQuestionField.getText();
                questionList = Question.readFromFile(ortho.getEmail() + "/qcu.bin");
                removeQuestionFromList(questionText, questionList);
                Question.writeToFile(questionList, ortho.getEmail() + "/qcu.bin");
            }
        }

        // Set up event handlers
        libreAddButton.setOnAction(event -> addLibreQuestion());
        qcmAddButton.setOnAction(event -> addQCMQuestion());
        qcuAddButton.setOnAction(event -> addQCUQuestion());
    }

    /**
     * Removes a question from the given list if the prompt matches the specified question text.
     *
     * @param questionText The text of the question to remove.
     * @param questionList The list of questions to search and remove the question from.
     */
    private void removeQuestionFromList(String questionText, List<Question> questionList) {
        Iterator<Question> iterator = questionList.iterator();
        while (iterator.hasNext()) {
            Question question = iterator.next();
            if (question.getPrompt().equals(questionText)) {
                iterator.remove();  // Safe removal
            }
        }
    }

    private void addLibreQuestion() {
        if (validateLibreFields()) {
            Ortho ortho = DataSingleton.getInstance().getOrtho();
            String questionText = libreQuestionField.getText();
            questionList = Question.readFromFile(ortho.getEmail() + "/question_libre.bin");
            Question question = new Question(questionText, Categorie.CHARACTER_BEHAVIOR, TypeQuestion.LIBRE, null, Arrays.asList(questionText));
            if (DataSingleton.getInstance().getQuestion()!= null) {
                for (Question question1 : questionList) {
                    if (question1.getPrompt().equals(question.getPrompt())) {
                        questionList.remove(question1);
                        questionList.add(question);
                        question.writeToFile(questionList,ortho.getEmail() + "/qcm.bin");
                        clearQCMFields();
                        showAlert("Success", "QCM question edited successfully.", Alert.AlertType.INFORMATION);
                        return;
                    }
                }
            }
            questionList.add(question);
            question.writeToFile(questionList,ortho.getEmail() + "/question_libre.bin");
            clearLibreFields();
            showAlert("Success", "Libre question added successfully.", Alert.AlertType.INFORMATION);
        }
    }

    private void addQCMQuestion() {
        if (validateQCMFields()) {
            Ortho ortho = DataSingleton.getInstance().getOrtho();
            String questionText = qcmQuestionField.getText();
            List<String> options = Arrays.asList(
                    qcmOption1Field.getText(),
                    qcmOption2Field.getText(),
                    qcmOption3Field.getText(),
                    qcmOption4Field.getText(),
                    qcmOption5Field.getText(),
                    qcmOption6Field.getText()
            );
            List<String> responses = Arrays.asList(
                    qcmReponse1Field.getText(),
                    qcmReponse2Field.getText(),
                    qcmReponse3Field.getText(),
                    qcmReponse4Field.getText(),
                    qcmReponse5Field.getText(),
                    qcmReponse6Field.getText()
            );
            questionList = Question.readFromFile(ortho.getEmail() + "/qcm.bin");
            Question question = new Question(questionText, Categorie.CHARACTER_BEHAVIOR, TypeQuestion.QCM, options, responses);
            if (DataSingleton.getInstance().getQuestion()!= null) {
                for (Question question1 : questionList) {
                    if (question1.getPrompt().equals(question.getPrompt())) {
                        questionList.remove(question1);
                        questionList.add(question);
                        question.writeToFile(questionList,ortho.getEmail() + "/qcm.bin");
                        clearQCMFields();
                        showAlert("Success", "QCM question edited successfully.", Alert.AlertType.INFORMATION);
                        return;
                    }
                }
            }
            questionList.add(question);
            question.writeToFile(questionList,ortho.getEmail() + "/qcm.bin");
            clearQCMFields();
            showAlert("Success", "QCM question added successfully.", Alert.AlertType.INFORMATION);
        }
    }

    private void addQCUQuestion() {
        if (validateQCUFields()) {
            Ortho ortho = DataSingleton.getInstance().getOrtho();
            String questionText = qcuQuestionField.getText();
            List<String> options = Arrays.asList(
                    qcuOption1Field.getText(),
                    qcuOption2Field.getText(),
                    qcuOption3Field.getText(),
                    qcuOption4Field.getText(),
                    qcuOption5Field.getText(),
                    qcuOption6Field.getText()
            );
            List<String> responses = Arrays.asList(qcuReponseField.getText());
            questionList = Question.readFromFile(ortho.getEmail() + "/qcu.bin");
            Question question = new Question(questionText, Categorie.CHARACTER_BEHAVIOR, TypeQuestion.QCU, options, responses);
            questionList.add(question);
            question.writeToFile(questionList,ortho.getEmail() + "/qcu.bin");
            clearQCUFields();
            showAlert("Success", "QCU question added successfully.", Alert.AlertType.INFORMATION);
        }
    }

    private boolean validateLibreFields() {
        if (libreQuestionField.getText().isEmpty()) {
            showAlert("Error", "Question field is empty for Libre question.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private boolean validateQCMFields() {
        if (qcmQuestionField.getText().isEmpty()) {
            showAlert("Error", "Question field is empty for QCM question.", Alert.AlertType.ERROR);
            return false;
        }
        if (qcmOption1Field.getText().isEmpty() || qcmOption2Field.getText().isEmpty() ||
                qcmOption3Field.getText().isEmpty() || qcmOption4Field.getText().isEmpty()) {
            showAlert("Error", "At least the first four options must be filled for QCM question.", Alert.AlertType.ERROR);
            return false;
        }
        if (!validateResponses(Arrays.asList(
                qcmReponse1Field.getText(),
                qcmReponse2Field.getText(),
                qcmReponse3Field.getText(),
                qcmReponse4Field.getText(),
                qcmReponse5Field.getText(),
                qcmReponse6Field.getText()
        ))) {
            showAlert("Error", "At least one response must be provided for QCM question.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private boolean validateQCUFields() {
        if (qcuQuestionField.getText().isEmpty()) {
            showAlert("Error", "Question field is empty for QCU question.", Alert.AlertType.ERROR);
            return false;
        }
        if (qcuOption1Field.getText().isEmpty() || qcuOption2Field.getText().isEmpty() ||
                qcuOption3Field.getText().isEmpty() || qcuOption4Field.getText().isEmpty()) {
            showAlert("Error", "At least the first four options must be filled for QCU question.", Alert.AlertType.ERROR);
            return false;
        }
        if (qcuReponseField.getText().isEmpty()) {
            showAlert("Error", "Response field is empty for QCU question.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void clearLibreFields() {
        libreQuestionField.clear();
    }

    private void clearQCMFields() {
        qcmQuestionField.clear();
        qcmOption1Field.clear();
        qcmOption2Field.clear();
        qcmOption3Field.clear();
        qcmOption4Field.clear();
        qcmOption5Field.clear();
        qcmOption6Field.clear();
        qcmReponse1Field.clear();
        qcmReponse2Field.clear();
        qcmReponse3Field.clear();
        qcmReponse4Field.clear();
        qcmReponse5Field.clear();
        qcmReponse6Field.clear();
    }

    private void clearQCUFields() {
        qcuQuestionField.clear();
        qcuOption1Field.clear();
        qcuOption2Field.clear();
        qcuOption3Field.clear();
        qcuOption4Field.clear();
        qcuOption5Field.clear();
        qcuOption6Field.clear();
        qcuReponseField.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateResponses(List<String> responses) {
        for (String response : responses) {
            if (response != null && !response.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
