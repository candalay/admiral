package com.game.kalah.service;

public interface KalahService {

	void board();

    int[] getBoard();

    void play(int pit);

    int turn();

    boolean isGameOver(int[] board);
}
