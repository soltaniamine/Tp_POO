package com.example.back;

import java.io.Serializable;

public class ObservationClinique implements Serializable {
    private static final long serialVersionUID = 1L;
    private String texte;

    public ObservationClinique(String texte) {
        this.texte = texte;
    }

    public String getObservationClinique() {
        return texte;
    }

    public void setObservationClinique(String texte) {
        this.texte = texte;
    }
}
