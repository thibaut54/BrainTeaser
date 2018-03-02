package fr.thib.exception;


public class TooManyPiecesException extends Exception{
	
	private static final long serialVersionUID = -7664455824234138388L;

	public TooManyPiecesException( String s ) {
		super( s );
	}
}
