package com.example.back;

import java.io.Serializable;

public class ProjetTherapeutique implements Serializable {
    private static final long serialVersionUID = 1L;
    private String demarche;

    public ProjetTherapeutique(String Demarche){
        this.demarche=Demarche;
    }

    public void setDemarche(String Demarche){
        this.demarche = Demarche;
    }

    public String getDemarche(){
        return this.demarche;
    }
}
