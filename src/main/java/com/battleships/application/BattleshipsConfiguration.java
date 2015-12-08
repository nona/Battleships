package com.battleships.application;

import org.springframework.context.annotation.Configuration;

import com.battleships.model.Board;
import com.battleships.model.GameState;
import com.battleships.model.ShipFactory;
import com.battleships.view.AbstractView;
import com.battleships.view.ConsoleView;

import org.springframework.context.annotation.Bean;

@Configuration
public class BattleshipsConfiguration {
	
	@Bean
    public Board board() {
        return new Board();
    }
	
	@Bean
    public ShipFactory shipFactory() {
        return new ShipFactory();
    }
	
	@Bean
    public GameState gameState() {
		return new GameState();
    }
	
	@Bean
    public AbstractView abstractView() {
		return new ConsoleView();
    }
}
