package com.battleships.model;

import java.util.HashSet;


public class Ship {
	private ShipType type;
	private HashSet<Cell> shipCells;
	
	public ShipType getType() {
		return type;
	}
	public void setType(ShipType type) {
		this.type = type;
	}
	public HashSet<Cell> getShipCells() {
		return shipCells;
	}
	public void setShipCells(HashSet<Cell> shipCells) {
		this.shipCells = shipCells;
	}
	
	public boolean isSunk() {
		for (Cell cell: shipCells) {
			if (!CellType.HIT.equals(cell.getType())) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks whether the cell is part of ship structure
	 */
	public boolean isCellInsideShip(Cell cell) {
		for(Cell shipCell : shipCells) {
			if(cell.equals(shipCell)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasIntersection(Ship ship) {
		for(Cell shipCell : shipCells) {
			if(ship.isCellInsideShip(shipCell)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void hitCell(Cell cell) {
		shipCells.remove(cell);
		shipCells.add(cell);
	}

}
