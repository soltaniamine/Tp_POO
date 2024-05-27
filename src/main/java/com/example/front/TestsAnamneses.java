package com.example.front;

import com.example.back.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestsAnamneses implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TableView<QuestionFront> tableView;
    @FXML
    private TableColumn<QuestionFront, String> nameColumn;
    @FXML
    private TableColumn<QuestionFront, String> questionsColumn;
    @FXML
    private TableColumn<QuestionFront, String> typesColumn;
    @FXML
    private TableColumn<QuestionFront, String> responsesColumn;
    @FXML
    private TableColumn<QuestionFront, Void> operationsColumn;
    @FXML
    private TableView<Exercice> tableView2;
    @FXML
    private TableColumn<Exercice, String> consigne;
    @FXML
    private TableColumn<Exercice, String> materiel;
    @FXML
    private TableColumn<Exercice, Void> operations2;
    @FXML private TableView<Anamnese> tableView3 ;
    @FXML private TableColumn<Anamnese, String> question;
    @FXML private TableColumn<Anamnese,String> categorie ;
    @FXML private TableColumn<Anamnese,Void> operations3 ;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    private int tabNumber = 1 ;

    private ObservableList<QuestionFront> questionData = FXCollections.observableArrayList();
    private ObservableList<Exercice> exoData = FXCollections.observableArrayList();
    private ObservableList<Anamnese> anamneseData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTabPane();
        loadQuestions();
        loadTableColumns();
        setupOperationsColumn();
        setupTabChangeListener();
    }

    private void initializeTabPane() {
        tabPane.getSelectionModel().selectFirst(); // Select the first tab by default
    }

    private void initializeChoiceBox() {
        choiceBox.getItems().addAll("ANAM", "QES", "EXOS");
        choiceBox.setValue("QES");
    }

    private void loadQuestions() {
        questionData.clear();
        Ortho ortho = DataSingleton.getInstance().getOrtho();
        ArrayList<com.example.back.Question> questionsLibre = com.example.back.Question.readFromFile(ortho.getEmail() + "/question_libre.bin");
        ArrayList<com.example.back.Question> qcms = com.example.back.Question.readFromFile(ortho.getEmail() + "/qcm.bin");
        ArrayList<com.example.back.Question> qcus = com.example.back.Question.readFromFile(ortho.getEmail() + "/qcu.bin");
        ArrayList<com.example.back.Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(questionsLibre);
        allQuestions.addAll(qcus);
        allQuestions.addAll(qcms);

        for (com.example.back.Question question : allQuestions) {
            QuestionFront question1 = null;
            if (question.getType().equals(TypeQuestion.LIBRE)) {
                question1 = new QuestionFront(question.getPrompt(), "LIBRE", "NONE", "Edit/Delete");
            } else if (question.getType().equals(TypeQuestion.QCM)) {
                String reponses = String.join(" ", question.getRepense());
                question1 = new QuestionFront(question.getPrompt(), "QCM", reponses, "Edit/Delete");
            } else if (question.getType().equals(TypeQuestion.QCU)) {
                question1 = new QuestionFront(question.getPrompt(), "QCU", question.getRepense().get(0), "Edit/Delete");
            }
            questionData.add(question1);
        }
    }

    private void loadTableColumns() {
        questionsColumn.setCellValueFactory(new PropertyValueFactory<>("questions"));
        typesColumn.setCellValueFactory(new PropertyValueFactory<>("types"));
        responsesColumn.setCellValueFactory(new PropertyValueFactory<>("responses"));
        consigne.setCellValueFactory(new PropertyValueFactory<>("consigne"));
        materiel.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        tableView.setItems(questionData);
    }

    private void setupOperationsColumn() {
        operationsColumn.setCellFactory(new Callback<TableColumn<QuestionFront, Void>, TableCell<QuestionFront, Void>>() {
            @Override
            public TableCell<QuestionFront, Void> call(final TableColumn<QuestionFront, Void> param) {
                return new TableCell<QuestionFront, Void>() {

                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");
                    private final HBox pane = new HBox(editButton, deleteButton);

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            QuestionFront data = getTableView().getItems().get(getIndex());
                            handleEditButton(data);
                        });

                        deleteButton.setOnAction((ActionEvent event) -> {
                            QuestionFront data = getTableView().getItems().get(getIndex());
                            handleDeleteButton(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
            }
        });
    }

    private void setupTabChangeListener() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == tab1) {
                loadTableViewData();
                tabNumber = 1;
            } else if (newTab == tab2) {
                handleTab2Selection();
                tabNumber = 2;
            } else if (newTab == tab3) {
                handleTab3Selection();
                tabNumber = 3;
            }
        });
    }

    private void handleTab2Selection() {
        Ortho ortho = DataSingleton.getInstance().getOrtho();
        ArrayList<Exercice> allExercices = Exercice.readFromFile(ortho.getEmail() + "/exos.bin");
        exoData.clear();
        exoData.addAll(allExercices);
        tableView2.setItems(exoData);

        operations2.setCellFactory(new Callback<TableColumn<Exercice, Void>, TableCell<Exercice, Void>>() {
            @Override
            public TableCell<Exercice, Void> call(final TableColumn<Exercice, Void> param) {
                return new TableCell<Exercice, Void>() {

                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");
                    private final HBox pane = new HBox(editButton, deleteButton);

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            Exercice data = getTableView().getItems().get(getIndex());
                            handleEditExoButton(data);
                        });

                        deleteButton.setOnAction((ActionEvent event) -> {
                            Exercice data = getTableView().getItems().get(getIndex());
                            handleDeleteExoButton(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
            }
        });
    }

    private void handleDeleteExoButton(Exercice data) {
        exoData.remove(data);
    }

    private void handleEditExoButton(Exercice data) {
        DataSingleton.getInstance().setExercice(data);
        try {
            String fxmlFilePath = "/com/example/tppoo/add_exo.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Scene newScene = new Scene(loader.load(), 600, 290);

            // Load the icon image
            Image icon = new Image("file:C:/Users/ya727/Desktop/TP_POO/src/main/resources/com/example/tppoo/pictures/logo_blue.png");

            // Get the current stage (assuming the event source is a Node)
            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.getIcons().add(icon);
            newStage.setTitle("Add/Edit EXO");
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Display an error message to the user
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
        }

    }

    private void handleTab3Selection() {
        // Handle content for tab 3
        /*Ortho ortho = DataSingleton.getInstance().getOrtho();
        Anamnese anamnese = Anamnese.readFromFile(ortho.getEmail()+"/anamneses.bin")
        exoData.addAll(allExercices);
        tableView2.setItems(exoData);

        operations2.setCellFactory(new Callback<TableColumn<Exercice, Void>, TableCell<Exercice, Void>>() {
            @Override
            public TableCell<Exercice, Void> call(final TableColumn<Exercice, Void> param) {
                return new TableCell<Exercice, Void>() {

                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");
                    private final HBox pane = new HBox(editButton, deleteButton);

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            Exercice data = getTableView().getItems().get(getIndex());
                            handleEditExoButton(data);
                        });

                        deleteButton.setOnAction((ActionEvent event) -> {
                            Exercice data = getTableView().getItems().get(getIndex());
                            handleDeleteExoButton(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
            }
        });*/
    }

    private void loadTableViewData() {
        questionData.clear();
        Ortho ortho = DataSingleton.getInstance().getOrtho();
        ArrayList<com.example.back.Question> questionsLibre = com.example.back.Question.readFromFile(ortho.getEmail() + "/question_libre.bin");
        ArrayList<com.example.back.Question> qcms = com.example.back.Question.readFromFile(ortho.getEmail() + "/qcm.bin");
        ArrayList<com.example.back.Question> qcus = com.example.back.Question.readFromFile(ortho.getEmail() + "/qcu.bin");
        ArrayList<com.example.back.Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(questionsLibre);
        allQuestions.addAll(qcus);
        allQuestions.addAll(qcms);
        for (com.example.back.Question question : allQuestions) {
            QuestionFront question1 = null;
            if (question.getType().equals(TypeQuestion.LIBRE)) {
                question1 = new QuestionFront(question.getPrompt(), "LIBRE", "NONE", "Edit/Delete");
            } else if (question.getType().equals(TypeQuestion.QCM)) {
                String reponses = String.join("-", question.getRepense());
                question1 = new QuestionFront(question.getPrompt(), "QCM", reponses, "Edit/Delete");
            } else if (question.getType().equals(TypeQuestion.QCU)) {
                question1 = new QuestionFront(question.getPrompt(), "QCU", question.getRepense().get(0), "Edit/Delete");
            }
            questionData.add(question1);
        }
        tableView.setItems(questionData);
    }

    private void handleDeleteButton(QuestionFront data) {
        Ortho ortho = DataSingleton.getInstance().getOrtho();
        questionData.remove(data);
        //System.out.println("Edit: " + data.getPrompt());
    }

    private void handleEditButton(QuestionFront data) {
        DataSingleton.getInstance().setQuestion(data);
        try {
            String fxmlFilePath = "/com/example/tppoo/add_question.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Scene newScene = new Scene(loader.load(), 600, 355);

            // Load the icon image
            Image icon = new Image("file:C:/Users/ya727/Desktop/TP_POO/src/main/resources/com/example/tppoo/pictures/logo_blue.png");

            // Get the current stage (assuming the event source is a Node)
            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.getIcons().add(icon);
            newStage.setTitle("Add/Edit Question");
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Display an error message to the user
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
        }
        //System.out.println("Delete: " + data.getPrompt());
    }

    public void handleAddButton(Event event) {
        try {
            double height = 0;
            String fxmlFilePath = "";

            if (tabNumber == 1) {
                fxmlFilePath = "/com/example/tppoo/add_question.fxml";
                height = 355;
            } else if (tabNumber == 3) {
                fxmlFilePath = "/com/example/tppoo/add_anam.fxml";
                height = 290;
            } else if (tabNumber == 2) {
                fxmlFilePath = "/com/example/tppoo/add_exo.fxml";
                height = 290;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Scene newScene = new Scene(loader.load(), 600, height);

            // Load the icon image
            Image icon = new Image("file:C:/Users/ya727/Desktop/TP_POO/src/main/resources/com/example/tppoo/pictures/logo_blue.png");

            // Get the current stage (assuming the event source is a Node)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.getIcons().add(icon);
            newStage.setTitle("Add Question");
            newStage.setScene(newScene);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Display an error message to the user
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
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
