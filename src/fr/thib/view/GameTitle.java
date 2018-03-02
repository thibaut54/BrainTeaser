package fr.thib.view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameTitle extends JLabel{

	private static final long serialVersionUID = 1573559190854092054L;
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates a screen title for the game
	 * 
	 * @param img
	 * 			The image displayed in the JPanel
	 */
	public GameTitle( ImageIcon img ) {
		
		super( img );

		setPreferredSize( new Dimension( 660 , 605 ) );
	}
}
