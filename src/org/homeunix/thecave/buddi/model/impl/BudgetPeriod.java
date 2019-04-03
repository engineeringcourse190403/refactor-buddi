package org.homeunix.thecave.buddi.model.impl;

import org.homeunix.thecave.buddi.model.BudgetCategoryType;

import java.util.Date;

public class BudgetPeriod {
    private final Date startDate;
    private final BudgetCategoryType type;
    private final Date endDate;

    public BudgetPeriod(BudgetCategoryType type, Date date) {
        this.type = type;
        startDate = type.getStartOfBudgetPeriod(date);
        endDate = type.getEndOfBudgetPeriod(date);
    }

    @Override
    public boolean equals(Object obj) {
        return startDate.equals(((BudgetPeriod)obj).startDate);
    }

    public BudgetPeriod nextBudgetPeriod() {
        return new BudgetPeriod(type, type.getStartOfNextBudgetPeriod(startDate));
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public BudgetPeriod previousPeriod() {
        return new BudgetPeriod(type, type.getStartOfPreviousBudgetPeriod(startDate));
    }
}
