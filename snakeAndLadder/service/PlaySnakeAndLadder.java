package snakeAndLadder.service;


import snakeAndLadder.model.Dice;
import snakeAndLadder.model.Entities;
//import snakeAndLadder.model.PairPosition;

import java.util.HashMap;


public class PlaySnakeAndLadder {
    //HashMap<String, PairPosition> playerHistory;
    HashMap<String, Integer> playerLatestPosition;
    Entities entities;
    Dice dice;
    public PlaySnakeAndLadder(int N){
      //playerHistory = new HashMap<>();
        playerLatestPosition = new HashMap<>();
        entities = Entities.getInstance();
        dice = new Dice(N);
    }
    public String PlayGame() {

        initializePlayersStartValue();

        int i=-1;
        do {
            i++; // ith Players playing
            if(i >= entities.getPlayers().size()){
                // this means new cycle of turn will start again for all the players participating in the game
                i=0;
            }
            StringBuilder str = new StringBuilder(); // To print output
            String playerName = entities.getPlayers().get(i);
            str.append(playerName);
            int diceNumber = dice.getNumberOfDice();
            int endPosition  = playerLatestPosition.get(playerName) + diceNumber;
            String sl="";

            // After the dice roll, if a piece is supposed to move outside position 100, it does not move.
            if(checkFordiceNumberGreaterThan100(endPosition)) {
                str.append(" rolled a ").append(diceNumber);
                str.append(" and moved from ").append(playerLatestPosition.get(playerName));
                if(entities.getSnakes().get(endPosition)!=null){
                    // Captures snake
                    sl = " after Snake dinner" ;
                    playerLatestPosition.put(playerName,entities.getSnakes().get(endPosition));
                }else{
                    if(entities.getLadders().get(endPosition)!=null){
                        // up ladder
                        sl = " after Ladder ride ";
                        playerLatestPosition.put(playerName,entities.getLadders().get(endPosition));
                    }else{
                        // move normally
                        playerLatestPosition.put(playerName,endPosition);
                    }
                }
                str.append(" to ").append(playerLatestPosition.get(playerName));
                str.append(sl);
            }
            // System.out.println(str); ==== this will also get printed
            System.out.println(str.toString());
        } while (!isPlayerWon(entities.getPlayers().get(i)));

        return entities.getPlayers().get(i);
    }
    private boolean isPlayerWon(String player){
        return playerLatestPosition.get(player) == 100;
    }
    private boolean checkFordiceNumberGreaterThan100(int endPostion){
        return endPostion<=100;
    }

    // setting up initial position of all players to "0"
    private void initializePlayersStartValue(){
        for(int i=0;i<entities.getPlayers().size();i++){
            playerLatestPosition.put(entities.getPlayers().get(i),0);
        }
    }
}