package com.game.kalah.service;

import com.game.kalah.model.KalahPlayer;

public interface KalahManager {

    int play(int selectedPit, int[] board, KalahPlayer current);

}
