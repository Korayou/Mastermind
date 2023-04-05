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

    private int sizeSelection;
    private LinkedList<Integer> choix;
    private LinkedList<Integer> selection;
    public Saisie(){
        this.choix=new LinkedList<Integer>();
        this.selection=new LinkedList<Integer>();
    }

    // getters et setters

    public void initSelection(Integer chosenColors){
        this.selection.removeAll(this.selection);
        for (int i=0;i<4;i++){
            this.selection.add(chosenColors);
        }
        this.sizeSelection=0;

    }
    public void addSelection(Integer chosenColors){
        if (this.sizeSelection<4){
            this.selection.set(this.sizeSelection,this.choix.get(chosenColors));
            this.sizeSelection+=1;
        }

    }

    public int getSizeSelection(){
        return this.sizeSelection;
    }

    public Collection<Integer> getChoix(){
        return this.choix;
    }

    public void setChoix(Collection<Integer> colorsSelected){
        this.choix.removeAll(this.choix);
        this.choix.addAll(colorsSelected);
    }

    public LinkedList<Integer> getSelection(){
        return this.selection;
    }

}
