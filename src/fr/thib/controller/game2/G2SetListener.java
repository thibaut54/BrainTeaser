package fr.thib.controller.game2;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.thib.exception.TooManySpotsException;
import fr.thib.model.Config;
import fr.thib.model.game2.G2Interface;
import fr.thib.view.game2.G2Challenger;
import fr.thib.view.game2.G2Defender;
import fr.thib.view.game2.G2Duel;
import fr.thib.view.game2.Game2Setting;


public class G2SetListener implements ActionListener {

	private Game2Setting game2Setting;
	private G2Interface game2;


	//----------CONSTRUCTOR----------

	public G2SetListener( Game2Setting game2Setting ) {

		this.game2Setting = game2Setting;

	}


	@Override
	public void actionPerformed( ActionEvent ev ) {

		// If player selects a mode
		displayModDescription( ev );

		// If player selects a difficulty
		displayDifficutlty( ev );

		
		nbDigitTry( );
		
		// If player clic on Start button
		if ( ev.getSource( ) == game2Setting.getBtnStart( ) ) {
			
			try {
				if( game2Setting.getRadBtnMode( 0 ).isSelected( ) ) {
					
					game2 = new G2Challenger( game2Setting.getMainFrame( ) );
					
					game2.getCombiComputer( ).setNbTry( ( byte ) 0 );
					
				}
				
				else if( game2Setting.getRadBtnMode( 1 ).isSelected( ) ) {
	
					Config.testSetting( );
					game2 = new G2Defender( game2Setting.getMainFrame( ) );
					
				}
				
				else if( game2Setting.getRadBtnMode( 2 ).isSelected( ) ) {
					
					Config.testSetting( );
					game2 = new G2Duel( game2Setting.getMainFrame( ) );
	
				}
				
				game2Setting.getMainFrame( ).getPanGame( ).removeAll( );
				game2Setting.getMainFrame( ).getPanGame( ).add( ( Component ) game2 );
			}
			catch ( TooManySpotsException e ) {
				JOptionPane.showMessageDialog( game2Setting , e.getMessage( ) , "Invalid settings" ,
						JOptionPane.ERROR_MESSAGE );
			
			}
				
		}

		game2Setting.getMainFrame( ).revalidate( );

	}

	/**
	 * Displays the description of the mode that is selected
	 * 
	 * @param ev
	 */
	public void displayModDescription( ActionEvent ev ) {

		for ( int i = 0; i < game2Setting.getRadBtnMode( ).length; i++ ) {
			if ( ev.getSource( ) == game2Setting.getRadBtnMode( i ) ) {
				game2Setting.getJlabMode( ).setText( game2Setting.getStrJlabMode( i ) );
			}
		}
	}

	/**
	 * Dispolays the details of each difficulty level as it is defined in config.properties
	 * 
	 * @param ev
	 */
	public void displayDifficutlty( ActionEvent ev ) {

		if ( ev.getSource( ) == game2Setting.getRadBtnMode( 0 )
				|| ev.getSource( ) == game2Setting.getRadBtnMode( 2 ) ) {
		
			for ( int i = 0; i < game2Setting.getRadBtnDiff( ).length; i++ ) {
				
				game2Setting.getJlabDiff( i ).setText( Config.getColorProp( i ) + " colors, "
					+ Config.getPieceProp( i ) + " squares, " + Config.getTryG2Prop( i ) + " tries" );
			}
		}

		else if ( ev.getSource( ) == game2Setting.getRadBtnMode( 1 ) ) {
			
			for ( int i = 0 , j = 3; i < game2Setting.getRadBtnDiff( ).length; i++ , j-- ) {
			
				game2Setting.getJlabDiff( i ).setText( Config.getColorProp( j ) + " colors, "
					+ Config.getPieceProp( j ) + " squares, " + Config.getTryG2Prop( j ) + " tries" );
			}
		}
	}



	/**
	 * Register the parameters corresponding to the difficulty level selected
	 */
	public void nbDigitTry( ) {

		for ( int i = 0; i < game2Setting.getRadBtnDiff( ).length; i++ ) {

			// If Challenger or Duel mode is selected
			if ( game2Setting.getRadBtnMode( 0 ).isSelected( ) 
					|| game2Setting.getRadBtnMode( 2 ).isSelected( ) ) {
				if ( game2Setting.getRadBtnDiff( i ).isSelected( ) 
						 ) {
					Config.setNbColor( Config.getColorProp( i ) );
					Config.setNbTry( Config.getTryG2Prop( i ) );
					Config.setNbPiece( Config.getPieceProp( i ) );
				}
			}

			// If Defender mode is selected, the number of digits an tries
			// of each difficulty level is inverted
			else if ( game2Setting.getRadBtnMode( 1 ).isSelected( ) ) {
				if ( game2Setting.getRadBtnDiff( i ).isSelected( ) ) {
					Config.setNbColor( Config.getColorProp( 3 - i ) );
					Config.setNbTry( Config.getTryG2Prop( 3 - i ) );
					Config.setNbPiece( Config.getPieceProp( 3 - i ) );
				}
			}
		}
	}

}
