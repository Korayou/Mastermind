package com.example.mastermind.game;

import android.graphics.Color;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/*
représente la grille de jeu à savoir la liste des soumissions ainsi que leur notation
 */
public class Grille {
    //sizeSubs indique le nombre de soumissions dans la grille. Il permet de limiter ce nombre à 10
    private int sizeSubs;
    // soumission représente la liste des soumissions réalisées ainsi que des cases grises pour compléter la grille
    private LinkedList<Integer> soumissions;
    // notations est la liste des notes attribuées aux soumissions
    private LinkedList<Integer> notations;

    public Grille(){
        this.soumissions=new LinkedList<Integer>();
        this.notations=new LinkedList<Integer>();
    }

    // Méthode permettant d'ajouter une soumission
    public void addSoumission(LinkedList<Integer> newSub){
        //le nombre de soumissions ne doit pas dépasser 10
        if (this.sizeSubs<10) {
            for (int i=0; i<4; i++) {
                //On retire une ligne vide en haut pour remonter l'affichage
                this.soumissions.remove(0);
                //On ajoute ensuite une deriere ligne qui correspond à la nouvelle soumission
                this.soumissions.addLast(newSub.poll());
            }
            this.sizeSubs += 1;
        }
    }

    // Méthode qui permet d'initialiser la grille vide
    public void initGrille(Collection<Integer> SubToCopy){
        //On réécupère une ligne de la couleur qui correspond à l'absence de pion et on remplit la grille avec (soumissions et notations
        for(int i=0;i<10;i++){
            this.soumissions.addAll(SubToCopy);
            this.notations.addAll(SubToCopy);
        }
        // Au début il n'y a pas de soumission
        this.sizeSubs=0;
    }

    // Méthode qui permet d'ajouter des notations comme pour les soumissions
    public void addNotation(LinkedList<Integer> newNot){
        for (int i=0; i<4; i++) {
            //On retire une ligne vide en haut pour remonter l'affichage
            this.notations.remove(0);
            //On ajoute ensuite une deriere ligne qui correspond à la nouvelle soumission
            this.notations.addLast(newNot.poll());
        }
    }

    //getters des soumissions et notations
    public Collection<Integer> getSoumissions(){
        return this.soumissions;
    }
    public Collection<Integer> getNotations(){
        return this.notations;
    }


}
