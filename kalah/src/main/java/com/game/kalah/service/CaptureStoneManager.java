package com.game.kalah.service;

import com.game.kalah.model.KalahPlayer;

public interface CaptureStoneManager {
	
	boolean checkIfPlayerStonesAvailableToCapture(int selected, int[] board, KalahPlayer player, int total);

	int[] capturePlayersStone(int selected, int[] board, KalahPlayer player, int total);
	
}
