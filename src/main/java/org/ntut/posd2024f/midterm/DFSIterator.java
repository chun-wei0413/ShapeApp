package org.ntut.posd2024f.midterm;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DFSIterator implements Iterator<Item>{
    private Item item;

    public DFSIterator(Item item) {
        this.item = item;
    }
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Item next() {
        throw new NoSuchElementException("No more element.");
    }
}
