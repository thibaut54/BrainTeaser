package fr.thib.view.game1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.thib.view.Selector;

public class SelectorG1 extends Selector{

	private static final long serialVersionUID = -4978124747038126417L;
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates a GUI tool in game1, that player uses for chose the code he wants
	 */
	public SelectorG1() {
		
		super();
		
		// When the game start, the tool displays zero
		setStartNb( "0" );
		
		getJlabNumber( ).setText( getStartNb( ) );
		
		
		//----------LISTENERS----------
		
		// The plus button increases the value
		getPlus( ).addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ev ) {
				setNbJlab( Integer.parseInt( getStartNb( ) ) );
				if( ev.getSource( ) == getPlus( ) && Integer.parseInt( getStartNb( ) ) < 9 ) {
					setNbJlab( getNbJlab( ) + 1 );
				}
				displayNb( );
			}
		});
		
		// The minus button decreases the value
		getMinus( ).addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ev ) {
				setNbJlab( Integer.parseInt( getStartNb() ) );
				if( ev.getSource( ) == getMinus( ) && Integer.parseInt( getStartNb( ) ) > 0 ) {
					setNbJlab( getNbJlab( ) - 1 );
				}
				displayNb();
			}
		} );
		
		// The mouse wheel can also increase or decrease the value
		getJlabNumber( ).addMouseWheelListener( new MouseWheelListener( ) {
			
			@Override
			public void mouseWheelMoved( MouseWheelEvent ev ) {
				setNbJlab( Integer.parseInt( getStartNb() ) );
				if( isMouseActiv() ) {
					int notches = ev.getWheelRotation();
					
					if ( notches > 0 && Integer.parseInt( getStartNb() ) < 9 ) {
						setNbJlab( getNbJlab( ) + notches );
					}
					else if (notches < 0 && Integer.parseInt( getStartNb() ) > 0 ) {
				    	setNbJlab( getNbJlab( ) + notches );
				    }
				    displayNb();
				}
			}
		} );
	}
}
