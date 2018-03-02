package fr.thib.model.game2;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.view.game2.G2Duel;
import fr.thib.view.game2.Game2;

public class Combi {

	
	static final Logger logger = LogManager.getLogger( );

	private byte [ ] combi;
	private byte [ ] score;

	private byte nbTry = 0;

	private boolean [ ] processed = new boolean[ Config.getNbPiece( ) ];
	private boolean [ ] processed2 = new boolean[ Config.getNbPiece( ) ];
	
	private static PossibleSolution possibleSolution;
	private static PossibleSolution possibleSolutionOpti;

	
	
//----------CONSTRUCTORS----------

	
	/**
	 * Generates a combination with a randomly generated table of byte
	 * that represents colors
	 * 
	 * @see Combi#genRandom
	 * @see Config#getNbPiece
	 * @see Config#getNbColor
	 */
	public Combi( ) {
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		
		this.combi = genRandom( Config.getNbPiece( ) , Config.getNbColor( ) );
		
		logger.debug( "The randomly generated combination is: " + this.toString( ) );
	}


	
	/**
	 * Generates a combination with a defined table of byte that represents colors
	 * 
	 * @param combi
	 * 			A defined combination
	 */
	public Combi( byte [ ] combi ) {
		
		this.combi = combi;
	}

	

	
//----------METHODS----------


	
	/**
	 * Displays to player wether the this combination he tried is the same
	 * than the target
	 *
	 * @param game
	 * 			The JPanel where the game is displayed
	 * @param otherCombi
	 * 			The target combination
	 *
	 * @see Game2#activSelector
	 * @see G2Duel#setPlayerWin
	 */
	public void displayInfo( G2Interface game , Combi otherCombi ) {
		
		
		// If players combi is the same than computers one
		if( this.equals( otherCombi ) ) {

			game.getBtnValidate( ).setEnabled( false );
			game.activSelector( false );
			game.getPanInfo().setBackground( Color.GREEN );
			game.getJTextInfo().setText(
					"What?! You won! You found my code! "
					+ "Congratulation my dear and smart player  :)");
			game.setPlayerWin( true );
		}
	}
	
	
	
	/**
	 * Compares this combination with another one (otherCombi) 
	 * and give the result according to Mastermind rules
	 *
	 * @param otherCombi
	 * 			The combination to be compared to this combination
	 *
	 * @return A table of byte that represent the score
	 *
	 * @see Config#getNbSpot
	 * @see Combi#getCombi
	 */
	public byte [] testCombi( Combi otherCombi ) {


		byte [] combiTmp2 =  new byte[ Config.getNbPiece( ) ];
		byte [] combiTmp = new byte[ Config.getNbPiece( ) ];
		
		for ( int i = 0; i < Config.getNbPiece( ); i++ ) {
			combiTmp[ i ] = otherCombi.getCombi( )[ i ];
			combiTmp2[ i ] = this.getCombi( )[ i ];
		}
		
		byte [] score = new byte[ Config.getNbPiece( ) ];
		
		otherCombi.setScore( new byte [ Config.getNbPiece( ) ] );


		for ( int j = 0; j < Config.getNbPiece( ); j++ ) {

			score[ j ] = 0;
			
			for ( int i = 0; i < Config.getNbPiece( ); i++ ) {

				// If good color good position
				if ( combiTmp2[ i ] == combiTmp [ j ] && i == j ) {
					combiTmp2[ i ] = -1;
					combiTmp [ j ] = -2;
					score[ j ] = 2 ;
					break;
				}
			}
		}

		for ( int j = 0; j < Config.getNbPiece( ); j++ ) {

			for ( int i = 0; i < Config.getNbPiece( ); i++ ) {

				// If good color wrong position
				if ( combiTmp2[ i ] == combiTmp [ j ] && i != j ) {
					combiTmp2[ i ] = -1;
					score[ j ] = 1 ;
					break;
				}
			}
		}
		
		this.score = score;
		
		return score;
	}
	
	

	/**
	 * Generates a random combination as a byte table
	 *
	 * @param nbSpot
	 * 			The length of the byte table
	 * @param nbColor
	 * 			The maximum value of each byte
	 * @return A table of byte that represent a combination of colors
	 */
	public byte [ ] genRandom( byte nbSpot , byte nbColor ) {

		this.combi = new byte [ nbSpot ];
		Random rand = new Random( );
		for ( int j = 0; j < nbSpot; j++ ) {
			this.combi [ j ] = ( byte ) rand.nextInt( nbColor );
		}
		return this.combi;
	}
	
	

	/**
	 * Generates every possible solution according
	 * to the current difficulty parameters
	 *
	 * @see Combi#possibleSolution
	 */
	public static void genPossible() {
		
		Combi.possibleSolution = new PossibleSolution( );
		Combi.possibleSolution.genAllSolution( ( byte ) 0 );
	}
	
	
 
	/**
	 * Pick a random combi among every possible solution 
	 * and store it into combiComputer
	 *
	 * @param game
	 * 			The JPanel where the game is displayed
	 *
	 * @see Combi#getCombi
	 */
	public void pickCombi( G2Interface game ) {
	
		byte[] randomCombi = Combi.possibleSolution.randomChoose( );
		
		game.setCombiComputerTry( new Combi( randomCombi ) );	
	}
	
	
	
	/**
	 * IA algorythm to find out the secret input combination by the player 
	 * This algorythm is based on Knuth algorythm
	 *
	 * @param game
	 * 			The JPanel where the game is displayed
	 *
	 * @see PossibleSolution
	 * @see Combi#testCombi
	 */
	public void searchCombi( G2Interface game ) {
		
		possibleSolutionOpti = new PossibleSolution( );
		
		long debut = System.currentTimeMillis();
		
		for ( int i = 0; i < Combi.possibleSolution.getNbSolution( ); i++ ) {
			
			Combi combiTmp = new Combi( Combi.possibleSolution.getPossibleSolution( ).get( i ) );
			

			byte [] scoreTmp = game.getCombiComputerTry( ).testCombi( combiTmp );
			
			Arrays.sort( this.score );
			Arrays.sort( scoreTmp );
			
			if( Arrays.equals( this.score , scoreTmp ) ) {
				possibleSolutionOpti.getPossibleSolution( ).add(
						Combi.possibleSolution.getPossibleSolution( ).get( i ).clone() );
			}	
		}
		
		
		logger.debug( "Number of solutions before the algorithm: "
				+ Combi.possibleSolution.getPossibleSolution( ).size( ) );	
		
		logger.debug( "Number of solutions after the algorithm: "
							+ possibleSolutionOpti.getPossibleSolution( ).size( ) );		
		
		Combi.possibleSolution.getPossibleSolution( ).clear( );
		
		
		Combi.possibleSolution.getPossibleSolution( ).addAll( possibleSolutionOpti.getPossibleSolution( ) );

		
		logger.info("Time to execute 'searchCombi': " 
				+ (System.currentTimeMillis()-debut) + "ms");

	}
	
	
	
	
	//----------GETTERS----------
	
	public byte getNbTry( ) {
		return nbTry;
	}

	public byte [ ] getCombi( ) {
		return combi;
	}

	public boolean [ ] getProcessed( ) {
		return processed;
	}

	public boolean [ ] getProcessed2( ) {
		return processed2;
	}

	public byte [ ] getScore( ) {
		return score;
	}
	
	public static PossibleSolution getPossibleSolution( ) {
		return possibleSolution;
	}
	

	//----------SETTERS----------

	public void setNbTry( byte nbTry ) {
		this.nbTry = nbTry;
	}

	public void setProcessed( int i , boolean processed ) {
		this.processed [ i ] = processed;
	}

	public void setProcessed2( int i , boolean processed ) {
		this.processed2 [ i ] = processed;
	}

	public void setCombi( byte [ ] combi ) {
		this.combi = combi;
	}

	public void setProcessed( boolean [ ] processed ) {
		this.processed = processed;
	}

	public void setProcessed2( boolean [ ] processed2 ) {
		this.processed2 = processed2;
	}
	
	public void setScore( byte [ ] score ) {
		this.score = score;
	}
	
	public void setScore( int i , byte score ) {
		this.score[i] = score;
	}


	
//----------TOSTRING - HASHCODE & EQUALS----------
	
	
	public String toString( ) {

		String str = "";
		for ( int j = 0; j < Config.getNbPiece( ); j++ ) {
			switch ( this.getCombi( ) [ j ] ) {
			case 0:
				str += "Blue "; break;
			case 1:
				str += "Black "; break;
			case 2:
				str += "Green "; break;
			case 3:
				str += "Red "; break;
			case 4:
				str += "White "; break;
			case 5:
				str += "Yellow "; break;
			case 6:
				str += "Magenta "; break;
			case 7:
				str += "Gray "; break;
			case 8:
				str += "Orange "; break;
			case 9:
				str += "Pink "; break;
			}

		}
		return str;
	}


	@Override
	public int hashCode( ) {

		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode( combi );
		return result;
	}

	
	@Override
	public boolean equals( Object obj ) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass( ) != obj.getClass( ) )
			return false;
		Combi other = ( Combi ) obj;
		if ( !Arrays.equals( combi , other.combi ) )
			return false;
		return true;
	}
}


