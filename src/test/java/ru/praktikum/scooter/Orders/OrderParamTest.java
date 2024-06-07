package ru.praktikum.scooter.Orders;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.steps.Order;

import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderParamTest {

    Order order = new Order();
    private final List<String> color;

    public OrderParamTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {List.of("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("")},
        };
    }


@Test
    public void shouldCreateOrder() {

             order
                     .createOrder("Naruto", "Uzumaki", "Konoha, 142 apt.", 4,"+7 800 355 35 35", 5, "2024-06-09", "Saske, come back to Konoha", color)
                     .statusCode(201)
                     .body("track", notNullValue());


    }
}
