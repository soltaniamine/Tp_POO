package com.example.back;
import com.example.front.FicheDeSuivieController;

import java.io.Serializable;
import java.time.Instant;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private Ortho orthophonist;
    private int numPatient;
    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private int age;
    private String lieuDeNaissance;
    private String adresse;
    private List<RendezVous> listeRendezVous;
    private List<BO> listeBO;
    private List<FicheDeSuivie> listeDesFichesDeSuivie;
    private List<Trouble> listeTroubles;
    private FicheDeSuivie ficheDeSuivie;
    private Boolean isAdulte;
    private Boolean anamneseEffectuee;
    private Anamnese anamnese;

    public Patient(Ortho orthophonist, String nom,String prenom, LocalDateTime dateNaissance,String LieuNaissance,String adresse,Boolean adulte){
        this.numPatient = generatePatientNumber();
        this.nom=nom;
        this.prenom=prenom;
        this.dateDeNaissance=dateNaissance;
        this.lieuDeNaissance=LieuNaissance;
        this.adresse=adresse;
        this.isAdulte=adulte;
        this.listeRendezVous = new ArrayList<>();
        this.listeBO = new ArrayList<>();
        this.listeDesFichesDeSuivie = new ArrayList<>();
        this.listeTroubles = new ArrayList<>();
        this.orthophonist=orthophonist;
        this.listeDesFichesDeSuivie=new ArrayList<>();
        orthophonist.ajouterPatient(this);

    }

    public Patient(Ortho ortho,String nom,String prenom,int age){
        if (age>=18){
            isAdulte=true;
        }
        this.numPatient = generatePatientNumber();
        this.nom=nom;
        this.prenom=prenom;
        this.age= age;
        this.listeRendezVous = new ArrayList<>();
        this.listeBO = new ArrayList<>();
        this.listeDesFichesDeSuivie = new ArrayList<>();
        this.listeTroubles = new ArrayList<>();
        this.orthophonist=ortho;

        orthophonist.ajouterPatient(this);
    }

    private int generatePatientNumber() {
        long currentTimeMillis = Instant.now().toEpochMilli();
        Random random = new Random();
        int randomNumber = random.nextInt(1000); // generates a random number between 0 and 999
        return (int) (currentTimeMillis % Integer.MAX_VALUE) + randomNumber;
    }


    public int getPatientId() {
        return numPatient;
    }
    public boolean getIsAdulte(){
        return isAdulte;
    }

    public void ajouterBO(BO bo){
        listeBO.add(bo);
    }

    public void supprimerBO(BO bo){
        listeBO.remove(bo);
    }

    public List<BO> getListeBO(){
        return listeBO;
    }

    public Boolean getAnamneseEffectuee(){
        return anamneseEffectuee;
    }

    public void setAnamneseEffectuee(Boolean anamneseEffectuee){
        this.anamneseEffectuee = anamneseEffectuee;
    }

    public void ajouterTrouble(Trouble trouble){
        this.listeTroubles.add(trouble);
    }

    public List<Trouble> getTroubles(){
        return listeTroubles;
    }

    public void ajouterRendezVous(RendezVous rendezvous){
        listeRendezVous.add(rendezvous);
    }

    public List<RendezVous> getListRendezVous(){
        return listeRendezVous;
    }

    public String getNom(){
        return nom;
    }

    public void setAnamnese(Anamnese anamnese1){
        this.anamnese=anamnese1;
    }

    public Anamnese getAnamnese(){
        return anamnese;
    }

    public void ajouteFicheDeSuivie(FicheDeSuivie fiche){
        this.listeDesFichesDeSuivie.add(fiche);
    }

    public void setFicheDeSuivie(FicheDeSuivie fiche){
        this.ficheDeSuivie=fiche;
    }

    public String getPrenom(){
        return prenom;
    }

    public FicheDeSuivie getFicheDeSuivie(){
        return ficheDeSuivie;
    }

    public List<FicheDeSuivie> getListeDesFichesDeSuivie(){
        return listeDesFichesDeSuivie;
    }
    public LocalDateTime getDateDeNaissance(){
        return dateDeNaissance;
    }

    public String getLieuDeNaissance(){
        return lieuDeNaissance;
    }

    public String getAdresse(){
        return adresse;
    }
    public List<BO> getBO(){
        return listeBO;
    }

    public Ortho getOrtho(){
        return orthophonist;
    }

}
