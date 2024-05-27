package com.example.back;

public class ObjectifRecord {
    private String nomObjectif;
    private String categorieObjectif;
    private int scoreObjectif;

    public ObjectifRecord(String nomObjectif, String categorieObjectif, int scoreObjectif) {
        this.nomObjectif = nomObjectif;
        this.categorieObjectif = categorieObjectif;
        this.scoreObjectif = scoreObjectif;
    }

    public String getNomObjectif() {
        return nomObjectif;
    }

    public void setNomObjectif(String nomObjectif) {
        this.nomObjectif = nomObjectif;
    }

    public String getCategorieObjectif() {
        return categorieObjectif;
    }

    public void setCategorieObjectif(String categorieObjectif) {
        this.categorieObjectif = categorieObjectif;
    }

    public int getScoreObjectif() {
        return scoreObjectif;
    }

    public void setScoreObjectif(int scoreObjectif) {
        this.scoreObjectif = scoreObjectif;
    }
}
