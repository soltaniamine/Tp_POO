package com.example.front;
import com.example.back.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FilesHandlingFunctions.createFileIfNotExists("accounts.txt") ;
        String fxmlFilePath = "/com/example/tppoo/sign-up.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
        Scene scene = new Scene(loader.load(), 1000, 680);
        // Load the icon image
        // Set the application icon
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}