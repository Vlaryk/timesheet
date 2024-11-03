package com.example.timesheet;

public class TaxCalculator {
    private final TaxResolver taxResolver;

    public TaxCalculator(TaxResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    // вычисляем стоимость товара с учетом НДС
    public double getPriceWithTax(double price) {
        double currentPrice = taxResolver.getCurrentTax();
        return price + price * currentPrice;
    }
}
