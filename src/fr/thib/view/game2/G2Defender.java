package fr.thib.view.game2;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import fr.thib.controller.game2.G2DefListener;
import fr.thib.model.game2.Combi;
import fr.thib.model.game2.G2Interface;
import fr.thib.view.MainFrame;

public class G2Defender extends Game2 implements G2Interface{

	private static final long serialVersionUID = -3544829439362963391L;
	
	private Combi combiPlayer = null;

	private Combi combiComputerTry = null;


	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates and displays the game 2 in defender mode
	 * 
	 * @param mainFrame
	 * 			The main frame in which the application is diplayed
	 */
	public G2Defender( MainFrame mainFrame ) {

		super( );
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		
		getJTextInfo( ).setText( 
				"Welcome dear player ! Please chose a code using the selector down there. "
				+ "But you need to know that I WILL break it!" );
		
		add( getGrid() , BorderLayout.CENTER );
		
		mainFrame.setSize( 800 , 600 );
		
		combiPlayer = new Combi(  );
		
		getBtnValidate( ).setText( "<html>REGISTER" );
		
		getBtnValidate().addActionListener( new G2DefListener( this ) );
	}
	
	
	
	//----------GETTER----------
	
	public Combi getCombiPlayer( ) {
		return combiPlayer;
	}

	public Combi getCombiComputerTry( ) {
		return combiComputerTry;
	}
	
	public JPanel [ ] getResult() {
		return super.getResult( );
	}

	
	
	//----------SETTER----------
	
	public void setCombiPlayer( Combi combiPlayer ) {
		this.combiPlayer = combiPlayer;
	}

	@Override
	public void setCombiComputerTry( Combi combi ) {
		this.combiComputerTry = combi;
	}

	@Override
	public Combi getCombiComputer( ) {
		return null; 
	}

	@Override
	public void setCombiComputer( Combi combi ) {

	}

	@Override
	public void setPlayerWin( boolean b ) {

	}
}
