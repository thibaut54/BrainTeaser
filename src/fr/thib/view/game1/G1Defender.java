package fr.thib.view.game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.thib.controller.game1.G1DefListener;
import fr.thib.model.Config;
import fr.thib.model.game1.Code;
import fr.thib.model.game1.G1Interface;

public class G1Defender extends Game1 implements G1Interface{

	private static final long serialVersionUID = -1345601468155087250L;
	
	// displays the code that the computer has tried
	private JPanel panCodeComputer;
	private JLabel[] jlabCodeComputer;
	
	private Code codePlayer , codeComputer;
	
	private String strDifferentCode = "<html>Your code is hard to break! "
			+ "<br>But the hacker still has X tries left!";
	
	private String strEqualsCode = "<html>You loose. What a shame! "
			+ "<br>Nuclear missiles have been launched! You better flee! "
			+ "<br>This is the perfect occasion to see how fast is your car! ";

	private String strNoTry = "<html>YOU WON! The hacker didn't break your code! "
			+ "<br>We are all safe thanks to you!";
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates and displays the game 1 deffender mode
	 */
	public G1Defender( ) {
		
		super(  );
		
		getBtnValidate( ).setText( "Register code" );
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		
		getPanMainSelect( ).setLayout( new GridLayout( 3 , 1 ) );
		
		getPanSelect().setBackground( Color.WHITE );

		panCodeComputer = new JPanel( new GridLayout( 1 , 1) );
		
		jlabCodeComputer = new JLabel[ Config.getNbDigit() ];
		
		
		for ( int i = 0 ; i < Config.getNbDigit() ; i++ ) {
		
			jlabCodeComputer[ i ] = new JLabel( "-" );
			jlabCodeComputer[ i ].setFont( new Font( "Calibri", Font.CENTER_BASELINE, 60 ) );
			jlabCodeComputer[ i ].setHorizontalAlignment( JTextField.CENTER );
			jlabCodeComputer[ i ].setBorder( BorderFactory.createLineBorder( Color.black ) );
			panCodeComputer.add( jlabCodeComputer[ i ] );
		
		}
		
		
		panCodeComputer.setBackground( Color.WHITE );
		getPanMainSelect( ).add( panCodeComputer );
		
		if ( Config.isDev( ) ) {
			getSolution( ).setText( "<html>Congrats, you said the dev word! <br>"
				+ "You will see the code when it has been choosen." );
		}
		
		getResult( ).setHorizontalAlignment( JLabel.CENTER );
		getResult().setText( 
				"<html>A hacker is trying to take controle of the nuclear missiles of your country !"
				+ "	<br>Try to stop him by crypting your systeme! "
				+ "<br>CHOOSE A CODE with the selector and press 'Register code' button!" );
		
		getBtnValidate().addActionListener( new G1DefListener( this ) );
		
	}

	
	
	//----------GETTER----------
	
	@Override
	public JLabel[] getJlabCodeComputer() {
		return jlabCodeComputer;
	}

	@Override
	public Code getCodeComputerTry( ) {
		return null;
	}
	
	@Override
	public boolean isPlayerWin( ) {
		return false;
	}

	@Override
	public Code getCodePlayer( ) {
		return codePlayer;
	}

	@Override
	public Code getCodeComputer( ) {
		return codeComputer;
	}

	@Override
	public String getStrDifferentCode( ) {
		return strDifferentCode;
	}
	
	@Override
	public String getStrEqualsCode( ) {
		return strEqualsCode;
	}

	@Override
	public String getStrNoTry( ) {
		return strNoTry;
	}

	
	//----------SETTER----------

	@Override
	public void setCodePlayer( Code codePlayer ) {
		this.codePlayer = codePlayer;
	}
	
	@Override
	public void setCodeComputer( Code codeComputer ) {
		this.codeComputer = codeComputer;
	}



	@Override
	public JLabel [ ] getIndicatorField2( ) {

		return null;
	}	
}
