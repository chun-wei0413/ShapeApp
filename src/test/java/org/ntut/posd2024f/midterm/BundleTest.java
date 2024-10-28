package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BundleTest {
    @Test
    public void test_bundle(){
        Bundle bundle = new Bundle("test bundle");
        assertEquals(0, bundle.size());
    }

    @Test
    public void test_nulltitle_bundle(){
        assertThrows(IllegalArgumentException.class, () -> new Bundle(""));
    }

    @Test
    public void test_bundle_gettitle(){
        Bundle bundle = new Bundle("test bundle");
        assertEquals("test bundle", bundle.getTitle());
    }

    @Test
    public void test_bundle_add(){
        Book b1 = new Book("b1", 100);
        Book b2 = new Book("b2", 100);
        Bundle bundle = new Bundle("bundle");
        bundle.add(b1);
        bundle.add(b2);

        assertEquals(2, bundle.size());
    }
}
