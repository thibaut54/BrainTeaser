package fr.thib.model.game1;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.exception.CodeInvalidException;
import fr.thib.model.Config;
import fr.thib.view.game1.G1Challenger;
import fr.thib.view.game1.G1Defender;
import fr.thib.view.game1.G1Duel;
import fr.thib.view.game1.Game1;

public class Code {

	
	
	static final Logger logger = LogManager.getLogger( );

	private int [ ] number;

	private boolean firstTry = true;

	private int [ ] newDigit;

	
	
	
	
	//----------CONSTRUCTOR----------

	/**
	 * Creates a code with a randomly generated int[ ] as attribute
	 */
	public Code( ) {

		this.number = genRandom( Config.getNbDigit( ) );
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		logger.debug( "The randomly generated code is: " + this.toString( ) );
	}

	/**
	 * Creates a code with a defined int[ ] as attribute
	 * @param number
	 * 			The int[ ] to register as attribute
	 */
	public Code( int [ ] number ) {

		this.number = number;
		
		logger.info( "New instance of " + getClass( ).getSimpleName( ) + "." );
		logger.debug( "The randomly generated code is: " + this.toString( ) );
	}

	
	
	
	//----------METHODS----------
	

	
	/**
	 * Generates a random code
	 * @param nbDigit
	 * 			the number of digit of the code to generate
	 * @return A table of int representing a code
	 */
	public int [ ] genRandom( int nbDigit ) {

		Random rand = new Random( );
		int randNb[] = new int [ nbDigit ];
		for ( int j = 0; j < nbDigit; j++ ) {
			// random.nextInt(max - min + 1) + min
			randNb [ j ] = rand.nextInt( 10 );
		}
		return randNb;
	}


	
	/**
	 * Displays to player wether he won or lost and number of try remaing
	 * @param game
	 * 			the JPanel where the game is displayed
	 * @see Game1#activSelector
	 */
	public void displayResult( G1Interface game )
	{	
		Code codePlayerTry = this ;
		
		Code codeTarget = game.getCodeComputer( );
		
		int nbTryLeft = Config.getNbTry( ) - game.getNbTryPlayer( ) - 1;
		
		String strEqualsCode = game.getStrEqualsCode( );
		String strNoTry = game.getStrNoTry( );
		String strDifferentCode = game.getStrDifferentCode();
		strDifferentCode = strDifferentCode.replace( "X" , nbTryLeft+"" );
		
		// If code is different and player still has tries
		if(  ! codePlayerTry.equals( codeTarget ) && nbTryLeft > 0 ) {
			
			game.getResult( ).setText( strDifferentCode );
			
		}
		
		else if ( codePlayerTry.equals( codeTarget ) && nbTryLeft >= 0 ){
			
			game.getResult( ).setText( strEqualsCode );				
			game.getBtnValidate( ).setEnabled( false );
			game.activSelector( false );
		}
		
		// If there is no more try
		else {

			game.getResult( ).setText( strNoTry );
			game.getBtnValidate( ).setEnabled( false );
			game.activSelector( false );
		}
	}
	
	
	
	/**
	 * Displays to player wether computer won or lost and number of try remaing
	 * @param game
	 * 			the JPanel where the game is displayed
	 * @see Game1#activSelector
	 */
	public void displayResultComputer( G1Duel game ) {	
		
		Code codeComputerTry = game.getCodeComputerTry( ) ;
		
		Code codeTarget = this;
		
		int nbTryLeft = Config.getNbTry( ) - game.getNbTryComputer( ) - 1;
		
		String strEqualsCode = game.getStrEqualsCodeComputer( );
		String strNoTry = game.getStrNoTryComputer( );
		String strDifferentCode = game.getStrDifferentCodeComputer( );
		strDifferentCode = strDifferentCode.replace( "X" , nbTryLeft+"" );
		
		// If player still has tries and code is different from target
		if(  ! codeComputerTry.equals( codeTarget ) && nbTryLeft > 0 ) {
			
			game.getResult( ).setText( strDifferentCode );	
		}
		
		// If player still has tries and code is the same than target
		else if ( codeComputerTry.equals( codeTarget ) && nbTryLeft >= 0 ){
			
			game.getResult( ).setText( strEqualsCode );				
			game.getBtnValidate( ).setEnabled( false );
			game.activSelector( false );
		}
		
		// If there is no more try
		else {
			
			game.getResult( ).setText( strNoTry );
			game.getBtnValidate( ).setEnabled( false );
			game.activSelector( false );
		}
	}
	
	
	
	/**
	 * For each number of the code tested, displays if it is + , - or =
	 * than the target code
	 * 
	 * @param game
	 * 			the JPanel where the game is displayed
	 * 
	 * @see G1Challenger#getCodeComputer
	 * @see G1Challenger#getIndicatorField
	 * @see Code#getNumber
	 * @see Config#getNbDigit
	 */
	public void testNumbersChall( G1Interface game ) {

		Code codeComputer = game.getCodeComputer();
		
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			if ( this.getNumber( i ) == codeComputer.getNumber( i ) ) {
				game.getIndicatorField( )[ i ].setText( "=" );
				game.getIndicatorField( )[ i ].setForeground( Color.GREEN );

			}
			else if ( this.getNumber( i ) > codeComputer.getNumber( i ) ) {
				game.getIndicatorField( )[ i ].setText( "-" );

			}
			else {
				game.getIndicatorField( )[ i ].setText( "+" );
			}
		}
	}
	
	
	
	/**
	 * For each number of the code tested, displays if it is + , - or =
	 * than the target code
	 * 
	 * @param game
	 * 			the JPanel where the game is displayed
	 * 
	 * @see G1Defender#getCodeComputer
	 * @see Game1#getIndicatorField
	 * @see Code#getNumber
	 * @see Config#getNbDigit
	 */
	public void testNumbersDef( G1Interface game ) {

		Code codeComputer = game.getCodeComputer();
		
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			if ( this.getNumber( i ) == codeComputer.getNumber( i ) ) {
				game.getIndicatorField( )[ i ].setText( "=" );
				game.getIndicatorField( )[ i ].setForeground( Color.RED );

			}
			else if ( this.getNumber( i ) > codeComputer.getNumber( i ) ) {
				game.getIndicatorField( )[ i ].setText( "+" );

			}
			else {
				game.getIndicatorField( )[ i ].setText( "-" );
			}
		}
	}
	
	
	
	/**
	 * For each number of the code tested, displays if it is + , - or =
	 * than the target code
	 * 
	 * @param game
	 * 			The JPanel where the game is displayed
	 * 
	 * @see G1Duel#getCodeComputer
	 * @see G1Duel#getIndicatorField2
	 * @see Code#getNumber
	 * @see Config#getNbDigit
	 */
	public void testNumbersDuel( G1Duel game ) {

		Code codeComputer = game.getCodeComputerTry();
		
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			if ( this.getNumber( i ) == codeComputer.getNumber( i ) ) {
				game.getIndicatorField2( )[ i ].setText( "=" );
				game.getIndicatorField2( )[ i ].setForeground( Color.RED );

			}
			else if ( this.getNumber( i ) > codeComputer.getNumber( i ) ) {
				game.getIndicatorField2( )[ i ].setText( "+" );

			}
			else {
				game.getIndicatorField2( )[ i ].setText( "-" );
			}
		}
	}
	


	/**
	 * IA algorithm. Will try to find the opponent's code using
	 * logic and a bit of random to imitate human
	 * 
	 * @param game
	 * 			the JPanel where the game is displayed
	 * 
	 * @see Code#initNewDigit
	 * @see Code#random
	 * @see Config#getNbDigit
	 */
	public void searchCode( G1Interface game ) {

		Code codeComputer;
		
		
		// Put new numbers in codeComputer
		if ( game.getNbTryPlayer( ) == 0 ) {
			
			int number[] = new int [ Config.getNbDigit( ) ];

			for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
				number [ i ] = random( 4 , 5 );
			}

			game.setCodeComputer( new Code( number ) );
			
			initNewDigit( );
		}

		
		else {
			
			codeComputer = game.getCodeComputer();
			int tries = game.getNbTryPlayer( ) - 1 ;
			
			for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
				if ( codeComputer.getNumber( i ) < getNumber( i ) ) {
					if ( ( codeComputer.getNumber( i ) + newDigit [ tries  ] ) <= 9 ) {
						codeComputer.setNumber( i , codeComputer.getNumber( i ) + newDigit [ tries  ]);
						
					}
					else {
						codeComputer.setNumber( i , codeComputer.getNumber( i ) + 1 );
					}
				}
				else if ( codeComputer.getNumber( i ) > getNumber( i ) ) {
					if ( ( codeComputer.getNumber( i ) - newDigit [ tries  ] ) >= 0 ) {
						codeComputer.setNumber( i , codeComputer.getNumber( i ) - newDigit [ tries  ] );
					}
					else {
						codeComputer.setNumber( i , codeComputer.getNumber( i ) - 1 );
					}
				}
			}
		}	
		
			
		// Display each new digit of the new codePC
		
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			game.getJlabCodeComputer( ) [ i ].setText( 
					String.valueOf( game.getCodeComputer().getNumber( i ) ) );

		}

	}
	
	
	
	/**
	 * IA algorithm for Duel mode. Will try to find the opponent's code using
	 * logic and a bit of random to imitate human
	 * 
	 * @param game
	 * 			the JPanel where the game is displayed
	 * 
	 * @see Code#initNewDigit
	 * @see Code#random
	 * @see Config#getNbDigit
	 */
	public void searchCodeDuel( G1Duel game ) {

		Code codeComputerTry;
		
		
		// Put new numbers in codeComputerTry
		if ( game.getNbTryComputer( ) == 0 ) {
			
			int number[] = new int [ Config.getNbDigit( ) ];

			for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
				number [ i ] = random( 4 , 5 );
			}

			game.setCodeComputerTry( new Code( number ) );
			
			initNewDigit( );
		}

		
		else {
			
			codeComputerTry = game.getCodeComputerTry();
			int tries = game.getNbTryPlayer( ) - 1 ;
			
			for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
				if ( codeComputerTry.getNumber( i ) < getNumber( i ) ) {
					if ( ( codeComputerTry.getNumber( i ) + newDigit [ tries  ] ) <= 9 ) {
						codeComputerTry.setNumber( i , codeComputerTry.getNumber( i ) + newDigit [ tries  ]);
						
					}
					else {
						codeComputerTry.setNumber( i , codeComputerTry.getNumber( i ) + 1 );
					}
				}
				else if ( codeComputerTry.getNumber( i ) > getNumber( i ) ) {
					if ( ( codeComputerTry.getNumber( i ) - newDigit [ tries  ] ) >= 0 ) {
						codeComputerTry.setNumber( i , codeComputerTry.getNumber( i ) - newDigit [ tries  ] );
					}
					else {
						codeComputerTry.setNumber( i , codeComputerTry.getNumber( i ) - 1 );
					}
				}
			}
		}	
		
			
		// Display each new digit of the new codePC
		
		for ( int i = 0; i < Config.getNbDigit( ); i++ ) {
			game.getJlabCodeComputer( ) [ i ].setText( 
					String.valueOf( game.getCodeComputerTry().getNumber( i ) ) );

		}

	}

	

	/**
	 * Initiates the sequences of incrementation for the solving algorism
	 *
	 * @see Config#getNbTry
	 * @see Code#random
	 */
	public void initNewDigit( ) {

		newDigit = new int [ Config.getNbTry( ) ];
		newDigit [ 0 ] = random( 2 , 3 );
		
		if ( Config.getNbTry( ) >= 2 ) {
			
			if ( newDigit [ 0 ] == 3 ) {
		
				newDigit [ 1 ] = 1;
			}
			
			else {
				
				newDigit [ 1 ] = 2;
			}
		}
		
		if ( Config.getNbTry( ) >= 3 ) {
			
			newDigit [ 2 ] = 1;
			
			for ( int i = 3; i < Config.getNbTry( ); i++ ) {
			
				newDigit [ i ] = 1;
			}
		}
	}

	
	
	/**
	 * Generates a random integer
	 *
	 * @param min
	 * 			the inclusive minimum value of the generated integer
	 * @param max
	 * 			the inclusive maximum value of the generated integer
	 *
	 * @return a random integer
	 */
	public int random( int min , int max ) {

		Random random = new Random( );
		return random.nextInt( max - min + 1 ) + min;
	}


	
	/**
	 * Test code validity and throws an exception is the code is two short
	 * or if it contains other character than digit
	 *
	 * @param code
	 * 			The tab of char to test
	 * 
	 * @see Config#getNbDigit
	 * @see CodeInvalidException
	 * 
	 * @throws CodeInvalidException
	 * 			If the inpt code is not considered as valide					
	 */
	public static void testCodeValidity( char [ ] code ) throws CodeInvalidException {

		String str = new String( code );
		
		if ( !( code.length == Config.getNbDigit( ) ) ){
			
			throw new CodeInvalidException(
					"The code must be " + Config.getNbDigit( ) + 
					" digits long." );
		}
		
		else {
			
			for ( int i = 0; i < code.length; i++ ) {
				
				if( ! Character.isDigit( str.charAt( i ) ) ) {
					throw new CodeInvalidException(
							"The code must contains only digits." );
				}
			}
		}
	}
	
	
	
	/**
	 * Displays the code as a string / text
	 * 
	 * @return The numbers contained in this, as a String
	 * @see Config#getNbDigit
	 */
	public String toString( ) {

		String str = "";
		for ( int j = 0; j < Config.getNbDigit( ); j++ ) {
			str += this.getNumber( ) [ j ];
		}
		return str;
	}

	
	
	

	//----------GETTERS----------
	
	public int [ ] getNumber( ) {
		return number;
	}

	public int getNumber( int i ) {
		return number [ i ];
	}

	public boolean isFirstTry( ) {
		return firstTry;
	}

	

	//----------SETTERS----------

	public void setNumber( int i , int nb ) {
		this.number [ i ] = nb;
	}

	public void setFirstTry( boolean firstTry ) {
		this.firstTry = firstTry;
	}


	
	//----------HASHTAG & EQUALS----------

	
	@Override
	public int hashCode( ) {

		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode( number );
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
		Code other = ( Code ) obj;
		if ( !Arrays.equals( number , other.number ) )
			return false;
		return true;
	}

}
