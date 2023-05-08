package com.application.loupmouton.modele;

import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

	private int width,height;	
	private ObservableList<Acteur> acteurs;
	private IntegerProperty nbToursProperty;

	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.nbToursProperty = new SimpleIntegerProperty(0);
		this.acteurs= FXCollections.observableArrayList();
	}

	// Getter & Setter

	public final int getNbTours(){
		return nbToursProperty.getValue();
	}

	public final IntegerProperty nbToursProperty() {
		return this.nbToursProperty;
	}

	public final void setNbTours(int n){
		nbToursProperty.setValue(n);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ObservableList<Acteur> getActeurs() {
		return acteurs;
	}

	public Acteur getActeur(String id) {
		for(Acteur a:this.acteurs){
			if(a.getId().equals(id)){
				return a;
			}
		}
		return null;
	}


	public void ajouter(Acteur a){
		acteurs.add(a);
	}

	public boolean dansTerrain(int x, int y){
		return (0 <= x && x<this.width && 0<=y && y< this.height);
	}

	public void unTour(){
		// cela ne peut etre un foreach a cause des naissances 
		// modification de acteurs.
		System.out.println("tour " + this.getNbTours());
		for(int i=0;i<acteurs.size(); i++){
			Acteur a = acteurs.get(i);			
			a.agit();
		}
		for(int i=acteurs.size()-1; i>=0;i--){
			Acteur a = acteurs.get(i);
			if(!a.estVivant()){
				System.out.println("mort de : " + a);
				acteurs.remove(i);
			}
		}
		this.setNbTours(this.getNbTours() + 1);
	}


	public void init(){
		for(int i=0; i<50;i++){
			this.ajouter(new Loup(this));
		}
		for(int i=0; i<100;i++){
			this.ajouter(new Mouton(this));
		}
	}


}
