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

    @Test
    public void test_visitDisCountItem(){
        DiscountItem discountItem = new DiscountItem(new Book("test", 100), 0.2);
        PriceVisitor visitor = new PriceVisitor();

        discountItem.accept(visitor);

        assertEquals(80.0, visitor.getResult());
    }

    @Test
    public void test_visitDisCountItem_with_Bundle(){
        Bundle bundle = new Bundle("bundle");
        Book b1 = new Book("b1", 200);
        Book b2 = new Book("b2", 500);
        PriceVisitor visitor = new PriceVisitor();
        bundle.add(b1);
        bundle.add(b2);

        DiscountItem discountItem = new DiscountItem(bundle, 0.2);

        discountItem.accept(visitor);

        assertEquals(560.0, visitor.getResult());
    }

    @Test
    public void test_visitComplexItem(){
        Bundle L1 = new Bundle("L1");
        Bundle L2 = new Bundle("L2");
        Bundle L3 = new Bundle("L3");


        Book b1 = new Book("b1", 200);
        Book b2 = new Book("b1", 200);
        Book b3 = new Book("b3", 300);
        Book b4 = new Book("b4", 400);
        Book b5 = new Book("b5", 500);

        L3.add(b4);
        L3.add(b5);

        DiscountItem innerd = new DiscountItem(L3, 0.5);

        L2.add(innerd);
        L2.add(b3);

        DiscountItem outterd = new DiscountItem(L2, 0.5);

        L1.add(b1);
        L1.add(b2);
        L1.add(outterd);

        PriceVisitor visitor = new PriceVisitor();

        L1.accept(visitor);

        assertEquals(775.0, visitor.getResult());
    }
}
