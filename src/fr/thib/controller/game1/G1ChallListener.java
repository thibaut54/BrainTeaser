package fr.thib.controller.game1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.game1.Code;
import fr.thib.view.game1.G1Challenger;

public class G1ChallListener implements ActionListener {

	static final Logger logger = LogManager.getLogger( );

	private G1Challenger game;

	
	
	
	//----------CONSTRUCTOR----------	
	
	
	
	public G1ChallListener( G1Challenger game ) {

		this.game = game;
	}

	
	//----------ACTIONS----------
	
	@Override
	public void actionPerformed( ActionEvent ev ) {

		// puts the code chosen by the player with the selector in codePlayer
		Code codePlayer = new Code( game.myNumber( ) );
		
		// compare codePlayer to the computers code
		codePlayer.testNumbersChall( game );
		
		codePlayer.displayResult( game );
		
		game.addTryPlayer( );
	}
}
