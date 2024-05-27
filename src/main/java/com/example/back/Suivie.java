package com.example.back;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Suivie extends RendezVous implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numDossier;
    private boolean presentiel;

    public Suivie(Ortho orthophonist,Patient patient,LocalDateTime dateTime, String observation, boolean presentiel){
        super(orthophonist,patient, dateTime, observation);
        this.numDossier = patient.getPatientId();
        this.presentiel=presentiel;
    }

    public Suivie(Ortho orthophonist, int numDossier,LocalDateTime dateTime, String observation, boolean presentiel ){
        super(orthophonist,null, dateTime, observation);
        Patient patient = orthophonist.getPatient(numDossier);
        super.setPatient(patient);
        this.numDossier=numDossier;
        this.presentiel=presentiel;
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

    public static ArrayList<Suivie> readFromFile(String fileName) {
        ArrayList<Suivie> suivies = new ArrayList<>();
        File file = new File(fileName);

        if (file.length() == 0) {
            System.out.println("The file is empty.");
            return suivies;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Suivie suivie = (Suivie) in.readObject();
                    suivies.add(suivie);
                } catch (EOFException e) {
                    // End of file reached
                    break;
                }
            }
            System.out.println("Deserialization successful.");
        } catch (IOException i) {
            System.err.println("Error during deserialization: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Suivie class not found: " + c.getMessage());
            c.printStackTrace();
        }
        return suivies;
    }

    public int getNumDossier(){
        return numDossier;
    }

    public boolean isPresentiel(){
        return presentiel;
    }

    
}
