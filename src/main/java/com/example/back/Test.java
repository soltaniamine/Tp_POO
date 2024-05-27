package com.example.back;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private Patient patient;
    private String nom;
    private ConclusionTest conclusion;
    private List<Integer> scores;

    public Test(Patient patient, String nom) {
        this.nom = nom;
        this.scores = new ArrayList<>();
        this.patient = patient;
    }

    public String getNom() {
        return nom;
    }


    public List<Integer> getScores() {
        return scores;
    }

    public void ajouterScore(int score) {
        scores.add(score);
    }

    public void setConclusionTest(ConclusionTest observ){
        this.conclusion = observ;
    }

    public ConclusionTest getConclusionTest(){
        return conclusion;
    }

    public abstract int calculerScoreTotal();
    //a different method for every sub-class
}
