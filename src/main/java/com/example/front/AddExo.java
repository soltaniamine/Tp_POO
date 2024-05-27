package com.example.front;

import com.example.back.Exercice;
import com.example.back.Exercice;
import com.example.back.Ortho;
import com.example.back.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddExo {
    @FXML
    private Button myButton;

    @FXML
    private TextField consigneField;

    @FXML
    private TextField materielField;

    @FXML
    public void initialize() {
        if (DataSingleton.getInstance().getExercice() != null) {
            Ortho ortho = DataSingleton.getInstance().getOrtho();
            ArrayList<Exercice> exos = Exercice.readFromFile(ortho.getEmail() + "/exos.bin");
            consigneField.setText(DataSingleton.getInstance().getExercice().getConsigne());
            materielField.setText(DataSingleton.getInstance().getExercice().getMateriel());
            Exercice exercice = new Exercice(consigneField.getText(), materielField.getText());
            removeExoFromList(exercice, exos);
            Exercice.writeToFile(exos, ortho.getEmail() + "/exos.bin");
        }
        myButton.setOnAction(event -> addExo());
    }

    private void removeExoFromList(Exercice exercice, List<Exercice> exoList) {
        Iterator<Exercice> iterator = exoList.iterator();
        while (iterator.hasNext()) {
            Exercice ex = iterator.next();
            if (ex.getConsigne().equals(exercice.getConsigne()) && ex.getMateriel().equals(exercice.getMateriel())) {
                iterator.remove();  // Safe removal
            }
        }
    }


    private void addExo() {
        Ortho ortho = DataSingleton.getInstance().getOrtho();
        String consigne = consigneField.getText();
        String materiel = materielField.getText();

        Exercice exo = new Exercice(consigne, materiel);
        ArrayList<Exercice> exos = Exercice.readFromFile(ortho.getEmail()+"/exos.bin");
        exos.add(exo);
        Exercice.writeToFile(exos, ortho.getEmail()+"/exos.bin");

        // Clear the fields after adding
        consigneField.clear();
        materielField.clear();
    }
}
