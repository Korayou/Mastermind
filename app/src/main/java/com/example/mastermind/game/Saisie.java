package com.example.mastermind.game;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/*
* Classe qui correspond à uen zone de saisie (soumission d'une combinaison ou notation)
* choix correspond aux couleurs pouvant être saisies en fonction de la situation et selection à la combinaison choisie
 */
public class Saisie {

    private LinkedList<Integer> choix;
    private LinkedList<Integer> selection;
    public Saisie(){
        this.choix=new LinkedList<Integer>();
        this.selection=new LinkedList<Integer>();
    }

    // getters et setters

    public void setSelection(Collection<Integer> chosenColors){
        this.selection.addAll(chosenColors);
    }
    public void setSelection(Integer chosenColors, int index){
        this.selection.set(index, chosenColors);
    }

    public Collection<Integer> getChoix(){
        return this.choix;
    }

    public void setChoix(Collection<Integer> colorsSelected){
        this.choix.addAll(colorsSelected);
    }

    public Collection<Integer> getSelection(){
        return this.selection;
    }

}
