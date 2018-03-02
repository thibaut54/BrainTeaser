package fr.thib.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.view.GameTitle;
import fr.thib.view.Index;
import fr.thib.view.MainFrame;
import fr.thib.view.Nav;

public class IndexListener implements ActionListener {

	static final Logger logger = LogManager.getLogger();

	private MainFrame parent;
	private Index index;

	
	
	//----------CONSTRUCTOR----------
	
	
	public IndexListener( Index index , MainFrame parent ) {
		this.index = index;
		this.parent = parent;
	}
	
	
	
	//----------ACTIONS----------
	
	
	@Override
	public void actionPerformed( ActionEvent ev ) {

		Nav nav = new Nav( parent );
		
		parent.getContentPane().removeAll();
		parent.setSize( 800 , 600 );
		parent.getPanNav().add( nav );
		parent.getContentPane().add( parent.getPanNav() , BorderLayout.WEST );

		GameTitle title = null;
		
		// if clic on Crack the code
		if ( ev.getSource() == index.getBtnGame1() ) {
			
			nav.getMainFrame().setGame1Go( true );										
			
			// initiates screen title with the image of game
			title = new GameTitle( new ImageIcon( "./ressources/imgTitleG1_500_600.jpg" ) );
			
			// sets the JFrame title in order to indicate if Developer Mode is on
			if( ! Config.isDev( ) ) {
				parent.setTitle( index.getTITLEGAME1() );
			}
			else {
				parent.setTitle( index.getTITLEGAME1() + " - Developer mode" );
			}
		}
		
		// If clic on Mastermind
		else if ( ev.getSource() == index.getBtnGame2() ) {
			
			nav.getMainFrame().setGame1Go( false );
			
			// initiates screen title with the image of game
			title = new GameTitle( new ImageIcon( "./ressources/imgTitleG2_500_600.jpg" ) );
			
			// sets the JFrame title in order to indicate if Developer Mode is on
			if( ! Config.isDev( ) ) {
				parent.setTitle( index.getTITLEGAME2() );
			}
			else {
				parent.setTitle( index.getTITLEGAME2() + " - Developer mode" );
			}	
		}
		
		parent.getPanGame().add( title );
		parent.getContentPane().add( parent.getPanGame() , BorderLayout.CENTER );		
		parent.getContentPane().revalidate();
		parent.revalidate();
	}
}
