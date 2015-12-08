package com.battleships.model;

public enum Command {
	WRONGFORMAT, 
	HIT, 
	QUIT, 
	SHOW;

	private String hitCell;

	public String getHitCell() {
		return hitCell;
	}

	public void setHitCell(String hitCell) {
		this.hitCell = hitCell;
	}
}
