package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование API https://developer.accuweather.com/")
@Feature(value = "Домашнее задание")
public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("GetLocationTest")
    @Description("GET Location")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Науфаль")
    @Story(value = "Тестирование метода Location")
    void getLocation_search_returnMoscow() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Moscow")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(20000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(24,response.size());
        Assertions.assertEquals("Moscow", response.get(0).getEnglishName());
    }
}
