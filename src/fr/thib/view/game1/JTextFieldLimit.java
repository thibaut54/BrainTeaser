
package fr.thib.view.game1;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 * Creates a limit to the number of character of a JTextField
 */
class JTextFieldLimit extends PlainDocument {

	private static final long serialVersionUID = -7259607798408779462L;

	private int limit;

	JTextFieldLimit( int limit ) {
		super();
		this.limit = limit;
	}

//	JTextFieldLimit( int limit , boolean upper ) {
//		super();
//		this.limit = limit;
//	}

	public void insertString( int offset , String str , AttributeSet attr ) throws BadLocationException {
		if ( str == null )
			
			return;

		if ( ( getLength() + str.length() ) <= limit ) {
			
			super.insertString( offset , str , attr );
		}
	}
}