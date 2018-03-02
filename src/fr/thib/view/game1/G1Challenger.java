package fr.thib.view.game1;

import javax.swing.JLabel;

import fr.thib.controller.game1.G1ChallListener;
import fr.thib.model.Config;
import fr.thib.model.game1.Code;
import fr.thib.model.game1.G1Interface;

public class G1Challenger extends Game1 implements G1Interface{

	private static final long serialVersionUID = 3740127501102354640L;

	private Code codeComputer;
	
	private String strDifferentCode = "<html><span>You didn't break the code. "
				+ "<br>But try again! You have X tries remaing!";
	
	private String strEqualsCode = "<html>YOU WON! You broke the code! Thank you! "
			+ "<br>There will be no war because you neutralize a dangerous "
			+ "installation of nuclear weapons !";
	
	private String strNoTry = "<html><span>YOU LOST! What a shame! "
			+ "The threat still exists..."
			+ "<br>Oh my god! They detected your attack and just "
			+ "launched their missiles on your country!!!... It will be a war! "
			+ "By the way, the code was X<span>";
	
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates and displays the game Challenger mode
	 */
	public G1Challenger( ) {
		
		super( );
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		
		getResult( ).setText(  "<html>START CRACKING THE CODE! "
				+ "<br>Use the selector to choose a code and press the 'Hack' Button"
				+ "<br>You have " + Config.getNbTry( ) + " tries!" );
		
		// randomly generates a secret code  
		codeComputer = new Code();
		
		strNoTry = strNoTry.replace( "X" , codeComputer.toString( ) );
		
		if ( Config.isDev( ) ) {
			getSolution( ).setText( "Congrats, you said the dev word! The code is "
				+ codeComputer.toString( ) + "." );
			//Removes the code to avoid that it's diplayed twice
			strNoTry = strNoTry.replace( "The code was " + codeComputer.toString( ) , "" );
		}
		
		getBtnValidate().addActionListener( new G1ChallListener( this ) );
		
	}

	
	

	//----------GETTERS----------
	
	public Code getCodeComputer( ) {
		return codeComputer;
	}
	
	public String getStrDifferentCode( ) {
		return strDifferentCode;
	}

	public String getStrEqualsCode( ) {
		return strEqualsCode;
	}

	public String getStrNoTry( ) {
		return strNoTry;
	}
	
	@Override
	public Code getCodePlayer( ) {
		return null;
	}

	@Override
	public Code getCodeComputerTry( ) {
		return null;
	}

	@Override
	public JLabel [ ] getJlabCodeComputer( ) {
		return null;
	}

	@Override
	public boolean isPlayerWin( ) {
		return false;
	}

	
	//----------SETTERS----------
	
	@Override
	public void setCodePlayer( Code code ) {

	}

	@Override
	public void setCodeComputer( Code code ) {

	}

	@Override
	public JLabel [ ] getIndicatorField2( ) {
		return null;
	}
}
