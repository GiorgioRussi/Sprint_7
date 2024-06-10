package ru.praktikum.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.scooter.model.CourierModel;
import static io.restassured.RestAssured.given;
import static ru.praktikum.scooter.config.Constants.*;

public class Courier {

    @Step("Создание курьера, /api/v1/courier")
    public ValidatableResponse createCourier(CourierModel courierModel){
        return given()
                .baseUri(HOST)
                .header("Content-type", "application/json")
                .body(courierModel)
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Залогинить курьера, /api/v1/courier/login")
    public ValidatableResponse loginCourier(CourierModel courierModel){
        return given()
                .baseUri(HOST)
                .header("Content-type", "application/json")
                .body(courierModel)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Удалить курьера, /api/v1/courier/{id}")
    public void deleteCourier(CourierModel courierModel){
        given()
                .baseUri(HOST)
                .header("Content-type", "application/json")
                .pathParam("id", courierModel.getId())
                .when()
                .delete(DELETE)
                .then();
    }
}
