package ru.praktikum.scooter.steps;

import io.restassured.response.ValidatableResponse;
import ru.praktikum.scooter.model.OrderModel;
import static io.restassured.RestAssured.given;
import static ru.praktikum.scooter.config.Constants.*;

public class Order {
    public ValidatableResponse createOrder(OrderModel orderModel) {
        return given()
                .baseUri(HOST)
                .header("Content-type", "application/json")
                .body(orderModel)
                .when()
                .post(ORDER)
                .then();
    }

    public ValidatableResponse viewOrderList() {
        return given()
                .baseUri(HOST)
                .header("Content-type", "application/json")
                .when()
                .get(ORDER)
                .then();
    }

}