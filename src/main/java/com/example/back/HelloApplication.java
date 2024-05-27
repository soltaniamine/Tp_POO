package com.example.back;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        /*launch();*//*
        Ortho yacine = new Ortho("allag","yacine","Eplf",05555,"Mahdi@gmail.com","12345");
        Patient mahdi = new Adulte(yacine,"mahdi","momo", LocalDateTime.now(),"tebessa","dar","engin","mahdi","054343");
        Patient moha = new Enfant(yacine,"moha","mimi",LocalDateTime.now(),"tebessa","dar","home","2222","423223");
        RendezVous rdv1 = new Consultation(yacine,LocalDateTime.now(),"good job","yacine","kkk",25);
        RendezVous rdv2 = new Suivie(yacine,mahdi,LocalDateTime.now(),"bad work",true);
        yacine.ajouterQuestion("what is today", Categorie.MEDICAL_FOLLOWUP, TypeQuestion.QCM, Arrays.asList("option1", "option2"),Arrays.asList("option1"));
        Exercice exo1 = new Exercice("what is this","loup");
        Question q1=null;
        for (Question rdv : yacine.getListeQuestions()){
            q1= rdv;
        }
        BO bo = new BO(yacine,mahdi);
        bo.ajouterEpreuveClinique(Arrays.asList(q1),Arrays.asList(exo1));
        bo.ajouterObservationEpreuve("not bad");
        bo.ajouterDiagnostic("bad luck", CategorieTrouble.NEURO_DEVELOPPEMENTAUX);
        bo.ajouterProjet("welcome back man");

        BO bo1 = new BO(yacine,Arrays.asList(q1),"issam","kkk",24);
        bo1.prendreCharge();

        for (RendezVous rdv : yacine.getListeRendezVous()){
            System.out.println(rdv.getDate());
        }*/
    }
}