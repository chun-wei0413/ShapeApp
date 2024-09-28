package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

import org.junit.Test;

public class NullIteratorTest {
    @Test
    public void test_nullIterator_hasNext() throws Exception {
        NullIterator nullIterator = new NullIterator();
        assertFalse(nullIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void test_nullIterator_next() throws Exception {
        NullIterator nullIterator = new NullIterator();
        nullIterator.next();
    }
}
