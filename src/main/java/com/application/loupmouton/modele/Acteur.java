package com.application.loupmouton.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;


public abstract class Acteur {

	private IntegerProperty x,y;
	private int v; // vitesse de deplacement
	private int dx,dy ;// direction 
	protected Environnement env;
	public static int compteur = 0;
	private String id;
	private int pv;

	public Acteur(int x, int y, int v, Environnement env,int pv) {
		this.pv=pv;
		this.x= new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		compteur++;
		this.tirerDirection();
	}

	public Acteur( int v, Environnement env, int pv) {
		this.pv=pv;
		Random random = new Random();
		int x = random.nextInt(env.getWidth()-1);
		int y = random.nextInt(env.getHeight()-1);
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		compteur++;
		this.tirerDirection();
		//System.out.println("y" + y + "x" +x);
	}	

	public  int getX() {
		return this.x.getValue();
	}

	public IntegerProperty getXProperty() {
		return this.x;
	}

	public  void setX(int n){
		this.x.setValue(n);
	}

	public  int getY() {
		return this.y.getValue();
	}

	public IntegerProperty getYProperty() {
		return this.y;
	}

	public  void setY(int n){
		this.y.setValue(n);
	}

	public String getId() {
		return id;
	}

	public void decrementerPv(int n) {
		this.pv-=n;	
	}

	public void incrementerPv(int n) {
		this.pv+=n;	
	}


	public boolean estVivant() {
		return this.pv>0;
	}

	public void meurt(){
		this.pv=0;
	}


	private void tirerDirection(){
		Random random=new Random();
		int randomInt = random.nextInt(3);
		dx=randomInt-1;
		if(dx==0){
			randomInt=random.nextInt(2)-1;
			if(randomInt==0){
				dy=-1;
			}
			else{
				dy=1;
			}
		}
		else{
			dy=random.nextInt(3)-1;
		}
	}
	//permet de savoir si une action probabiliste se réalise 
	public static boolean reussitProba(double pourcent){
		double x= Math.random();
		double pp=pourcent/100;
		return (x<=pp);
	}


	public void seDeplace(){
		// 20% de chance de changer de direction
		// if(Math.random()*100< pourentageRepro )-
		if(reussitProba(20)) {
			tirerDirection();
		}
		int nposX=this.getX()+(this.v*dx);
		int nposY=this.getY()+(this.v*dy);
		while(!env.dansTerrain(nposX, nposY)){
			tirerDirection();
			nposX = this.getX() + (this.v * dx);
			nposY=this.getY()+(this.v*dy);
		}
		this.setX(nposX);
		this.setY(nposY);
	}


	public abstract void agit();

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y + ", id=" + id ;
	}


}
