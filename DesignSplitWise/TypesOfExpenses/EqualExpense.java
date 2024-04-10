package DesignSplitWise.TypesOfExpenses;

import DesignSplitWise.TypesOfSplits.EqualSplit;
import DesignSplitWise.User;
import DesignSplitWise.TypesOfSplits.Split;
import DesignSplitWise.ExpenseMetadata;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(){

    }
    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }

        return true;
    }
}
