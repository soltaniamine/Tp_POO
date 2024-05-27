package com.example.front;

import com.example.back.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PatientDetailsController {

    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_nic;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_address1;

    @FXML
    private TextField tf_address11;

    @FXML
    private TableView<BORecord> table_record111;

    @FXML
    private TableColumn<BORecord, String> tc_observationClinique;

    @FXML
    private TableColumn<BORecord, String> tc_projetTherapeutique;

    @FXML
    private TableColumn<BORecord, String> tc_questionsExercices;

    @FXML
    private TableColumn<BORecord, String> tc_repenses;

    @FXML
    private TableColumn<BORecord, Integer> tc_scoreFinale;

    @FXML
    private TextField tf_search111;

    @FXML
    private TableView<TroubleRecord> table_troubles;

    @FXML
    private TableColumn<TroubleRecord, String> tc_trouble;

    @FXML
    private TableColumn<TroubleRecord, String> tc_categorie;

    @FXML
    private TextField tf_search_troubles;

    @FXML
    private TableView<AppointmentRecord> table_appointments;

    @FXML
    private TableColumn<AppointmentRecord, String> tc_date;

    @FXML
    private TableColumn<AppointmentRecord, String> tc_observation;

    @FXML
    private TextField tf_search;

    @FXML
    private TableView<ObjectifRecord> table_objectifs;

    @FXML
    private TableColumn<ObjectifRecord, String> tc_objectif;

    @FXML
    private TableColumn<ObjectifRecord, ObjectifCategorie> tc_categorie1;

    @FXML
    private TableColumn<ObjectifRecord, Integer> tc_score;

    @FXML
    private TextField tf_search_objectifs;

    @FXML
    private void initialize() {
        // Initialize the BO table columns
        tc_observationClinique.setCellValueFactory(new PropertyValueFactory<>("observationClinique"));
        tc_projetTherapeutique.setCellValueFactory(new PropertyValueFactory<>("projetTherapeutique"));
        tc_questionsExercices.setCellValueFactory(new PropertyValueFactory<>("questionsExercices"));
        tc_repenses.setCellValueFactory(new PropertyValueFactory<>("repenses"));
        tc_scoreFinale.setCellValueFactory(new PropertyValueFactory<>("scoreFinale"));

        // Initialize the troubles table columns
        tc_trouble.setCellValueFactory(new PropertyValueFactory<>("nomTrouble"));
        tc_categorie.setCellValueFactory(new PropertyValueFactory<>("categorieTrouble"));

        // Initialize the appointments table columns
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_observation.setCellValueFactory(new PropertyValueFactory<>("observation"));

        // Initialize the objectifs table columns
        tc_objectif.setCellValueFactory(new PropertyValueFactory<>("nomObjectif"));
        tc_categorie1.setCellValueFactory(new PropertyValueFactory<>("categorieObjectif"));
        tc_score.setCellValueFactory(new PropertyValueFactory<>("scoreObjectif"));
    }

    public void setPatientDetails(Patient patient) {
        tf_firstname.setText(patient.getNom());
        tf_lastname.setText(patient.getPrenom());
        tf_phone.setText("123456789"); // Example placeholder
        tf_email.setText(patient.getLieuDeNaissance());
        tf_nic.setText(String.valueOf(patient.getPatientId()));
        tf_address.setText(patient.getAdresse());
        tf_address1.setText("Diploma Placeholder");
        tf_address11.setText("Profession Placeholder");

        // Load BO data into the table
        List<BO> boList = patient.getBO(); // Ensure this method exists in Patient class to get BO list
        ObservableList<BORecord> boData = FXCollections.observableArrayList();

        for (BO bo : boList) {
            for (EpreuveClinique epreuve : bo.getEpreuvesClinique()) {
                String observationClinique = epreuve.getObservation();
                String projetTherapeutique = bo.getProjets() != null ? bo.getProjets().getDemarche() : ""; // Ensure getProjets() and getDescription() exist
                List<String> questionsExercices = epreuve.getTestQuest().getQuestion();
                questionsExercices.addAll(epreuve.getTestExo().getExercice());

                List<String> repenses = epreuve.getTestQuest().getRepenses();
                repenses.addAll(epreuve.getTestExo().getRepense());
                int scoreFinale = epreuve.getScore(); // Ensure this method exists in EpreuveClinique

                BORecord record = new BORecord(observationClinique, projetTherapeutique, questionsExercices, repenses, scoreFinale);
                boData.add(record);
            }
        }

        table_record111.setItems(boData);

        // Load troubles data into the troubles table
        List<Trouble> troublesList = patient.getTroubles(); // Ensure this method exists in Patient class to get Trouble list
        ObservableList<TroubleRecord> troublesData = FXCollections.observableArrayList();

        for (Trouble trouble : troublesList) {
            TroubleRecord troubleRecord = new TroubleRecord(trouble.getNom(), trouble.getCategorie().toString());
            troublesData.add(troubleRecord);
        }

        table_troubles.setItems(troublesData);

        // Load appointments data into the appointments table
        List<RendezVous> rendezVousList = patient.getListRendezVous(); // Ensure this method exists in Patient class to get Rendez-vous list
//        for(RendezVous rdv : rendezVousList){
//            System.out.println(rdv.getDate());
//
//        }
        ObservableList<AppointmentRecord> appointmentsData = FXCollections.observableArrayList();

        for (RendezVous rendezVous : rendezVousList) {
            AppointmentRecord appointmentRecord = new AppointmentRecord(rendezVous.getDate().toString(), rendezVous.getObservation());
            appointmentsData.add(appointmentRecord);
        }
        for(AppointmentRecord rec : appointmentsData){
            System.out.println(rec.getDate());
        }

        table_appointments.setItems(appointmentsData);

        // Load objectifs data into the objectifs table
        if (patient.getFicheDeSuivie() == null) {
            FicheDeSuivie ficheDeSuivie = new FicheDeSuivie(patient.getOrtho(), patient, FXCollections.observableArrayList());
            patient.setFicheDeSuivie(ficheDeSuivie);
        }
        List<ObjectifPatient> objectifsList = patient.getFicheDeSuivie().getListeObjectifsPatient(); // Ensure this method exists in Patient class to get Objectif list
        ObservableList<ObjectifRecord> objectifsData = FXCollections.observableArrayList();

        for (ObjectifPatient objectif : objectifsList) {
            ObjectifRecord objectifRecord = new ObjectifRecord(objectif.getNom(), objectif.getCategorie().toString(), objectif.getScore());
            System.out.println((objectifRecord.getScoreObjectif()));
            objectifsData.add(objectifRecord);
        }

        table_objectifs.setItems(objectifsData);
    }
}
