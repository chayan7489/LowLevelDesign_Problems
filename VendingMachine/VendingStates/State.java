package DesignVendingMachine.VendingStates;

import DesignVendingMachine.Coin;
import DesignVendingMachine.Item;
import DesignVendingMachine.VendingMachine;

import java.util.List;

//public interface State {
//
//    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception;
//
//    public void insertCoin(VendingMachine machine, Coin coin) throws Exception;
//
//    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception;
//
//    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception;
//
//    public int getChange(int returnChangeMoney) throws Exception;
//
//    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception;
//
//    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception;
//
//    public void updateInventory(VendingMachine machine, Item item, int codeNumber) throws Exception;
//
//}

public abstract class State{
    public void clickOnInsertCoinButton(VendingMachine machine){
        System.out.println("Click on Insert coin button");
    }

    public void insertCoin(VendingMachine machine, Coin coin){
        System.out.println("Insert coin in the machine");
    }

    public void clickOnStartProductSelectionButton(VendingMachine machine){
        System.out.println("Click on product selection button");
    }

    public void chooseProduct(VendingMachine machine, int codeNumber){
        System.out.println("Choose your product");
    }

    public int getChange(int returnChangeMoney){
        return 0;
    }

    public Item dispenseProduct(VendingMachine machine, int codeNumber){
        return null;
    }

    public List<Coin> refundFullMoney(VendingMachine machine){
        return null;
    }

    public void updateInventory(VendingMachine machine, Item item, int codeNumber){
        System.out.println("Updating inventory");
    }
}

