package DesignSplitWise.Service;

//import com.workattech.splitwise.models.User;
//import com.workattech.splitwise.models.expense.*;
//import com.workattech.splitwise.models.split.PercentSplit;
//import com.workattech.splitwise.models.split.Split;

import DesignSplitWise.ExpenseType;
import DesignSplitWise.TypesOfExpenses.EqualExpense;
import DesignSplitWise.TypesOfExpenses.ExactExpense;
import DesignSplitWise.TypesOfExpenses.Expense;
import DesignSplitWise.TypesOfExpenses.PercentExpense;
import DesignSplitWise.TypesOfSplits.PercentSplit;
import DesignSplitWise.User;
import DesignSplitWise.TypesOfSplits.Split;
import DesignSplitWise.ExpenseMetadata;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, paidBy, splits, expenseMetadata);
            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new PercentExpense(amount, paidBy, splits, expenseMetadata);
            case EQUAL:
                int totalSplits = splits.size();

                //java.lang.Math.round() is used round of the decimal numbers to the nearest value.
                //This method is used to return the closest "long" to the argument, with ties rounding to positive infinity.

                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }

                // added to check the value
//                if(splits.get(0) instanceof Split){
//                   System.out.println(splits.get(0).getAmount());
//                }
                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                // added to check the value
                // System.out.println(splits.get(0).getAmount());
                return new EqualExpense(amount, paidBy, splits, expenseMetadata);
            default:
                return null;
        }
    }
}

