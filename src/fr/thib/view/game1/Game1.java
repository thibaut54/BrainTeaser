package fr.thib.view.game1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;

public abstract class Game1 extends JPanel {

	private static final long serialVersionUID = -6467940084548809539L;

	static final Logger logger = LogManager.getLogger( );

	// one selector for each piece 
	private SelectorG1 [ ] selector;
	
	// panMainSelect: JPanel in which selector and indicator are displayed
	// panSelect: JPanel in which the selector is displayed
	// panIndic: JPanel in which the result + / - / = is deplayed
	// panResult: JPanel in which then information for player is displayed
	private JPanel panMainSelect , panSelect , panIndic , panResult;
	
	// contains the text indicators + / - / = 
	private JLabel [ ] indicatorField;
	
	// contains the text information for player
	private JLabel result;
	private JLabel solution;
	private JButton btnValidate;
	private int nbTryPlayer = 0;


	
	//----------CONSTRUCTOR----------

	
	/**
	 * The basis of the view for each mode for game1
	 */
	public Game1( ) {

		setLayout( new GridLayout( 3 , 1 ) );

		panMainSelect = new JPanel( new GridLayout( 2 , 1 ) );
		panSelect = new JPanel( new GridLayout( 1 , 1 ) );
		panIndic = new JPanel( new GridLayout( 1 , 1 ) );
		panIndic.setBackground( Color.WHITE );

		initSelector( );

		initIndicator( );

		panResult = new JPanel( );
		panResult.setBackground( Color.BLACK );

		result = new JLabel( );
		result.setForeground( Color.WHITE );
		result.setFont( new Font( "Calibri" , Font.CENTER_BASELINE , 20 ) );
		result.setHorizontalAlignment( JLabel.CENTER );
		
		panResult.setLayout( new BorderLayout( ) );
		panResult.add( result , BorderLayout.CENTER );

		// if the application is in developer mode, the solution is displayed in a JPanel
		if ( Config.isDev( ) ) {
			panResult.setLayout( new GridLayout( 2 , 1 ) );
			solution = new JLabel( "" );
			solution.setForeground( Color.WHITE );
			solution.setFont( new Font( "Calibri" , Font.CENTER_BASELINE , 20 ) );
			solution.setHorizontalAlignment( JLabel.CENTER );
			panResult.add( solution );
		}

		add( panResult );

		btnValidate = new JButton( "Hack" );
		add( btnValidate );
	}


	
	//----------METHODS----------

	
	/**
	 * Initiates the selectors and add it to panSelect
	 * 
	 * @see Config#getNbDigit
	 */
	public void initSelector( ) {

		selector = new SelectorG1 [ Config.getNbDigit( ) ];
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			selector [ i ] = new SelectorG1( );
			panSelect.add( selector [ i ].getPanMain( ) );
		}
		panMainSelect.add( panSelect );
	}


	
	/**
	 * Initiates the indicators
	 * 
	 * @see Config#getNbDigit
	 */
	public void initIndicator( ) {

		indicatorField = new JLabel [ Config.getNbDigit( ) ];
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			indicatorField [ i ] = new JLabel( );
			indicatorField [ i ].setFont( new Font( "Calibri" , Font.CENTER_BASELINE , 60 ) );
			indicatorField [ i ].setHorizontalAlignment( JTextField.CENTER );
			indicatorField [ i ].setText( "?" );
			indicatorField [ i ].setBorder( BorderFactory.createLineBorder( Color.black ) );
			panIndic.add( indicatorField [ i ] );

		}
		panMainSelect.add( panIndic );
		add( panMainSelect );
	}

	
	
	/**
	 * Enable or Disable buttons in selector
	 * @param onOff
	 * 			Says if selector must be on or off
	 */
	public void activSelector( boolean onOff ) {

		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			this.getSelector( ) [ i ].getPlus( ).setEnabled( onOff );
			this.getSelector( ) [ i ].getMinus( ).setEnabled( onOff );
			this.getSelector( )[ i ].setMouseActiv( onOff );
		}
	}
	
	
	
	/**
	 * Returns numbers of the selector in an int[ ]
	 * 
	 * @return numbers of the selector in an int[ ]
	 * 
	 * @see Config#getNbDigit
	 */
	public int [ ] myNumber( ) {

		int [] number = new int [ Config.getNbDigit( ) ];

		for ( int j = 0; j < Config.getNbDigit( ); j++ ) {

			String str = getSelector( ) [ j ].getStartNb( );
			number [ j ] = Integer.valueOf( str );
		}
		return number;
	}
	

	
	//----------GETTERS----------

	public SelectorG1 [ ] getSelector( ) {
		return selector;
	}
	
	public JPanel getPanMainSelect() {
		return panMainSelect;
	}

	public JLabel getResult( ) {
		return result;
	}

	public JButton getBtnValidate( ) {
		return btnValidate;
	}

	public JLabel [ ] getIndicatorField( ) {
		return indicatorField;
	}

	public JPanel getPanSelect( ) {
		return panSelect;
	}

	public JLabel getSolution( ) {
		return solution;
	}
	
	public int getNbTryPlayer( ) {
		return nbTryPlayer;
	}

	
	//----------SETTERS----------
	
	public void setNbTryPlayer( int nbTryPlayer ) {
		this.nbTryPlayer = nbTryPlayer;
	}
	
	public void addTryPlayer() {
		nbTryPlayer++;
	}
}
