package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DFSIteratorTest {

    @Test
    public void testSingleItem() {
        Book book = new Book("Single Book", 50);
        DFSIterator iterator = new DFSIterator(book);

        assertTrue(iterator.hasNext());
        assertEquals(book, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testBundleWithBooks() {
        Book book1 = new Book("Book 1", 100);
        Book book2 = new Book("Book 2", 150);
        Bundle bundle = new Bundle("Book Bundle");
        bundle.add(book1);
        bundle.add(book2);

        DFSIterator iterator = new DFSIterator(bundle);

        assertTrue(iterator.hasNext());
        assertEquals(bundle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(book1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(book2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNestedBundles() {
        Book book1 = new Book("Book 1", 100);
        Book book2 = new Book("Book 2", 150);
        Book book3 = new Book("Book 3", 200);

        Bundle innerBundle = new Bundle("Inner Bundle");
        innerBundle.add(book2);

        Bundle outerBundle = new Bundle("Outer Bundle");
        outerBundle.add(book1);
        outerBundle.add(innerBundle);
        outerBundle.add(book3);

        DFSIterator iterator = new DFSIterator(outerBundle);

        assertTrue(iterator.hasNext());
        assertEquals(outerBundle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(book1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(innerBundle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(book2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(book3, iterator.next());
        assertFalse(iterator.hasNext());
    }
}