package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void test_Item_gettitle() {
        Item book = new Book("test", 100);
        assertEquals("test", book.getTitle());
    }

    @Test
    public void test_Item_add_exception() {
        Item b1 = new Book("b1", 100);
        Item b2 = new Book("b2", 100);

        assertThrows(BookStoreException.class, () -> b1.add(b2));
    }

    @Test
    public void test_Item_iterator() {
        Item b1 = new Book("b1", 100);
        Iterator<Item> it = b1.iterator();

        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        
    }
}
