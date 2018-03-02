package fr.thib.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.thib.controller.IndexListener;
import fr.thib.model.Config;

public class Index extends JPanel {

	private static final long serialVersionUID = 46165525726262480L;

	// buttons to start game1 or game2
	private JButton btnGame1 , btnGame2;

	// displays the title of each game
	private JLabel nameGame1 , nameGame2;

	// the title of each game
	private final String TITLEGAME1 = "CRACK THE CODE";
	private final String TITLEGAME2 = "MASTERMIND";
	
	@SuppressWarnings( "unused" )
	private MainFrame parent;

	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates the main menu where player chose the game he wants to play
	 * 
	 * @param parent
	 * 			The JFrame in which the entire applicaiton is displayed
	 * 
	 * @see Config#isDev
	 */
	public Index( MainFrame parent ) {

		this.parent = parent;
		
		parent.setSize( 830 , 450 );
		
		// Indicates in the title of the frame if the application
		// is started in Developer mode  
		if( ! Config.isDev( ) ) {
			parent.setTitle( "CHOOSE A GAME!" );
		}
		else {
			parent.setTitle( "DEVELOPER MODE - CHOOSE A GAME" );
		}
		
		// JPanels and Layout settings
		this.setLayout( new FlowLayout() );
		this.setBackground( Color.BLACK );

		// Creation of buttons with icons
		btnGame1 = new JButton( new ImageIcon( "./ressources/imageBtn1_400_400.jpg" ) );
		btnGame1.setLayout( new FlowLayout() );
		btnGame1.setPreferredSize( new Dimension( 400, 400 ) );
		nameGame1 = new JLabel( TITLEGAME1 );
		nameGame1.setFont( new Font( "Berlin Sans FB", Font.BOLD, 40 ) );
		nameGame1.setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
		nameGame1.setForeground( Color.WHITE );
		btnGame1.add( nameGame1 );
		this.add( btnGame1 );

		btnGame2 = new JButton( new ImageIcon( "./ressources/imageBtn2_400_400.jpg" ) );
		btnGame2.setLayout( new FlowLayout() );
		btnGame2.setPreferredSize( new Dimension( 400, 400 ) );
		nameGame2 = new JLabel( TITLEGAME2 );
		nameGame2.setFont( new Font( "Berlin Sans FB", Font.BOLD, 40 ) );
		nameGame2.setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
		nameGame2.setForeground( Color.WHITE );
		btnGame2.add( nameGame2 );
		this.add( btnGame2 );

		// Adds listener to buttons
		btnGame1.addActionListener( new IndexListener( this , parent ) );
		btnGame2.addActionListener( new IndexListener( this , parent ) );

		setVisible( true );

	}

	
	//----------GETTERS----------
	
	public JButton getBtnGame1() {
		return btnGame1;
	}

	public JButton getBtnGame2() {
		return btnGame2;
	}

	public String getTITLEGAME1() {
		return TITLEGAME1;
	}

	public String getTITLEGAME2() {
		return TITLEGAME2;
	}
	
}
