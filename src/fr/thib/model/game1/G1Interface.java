package fr.thib.model.game1;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface G1Interface {

	public String getStrDifferentCode();
	public String getStrEqualsCode( );
	public String getStrNoTry( );

	public Code getCodePlayer();
	public Code getCodeComputerTry();
	public Code getCodeComputer( );

	public JLabel [ ] getJlabCodeComputer( );
	public JLabel [ ] getIndicatorField( );
	public JLabel getSolution( );
	public JLabel getResult( );

	public JButton getBtnValidate( );

	public boolean isPlayerWin( );
	
	public void setCodePlayer( Code code );
	public void setCodeComputer( Code code );
	public int getNbTryPlayer( );
	public void activSelector( boolean b );
	public JLabel [ ] getIndicatorField2( );
}
