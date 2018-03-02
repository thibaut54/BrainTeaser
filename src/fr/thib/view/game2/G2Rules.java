package fr.thib.view.game2;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.thib.view.MainFrame;

public class G2Rules extends JDialog{

	
	private static final long serialVersionUID = 5270584879248448392L;

	private JLabel jlabTxt;
	private JButton btnOk;

	// the rules of game 2
	private String txt = 
			"<html><span>Welcome in the MASTERMIND!"
			
			+ "<br><br><div align=\"justify\">The game to find out the secret combination that the opponent has created. "
			+ "A combination is made up of a certain number of pawn of color. "
			+ "The number of pawns and colors are chosen by players. "
			+ "The codebreaker tries to guess the combination, in both order and color, "
			+ "within the defined number of turns. Each guess is made by placing "
			+ "a row of code pegs on the decoding board. Once placed, "
			+ "the codemaker provides feedback by placing key pegs"
			+ " in the small holes of the row with the guess. A black key peg"
			+ " is placed for each code peg from the guess which is correct"
			+ " in both color and position. A white key peg indicates the existence"
			+ " of a correct color code peg placed in the wrong position."
			+ "f there are duplicate colours in the guess, they cannot"
			+ " all be awarded a key peg unless they correspond to"
			+ " the same number of duplicate colours in the hidden code."
			
			+ "<br><br>1 - In Challenger mode, you are the codebreaker."
			+ "You have to find out the secret combination "
			+ "that the artificial intelligence has created."
			
			+ "<br><br>2 - In Defender mode, you are the codemaker. "
			+ "The artificial intelligence will try to find out your combination."
			
			+ "<br><br>3 - In Duel mode, you face the artificial intelligence. "
			+ "Each of you will try to find out the secret combination of the other."
			+ "</div><span></html>";
	
	/**
	 * Displays the rules of the game
	 * 
	 * @param parent
	 * 			The main frame in which the application is displayed
	 */
	public G2Rules ( MainFrame parent ) {
		
		super ( parent , "Rules of Crack the code" , true );
				
		setTitle( "Rules of Crack the code" );

		setSize( 600 , 420 );
		
		
		jlabTxt = new JLabel( txt );

		getContentPane( ).add( jlabTxt );
		JPanel pan = (JPanel) getContentPane( );
		
		pan.setBorder( BorderFactory.createEmptyBorder( 10, 5, 10, 5 )) ;

		btnOk = new JButton( "OK" );
		pan.add( btnOk , BorderLayout.SOUTH );
		
		btnOk.addActionListener( new ActionListener( ) {
			
			@Override
			public void actionPerformed( ActionEvent ev ) {
				
				dispose();
				
			}
		});
		
		setLocationRelativeTo( parent );
	}
}
