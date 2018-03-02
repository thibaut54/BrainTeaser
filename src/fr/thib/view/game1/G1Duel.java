package fr.thib.view.game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.thib.controller.game1.G1DuelListener;
import fr.thib.model.Config;
import fr.thib.model.game1.Code;
import fr.thib.model.game1.G1Interface;

public class G1Duel extends Game1 implements G1Interface{

	private static final long serialVersionUID = -8917892106054719366L;

	// the selectors for player and for computer
	private SelectorG1 [ ] selectorP1 , selectorP2;
	
	// contains the text indicators + / - / = for computer
	private JLabel [ ] indicatorField2;
	
	// panMainSelect2: JPanel in which selector for computer and indicator are displayed
	// panSelect2: JPanel in which the selector for computer is displayed
	// panIndic2: JPanel in which the result + / - / = is deplayed for computer
	private JPanel panMainSelect2 , panSelect2 , panIndic2;
	
	// buttons to validate player and computer guess
	private JButton btnPlayer , btnComputer;
	
	// contains the code of computer as text
	private JLabel [ ] jlabCodeComputer;
	
	
	private Code codeComputer , codePlayer , codeComputerTry , codePlayerTry;
	private int nbTryComputer = 0;
	
	// indicates if player or / and computer won
	private boolean playerWin, computerWin;

	private String strDifferentCodePlayer = "<html>You didn't break the code. "
			+ "You have X tries remaing! "
			+ "<br>But the hacker you're fighting will now try something. "
			+ "<br>It's his turn! Press 'Adverse hacker attack' button!";

	private String strEqualsCodePlayer = "<html>YOU WON! You broke the code! Thank you!<br> "
			+ "There will be no war because you neutralize the hacker! ";

	private String strNoTryPlayer = "<html>YOU LOST! What a pity! The code was X. <br>"
			+ "The hacker is still trying to destroy us! ";
	
	private String strDifferentCodeComputer = "<html>Your code is hard to break! <br> "
			+ "But the hacker still has X tries left! Anyway, it's your turn now!";
	
	private String strEqualsCodeComputer = "<html>You lost. We all lost! What a shame! "
			+ "<br>Nuclear missiles have been launched against ourselves!";

	private String strNoTryComputer = "YOU WON! The hacker didn't break your code!";
	
	private String strLostBoth = "<html>YOU AND THE HACKER BOTH FAIL!<br> "
			+ "Prepare yourself for another attack! Our enemy won't stop that easily... ";

	private String strWonBoth = "<html>YOU AND THE HACKER BOTH BROKE EACH OTHER CODE! "
			+ "<br>There will be no war!"
			+ "<br>But our security system and the hacker's one are both very vulnerable now!";
	
	
	
	//----------CONSTRUCTOR----------

	
	
	/**
	 * Creates and displays the game 2 in duel mode
	 */
	public G1Duel( ) {

		super( );
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );

		codeComputer = new Code( );
		
		strNoTryPlayer = strNoTryPlayer.replace( "X" , codeComputer.toString( ) );
		
		if ( Config.isDev( ) ) {
			strNoTryPlayer = strNoTryPlayer.replace( 
					"The code was " + codeComputer.toString( ) +"." , "");
		}
		
		playerWin = false;
		computerWin = false;

		remove( getBtnValidate( ) );

		panMainSelect2 = new JPanel( new GridLayout( 3 , 1 ) );

		panSelect2 = new JPanel( new GridLayout( 1 , 1 ) );
		panSelect2.setBackground( Color.WHITE );

		panIndic2 = new JPanel( new GridLayout( 1 , 1 ) );
		panIndic2.setBackground( Color.WHITE );

		int nbDigit = Config.getNbDigit( );

		selectorP2 = new SelectorG1 [ nbDigit ];

		indicatorField2 = new JLabel [ nbDigit ];

		getResult( ).setText( "<html>START CRACKING THE CODE with the selector and press 'Hack' button! "
				+ "<br>You have " + Config.getNbTry( ) + " tries!" );

		jlabCodeComputer = new JLabel [ Config.getNbDigit( ) ];

		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			jlabCodeComputer [ i ] = new JLabel( "-" );
			jlabCodeComputer [ i ].setFont( new Font( "Calibri" , Font.CENTER_BASELINE , 60 ) );
			jlabCodeComputer [ i ].setHorizontalAlignment( JTextField.CENTER );
			jlabCodeComputer [ i ].setBorder( BorderFactory.createLineBorder( Color.black ) );
			panSelect2.add( jlabCodeComputer [ i ] );
		}

		panMainSelect2.add( panSelect2 );

		initIndicator( panMainSelect2 , panIndic2 , indicatorField2 );

		btnPlayer = new JButton( "Hack" );
		btnComputer = new JButton( "Adverse hacker attack" );

		btnPlayer.addActionListener( new G1DuelListener( this ) );
		btnComputer.addActionListener( new G1DuelListener( this ) );

		getPanMainSelect( ).setLayout( new GridLayout( 3 , 1 ) );
		getPanMainSelect( ).add( btnPlayer );

		panMainSelect2.add( btnComputer );

		add( panMainSelect2 );

		btnComputer.setEnabled( false );

		setComponentZOrder( getPanMainSelect( ) , 0 );

	}

	

	//----------METHODS----------

	
	/**
	 * 
	 * @param panMainSelect
	 * @param panIndic
	 * @param indicator
	 */
	public void initIndicator( JPanel panMainSelect , JPanel panIndic , JLabel [ ] indicator ) {

		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			indicator [ i ] = new JLabel(  );
			indicator [ i ].setFont( new Font( "Calibri" , Font.CENTER_BASELINE , 60 ) );
			indicator [ i ].setHorizontalAlignment( JTextField.CENTER );
			indicator [ i ].setText( "?" );
			indicator [ i ].setBorder( BorderFactory.createLineBorder( Color.black ) );
			panIndic.add( indicator [ i ] );
		}
		panMainSelect.add( panIndic );
	}


	
	/**
	 * Disables or enables buttons in selector
	 * 
	 * @param selector
	 * 			The selector to act on
	 * @param onOff
	 * 
	 * @see Config#getNbDigit
	 */
	public void activSelector( SelectorG1 [ ] selector , boolean onOff ) {

		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			selector [ i ].getMinus( ).setEnabled( onOff );
			selector [ i ].getPlus( ).setEnabled( onOff );
		}
	}
	
	
	
	/**
	 * Displays a message in case of draw
	 */
	public void displayIfDraw( ) {
		
		// if both won
		if( playerWin && computerWin ) {
			
			getResult( ).setText( strWonBoth );
		}
		
		// if both lost
		else if ( ! playerWin && ! computerWin && nbTryComputer == Config.getNbTry( ) ) {
			
			getResult( ).setText( strLostBoth );
		}
	}

	

	//----------GETTER----------

	public SelectorG1 [ ] getSelectorP1( ) {
		return selectorP1;
	}

	public SelectorG1 [ ] getSelectorP2( ) {
		return selectorP2;
	}

	public JLabel [ ] getIndicatorField2( ) {
		return indicatorField2;
	}

	public JButton getBtnPlayer( ) {
		return btnPlayer;
	}

	public JButton getBtnComputer( ) {
		return btnComputer;
	}

	public JLabel [ ] getJlabCodeComputer( ) {
		return jlabCodeComputer;
	}

	public Code getCodeComputer( ) {
		return codeComputer;
	}
	
	public Code getCodePlayer( ) {
		return codePlayer;
	}

	public boolean isPlayerWin( ) {
		return playerWin;
	}
	
	public boolean isComputerWin( ) {
		return computerWin;
	}

	public int getNbTryComputer( ) {
		return nbTryComputer;
	}

	public String getStrDifferentCode( ) {
		return strDifferentCodePlayer;
	}
	
	public String getStrEqualsCode( ) {
		return strEqualsCodePlayer;
	}
	
	public String getStrNoTry( ) {
		return strNoTryPlayer;
	}
	
	public String getStrDifferentCodeComputer( ) {
		return strDifferentCodeComputer;
	}
	
	public String getStrEqualsCodeComputer( ) {
		return strEqualsCodeComputer;
	}
	
	public String getStrNoTryComputer( ) {
		return strNoTryComputer;
	}

	public Code getCodeComputerTry( ) {
		return codeComputerTry;
	}

	public Code getCodePlayerTry( ) {
		return codePlayerTry;
	}
	
	

	// ----------SETTER----------
	
	public void addTryComputer() {
		nbTryComputer++;
	}
	
	public void setPlayerWin( boolean playerWin ) {
		this.playerWin = playerWin;
	}

	public void setComputerWin( boolean computerWin ) {
		this.computerWin = computerWin;
	}

	public void setCodePlayer( Code codePlayer ) {
		this.codePlayer = codePlayer;
	}

	public void setCodeComputer( Code codeComputer ) {
		this.codeComputer = codeComputer;
	}

	public void setCodeComputerTry( Code codeComputerTry ) {
		this.codeComputerTry = codeComputerTry;
	}

	public void setCodePlayerTry( Code codePlayerTry ) {
		this.codePlayerTry = codePlayerTry;
	}
}
