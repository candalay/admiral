package com.game.kalah.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SixStoneKalahService")
public class SixStoneKalahService implements KalahService {

	private final Logger logger = LoggerFactory.getLogger(KalahService.class);

    private KalahGame kalahGame;

    @Autowired
    public SixStoneKalahService(@Qualifier("SixStoneKalahGame") KalahGame kalahGame) {
        this.kalahGame = kalahGame;
    }

    @Override
    public void board() {
    	logger.info("board is initialized");
        kalahGame.init();
    }

    @Override
    public int[] getBoard() {
        return kalahGame.getGameBoard();
    }

    @Override
    public void play(int pit) {
        kalahGame.play(pit);
    }

    @Override
    public int turn() {
        return kalahGame.getTurnFor();
    }

    @Override
    public boolean isGameOver(int[] gameBoard) {
        return kalahGame.isGameOver(gameBoard);
    }
}
