package fr.thib.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.thib.view.Index;
import fr.thib.view.MainFrame;
import fr.thib.view.Nav;
import fr.thib.view.game1.G1Rules;
import fr.thib.view.game1.Game1Setting;
import fr.thib.view.game2.G2Rules;
import fr.thib.view.game2.Game2Setting;

public class NavListener implements ActionListener {

	
	private Nav nav;
	private MainFrame parent;


	//----------CONSTRUCTOR----------
	
	public NavListener( Nav nav , MainFrame parent ) {

		this.nav = nav;
		this.parent = parent;
	}


	//----------ACTIONS----------
	
	@Override
	public void actionPerformed( ActionEvent ev ) {


		// If click on New Game
		if ( ev.getSource( ) == nav.getNavBtn( 0 ) ) {
			parent.getPanGame( ).removeAll( );
			
			// If click on Game 1 in the index
			if ( parent.isGame1Go( ) ) {
				
				Game1Setting gameSet = new Game1Setting( parent );
				
				parent.getPanGame( ).add( gameSet , BorderLayout.CENTER );
			}

			// If click on Game 2 in the index
			else if ( !parent.isGame1Go( ) ) {
				
				Game2Setting gameSet = new Game2Setting( parent );
				
				parent.getPanGame( ).add( gameSet , BorderLayout.CENTER );
			}
			parent.getContentPane( ).add( parent.getPanGame( ) , BorderLayout.CENTER );
		}

		// If click on Rules
		else if ( ev.getSource( ) == nav.getNavBtn( 1 ) ) {
			
			// If click on Game 1 in the navigation menu
			
			if ( parent.isGame1Go( ) ) {

				G1Rules rules = new G1Rules( parent );
				rules.setVisible( true );
			}

			// If click on Game 2 in the index
			else if ( !parent.isGame1Go( ) ) {
				G2Rules rules = new G2Rules( parent );
				rules.setVisible( true );
			}
		}

		// If click on Back to main menu
		else if ( ev.getSource( ) == nav.getNavBtn( 2 ) ) {

			parent.getPanNav( ).removeAll( );
			parent.getPanGame( ).removeAll( );
			parent.getContentPane( ).removeAll( );
			Index index = new Index( parent );
			parent.getContentPane( ).add( index );
		}

		// If click on Quit
		else if ( ev.getSource( ) == nav.getNavBtn( 3 ) ) {
			parent.getPanGame( ).removeAll( );
			System.exit( 0 );
		}

		parent.revalidate( );

	}

}
