package fr.thib.controller.game1;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.view.MainFrame;
import fr.thib.view.game1.G1Challenger;
import fr.thib.view.game1.G1Defender;
import fr.thib.view.game1.G1Duel;
import fr.thib.view.game1.Game1Setting;
import fr.thib.view.game1.RegisterCode;

public class G1SetListener implements ActionListener {

	
	
	static final Logger logger = LogManager.getLogger( );

	private Game1Setting game1Setting;
	private MainFrame parent;


	
	//----------CONSTRUCTOR----------

	
	public G1SetListener( Game1Setting game1Setting , MainFrame parent ) {

		this.game1Setting = game1Setting;
		this.parent = parent;

	}


	//----------ACTIONS----------
	
	
	@Override
	public void actionPerformed( ActionEvent ev ) {

		// If player selects a mode
		displayModDescription( ev );

		// If player selects a difficulty
		displayDifficutlty( ev );
		

		// If click on Start button
		if ( ev.getSource( ) == game1Setting.getBtnStart( ) ) {

			nbDigitTry( );
			

			// If Challenger mode is selected
			if ( game1Setting.getRadBtnMode( 0 ).isSelected( ) ) {

				G1Challenger game = new G1Challenger( );

				updateView( game );
			}

			
			// If Defender mode is selected
			else if ( game1Setting.getRadBtnMode( 1 ).isSelected( ) ) {

				G1Defender game = new G1Defender( );
				
				updateView( game );
			}

			
			// If Duel mode is selected
			else if ( game1Setting.getRadBtnMode( 2 ).isSelected( ) ) {
				
				G1Duel game = new G1Duel( );

				RegisterCode dialCode = new RegisterCode( parent , game );
				
				if( dialCode.isCodeRegistered( ) ) {

					updateView( game );
				}
			}
			
			game1Setting.getMainFrame( ).revalidate( );
		}
	}


	// ----------METHODS----------

	/**
	 * Displays the mode description corresponding to the mode selected
	 * @param ev
	 */
	public void displayModDescription( ActionEvent ev ) {

		for ( int i = 0; i < game1Setting.getRadBtnMode( ).length; i++ ) {
			if ( ev.getSource( ) == game1Setting.getRadBtnMode( i ) ) {
				game1Setting.getJlabMode( ).setText( game1Setting.getStrJlabMode( i ) );
			}
		}
	}


	public void displayDifficutlty( ActionEvent ev ) {

		if ( ev.getSource( ) == game1Setting.getRadBtnMode( 0 )
				|| ev.getSource( ) == game1Setting.getRadBtnMode( 2 ) ) {
			for ( int i = 0; i < game1Setting.getRadBtnDiff( ).length; i++ ) {
				game1Setting.getJlabDiff( i )
						.setText( Config.getDigitProp( i ) + " numbers, " 
				+ Config.getTryProp( i ) + " tries" );
			}
		}

		else if ( ev.getSource( ) == game1Setting.getRadBtnMode( 1 ) ) {
			for ( int i = 0 , j = 3; i < game1Setting.getRadBtnDiff( ).length; i++ , j-- ) {
				game1Setting.getJlabDiff( i ).setText( Config.getDigitProp( j ) + " numbers, " 
				+ Config.getTryProp( j ) + " tries" );
			}
		}
	}


	// Enter the parameters depending on the difficulty level 
	// in nbDigit / nbTry in Config
	/**
	 * Sets the difficulty parameters corresponding to the difficulty level
	 * 
	 * @see Config#getDigitProp
	 * @see Config#getTryProp
	 * @see Config#setNbDigit
	 * @see Config#setNbTry
	 */
	public void nbDigitTry( ) {

		for ( int i = 0; i < game1Setting.getRadBtnDiff( ).length; i++ ) {

			// If Challenger mode is selected
			if ( game1Setting.getRadBtnMode( 0 ).isSelected( ) 
					|| game1Setting.getRadBtnMode( 2 ).isSelected( ) ) {
				
				if ( game1Setting.getRadBtnDiff( i ).isSelected( ) ) {
					
					Config.setNbDigit( Config.getDigitProp( i ) );
					Config.setNbTry( Config.getTryProp( i ) );
				}
			}
			
			
			// If Defender mode is selected, the number of digits an tries
			// of each difficulty level is inverted
			else if ( game1Setting.getRadBtnMode( 1 ).isSelected( ) ) {
			
				if ( game1Setting.getRadBtnDiff( i ).isSelected( ) ) {
					
					Config.setNbDigit( Config.getDigitProp( 3 - i ) );
					Config.setNbTry( Config.getTryProp( 3 - i ) );
				}
			}
		}
	}
	
	/**
	 * Update the view and displays the game
	 * @param game
	 * 			The game to displays
	 */
	public <T> void updateView( T game ) {
		game1Setting.getMainFrame( ).getPanGame( ).removeAll( );
		game1Setting.getMainFrame( ).getPanGame( ).add( ( Component ) game );
	}

}
