package com.game.kalah.model;

public class KalahPlayer {

	private int id;
	private int[] availablePits;
	private int playerNorth;
	private int playerSouth;

	public KalahPlayer(int id, int[] availablePits, int playerSouth, int playerNorth) {
		this.id = id;
		this.availablePits = availablePits;
		this.playerNorth = playerNorth;
		this.playerSouth = playerSouth;
	}

	public int getId() {
		return id;
	}

	public int getPlayerNorth() {
		return playerNorth;
	}

	public void setPlayerNorth(int playerNorth) {
		this.playerNorth = playerNorth;
	}

	public int getPlayerSouth() {
		return playerSouth;
	}

	public void setPlayerSouth(int playerSouth) {
		this.playerSouth = playerSouth;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getAvailablePits() {
		return availablePits;
	}

	public void setAvailablePits(int[] availablePits) {
		this.availablePits = availablePits;
	}

}
