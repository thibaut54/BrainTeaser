package fr.thib.controller.game2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.thib.model.Config;
import fr.thib.model.game2.Combi;
import fr.thib.view.game2.G2Duel;

public class G2DuelListener implements ActionListener{

	
	private G2Duel game;
	private boolean firstTry = true;
	private byte nbTryComputer = 0;
	
	
	//----------CONSTRUCTOR----------

	
	public G2DuelListener( G2Duel game ) {

		super( );
		this.game = game;
		
	}

	
	
	//----------ACTIONS----------

	
	@Override
	public void actionPerformed( ActionEvent ev ) {
		

		// Computer guess validation
		if ( ev.getSource( ) == game.getBtnValidate( ) ) {
		

			// If first computer try, register the combination of the player
			if( firstTry ) {
				
				firstTry = false;
				
				game.setCombiPlayer( new Combi( game.myCombi( ) ) );
				
				Combi.genPossible( );
				
				game.getBtnValidate( ).setText( "<html>Computer <br>try" );
				
				game.showCombi( );
				
				game.removeBtnSelector( game.getSelector() );
				
				if( Config.isDev( ) ) {

					showCombiComputer( game );
				}
			}
			
			// If not first computer try, IA tries to find out 
			// the secret combination of the player
			else {
				
				// ransomly picks one combination among every possible solution
				game.getCombiPlayer( ).pickCombi( game );
				
				
				game.displayCombi( game.getCombiComputerTry( ) );
				

				byte [] score = game.getCombiPlayer( ).testCombi( game.getCombiComputerTry( ) );
			
				game.displayScore( score );
				
				game.getCombiPlayer( ).searchCombi( game );

				game.addTryPlayer( );
					
				game.getJTextInfo( ).setText( "Dear player, it's your turn to make a guess! "
						+ "Please validate with the button 'Validate' down there. " );

				// each round, tests the equality of the player secret combination 
				// and the combination tried by the computer
				if( game.getCombiPlayer( ).equals( game.getCombiComputerTry( ) ) ) {
					
					game.setComputerWin( true );

					if( ! Config.isDev( ) ) {
						showCombiComputer( game );
					}
					
					// If the player didn't win just before the computer round, then computer wins
					if( ! game.isPlayerWin( ) ) {
						game.getJTextInfo( ).setText( "Hey, I won! But please don't get mad, "
								+ " I'm just VERY good at maths  ;)" );
						game.getPanInfo( ).setBackground( Color.RED );
					}
					
					// If the player did win just before the computer, then it's a draw
					else {
						game.getJTextInfo( ).setText( "Hey, we both won! It's a draw. Congratulations, "
								+ "this means you are almost as good at this game as me!");
						
						game.getPanInfo( ).setBackground( Color.GRAY );
					}
				}
				
				// If combination are not equals and the computer has no try anymore
				else if( ! game.getCombiPlayer( ).equals( game.getCombiComputerTry( ) ) 
						&& nbTryComputer > Config.getNbTry( ) - 2 ) {
					
					System.out.println( "loop" );
					
					// If the player didn't win just before the computer round, it's a draw
					if( ! game.isPlayerWin( ) ) {
						
						game.getJTextInfo( ).setText( "Hey, we bot lost! What a shame! But hey, it happens !");
						game.getPanInfo( ).setBackground( Color.GRAY );
					}
					
					// If the player did win just before the computer, then player wins
					else {
						
						game.getJTextInfo().setText("You won! I could'nt find out your secret "
								+ "combination! Congratulations :)");
						game.getPanInfo().setBackground( Color.GREEN );
					}
					
					if( ! Config.isDev( ) ) {

						showCombiComputer( game );
					}
				}
				nbTryComputer++;
			}

			
			// just after computer played, disable his buttons
			game.getBtnValidate( ).setEnabled( false );
			game.activSelector( game.getSelector( ) , false );
			
			// just after computer played, if anyone won, then activate player buttons
			if( ! game.isComputerWin( ) && ! game.isPlayerWin( ) 
					&& game.getNbTryPlayer( ) < Config.getNbTry( ) ) {
				
				game.activSelector( game.getSelector2( ) , true );
				game.getBtnValidate2( ).setEnabled( true );
			}
		}
		
		
		
		// Player guess validation
		if ( ev.getSource( ) == game.getBtnValidate2( ) ) {
			
			game.getJTextInfo( ).setText( "Hey, it's the turn of the computer! "
					+ "Please clic on the 'Computer try' button down there. " );
			
			// puts the combination that the player chose with the selector in a new Combi
			game.setCombiPlayerTry( new Combi( game.myCombiTry( ) ) );
			
			// displays the combination of player on its line
			game.displayCombiComputer( game.getCombiPlayerTry( ) );
			
			game.getCombiPlayerTry( ).displayInfo( game , game.getCombiComputer( ) );
			
			game.getCombiPlayerTry( ).testCombi( game.getCombiComputer( ) );
			
			game.displayScoreComputer( game.getCombiPlayerTry().getScore( )  );
			
			game.addTryComputer( );

			// actions on buttons
			// just after the player round, disable his buttons
			game.getBtnValidate2( ).setEnabled( false );
			
			game.activSelector( game.getSelector2( ) , false );
			
			// just after the player round, if computer didn't win, then activate computer buttons
			if( ! game.isComputerWin( ) ) {
				game.getBtnValidate( ).setEnabled( true );
			}
		}	
	}
	
	
	/**
	 * Displays the secret combination of the computer
	 * 
	 * @param game
	 * 			The JPanel where the game is displayed
	 */
	public void showCombiComputer( G2Duel game ) {
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			
			Color c = game.getSelector( )[ i ].getColor()[ game.getCombiComputer( ).getCombi( )[ i ] ];
			game.setSpotSolution2( i , game.new Piece( ) );
			game.getSpotSolution2()[ i ].setBackground( game.getColorBg( ) );
			game.getSpotSolution2()[ i ].setPreferredSize( new Dimension( 36 , 36 ) );
			game.getSpotSolution2()[ i ].setC( c );
			game.getPanSolution2().add( game.getSpotSolution2()[ i ] );
		}
		game.getGrid2().add( game.getPanSolution2() , 0 );
		game.getPanSolution2().add( game.getPanVirtual2() );
	}
}
