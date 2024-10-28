package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BookTest {
    
    @Test
    public void test_valid_book() {
        Book book = new Book("test", 100);
    }

    @Test
    public void test_nulltitle_book() {
        assertThrows(IllegalArgumentException.class, () -> new Book("", 100));

    }

    @Test
    public void test_negative_price_book() {
        assertThrows(IllegalArgumentException.class, () -> new Book("test", -100));

    }

    @Test
    public void test_book_gettitle(){
        Book book = new Book("test", 100);
        assertEquals("test", book.getTitle());
    }

    @Test
    public void test_book_getprice(){
        Book book = new Book("test", 100);
        assertEquals(100, book.getPrice());
    }
}
