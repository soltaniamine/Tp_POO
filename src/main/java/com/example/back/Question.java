package com.example.back;
import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String prompt;
    private Categorie categorie;
    private TypeQuestion type;
    private List<String> options; // Pour QCU et QCM
    private List<String> repense;

    public Question(String question, Categorie categorie, TypeQuestion type, List<String> options, List<String> repense) {
        this.prompt = question;
        this.categorie = categorie;
        this.type = type;
        this.options = options;
        this.repense = repense;

    }

    public Question(String prompt, Categorie categorie, TypeQuestion type) {
        this.prompt = prompt;
        this.categorie = categorie;
        this.type = type;
    }

    public String getPrompt() {
        return prompt;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public TypeQuestion getType() {
        return type;
    }

    public List<String> getOtions(){
        return options;
    }

    public List<String> getRepense(){
        return repense;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // Write an ArrayList of questions to file
    public static void writeToFile(ArrayList<Question> questions, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(questions);
            System.out.println("Serialized data is saved in " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Read an ArrayList of questions from file
    public static ArrayList<Question> readFromFile(String fileName) {
        ArrayList<Question> questions = new ArrayList<>();
        File file = new File(fileName);

        if (file.length() == 0) {
            System.out.println("The file is empty.");
            return questions;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            questions = (ArrayList<Question>) in.readObject();
            System.out.println("Deserialization successful.");
        } catch (IOException i) {
            System.err.println("Error during deserialization: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Question class not found: " + c.getMessage());
            c.printStackTrace();
        }
        return questions;
    }
}
