package org.example;

public class ItemYear {

    int month;
    Long amount;
    boolean expense;

    public ItemYear(int month, Long amount, boolean expense) {
        this.month = month;
        this.amount = amount;
        this.expense = expense;
    }

    public Long getAmountYear() {
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
