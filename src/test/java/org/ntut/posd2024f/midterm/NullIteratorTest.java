package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class NullIteratorTest {

    @Test
    public void test_hasNext(){
        NullIterator it = new NullIterator();

        assertFalse(it.hasNext());
    }

    @Test
    public void test_next(){
        NullIterator it = new NullIterator();

        assertThrows(NoSuchElementException.class, () -> it.next());
    }
}
