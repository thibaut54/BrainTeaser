package fr.thib.view.game1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.thib.view.MainFrame;

public class G1Rules extends JDialog{

	
	private static final long serialVersionUID = 5270584879248448392L;

	private JLabel jlabTxt;
	private JButton btnOk;

	private String txt = 
			"<html><span>Welcome in CRACK THE CODE! "
			
			+ "<br><br><div align=\"justify\">You are a very skilled hacker, "
			+ "fighting for peace all arround the world!"
			
			+ "<br><br>1 - In Challenger mode, you have to crack the access code of "
			+ "the nuclear weapons security system of a dangerous country in order to"
			+ "neutralize it. If you fail... you better run. We ALL better run!!!"
			
			+ "<br><br>2 - In Defender mode, you must create an UN-BREA-KA-BLE code so that "
			+ "any hostile hacker can take controle of your country's nuclear weapons."
			+ "If you fail... you better run. Because he will turn over the weapons against you!"
			
			+ "<br><br>3 - In Duel mode, you are fighting against another hostile hacker! "
			+ "But he doesn't want only neutralize your country's nuclear weapons. "
			+ "He wants to trigger it against yourself! You must create a solid security "
			+ "code that he won't find out, and you must break his security code "
			+ "in order to neutralize his system and to locate hime, so that the authorities arrest him."
			+ "</div><span></html>";
	
	/**
	 * Displays the rules of the game
	 * 
	 * @param parent
	 * 			The main frame in which the application is displayed
	 */
	public G1Rules ( MainFrame parent ) {
		
		super ( parent , "Rules of Crack the code" , true );
				
		setTitle( "Rules of Crack the code" );

		setSize( 600 , 420 );
		
		jlabTxt = new JLabel( txt );

		getContentPane( ).add( jlabTxt );
		JPanel pan = (JPanel) getContentPane( );
		
		pan.setBorder( BorderFactory.createEmptyBorder( 10, 5, 10, 5 ) ) ;

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
