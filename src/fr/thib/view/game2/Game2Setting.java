package fr.thib.view.game2;

import fr.thib.controller.game2.G2SetListener;
import fr.thib.model.Config;
import fr.thib.view.GameSetting;
import fr.thib.view.MainFrame;

public class Game2Setting extends GameSetting {

	private static final long serialVersionUID = -3186347437274149363L;

	// the description of each mod
	private static String[] strJlabMod = {
			"<html>In Challenger mode, you are the codebreaker. "
			+ "<br>You have to find out the secret combination "
			+ "that the artificial intelligence has created.",
			
			"<html>In Defender mode, you are the codemaker. "
			+ "<br>The artificial intelligence will try to find out your combination. ",
			
			"<html> In Duel mode, you face the artificial intelligence. "
			+ "<br>Each of you will try to find out the secret combination of the other."}; 

	// the descrption of each parameter of each difficulty level
	private static String[] strJlabDif = { "" , "" , "" , "" };
	
	private static String gameTitle = "MASTERMIND"; 
	
	// the main frame in which the application is displayed
	private MainFrame parent;
	
	
	
	//----------CONSTRUCTOR----------
	
	
	/**
	 * Creates the JPanel in which the player chooses a game mode and a difficulty level
	 * 
	 * @param parent
	 * 			The main frame in which the application is displayed
	 */
	public Game2Setting( MainFrame parent ) {
		
		super( strJlabMod, strJlabDif , gameTitle );
		
		parent.setSize( 800 , 600 );
		
		this.parent = parent;
		
		for ( int i = 0; i < 4; i++ ) {
			this.getJlabDiff( i ).setText( Config.getColorProp( i ) + " colors, " 
											+ Config.getPieceProp( i ) + " squares, "
											+ Config.getTryG2Prop( i ) + " tries" );
		}
		
		for ( int i = 0; i < getRadBtnMode().length; i++ ) {
			getRadBtnMode(i).addActionListener( new G2SetListener( this ) );
		}
		
		getBtnStart().addActionListener( new G2SetListener( this ) );
	}

	
	//----------GETTER----------
	
	public MainFrame getMainFrame( ) {
		return parent;
	}
}

