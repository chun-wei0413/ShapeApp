package org.ntut.posd2024f.midterm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DFSIterator implements Iterator<Item> {
    private List<Item> items;
    private int index;

    public DFSIterator(Item root) {
        index = 0;
        items = new ArrayList<>();
        dfs(root);
    }

    public void dfs(Item root) {
        if(root == null) {
            return;
        }

        items.add(root);

        Iterator<Item> it = root.iterator();

        while(it.hasNext()){
            dfs(it.next());
        }
    }

    @Override
    public boolean hasNext() {
        return index < items.size();
    }

    @Override
    public Item next() {
        if(index >= items.size()){
            throw new NoSuchElementException("No more element.");
        }

        return items.get(index++);
    }
}