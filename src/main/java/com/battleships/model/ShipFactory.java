package com.battleships.model;

import java.util.HashSet;

public class ShipFactory {

	public Ship createBattleShip() {
		return createShip(ShipType.BATTLESHIP);
	}

	public Ship createDestroyer() {
		return createShip(ShipType.DESTROYER);
	}

	private Ship createShip(ShipType type) {
		Ship ship = new Ship();

		ship.setType(type);

		int shipSize = type.getCellSize();

		while (true) {
			int x = (int) (Math.random() * Board.ROWS) + 1;
			int y = (int) (Math.random() * Board.COLUMNS - 1);
			ShipDirection direction = ShipDirection.getRandom();
			boolean isVertical = direction.equals(ShipDirection.VERTICAL);

			HashSet<Cell> shipCells = new HashSet<Cell>();
			boolean isCreationPossible = true;
			for (int j = 0; j < shipSize; j++) {
				int row = x + (isVertical ? 0 : j);
				int col = y + (isVertical ? j : 0);

				if (!isCreationPossible(row, col)) {
					isCreationPossible = false;
					break;
				}

				shipCells.add(new Cell(row, Character.toChars(65 + col)[0]));
			}

			if (isCreationPossible) {
				ship.setShipCells(shipCells);
				return ship;
			}

		}
	}

	private boolean isCreationPossible(int row, int col) {
		return row >= 1 && row <= Board.ROWS && col >= 0 && col < Board.COLUMNS;
	}
}
