/**
 * Zachary Alleman
 * CSCI 373.002 Artificial Intelligence
 * Othello Assignment
 * 
 */
package othello;

import java.util.LinkedList;

public class Board {
    int a = 1;
    int b = 2;
    int c = 3;
    int d = 4;
    int e = 5;
    int f = 6;
    int g = 7;
    int h = 8;
    int roundCt = 0;
    int[][] board = new int[10][10];
    int[][] backUpBoard = new int[10][10];
    //convert numbers to letters
    String[] numLetConv = {"x", "a", "b", "c", "d", "e", "f", "g", "h"};
    boolean forfeit = false;
    
    public Board() { //initialize board
        System.out.println("C Initializing Board...");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i < 1 || i > 8 || j < 1 || j > 8) {
                    board[i][j] = Game.BORDER;
                } else {
                    board[i][j] = Game.EMPTY;
                }
            }
        }
        board[d][4] = Game.WHITE;
        board[e][5] = Game.WHITE;
        board[e][4] = Game.BLACK;
        board[d][5] = Game.BLACK;
        backUpBoard = board;
    }
    
    public Board(Board oldBoard) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j] = oldBoard.board[i][j];
            }
        }
        backUpBoard = this.board;
    }
    
    
    //generates possible moves for player
    public LinkedList<Move> generateMoves(int color) {
        //String[] moveList = new String[64];
        LinkedList<String> moveList = new LinkedList<String>();
        //backUpBoard = board;
        moveList.clear();
        
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board[i][j] == color) {
                    genMovesHelper(moveList, i, j, color);
                }
            }
        }
        
        
        /*if (color == Game.BLACK) {
            System.out.println("C Possible moves for Black: " + moveList.toString());
        } else {
            System.out.println("C Possible moves for White: " + moveList.toString());
        }*/
        
        LinkedList<Move> moveList2 = new LinkedList<Move>();
        
        for (int i = 0; i < moveList.size(); i++) {
            moveList2.add(new Move(moveList.get(i)));
        }
        
        return moveList2;
    }
    
    private LinkedList<String> genMovesHelper(LinkedList<String> moveListHelp, int y, int x, int pColor) {
        int xTemp = x;
        int yTemp = y;
        boolean checkDone = false;
        int oppStoneCt = 0;
        int oColor = -1 * pColor;
        
        //check to the west for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            xTemp--;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the east for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            xTemp++;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the north for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            yTemp--;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the south for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            yTemp++;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the north east for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            yTemp--;
            xTemp++;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the north west for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            yTemp--;
            xTemp--;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the south west for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            yTemp++;
            xTemp--;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //reset for next pass
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the south east for moves
        while (board[yTemp][xTemp] != Game.BORDER && !checkDone) {
            yTemp++;
            xTemp++;
            if (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt != 0) {
                if (pColor == Game.BLACK && !moveListHelp.contains("B " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("B " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else if (pColor == Game.WHITE && !moveListHelp.contains("W " + numLetConv[xTemp] + " " + yTemp)) {
                    moveListHelp.add("W " + numLetConv[xTemp] + " " + yTemp);
                    checkDone = true;
                } else {
                    checkDone = true;
                }
            } else if (board[yTemp][xTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[yTemp][xTemp] == Game.BORDER) || 
                    (board[yTemp][xTemp] == Game.EMPTY && oppStoneCt == 0) ||
                    (board[yTemp][xTemp] == pColor)) {
                checkDone = true;
            }
        }
        
        //moveList.add(numLetConv[x] + " " + y);
        return moveListHelp;
    }
    
    //alpha-beta
    public Move alphaBeta(Board currentBoard, int ply, int playerColor, double alpha, double beta, int maxDepth) {
        if (ply >= maxDepth) {
            Move returnMove = new Move();
            returnMove.value = currentBoard.evaluate(playerColor);
            
            //System.out.println("C return: " + returnMove.moveStr[0] + " " + returnMove.moveStr[1] + " " + returnMove.moveStr[2]);
            //System.out.println("C returnMove Value: " + playerColor + ", " + returnMove.value);
            return returnMove;
        } else {
            LinkedList<Move> moves = new LinkedList<Move>();
            moves.clear();
            moves = currentBoard.generateMoves(playerColor);
            if (moves.isEmpty()) {
                if (playerColor == Game.BLACK) {
                    moves.add(new Move("B"));
                } else {
                    moves.add(new Move("W"));
                }
            }
            
            Move bestMove = moves.get(0);
            /*Move bestMove = new Move();
            bestMove.value = -1.0;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).value > bestMove.value) {
                    bestMove = moves.get(i);
                }
            }*/
            
            
            for (Move move : moves) {
                //printBoard();
                //System.out.println("C 320");
                Board newBoard = new Board(currentBoard);
                newBoard.applyMove(playerColor, move);
                //newBoard.printBoard();
                Move tempMove = alphaBeta(newBoard, ply+1, -playerColor, -beta, -alpha, maxDepth);
                move.value = -tempMove.value;
                if (move.value > alpha) {
                    bestMove = move;
                    alpha = move.value;
                    if (alpha > beta) {
                        //System.out.println("C best: " + bestMove.moveStr[0] + " " + bestMove.moveStr[1] + " " + bestMove.moveStr[2]);
                        //System.out.println("C bestMove Value: " + playerColor + ", " + bestMove.value);
                        return bestMove;
                    }
                }
            }
            //System.out.println("C 335");
            /*if (bestMove.moveStr.length == 3){
                System.out.println("C best: " + bestMove.moveStr[0] + " " + bestMove.moveStr[1] + " " + bestMove.moveStr[2]);
                System.out.println("C bestMove Value: " + playerColor + ", " + bestMove.value);
            }*/
            
            return bestMove;
        }
    }
    
    
    //pColor is color of current player
    public void applyMove(int pColor, Move move) {
        if (move.moveStr.length > 1) {
            if (pColor == Game.BLACK && move.moveStr[0].matches("B")) {
                applyMoveHelper(pColor, move.moveStr[2], move.moveStr[1]);
            } else if (pColor == Game.WHITE && move.moveStr[0].matches("W")) {
                applyMoveHelper(pColor, move.moveStr[2], move.moveStr[1]);
            } else {
                System.out.println("C ERROR! Move color mismatch!");
            }
        } else {
            //System.out.println("C cant move");
            return;
        }
        
    }
    
    //pColor is color of current player
    private void applyMoveHelper(int pColor, String xStr, String yStr) {
        int changeCt = 0;
        int y = 0;
        int x = Integer.parseInt(xStr);
        while (!numLetConv[y].matches(yStr)) {
            y++;
        }
        //System.out.println(xStr + ", " + yStr + "; " + x + ", " + y);
        
        //apply board changes
        backUpBoard = board;
        if (board[x][y] == Game.EMPTY) {
            board[x][y] = pColor;
        } else {
            if (pColor == Game.BLACK) {
                System.out.println("C ERROR! Move invalid! Black forfeits! GAME OVER!");
            } else {
                System.out.println("C ERROR! Move invalid! White forfeits! GAME OVER!");
            }
            endGame();
            return;
        }
        
        int xTemp = x;
        int yTemp = y;
        boolean checkDone = false;
        int oppStoneCt = 0;
        int oColor = -1 * pColor;
        //check to the west for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            yTemp--;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp][yTemp + oppStoneCt] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the east for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            yTemp++;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp][yTemp - oppStoneCt] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the south for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            xTemp++;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp - oppStoneCt][yTemp] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the north for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            xTemp--;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp + oppStoneCt][yTemp] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the north west for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            xTemp--;
            yTemp--;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp + oppStoneCt][yTemp + oppStoneCt] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the north east for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            xTemp--;
            yTemp++;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp + oppStoneCt][yTemp - oppStoneCt] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the south west for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            xTemp++;
            yTemp--;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp - oppStoneCt][yTemp + oppStoneCt] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        xTemp = x;
        yTemp = y;
        checkDone = false;
        oppStoneCt = 0;
        //check to the south east for changes
        while (board[xTemp][yTemp] != Game.BORDER && !checkDone) {
            xTemp++;
            yTemp++;
            if (board[xTemp][yTemp] == pColor && oppStoneCt != 0) {
                while (oppStoneCt != 0) {
                    board[xTemp - oppStoneCt][yTemp - oppStoneCt] = pColor;
                    changeCt++;
                    oppStoneCt--;
                }
                checkDone = true;
            } else if (board[xTemp][yTemp] == oColor) {
                oppStoneCt++;
            } else if ((board[xTemp][yTemp] == Game.BORDER) || 
                    (board[xTemp][yTemp] == Game.EMPTY) || 
                    (board[xTemp][yTemp] == pColor && oppStoneCt == 0)) {
                checkDone = true;
            }
        }
        
        if (changeCt == 0) {
            board = backUpBoard;
            
            /*if (pColor == Game.BLACK) {
                System.out.println("C ERROR! Move invalid! Black forfeits! GAME OVER!");
            } else {
                System.out.println("C ERROR! Move invalid! White forfeits! GAME OVER!");
            }*/
            //System.out.println(countBlackPieces());
            //endGame();
            
        }
    }
    
    //prints the current gameboard
    public void printBoard() {
        System.out.println("C        a  b  c  d  e  f  g  h       ");
        for (int i = 0; i < board.length; i++) {
            if (i > 0 && i < 9) {
                System.out.print("C  " + i + " ");
            } else {
                System.out.print("C    ");
            }
            
            for (int j = 0; j < board[i].length; j++) {
                //System.out.print(board[i][j]);
                if (board[i][j] == Game.BLACK) {
                    System.out.print(" B ");
                } else if (board[i][j] == Game.WHITE) {
                    System.out.print(" W ");
                } else if (board[i][j] == Game.BORDER) {
                    System.out.print(" * ");
                } else if (board[i][j] == Game.EMPTY) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
    
    public int evaluate(int color) {
        /*int n = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == color) {
                    n++;
                }
            }
        }*/
        int n = this.generateMoves(color).size();
        return n;
    }
    
    public void endGame() {
        forfeit = true;
        int n = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Game.BLACK) {
                    n++;
                }
            }
        }
        System.out.println(n);
    }
    
    public boolean gameOver() {
        int emptyCt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Game.EMPTY) {
                    emptyCt++;
                }
            }
        }
        if (emptyCt == 0 || forfeit) {
            return true;
        } else {
            return false;
        }
    }
}
