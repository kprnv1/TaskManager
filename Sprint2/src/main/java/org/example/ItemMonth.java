package org.example;

public class ItemMonth {
    protected final String name;
    protected final boolean expense;
    protected final int quantity;
    protected final long price;
    protected ItemMonth(String name, boolean expense, int quantity, long price) {
        this.name = name;
        this.expense = expense;
        this.quantity = quantity;
        this.price = price;
    }

    protected long getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "ItemMonth{" +
                "name='" + name + '\'' +
                ", expense=" + expense +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
