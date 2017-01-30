package tictactoe2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tmaule
 * @author pfernandez
 */

    /**
     * The computer randomly selects two integers, representing row and column. Afterwards, the computer tests if 
     * the spot is open. If so, it will mark a '0' there. Otherwise, it will throw a SpaceTakenException, and
     * recalculate new rows and columns until it finds an open space (if there is no open space, a tie will be
     * indicated)
     * @param board
     * @throws TicTacToeBoard.SpaceTakenException
     */

public class TicTacToeAI {
    
    // Moves {row, col} in order of preferences. {0, 0} at top-left corner
    public int[][] preferredMoves = {
         {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
         {0, 1}, {1, 0}, {1, 2}, {2, 1}
    };
  
    public int [] BestMovesHeuristic(TicTacToeBoard board) {
        /**
         * Takes a TicTacToeBoard board object, and uses a heuristic
         * to determine which move is preferred to increase the computer's 
         * chance of winning
         */
        
        // Simulate all that hard thinking!
        try {
            Thread.sleep(1400);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        int row;
    	int column;
        int [] heuristic = {-1,1};
               
        for (int[] move : preferredMoves) {
            row = move[0];
            column = move[1];
        
            if(board.isOpen(row, column)) {
                heuristic[0]=row;
                heuristic[1]=column;
                return heuristic; 
            }   
        }
        
        
        //No preferred move is available. Resort to random. 
        row    = (int)(Math.random()*3);
        column = (int)(Math.random()*3);
        heuristic[0] =row; 
        heuristic[1] =column;
        return heuristic;
   }
  
    public void computerTurnDifficult(TicTacToeBoard board) throws TicTacToeBoard.SpaceTakenException {
        /**
         * computer turn takes a TicTacToe board object, and uses the BestMovesHeuristic
         * to determine the optimal move for the computer to make. The computer attempts to
         * make that move, and throws a SpaceTakenException if it cannot. Returns nothing.
         */
        int [] move;
        int row;
        int column;
        
        while(true){
            move   = BestMovesHeuristic(board);
            row    = move[0];
            column = move[1];
            
            try {
                // The function markSpace can overide this move based on win and loss analysis
                board.markSpace(row, column, '0');
                //System.out.println("\033[0;1m" + "The computer marks (" + Integer.toString(row+1) +","+ Integer.toString(column+1)+").");
                return;
            } catch (TicTacToeBoard.SpaceTakenException e){
            } 
        }
    } 
    
    public void computerTurnEasy(TicTacToeBoard board) throws TicTacToeBoard.SpaceTakenException {
        /**
         * computer turn takes a TicTacToe board object, and uses Random or the BestMovesHeuristic
         * to determine the optimal or random move for the computer to make. The computer attempts to
         * make that move, and throws a SpaceTakenException if it cannot. Returns nothing.
         */
        int [] move;
        int row;
        int column;
        
        while(true){
            boolean useheuristic = (Math.random() < 0.4); // Only use skilled moves 4/10 times
            if(useheuristic)
            {
                move = BestMovesHeuristic(board);
                row    = move[0];
                column = move[1];
            }
            else
            {
                row = (int)(Math.random()*3);
                column = (int)(Math.random()*3);
            }
            
            try {
            	board.markSpace(row, column, '0');
                //System.out.println("\033[0;1m" + "The computer marks (" + Integer.toString(row+1) +","+ Integer.toString(column+1)+")." );
                return;
            } catch (TicTacToeBoard.SpaceTakenException e){
            } 
            
        }
       
    } 
    
}
