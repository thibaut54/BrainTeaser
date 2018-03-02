package fr.thib.controller.game2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.thib.model.Config;
import fr.thib.model.game2.Combi;
import fr.thib.view.game2.G2Defender;

public class G2DefListener implements ActionListener {

	private G2Defender game;
	public static byte[] score;
	private boolean firstTry = true;

	
	
	//----------CONSTRUCTOR----------
	
	
	public G2DefListener( G2Defender game ) {

		this.game = game;
	}


	@Override
	public void actionPerformed( ActionEvent ev ) {

		if( ev.getSource( ) == game.getBtnValidate( ) ) {
			
			// First clic is to register the code created by the player
			if ( firstTry ) {
				
				game.getBtnValidate( ).setText( "<html>COMPUTER <br>TRY" );
				game.getBtnValidate( ).setFont( new Font( "Berlin Sans FB", Font.BOLD, 12 ) );

				
				game.setCombiPlayer(  new Combi( game.myCombi( ) ) );
				
				game.activSelector( false );
				game.hideBtnSelector( game.getSelector( ) );
				
				firstTry = false;
			}
			
			// Second clic to start computer breaking the code
			else {
				
				// If first computer try, then generate every possible solutions
				if( game.getNbTryPlayer() == 0 ) {
					
					Combi.genPossible( );
				}
				
				// randomly picks one combination among every possible solution
				game.getCombiPlayer( ).pickCombi( game );
				
				game.displayCombi( game.getCombiComputerTry( ) );
				
				// calculates the score of the combination compared to the target
				byte [] score = game.getCombiPlayer( ).testCombi( game.getCombiComputerTry( ) );
				
				
				// displays that score on the line
				game.displayScore( score );
				
				// run the search algorithm 
				game.getCombiPlayer( ).searchCombi( game );
				
				
				// each turn, tests if the computer combination is not equal to the target
				if ( ! game.getCombiPlayer( ).equals( game.getCombiComputerTry( ) ) ) {
					// if there are still tries
					if ( game.getNbTryPlayer() < Config.getNbTry( ) - 1 ) {
						
						game.getJTextInfo().setText("I didn't find out your secret "
								+ "combination! But I can keep trying!");
						game.addTryPlayer( );
					}
					
					else {
						
						game.getJTextInfo().setText("Hey! You won! I didn't find out your secret "
								+ "combination! Congratulations!");
						game.getPanInfo().setBackground( Color.GREEN );
						game.getBtnValidate( ).setEnabled( false );
					}

				}
				// if combinations are not different, then they are equals
				else {
					
					game.getJTextInfo( ).setText( "Hey, I won! But please don't get mad, "
							+ " I'm just very good at maths  ;)" );
					game.getPanInfo( ).setBackground( Color.RED );
					game.getBtnValidate( ).setEnabled( false );
				}
			}
		}
	}
}
