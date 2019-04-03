package org.homeunix.thecave.buddi.model.impl;

import org.homeunix.thecave.buddi.model.BudgetCategoryType;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BudgetPeriod {
    private final Date startDate;
    private final BudgetCategoryType type;
    private final Date endDate;
    private Period period;

    public BudgetPeriod(BudgetCategoryType type, Date date) {
        this.type = type;
        startDate = type.getStartOfBudgetPeriod(date);
        endDate = type.getEndOfBudgetPeriod(date);
        period = new Period(startDate, endDate);
    }

    private BudgetPeriod nextBudgetPeriod() {
        return new BudgetPeriod(type, type.getStartOfNextBudgetPeriod(startDate));
    }

    public Date getStartDate() {
        return startDate;
    }

    public long getDayCount() {
        return period.getDayCount();
    }

    public Period getPeriod() {
        return period;
    }

    /**
     * Returns a list of BudgetPeriods, covering the entire range of periods
     * occupied by startDate to endDate.
     *
     * @param endBudgetPeriod
     * @return
     */
    public List<BudgetPeriod> getBudgetPeriods(BudgetPeriod endBudgetPeriod) {
        List<BudgetPeriod> budgetPeriods = new LinkedList<>();

        BudgetPeriod current = this;

        while (current.startDate.before(endBudgetPeriod.endDate)) {
            budgetPeriods.add(current);
            current = current.nextBudgetPeriod();
        }

        return budgetPeriods;
    }
}
