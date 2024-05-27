package com.example.back;
import java.io.*;
import java.time.LocalDateTime;



public class Consultation extends RendezVous implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private String prenom;
    private int age;

    public Consultation(Ortho orthophonist,LocalDateTime dateTime, String observation, String nom,String prenom,int age){
        super(orthophonist,dateTime,observation);
        this.age=age;
        this.nom=nom;
        this.prenom=prenom;
        //if adult then it is 2h30 
        if(age>=18){
            super.setDuree("2h30m");
        }
        else{
            super.setDuree("1h30m");
        }
    }

    public void writeToFile(String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Serialized data is saved in " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Consultation readFromFile(String fileName) {
        Consultation consultation = null;
        File file = new File(fileName);

        if (file.length() == 0) {
            System.out.println("The file is empty.");
            return null;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            consultation = (Consultation) in.readObject();
            System.out.println("Deserialization successful.");
        } catch (IOException i) {
            System.err.println("Error during deserialization: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Consultation class not found: " + c.getMessage());
            c.printStackTrace();
        }
        return consultation;
    }
    
}
