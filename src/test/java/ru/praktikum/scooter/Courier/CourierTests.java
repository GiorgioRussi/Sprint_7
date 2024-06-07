package ru.praktikum.scooter.Courier;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.model.CourierModel;
import ru.praktikum.scooter.steps.Courier;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CourierTests {
    Courier courier = new Courier();
    CourierModel courierModel;

    @Before
    public void setUp(){
        courierModel = new CourierModel();
        courierModel.setLogin(RandomStringUtils.randomAlphabetic(5));
        courierModel.setPassword(RandomStringUtils.randomAlphabetic(5));
    }

    @Test
    public void shouldReturnOkTrue() {

        courier
                .createCourier(courierModel)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    public void cannotCreateIdenticalCouriers(){

        courier
                .createCourier(courierModel);
        courier
                .createCourier(courierModel)
                .statusCode(409)
                .body("message", is("Этот логин уже используется"));
    }

    @Test
    public void createCourierWithoutLogin() {

        courierModel.setLogin(null);
        courier
                .createCourier(courierModel)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void createCourierWithoutPassword() {

        courierModel.setPassword("");
        courier
                .createCourier(courierModel)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }




    @Test
    public void shouldReturnId(){

        courier
                .createCourier(courierModel);


        courier
                .loginCourier(courierModel)
                .statusCode(200)
                .body("id", notNullValue());

    }

    @Test
    public void loginCourierWithoutLogin(){

        courier
                .createCourier(courierModel);
        courierModel.setLogin(null);
        courier
                .loginCourier(courierModel)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    public void loginCourierWithoutPassword(){

        courier
                .createCourier(courierModel);
        courierModel.setPassword("");
        courier
                .loginCourier(courierModel)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));

    }

    @Test
    public void loginCourierWithWrongLogin() {

        courier
                .createCourier(courierModel);
        courierModel.setLogin("Boo");
        courier
                .loginCourier(courierModel)
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    public void loginCourierWithWrongPassword() {

        courier
                .createCourier(courierModel);
        courierModel.setPassword("Boo");
        courier
                .loginCourier(courierModel)
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }



    @After
    public void deleteCourier(){
        Integer id = courier.loginCourier(courierModel)
                .extract().body().path("id");
                courierModel.setId(id);
                if(id != null) {
                    courier.deleteCourier(courierModel);
                }
    }
}
