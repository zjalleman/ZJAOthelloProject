/**
 * Zachary Alleman
 * CSCI 373.002 Artificial Intelligence
 * Othello Assignment
 * 
 */
package othello;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Game class
 * main class
 * takes all inputs, manages player info and initializes board.
 */
public class Game {
    public static final int ME = 1;
    public static final int OPPONENT = -1;
    public static final int BORDER = -2;
    public static final int EMPTY = 0;
    public static final int BLACK = -1;
    public static final int WHITE = 1;
    
    private static int myColor; //1 for white; -1 for black
    public static int oppColor;
    public static Board gameBoard;
    public static int currentPlayer; //1 for ME; -1 for OPPONENT
    
    static double timeAllocation[] = {0.015, 0.015, 0.015, 0.015, 0.025, 0.025, 0.025, 0.025, 0.025, 0.025,
                                    0.048,  0.048, 0.048, 0.048, 0.048, 0.048, 0.050, 0.051, 0.052, 0.053,
                                    0.044,  0.045, 0.049, 0.049, 0.049, 0.051, 0.053, 0.055, 0.057, 0.059,
                                    0.060, 0.060, 0.061, 0.062, 0.063, 0.064, 0.065, 0.065, 0.065, 0.065,
                                    0.167, 0.168, 0.169, 0.169, 0.171, 0.172, 0.173, 0.175, 0.180, 0.180,
                                    0.181, 0.187, 0.196, 0.199, 0.220, 0.220, 0.220, 0.220, 0.220, 0.220,
                                    0.220, 0.250, 0.250, 0.250, 0.250, 0.250, 0.250, 0.250, 0.250, 0.250
                                  };
    public int timeRemaining; //declare a variable to keep track of remaining time ..... initialize at beginning of game
    Timer timer;
    public static boolean timeUP;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        while (myColor == 0) { //get color
            System.out.println("C Enter my color. ");
            String inColor = input.readLine();
            //System.out.println(inColor);

            if (inColor.matches("I B")) {
                myColor = BLACK;
                oppColor = WHITE;
                System.out.println("C My color is Black. " + myColor);
                System.out.println("C My Opponent's color is White. " + oppColor);
            } else if (inColor.matches("I W")) {
                myColor = WHITE;
                oppColor = BLACK;
                System.out.println("C My color is White. " + myColor);
                System.out.println("C My Opponent's color is Black. " + oppColor);
            } else {
                System.out.println("C Invalid Entry! Try Again.");
            }
        }
        
        
        gameBoard = new Board(); //initialize board
        System.out.println("C Board at game start!");
        gameBoard.printBoard();
        
        //ready
        if (myColor == BLACK) {
            System.out.println("R B");
            currentPlayer = ME;
            System.out.println("C currentPlayer = " + currentPlayer);
        } else {
            System.out.println("R W");
            currentPlayer = OPPONENT;
            System.out.println("C currentPlayer = " + currentPlayer);
        }
        
        //GET moves
        while (!gameBoard.gameOver()) {
            gameBoard.printBoard();
            
            //Get my move
            if (currentPlayer == ME) {
                //alphaBeta(Board currentBoard, int ply, int playerColor, double alpha, double beta, int maxDepth)
                Board testBoard = new Board(gameBoard);
                Move myMove = testBoard.alphaBeta(testBoard, 0, myColor, Double.MIN_VALUE, Double.MAX_VALUE, 8);
                if (myMove.moveStr.length > 1) {
                    //System.out.println("C Move Value: " + myMove.value);
                    System.out.println(myMove.moveStr[0] + " " + myMove.moveStr[1] + " " + myMove.moveStr[2]);
                    gameBoard.applyMove(myColor, myMove);
                } else if (myMove.moveStr.length == 1) {
                    System.out.println(myMove.moveStr[0]);
                } else {
                    System.out.println("C Error!!!");
                }
                
                
                //gameBoard.printBoard();
                
                
                //LinkedList<String> myMoveList;
                //myMoveList = gameBoard.generateMoves(myColor);
                //System.out.println("C " + myMoveList.toString());
                /*if (myMoveList.isEmpty()) { //if cant move pass
                    if (myColor == BLACK) {
                        System.out.println("B");
                    } else {
                        System.out.println("W");
                    }
                } else {
                    Random rng = new Random();
                    int moveNum = rng.nextInt(myMoveList.size());
                    Move myMove = new Move(myMoveList.get(moveNum));
                    System.out.println(myMoveList.get(moveNum));
                    gameBoard.applyMove(myColor, myMove);
                    myMoveList.clear();
                    
                }
                myMoveList.clear();
                System.out.println("C " + myMoveList.toString());*/
                
            
            //Get opponents move
            } else {
                Move oppMove = new Move(input.readLine());
                if (oppMove.moveStr.length == 3) {
                    gameBoard.applyMove(oppColor, oppMove);
                } else if (((oppMove.moveStr[0].matches("B") && oppColor == BLACK) || 
                        (oppMove.moveStr[0].matches("W") && oppColor == WHITE)) && 
                        oppMove.moveStr.length == 1) {
                    System.out.println("C Opponent passes");
                } else if (oppMove.moveStr.length == 1 && 
                        oppMove.moveStr[0].contains(Integer.toString(gameBoard.evaluate(BLACK)))) {
                    gameBoard.endGame();
                    break;
                } else {
                    System.out.println("C Invalid Entry! Try Again.");
                    currentPlayer = -1 * currentPlayer;
                }
                
            }
            //myMoveList.clear();
            //gameBoard.applyMove(currentPlayer, move);
            gameBoard.printBoard();
            currentPlayer = -1 * currentPlayer;//switch players
        }
        System.out.println("C GAME OVER!");
        System.out.println(gameBoard.evaluate(BLACK));
        System.exit(1);
    }
}














