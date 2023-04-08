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

    //sizeSelection est le nombre de pions dans la zone actuelle de saisie.
    // Cela permet de limiter ce nombre à 4 et vérfier qu'il y en a 4 avant de soumettre
    private int sizeSelection;
    //choix est la liste des couleurs que l'on peut choisir (N&B pour la notation et couleuts pour les soumissions)
    private LinkedList<Integer> choix;
    //selection est la liste des pions choisis pour une soumission ou une notation
    private LinkedList<Integer> selection;
    public Saisie(){
        this.choix=new LinkedList<Integer>();
        this.selection=new LinkedList<Integer>();
    }

    // getters et setters

    //récupère la couleur d'un pion vide pour remplir la zone de saisie avec
    public void initSelection(Integer pionVide){
        this.selection.removeAll(this.selection);
        for (int i=0;i<4;i++){
            this.selection.add(pionVide);
        }
        //au début la zone de séléction est vide
        this.sizeSelection=0;

    }
    //addSelection permet d'ajouter un pion à la zone de saisie
    public void addSelection(int indexcolor){
        //On vérifie qu'il n'y a pas déjà 4 pions
        if (this.sizeSelection<4){
            this.selection.set(this.sizeSelection,this.choix.get(indexcolor));
            this.sizeSelection+=1;
        }

    }

    public void removeSelection(Integer pionVide) {
        if(this.sizeSelection<=4 && this.sizeSelection>0) {
            this.selection.removeLast();
            this.selection.add(pionVide);
            this.sizeSelection-=1;
        }
    }

    public int getSizeSelection(){
        return this.sizeSelection;
    }

    public Collection<Integer> getChoix(){
        return this.choix;
    }

    //Méthode qui permet depuis GameView de changer la liste des choix de couleur en fonction de l'état de la partie
    public void setChoix(Integer[] colorsSelected){
        this.choix.removeAll(this.choix);
        for (int i=0;i<colorsSelected.length;i++){
            this.choix.add(colorsSelected[i]);
        }
    }

    public Integer[] getSelection(){
        Integer[] selectiontab=new Integer[4];
        for (int i=0;i<this.selection.size();i++){
            selectiontab[i]=this.selection.get(i);
        }
        return selectiontab;
    }

}
