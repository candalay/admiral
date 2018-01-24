package com.game.kalah.service;

import com.game.kalah.model.KalahPlayer;

public interface NextPlayerManager {
	
	 int nextPlayer(int selected, int[] gameBoard, KalahPlayer player, int total);

}
