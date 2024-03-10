package org.max.lesson3.seminar.accuweather;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class GetWeatherFifteenDayTest extends AccuweatherAbstractTest{

    @ParameterizedTest
    @ValueSource(ints = {25, 50, 100})
    void get_ten_day_return_401(int location) {

        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", location)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/{version}/daily/15day/{location}")
                .then()
                .statusCode(401);
    }

}
