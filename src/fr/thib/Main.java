package fr.thib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.view.MainFrame;

//	logger.trace("msg de trace"); // E/S methods
//	logger.debug("msg de debogage"); // Display values of datas
//	logger.info("msg d'information"); //loading of conf file, beg/end long process
//	logger.warn("msg d'avertissement"); // error login, invalid data
//	logger.error("msg d'erreur"); // all throwned exceptions
//	logger.fatal("msg d'erreur fatale");  // error DB, exceptions which stop the program



/**
 * Date: 27/02/2018
 * 
 * This is an application that contains two different games.
 * One game is "CRACK THE CODE". The other is the famous MASTERMIND.
 * 
 * @author Thibaut
 * @version 1.0
 *
 */
public class Main {
	

	static final Logger logger = LogManager.getLogger();
	
	
	public static void main( String[] args ) {
		
		// If program is started with -dev parameter or properties file 'Developper mode=true'
		if( Config.getDevModeProp( ) || ( args.length > 0 && args[0].equals( "dev" ) ) ) {
			
			logger.info( "Start Developer mode" );
			
			Config.setDev( true );
		}
		
		@SuppressWarnings( "unused" )
		MainFrame mainFrame = new MainFrame();
		
	}
	
}
