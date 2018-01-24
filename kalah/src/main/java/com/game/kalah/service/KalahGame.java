package com.game.kalah.service;


public interface KalahGame {

    void init();

    void play(int pit);

    boolean isGameOver(int[] gameBoard);

    int[] getGameBoard();

    int getTurnFor();
}
