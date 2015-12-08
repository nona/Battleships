package com.battleships.view;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.battleships.model.Board;
import com.battleships.model.Cell;
import com.battleships.model.CellType;
import com.battleships.model.GameState;
import com.battleships.model.HitStatus;

public abstract class AbstractView {

	static final String enterMessage = "Enter coordinates (row, col), e.g. A5 = ";

	static final String statisticsMessage = "Well done! You completed the game in %d shots\n";

	static final String endMessage = "*** Game ended by user! ***";

	static final String errorMessage = "*** Error ***";

	static final String sunkMessage = "*** Sunk ***";

	static final String hitMessage = "*** Hit ***";

	static final String missMessage = "*** Miss ***";

	/**
	 * Lambda expression for finding all cells starting with a given letter.
	 */
	private static final Function<String, Predicate<Cell>> startsWithLetter = letter -> cell -> cell
			.getCoordinatesString().startsWith(letter);

	/**
	 * Represents the location of the ships on the board.
	 * 
	 * @param state
	 * @return
	 */
	public static String getShipsOnBoardString(GameState state) {
		String boardStr = "\n  "
				+ IntStream.range(1, Board.ROWS + 1).boxed().map(String::valueOf).collect(Collectors.joining(" "));

		for (int j = 0; j < Board.COLUMNS; j++) {
			String letter = String.valueOf(Character.toChars(65 + j)[0]);
			boardStr += '\n' + letter + " "
					+ state.getBoard().getBoardCells().stream()
							.filter(startsWithLetter.apply(letter)).map(cell -> state.isSomeOfTheShipsHit(cell)
									? CellType.HIT.getCharacterString() : CellType.EMPTY.getCharacterString())
					.collect(Collectors.joining(" "));
		}

		return boardStr;
	}

	/**
	 * Represents the board with respect of user's hit attempts.
	 * 
	 * @param board
	 * @return
	 */
	public static String getBoardString(Board board) {
		String boardStr = "\n  "
				+ IntStream.range(1, Board.ROWS + 1).boxed().map(String::valueOf).collect(Collectors.joining(" "));

		for (int j = 0; j < Board.COLUMNS; j++) {
			String letter = String.valueOf(Character.toChars(65 + j)[0]);
			boardStr += '\n' + letter + " " + board.getBoardCells().stream().filter(startsWithLetter.apply(letter))
					.map(Cell::getCellTypeCharacter).collect(Collectors.joining(" "));
		}

		return boardStr;
	}

	public abstract void printEnterMessage();

	public abstract void printHitStatus(HitStatus status);

	public abstract void printStatistics(GameState state);

	public abstract void printBoard(Board board);

	public abstract void printShipsOnBoard(GameState state);

	public abstract void printGameEndedByUser();

	public abstract void printError();
}
