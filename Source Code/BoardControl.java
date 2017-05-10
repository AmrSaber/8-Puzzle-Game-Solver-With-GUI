package pkg8.puzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import javax.swing.Timer;

/**
 * @author Amr Saber
 * BoardControl Class
 * Contains all the methods that manipulates the board
 */
public class BoardControl{
    
    public static enum MOVES{UP, DOWN, RIGHT, LEFT};
    public static enum SPEED{SLOW, MEDIUM, FAST};
    private int timerSpeed = 500;
    public static final byte[] GOAL = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    private byte[] current = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    private boolean solving = false;
    
    public boolean isSolving(){
        return this.solving;
    }
    
    public byte[] getCurrentBoard(){
        return current.clone();
    }
    
    public void setCurrentBoard(byte[] b){
        this.current = b;
    }
    
    public void setTimerSpeed(SPEED speed){
        switch(speed){
            case SLOW:
                this.timerSpeed = 700;
                break;
            case MEDIUM:
                this.timerSpeed = 300;
                break;
            case FAST:
                this.timerSpeed = 100;
                break;
        }
    }
    
    //handles the tiles' press
    //figures out the tile's position relative to the blank and moves accordingly
    public void tilePressed(int btn){
        int blank = getBlankIndex(current);
        if(btn == blank-1){
            move(current, MOVES.LEFT);
        }else if(btn == blank+1){
            move(current, MOVES.RIGHT);
        }else if(btn == blank+3){
            move(current, MOVES.DOWN);
        }else if(btn == blank-3){
            move(current, MOVES.UP);
        }
    }
    
    //make a move on the given board, changes the given board
    //if the move is invalid, do nothing
    //Note that the move is according to the blank, i.e. the blank moves UP or DOWN and so on
    public static void move(byte[] board, MOVES toMove){
        int blank = getBlankIndex(board);
        if(blank == -1) return;  //impossible, but just to be sure
        switch(toMove){
            case UP:
                if(blank/3 != 0) swap(board, blank, blank-3);
                break;
            case DOWN:
                if(blank/3 != 2) swap(board, blank, blank+3);
                break;
            case RIGHT:
                if(blank%3 != 2) swap(board, blank, blank+1);
                break;
            case LEFT:
                if(blank%3 != 0) swap(board, blank, blank-1);
                break;
        }
    }
    
    public boolean isSolved(){
        return Arrays.equals(this.current, this.GOAL);
    }
    
    //resets the board
    public void resetBoard(){
        for(int i = 0 ; i < current.length-1 ; ++i) current[i] = (byte)(i+1);
        current[current.length - 1] = 0;
    }
    
    //generates a random, solvable board and makes it the current board
    public void randomizeBoard(){
        byte board[];
        while(!isSolvable(board = getRandomBoard()));
        current = board;
    }
    
    //makes a state at random, not necessarly solvable
    private byte[] getRandomBoard(){
        boolean f[] = new boolean[current.length];
        byte board[] = new byte[current.length];
        Random rand = new Random();
        
        //randomizes each element and make sure no element is repeated
        for(int i = 0 ; i < current.length ; ++i){
            byte t;
            while(f[t = (byte)rand.nextInt(9)]);
            f[t] = true;
            board[i] = t;
        }
        return board;
    }
    
    //checks if the given state is solvable or not
    private boolean isSolvable(byte board[]){
        //inversion counter
        int inv = 0;
        for(int i = 0 ; i < board.length ; ++i){
            if(board[i] == 0) continue;
            for(int j = i+1 ; j < board.length ; ++j){
                //wrong precedence, count an inversion
                if(board[j] != 0 && board[i] > board[j]) ++inv;
            }
        }
        
        //the board is solvable if the number of inversions is even
        return (inv % 2 == 0);
    }
    
    //returns the index of the blank element in the given board, -1 if not found (impossible case)
    public static int getBlankIndex(byte[] board){
        for(int i = 0 ; i < board.length ; ++i) if(board[i] == 0) return i;
        return -1;
    }
    
    //swaps 2 elemets in the given board
    //if the swap is impossible (index out of range), do nothing
    public static void swap(byte[] board, int i, int j){
        try{
            byte iv = board[i];
            byte jv = board[j];
            board[i] = jv;
            board[j] = iv;
        }catch(ArrayIndexOutOfBoundsException ex){
            //if i or j is out of range, do nothing
        }
    }
    
    //used for debugging :D
    public static void print(byte[] b){
        for(int i = 0 ; i < b.length ; ++i)
                System.out.print(b[i] + " ");
            System.out.println("");
    }
    
    public void solve(GUI gui, Solvers.SOLVE_METHOD method){
        
        Map<String, byte[]> parent = null;
        
        this.solving = true;
        
        long time = System.nanoTime();
        switch(method){
            case A_STAR:
                parent = Solvers.aStar(getCurrentBoard().clone());
                break;
            case DFS:
                parent = Solvers.dfs(getCurrentBoard().clone());
                break;
        }
        
        time = (System.nanoTime() - time) / 1000000;
        
        //use backtracking like technique to get the moves to be made
        //solution states (not moves) are saved into the stack in order to be executed
        Stack<byte[]> nextBoard = new Stack<>();
        nextBoard.add(GOAL.clone());
        while(!Arrays.equals(nextBoard.peek(), this.current))
            nextBoard.add(parent.get(make(nextBoard.peek())));        
        nextBoard.pop();
        
        String status = String.format("<html>%d ms<br/>%d moves<br/>%d expanded nodes</html>", time, nextBoard.size(), Solvers.times);
        gui.setStatus(status);
        
        //start a timer to make a move every given time until the board is solved
        new Timer(this.timerSpeed, new ActionListener(){
            private Stack<byte[]> boards;
            public BoardControl bc;
            
            //gives the timer the stack of states, the gui and the board controller
            //and disables the whole GUI untill finished
            public ActionListener me(Stack<byte[]> stk, BoardControl _bc){
                this.boards = stk;
                this.bc = _bc;
                return this;
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //if the stack is empty, close enable the GUI and stop the timer
                if(boards.empty() || isSolved()){
                    BoardControl.this.solving = false;
                    ((Timer)e.getSource()).stop();
                    return;
                }
                
                //set the current board to the given state and update the GUI
                bc.setCurrentBoard(boards.pop());
                gui.drawBoard();
            }
        }.me(nextBoard, this)).start();    //start the timer right away
    }
    
    //takes an array of byte and makes it into a string and returns the string
    //used for the hashing, NEVER HASH AN ARRAY IN JAVA
    private String make(byte[] arr){
        String str = "";
        for(int i = 0 ; i < arr.length ; ++i){
            str += String.valueOf(arr[i]);
        }
        return str;
    }
    
}
