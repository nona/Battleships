package com.battleships.view;

import org.springframework.stereotype.Component;

import com.battleships.model.Board;
import com.battleships.model.GameState;
import com.battleships.model.HitStatus;

@Component
public class ConsoleView extends AbstractView {

	public void printEnterMessage() {
		System.out.print(enterMessage);
	}

	public void printHitStatus(HitStatus status) {
		switch (status) {
		case HIT:
			System.out.print(hitMessage);
			break;
		case SUNK:
			System.out.print(sunkMessage);
			break;
		case MISS:
			System.out.print(missMessage);
			break;
		}
	}

	public void printStatistics(GameState state) {
		System.out.println(sunkMessage);
		System.out.println(getBoardString(state.getBoard()));
		System.out.printf(statisticsMessage, state.getNumberOfAttempts());
	}

	public void printBoard(Board board) {
		System.out.println(getBoardString(board));
	}

	public void printShipsOnBoard(GameState state) {
		System.out.println(getShipsOnBoardString(state));
	}

	public void printGameEndedByUser() {
		System.out.println(endMessage);
	}

	public void printError() {
		System.out.println(errorMessage);
	}
}
