package com.example.front;

import com.example.back.Patient;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDateTime;

public class PatientWrapper {
    private SimpleIntegerProperty numPatient;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleObjectProperty<LocalDateTime> dateDeNaissance;
    private SimpleStringProperty details;
    private Patient patient;

    public PatientWrapper(Patient patient) {
        this.numPatient = new SimpleIntegerProperty(patient.getPatientId());
        this.nom = new SimpleStringProperty(patient.getNom());
        this.prenom = new SimpleStringProperty(patient.getPrenom());
        this.dateDeNaissance = new SimpleObjectProperty<>(patient.getDateDeNaissance());
        this.patient = patient;
        this.details = new SimpleStringProperty("Some details"); // You can modify this to include actual details if needed
    }

    public int getNumPatient() {
        return numPatient.get();
    }

    public SimpleIntegerProperty numPatientProperty() {
        return numPatient;
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public LocalDateTime getDateDeNaissance() {
        return dateDeNaissance.get();
    }

    public SimpleObjectProperty<LocalDateTime> dateDeNaissanceProperty() {
        return dateDeNaissance;
    }

    public String getDetails() {
        return details.get();
    }

    public SimpleStringProperty detailsProperty() {
        return details;
    }

    public Patient getPatient() {
        return patient;
    }
}
