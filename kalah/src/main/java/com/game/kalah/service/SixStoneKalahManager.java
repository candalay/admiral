package com.game.kalah.service;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.kalah.model.KalahPlayer;

@Service("SixStoneKalahManager")
public class SixStoneKalahManager implements KalahManager {

	private final Logger logger = LoggerFactory.getLogger(SixStoneKalahManager.class);

	private static final int TOTAL_PITS = 14;

	@Autowired
	@Qualifier("SixStoneCaptureManager")
	private CaptureStoneManager captureStoneManager;

	@Autowired
	@Qualifier("SixStoneManager")
	private StoneManager stoneManager;

	@Autowired
	@Qualifier("SixStoneNextPlayerManager")
	private NextPlayerManager nextPlayerManager;

	@Override
	public int play(int selected, int[] board, KalahPlayer current) {

		logger.info("player id - {}", current.getId());

		if (!checkMove(selected, board, current)) {
			logger.info("wrong move");
			return current.getId();
		}

		boolean captureCondition = captureStoneManager.checkIfPlayerStonesAvailableToCapture(selected, board, current,
				TOTAL_PITS);

		board = stoneManager.move(selected, board, current, TOTAL_PITS);

		if (captureCondition) {
			board = captureStoneManager.capturePlayersStone(selected, board, current,
					TOTAL_PITS);
		}

		return nextPlayerManager.nextPlayer(selected, board, current, TOTAL_PITS);
	}

	private boolean checkMove(int selected, int[] board, KalahPlayer current) {
		return (ArrayUtils.contains(current.getAvailablePits(), selected) && board[selected] != 0);
	}

}
