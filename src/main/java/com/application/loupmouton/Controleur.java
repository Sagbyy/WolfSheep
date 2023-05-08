package com.application.loupmouton;

import com.application.loupmouton.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    public Slider sliderLoup;
    @FXML
    public Slider sliderMouton;
    @FXML
    private RadioButton buttonLoup;
    @FXML
    private RadioButton buttonMouton;
    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private TextField textFieldLancer;
    @FXML
    private TextField textFieldNombreActeur;
    @FXML
    private Pane panneauDeJeu;
    @FXML
    private Label labelNbTours;
    @FXML
    private Label labelTemps;
    @FXML
    private Label nbVivant;
    @FXML
    private Label labelNbLoup;
    @FXML
    private Label labelNbMouton;

    private int cptJour;
    private Environnement environnement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        environnement = new Environnement(657, 459);
        cptJour = 0;
        this.environnement.nbToursProperty().addListener((obs, old, nouv) -> {
            int newValue = (int) nouv;

            labelNbTours.setText(Integer.toString(newValue));

            this.cptJour++;

            if (cptJour <= 12) {
                labelTemps.setText("Jour");
            }
            else if (cptJour < 24) {
                labelTemps.setText("Nuit");
            }
            else {
                this.cptJour = 0;
            }
        });
        this.sliderLoup.valueProperty().addListener((obs, old, nouv) -> {
            Loup.setPourcentageRepro((Double) nouv);
            System.out.println(Loup.getPourcentageRepro());
        });
        this.sliderMouton.valueProperty().addListener((obs, old, nouv) -> {
            Mouton.setPourcentageRepro((Double) nouv);
        });
        this.environnement.getActeurs().addListener(new MonObservateurActeurs(panneauDeJeu, nbVivant, labelNbLoup, labelNbMouton));
    }

    @FXML
    public void ajouter(ActionEvent actionEvent) {
        System.out.println("Clic sur le bouton ajouter !");
        int nbrActeur = 0;

        try {
            nbrActeur = Integer.parseInt(textFieldNombreActeur.getText());
        } catch (Exception e) {
            System.err.println("Erreur : le champ n'est pas un int !");
        }

        for(int i = 0; i < nbrActeur; i++) {
            if (buttonLoup.isSelected()) {
                Acteur loup = new Loup(environnement);
                environnement.ajouter(loup);
                System.out.println("Ajout loup");
            }
            else {
                Acteur mouton = new Mouton(environnement);
                environnement.ajouter(mouton);
                System.out.println("Ajout mouton");
            }
        }
        textFieldNombreActeur.appendText("");
    }

//    public void rafraichir() {
//        for (Acteur a : environnement.getActeurs()) {
//            Node r = panneauDeJeu.lookup("#" + a.getId());
//            if (r != null) {
//                r.setTranslateY(a.getY());
//                r.setTranslateX(a.getX());
//            }
//        }
//        panneauDeJeu.getChildren().removeIf(node -> !ifActeurExists(node.getId()));
//    }

    public boolean ifActeurExists(String id) {
        for (Acteur acteur : environnement.getActeurs()) {
            if (acteur.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    @FXML
    void faireDesTours(ActionEvent event) {
        System.out.println("Clic sur le bouton lancer");
        int nbTour = 0;

        try {
            nbTour = Integer.parseInt(textFieldLancer.getText());
        } catch (Exception e) {
            System.err.println("Erreur : le champ n'est pas un int !");
        }

        for(int i = 0; i < nbTour; i++) {
            this.unTour();
            System.out.println("test");
        }

        textFieldLancer.appendText("");
    }

    @FXML
    void unTour() {
        System.out.println("Click sur unTour");
        environnement.unTour();
        System.out.println(panneauDeJeu.getHeight());
    }

    public void loupSlider(ScrollEvent scrollEvent) {

    }

    public void moutonSlider(ScrollEvent scrollEvent) {

    }
}
