package com.tdd.test_double;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderProcessorTest {


    /**
     * Chapter 4.3.1
     * Typical usage of test double.
     * Fake.
     * The "Fake" is one of test doubles.
     * Compare it with mock in OrderProcessorEasyMockTest.
     * */
    @Test
    public void testOrderProcessorWithMockObject(){
        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float discount = 10.0f;
        float exceptedBalance = initialBalance - listPrice * (1 - discount / 100);
        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in action", listPrice);
        OrderProcessor processor = new OrderProcessor();
        //A fake implementation of PricingService.
        PricingService service = new PricingServiceTestDouble(discount);
        processor.setPricingService(service);
        processor.process(new Order(customer, product));

        assertEquals(exceptedBalance, customer.getBalance(), 0.001f);

    }


}
