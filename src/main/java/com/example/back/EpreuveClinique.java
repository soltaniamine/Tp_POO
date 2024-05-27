package com.example.back;
import java.io.Serializable;
import java.util.List;

public class EpreuveClinique implements Serializable {
    private static final long serialVersionUID = 1L;

    private Patient patient;
    private BO bo;
    private ObservationClinique observations;
    private TestQuestionnaire testQuest;
    private TestExercice testExo;
    private int Score;

    public EpreuveClinique(Patient patient, List<Question> questions, List<Exercice> exercices){
        this.patient = patient;
        testQuest = new TestQuestionnaire(patient, null, questions);
        testExo = new TestExercice(patient, null, exercices);


    }
    public EpreuveClinique(BO bo,TestQuestionnaire testQuest,TestExercice testExercice,ObservationClinique observationClinique,int Score){
        this.bo = bo;
        this.testExo=testExercice;
        this.testQuest=testQuest;
        this.observations=observationClinique;
        this.Score=Score;

        bo.ajouterEpreuveClinique(this);
    }

    public void setObservation(ObservationClinique obs){
        this.observations = obs;
    }
//change this one asap
    public int getScore(){
        Score =testQuest.calculerScoreTotal()+testExo.calculerScoreTotal();
    return Score;
    }

    public TestQuestionnaire getTestQuest(){
        return testQuest;
    }

    public String getObservation(){
        return observations.getObservationClinique();
    }

    public TestExercice getTestExo(){
        return testExo;
    }

    
}
