package com.example.back;

import java.io.Serializable;

public class ExercicePatient implements Serializable {
    private static final long serialVersionUID = 1L;
    private String consigne;
    private String materiel;
    private int score;
    private String reponse;

    public ExercicePatient(String consigne) {
        this.consigne = consigne;

    }

    public ExercicePatient(String consigne, String materiel) {
        this.consigne = consigne;
        this.materiel = materiel;

    }

    public String getConsigne() {
        return consigne;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setScore(int score){
        this.score=score;
    }

    public int getScore(){
        return score;
    }

    public String getReponse(){
        return reponse;
    }

    public void setReponse(String reponse){
        this.reponse = reponse;
    }

}
