package snakeAndLadderOptimalWay;

import snakeAndLadderOptimalWay.model.Player;
import snakeAndLadderOptimalWay.service.SnakeLadderService;
import snakeAndLadderOptimalWay.model.Game;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SnakeLadder {
    public static void main(String[] args){

        HashMap<Integer, Integer> ladders = new HashMap<>();
        ladders.put(1, 7);
        ladders.put(5, 20);
        ladders.put(30, 50);
        ladders.put(60, 80);

        HashMap<Integer, Integer> snakes = new HashMap<>();
        snakes.put(10, 1);
        snakes.put(25, 3);
        snakes.put(34, 7);
        snakes.put(78, 2);

        Player player = new Player(1, "Chayan");
        //  singletonList() method of java.util.Collections class is used to return an immutable list
        //  containing only the specified object. The returned list is serializable.
        //  This list will always contain only one element thus the name singleton list.
        //  When we try to add/remove an element on the returned singleton list, it would give UnsupportedOperationException.
        List<Player> players = Collections.singletonList(player);
        Game game = new Game(snakes, ladders, players);
        SnakeLadderService snakeLadderService = new SnakeLadderService(game);
        System.out.println(snakeLadderService.findOptimalPath());
    }
}
