package com.game.kalah.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.game.kalah.model.KalahPlayer;

@Service("SixStoneNextPlayerManager")
public class SixStoneNextPlayerManager implements NextPlayerManager {

	private final Logger logger = LoggerFactory.getLogger(SixStoneNextPlayerManager.class);
	
	@Override
	public int nextPlayer(int selected, int[] board, KalahPlayer player, int total) {
		
		logger.info("Calculating next player");
		
		int stones = board[selected];
        int house = player.getPlayerSouth();
        int turn;

        if (selected + stones < total) {
            if (selected + stones == house) {
                turn = player.getId();
            } else {
                turn = changePlayer(player);
            }
        } else if (selected + stones - total == house) {
            turn = player.getId();
        } else {
            turn = changePlayer(player);
        }

        return turn;
	
	}

	private int changePlayer(KalahPlayer player) {
		return player.getId() == 1 ? 2 : 1;
	}

}
