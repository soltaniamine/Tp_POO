package com.example.back;

import java.io.Serializable;

public class Objectif implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private ObjectifCategorie categorie;

    public Objectif (String nom,ObjectifCategorie categorie){
        this.nom= nom;
        this.categorie= categorie;
    }


    public String getNom(){
        return nom;
    }

    public ObjectifCategorie getCategorie(){
        return categorie;
    }

}
