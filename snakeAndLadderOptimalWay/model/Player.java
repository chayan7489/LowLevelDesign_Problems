package snakeAndLadderOptimalWay.model;
import snakeAndLadderOptimalWay.model.Player;
public class Player implements Cloneable{

    int playerId;
    String playerName;
    boolean isWin;
    int playerPosition;
    int totalMoves;

    public Player(int playerId, String playerName){
        this.playerId = playerId;
        this.playerName = playerName;
        this.isWin = false;
        this.playerPosition = 0;
        this.totalMoves = 0;
    }

    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }
    public int getPlayerId(){
        return playerId;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setWin(boolean isWin){
        this.isWin = isWin;
    }

    public boolean isWin(){
        return isWin;
    }

    public void setPlayerPosition(int playerPosition){
        this.playerPosition = playerPosition;
    }

    public int getPlayerPosition(){
        return playerPosition;
    }

    public void setTotalMoves(Integer totalMoves){
        this.totalMoves = totalMoves;
    }

    public int getTotalMoves(){
        return totalMoves;
    }

    public Player clone(){

        try{
            Player clone = (Player) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            // a class that implements the Cloneable interface is expected to override the
            // Object.clone() method to provide a public clone method that returns a copy of the object.
            return clone;
        } catch (CloneNotSupportedException e){
            // An assertion allows testing the correctness of any assumptions that have been made in the program
            throw new AssertionError();
        }
    }
}
