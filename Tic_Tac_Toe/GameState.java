public class GameState {

	private char[][] board;
	private int alpha;
	private int beta;
	private int staticValue;
	private boolean player;
	private int emptyCell;
	private GameState[] nextMoves;
	
	public GameState() {
		board = new char[3][3];
		for( int i = 0; i < 3; i++) {
			for( int j = 0; j < 3; j++) {
				board[i][j] = '.';
			}
		}
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
		staticValue = Integer.MIN_VALUE;
		player = true;
		emptyCell = 9;
		
	}
	
	public GameState[] getNextMoves() {
		return nextMoves;
	}
	public char[][] getBoard(){
		return board;
	}
	
	public GameState[] moveInBoard(){
		nextMoves = new GameState[emptyCell];		
		if( this.emptyCell == 0) {
			return this.nextMoves;
		}
		
		int index = 0;
		
		for( int i = 0; i < 3; i++) {
			for( int j = 0; j < 3; j++) {
				
				if( this.board[i][j] == '.' ) {
					GameState nextMove = new GameState();
					nextMove.emptyCell = this.emptyCell - 1;					
					
					for( int k = 0; k < 3; k++) {
						for( int m = 0; m < 3; m++) {
							nextMove.board[k][m] = this.board[k][m];
						}
					}
					nextMove.player = !(this.player);
					if( this.player == true) {
						nextMove.board[i][j] = 'X';
					}
					else {
						nextMove.board[i][j] = 'O';
					}
					this.nextMoves[index] = nextMove;
					index++;
				}
			}
		}

		return this.nextMoves;
	}
	
	public boolean getPlayer() {
		return player;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public void setAlpha( int newAlpha) {
		this.alpha = newAlpha;
	}
	
	public int getBeta() {
		return beta;
	}
	
	public void setBeta( int newBeta) {
		this.beta = newBeta;
	}
	
	public int getEmptyCell() {
		return emptyCell;
	}
	
	public void setEmptyCell( int newEmptyCell) {
		this.emptyCell = newEmptyCell;
	}
	
	public int getStaticValue() {
		return staticValue;
	}
	
	public void setStaticValue( int value) {
		staticValue = value;
	}
	
	/*
	 * This method returns true if two GameState objects' properties equal.
	 */
	public boolean equals( GameState s){
		
		// Check board situation
		for( int i = 0; i < 3; i++) {
			for( int j = 0; j < 3; j++) {
				if( this.board[i][j] != s.board[i][j]) {
					return false;
				}
			}
		}
		return this.alpha == s.alpha && this.beta == s.beta && this.staticValue == s.staticValue && this.player == s.player;
	}
	
	/*
	 * This method converts State object to string format.
	 */
	public String toString() {
		String str = "";
		for( int i = 0; i < 3; i++) {
			for( int j = 0; j < 3; j++) {
				str += this.board[i][j] + " ";
			}
			str += '\n';
		}
		return str;
	}
	
}

