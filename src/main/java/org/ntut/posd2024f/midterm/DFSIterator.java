package org.ntut.posd2024f.midterm;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DFSIterator implements Iterator<Item>{
    private Item item;

    public DFSIterator(Item item){
        this.item = item;
    }

    @Override
    public boolean hasNext() {
        return item.iterator().hasNext();
    }

    @Override
    public Item next() {
        if(!item.iterator().hasNext())
            throw new NoSuchElementException("No more element.");

        return item.iterator().next();
    }
    
}
