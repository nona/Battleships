package com.battleships.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import com.battleships.application.BattleshipsConfiguration;
import com.battleships.model.*;
import com.battleships.view.*;

@Controller
@SpringBootApplication
public class GameController implements CommandLineRunner {

	@Autowired
	GameState gameState;

	@Autowired
	AbstractView view;

	@Override
	public void run(String... arg0) throws Exception {

		gameState.generateNewGameState();
		view.printBoard(gameState.getBoard());
		view.printEnterMessage();

		try (Scanner scanner = new Scanner(System.in)) {
			while (!gameState.checkForGameOver()) {
				String input = scanner.nextLine();
				Command command = processUserInput(input);
				switch (command) {
				case QUIT:
					view.printGameEndedByUser();
					System.exit(0);
				case SHOW:
					view.printShipsOnBoard(gameState);
					break;
				case WRONGFORMAT:
					view.printError();
					break;
				case HIT:
					view.printHitStatus(gameState.hit(command.getHitCell()));
					view.printBoard(gameState.getBoard());
					break;
				}
				view.printEnterMessage();
			}
			view.printStatistics(gameState);
			System.exit(0);
		}

	}

	public static void main(String... args) {
		SpringApplication.run(new Object[] { BattleshipsConfiguration.class, GameController.class }, args);
	}

	public static Command processUserInput(String input) {
		switch (input) {
		case "quit":
			return Command.QUIT;
		case "show":
			return Command.SHOW;
		default:
			if (input.matches("[A-J]([1-9]|10)")) {
				Command command = Command.HIT;
				command.setHitCell(input);
				return command;
			} else {
				return Command.WRONGFORMAT;
			}
		}
	}

}
