package com.game.kalah.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import static java.util.Arrays.fill;

import com.game.kalah.KalahConstants;
import com.game.kalah.model.KalahPlayer;

@Service("SixStoneKalahGame")
public class SixStoneKalahGame implements KalahGame {

	private final Logger logger = LoggerFactory.getLogger(SixStoneKalahGame.class);

	private final KalahPlayer playerNorth = new KalahPlayer(1, new int[] { 1, 2, 3, 4, 5, 6 }, 0, 7);
	private final KalahPlayer playerSouth = new KalahPlayer(2, new int[] { 8, 9, 10, 11, 12, 13 }, 7, 0);

	private volatile int[] gameBoard;

	private int turn = 1;

	@Autowired
	@Qualifier("SixStoneKalahManager")
	private KalahManager kalahManager;

	@Override
	public void init() {

		int stones = KalahConstants.SIX_STONES;
		int pits = KalahConstants.SIX_PITS;
		this.gameBoard = new int[(pits * 2) + 2];

		
		int northStartIndex = 1;
		int southStartIndex = northStartIndex + stones + 1;

		fill(this.gameBoard, northStartIndex, northStartIndex + stones, pits);
		fill(this.gameBoard, southStartIndex, southStartIndex + stones, pits);
	}

	@Override
	public void play(int pit) {
		
		logger.info("playing from {} ", pit);

		KalahPlayer current;

		if (turn == playerNorth.getId()) {
			current = playerNorth;
		} else {
			current = playerSouth;
		}

		this.turn = kalahManager.play(pit, this.gameBoard, current);
	}

	@Override
	public boolean isGameOver(int[] board) {

		boolean north = board[1] == 0 && board[2] == 0 && board[3] == 0 && board[4] == 0 && board[5] == 0
				&& board[6] == 0;

		boolean south = board[8] == 0 && board[9] == 0 && board[10] == 0 && board[11] == 0 && board[12] == 0
				&& board[13] == 0;

		return (north || south);
	}

	@Override
	public int[] getGameBoard() {
		return this.gameBoard;
	}

	@Override
	public int getTurnFor() {
		return this.turn;
	}

}