package org.ntut.posd2024f.midterm;

import java.util.Iterator;

public class DiscountItem implements Item{
    private Item item;
    //between 0-1
    private double discount;

    public DiscountItem(Item item, double discount) {
        if(discount < 0 || discount > 1){
            throw new IllegalArgumentException("The discount should be between 0 and 1.");
        }
        this.item = item;
        this.discount = discount;
    }

    @Override
    public String getTitle() {
        String s = "";
        if(item instanceof Book){
            return "<"+item.getTitle()+">" + " is on sale! " + discount*100 + "% off";
        }
        else{
            Iterator<Item> it = item.iterator();

            while(it.hasNext()){
                s += "<"+ it.next().getTitle()+">" + " is on sale! " + discount*100 + "% off\n";
            }
            return s;
        }
    }

    public Item getItem(){
        return item;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public Iterator<Item> iterator(){
        return item.iterator();
    }

    @Override
    public <T> void accept(ItemVisitor<T> visitor) {
        visitor.visitDiscountItem(this);
    }

}
