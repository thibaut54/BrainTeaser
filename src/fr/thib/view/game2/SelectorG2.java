package fr.thib.view.game2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thib.model.Config;
import fr.thib.view.Selector;

public class SelectorG2 extends Selector{
	
	static final Logger logger = LogManager.getLogger();

	private static final long serialVersionUID = 709229257739364621L;

	// the list of available colors in game 2
	private Color[] color = new Color[10];
	
	// indexColor will be use as the index of the table color[]
	private byte indexColor = 0 ;
	
	// component that displays a round pawn with one of the color of the list
	private Pawn pawn;
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates a GUI that player uses to select a pawn with one of the color of the list
	 * 
	 * @see Pawn#Pawn
	 */
	public SelectorG2( ) {

		super( );
		
		setMouseActiv( true );
		
		color[0] = Color.BLUE;
		color[1] = Color.BLACK;
		color[2] = Color.GREEN;
		color[3] = Color.RED;
		color[4] = Color.WHITE;
		color[5] = Color.YELLOW;
		color[6] = Color.MAGENTA;
		color[7] = Color.GRAY;
		color[8] = Color.ORANGE;
		color[9] = Color.PINK;
		

		getPlus( ).setPreferredSize( new Dimension( 30 , 30 ) );
		getMinus( ).setPreferredSize( new Dimension( 30 , 30 ) );
		
		
		Container parent = getJlabNumber( ).getParent();
		parent.remove( getJlabNumber( ) );
		parent.validate();
		parent.repaint();

		pawn = new Pawn( );
		
		getPanMain( ).add( pawn, BorderLayout.CENTER ); 
		
		// If clic on Plus button
		getPlus( ).addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ev ) {

				if( ev.getSource() == getPlus() && indexColor < Config.getNbColor( ) - 1 ) {

					indexColor++;
					pawn.repaint( );
				
				}
			}
		});
		
		// If clic on Minus button
		getMinus( ).addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ev ) {
				
				if( ev.getSource() == getMinus( ) && indexColor > 0 ) {

					indexColor--;
					pawn.repaint( );
				}
				
				getJlabNumber( ).revalidate( );
				getJlabNumber( ).repaint( );
			}
		} );
		
		// IF player uses mouse wheel
		pawn.addMouseWheelListener( new MouseWheelListener( ) {
			
			@Override
			public void mouseWheelMoved( MouseWheelEvent ev ) {
				
				if( isMouseActiv() ) {
					int notches = ev.getWheelRotation();
				
					// if mouse wheel is rolled forward
					if ( notches > 0 && indexColor < Config.getNbColor( ) - 1) {
				    	indexColor++;
						pawn.repaint( );
				    }
					
					// if mouse wheel is rolled backwards
					else if (notches < 0 && indexColor > 0) {
				    	indexColor--;
						pawn.repaint( );
				    }

				    getJlabNumber( ).revalidate( );
					getJlabNumber( ).repaint( );
				}
			}
		} );
		
	}


	//----------GETTERS----------
	
	public byte getIndexColor( ) {
		return indexColor;
	}
	
	public Pawn getPawn( ) {
		return pawn;
	}
	
	public Color [ ] getColor( ) {
		return color;
	}


	
	/**
	 * This class creates a round pawn fill with one of the color of the mastermind
	 *
	 */
	class Pawn extends JPanel{
		
		private static final long serialVersionUID = 4829679916036291889L;
	
		public void paintComponent( Graphics g ) {
			super.paintComponent( g );
	
			int width = this.getWidth();	
		    int height = this.getHeight();
		    int diam = 35;
	
		    g.setColor( color[ indexColor ] );
			g.fillOval( width/2-diam/2 , height/2-diam/2 , diam , diam );
			g.setColor( Color.black );
			g.drawOval( width/2-diam/2 , height/2-diam/2 , diam , diam );
		}
		
	}

	
	
}
