package com.example.back;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Anamnese implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<QuestionPatient> questions;

    public Anamnese(List<Question> quests,int age) {
        questions = new ArrayList<>();
        for(Question quest: quests){
            QuestionPatient questpation = new QuestionPatient(quest.getPrompt(), quest.getCategorie(), quest.getType(),quest.getOtions(),quest.getRepense());
            questions.add(questpation);
        }

        if (age>=18) {
            initializeAdultQuestions();
        } else {
            initializeChildQuestions();
        }
    }


    private void initializeAdultQuestions() {
        questions.add(new QuestionPatient("Describe your medical history.", Categorie.MEDICAL_HISTORY,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe your medical follow-up.", Categorie.MEDICAL_FOLLOWUP,TypeQuestion.LIBRE));
        // Add more adult-specific questions here
    }

    private void initializeChildQuestions() {
        questions.add(new QuestionPatient("Describe the family structure.", Categorie.FAMILY_STRUCTURE,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe the family dynamics.", Categorie.FAMILY_DYNAMICS,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe the family history.", Categorie.FAMILY_HISTORY,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe the birth conditions.", Categorie.BIRTH_CONDITIONS,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe the psychomotor development.", Categorie.PSYCHOMOTOR_DEVELOPMENT,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe the language development.", Categorie.LANGUAGE_DEVELOPMENT,TypeQuestion.LIBRE));
        questions.add(new QuestionPatient("Describe the character and behavior.", Categorie.CHARACTER_BEHAVIOR,TypeQuestion.LIBRE));
        // Add more child-specific questions here
    }

    // Write a list of Exo objects to file
    public static void writeToFile(Anamnese anamnese, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(anamnese);
            System.out.println("Serialized data is saved in " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Read a list of Exo objects from file
    public static Anamnese readFromFile(String fileName) {
        Anamnese anamnese = null ;
        File file = new File(fileName);

        if (file.length() == 0) {
            System.out.println("The file is empty.");
            return anamnese;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            anamnese = (Anamnese) in.readObject();
            System.out.println("Deserialization successful.");
        } catch (IOException i) {
            System.err.println("Error during deserialization: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Exo class not found: " + c.getMessage());
            c.printStackTrace();
        }
        return anamnese;
    }

    public void supprimerAllQuestions(){
        questions.clear();
    }

    public List<QuestionPatient> getQuestions() {
        return questions;
    }

    public void ajouterQuestion(QuestionPatient question) {
        questions.add(question);
    }

    public void supprimerQuestion (QuestionPatient question){
        questions.remove(question);
    }

}
