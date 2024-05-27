package com.example.back;

import java.io.*;
import java.util.Arrays;

public class Creneaux implements Serializable {

    private static final long serialVersionUID = 1L;  // Add serialVersionUID
    private Creneau[] creaneaux ;

    public Creneau[] getCreneaux() {
        return creaneaux;
    }

    public void setCreneaux(Creneau[] creaneaux) {
        this.creaneaux = creaneaux;
    }

    public Creneaux() {
        creaneaux = new Creneau[] {
                new Creneau("08:00","09:00" , true),
                new Creneau("09:30","10:30" , true),
                new Creneau("11:00","12:00" , true),
                new Creneau("13:00","15:30" , true),
                new Creneau("16:00","17:00" , true),
                new Creneau("17:00","18:00" , true),
                new Creneau("18:00","19:00" , true)
        };

    }

    public void writeToFile(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Creneaux readFromFile(String filename) {
        Creneaux creneaux = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            creneaux = (Creneaux) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return creneaux;
    }

    @Override
    public String toString() {
        return "Creaneaux{" +
                "creaneaux=" + Arrays.toString(creaneaux) +
                '}';
    }

}
