package com.game.kalah.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.game.kalah.KalahConstants;
import com.game.kalah.service.KalahService;

@RestController
@RequestMapping("/game/v1")
public class KalahController {

	@Autowired
	@Qualifier("SixStoneKalahService")
	private KalahService kalahService;

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public List<Integer> showBoard() {
		kalahService.board();
		return Arrays.stream(kalahService.getBoard()).boxed().collect(Collectors.toList());
	}

	@RequestMapping(value = "/play/{pit}", method = RequestMethod.POST)
	public Map<String, Object> play(@PathVariable int pit) {

		kalahService.play(pit);
		int[] board = kalahService.getBoard();

		Map<String, Object> resp = new HashMap<>();
		resp.put(KalahConstants.GAME_BOARD, Arrays.stream(board).boxed().collect(Collectors.toList()));
		resp.put(KalahConstants.GAME_TURN, kalahService.turn());
		resp.put(KalahConstants.GAME_STATUS, kalahService.isGameOver(board));

		return resp;
	}

}
