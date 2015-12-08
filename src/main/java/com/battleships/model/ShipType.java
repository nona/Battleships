package com.battleships.model;

public enum ShipType {

	BATTLESHIP(5), 
	DESTROYER(4);

	private int size;

	ShipType(int size) {
		this.size = size;
	}

	public int getCellSize() {
		return size;
	}
}
