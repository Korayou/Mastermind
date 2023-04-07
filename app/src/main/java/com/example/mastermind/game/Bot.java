package com.example.mastermind.game;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;


/* TODO: Modifs pour GameView :
    - création d'un Bot si le mode de jeu est solo
    - création d'une méthode redraw(){invalidate()}
    - variable qui :
        + interdit le switch de state
        + demande de verif si victoire au Bot lors des soumissions
        + soumet à grille sa notation
 */
public class Bot {
    private Integer[] collectionWin;
    private Integer pionVide;
    private Integer[] pionsNotation;

    public Bot(Integer[] pionsAutorisés, Integer[] pionsNotation, Integer pionVide){
        this.collectionWin = new Integer[4];
        this.pionVide=pionVide;
        this.pionsNotation=pionsNotation;
        generationCombiWin(pionsAutorisés);
    }

    protected void generationCombiWin(Integer[] pions){
        Random rand = new Random();
        int nbPions = pions.length;
        for (int i=0;i<4;i++){
            this.collectionWin[i]=pions[rand.nextInt(nbPions)];
        }
    }

    public Integer[] notation(Integer[] soumission){
        LinkedList<Integer> note=new LinkedList<>();
        for(int i=0; i<4;i++) {
            if (this.collectionWin[i]==soumission[i]) {
                note.add(this.pionsNotation[1]);
                System.out.println(note.getLast()+" good");
            }
        }
        //On crée une copie de la combinaison gagnante pour la modifier et éviter la fausse répétition de pions blancs
        Integer[] copyCombi = this.collectionWin;
        for(int i=0; i<4;i++) {
            for (int y=0; y<4;y++) {
                if (i!=y){
                    if (copyCombi[y] == soumission[i]){
                        note.add(this.pionsNotation[0]);
                        System.out.println(note.getLast()+" almost");
                        copyCombi[y]=null;
                    }
                }
            }
        }
        // On complête avec des cases vides
        while (note.size()<4){
            note.addLast(this.pionVide);
        }

        Integer[] tabnote = new Integer[4];
        //fill tab TODO: and shuffle
        Random rand = new Random();
        for(int i=0; i<4;i++) {
            tabnote[i]=note.get(i);
        }
        return tabnote;
    }
}
