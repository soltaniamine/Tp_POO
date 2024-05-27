package com.example.front;
import com.example.back.*;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class Appointments implements Initializable {
    @FXML
    private Button dashBoard;
    @FXML
    private Button myPatients;
    @FXML
    private Button myAppointments;
    @FXML
    private Button tests;
    @FXML
    private Button settings;
    @FXML
    private Button payments;
    @FXML
    private Button notifications;
    @FXML
    private Button downloadData;
    @FXML
    private Button logOut;
    @FXML
    private Text nextPatientName;
    @FXML
    private Text doctorName;

    @FXML
    private AnchorPane anchor1;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private AnchorPane anchor3;
    @FXML
    private AnchorPane anchor4;
    @FXML
    private AnchorPane anchor5;
    @FXML
    private AnchorPane anchor6;
    @FXML
    private AnchorPane anchor7;
    @FXML
    private AnchorPane anchor8;
    @FXML
    private AnchorPane anchor9;
    @FXML
    private AnchorPane anchor10;
    @FXML
    private AnchorPane anchor11;
    @FXML
    private AnchorPane anchor12;
    @FXML
    private AnchorPane anchor13;
    @FXML
    private AnchorPane anchor14;
    @FXML
    private AnchorPane anchor15;
    @FXML
    private AnchorPane anchor16;
    @FXML
    private AnchorPane anchor17;
    @FXML
    private AnchorPane anchor18;
    @FXML
    private AnchorPane anchor19;
    @FXML
    private AnchorPane anchor20;
    @FXML
    private AnchorPane anchor21;
    @FXML
    private AnchorPane anchor22;
    @FXML
    private AnchorPane anchor23;
    @FXML
    private AnchorPane anchor24;
    @FXML
    private AnchorPane anchor25;
    @FXML
    private AnchorPane anchor26;
    @FXML
    private AnchorPane anchor27;
    @FXML
    private AnchorPane anchor28;
    @FXML
    private AnchorPane anchor29;
    @FXML
    private AnchorPane anchor30;
    @FXML
    private AnchorPane anchor31;
    @FXML
    private AnchorPane anchor32;
    @FXML
    private AnchorPane anchor33;
    @FXML
    private AnchorPane anchor34;
    @FXML
    private AnchorPane anchor35;
    @FXML
    private AnchorPane anchor36;
    @FXML
    private AnchorPane anchor37;
    @FXML
    private AnchorPane anchor38;
    @FXML
    private AnchorPane anchor39;
    @FXML
    private AnchorPane anchor40;
    @FXML
    private AnchorPane anchor41;
    @FXML
    private AnchorPane anchor42;

    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;
    @FXML
    private Text text6;
    @FXML
    private Text text7;
    @FXML
    private Text text8;
    @FXML
    private Text text9;
    @FXML
    private Text text10;
    @FXML
    private Text text11;
    @FXML
    private Text text12;
    @FXML
    private Text text13;
    @FXML
    private Text text14;
    @FXML
    private Text text15;
    @FXML
    private Text text16;
    @FXML
    private Text text17;
    @FXML
    private Text text18;
    @FXML
    private Text text19;
    @FXML
    private Text text20;
    @FXML
    private Text text21;
    @FXML
    private Text text22;
    @FXML
    private Text text23;
    @FXML
    private Text text24;
    @FXML
    private Text text25;
    @FXML
    private Text text26;
    @FXML
    private Text text27;
    @FXML
    private Text text28;
    @FXML
    private Text text29;
    @FXML
    private Text text30;
    @FXML
    private Text text31;
    @FXML
    private Text text32;
    @FXML
    private Text text33;
    @FXML
    private Text text34;
    @FXML
    private Text text35;
    @FXML
    private Text text36;
    @FXML
    private Text text37;
    @FXML
    private Text text38;
    @FXML
    private Text text39;
    @FXML
    private Text text40;
    @FXML
    private Text text41;
    @FXML
    private Text text42;
    @FXML
    private AnchorPane rendezVous ;
    private AnchorPane[] anchorPanes;
    private Text[] texts;
    @FXML
    private ChoiceBox<String> monthsChoiceBox;
    @FXML
    private ChoiceBox<String> yearsChoiceBox;

    @FXML
    private Text month ;

    @FXML
    private Text date ;

    private String selectedDay ;
    private String selectedMonth ;
    private String selectedYear ;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeAnchors();
        initializeTexts();
        initializeChoiceBoxes();
        initializeView() ;
        // Set event handlers for the ChoiceBoxes
        monthsChoiceBox.setOnAction(this::handleMonthSelection);
        yearsChoiceBox.setOnAction(this::handleYearSelection);
    }

    private void initializeAnchors() {
        anchorPanes = new AnchorPane[]{
                anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8,
                anchor9, anchor10, anchor11, anchor12, anchor13, anchor14, anchor15, anchor16,
                anchor17, anchor18, anchor19, anchor20, anchor21, anchor22, anchor23, anchor24,
                anchor25, anchor26, anchor27, anchor28, anchor29, anchor30, anchor31, anchor32,
                anchor33, anchor34, anchor35, anchor36, anchor37, anchor38, anchor39, anchor40,
                anchor41, anchor42
        };
        for (AnchorPane anchorPane : anchorPanes) {
            anchorPane.setOnMouseClicked(this::handleAnchorClicked);
        }
    }

    private void handleAnchorClicked(MouseEvent mouseEvent) {
        rendezVous.getChildren().clear() ;
        // Create the SVGPath graphic
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M12 2C12.5523 2 13 2.44772 13 3V11H21C21.5523 11 22 11.4477 22 12C22 12.5523 21.5523 13 21 13H13V21C13 21.5523 12.5523 22 12 22C11.4477 22 11 21.5523 11 21V13H3C2.44772 13 2 12.5523 2 12C2 11.4477 2.44772 11 3 11H11V3C11 2.44772 11.4477 2 12 2Z");
        svgPath.setFill(javafx.scene.paint.Color.WHITE);

        // Create the Button
        Button button = new Button();
        button.setLayoutX(176.0);
        button.setLayoutY(11.0);
        button.setMaxWidth(54.0);
        button.setMinHeight(36.0);
        button.setMinWidth(33.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(50.0);
        button.setPrefWidth(33.0);
        button.setStyle("-fx-background-radius: 50%; -fx-border-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px;");
        button.getStyleClass().add("add-button");
        button.setTextFill(Color.WHITE);
        button.setGraphic(svgPath);
        button.setOnAction(this::handleAddButtonClicked);
        getSelectedYear();
        getSelectedMonth();
        month.setText(selectedMonth + "-" + selectedYear);
        // Create the Text element
        Text text = new Text();
        text.setLayoutX(14.0);
        text.setLayoutY(42.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setStyle("-fx-font-weight: bold;");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(110.0);
        text.setFont(new javafx.scene.text.Font("Product Sans Bold", 18.0));
        rendezVous.getChildren().addAll(button,text) ;
        // Create the Separator (Horizontal Line)
        Separator separator = new Separator();
        separator.setLayoutX(0); // Adjust the X position as needed
        separator.setLayoutY(80.0); // Adjust the Y position as needed
        separator.setPrefWidth(243.0);
        separator.setPrefHeight(3.0); // Default height for horizontal line

        // Adding Separator to the AnchorPane
        rendezVous.getChildren().add(separator);
        processMonth(selectedMonth+"/"+selectedYear);
        AnchorPane sourcePane = (AnchorPane) mouseEvent.getSource();
        String todayIs = "";
        for (int i = 0 ; i< 42 ; i++) {
            if (!texts[i].getText().isEmpty()) {
                if (anchorPanes[i].equals(sourcePane)) {
                    anchorPanes[i].setStyle("-fx-background-color:  #90EE90; -fx-background-radius: 14;");
                    todayIs = texts[i].getText() + "-" + selectedMonth + "-" + selectedYear ;
                    selectedDay = texts[i].getText() ;
                    text.setText(selectedDay+ "-" + selectedMonth + "-" + selectedYear);
                }
                else {
                    if (!(LocalDate.now().getDayOfMonth() == Integer.parseInt(texts[i].getText()))) {
                        anchorPanes[i].setStyle("-fx-background-color:  #D6D7F2; -fx-background-radius: 14;");
                    }
                }
            }
        }

        date.setText(todayIs);
        String folderPath = DataSingleton.getInstance().getOrtho().getEmail() + "/rendez-vous-historique/" + selectedYear +"/" + removeLeadingZero(selectedMonth) + "/" + selectedDay ;
        Consultation consultation = Consultation.readFromFile(folderPath + "/Consultation.bin") ;
        if (consultation != null) {
            AnchorPane anchorPane1 = new AnchorPane();

            // Set properties for the AnchorPane
            anchorPane1.setLayoutX(8.0);
            anchorPane1.setLayoutY(85.0);
            anchorPane1.setPrefWidth(222.0);
            anchorPane1.setPrefHeight(65.0);
            // FF8C00
            anchorPane1.setStyle("-fx-background-color: #FF8C00; -fx-background-radius: 14;");
            Text saDate = new Text(consultation.getDate().toString());
            saDate.setStyle("-fx-font-weight: bold; -fx-font-family: 'Product Sans'; -fx-font-size: 12px; -fx-fill: white;");

            anchorPane1.getChildren().add(saDate);
            saDate.setLayoutX(10);
            saDate.setLayoutY(20);

            rendezVous.getChildren().add(anchorPane1);
        }
        else {
            // Calculate the Y position for the new Text node
            double maxY = 0;
            for (javafx.scene.Node node : rendezVous.getChildren()) {
                double nodeMaxY = node.getLayoutY() + node.getBoundsInParent().getHeight();
                if (nodeMaxY > maxY) {
                    maxY = nodeMaxY;
                }
            }
            AnchorPane anchorPane2 = new AnchorPane();

            // Set properties for the AnchorPane
            anchorPane2.setLayoutX(10.0);
            anchorPane2.setLayoutY(maxY + 10);
            anchorPane2.setPrefWidth(222.0);
            anchorPane2.setPrefHeight(65.0);
            // 01D5FF
            anchorPane2.setStyle("-fx-background-color: #01D5FF; -fx-background-radius: 14;");
        }
        /*AnchorPane anchorPane1 = new AnchorPane();

        // Set properties for the AnchorPane
        anchorPane1.setLayoutX(8.0);
        anchorPane1.setLayoutY(50.0);
        anchorPane1.setPrefWidth(222.0);
        anchorPane1.setPrefHeight(65.0);
        // FF8C00
        anchorPane1.setStyle("-fx-background-color: #FF8C00; -fx-background-radius: 14;");
        AnchorPane anchorPane2 = new AnchorPane();

        // Set properties for the AnchorPane
        anchorPane2.setLayoutX(8.0);
        anchorPane2.setLayoutY(125.0);
        anchorPane2.setPrefWidth(222.0);
        anchorPane2.setPrefHeight(65.0);
        // 01D5FF
        anchorPane2.setStyle("-fx-background-color: #01D5FF; -fx-background-radius: 14;");

        AnchorPane anchorPane3 = new AnchorPane();

        // Set properties for the AnchorPane
        anchorPane3.setLayoutX(8.0);
        anchorPane3.setLayoutY(200.0);
        anchorPane3.setPrefWidth(222.0);
        anchorPane3.setPrefHeight(65.0);
        // 28A745
        anchorPane3.setStyle("-fx-background-color: #28A745; -fx-background-radius: 14;");
        rendezVous.getChildren().add(anchorPane1) ;
        rendezVous.getChildren().add(anchorPane2) ;
        rendezVous.getChildren().add(anchorPane3) ;*/
    }
    private void initializeTexts() {
        texts = new Text[]{
                text1, text2, text3, text4, text5, text6, text7, text8,
                text9, text10, text11, text12, text13, text14, text15, text16,
                text17, text18, text19, text20, text21, text22, text23, text24,
                text25, text26, text27, text28, text29, text30, text31, text32,
                text33, text34, text35, text36, text37, text38, text39, text40,
                text41, text42
        };
    }


    private void initializeChoiceBoxes() {
        // Get the current year
        int currentYear = LocalDate.now().getYear();

        // Add items to the ChoiceBox with years from -5 to +5 years relative to the current year
        for (int year = currentYear ; year < currentYear + 3; year++) {
            yearsChoiceBox.getItems().add(String.valueOf(year));
        }

        // Set default value to the current year
        yearsChoiceBox.setValue(String.valueOf(currentYear));

        // Add items to the ChoiceBox
        monthsChoiceBox.getItems().addAll(
                "1- Jan", "2- Fev", "3- Mars", "4- Avr",
                "5- Mai", "6- Juin", "7- Juil", "8- Août",
                "9- Sept", "10- Oct", "11- Nov", "12- Dec"
        );

        String [] months = {"1- Jan", "2- Fev", "3- Mars", "4- Avr",
                "5- Mai", "6- Juin", "7- Juil", "8- Août",
                "9- Sept", "10- Oct", "11- Nov", "12- Dec"};

        // Set default value to the current month
        Month currentMonth = LocalDate.now().getMonth();
        int monthIndex = currentMonth.getValue(); // Get the month as an integer (1-12)
        selectedMonth = months[monthIndex-1];
        System.out.println(selectedMonth);
        monthsChoiceBox.setValue(selectedMonth);
    }

    public void initializeView() {
        getSelectedYear();
        getSelectedMonth();
        month.setText(selectedMonth + "-" + selectedYear);
        processMonth(selectedMonth+"/"+selectedYear);
        date.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        LocalDate currentDate = LocalDate.now();

        // Format the current day as "dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        selectedDay = currentDate.format(formatter);
    }

    private void getSelectedMonth() {
        int selectedIndex = monthsChoiceBox.getSelectionModel().getSelectedIndex();
        selectedMonth = String.format("%02d", selectedIndex + 1); // Return month number as two-digit string (01-12)
    }

    private void getSelectedYear() {
        selectedYear = yearsChoiceBox.getValue();
    }

    @FXML
    private void handleMonthSelection(ActionEvent event) {
        getSelectedYear();
        getSelectedMonth();
        month.setText(selectedMonth + "-" + selectedYear);
        processMonth(selectedMonth+"/"+selectedYear);
    }

    @FXML
    private void handleYearSelection(ActionEvent event) {
        getSelectedYear();
        getSelectedMonth();
        month.setText(selectedMonth + "-" + selectedYear);
        processMonth(selectedMonth+"/"+selectedYear);
    }
    private static int[] findSquarePosition(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfMonth = date.getDayOfMonth();

        // Get the column based on the day of the week, with Sunday as the first day
        int column = (dayOfWeek == DayOfWeek.SUNDAY) ? 1 : dayOfWeek.getValue() + 1;

        // Calculate the row, considering the first day of the month
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        int offset = (firstDayOfMonth.getDayOfWeek() == DayOfWeek.SUNDAY) ? 0 : firstDayOfMonth.getDayOfWeek().getValue();
        int row = (dayOfMonth + offset - 1) / 7 + 1;

        int[] position = new int[2];
        position[0] = row;
        position[1] = column;
        return position;
    }


    private void processMonth(String monthYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yearMonth = YearMonth.parse(monthYear, formatter);
        int elementIndex = 0 ;
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            String dateStr = String.format("%02d/%02d/%d", day, yearMonth.getMonthValue(), yearMonth.getYear());
            int[] position = findSquarePosition(dateStr);

            elementIndex = ((position[0] - 1) * 7 + position[1]) - 1;

            if (day == 1) {
                for (int notUsed = elementIndex - 1; notUsed >= 0; notUsed--) {
                    anchorPanes[notUsed].setStyle("-fx-background-color:  white; -fx-background-radius: 14; -fx-opacity:0.1;");
                    texts[notUsed].setText("");
                }
            }
            if (elementIndex >= 0 && elementIndex < anchorPanes.length) {
                if (isToday(dateStr)) {
                    // Apply special style for today's date
                    anchorPanes[elementIndex].setStyle("-fx-background-color:  #FF7F7F; -fx-background-radius: 14;");
                    texts[elementIndex].setStyle("-fx-font-weight: bold; -fx-font-family: 'Product Sans'; -fx-font-size: 12px; -fx-fill: white;");
                } else {
                    anchorPanes[elementIndex].setStyle("-fx-background-color: #D6D7F2; -fx-background-radius: 14;");
                    texts[elementIndex].setStyle("-fx-font-weight: bold; -fx-font-family: 'Product Sans'; -fx-font-size: 12px;");
                }
                texts[elementIndex].setText(String.valueOf(getDayFromDateStr(dateStr)));
            }
        }
        for (int notUsed = elementIndex + 1 ; notUsed<42 ; notUsed++) {
            anchorPanes[notUsed].setStyle("-fx-background-color:  white; -fx-background-radius: 14; -fx-opacity:0.1;");
            texts[notUsed].setText("");
        }
    }

    private static boolean isToday(String dateStr) {
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse the input date string to LocalDate
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // Get the current date
        LocalDate today = LocalDate.now();

        // Compare the parsed date with the current date
        return date.equals(today);
    }
    private static int getDayFromDateStr(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return date.getDayOfMonth();
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
    public void handleAddButtonClicked(Event event) {
        try {
            String selectedDate = DataSingleton.getInstance().getOrtho().getEmail() + "/rendez-vous-historique/" + selectedYear + "/" + selectedMonth + "/" + selectedDay;
            // get and Set data in the singleton
            DataSingleton.getInstance().setData(DataSingleton.getInstance().getOrtho().getEmail() + "/rendez-vous-historique/" + selectedYear + "/" + removeLeadingZero(selectedMonth) + "/" + selectedDay);
            DataSingleton.getInstance().setDate(selectedYear + "-" + selectedMonth + "-" + selectedDay);

            String fxmlFilePath = "/com/example/tppoo/add-appointment.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Scene newScene = new Scene(loader.load(), 600, 570);

            // Load the icon image
            Image icon = new Image("file:C:/Users/ya727/Desktop/TP_POO/src/main/resources/com/example/tppoo/pictures/logo_blue.png");

            // Get the current stage (assuming the event source is a Node)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.getIcons().add(icon);
            newStage.setTitle("Add Appointment");
            newStage.setScene(newScene);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Display an error message to the user
            showErrorDialog("Error loading the FXML file", "Could not load the add-appointment.fxml file.");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
            showErrorDialog("Unexpected Error", "An unexpected error occurred.");
        }
    }

    private void showErrorDialog(String title, String message) {
        // You can use an Alert dialog to show the error message
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String removeLeadingZero(String month) {
        if (month.startsWith("0")) {
            return month.substring(1);
        }
        return month;
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
