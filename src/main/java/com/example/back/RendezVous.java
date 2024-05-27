package com.example.back;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class RendezVous implements Serializable {

    private static final long serialVersionUID = 1L;  // Add serialVersionUID
    private Ortho orthophonist;
    private LocalDateTime dateTime;
    private Patient patient;

    public String getDuree() {
        return Duree;
    }

    private String Duree;
    private String observation;
    private List<Patient> patientList;


    public RendezVous(Ortho orthophonist,Patient patient,LocalDateTime dateTime, String observation){
        this.orthophonist=orthophonist;
        this.patient = patient;
        this.dateTime=dateTime;
        this.Duree="1h";
        this.observation=observation;
        orthophonist.ajouterRendezVous(this);
    }

    public RendezVous(Ortho orthophonist,LocalDateTime dateTime, String observation){
        this.orthophonist=orthophonist;
        this.dateTime=dateTime;
        this.observation=observation;
        orthophonist.ajouterRendezVousConsultation(this);

    }

    public RendezVous(Ortho orthophonist,LocalDateTime dateTime){

        this.orthophonist=orthophonist;
        this.dateTime=dateTime;
        orthophonist.ajouterRendezVous(this);
    }

    public String getObservation(){
        return observation;
    }

    public RendezVous(Ortho orthophonist,List<Patient> patientList,LocalDateTime dateTime){
        this.orthophonist= orthophonist;
        this.dateTime=dateTime;
        this.patientList= patientList;
        orthophonist.ajouterRendezVous(this);

    }

    public void setDuree(String duree){
        this.Duree=duree;
    }

    public void setPatient(Patient patient){
        this.patient=patient;
    }

    public void setObservation(String observation){
        this.observation=observation;
    }

    public Patient getPatient(){
        return patient;
    }

    public LocalDateTime getDate(){
        return dateTime;
    }

}
