package fr.thib.view.game2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.thib.controller.game2.G2DuelListener;
import fr.thib.model.Config;
import fr.thib.model.game2.Combi;
import fr.thib.model.game2.G2Interface;
import fr.thib.view.MainFrame;

public class G2Duel extends Game2 implements G2Interface{

	private static final long serialVersionUID = -40727817694605131L;
	
	// line2: contains each piece of the combination
	private JPanel[] line2;
	
	// a table of X pieces, with X the number of piece defined in the difficulty level
	private Piece[] piece2;
	
	// result2: contains the score of each combination
	private JPanel[] result2;
	
	// a table of selector for each piece 
	private SelectorG2[] selector2;
	
	private JButton btnValidate2;

	// grid2: JPanel in which the combinations that player or IA tries are displayed
	private JPanel grid2 , gridMain , panSolutionMain , panSolution , 
					panSolution2 , panVirtual , panVirtual2;
	
	// to display the solution of the player and the IA secret combination
	private Piece[] spotSolution, spotSolution2;
	private byte nbTryComputer =0;
	
	
	private Combi combiComputer , combiPlayer , combiComputerTry , combiPlayerTry;
	
	
	private boolean playerWin , computerWin;
	
	
	
	//----------CONSTRUCTOR----------
	
	
	/**
	 * Creates and displays the game 2 duel mode
	 * 
	 * @param mainFrame
	 * 			The main frame in which the application is diplayed
	 */
	public G2Duel( MainFrame mainFrame ) {
		
		super();
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		
		playerWin = false;
		computerWin = false;
		
		getJTextInfo( ).setText( "Welcome dear player ! In this mode, you will "
				+ "face the computer. Please first chose your secret combination "
				+ "and try to find out the one of the computer." );
		
		grid2 = new JPanel( new GridLayout( Config.getNbTry( ) , 1 ) );
		gridMain = new JPanel( new GridLayout( 1 , 2 ) );
		
		gridMain.add( getGrid( ) );
		
		// generates the secret combination that player must find out
		combiComputer = new Combi( );
	
		mainFrame.setSize( 1200 , 600 );
		
		selector2 = new SelectorG2[ Config.getNbPiece( ) ];
		
		
		for ( int i = 0; i < selector2.length; i++ ) {
			selector2[i] = new SelectorG2( );
			getPanSelect().add( selector2[ i ].getPanMain( ) );
		}
		
		btnValidate2 = new JButton( "Validate" );
		getPanSelect().add( btnValidate2 );

		
		line2 = new JPanel[ Config.getNbTry( ) ];
		result2 = new JPanel[ Config.getNbTry( ) ];
		piece2 = new Piece[ Config.getNbPiece( ) ];
		
		// Creates 1 line per available try
		for ( int l = 0; l < Config.getNbTry( ); l++ ) {
			
			line2[ l ] = new JPanel( );
			result2[ l ] = new JPanel( );
			result2[ l ].setPreferredSize( new Dimension( 60 , 35 ) );
			result2[ l ].setBackground( getColorBg( ) );
			result2[ l ].setBorder( BorderFactory.createLineBorder( Color.black ) );
		}
		
		
		// For each line < l >
		for ( int l = 0; l < Config.getNbTry( ); l++ ) {
			
			// For each square < s > of the line < l >
			for ( int s = 0; s < Config.getNbPiece( ); s++ ) {
				
				piece2[ s ] = new Piece( );
				piece2[ s ].setPreferredSize( new Dimension( 36 , 36 ) );
				
				line2[ Config.getNbTry( ) - 1 - l ].add( piece2[ s ] );
				
			}
			
			line2[ Config.getNbTry( ) -1 - l ].add( result2 [ Config.getNbTry( ) - 1 - l ] );
			grid2.add( line2[ Config.getNbTry( ) -1 - l ] );
			
		}
		
		gridMain.add( grid2 );
		
		add( gridMain , BorderLayout.CENTER );

		btnValidate2.addActionListener( new G2DuelListener( this ) );
		
		getBtnValidate().setText( "<html>Register <br>combination" );
		getBtnValidate( ).addActionListener( new G2DuelListener( this ) );
		
		btnValidate2.setEnabled( false );
		activSelector( selector2 , false );
		
	
	}
	
	
	
	
	
	//----------METHODS----------

	
	
	/**
	 * Disables or enables buttons in selector
	 * 
	 * @param onOff
	 * 
	 * @see Config#getNbPiece
	 * @see Selector#setMouseActiv
	 */
	public void activSelector( SelectorG2 [ ] selector , boolean onOff ) {

		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			selector[ i ].getPlus( ).setEnabled( onOff );
			selector[ i ].getMinus( ).setEnabled( onOff );
			selector[ i ].setMouseActiv( onOff );
		}
		
	}
	
	
	
	/**
	 * Puts combination corresponding to colors chosen by player into "combiPlayer"
	 * 
	 * @return the combination, as a table of byte, corresponding to the one in the selector
	 * 
	 * @see Config#getNbPiece
	 */
	public byte[] myCombiTry( ) {
		
		byte[ ] combiPlayerTry = new byte[ Config.getNbPiece( ) ];
		
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			combiPlayerTry[i] = this.getSelector2( )[ i ].getIndexColor( );
		}
		
		return combiPlayerTry;
	}
	
	
	
	/**
	 * Displays the combination corresponding to the combination in parameter
	 * 
	 * @param combi
	 * 			The combination to display
	 * 
	 * @see Config#getNbPiece
	 */
	public void displayCombiComputer( Combi combi ) {

		// Fill each spot of the line with the colors chossen by the player
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {

			Color c = getSelector2( ) [ i ].getColor( ) [ combi.getCombi( ) [ i ] ];

			Component comp = getLine2( ) [ nbTryComputer ].getComponent( i );

			( ( Piece ) comp ).setC( c );

			line2[ nbTryComputer ].getComponent( i ).repaint( );
			line2[ nbTryComputer ].getComponent( i ).revalidate( );

		}
	}
	
	
	
	/**
	 * Displays the score of each combination, corresponding to the score in parameter
	 * 
	 * @param score
	 * 			The score resulting of the comparison between two combination
	 * 
	 * @see Config#getNbPiece
	 */
	public void displayScoreComputer( byte [ ] score ) {

		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			// if good color good located
			if ( score [ i ] == 2 ) {
				JPanel panRes = new JPanel( );
				panRes.setPreferredSize( new Dimension( 10 , 10 ) );
				panRes.setBackground( Color.BLACK );
				result2[ nbTryComputer ].add( panRes );
			}
			// if good color wrong located
			if ( score [ i ] == 1 ) {
				JPanel panRes = new JPanel( );
				panRes.setSize( new Dimension( 10 , 10 ) );
				panRes.setBackground( Color.WHITE );
				result2[ nbTryComputer ].add( panRes );
			}

			FlowLayout layout = new FlowLayout( FlowLayout.LEFT , 3 , 2 );

			result2[ nbTryComputer ].setLayout( layout );
		}
	}

	/**
	 * Shows the secret combination of the player when duel mode is started
	 */
	public void showCombi() {
		
		getGrid( ).setLayout( new GridLayout( Config.getNbTry( ) + 1 , 1 ) ) ;
		grid2.setLayout( new GridLayout( Config.getNbTry( ) + 1 , 1 ) ) ;
		
		
		panSolution = new JPanel( );
		panVirtual = new JPanel( );
		panVirtual.setPreferredSize( new Dimension( 60 , 35 ) );
		panVirtual.setBackground( getColorBg() );
		spotSolution = new Piece[ Config.getNbPiece( ) ];

		panSolution2 = new JPanel( );
		panVirtual2 = new JPanel( );
		panVirtual2.setPreferredSize( new Dimension( 60 , 35 ) );
		panVirtual2.setBackground( getColorBg() );
		spotSolution2 = new Piece[ Config.getNbPiece( ) ];
		
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
		
			Color c2 = getSelector( )[ i ].getColor()[ combiPlayer.getCombi( )[ i ] ];
			spotSolution[ i ] = new Piece( );
			spotSolution[ i ].setBackground( getColorBg( ) );
			spotSolution[ i ].setPreferredSize( new Dimension( 36 , 36 ) );
			spotSolution[ i ].setC( c2 );
			panSolution.add( spotSolution[ i ] );
		
		}
		
		panSolution.add( panVirtual );
		panSolution.setSize( new Dimension( getGrid( ).getWidth( )/2 , 40 ) );
		panSolution.setBackground( getColorBg( ) );
		
		
		panSolution2.setSize( new Dimension( getGrid( ).getWidth( )/2 , 40 ) );
		panSolution2.setBackground( getColorBg( ) );
		
		getGrid( ).add( panSolution , 0);
		grid2.add( panSolution2 , 0 );
	}
	
	
	public void addTryComputer( ) {
		nbTryComputer++;
	}
	
	
	//----------GETTERS----------
	
	@Override
	public Combi getCombiComputer( ) {
		return combiComputer;
	}

	@Override
	public Combi getCombiPlayer( ) {
		return combiPlayer;
	}

	public JPanel [ ] getLine2( ) {
		return line2;
	}

	public Piece [ ] getPiece2( ) {
		return piece2;
	}

	public JPanel [ ] getResult2( ) {
		return result2;
	}

	public SelectorG2 [ ] getSelector2( ) {
		return selector2;
	}

	public JButton getBtnValidate2( ) {
		return btnValidate2;
	}

	public Combi getCombiComputerTry( ) {
		return combiComputerTry;
	}

	public Combi getCombiPlayerTry( ) {
		return combiPlayerTry;
	}

	public JPanel getPanSolutionMain( ) {
		return panSolutionMain;
	}

	public JPanel getPanSolution( ) {
		return panSolution;
	}
	
	public JPanel getPanSolution2( ) {
		return panSolution2;
	}

	public JPanel getPanVirtual( ) {
		return panVirtual;
	}
	
	public JPanel getPanVirtual2( ) {
		return panVirtual2;
	}

	public Piece [ ] getSpotSolution( ) {
		return spotSolution;
	}

	public Piece [ ] getSpotSolution2( ) {
		return spotSolution2;
	}
	
	public boolean isPlayerWin( ) {
		return playerWin;
	}

	public boolean isComputerWin( ) {
		return computerWin;
	}
	
	
	//----------SETTERS----------
	
	public void setLine2( JPanel [ ] line2 ) {
		this.line2 = line2;
	}

	public void setSpot2( Piece [ ] spot2 ) {
		this.piece2 = spot2;
	}

	public void setResult2( JPanel [ ] result2 ) {
		this.result2 = result2;
	}

	public void setSelector2( SelectorG2 [ ] selector2 ) {
		this.selector2 = selector2;
	}

	public void setBtnValidate2( JButton btnValidate2 ) {
		this.btnValidate2 = btnValidate2;
	}

	public void setCombiPlayer( Combi combiPlayer ) {
		this.combiPlayer = combiPlayer;
	}

	public void setCombiComputerTry( Combi combiComputerTry ) {
		this.combiComputerTry = combiComputerTry;
	}

	public void setCombiPlayerTry( Combi combiPlayerTry ) {
		this.combiPlayerTry = combiPlayerTry;
	}

	public void setPanSolutionMain( JPanel panSolutionMain ) {
		this.panSolutionMain = panSolutionMain;
	}

	public void setPanSolution( JPanel panSolution ) {
		this.panSolution = panSolution;
	}

	public void setPanSolution2( JPanel panSolution2 ) {
		this.panSolution2 = panSolution2;
	}

	public void setPanVirtual( JPanel panVirtual ) {
		this.panVirtual = panVirtual;
	}

	public void setPanVirtual2( JPanel panVirtual2 ) {
		this.panVirtual2 = panVirtual2;
	}

	public void setSpotSolution( int i , Piece spotSolution ) {
		this.spotSolution[ i ] = spotSolution;
	}

	public void setSpotSolution2( int i , Piece spotSolution2 ) {
		this.spotSolution2[ i ] = spotSolution2;
	}

	public void setPlayerWin( boolean playerWin ) {
		this.playerWin = playerWin;
	}

	public void setComputerWin( boolean computerWin ) {
		this.computerWin = computerWin;
	}

	@Override
	public void setCombiComputer( Combi combi ) {
		this.combiComputer = combi;	
	}

	public JPanel getGrid2( ) {
		return grid2;
	}
}
