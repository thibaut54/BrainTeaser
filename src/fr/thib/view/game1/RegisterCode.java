package fr.thib.view.game1;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import fr.thib.exception.CodeInvalidException;
import fr.thib.model.Config;
import fr.thib.model.game1.Code;
import fr.thib.model.game1.G1Interface;
import fr.thib.view.MainFrame;

public class RegisterCode extends JDialog {

	private static final long serialVersionUID = 3698562245393885347L;

	// field for the code so that it is not seen by anyone
	private JPasswordField txt;
	
	// start button
	private JButton btnStart;
	
	private Border border;
	private String strBord = "ENTER YOUR CODE";
	private boolean codeRegistered = false;


	//----------CONSTRUCTOR----------

	
	/**
	 * Creates a JDialog in which player will input his secret code 
	 * 
	 * @param superParent
	 * 				The JFrame in which the entire applicaiton is displayed
	 * @param parent
	 * 				The direct parent of the JDialog
	 * 				
	 */
	public RegisterCode( MainFrame superParent , G1Interface parent ) {

		super( );
		
		// JDialog setting and layout
		setSize( 420 , 200 );
		setModal( true );
		Container c = getContentPane( );
		c.setLayout( new GridLayout( 2 , 1 ) );

		// creates a field for the code so that it is not seen by anyone,
		// to respect the lillte scenario of the game
		txt = new JPasswordField( );
		// the JTextFieldLimit is used to avoid player to input a code with too many digits
		txt.setDocument( new JTextFieldLimit( Config.getNbDigit( ) ) );
		txt.setFont( new Font( "Calibri" , Font.CENTER_BASELINE , 60 ) );

		border = BorderFactory.createTitledBorder( strBord );
		txt.setBorder( border );

		c.add( txt );

		// start button
		btnStart = new JButton( "REGISTER CODE & START" );

		// add listeners to the button
		btnStart.addActionListener( new BtnListener( this , parent ) );
		btnStart.addKeyListener(  new BtnKeyListener( this , parent ) );
	

		c.add( btnStart );
		
		this.getRootPane().setDefaultButton(btnStart);
		
		// center the JDialog on the main frame
		setLocationRelativeTo( superParent );
		
		// give the focus to the JDialog
		requestFocusInWindow();

		setVisible( true );
	}


	//----------GETTERS----------

	public JPasswordField getTxt( ) {
		return txt;
	}

	public JButton getBtnStart( ) {
		return btnStart;
	}

	public boolean isCodeRegistered( ) {
		return codeRegistered;
	}
	
	

	//----------SETTERS----------

	public void setStrBord( String strBord ) {
		this.strBord = strBord;
	}

	public void setBorder( Border border ) {
		this.border = border;
	}

	public void setCodeRegistered( boolean codeRegistered ) {
		this.codeRegistered = codeRegistered;
	}
	
	
	
	//----------INNER CLASS----------
	
	
	/**
	 * Triggers the btnStart button when player press the Enter key
	 */
	class BtnKeyListener implements KeyListener {
		
		private RegisterCode parent;
		private G1Interface game;

		//----------CONSTRUCTOR----------
		
		public BtnKeyListener( RegisterCode parent , G1Interface game ) {
			this.parent = parent;
			this.game = game;
		}

		@Override
		public void keyPressed( KeyEvent ev ) {
			if (ev.getKeyCode() == KeyEvent.VK_ENTER){
				registerCode( parent , game , ev );
			}
		}

		@Override
		public void keyReleased( KeyEvent ev ) { }

		@Override
		public void keyTyped( KeyEvent ev ) { }
		
	}
	
	class BtnListener implements ActionListener {
		
		private RegisterCode parent;
		private G1Interface game;
		
		//----------CONSTRUCTOR----------
		
		public BtnListener( RegisterCode parent , G1Interface game ) {
			
			this.parent = parent;
			this.game = game;
		}
		
		
		@Override
		public void actionPerformed( ActionEvent ev ) {
			
			registerCode( parent , game , ev );
		}	
	}
	
	
	
	/**
	 * Register the input code in the JDialog
	 * 
	 * @param parent
	 * 			The JDialog
	 * @param game
	 * 			The Jpanel in which the game in displayed
	 * @param ev
	 * 			The event that start the action
	 */
	public void registerCode( RegisterCode parent , G1Interface game , ActionEvent ev ) {
		
		int [ ] intCode;
		
		try {
			
			char [ ] charCode = parent.getTxt( ).getPassword( );
			
			Code.testCodeValidity( charCode );
			
			// If clic on button Start
			if ( ev.getSource( ) == parent.getBtnStart( ) ) {
				
				intCode = new int [ Config.getNbDigit( ) ];
				
				for ( int i = 0; i < intCode.length; i++ ) {
					intCode [ i ] = Character.getNumericValue( charCode [ i ] );
				}
				
				//Store the input code by the player in game1.codePlayer
				game.setCodePlayer( new Code( intCode ) );
				
				//Store a random code in game game1.codeComputer
//				game.setCodeComputer( new Code( ) );
				
				// Chelou à vérifier
				if ( Config.isDev( ) ) {
					game.getSolution( ).setText( 
							"<html>Congrats, you said the dev word!<br> " 
									+ "The computer code is " + game.getCodeComputer( ).toString( ) 
									+ ".<br>" + "Your code is "	+ game.getCodePlayer( ).toString( ) 
									+ ".</html>" );
				}
				
				parent.setCodeRegistered( true );
				
				parent.dispose( );
			}
		}
		
		catch ( CodeInvalidException e ) {
			
			JOptionPane.showMessageDialog( parent , e.getMessage( ) , "Invalid code" ,
					JOptionPane.ERROR_MESSAGE );
		}
	}
	
	
	
	/**
	 * Register the input code in the JDialog
	 * 
	 * @param parent
	 * 			The JDialog
	 * @param game
	 * 			The Jpanel in which the game in displayed
	 * @param ev
	 * 			The key event that start the method
	 */
	public void registerCode( RegisterCode parent , G1Interface game , KeyEvent ev ) {
		
		int [ ] intCode;
		
		try {
			
			char [ ] charCode = parent.getTxt( ).getPassword( );
			
			Code.testCodeValidity( charCode );
			
			// If clic on button Start
			if ( ev.getSource( ) == parent.getBtnStart( ) ) {
				
				intCode = new int [ Config.getNbDigit( ) ];
				
				for ( int i = 0; i < intCode.length; i++ ) {
					intCode [ i ] = Character.getNumericValue( charCode [ i ] );
				}
				
				//Store the input code by the player in game1.codePlayer
				game.setCodePlayer( new Code( intCode ) );
				
				//Store a random code in game game1.codeComputer
				game.setCodeComputer( new Code( ) );
				
				// Chelou à vérifier
				if ( Config.isDev( ) ) {
					game.getSolution( ).setText( 
							"<html>Congrats, you said the dev word!<br> " 
									+ "The computer code is " + game.getCodeComputer( ).toString( ) 
									+ ".<br>" + "Your code is "	+ game.getCodePlayer( ).toString( ) 
									+ ".</html>" );
				}
				
				parent.setCodeRegistered( true );
				
				parent.dispose( );
			}
		}
		
		catch ( CodeInvalidException e ) {
			
			JOptionPane.showMessageDialog( parent , e.getMessage( ) , "Invalid code" ,
					JOptionPane.ERROR_MESSAGE );
		}
	}
	
}


