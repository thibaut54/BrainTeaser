package fr.thib.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public abstract class GameSetting extends JPanel {

	private static final long serialVersionUID = 1L;

	// panMode: contains the group of JRadioButton to select the game mode
	// panDiff: contains the group of JRadioButton to select the difficulty
	private JPanel panMode , panDiff;

	// title: displays the title of the current game
	// jlabMode: displays the description of the selected mode
	private JLabel title , jlabMode;

	// the JLabels for each difficulty mode
	private JLabel [ ] jlabDiff;

	// the JRadioButtons for each game mode
	private JRadioButton [ ] radBtnMode;

	// the JRadioButtons for each difficulty
	private JRadioButton [ ] radBtnDiff;

	private ButtonGroup gpBtnMode , gpBtnDiff;
	private JButton btnStart;

	// the text for each game mode
	private String [ ] strMode = { "Challenger" , "Defender" , "Duel" };

	// the text for each difficulty
	private String [ ] strDiff = { "Easy" , "Normal" , "Hard" , "Insane" };

	// contains the text for each game mode
	private String [ ] strJlabMode;

	// contains the text for each difficulty
	private String [ ] strJlabDiff;

	// contains the layout use for this view
	private GridBagConstraints [ ] ctrMode , ctrDiff , ctrPan;
	
	@SuppressWarnings( "unused" )
	private String gameTitle;

	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates the view where player chose, for the current game, 
	 * the game mode and the difficulty level that he wants
	 * 
	 * @param strJlabMode
	 * 				The text for each game mode
	 * @param strJlabDiff
	 * 				The text for each difficulty level	
	 * @param gameTitle
	 * 				The title of the current game
	 * 
	 * @see GameSetting#setConstraintsMainPanel
	 * @see GameSetting#setConstraintsModPanel
	 * @see GameSetting#setConstraintsDifPanel
	 */
	public GameSetting(  String[] strJlabMode , String[] strJlabDiff , String gameTitle ) {

		this.strJlabMode = strJlabMode;
		this.strJlabDiff = strJlabDiff;
		this.gameTitle = gameTitle;

		// JPanel setting
		setLayout( new GridBagLayout() );
		setBackground( Color.BLACK );
		
		// GridBagConstraints
		setConstraintsMainPanel();
		
		setConstraintsModPanel();
		
		setConstraintsDifPanel();

		// Creates JLabel for title of the current game
		title = new JLabel( gameTitle );
		title.setForeground( Color.WHITE );
		title.setHorizontalAlignment( JLabel.CENTER );
		title.setFont( new Font( "Berlin Sans FB", Font.CENTER_BASELINE, 20 ) );
		
		add( title , ctrPan[0] );

		// Creates and fill the JPanel for choosing the mod
		fillPanelMode();
		
		// Creates and fill the JPanel for choosing the difficulty
		fillPanelDiff();
		
		add( panMode , ctrPan[1] );
		add( panDiff , ctrPan[2] );

		// Creates the start button
		btnStart = new JButton( "START" );

		add( btnStart , ctrPan[3] );

		setVisible( true );
	}
	
	
	//----------METHODS----------
	
	/**
	 * Sets the layout of the main JPanel 
	 */
	public void setConstraintsMainPanel() {
		ctrPan = new GridBagConstraints[4];
		
		int [ ] xPan = { 0 , 0 , 0 , 0 }; // gridX
		int [ ] yPan = { 0 , 1 , 2 , 3 }; // gridY
		int [ ] largPan = { 1 , 1 , 1 , 1 }; // gridwidth : nbre colonne occupée
		int [ ] pxPan = { 1 , 1 , 1 , 1 }; // weightX : poids X
		int [ ] pyPan = { 3 , 7 , 7 , 5 }; // weightY : poids Y
		
		for ( int i = 0; i < ctrPan.length; i++ ) {
			ctrPan [ i ] = new GridBagConstraints( );
			ctrPan [ i ].fill = GridBagConstraints.BOTH;
			ctrPan [ i ].gridx = xPan [ i ];
			ctrPan [ i ].gridy = yPan [ i ];
			ctrPan [ i ].gridwidth = largPan [ i ];
			ctrPan [ i ].weightx = pxPan [ i ];
			ctrPan [ i ].weighty = pyPan [ i ];
		}
	}
	
	/**
	 * Sets the layout of the JPanel in which the game modes are displayed
	 */
	public void setConstraintsModPanel() {
		ctrMode = new GridBagConstraints[4];
		
		int xMode[] = { 0 , 0 , 0 , 3 }; // gridX
		int largMode[] = { 1 , 1 , 1 , 3 }; // gridwidth
		int hautMode[] = { 1 , 1 , 1 , 3 }; // gridheight
		int pxMode[] = { 1 , 1 , 1 , 3 }; // weightX
		int pyMode[] = { 1 , 1 , 1 , 3 }; // weightY

		for ( int i = 0; i < ctrMode.length; i++ ) {
			ctrMode [ i ] = new GridBagConstraints( );
			ctrMode [ i ].fill = GridBagConstraints.HORIZONTAL;
			ctrMode [ i ].gridx = xMode [ i ];
			ctrMode [ i ].gridwidth = largMode [ i ];
			ctrMode [ i ].gridheight = hautMode [ i ];
			ctrMode [ i ].weightx = pxMode [ i ];
			ctrMode [ i ].weighty = pyMode [ i ];
		}
	}
	
	/**
	 * Sets the layout of the JPanel in which the difficulty levels are displayed
	 */
	public void setConstraintsDifPanel() {
		ctrDiff = new GridBagConstraints[8];
		
		int xDiff[] = { 0 , 0 , 0 , 0 , 50 , 50 , 50 , 50 }; // gridX
		int largDiff[] = { 1 , 1 , 1 , 1 , 3 , 3 , 3 , 3 }; // gridwidth
		int hautDiff[] = { 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 }; // gridheight
		int pxDiff[] = { 1 , 1 , 1 , 1 , 3 , 3 , 3 , 3 }; // weightX
		int pyDiff[] = { 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 }; // weightY

		for ( int i = 0; i < ctrDiff.length; i++ ) {
			ctrDiff [ i ] = new GridBagConstraints( );
			ctrDiff [ i ].fill = GridBagConstraints.HORIZONTAL;
			ctrDiff [ i ].gridx = xDiff [ i ];
			ctrDiff [ i ].gridwidth = largDiff [ i ];
			ctrDiff [ i ].gridheight = hautDiff [ i ];
			ctrDiff [ i ].weightx = pxDiff [ i ];
			ctrDiff [ i ].weighty = pyDiff [ i ];
		}
	}
	
	/**
	 * Fills the JPanel in which the game modes are displayed
	 */
	public void fillPanelMode() {
		
		panMode = new JPanel( new GridBagLayout() );
		panMode.setBackground( Color.WHITE );
		panMode.setBorder( BorderFactory.createTitledBorder( "GAME MODE" ) );
		radBtnMode = new JRadioButton[ 3 ];
		gpBtnMode = new ButtonGroup();
		
		for ( int i = 0; i < radBtnMode.length; i++ ) {
			radBtnMode[ i ] = new JRadioButton( strMode[ i ] );
			radBtnMode[ i ].setBackground( Color.white );
			gpBtnMode.add( radBtnMode[ i ] );
			panMode.add( radBtnMode[ i ] , ctrMode[ i ] );
		}
		
		// Challenger mode by default
		radBtnMode [ 0 ].setSelected( true );
		jlabMode = new JLabel( strJlabMode [ 0 ] );
		panMode.add( jlabMode , ctrMode [ 3 ] );
	}
	
	/**
	 * Fills the JPanel in which the difficulty levels are displayed
	 */
	public void fillPanelDiff() {
		
		panDiff = new JPanel( new GridBagLayout() );
		panDiff.setBackground( Color.white );
		panDiff.setBorder( BorderFactory.createTitledBorder( "DIFFICULTY LEVEL" ) );
		gpBtnDiff = new ButtonGroup();
		radBtnDiff = new JRadioButton[ 4 ];
		
		for ( int i = 0; i < radBtnDiff.length; i++ ) {
			radBtnDiff[ i ] = new JRadioButton( strDiff[ i ] );
			radBtnDiff[ i ].setBackground( Color.white );
			gpBtnDiff.add( radBtnDiff[ i ] );
			panDiff.add( radBtnDiff[ i ] , ctrDiff[ i ] );
		}
		
		// Normal level by default
		radBtnDiff [ 1 ].setSelected( true );
		// Creates a tab of JLabel that display each difficulty level
		jlabDiff = new JLabel [ 4 ];
		for ( int i = 0 , j = 4; i < jlabDiff.length; i++ , j++ ) {
			jlabDiff [ i ] = new JLabel( strJlabDiff [ i ] );
			panDiff.add( jlabDiff [ i ] , ctrDiff [ j ] );
		}
	}
	
	
	//----------GETTERS----------
	
	public JRadioButton getRadBtnMode( int i ) {
		return radBtnMode [ i ];
	}

	public JRadioButton[] getRadBtnMode() {
		return radBtnMode;
	}

	public JRadioButton getRadBtnDiff( int i ) {
		return radBtnDiff[ i ];
	}

	public JRadioButton[] getRadBtnDiff() {
		return radBtnDiff;
	}

	public JLabel getJlabMode() {
		return jlabMode;
	}

	public String getStrJlabMode( int i ) {
		return strJlabMode[ i ];
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JLabel getJlabDiff( int i ) {
		return jlabDiff[ i ];
	}
}
