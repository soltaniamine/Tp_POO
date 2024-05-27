package com.example.back;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TestExercice extends Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ExercicePatient> listeExercices;

    public TestExercice(Patient patient,String nom,List<Exercice> exercices) {
        super(patient,nom);
        listeExercices = new ArrayList<>();
        for (Exercice exo: exercices){
            ExercicePatient exoPatient = new ExercicePatient(exo.getConsigne(),exo.getMateriel());
            listeExercices.add(exoPatient);
        }
    }

    public void ajouterExercice(ExercicePatient exercice){
        listeExercices.add(exercice);
    }

    public void supprimerExercice(ExercicePatient exercice){
        listeExercices.remove(exercice);
    }

    @Override
    public int calculerScoreTotal() {
        int total = 0;
        for (ExercicePatient exo:listeExercices) {
            total=+exo.getScore();
        }
        return total;
    }

    public void printExercicesDetails() {
        for (ExercicePatient exo : listeExercices) {
            System.out.println("Exercise: " + exo.getConsigne() + ", Response: " + exo.getReponse() + ", Score: " + exo.getScore());
        }
    }



    public List<ExercicePatient> getExercices() {
        return listeExercices;
    }
    public List<String> getExercice(){
        List<String> list = new ArrayList<>();
        for(ExercicePatient ques : listeExercices){
            list.add(ques.getConsigne());
        }
        return list;
    }
    public List<String> getRepense(){
        List<String> list = new ArrayList<>();
        for(ExercicePatient ques : listeExercices){
            list.add(ques.getReponse());
        }
        return list;
    }
}
