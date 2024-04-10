package DesignSplitWise.TypesOfExpenses;

import DesignSplitWise.TypesOfSplits.ExactSplit;
import DesignSplitWise.User;
import DesignSplitWise.TypesOfSplits.Split;
import DesignSplitWise.ExpenseMetadata;

import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(){

    }
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }

        if (totalAmount != sumSplitAmount) {
            return false;
        }

        return true;
    }
}
