package tictactoe2;

public class TicTacToeBoard {
	
	static int size = 3;
	//insert exception here
	public class SpaceTakenException extends Exception {
		/**
		 * @return 
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		}

	public char[][] board;
	
	public void initialize(){
		//initializes the board to all spaces (empty)
		// do we need to declare this ???? int size = 3;
		this.board = new char[3][3];
			for (int i=0;i<3;i++){
				for (int j=0;j<3;j++){
					board[i][j]=' ';
				}
			}
		
	}
	
	public int getSize(){
		//Gets the board size. The board is always square.
		//Returns: The number of rows and columns on the board.
		return 3; //I should be returning the int size, but 3 is easier?
	}
	
	public void printBoard(){
		//Prints the TicTacToe board. 
		//Returns: Nothing. Called for side effects.
		//System.out.println("about to print");
		String seperator = "+---+---+---+";
		System.out.println("\n"+seperator);
		String line;
		for (int i=0; i<3; i++){
			line = "| ";
			for (int j=0; j<3; j++){
				line = line + this.board[i][j];
				line = line + " | ";
			}
			System.out.println(line);
			System.out.println(seperator);	
		}
		System.out.println("\n");
	}
	
	public boolean isOpen(int row, int column){
		/*
		 * Checks if a space is open. Parameters:
		 * 	row, an integer between 0 and size-1
		 * 	column, an integer between 0 and size-1
		 * Returns: True if the space is open 
		 */
		if ((row > 3) || (column > 3)){
			return false;
		}
		return ' ' == this.board[row][column];
	}
        
	public boolean markSpace(int row, int column, char mark) throws SpaceTakenException{
		/*
		 * Marks a space. If the space is taken, throws a SpaceTakenException.
		 * Parameters:
		 * 	row, an integer between 0 and size-1
		 * 	column, an integer between 0 and size-1
		 * 	mark, a character, either 'x' or 'o'
		 * Returns:
		 * 	Nothing, called for its side effects
		 */
		//checkBounds(row, column);
		if ((row>3)||(column>3)){
			System.out.println("Out of bounds!");
			return false;
		} else if (board[row][column]!=' '){
			System.out.println("SpaceTaken throw an exception here");
			throw new SpaceTakenException();
		} else {
		board[row][column] = mark;
		}
		return true;
	}
	
	public boolean isTied(){
		/*
		 * Checks if the game is tied
		 * Returns: True if no spaces are unmarked
		 */
		for (int i =0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (isOpen(i,j)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isWonBy(char mark){
		/*
		 * Checks if a given player has won. 
		 * Parameter: mark, 'x' or 'o,' the mark of the player who may have won
		 * Returns: True if the player with the given mark has won.
		 */
		for (int i=0; i <size; i++){
			char[] row={' ',' ',' '};
			for (int j=0; j<size; j++){
				row[j] = board[i][j];
			}
			//System.out.println("Row we're checking: " + row[0] + row[1] + row[2]);
			if (match(row, mark)){
				return true;
				}
		}
		
		for (int i=0; i <size; i++){
			char[] column = {' ',' ',' '};
			for (int j=0;j<size;j++){
				column[j] = board[j][i];
			}
			//System.out.println("Column we're checking: " + column[0] + column[1] + column[2]);
			if (match(column, mark)){
				return true;
				}
		}
		
		char[] diagonal_a = {board[0][0],board[1][1],board[2][2]};
		if (match(diagonal_a,mark)){
			return true;
		}
		
		char[] diagonal_b = {board[2][0],board[1][1],board[0][2]};
		if (match(diagonal_b,mark)){
			return true;
		}
		
		return false;
	}
	
	public boolean match(char[] spaces, char mark){
		/*
		 * Returns true if the given list contains only the given mark.
		 * Parameters: 
		 * 	spaces, a list of characters
		 * 	mark, a character to match
		 */
		
		for (int s = 0; s < 3; s++){
			if (spaces[s]!= mark){
				return false;
			}
		}
		return true;
	}
	
    public static void main(String[] args) {
        // TODO code application logic here
    }
}