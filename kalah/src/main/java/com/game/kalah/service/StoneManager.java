package com.game.kalah.service;

import com.game.kalah.model.KalahPlayer;

public interface StoneManager {
	
	int[] move(int selected, int[] board, KalahPlayer player, int total);
}
