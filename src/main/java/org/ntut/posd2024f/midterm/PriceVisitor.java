package org.ntut.posd2024f.midterm;

import java.util.Iterator;

public class PriceVisitor implements ItemVisitor{
    private double total;

    public PriceVisitor(){
        this.total = 0;
    }

    @Override
    public void visitBook(Book book) {
        total += book.getPrice();
    }

    @Override
    public void visitBundle(Bundle bundle) {
        Iterator<Item> it = bundle.iterator();
        while(it.hasNext()){
            it.next().accept(this);
        }
    }

    @Override
    public void visitDiscountItem(DiscountItem discountItem) {
        // discount = (1 - discountItem.getDiscount()) * discount;
        discountItem.getItem().accept(this);
        total *= (1 - discountItem.getDiscount());
    }

    @Override
    public Object getResult() {
        return Double.valueOf(total);
    }
}
