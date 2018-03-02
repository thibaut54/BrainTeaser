package fr.thib.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Selector extends JPanel{
	
	static final Logger logger = LogManager.getLogger();
	
	private static final long serialVersionUID = 4418522996223098215L;
	
	// panMain: the main JPanel
	// panBtn: contains buttons
	private JPanel panMain , panBtn;

	// jlabNumber: contains the number
	private JLabel jlabNumber;

	// buttons to increase / decrease the number
	private JButton plusBtn , minusBtn;

	// the number displayed at the begining of the game, as s String
	private String startNb;

	// the number
	private int nbJlab;
	private boolean mouseActiv = true;
	
	
	//----------CONSTRUCTOR----------
	
	/**
	 * Creates a GUI tool that player will use to select
	 * the value of his code in game1, or combination in game2
	 * 
	 */
	public Selector() {
		
	
		panMain = new JPanel( new BorderLayout() );
		panMain.setBorder( BorderFactory.createLineBorder( Color.black ) );
		
		jlabNumber = new JLabel(  );
		jlabNumber.setFont( new Font( "Calibri", Font.CENTER_BASELINE, 60 ) );
		jlabNumber.setHorizontalAlignment( JLabel.CENTER );  
		jlabNumber.setPreferredSize( new Dimension( 100 , 20 ) );
		
		panBtn = new JPanel( new GridLayout( 2 , 1 ));
		
		ImageIcon iconPls = new ImageIcon(
								new ImageIcon(
											"ressources/imgIconUp_50_50.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		plusBtn = new JButton(iconPls);
		plusBtn.setBackground( Color.BLACK );
		
		ImageIcon iconMns = new ImageIcon(
								new ImageIcon(
											"ressources/imgIconDWN_50_50.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		minusBtn = new JButton(iconMns);
		minusBtn.setBackground( Color.BLACK );
		
		plusBtn.setPreferredSize( new Dimension( 50 , 50 ) );
		minusBtn.setPreferredSize( new Dimension( 50 , 50 ) );
		panBtn.add( plusBtn );
		panBtn.add( minusBtn );
		
		
		panMain.add( jlabNumber , BorderLayout.CENTER );
		panMain.add( panBtn , BorderLayout.EAST );
		panMain.setBackground( Color.WHITE );

	}
	
	
	//----------METHOS----------
	
	/**
	 * Displays the updates value of the number in the selector,
	 * after the player increased or decreased the value with buttons
	 * or with mouse wheel
	 */
	public void displayNb() {
		
		setStartNb( String.valueOf( nbJlab ) );
		getJlabNumber().setText( startNb );
		getJlabNumber().repaint();
	}

	
	//----------GETTERS----------
	
	public JLabel getJlabNumber() {
		return jlabNumber;
	}

	public JButton getPlus() {
		return plusBtn;
	}

	public JButton getMinus() {
		return minusBtn;
	}
	
	public String getStartNb() {
		return startNb;
	}

	public JPanel getPanMain() {
		return panMain;
	}
	
	public JPanel getPanBtn() {
		return panBtn;
	}
	
	public int getNbJlab( ) {
		return nbJlab;
	}
	
	public boolean isMouseActiv( ) {
		return mouseActiv;
	}
	
	
	//----------SETTERS----------

	public void setStartNb( String startNb ) {
		this.startNb = startNb;
	}
	
	public void setNbJlab( int nbJlab ) {
		this.nbJlab = nbJlab;
	}

	public void setMouseActiv( boolean mouseActive ) {
		mouseActiv = mouseActive;
	}	
}
