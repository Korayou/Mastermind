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
        for (int i=4;i>0;i--){
            System.out.println("i="+i+", new sub="+newSub);
            this.soumissions.set(this.soumissions.size()-this.sizeSubs*4-i,newSub.pop());
        }
        this.sizeSubs+=1;
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
