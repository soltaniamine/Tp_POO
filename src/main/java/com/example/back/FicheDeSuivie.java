package com.example.back;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
public class FicheDeSuivie implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ObjectifPatient> listeObjectifsPatient;
    private List<Objectif> listeObjectifs;
    private Patient patient;
    private Ortho ortho;

    public FicheDeSuivie(Ortho ortho, Patient patient, List<Objectif> listeObjectifs){
        this.ortho=ortho;
        this.listeObjectifs=listeObjectifs;
        listeObjectifsPatient = new ArrayList<>();
        for(Objectif obj : listeObjectifs){
            ObjectifPatient objPat= new ObjectifPatient(obj.getNom(),obj.getCategorie());
            listeObjectifsPatient.add(objPat);
        }
        patient.ajouteFicheDeSuivie(this);
        patient.setFicheDeSuivie(this);
    }

    public FicheDeSuivie(Patient patient,List<ObjectifPatient> listeObjectifsPatient){
        this.patient=patient;
        this.listeObjectifsPatient=listeObjectifsPatient;
        patient.getListeDesFichesDeSuivie().add(this);
        patient.setFicheDeSuivie(this);
    }

    public List<ObjectifPatient> getListeObjectifsPatient(){
        return listeObjectifsPatient;
    }

    public List<Objectif> getListeObjectifs(){
        return listeObjectifs;
    }
    
}
