package DesignVendingMachine.VendingStates.impl;

import DesignVendingMachine.Coin;
import DesignVendingMachine.Item;
import DesignVendingMachine.VendingMachine;
import DesignVendingMachine.VendingStates.State;
import DesignVendingMachine.VendingMachine;

import java.util.List;

//public class HasMoneyState implements State {
//
//    public HasMoneyState(){
//
//        System.out.println("Currently Vending machine is in HasMoneyState");
//    }
//
//    @Override
//    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
//        throw new Exception("you can not click on insert coin button in HasMoney state");
//    }
//
//    @Override
//    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
//        machine.setVendingMachineState(new SelectionState());
//    }
//
//    @Override
//    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
//        System.out.println("Accepted the coin");
//        machine.getCoinList().add(coin);
//    }
//
//    @Override
//    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
//        throw new Exception("you need to click on start product selection button first");
//    }
//
//    @Override
//    public int getChange(int returnChangeMoney) throws Exception {
//        throw new Exception("you can not get change in hasMoney state");
//    }
//
//    @Override
//    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
//        throw new Exception("product can not be dispensed in hasMoney state");
//    }
//
//    @Override
//    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
//        System.out.println("Returned the full amount back in the Coin Dispense Tray");
//        machine.setVendingMachineState(new IdleState(machine));
//        return machine.getCoinList();
//    }
//
//    @Override
//    public void updateInventory(VendingMachine machine, Item item, int codeNumber) throws Exception {
//        throw new Exception("you can not update inventory in hasMoney  state");
//    }
//}

public class HasMoneyState extends State{

    public HasMoneyState(){
        System.out.println("Currently Vending machine is in HasMoneyState");
    }
    public void clickOnStartProductSelectionButton(VendingMachine machine) {
        machine.setVendingMachineState(new SelectionState());
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin)  {
        System.out.println("Accepted the coin");
        machine.getCoinList().add(coin);
    }
    public List<Coin> refundFullMoney(VendingMachine machine) {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoinList();
    }
}

