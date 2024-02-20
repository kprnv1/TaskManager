package org.example;

public class ItemYear {

    protected final int month;
    protected final Long amount;
    protected  final boolean expense;

    protected ItemYear(int month, Long amount, boolean expense) {
        this.month = month;
        this.amount = amount;
        this.expense = expense;
    }

    protected final Long getAmountYear() {
        return amount;
    }

    @Override
    public String toString() {
        return "ItemYear{" +
                "month='" + month + '\'' +
                ", amount=" + amount +
                ", expense=" + expense +
                '}';
    }
}
