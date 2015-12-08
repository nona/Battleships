package com.battleships.model;

public enum ShipDirection {
	HORIZONTAL,
	VERTICAL;
	
	public static ShipDirection getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
