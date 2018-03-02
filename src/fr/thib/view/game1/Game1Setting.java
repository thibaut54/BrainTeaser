package fr.thib.view.game1;

import fr.thib.controller.game1.G1SetListener;
import fr.thib.model.Config;
import fr.thib.view.GameSetting;
import fr.thib.view.MainFrame;

public class Game1Setting extends GameSetting {

	private static final long serialVersionUID = 3725545634376621339L;

	// the description of each mod
	private static String[] strMode = { 
			"<html>In Challenger mode, you have to crack the access code of "
			+ "<br>the nuclear weapons security system of a dangerous country in order to "
			+ "<br>neutralize it. If you fail... you better run. We ALL better run!!!" ,
			
	        "<html>In Defender mode, you must create an UN-BREA-KA-BLE code so that "
	        + "<br>any hostile hacker can take controle of your country's nuclear weapons."
	        + "<br>If you fail... you better run. Because he will turn over the weapons against you!" ,
	        
	        "<html>In Duel mode, you are fighting against another hostile hacker! "
	        + "<br>But he doesn't want only neutralize your country's nuclear weapons. "
	        + "<br>He wants to trigger it against yourself! You must create a solid security "
	        + "<br>code that he won't find out, and you must break his security code "
            + "<br>in order to neutralize his system and to locate hime, so that the authorities arrest him." };
	
	// the descrption of each parameter of each difficulty level
	private static String[] strJlabDiff = { "" , "" , "" , "" };

	private static String gameTitle = "CRACK THE CODE"; 
	
	// the main frame in which the application is displayed
	private MainFrame parent;
	
	
	
	//----------CONSTRUCTOR----------
	

	/**
	 * Creates the JPanel in which the player chooses a game mode and a difficulty level
	 * 
	 * @param parent
	 * 			The main frame in which the application is displayed
	 */
	public Game1Setting( MainFrame parent ) {
		
		super( strMode , strJlabDiff , gameTitle );
		
		this.parent = parent;

		for ( int i = 0; i < 4; i++ ) {
			this.getJlabDiff( i ).setText( Config.getDigitProp( i ) + " numbers, " 
					+ Config.getTryProp( i ) + " tries" );
		}
		
		for ( int i = 0; i < getRadBtnMode().length; i++ ) {
			getRadBtnMode(i).addActionListener( new G1SetListener( this , parent ) );
		}
		
		getBtnStart().addActionListener( new G1SetListener( this , parent ) );
		
	}

	
	//----------GETTERS----------

	public MainFrame getMainFrame() {
		return parent;
	}
}
