import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author tmaule
 * @author pfernandez
 */
public class TicTacToeGame  {
    
	public int flag = 0;
    static Scanner reader = new Scanner(System.in);
	private Scanner diff;

	/**
	 * Plays the TicTacToe game. play() randomly determines which player (human or computer)
	 * goes first, creates a new Board object, and initializes it as empty. It announces
	 * which player goes first, and then asks the user to press any key and the enter key
	 * to begin the game. Afterwards, the play() function proceeds with the first
	 * turn (calling humanturn or computerturn), checks whether there is a win or tie, 
	 * and then switches players and begins the next turn. The turns alternate until 
	 * there is a win or a tie.
	 * @throws TicTacToeBoard.SpaceTakenException
	 */
    public void play() throws TicTacToeBoard.SpaceTakenException{
        boolean isHumanTurn = (Math.random() < 0.5);
        TicTacToeBoard board = new TicTacToeBoard();
        //TicTacToeAI computer = new TicTacToeAI();
        board.initialize();
        System.out.print("Let's Play Tic Tac Toe! \n");
        System.out.print("You are X and the computer is 0.\n");
        if(isHumanTurn)
        {
            System.out.print("You go first. \n"); 
        }
        else
        {
            System.out.print("The computer goes first. \n"); 
        }         
        String response = null;
        while (response == null){
        	System.out.println("Press any key and 'enter' to begin. ");
        	Scanner user_input = new Scanner(System.in);
        	response = user_input.toString();
        	if (response != null){
        	reader.next();
        }
        
        while(true) {
            if(isHumanTurn) {
                humanTurn(board);
            }
            else {
            	if (flag==1){
                computerTurn(board);
            	} else if (flag==2){
            	//call method to begin difficult version
            	}
            }
            board.printBoard();
            if (isHumanTurn){
            	if (board.isWonBy('X')){
            	System.out.println("The human has won!");
            	break;
            	}
            } else if (!isHumanTurn){
            	if (board.isWonBy('0')){
            	System.out.println("The computer has won!");
            	break;
            	}
            } else if (board.isTied()){
            	System.out.println("It's a tie!");
                break;
            }
            isHumanTurn = !isHumanTurn;
        }    
      System.out.println("=== GAME OVER ===");
      user_input.close();
        }
    }
    
    /**
     * Gets numeric input from the user to determine where
     * to place the marker
     * @param row0rColumn
     * @param size 
     */
    public static int getInput(String row0rColumn, int size) {
        while(true){
            System.out.println("What " + row0rColumn + "? (1-" + size + ")");
            try {
            	int input = reader.nextInt(); 
                if((input<1) || (input>3)) {
                    System.out.println("Please enter a number in the range.");
                    reader.nextLine();
                    continue;
                }
                return input;
            }
            catch(IllegalArgumentException | InputMismatchException a) {
                System.out.println("Please enter an integer.");
                reader.nextLine();

                continue;
            }
        }
    }    
     
    /**
     * Accomplishes and reports the placing of an 'X' mark
     * on the TicTacToe Board in the spot that the human chose.
     * @param board
     * @throws TicTacToeBoard.SpaceTakenException
     */
    public static void humanTurn(TicTacToeBoard board) throws TicTacToeBoard.SpaceTakenException{
      System.out.println("It's your turn.");  
      while(true){
          int row = getInput("row", 3);
          int column = getInput("column", 3);
                  
          try {
            board.markSpace(row-1, column-1, 'X');
            System.out.println("You mark ("+(row)+","+(column)+").");
          	return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The space is out of bounds. Please try again.");
          } catch (TicTacToeBoard.SpaceTakenException e) {
                System.out.println("That space is taken. Please choose another space.");
          }	  
          }
       
    }    
   
    /**
     * The computer randomly selects two integers, representing row and column. Afterwards, the computer tests if 
     * the spot is open. If so, it will mark a '0' there. Otherwise, it will throw a SpaceTakenException, and
     * recalculate new rows and columns until it finds an open space (if there is no open space, a tie will be
     * indicated)
     * @param board
     * @throws TicTacToeBoard.SpaceTakenException
     */
    public static void computerTurn(TicTacToeBoard board) throws TicTacToeBoard.SpaceTakenException{
    	int row;
    	int column;
        while(true){
            row    = (int)(Math.random()*3);
            column = (int)(Math.random()*3);
            System.out.println("The computer wants to mark " + Integer.toString(row+1) +","+ Integer.toString(column+1));
            try {
            	board.markSpace(row, column, '0');
                System.out.println("The computer marks (" + Integer.toString(row+1) +","+ Integer.toString(column+1)+").");
                return;
            } catch (TicTacToeBoard.SpaceTakenException e){
            	continue;
            } 
        }
    } 
    
    public void askLevel(){
    	System.out.println("Which level of difficulty? 1 -> easy, 2 -> Hard");
    	while (flag != 1 && flag != 2){
        	diff = new Scanner(System.in);
        	flag = diff.nextInt();
        	if (flag == 1 || flag == 2){
        		break;
        	}
    	}
    	//diff.close();
    }
    
    /**
     * Main function that runs when the project is run; calls 'play()' to begin playing the game.
     *
     * @param args the command line arguments
     * @throws SpaceTakenException 
     */
    public static void main(String[] args) throws TicTacToeBoard.SpaceTakenException {
    	System.out.println("Let's play Tic Tac Toe! \n");
    	TicTacToeGame Game = new TicTacToeGame();
    	Game.askLevel();
    	Game.play();
    }   
}