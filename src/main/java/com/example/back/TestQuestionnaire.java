package com.example.back;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestQuestionnaire extends Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<QuestionPatient> listquestions;

    public TestQuestionnaire(Patient patient,String nom, List<Question> questions) {
        super(patient,nom);
        listquestions = new ArrayList<>();
        for(Question quest: questions){
            QuestionPatient questpation = new QuestionPatient(quest.getPrompt(), quest.getCategorie(), quest.getType(),quest.getOtions(),quest.getRepense());
            listquestions.add(questpation);
        }
    }


    public List<QuestionPatient> getQuestions() {
        return listquestions;
    }

    public void ajouterQuestion(QuestionPatient question){
        listquestions.add(question);
    }

    public void supprimerQuestion(QuestionPatient question){
        listquestions.remove(question);
    }

    @Override
    public int calculerScoreTotal() {
        int score=0;
        for (QuestionPatient ques: listquestions ){
            score+=ques.getScore();
        }
        System.out.println("it is now "+score );
        return score;
    }

    public void printQuestionsDetails() {
        for (QuestionPatient ques : listquestions) {
            System.out.println("Question: " + ques.getQuestion() + ", Response: " + ques.getRepensePatient() + ", Score: " + ques.getScore());
        }
    }
    public List<String> getQuestion(){
        List<String> list = new ArrayList<>();
        for(QuestionPatient ques : listquestions){
            list.add(ques.getQuestion());
        }
        return list;
    }
    public List<String> getRepenses(){
        List<String> list = new ArrayList<>();
        for(QuestionPatient ques : listquestions){
            list.add(ques.getRepensePatient());
        }
        return list;
    }
}
