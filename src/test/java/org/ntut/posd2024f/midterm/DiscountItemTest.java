package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DiscountItemTest {

    @Test
    public void test_valid_discountitem() {
        DiscountItem discountItem = new DiscountItem(new Book("test", 100), 0.1);
    }

    @Test
    public void test_nulltitle_discountitem() {
        Book book = new Book("test", 100);
        assertThrows(IllegalArgumentException.class, () -> new DiscountItem(book, -0.1));
    }

    @Test
    public void test_discountitem_gettitle(){
        Book book = new Book("test", 100);
        DiscountItem discountItem = new DiscountItem(book, 0.1);
        assertEquals(0.1, discountItem.getDiscount());
        assertEquals("<test> is on sale! 10.0% off", discountItem.getTitle());
    }

    @Test
    public void test_discountitem_getitem(){
        Bundle bundle = new Bundle("bundle");
        Book b1 = new Book("b1", 100);
        Book b2 = new Book("b2", 100);
        bundle.add(b1);
        bundle.add(b2);
        DiscountItem discountItem = new DiscountItem(bundle, 0.1);

        assertEquals("bundle" ,discountItem.getItem().getTitle());
    }

    @Test
    public void test_discountitem_getdiscount(){
        Book book = new Book("test", 100);
        DiscountItem discountItem = new DiscountItem(book, 0.1);

        assertEquals(0.1, discountItem.getDiscount(), 0.01);
    }
}
