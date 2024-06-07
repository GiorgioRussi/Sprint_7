package ru.praktikum.scooter.steps;

import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.praktikum.scooter.config.Constants.*;

public class Order {
    public ValidatableResponse createOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        return given()
                .baseUri(HOST)
                .header("Content-type", "application/json")
                .body("\n" +
                        "\n" +
                        "{\n" +
                        "    \"firstName\": \""+firstName+"\",\n" +
                        "    \"lastName\": \""+lastName+"\",\n" +
                        "    \"address\": \""+address+"\",\n" +
                        "    \"metroStation\": "+metroStation+",\n" +
                        "    \"phone\": \""+phone+"\",\n" +
                        "    \"rentTime\": "+rentTime+",\n" +
                        "    \"deliveryDate\": \""+deliveryDate+"\",\n" +
                        "    \"comment\": \""+comment+"\",\n" +
                        "    \"color\": [\n" +
                        "        \""+color+"\"\n" +
                        "    ]\n" +
                        "}\n" +
                        "\n")
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