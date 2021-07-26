package com.test.weatherProj.applicationApi;

import static com.test.weatherProj.api.Route.WEATHER;

import java.util.HashMap;

import com.test.weatherProj.api.RestResource;
import com.test.weatherProj.utils.APIConstants;
import com.test.weatherProj.utils.DataLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WeatherAPI {

	private static String Q = "q";
	private static String APPID = "appid";
	private static String UNITS = "units";

	public static Response getCityWeatherAPIResponse(String cityName) {
		HashMap<String, String> queryParamsMap = new HashMap<String, String>();
		DataLoader dataLoader = DataLoader.getInstance();
		queryParamsMap.put(Q, cityName);
		queryParamsMap.put(APPID, dataLoader.getPropertyValue(APIConstants.APPID));
		queryParamsMap.put(UNITS, dataLoader.getPropertyValue(APIConstants.UNITS));
		return RestResource.get(WEATHER, queryParamsMap);
	}

	public static String getCityWeatherTemp(Response response) {
		String temp = null;
		JsonPath jsonPath = new JsonPath(response.asString());
		temp = jsonPath.getString(APIConstants.API_TEMPERATURE);
		return temp;
	}
}
