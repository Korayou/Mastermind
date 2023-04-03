package com.example.mastermind.game;

import android.graphics.Color;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/*
représente la grille de jeu à savoir la liste des soumissions ainsi que leur notation
 */
public class Grille {
    private LinkedList<Integer> soumissions;
    private LinkedList<Integer> notations;

    public Grille(){
        this.soumissions=new LinkedList<Integer>();
        this.notations=new LinkedList<Integer>();
    }

    public void addSoumission(Collection<Integer> newSub){
        this.soumissions.addAll((this.soumissions.size()-4),newSub);
    }

    public void initGrille(Collection<Integer> SubToCopy){
        for(int i=0;i<10;i++){
            this.soumissions.addAll(SubToCopy);
        }
    }

    public void addNotation(Collection<Integer> newNot){
        this.notations.addAll(newNot);
    }

    public Collection<Integer> getSoumissions(){
        return this.soumissions;
    }
    public Collection<Integer> getNotations(){
        return this.notations;
    }


}
