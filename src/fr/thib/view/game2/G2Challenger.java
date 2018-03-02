package fr.thib.view.game2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import fr.thib.controller.game2.G2ChallListener;
import fr.thib.model.Config;
import fr.thib.model.game2.Combi;
import fr.thib.model.game2.G2Interface;
import fr.thib.view.MainFrame;

public class G2Challenger extends Game2 implements G2Interface{
	
	private static final long serialVersionUID = -8706926886194538355L;
	
	private JPanel panSolution;
	private JPanel panVirtual;
	private Piece[] spotSolution;
	
	private Combi combiComputer;
	private Combi combiPlayer;

	private String strStart = "Welcome dear player ! Please feel free to find out my combination... "
			+ "	I mean, you can try at least...";
	
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates and displays the view of game 2 in Challenger mode
	 * 
	 * @param mainFrame
	 * 				The main frame in which the application is diplayed
	 */
	public G2Challenger( MainFrame mainFrame ) {
		
		super();
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		
		add( getGrid() , BorderLayout.CENTER );
		
		getJTextInfo( ).setText( strStart );

		mainFrame.setSize( 800 , 600 );
		
		// generates the secret combination that player must find out
		combiComputer = new Combi();
		
		if ( Config.isDev( ) ) {
			
			showCombi( );
		}
		
		
		getBtnValidate().addActionListener( new G2ChallListener( this ) );
	}

	
	
	//----------METHODS----------
	
	/**
	 * Displays the secret combination of the computer at the end of the game
	 * 
	 * @see Config#getNbPiece
	 * @see Config#getNbTry
	 */
	public void showCombi() {
		
		getGrid( ).setLayout( new GridLayout( Config.getNbTry( ) + 1 , 1 ) ) ;
		
		panSolution = new JPanel( );
		panVirtual = new JPanel( );
		panVirtual.setPreferredSize( new Dimension( 60 , 35 )  );
		panVirtual.setBackground( getColorBg() );
		spotSolution = new Piece[ Config.getNbPiece( ) ];
		
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			
			Color c = getSelector( )[ i ].getColor()[ combiComputer.getCombi( )[ i ] ];
			spotSolution[ i ] = new Piece( );
			spotSolution[ i ].setBackground( getColorBg( ) );
			spotSolution[ i ].setPreferredSize( new Dimension( 36 , 36 ) );
			spotSolution[ i ].setC( c );
			panSolution.add( spotSolution[ i ] );
			
		}
		
		panSolution.add( panVirtual );
		panSolution.setBackground( getColorBg( ) );
		
		getGrid( ).add( panSolution , 0 );
	}
	
	
	
	//----------GETTER----------
	
	public Combi getCombiComputer( ) {
		return combiComputer;
	}

	public JPanel [ ] getResult() {
		return super.getResult( );
	}

	@Override
	public Combi getCombiPlayer( ) {
		return combiPlayer;
	}

	@Override
	public Combi getCombiComputerTry( ) {
		return null;
	}

	
	//----------SETTER----------

	@Override
	public void setCombiComputer( Combi combi ) {

	}
	
	public void setCombiPlayer( Combi combiPlayer ) {
		this.combiPlayer = combiPlayer;
	}

	@Override
	public void setCombiComputerTry( Combi combi ) {
		
	}

	@Override
	public void setPlayerWin( boolean b ) {

	}
}
