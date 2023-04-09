package com.example.mastermind;

public interface SaisieActivity {
    public void addChoix(int index);

    void changeState();

    void removePion();

    void clearChoix();

    int getNbrPion();
}
    
