package org.ntut.posd2024f.shapes;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;


public class NullIteratorTest {
    @Test
    public void test_nullIterator_hasNext() {
        NullIterator nullIterator = new NullIterator();
        assertFalse(nullIterator.hasNext());
    }

    @Test
    public void test_nullIterator_next() {
        NullIterator nullIterator = new NullIterator();
        assertThrows(NoSuchElementException.class, () -> nullIterator.next());
    }
}
