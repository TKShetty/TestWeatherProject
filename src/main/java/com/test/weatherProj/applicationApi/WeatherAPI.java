package com.test.weatherProj.applicationApi;

import static com.test.weatherProj.api.Route.WEATHER;

import java.util.HashMap;

import com.test.weatherProj.api.RestResource;
import com.test.weatherProj.utils.DataLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WeatherAPI {

	public static Response getCityWeatherAPIResponse(String cityName) {
		HashMap<String, String> queryParamsMap = new HashMap<String, String>();
		DataLoader dataLoader = DataLoader.getInstance();
		queryParamsMap.put("q", cityName);
		queryParamsMap.put("appid", dataLoader.getPropertyValue("AppId"));
		queryParamsMap.put("units", dataLoader.getPropertyValue("Units"));
		return RestResource.get(WEATHER, queryParamsMap);
	}

	public static String getCityWeatherTemp(Response response) {
		String temp=null;
		JsonPath jsonPath= new JsonPath(response.asString());
		temp=jsonPath.getString("main.temp");		
		return temp;		
	}
}
