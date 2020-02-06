import java.util.Scanner;

public class XOX {
	
	public static void main(String[] args) {
	
		// Create initial game state
		GameState initialState = new GameState();
		
		// To apply alpha-beta pruning, user should enter 1
		System.out.println( "To apply alpha-beta pruning, please enter 1.");
		int start;
		Scanner scan = new Scanner( System.in);
		start = scan.nextInt();
		
		// After user entered 1, program will call abPruning method and set the nodes' values.
		while( start != 1)
			start = scan.nextInt();	
		int result = abPruning( initialState);
		
		System.out.println( "ALPHA-BETA PRUNING IS APPLIED TO TIC-TAC-TOE GAME!");
		System.out.println( "RESULT AFTER ALPHA-BETA PRUNING IS " + result + ".\n");
		
		
		// PRINT TIE PATH
		GameState cur = initialState;
		
		System.out.println( "To see a complete tie game step by step, please enter 1 for each step.");
		int cont;	// cont is "continue signal" which is entered by user. If cont = 1, program shows next step.
		cont = scan.nextInt();
		while( cont != 1)
			cont = scan.nextInt();	
		
		System.out.println( "INITIAL STATE");
		
		boolean found = false;
		boolean temp;
		int i;
		while( found == false) {
			
			temp = false;
			for( i = 0; i < cur.getNextMoves().length && temp == false; i++) {
				// If current player is maximizer and its child's beta value is 0; print the node, state value, and children's values.
				if( cur.getNextMoves()[i].getBeta() == 0 && cur.getPlayer() == true) {
					System.out.println( cur);
					System.out.println( "State value: " + cur.getNextMoves()[i].getBeta());
					System.out.print( "Child values: ");
					
					for( int j = 0; j < cur.getNextMoves().length; j++) {
						System.out.print( cur.getNextMoves()[j].getBeta() + " ");
					}
					System.out.println( "\n");
					System.out.println( "NOW, MAXIMIXER WILL PLAY ACOORDING TO CHILDREN'S VALUES\n");
					temp = true;
				}
				// If current player is minimizer and its child's alpha value is 0; print the node, state value, and children's values. 
				else if( cur.getNextMoves()[i].getAlpha() == 0 && cur.getPlayer() == false) {
					System.out.println( cur);
					System.out.println( "State value: " + cur.getNextMoves()[i].getAlpha());
					System.out.print( "Child values: ");
					
					for( int j = 0; j < cur.getNextMoves().length; j++) {
						System.out.print( cur.getNextMoves()[j].getAlpha() + " ");
					}
					System.out.println( "\n");
					System.out.println( "NOW, MINIMIZER WILL PLAY ACOORDING TO CHILDREN'S VALUES\n");
					temp = true;
				}
			}
			// Update cur. Since i value is increased extra 1 before we exit the loop, i should be decreased 1. 
			cur = cur.getNextMoves()[i-1];
			
			// CONTROL POINT. In order to continue, user should enter 1.
			cont = scan.nextInt();
			while( cont != 1)
				cont = scan.nextInt();
			
			// If there is one cell left, print this node and its children's static values.
			if( cur.getEmptyCell() == 1) {
				System.out.println( cur);
				System.out.println( "State value: " + cur.getAlpha());
				System.out.print( "Child values: ");
				
				for( int j = 0; j < cur.getNextMoves().length; j++) {
					System.out.print( cur.getNextMoves()[j].getStaticValue() + " ");
				}
				System.out.println( "\n");
				System.out.println( "NOW, MAXIMIXER WILL PLAY ACOORDING TO CHILD'S VALUE\n");
			}
			

			if( cur.getStaticValue() == 0 && cur.getEmptyCell() == 0) {
				System.out.println( "(THIS IS ALSO LAST MOVE)");
				System.out.println( cur);				
				System.out.println( "State Value: " + cur.getStaticValue() );
				found = true;
			}

		}
		System.out.println( "\nA tie game is displayed succesfully! This is the end of the program.");
	}
	
	/*
	 * THIS ALGORITHM IS ADAPTED FROM "https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/"
	 * This recursive method returns maximizer's best possible point by using alpha-beta pruning. 
	 */
	public static int abPruning( GameState node) {
		
		int stVal = isLeafState( node);
		
		// If this is the leaf node, update and return its static value.
		if( stVal != Integer.MIN_VALUE) {
			node.setStaticValue( stVal);
			return node.getStaticValue();
		}
		
		GameState[] nextMoves = node.moveInBoard();
		// If current player is maximizer
		if( node.getPlayer() == true) {
			int best = Integer.MIN_VALUE;
			
			// Recursion for children of current node
			for( int i = 0; i < nextMoves.length; i++ ) {
				int value = abPruning( nextMoves[i] );
				best = Math.max( best, value);
				node.setAlpha( Math.max( node.getAlpha(), best) );
				
				// Alpha beta pruning
				if( node.getBeta() <= node.getAlpha() ) {
					break;
				}
			}
			return best;
		}
		// If current player is minimizer
		else {
			int best = Integer.MAX_VALUE;
			
			// Recursion for children of current node
			for( int i = 0; i < nextMoves.length; i++) {
				int value = abPruning( nextMoves[i]);
				
				best = Math.min( best, value);
				node.setBeta( Math.min( node.getBeta(), best) );
				
				// Alpha beta pruning
				if( node.getBeta() <= node.getAlpha() ) {
					break;
				}
			}
			return best;
		}
		
	}
	
	/*
	 * This method checks current GameState and 
	 * returns 1 if maximizer wins
	 * returns -1 if minimizer wins
	 * returns 0 if there is tie
	 * returns Integer.MIN_VALUE if game continues.
	 */
	public static int isLeafState( GameState s) {
		char[][] board = s.getBoard();
		
		// if maximizer player is winner
		if ( board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X' 
		  || board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X' 
		  || board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X' 
		  || board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X' 
		  || board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X' 
		  || board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X' 
		  || board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X' 
		  || board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X' ) {
			
			return 1;
		}
		
		// if minimizer player is winner
		if ( board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O' 
		  || board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O' 
		  || board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O' 
		  || board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O' 
		  || board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O' 
		  || board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O' 
		  || board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O' 
		  || board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O' ) {
							
			return -1;
		}
		
		// if there is a tie
		if( s.getEmptyCell() == 0) {
			return 0;
		}
		
		// if the game continues
		return Integer.MIN_VALUE;		
	}
}