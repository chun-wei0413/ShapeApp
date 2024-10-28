package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PriceVisitorTest {
    
    @Test
    public void test_visitBook(){
        Book book = new Book("test", 100);
        
        PriceVisitor visitor = new PriceVisitor();

        book.accept(visitor);

        assertEquals(100.0, visitor.getResult());
    }

    @Test
    public void test_visitBundle(){
        Bundle bundle = new Bundle("bundle");
        Book b1 = new Book("b1", 200);
        Book b2 = new Book("b2", 500);
        PriceVisitor visitor = new PriceVisitor();
        bundle.add(b1);
        bundle.add(b2);
        bundle.accept(visitor);

        assertEquals(700.0, visitor.getResult());
    }
}
