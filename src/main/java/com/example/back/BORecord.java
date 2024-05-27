package com.example.back;

import java.util.List;
import java.util.stream.Collectors;

public class BORecord {
    private String observationClinique;
    private String projetTherapeutique;
    private List<String> questions;
    private List<String> repenses;
    private int scoreFinale;

    public BORecord(String observationClinique, String projetTherapeutique, List<String> questions, List<String> repenses, int scoreFinale) {
        this.observationClinique = observationClinique;
        this.projetTherapeutique = projetTherapeutique;
        this.questions = questions;
        this.repenses = repenses;
        this.scoreFinale = scoreFinale;
    }

    public String getObservationClinique() {
        return observationClinique;
    }

    public void setObservationClinique(String observationClinique) {
        this.observationClinique = observationClinique;
    }

    public String getProjetTherapeutique() {
        return projetTherapeutique;
    }

    public void setProjetTherapeutique(String projetTherapeutique) {
        this.projetTherapeutique = projetTherapeutique;
    }

    public List<String> getQuestionsExercices() {
        return questions;
    }

    public void setQuestionsExercices(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getRepenses() {
        return repenses;
    }

    public void setRepenses(List<String> repenses) {
        this.repenses = repenses;
    }

    public int getScoreFinale() {
        return scoreFinale;
    }

    public void setScoreFinale(int scoreFinale) {
        this.scoreFinale = scoreFinale;
    }

    public String getQuestionsAsString() {
        return String.join(", ", questions);
    }

    public String getRepensesAsString() {
        return String.join(", ", repenses);
    }
}
