package fr.thib.model.game2;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public interface G2Interface {

	
	public Combi getCombiComputer( );
	public Combi getCombiComputerTry( );
	public Combi getCombiPlayer( );
	
	public JPanel[] getResult( );
	public JPanel getPanInfo( );
	
	public JLabel getJTextInfo( );
	
	public JButton getBtnValidate( );
	
	public byte getNbTryPlayer( );

	public void setCombiComputer( Combi combi );
	public void activSelector( boolean b );
	public void setCombiComputerTry( Combi combi );
	public void setPlayerWin( boolean b );
}
