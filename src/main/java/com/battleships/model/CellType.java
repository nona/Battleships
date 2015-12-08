package com.battleships.model;

public enum CellType {

	NOSHOT('.'), 
	MISS('-'), 
	HIT('X'), 
	EMPTY(' ');

	private char character;

	CellType(char character) {
		this.character = character;
	}

	public char getCharacter() {
		return character;
	}

	public String getCharacterString() {
		return String.valueOf(character);
	}
}
