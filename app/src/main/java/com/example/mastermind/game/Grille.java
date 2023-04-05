package com.example.mastermind.game;

import android.graphics.Color;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/*
représente la grille de jeu à savoir la liste des soumissions ainsi que leur notation
 */
public class Grille {
    private int sizeSubs;
    private LinkedList<Integer> soumissions;
    private LinkedList<Integer> notations;

    public Grille(){
        this.soumissions=new LinkedList<Integer>();
        this.notations=new LinkedList<Integer>();
        this.sizeSubs=0;
    }

    public void addSoumission(LinkedList<Integer> newSub){
        if (this.sizeSubs<10) {
            for (int i = 4; i > 0; i--) {
                this.soumissions.remove(0);
                this.soumissions.addLast(newSub.poll());
            }
            this.sizeSubs += 1;
        }
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
