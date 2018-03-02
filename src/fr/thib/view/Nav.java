package fr.thib.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.thib.controller.NavListener;

public class Nav extends JPanel{

	private static final long serialVersionUID = -1124204453651096560L;
	
	// a table that contains each button of the menu
	private JButton[] navBtn;
	
	// the text of each button
	private String[] btnTxt = {
			"<html>NEW<br> GAME<html>" , 
			"<html>RULES <br>& MODES<html>" , 
			"<html>BACK TO<br> MAIN MENU<html>" , 
			"QUIT" };

	// the JFrame in which the entire applicaiton is displayed
	private MainFrame parent;
	
	
	//----------CONSTRUCTOR----------
	
	
	/**
	 * Creates a menu that player uses to:
	 * - start a new game
	 * - display the rules and scenario of each mode for the current game 
	 * - acces to the main menu to chose the game
	 * - quit the application
	 * 			
	 * @param parent
	 * 			The JFrame in which the entire applicaiton is displayed
	 */
	public Nav( MainFrame parent ) {
		
		this.parent = parent;

		setLayout( new GridLayout( 4 , 1 ) );
		setBackground( Color.WHITE );
		setSize( new Dimension( 105 , 600 ) );
		
		navBtn = new JButton[4];
		
		for ( int i = 0; i < navBtn.length; i++ ) {
			navBtn[i] = new JButton( btnTxt[i] );
			navBtn[i].setFont( new Font( "Berlin Sans FB", Font.BOLD, 14 ) );
			navBtn[i].setBackground( new Color( 255	, 190 , 28 ) );
			navBtn[i].setForeground( Color.WHITE );
			setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
			navBtn[i].setHorizontalAlignment( JButton.CENTER );
			navBtn[i].addActionListener( new NavListener( this , parent ) );
			add(navBtn[i]);
		}
		setVisible( true );
	}

	
	//----------GETTERS----------
	
	public JButton getNavBtn(int i) {
		return navBtn[i];
	}

	public MainFrame getMainFrame() {
		return parent;
	}
}
