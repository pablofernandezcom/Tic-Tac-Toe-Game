/**
 * 
 * @author tmaule
 *This is the TicTacToe Board class, which provides a 'playing field' and is
 *initialized by TicTacToe Game. 
 *
 */
public class TicTacToeBoard {
	
	/*
	 * Below are public variables, an integer and an array of
	 * areas, to provide a representation of the game board and
	 * its size, respectively.
	 */
	char[][] board;
	static int size = 3;

	/**
	 * This exception is thrown when the user or computer
	 * tries to mark a spot that is already taken. It simply
	 * prints out "That space is taken"
	 * @author tmaule
	 *
	 */
	public class SpaceTakenException extends Exception {
		private static final long serialVersionUID = 1L;
		}
	
	/**
	 * This method takes no parameters and returns nothing; 
	 * it simply creates an array of arrays, with dimensions
	 * three by three, and fills it with space characters.
	 */
	public void initialize(){
		this.board = new char[3][3];
			for (int i=0;i<3;i++){
				for (int j=0;j<3;j++){
					board[i][j]=' ';
				}
			}
		
	}
	
	
	/**
	 * Called for side effects alone, printboard() takes
	 * no parameters and simply prints the tictactoe board
	 * by printing a variety of strings, and iterating 
	 * through the matrix. 
	 */
	public void printBoard(){
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

	/**
	 * Checks if a space is open. Parameters are row,
	 * an integer, column, an integer. Returns 'true' 
	 * if the integers are between 0 and 2, and 
	 * the space they reference is open.
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isOpen(int row, int column){
		if ((row > 3) || (column > 3)){
			return false;
		}
		return ' ' == this.board[row][column];
	}
	
	
	/**
	 * Marks a space.
	 * It checks if the space is within bounds, and if not returns 'false' so 
	 * that the human or computer picks another row/column pair. Next, it
	 * checks if the space indicated is taken, and throws a SpaceTakenException
	 * if it is. Otherwise, the method marks the indicated space with the 
	 * symbol for the current player ('X' or '0')
	 * @param row
	 * @param column
	 * @param mark
	 * @return
	 * @throws SpaceTakenException
	 */
	public boolean markSpace(int row, int column, char mark) throws SpaceTakenException{
		if ((row>3)||(column>3)){
			System.out.println("Out of bounds!");
			return false;
		} else if (board[row][column]!=' '){
			System.out.println("That space is taken!");
			throw new SpaceTakenException();
		} else {
		board[row][column] = mark;
		}
		return true;
	}
	
	/**
	 * Checks to see if the game is tied, and 
	 * returns true if no spaces are unmarked.
	 * @return
	 */
	public boolean isTied(){
		for (int i =0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (isOpen(i,j)){
					return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * Checks to see if a given player has won.
	 * Parameter: mark, 'x' or 'o', the mark of the player who may have won
	 * Returns true if the player with the given mark has won.
	 * Checks for vertical, horizontal, and diagonal 'wins.'
	 * @param mark
	 * @return
	 */
	public boolean isWonBy(char mark){
		for (int i=0; i <size; i++){
			char[] row={' ',' ',' '};
			for (int j=0; j<size; j++){
				row[j] = board[i][j];
			}
			if (match(row, mark)){
				return true;
				}
		}
		
		for (int i=0; i <size; i++){
			char[] column = {' ',' ',' '};
			for (int j=0;j<size;j++){
				column[j] = board[j][i];
			}
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
	
	/**
	 * Returns true if the given list contains only the given mark
	 * Parameters include: spaces, a list of characters; mark, a 
	 * character to match
	 * @param spaces
	 * @param mark
	 * @return
	 */
	public static boolean match(char[] spaces, char mark){
		for (int s = 0; s < 3; s++){
			if (spaces[s]!= mark){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Unused main statement, since this class is not meant to be run alone,
	 * only through TicTacToeGame.java
	 * @param args
	 */
    public static void main(String[] args) {}
}
