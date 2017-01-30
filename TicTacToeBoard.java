/**
 * 
 * 
 *This is the TicTacToe Board class, which provides a 'playing field' and is
 *initialized by TicTacToe Game. 
 *
 */
package tictactoe2;

/**
 * 
 * @author tmaule
 * @author pfernandez
 */

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard {
    
	static int size = 3;
        public char[][] board;
        
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

	/**
	 * This exception is thrown when the user or computer
	 * tries to mark a spot that is already taken. 
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
            	String topguide   = "      1   2   3  ";
		String seperator  = "    +---+---+---+";
                System.out.println("\n" + topguide);
		System.out.println(seperator);
                String line = "";
		for (int i=0; i<3; i++){
                    
                        if(i==0) { line =  "R " + (i+1) + " | "; }
                        if(i==1) { line =  "O " + (i+1) + " | "; }
                        if(i==2) { line =  "W " + (i+1) + " | "; }

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
         * 
         * If on impossible mode, the AI will override any moves if a defense
         * is necessary or a win is possible with the current board state. 
	 * @param row
	 * @param column
	 * @param mark
	 * @return
	 * @throws SpaceTakenException
	 */
	public boolean markSpace(int row, int column, char mark) throws SpaceTakenException{
		if (TicTacToeGame.difficulty==2 && mark=='0'){
			//System.out.println("checking 2");
			if (!nearlyWonBy('X').isEmpty()){
				//System.out.println(nearlyWonBy('X'));
				row = nearlyWonBy('X').get(0);
				column = nearlyWonBy('X').get(1);
			}
			if (!nearlyWonBy('0').isEmpty()){
				//System.out.println(nearlyWonBy('0'));
				row = nearlyWonBy('0').get(0);
				column = nearlyWonBy('0').get(1);
			}		
		}
		if ((row>3)||(column>3)){
			System.out.println("Out of bounds!");
			return false;
		} else if (board[row][column]!=' '){
			System.out.println("SpaceTaken throw an exception here");
			throw new SpaceTakenException();
		} else {
		board[row][column] = mark;
		}
		if (mark=='0'){System.out.println("\033[0;1m" + "The computer marks (" + 
				(row+1) + "," + (column+1) + ")");}
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
        
     /**
	 * Returns true if the given list contains only the given mark
	 * Parameters include: spaces, a list of characters; mark, a 
	 * character to match
	 * @param spaces
	 * @param mark
	 * @return
	 */
        
	public boolean match(char[] spaces, char mark){
		for (int s = 0; s < 3; s++){
			if (spaces[s]!= mark){
				return false;
			}
		}
		return true;
	}
    	
        /**
	* Checks to see if a given player is one move away from victory
	* Parameter: mark, 'x' or 'o', the mark of the player who may win
	* Returns an List of integers that indicate possible wins
	* for every pair of integers from n=0, if 2n is a row, 2n+1 is the
        * corresponding column
	* Checks for vertical, horizontal, and diagonal 'wins.'
	* @param mark
	* @return
	*/    
	public List<Integer> nearlyWonBy(char mark){
			int count;
			List<Integer> locations = new ArrayList<Integer>();		
			for (int i=0; i <3; i++){
				count = 0;
				for (int j=0; j<3; j++){
					if (board[i][j] == mark) {count++;}
				}
				if (count == 2){
					//System.out.print("2!");

					for (int j=0; j<3; j++){
						if (board[i][j] == ' ') {
							locations.add(i);
							locations.add(j);
						}
					}
				}
			}
			
			for (int i=0; i <3; i++){
				count = 0;
				for (int j=0;j<3;j++){
					if (board[j][i] == mark) {count++;}
				}
				if (count == 2){
					//System.out.print("2!");

					for (int j=0; j<3; j++){
						if (board[j][i] == ' ') {
							locations.add(j);
							locations.add(i);
						}
					}
				}
			}
			
			count = 0;
			for (int i=0; i<3; i++){
				if (board[i][i]==mark){count++;}
			}
			if (count == 2){
				//System.out.print("2!");
				for (int j=0; j<3; j++){
					if (board[j][j] == ' ') {
						locations.add(j);
						locations.add(j);
					}
				}
			}
			
			count = 0;
			for (int i=0; i<3; i++){
				if (board[2-i][i]==mark){count++;}
			}
			if (count == 2){
				//System.out.print("2!");

				for (int j=0; j<3; j++){
					if (board[2-j][j] == ' ') {
						locations.add(2-j);
						locations.add(j);
					}
				}
			}
			
			//System.out.println(mark + " "+locations);
			return locations;
		}
	    
	    
        /**
	 * Unused main statement, since this class is not meant to be run alone,
	 * only through TicTacToeGame.java
	 * @param args
	 */
    public static void main(String[] args) {}
}