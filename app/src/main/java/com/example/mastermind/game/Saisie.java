package com.example.mastermind.game;

import android.graphics.Color;

/*
* Classe qui correspond à uen zone de saisie (soumission d'une combinaison ou notation)
* choix correspond aux couleurs pouvant être saisies en fonction de la situation et selection à la combinaison choisie
 */
public class Saisie {

    private Color[] choix;
    private Color[] selection;
    public Saisie(int state){
        // state correspond à si on soumet une combinaison ou si on la note (0 combi, 1 notation)
        if (state==0){
            choixCombinaison();
        } else if (state==1){
            notation();
        } // eventuellement signaler une erreur si on a une autre valeur
    }

    // rempli le tableau de couleur avec les pions de l'attaquant
    public void choixCombinaison(){
        this.choix = new Color[6];
    }

    // Rempli le tableau de couleur avec les pions du défenseur
    public void notation(){
        this.choix = new Color[2];
    }

    // getters et setters

    public void setChoix(Color[] colorsToChose){
        this.choix=colorsToChose;
    }

    public Color[] getChoix(){
        return this.choix;
    }

    public void setSelection(Color[] colorsSelected){
        this.selection=colorsSelected;
    }

    public Color[] getSelection(){
        return this.selection;
    }

}
