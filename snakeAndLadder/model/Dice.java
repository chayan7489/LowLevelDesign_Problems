package snakeAndLadder.model;

import java.util.Random;

public class Dice {
    private int numberOfDice;
    private static int MIN = 1;
    Random random;
    public Dice(int numberOfDice){
        random = new Random();
        this.numberOfDice = numberOfDice;
    }

    public int getNumberOfDice() {

        // nextInt(int n) is used to get a random number between 0(inclusive) and
        // the number passed in this argument(n), exclusive.
        // (this.numberOfDice - MIN) + 1) = (6 - 1) + 1 = 6. So this will next random number between 0 to 5 and
        // later on we will add +1 to it.
        // "this.numberOfDice" will also work
        return random.nextInt((numberOfDice - MIN) + 1) + 1;
    }
}