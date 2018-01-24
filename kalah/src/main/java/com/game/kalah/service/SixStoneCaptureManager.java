package com.game.kalah.service;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.game.kalah.model.KalahPlayer;

@Service("SixStoneCaptureManager")
public class SixStoneCaptureManager implements CaptureStoneManager {

	private final Logger logger = LoggerFactory.getLogger(SixStoneCaptureManager.class);
	
	/*
	 * If the last seed is deposited into an empty hole owned by the current 
	 * player and the hole opposite is not empty, 
	 * the player captures all of the seeds in the opposite hole plus the capturing seed, 
	 * which go into the players scoring well.
	 * */
	@Override
	public boolean checkIfPlayerStonesAvailableToCapture(int selected, int[] board, KalahPlayer player, int total) {
		
		int stones = board[selected];
		int[] availablePits = player.getAvailablePits();
		
        if (selected + stones < total) {
            if (ArrayUtils.contains(availablePits, selected + stones) &&
            		board[selected + stones] == 0 &&
            	    board[total - selected - stones] > 0) {
                return true;
            }
        } else if (ArrayUtils.contains(availablePits, selected + stones + 1 - total) &&
                board[selected + stones + 1 - total] == 0 &&
                board[total - selected - stones - 1 + total] > 0) {
            return true;
        } 

		return false;
	}

	@Override
	public int[] capturePlayersStone(int selected, int[] board, KalahPlayer player, int total) {
		
        int stones = board[selected];
        int playersHouse = player.getPlayerSouth();
        
        logger.info("Current board before capture: {}", board);

        if (selected + stones < total) {
            board[playersHouse] = board[playersHouse]
                    + board[selected + stones]
                    + board[total - selected - stones];
            board[selected + stones] = 0;
            board[total - selected - stones] = 0;
        } else {
            board[playersHouse] = board[playersHouse]
                    + board[selected + stones + 1 - total]
                    + board[total - selected - stones - 1 + total];
            board[selected + stones + 1 - total] = 0;
            board[total - selected - stones - 1 + total] = 0;
        }
        
        logger.info("Current board afters capture: {}", board);
        
        return board;
	}

}
