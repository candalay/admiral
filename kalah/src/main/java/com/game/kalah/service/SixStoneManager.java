package com.game.kalah.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.game.kalah.model.KalahPlayer;

@Service("SixStoneManager")
public class SixStoneManager implements StoneManager {
	
	private final Logger logger = LoggerFactory.getLogger(SixStoneManager.class);

	@Override
	public int[] move(int selected, int[] board, KalahPlayer player, int total) {
		
		logger.info("Stone move is started");
	
		int southHouse = player.getPlayerSouth();
        int stones = board[selected];

        // Indicates that the stones will meet the opponent's house in their way and skip it
        boolean skipped = false;

        // Move all stones from the selected pit moving to the right
        for (int i = 1; i < stones + 1; i++) {

            //Keep sowing until you reach the end of the board
            if (selected + i < total) {

                //If a stone meets the opponent's house, skips it and so do the rest of the stones
                if (selected + i == southHouse) {
                    skipped = true;
                }

                if (skipped) {
                	board[selected + i + 1]++;
                } else {
                	board[selected + i]++;
                }
            } else {
                //When you reach the end of the board start from the beginning

                //If a stone meets the opponent's house, skips it and so do the rest of the stones
                if (selected + i - total == southHouse) {
                    skipped = true;
                }

                if (skipped) {
                    board[selected + i + 1 - total]++;
                } else {
                	board[selected + i - total]++;
                }
            }
        }

        //Empty player's selected pit
        board[selected] = 0;

        return board;
	}

}
