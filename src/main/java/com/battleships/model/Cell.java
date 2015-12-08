package com.battleships.model;

public class Cell implements Comparable<Cell> {

	private int x;
	private char y;
	private CellType type;

	public Cell(int x, char y) {
		this.x = x;
		this.y = y;
		this.type = CellType.NOSHOT;
	}

	public int getX() {
		return x;
	}

	public char getY() {
		return y;
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		Cell other = (Cell) (obj);
		return other.x == x && other.y == y;
	}

	@Override
	public int compareTo(Cell o) {
		if (this.y < o.y) {
			return -1;
		} else if (this.y > o.y) {
			return 1;
		} else if (this.x < o.x) {
			return -1;
		} else if (this.x > o.x) {
			return 1;
		}
		return 0;
	}

	public String getCoordinatesString() {
		return y + "" + x;
	}

	public String getCellTypeCharacter() {
		return Character.toString(type.getCharacter());
	}

	@Override
	public int hashCode() {
		return this.getCoordinatesString().hashCode();
	}

}
