package DesignATM;

import DesignATM.ATMStates.ATMState;
import DesignATM.ATMStates.IdleState;

public class ATM {

    // made static because "Non-Static" field cannot be referenced from static field
    private static ATM atmObject = new ATM(); //Singleton: eager initialization

    ATMState currentATMState;
    int atmBalance;
    int noOfTwoThousandNotes;
    int noOfFiveHundredNotes;
    int noOfOneHundredNotes;


    private ATM() {
    }

    public static ATM getATMObject() {
        atmObject.setCurrentATMState(new IdleState());
        return atmObject;
    }

    public void setCurrentATMState(ATMState currentATMState) {
        this.currentATMState = currentATMState;
    }

    public ATMState getCurrentATMState() {
        return currentATMState;
    }

    public void setAtmBalance(int atmBalance, int noOfTwoThousandNotes, int noOfFiveHundredNotes, int noOfOneHundredNotes) {
        this.atmBalance = atmBalance;
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
        this.noOfOneHundredNotes = noOfOneHundredNotes;
    }
    public int getAtmBalance() {
        return atmBalance;
    }

    public void deductATMBalance(int amount) {
        atmBalance = atmBalance - amount;
    }
    public int getNoOfOneHundredNotes() {
        return noOfOneHundredNotes;
    }
    public void deductOneHundredNotes(int number) {
        noOfOneHundredNotes = noOfOneHundredNotes - number;
    }
    public int getNoOfFiveHundredNotes() {
        return noOfFiveHundredNotes;
    }
    public void deductFiveHundredNotes(int number) {
        noOfFiveHundredNotes = noOfFiveHundredNotes - number;
    }
    public int getNoOfTwoThousandNotes() {
        return noOfTwoThousandNotes;
    }
    public void deductTwoThousandNotes(int number) {
        noOfTwoThousandNotes = noOfTwoThousandNotes - number;
    }

    public void printCurrentATMStatus(){
        System.out.println("Balance: " + atmBalance);
        System.out.println("2kNotes: " + noOfTwoThousandNotes);
        System.out.println("500Notes: " + noOfFiveHundredNotes);
        System.out.println("100Notes: " + noOfOneHundredNotes);

    }
}

