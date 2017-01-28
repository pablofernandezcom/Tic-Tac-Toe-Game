/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package TicTacToe;

//import java.io.IOException;
//import java.util.Random;
import java.util.InputMismatchException;
import java.util.Scanner;

//import TicTacToeBoard.SpaceTakenException;

/**
 *
 * @author pablofernandez
 */
public class TicTacToeGame  {
    
    static Scanner reader = new Scanner(System.in);

	/**
     * Play the game
     */
    public static void play() throws TicTacToeBoard.SpaceTakenException{
        
        boolean isHumanTurn = (Math.random() < 0.5);
        TicTacToeBoard board = new TicTacToeBoard();
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
        	user_input.next();
        }
        //int empty = reader.nextInt(); 
        
        while(true) {
            if(isHumanTurn) {
                humanTurn(board);
            }
            else {
                computerTurn(board);
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
    }
    
    /**
     * Gets numeric input
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
     * Gets a row and a column from the human player and marks an "x" accordingly. 
     * @param board 
     */
    public static void humanTurn(TicTacToeBoard board) throws TicTacToeBoard.SpaceTakenException{
      System.out.println("It's your turn.");  
      while(true){
          int row = getInput("row", board.getSize());
          int column = getInput("column", board.getSize());
                  
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
     * Marks an "0" in a randomly chosen space.
     * @param board 
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
            /*try{
                System.out.println("The computer marks " + Integer.toString(row+1) +","+ Integer.toString(column+1));
                board.markSpace(row, column,'0');
                return;
            
            catch( SpaceTakenException) {
                 //continue;
            }
        	finally {
        	}}*/
       
    } 
    


    /**
     *
     * @param args the command line arguments
     * @throws SpaceTakenException 
     */
    public static void main(String[] args) throws TicTacToeBoard.SpaceTakenException {
    	play();
    }
    
}

