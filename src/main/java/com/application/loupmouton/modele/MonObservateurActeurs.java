package com.application.loupmouton.modele;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MonObservateurActeurs implements ListChangeListener<Acteur> {

    private Pane panneauDeJeu;
    private Label labelNbVivant;
    private Label labelNbLoup;
    private Label labelNbMouton;
    int nbVivant;
    int nbLoup;
    int nbMouton;

    public MonObservateurActeurs(Pane panneauDeJeu, Label nbVivant, Label labelNbLoup, Label labelNbMouton) {
        this.panneauDeJeu = panneauDeJeu;
        this.labelNbVivant = nbVivant;
        this.labelNbLoup = labelNbLoup;
        this.labelNbMouton = labelNbMouton;
        this.nbVivant = 0;
        this.nbLoup = 0;
        this.nbMouton = 0;
    }


    // List = ['a', 'b']
    // List = ['a', 'b', 'c' , 'd']
    // getAddedSubList = ['c', 'd']
    // ? N'importe quel type
    // extends Acteur toute les sous classe

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        while(change.next()) {
            for (Acteur nouveau : change.getAddedSubList()) {
                if (nouveau instanceof Loup) {
                    nbLoup++;
                }
                else {
                    nbMouton++;
                }

                creerSprite(nouveau);
            }
            for (Acteur mort : change.getRemoved()) {
                if (mort instanceof Loup) {
                    nbLoup--;
                }
                else {
                    nbMouton--;
                }
                enleverSprite(mort);
            }
            labelNbVivant.setText(Integer.toString(change.getList().size()));
            labelNbLoup.setText(Integer.toString(nbLoup));
            labelNbMouton.setText(Integer.toString(nbMouton));
        }
    }

    public void creerSprite(Acteur a) {
        System.out.println("Sprite ajouté !");
        Circle r = new Circle(3);
        r.setId(a.getId());

        if (a instanceof Loup) {
            r.setFill (Color.RED);
        }
        else {
            r.setFill (Color.WHITE);
        }
        r.translateXProperty().bind(a.getXProperty());
        r.translateYProperty().bind(a.getYProperty());
        panneauDeJeu.getChildren().add(r);
    }

    public void enleverSprite(Acteur a) {
        this.panneauDeJeu.getChildren().remove(this.panneauDeJeu.lookup("#" + a.getId()));
    }
}
