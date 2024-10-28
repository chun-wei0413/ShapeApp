package org.ntut.posd2024f.midterm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bundle implements Item{
    private String title;
    private List<Item> bundles;

    public Bundle(String title) {
        if(title.equals("")){
            throw new IllegalArgumentException("The bundle should have a title.");
        }
        this.title = title;
        bundles = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void add(Item item){
        bundles.add(item);
    }

    public int size() {
        return bundles.size();
    }

    @Override
    public Iterator<Item> iterator(){
        return bundles.iterator();
    }

    @Override
    public <T> void accept(ItemVisitor<T> visitor) {
        visitor.visitBundle(this);
    }
}
