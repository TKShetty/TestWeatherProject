package com.test.weatherProj.api;

import static com.test.weatherProj.api.Route.BASE_PATH;

import com.test.weatherProj.utils.ConfigLoader;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	static ConfigLoader configLoader = ConfigLoader.getInstance();

	public static RequestSpecification getRequestSpec() {
		String baseURI = configLoader.getPropertyValue("BASE_URI");

		return new RequestSpecBuilder().setBaseUri(baseURI).setBasePath(BASE_PATH).setContentType(ContentType.JSON)
/*				.log(LogDetail.ALL)*/.build();
	}

	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder()./*log(LogDetail.ALL)*/build();
	}
}
