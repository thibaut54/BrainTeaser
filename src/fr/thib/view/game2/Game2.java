package fr.thib.view.game2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.model.game2.Combi;

public class Game2 extends JPanel {

	private static final long serialVersionUID = 7548165604684859680L;
	
	static final Logger logger = LogManager.getLogger( );

	// one selector for each piece 
	private SelectorG2 [ ] selector;
	
	// panSelect: JPanel in which the selector is displayed
	// grid: JPanel in which the combinations that player or IA tries are displayed
	// panInfo: JPanel in which then information for player is displayed
	private JPanel panSelect , grid, panInfo;
	
	// JLabel that display information for player
	private JLabel jTextInfo;
	
	// button to validate a combination
	private JButton btnValidate;
	
	// line: contains each piece of the combination
	// result: contains the score of each combination
	private JPanel [ ] line , result;
	
	// a table of X pieces, with X the number of piece defined in the difficulty level
	private Piece [ ] piece;
	
	// the color of the JPanel in which the score is displayed
	private Color colorBg;
	
	// the number of try the player has used at 
	private byte nbTryPlayer = 0;


	
	//----------CONSTRUCTOR----------

	
	/**
	 * The basis of the view each mode for game2
	 */
	public Game2( ) {

		setLayout( new BorderLayout( ) );

		// initiates the selector
		selector = new SelectorG2 [ Config.getNbPiece( ) ];
		
		
		panInfo = new JPanel( new GridLayout( 1 , 1 ) );
		add( panInfo , BorderLayout.NORTH );
		
		jTextInfo = new JLabel( );
		jTextInfo.setBorder( BorderFactory.createLineBorder( Color.black ) );
		panInfo.add( jTextInfo );
		
		
		panSelect = new JPanel( new GridLayout( 1 , 1 ) );

		for ( int i = 0; i < selector.length; i++ ) {
			selector [ i ] = new SelectorG2( );
			panSelect.add( selector [ i ].getPanMain( ) );
		}

		btnValidate = new JButton( "VALIDATE" );
		btnValidate.setFont( new Font( "Berlin Sans FB", Font.BOLD, 14 ) );
		btnValidate.setBackground( Color.BLACK );
		btnValidate.setForeground( Color.WHITE );

		panSelect.add( btnValidate );

		add( panSelect , BorderLayout.SOUTH );

		grid = new JPanel( new GridLayout( Config.getNbTry( ) , 1 ) );

		line = new JPanel [ Config.getNbTry( ) ];
		result = new JPanel [ Config.getNbTry( ) ];
		piece = new Piece [ Config.getNbPiece( ) ];
		colorBg = new Color( 145 , 104 , 86 );

		// Creates 1 line per available try
		for ( int l = 0; l < Config.getNbTry( ); l++ ) {

			line [ l ] = new JPanel( );
			result [ l ] = new JPanel( );
			result [ l ].setPreferredSize( new Dimension( 60 , 35 ) );
			result [ l ].setBackground( colorBg );
			result [ l ].setBorder( BorderFactory.createLineBorder( Color.black ) );
		}

		// For each line < l >
		for ( int l = 0; l < Config.getNbTry( ); l++ ) {

			// For each square < s > of the line < l >
			for ( int s = 0; s < Config.getNbPiece( ); s++ ) {

				piece [ s ] = new Piece( );
				piece [ s ].setPreferredSize( new Dimension( 36 , 36 ) );

				line [ Config.getNbTry( ) - 1 - l ].add( piece [ s ] );

			}
			
			// Puts the result right next the line where the combination is displayed
			line [ Config.getNbTry( ) - 1 - l ].add( result [ Config.getNbTry( ) - 1 - l ] );
			
			// Fills the grid starting by the bottom to reproduce a Mastermind game
			grid.add( line [ Config.getNbTry( ) - 1 - l ] );

		}

	}

	
	
	//----------METHODS----------

	

	/**
	 * Puts combination corresponding to colors chosen by player into "combiPlayer"
	 * 
	 * @return the combination, as a table of byte, corresponding to the one in the selector
	 * 
	 * @see Config#getNbPiece
	 */
	public byte [ ] myCombi( ) {

		byte [ ] combiPlayer = new byte [ Config.getNbPiece( ) ];
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			combiPlayer [ i ] = this.getSelector( ) [ i ].getIndexColor( );
		}
		return combiPlayer;
	}


	
	/**
	 * Disables or enable buttons in selector
	 * 
	 * @param onOff
	 * 
	 * @see Config#getNbPiece
	 * @see Selector#setMouseActiv
	 */
	public void activSelector( boolean onOff ) {

		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			this.getSelector( ) [ i ].getPlus( ).setEnabled( onOff );
			this.getSelector( ) [ i ].getMinus( ).setEnabled( onOff );
			this.getSelector( ) [ i ].setMouseActiv( onOff );
		}
	}
	
	

	/**
	 * Removes buttons in selector
	 * 
	 * @param selector
	 * 
	 * @see Config#getNbPiece
	 */
	public void removeBtnSelector( SelectorG2 [ ] selector ) {

		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			selector [ i ].getPanBtn( ).removeAll( );
			selector [ i ].getPanMain( ).revalidate( );
		}
	}

	
	
	/**
	 * Hides buttons in selector
	 * 
	 * @param selector
	 * 
	 * @see Config#getNbPiece
	 */
	public void hideBtnSelector( SelectorG2 [ ] selector ) {

		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			selector [ i ].getPlus( ).setVisible( false );
			selector [ i ].getMinus( ).setVisible( false );

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
	public void displayScore( byte [ ] score ) {

		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			// if good color good located
			if ( score [ i ] == 2 ) {
				JPanel panRes = new JPanel( );
				panRes.setPreferredSize( new Dimension( 10 , 10 ) );
				panRes.setBackground( Color.BLACK );
				getResult( ) [ nbTryPlayer ].add( panRes );
			}
			// if good color wrong located
			if ( score [ i ] == 1 ) {
				JPanel panRes = new JPanel( );
				panRes.setSize( new Dimension( 10 , 10 ) );
				panRes.setBackground( Color.WHITE );
				getResult( ) [ nbTryPlayer ].add( panRes );
			}

			FlowLayout layout = new FlowLayout( FlowLayout.LEFT , 3 , 2 );

			getResult( ) [ nbTryPlayer ].setLayout( layout );

		}

	}


	/**
	 * Displays the combination corresponding to the combination in parameter
	 * 
	 * @param combi
	 * 			The combination to display
	 * 
	 * @see Config#getNbPiece
	 */
	public void displayCombi( Combi combi ) {

		// Fill each spot of the line with the colors chossen by the player
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {

			Color c = getSelector( ) [ i ].getColor( ) [ combi.getCombi( ) [ i ] ];

			Component comp = getLine( ) [ nbTryPlayer ].getComponent( i );

			( ( Piece ) comp ).setC( c );

			getLine( ) [ nbTryPlayer ].getComponent( i ).repaint( );
			getLine( ) [ nbTryPlayer ].getComponent( i ).revalidate( );

		}

	}
	
	

	public void addTryPlayer( ) {
		nbTryPlayer++;
	}


	
	//----------GETTERS----------

	public SelectorG2 [ ] getSelector( ) {
		return selector;
	}

	public JButton getBtnValidate( ) {
		return btnValidate;
	}

	public JPanel [ ] getLine( ) {
		return line;
	}

	public Piece [ ] getSpot( ) {
		return piece;
	}

	public JPanel [ ] getResult( ) {
		return result;
	}

	public JPanel getGrid( ) {
		return grid;
	}

	public Color getColorBg( ) {
		return colorBg;
	}

	public JPanel getPanSelect( ) {
		return panSelect;
	}

	public byte getNbTryPlayer( ) {
		return nbTryPlayer;
	}
	
	public JLabel getJTextInfo() {
		return jTextInfo;
	}
	
	public JPanel getPanInfo() {
		return panInfo;
	}



	//----------INNER CLASS----------
	
	/**
	 * Displays a piece filled with a color
	 *
	 */
	public class Piece extends JPanel {

		private static final long serialVersionUID = 664081141343241302L;

		private Color c = null;


		public void paintComponent( Graphics g ) {

			super.paintComponent( g );

			int width = this.getWidth( );
			int height = this.getHeight( );
			int diam = height - 2;

			if ( !( c == null ) ) {
				g.setColor( c );
				g.fillOval( width / 2 - diam / 2 , height / 2 - diam / 2 , diam , diam );
				g.setColor( Color.BLACK );
				g.drawOval( width / 2 - diam / 2 , height / 2 - diam / 2 , diam , diam );
			}

			g.drawOval( width / 2 - diam / 2 , height / 2 - diam / 2 , diam , diam );

		}


		public void setC( Color c ) {
			this.c = c;
		}
	}
}
