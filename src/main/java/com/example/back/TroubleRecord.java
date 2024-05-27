package com.example.back;

public class TroubleRecord {
    private String nomTrouble;
    private String categorieTrouble;

    public TroubleRecord(String nomTrouble, String categorieTrouble) {
        this.nomTrouble = nomTrouble;
        this.categorieTrouble = categorieTrouble;
    }

    public String getNomTrouble() {
        return nomTrouble;
    }

    public void setNomTrouble(String nomTrouble) {
        this.nomTrouble = nomTrouble;
    }

    public String getCategorieTrouble() {
        return categorieTrouble;
    }

    public void setCategorieTrouble(String categorieTrouble) {
        this.categorieTrouble = categorieTrouble;
    }
}
