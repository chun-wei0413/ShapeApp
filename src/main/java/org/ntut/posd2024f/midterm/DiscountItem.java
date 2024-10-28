package org.ntut.posd2024f.midterm;

import java.util.Iterator;

public class DiscountItem implements Item{
    private Item item;
    //between 0-1
    private double discount;

    public DiscountItem(Item item, double discount) {
        if(discount <= 0 || discount >= 1){
            throw new IllegalArgumentException("The discount should be between 0 and 1.");
        }
        this.item = item;
        this.discount = discount;
    }

    @Override
    public String getTitle() {
        String s = "";

        if((getDiscount()*100) >= 0) {
            int discountInt = (int)getDiscount()*100;
            if(item instanceof Book){
                return "<"+item.getTitle()+">" + " is on sale! " + discountInt + "% off";
            }
            else{
                Iterator<Item> it = item.iterator();
    
                while(it.hasNext()){
                   s += "<"+item.getTitle()+">" + " is on sale! " + discountInt + "% off\n";
                }
                return s;
            }
        }  else {
            double discountDouble = getDiscount()*100;
            if(item instanceof Book){
                return "<"+item.getTitle()+">" + " is on sale! " + discountDouble + "% off";
            }
            else{
                Iterator<Item> it = item.iterator();
    
                while(it.hasNext()){
                   s += "<"+item.getTitle()+">" + " is on sale! " + discountDouble + "% off\n";
                }
                return s;
            }
        }

    }

    public Item getItem(){
        return item;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public <T> void accept(ItemVisitor<T> visitor) {
        visitor.visitDiscountItem(this);
    }
}
