package org.ntut.posd2024f.midterm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DFSIterator implements Iterator<Item>{
    private List<Item> items;
    private Item item;

    public DFSIterator(Item item) {
        items = new ArrayList<>();
        this.item = item;
    }
    
    @Override
    public boolean hasNext() {
        if(items.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Item next() {
        throw new NoSuchElementException("No more element.");
    }
}
