package com.example.back;

import java.io.*;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;
import java.util.List;
public class Exercice implements Serializable {
    private String consigne;
    private String materiel;
    private static final long serialVersionUID = 1L;


    public Exercice(String consigne) {
        this.consigne = consigne;
    }

    public Exercice(String consigne, String materiel) {
        this.consigne=consigne;
        this.materiel=materiel;
    }

    public String getConsigne() {
        return consigne;
    }


    public String getMateriel() {
        return materiel;
    }

    // Write a list of Exo objects to file
    public static void writeToFile(ArrayList<Exercice> exos, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(exos);
            System.out.println("Serialized data is saved in " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Read a list of Exo objects from file
    public static ArrayList<Exercice> readFromFile(String fileName) {
        ArrayList<Exercice> exos = new ArrayList<>();
        File file = new File(fileName);

        if (file.length() == 0) {
            System.out.println("The file is empty.");
            return exos;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            exos = (ArrayList<Exercice>) in.readObject();
            System.out.println("Deserialization successful.");
        } catch (IOException i) {
            System.err.println("Error during deserialization: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Exo class not found: " + c.getMessage());
            c.printStackTrace();
        }
        return exos;
    }
}
