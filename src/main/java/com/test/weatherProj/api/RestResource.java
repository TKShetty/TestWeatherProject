package com.test.weatherProj.api;

import static com.test.weatherProj.api.SpecBuilder.getRequestSpec;
import static com.test.weatherProj.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;

public class RestResource {


    public static Response get(String path, Map<String,String> parametersMap){
        return given(getRequestSpec()).queryParams(parametersMap).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

}
