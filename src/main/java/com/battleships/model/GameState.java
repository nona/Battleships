package com.battleships.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameState {

	int numberOfAttempts;

	private List<Ship> ships;

	@Autowired
	private Board board;

	@Autowired
	private ShipFactory shipFactory;

	public GameState() {
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public boolean isSomeOfTheShipsHit(Cell cell) {
		for (Ship ship : ships) {
			if (ship.isCellInsideShip(cell)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns a ship hit and null if no one.
	 * @param cell
	 * @return Ship
	 */
	public Ship getHitShip(Cell cell) {
		for (Ship ship : ships) {
			if (ship.isCellInsideShip(cell)) {
				return ship;
			}
		}
		return null;
	}

	/**
	 * Checks if all ships' cells are destroyed.
	 * @return boolean
	 */
	public boolean checkForGameOver() {
		for (Ship ship : this.getShips()) {
			if (!ship.isSunk()) {
				return false;
			}
		}
		return true;
	}

	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void incrementNumberOfAttempts() {
		this.numberOfAttempts++;
	}

	/**
	 * Initialize a board and create ships;
	 */
	public void generateNewGameState() {
		this.numberOfAttempts = 0;
		this.board = new Board();
		while (true) {
			List<Ship> ships = new ArrayList<Ship>();
			ships.add(shipFactory.createBattleShip());
			ships.add(shipFactory.createDestroyer());
			ships.add(shipFactory.createDestroyer());
			if (isGameStateValid(ships)) {
				this.setShips(ships);
				return;
			}
		}
	}

	/**
	 * Check for intersections in this game state.
	 * @param ships 
	 * @return boolean
	 */
	private boolean isGameStateValid(List<Ship> ships) {
		for (int i = 0; i < ships.size(); i++) {
			for (int j = i + 1; j < ships.size(); j++) {
				if (ships.get(i).hasIntersection(ships.get(j))) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Try to hit a ship. Mark the cell with a proper CellType. Returns whether the hit was successful.
	 * @param cellStr 
	 * @return boolean
	 */
	public HitStatus hit(String cellStr) {
		incrementNumberOfAttempts();
		char col = cellStr.charAt(0);
		int row = Integer.valueOf(cellStr.substring(1));
		Cell cell = new Cell(row, col);
		if (isSomeOfTheShipsHit(cell)) {
			Ship ship = getHitShip(cell);
			cell.setType(CellType.HIT);
			ship.hitCell(cell);
			board.addBoardCell(cell);
			return ship.isSunk() ? HitStatus.SUNK : HitStatus.HIT;
		} else {
			cell.setType(CellType.MISS);
			board.addBoardCell(cell);
			return HitStatus.MISS;
		}
	}
}
