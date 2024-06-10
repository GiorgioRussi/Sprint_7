package ru.praktikum.scooter.Orders;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.model.OrderModel;
import ru.praktikum.scooter.steps.Order;

import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderParamTest {

    Order order = new Order();
    Faker faker = new Faker();
    OrderModel orderModel;

    @Before
    public void setUp(){
        orderModel = new OrderModel();
        orderModel.firstName = faker.name().firstName();
        orderModel.lastName = faker.name().lastName();
        orderModel.address = faker.address().streetAddress();
        orderModel.metroStation = faker.number().numberBetween(1, 10);
        orderModel.phone = faker.phoneNumber().phoneNumber();
        orderModel.rentTime = faker.number().numberBetween(1, 6);
        orderModel.setDeliveryDate("2024-06-06");
        orderModel.comment = faker.funnyName().name();
    }
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
            orderModel.setColor(color);
             order
                     .createOrder(orderModel)
                     .statusCode(201)
                     .body("track", notNullValue());


    }
}
