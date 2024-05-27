package com.example.back;
import java.io.Serializable;


import java.util.List;
import java.util.ArrayList;

public class Ortho implements Serializable{
    private String nom;
    private String prenom;
    private String adress;
    private String tlph;
    private String email;
    private String motDePasse;

    private List<Patient> listePatient;
    private List<RendezVous> listeRendezVous;
    private List<Anamnese> listeAnamnese;
    private List<Exercice> listeExercices;
    private List<Question> listeQuestions;
    private List<Objectif> listeObjectifs;

    public Ortho(String nom,String prenom,String adress,String tlph,String email, String motDePasse){
        this.nom=nom;
        this.prenom=prenom;
        this.adress=adress;
        this.tlph=tlph;
        this.email=email;
        this.motDePasse=motDePasse;
        this.listePatient = new ArrayList<>();
        this.listeRendezVous = new ArrayList<>();
        this.listeAnamnese = new ArrayList<>();
        this.listeExercices = new ArrayList<>();
        this.listeQuestions = new ArrayList<>();
        this.listeObjectifs = new ArrayList<>();


    }

    public void ajouterPatient(Patient patient){

        listePatient.add(patient);
    }

    public Patient getPatient(int numDossier){
        System.out.println("Searching in Ortho class for patient with numero: " + numDossier);
        for (Patient patient : listePatient) {
            System.out.println("Checking patient with numero: " + patient.getPatientId()); // Ensure getNumero() exists
            if (patient.getPatientId() == numDossier) {
                return patient;
            }
        }
        return null;
    }

    public  String getEmail(){
        return email;
    }

    public void ajouterRendezVous(RendezVous rendezvous) {
        listeRendezVous.add(rendezvous);
        rendezvous.getPatient().getListRendezVous().add(rendezvous);
    }

    public void ajouterRendezVousConsultation(RendezVous rendezVous){
        listeRendezVous.add(rendezVous);
    }

    public void ajouterExercice(String consigne,String materiel){

        Exercice exercice = new Exercice(consigne,materiel);
        listeExercices.add(exercice);

    }

    public void ajouterExercice(String consigne){
        Exercice exercice = new Exercice(consigne);
        listeExercices.add(exercice);
    }

    public void ajouterQuestion(String prompt, Categorie categorie, TypeQuestion type, List<String> options, List<String> repense){
        Question question = new Question(prompt, categorie, type, options, repense);
        listeQuestions.add(question);
    }

    public void ajouterQuestion(String prompt, Categorie categorie, TypeQuestion type){
        Question question = new Question(prompt, categorie, type);
        listeQuestions.add(question);
    }


    public void ajouterObjectif(String nom, ObjectifCategorie categorie){
        Objectif obj = new Objectif(nom,categorie);
        listeObjectifs.add(obj);
    }

    public List<Patient> getPatients(){
        return listePatient;
    }

    public List<RendezVous> getListeRendezVous(){
        return listeRendezVous;
    }

    public void ajouterQuestionList(List<Question> questins){
        this.listeQuestions.addAll(questins);
    }

    public List<Question> getListeQuestions(){
        return listeQuestions;
    }
    public List<Exercice> getListeExercices(){return listeExercices;}

    public String getNom(){
        return nom;
    }



}