package com.example.back;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Atelier extends RendezVous implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Integer> numPatients;
    private String thematique;

    public Atelier (Ortho orthophonist, List<Patient> listPatients,String thematique,LocalDateTime dateTime){
        super(orthophonist,listPatients,dateTime);
        this.numPatients = new ArrayList<>();
        for (Patient pat : listPatients){
            numPatients.add(pat.getPatientId());
        }
        this.thematique=thematique;
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

    public static ArrayList<Atelier> readFromFile(String fileName) {
        ArrayList<Atelier> ateliers = new ArrayList<>();
        File file = new File(fileName);

        if (file.length() == 0) {
            System.out.println("The file is empty.");
            return ateliers;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Atelier atelier = (Atelier) in.readObject();
                    ateliers.add(atelier);
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
        return ateliers;
    }
}
