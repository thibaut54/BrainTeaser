package fr.thib.model.game2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.thib.model.Config;

public class PossibleSolution {
	
	private List< byte[] > possibleSolution;
	private byte nbSpot = Config.getNbPiece( );
	private byte nbColor = Config.getNbColor( );
	private byte [ ] combiNb = new byte [ nbSpot ];
	
	
	
	//----------CONSTRUCTOR----------

	/**
	 * 
	 */
	public PossibleSolution( ) {
		this.possibleSolution = new ArrayList<>( );
	}
	
	
	
	//----------METHODS----------
	
	
	/**
	 * Generates all possible combinations of the mastermind,
	 * depending of the difficulty parameters
	 * 
	 * @param index
	 * 			index must be zero
	 */
	public void genAllSolution( byte index ) {
		
		if ( index >= nbSpot ) {
				
			possibleSolution.add( combiNb.clone( ) );
			
			return;
		}

		for ( int i = 0; i < nbColor; i++ ) {
			combiNb [ index ] = ( byte ) i;
			genAllSolution( ( byte ) ( index + 1 ) );
		}
	}


	
	
	/**
	 * randomly picks a combination among every possible solutions
	 * 
	 * @return
	 * 		A randomly chosen combination among every possible solution
	 */
	public byte[] randomChoose( ) {
		int nbSolution = Combi.getPossibleSolution( ).getPossibleSolution( ).size( );
		Random random = new Random( );
		int i = random.nextInt( nbSolution - 1  - 0 + 1 ) + 0;
		return possibleSolution.get( i );
	}


	// ----------GETTERS----------
	
	public List< byte [ ] > getPossibleSolution( ) {
		return possibleSolution;
	}

	
	public byte[] getSolution( byte i ) {
		return possibleSolution.get( i );
	}

	
	public int getNbSolution( ) {
		return this.possibleSolution.size();
	}



	@Override
	public int hashCode( ) {

		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode( combiNb );
		return result;
	}



	@Override
	public boolean equals( Object obj ) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass( ) != obj.getClass( ) )
			return false;
		PossibleSolution other = ( PossibleSolution ) obj;
		if ( !Arrays.equals( combiNb , other.combiNb ) )
			return false;
		return true;
	}
	
	
}
