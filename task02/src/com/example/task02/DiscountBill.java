package com.example.task02;

public class DiscountBill extends Bill {
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount + "%";
    }

    @Override
    public long getPrice() {
        double discounted = super.getPrice() * (1.0 - discount / 100.0);
        return Math.round(discounted);
    }

    public long getAbsoluteDiscount() {
        return super.getPrice() - getPrice();
    }
}
