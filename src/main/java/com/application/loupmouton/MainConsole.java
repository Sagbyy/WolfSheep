package com.application.loupmouton;


import com.application.loupmouton.modele.Environnement;
import com.application.loupmouton.modele.VueConsole;

public class MainConsole {

    public static void main(String[] args) {
        Environnement env= new Environnement(20, 20);
        VueConsole vue = new VueConsole(env);
        vue.go();

    }

}