package com.example.back;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Ortho ortho;
    private Patient patient;
    private Anamnese anamnese;
    private List<EpreuveClinique> epreuvesClinique;
    private Diagnostic diagnostics;
    private ProjetTherapeutique projets;
    private String nom;
    private String prenom;
    private int age;

    public BO(Ortho ortho,Patient patient){
        this.ortho= ortho;
        this.patient = patient;
        epreuvesClinique = new ArrayList<>();


        patient.ajouterBO(this);

    }

    public BO(Ortho ortho,List<Question> questions, String nom, String prenom, int age){
        this.ortho= ortho;
        Anamnese anamnese1 = new Anamnese(questions,age);
        epreuvesClinique = new ArrayList<>();
        this.anamnese = anamnese1;
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;

    }

    public void ajouterEpreuveClinique( List<Question> questions,List<Exercice> exercices){


        EpreuveClinique epreuve = new EpreuveClinique(patient, questions,exercices);
        this.epreuvesClinique.add(epreuve);
    }

    public void ajouterEpreuveClinique(EpreuveClinique ec){
        this.epreuvesClinique.add(ec);
    }



    public void ajouterDiagnostic(String nomTrouble, CategorieTrouble categorieTrouble){
        Trouble troubl = new Trouble(nomTrouble,categorieTrouble);
        Diagnostic diagno = new Diagnostic(troubl,patient);
        this.diagnostics = diagno;
        this.patient.ajouterTrouble(troubl);
    }

    public void ajouterProjet(String projet){
        ProjetTherapeutique proj = new ProjetTherapeutique(projet);
        this.projets = proj;
    }

    public List<EpreuveClinique> getEpreuvesClinique(){
        return epreuvesClinique;
    }

    public void prendreCharge(){
        Patient newPatient;
        if(age>=18) {
            newPatient = new Adulte(ortho, nom, prenom, age);

        }
        else{
            newPatient = new Enfant(ortho,nom,prenom,age);

        }

        patient = newPatient;
        patient.ajouterBO(this);
        patient.setAnamnese(anamnese);

    }

    public ProjetTherapeutique getProjets(){
        return projets;
    }

    public void ajouterDiagnostic(Diagnostic diagno){
        this.diagnostics=diagno;

    }




    
}
