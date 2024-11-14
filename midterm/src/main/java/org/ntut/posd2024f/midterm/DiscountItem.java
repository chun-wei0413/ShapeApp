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
        StringBuilder sb = new StringBuilder();
        if(check()){
            sb.append("<").append(item.getTitle()).append(">").append(" is on sale! ").append((int)(discount*100)).append("% off!");
            return sb.toString();
            // return "<"+item.getTitle()+">" + " is on sale! " + Integer.valueOf((int)discount*100).toString() + "% off";
        }
        else{
            sb.append("<").append(item.getTitle()).append(">").append(" is on sale! ").append((discount*100)).append("% off!");
            return sb.toString();
            // return "<"+item.getTitle()+">" + " is on sale! " + discount*100 + "% off";
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

    public boolean check(){
        double test = discount * 1000;
        if((test % 10) > 0) {
            return false;
        }
        return true;
    }
}
