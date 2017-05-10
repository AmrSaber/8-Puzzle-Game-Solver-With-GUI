package pkg8.puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Amr Saber
 * 
 * Describes board states, used in the A* search only
 * implemets coparator that copare the total weight of the states (cost + hurestic)
 */
public class State implements Comparable<State>{
    
    private final byte board[];     //stat's board
    private final int cost;         //the cost needed to reach this state
    private final int weight;       //the total weight of this state
    
    public State(byte b[], int _cost){
        this.board = b;
        cost = _cost;
        weight = cost + hurestic();
    }
    
    public byte[] getBoard(){
        return this.board;
    }
    public int getCost(){
        return this.cost;
    }
    
    //caculates the hurestic of this board using manhaten distance
    private int hurestic(){
        int h = 0;
        for(int i = 0 ; i < board.length ; ++i){
            if(board[i] == 0) continue;
            int dr = Math.abs(i/3 - (board[i]-1)/3);
            int dc = Math.abs(i%3 - (board[i]-1)%3);
            h += dr + dc;
        }
        return h;
    }
    
    //generates and returns all the possible states from this state
    public ArrayList<State> getNextStates(){
        ArrayList<State> states = new ArrayList<>();
                
        //try every move, if a move changes the board, then it's a valid move :D
        for(BoardControl.MOVES move : BoardControl.MOVES.values()){
            byte newBoard[] = this.board.clone();
            BoardControl.move(newBoard, move);
            if(!Arrays.equals(this.board, newBoard)){
                states.add(new State(newBoard, this.cost + 1));
            }
        }
        return states;
    }

    //compares the states using the total weight
    @Override
    public int compareTo(State o) {
        return this.weight - o.weight;
    }
    
}
