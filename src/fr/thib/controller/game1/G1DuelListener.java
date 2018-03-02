package fr.thib.controller.game1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.model.game1.Code;
import fr.thib.view.game1.G1Duel;
import fr.thib.view.game1.Game1;

public class G1DuelListener implements ActionListener {

	
	
	static final Logger logger = LogManager.getLogger( );

	private G1Duel game;


	
	//----------CONSTRUCTOR----------

	
	public G1DuelListener( G1Duel game ) {

		this.game = game;

	}


	
	//----------ACTIONS----------
	
	
	@Override
	public void actionPerformed( ActionEvent ev ) {

		// Player 1 guess validation
		if ( ev.getSource( ) == game.getBtnPlayer( ) ) {

			// puts the code chosen by the player in codePlayerTry
			Code codePlayerTry = new Code( game.myNumber( ) );

			// compares the players code with the target and displays the message
			// depending on the result
			codePlayerTry.displayResult( game );
			
			// displays the indicators + /- / = for each number of the code
			codePlayerTry.testNumbersChall( game );
			
			// if player wins
			if( codePlayerTry.equals( game.getCodeComputer( ) ) ) {
				
				game.setPlayerWin( true );
			}
			
			// if computer still has tries and he didn't win, activate his button
			if ( ( game.getNbTryComputer( ) < Config.getNbTry( ) )
					&& ! game.isComputerWin( ) ) {
				
				game.getBtnComputer( ).setEnabled( true );
			}
			
			// Disable the mouseListener
			game.activSelector( game.getSelector( ) , false );

			setMouseActiv( game , false );

			game.getBtnPlayer( ).setEnabled( false );
			
			game.addTryPlayer( );
		}

		
		// Computer guess validation
		else if ( ev.getSource( ) == game.getBtnComputer( ) ) {

			// if the player still has tries and he didn't win, activate his button
			if ( ( game.getNbTryPlayer( ) < Config.getNbTry( )  ) && ! game.isPlayerWin( ) ) {
				
				game.getBtnPlayer( ).setEnabled( true );
				game.activSelector( game.getSelector( ) , true );
				
				// Enable the mouseListener
				setMouseActiv( game , true );
				
			}
			
			// computer try to find out the players code
			game.getCodePlayer( ).searchCodeDuel( game );
			
			// displays the result 
			game.getCodePlayer( ).displayResultComputer( game );
			
			// displays the indicators
			game.getCodePlayer( ).testNumbersDuel( game );
			
			// if codeComputerTry is equal to the players code
			if( game.getCodeComputerTry( ).equals( game.getCodePlayer( ) ) ) {
				
				game.setComputerWin( true );
			}


			game.getBtnComputer( ).setEnabled( false );
			
			game.addTryComputer( );
		}

		game.displayIfDraw( );
		
	}


	
	//----------METHOD----------
	
	/**
	 * Enables or disables the mouse in the selector
	 * @param game
	 * 			The game that contains the selector
	 * @param onOff
	 */
	public void setMouseActiv( Game1 game , boolean onOff ){
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			game.getSelector( )[ i ].setMouseActiv( onOff );
		}
	}
}
