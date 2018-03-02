package fr.thib.controller.game1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.model.game1.Code;
import fr.thib.view.game1.G1Defender;

public class G1DefListener implements ActionListener {

	
	
	static final Logger logger = LogManager.getLogger( );

	private G1Defender game;

	
	
	//----------CONSTRUCTOR----------
	
	
	public G1DefListener( G1Defender game ) {

		this.game = game;

	}

	
	
	//----------ACTIONS----------
	
	
	@Override
	public void actionPerformed( ActionEvent ev ) {

		// Store the input code by player with the selector in codePlayer
		if ( game.getNbTryPlayer( ) == 0 ) {

			game.setCodePlayer( new Code( game.myNumber( ) ) ); 

			// If the apps is started with "dev" parameter
			if( Config.isDev( ) ) {
				game.getSolution( ).setText( "Congrats, you said the dev word! The code is " + game.getCodePlayer( ).toString( )  ); //ICI
			}
			
			game.getBtnValidate( ).setText( "Adverse hacker attack" );
			
			// Disable selector so that player cannot change code during a game
			game.activSelector( false );
			
		}


		game.getCodePlayer( ).searchCode( game );
		
		game.getCodePlayer( ).displayResult( game );
		
		game.getCodePlayer( ).testNumbersDef( game );

		game.addTryPlayer( );
	}
}
