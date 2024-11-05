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
        double previousTotal = total; // 保存進入該 DiscountItem 前的總價
        discountItem.getItem().accept(this); // 對內部 item 使用 visitor 計算未折扣金額
        total = previousTotal + (total - previousTotal) * (1 - discountItem.getDiscount()); // 將折扣應用於該層
    }

    @Override
    public Object getResult() {
        return Double.valueOf(total);
    }
}
