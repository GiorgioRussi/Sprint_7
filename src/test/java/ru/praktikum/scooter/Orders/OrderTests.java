package ru.praktikum.scooter.Orders;

import org.junit.Test;
import ru.praktikum.scooter.steps.Order;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;

public class OrderTests {
    Order order = new Order();

    @Test
    public void shouldReturnListOfOrders(){

        order
                .viewOrderList()
                .statusCode(200)
                .body("orders", not(empty()));
    }
}
