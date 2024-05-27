package com.example.back;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Adulte extends Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    private String diplome;
    private String profession;
    private String numero;

    public Adulte(Ortho orthophonist, String nom,String prenom, LocalDateTime dateNaissance,String LieuNaissance,String adresse,String diplome,String profession,String numero){
        super(orthophonist,nom,prenom,  dateNaissance, LieuNaissance, adresse, true);
        this.diplome=diplome;
        this.profession=profession;
        this.numero=numero;
    }

    public Adulte(Ortho ortho,String nom,String prenom,int age){
        super(ortho,nom,prenom,age);
    }
    
}
