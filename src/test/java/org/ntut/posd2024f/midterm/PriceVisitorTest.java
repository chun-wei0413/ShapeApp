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
    public void test_visitDisCountItem(){

    }
}
