package fr.thib.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = -8637374747983686548L;
	
	// panNav: will contain the game menu
	// panGame: will contain the game
	private JPanel panNav , panGame;

	// index: the main menu where player chose the game he wants to play
	private Index index;

	// use to know if game1 or game2 has been started
	private boolean Game1Go;
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates the main frame, eg. the window in which the entire application 
	 * is displayed
	 * 
	 * @see Index#Index
	 */
	public MainFrame() {
		
		//inititiates the main menu
		index = new Index( this );
				
		// Main Window settings		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		setResizable( false );
		
		panNav = new JPanel( new GridLayout( 1 , 1 ) );
		panGame = new JPanel( new GridLayout( 1 , 1 ) );
		panGame.setBackground( Color.BLACK );
		
		getContentPane().add( index );
		setVisible( true );
	}

	
	//----------GETTERS----------
	
	public Index getIndex() {
		return index;
	}

	public JPanel getPanNav() {
		return panNav;
	}
	
	public boolean isGame1Go() {
		return Game1Go;
	}

	public JPanel getPanGame() {
		return panGame;
	}

	
	//----------SETTERS----------
	
	public void setIndex( Index index ) {
		this.index = index;
	}

	
	public void setGame1Go( boolean game1Go ) {
		Game1Go = game1Go;
	}
}
