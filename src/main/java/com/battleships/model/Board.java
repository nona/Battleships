package com.battleships.model;

import java.util.TreeSet;;

public class Board {

	public static final int ROWS = 10;
	public static final int COLUMNS = 10;

	private TreeSet<Cell> boardCells;

	public Board() {
		boardCells = new TreeSet<Cell>();
		for (int i = 1; i <= ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				boardCells.add(new Cell(i, Character.toChars(65 + j)[0]));
			}
		}
	}

	public TreeSet<Cell> getBoardCells() {
		return boardCells;
	}

	public void setBoardCells(TreeSet<Cell> boardCells) {
		this.boardCells = boardCells;
	}

	public void addBoardCell(Cell cell) {
		this.boardCells.remove(cell);
		this.boardCells.add(cell);
	}

}
