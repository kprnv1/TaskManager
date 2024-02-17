package org.example;

public class ItemMonth {
    String name;
    boolean expense;
    int quantity;
    long price;
    public ItemMonth(String name, boolean expense, int quantity, long price) {
        this.name = name;
        this.expense = expense;
        this.quantity = quantity;
        this.price = price;
    }

    long getTotal() {
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
