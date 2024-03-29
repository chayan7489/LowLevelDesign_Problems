package snakeAndLadderOptimalWay.model;

import java.util.HashMap;
import java.util.List;

public class Game {

    HashMap<Integer, Integer> snakes;
    HashMap<Integer, Integer> ladders;
    List<Player> players;

    public Game(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, List<Player> players){
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
    }

    public HashMap<Integer, Integer> getSnakes(){
        return snakes;
    }
    public HashMap<Integer, Integer> getLadders(){
        return ladders;
    }

    public List<Player> getPlayers(){
        return players;
    }
}
