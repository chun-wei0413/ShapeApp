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
                s += "<"+item.getTitle()+">" + " is on sale! " + discount*100 + "% off\n";
            }
            return s;
        }

        // double d =0;
        // int i =0;
        // boolean f = false;
        // int check = (int)discount * 1000;
        // if((check % 10) > 0){
        //     f = true;
        //     d = discount * 100;
        // } else {
        //     i = (int)discount * 100;
        // }

        // if(f){
        //     if(item instanceof Book){
        //         return "<"+item.getTitle()+">" + " is on sale! " + d + "% off";
        //     }
        //     else{
        //         Iterator<Item> it = item.iterator();

        //         while(it.hasNext()){
        //             s += "<"+item.getTitle()+">" + " is on sale! " + d + "% off\n";
        //         }
        //         return s;
        //     }
        // }else {
        //     if(item instanceof Book){
        //         return "<"+item.getTitle()+">" + " is on sale! " + i + "% off";
        //     }
        //     else{
        //         Iterator<Item> it = item.iterator();

        //         while(it.hasNext()){
        //             s += "<"+item.getTitle()+">" + " is on sale! " + i + "% off\n";
        //         }
        //         return s;
        //     }
        // }

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
