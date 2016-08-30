/**
 * Zachary Alleman
 * CSCI 373.002 Artificial Intelligence
 * Othello Assignment
 * 
 */
package othello;

public class Move {
    String[] moveStr;
    double value;
    
    
    public Move(String m) {
        moveStr = m.split(" ");
        /*for (int i = 0; i < moveStr.length; i++) {
            System.out.print(moveStr[i]);
        }*/
    }
    
    public Move() {
        
    }
}
