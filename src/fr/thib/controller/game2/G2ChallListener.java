package fr.thib.controller.game2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.model.game2.Combi;
import fr.thib.view.game2.G2Challenger;

public class G2ChallListener implements ActionListener {
	
	static final Logger logger = LogManager.getLogger();

	private G2Challenger game;

	
	//----------CONSTRUCTOR----------
	
	
	public G2ChallListener( G2Challenger game ) {
		this.game = game;
	}
	
	
	
	//----------ACTIONS----------
	
	
	@Override
	public void actionPerformed( ActionEvent ev ) {

		// clic on button validate
		if( ev.getSource( ) == game.getBtnValidate( ) ) {
			
	
			game.setCombiPlayer( new Combi( game.myCombi( ) ) );
			
			game.displayCombi( game.getCombiPlayer( ) );
			
			// displayInfo tests if player combination is equal to solution,
			// and if so, displays the victory message
			game.getCombiPlayer().displayInfo( game , game.getCombiComputer( ) );
			
			// test combination and puts the score in score, in Combi combiPlayer
			game.getCombiPlayer().testCombi( game.getCombiComputer( ) );
		
			// displays the score
			game.displayScore( game.getCombiPlayer().getScore( )  );
			
			// if player still has tries
			if( game.getNbTryPlayer( ) < Config.getNbTry( ) - 1 ) {
			
				game.addTryPlayer( );
			}
			
			// if the player didn't win and he has no more try, then he lost
			else if ( ! game.getCombiPlayer( ).equals( game.getCombiComputer( ) ) ) {
				
				game.getJTextInfo().setText( "I'm sorry but you lost my dear player! " );
				game.getPanInfo().setBackground( Color.RED );
				game.activSelector( false );
				game.getBtnValidate( ).setEnabled( false );
				
				if( ! Config.isDev( ) ) {
					game.showCombi( );
				}
			}
		}
	}
}
