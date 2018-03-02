package fr.thib.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.exception.TooManyPiecesException;



public abstract class Config {

	
	
	static final Logger logger = LogManager.getLogger( );

	private static Properties prop = new Properties( );
	
	private final static String CONFIG_FILE = "./ressources/config.properties";
	
	private static boolean dev = false;

	private final static String [ ] NB_DIGIT = { 
			"Easy digit" , "Normal digit" , "Hard digit" , "Insane digit" };

	private final static String [ ] NB_COLOR = { 
			"Easy color" , "Normal color" , "Hard color" , "Insane color" };

	private final static String [ ] NB_PIECE = { 
			"Easy piece" , "Normal piece" , "Hard piece" , "Insane piece" };

	private final static String [ ] NB_TRY_G1 = { 
			"Easy try game1" , "Normal try game1" , "Hard try game1" ,
			"Insane try game1" };

	private final static String [ ] NB_TRY_G2 = { 
			"Easy try game2" , "Normal try game2" , "Hard try game2" ,
			"Insane try game2" };
	
	private final static String DEV_MODE = "Developer mode";

	private static int nbDigit = 0;
	private static int nbTry = 0;
	private static byte nbColor = 0;
	private static byte nbPiece = 0;

	
	
	//----------GAME 1----------
	
	
	/**
	 * Returns the boolean value of 'Developer mode" parameter in config.properties
	 * @return the boolean value of 'Developer mode" parameter in config.properties
	 */
	public static boolean getDevModeProp( ) {

		try {
			InputStream is = new FileInputStream( CONFIG_FILE );
			prop.load( is );
			is.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Boolean.parseBoolean( ( prop.getProperty( DEV_MODE ) ) );
	}
	
		

	/**
	 * For Game1, return number of digit as set in the file config.properties 
	 * for the dificulty level equals to NB_DIGIT[ i ]
	 * 
	 * @param i
	 * 		The key in NB_DIGIT[ i ]
	 * 
	 * @return the number of digit as set in config.properties
	 */
	public static int getDigitProp( int i ) {

		try {
			InputStream is = new FileInputStream( CONFIG_FILE );
			prop.load( is );
			is.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt( prop.getProperty( NB_DIGIT [ i ] ) );
	}


	
	/**
	 * For Game1, return number of tries as set in the file config.properties 
	 * for the dificulty level equals to NB_TRY_G1 [ i ]
	 * 
	 * @param i
	 * 		The key in NB_TRY_G1 [ i ]
	 * 
	 * @return the number of tries as set in config.properties
	 */
	public static int getTryProp( int i ) {

		InputStream is;
		try {
			is = new FileInputStream( CONFIG_FILE );
			prop.load( is );
			is.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt( prop.getProperty( NB_TRY_G1 [ i ] ) );
	}

	

	//----------GAME 2----------
	
	

	/**
	 * For Game2, return number of colors as set in the file config.properties 
	 * for the dificulty level equals to NB_COLOR [ i ]
	 * 
	 * @param i
	 * 		The key in NB_COLOR [ i ]
	 * 
	 * @return the number of colors as set in config.properties
	 */
	public static byte getColorProp( int i ) {

		try {
			InputStream is = new FileInputStream( CONFIG_FILE );
			prop.load( is );
			is.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Byte.parseByte( prop.getProperty( NB_COLOR [ i ] ) );
	}


	
	/**
	 * For Game2, return number of spot as set in the file config.properties 
	 * for the dificulty level equals to NB_SQUARE [ i ]
	 * 
	 * @param i
	 * 		The key in NB_SQUARE [ i ]
	 * 
	 * @return the number of spot as set in config.properties
	 */
	public static byte getPieceProp( int i ) {

		try {
			InputStream is = new FileInputStream( CONFIG_FILE );
			prop.load( is );
			is.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Byte.parseByte( prop.getProperty( NB_PIECE [ i ] ) );
	}


	
	/**
	 * For Game2, return number of tries as set in the file config.properties 
	 * for the dificulty level equals to NB_TRY_G2 [ i ]
	 * 
	 * @param i
	 * 		The key in NB_TRY_G2 [ i ]
	 * 
	 * @return the number of tries as set in config.properties
	 */
	public static int getTryG2Prop( int i ) {

		InputStream is;
		try {
			is = new FileInputStream( CONFIG_FILE );
			prop.load( is );
			is.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt( prop.getProperty( NB_TRY_G2 [ i ] ) );
	}

	
	
	/**
	 * Throws an exception if the number of spot of game2 is bigger than 7
	 * The selected algorithm can support such a number of spot
	 * @throws TooManyPiecesException
	 */
	public static void testSetting(  ) throws TooManyPiecesException {
		if ( nbPiece > 7 ) {
			 throw new TooManyPiecesException( 
					 "This difficulty level has been set with too many spots. "
			 		+ "Please use the file ./ressources/config.properties and "
			 		+ "set a number of piece lower than 8." );
			
		}
	}

	
	//----------GETTERS----------

	
	public static int getNbDigit( ) {
		return nbDigit;
	}

	public static int getNbTry( ) {
		return nbTry;
	}

	public static boolean isDev( ) {
		return dev;
	}

	public static byte getNbColor( ) {
		return nbColor;
	}

	public static byte getNbPiece( ) {
		return nbPiece;
	}
	
	
	
	//----------SETTERS----------

	public static void setNbDigit( int nbDigit ) {
		Config.nbDigit = nbDigit;
	}

	public static void setNbTry( int nbTry ) {
		Config.nbTry = nbTry;
	}

	public static void setDev( boolean dev ) {
		Config.dev = dev;
	}

	public static void setNbColor( byte nbColor ) {
		Config.nbColor = nbColor;
	}

	public static void setNbPiece( byte nbPiece ) {
		Config.nbPiece = nbPiece;
	}
}
