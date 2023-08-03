package com.travel.assistance.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.travel.assistance.model.City;
import com.travel.assistance.model.Country;
import com.travel.assistance.model.ExchangeRate;
import com.travel.assistance.model.GdpPerCapita;
import com.travel.assistance.model.Indicator;
import com.travel.assistance.model.Rate;
import com.travel.assistance.model.Weather;

public class JsonUtils {

	public static GdpPerCapita getGdpPerCapita(String data) throws JSONException {
		JSONObject object = new JSONArray(data).getJSONArray(1).getJSONObject(0);
		JSONObject jsonIndicator = object.getJSONObject("indicator");
		Indicator indicator = new Indicator();
		indicator.setId(jsonIndicator.getString("id"));
		indicator.setValue(jsonIndicator.getString("value"));

		JSONObject jsonCountry = object.getJSONObject("country");
		Country country = new Country();
		country.setId(jsonCountry.getString("id"));
		country.setValue(jsonCountry.getString("value"));

		GdpPerCapita gdpPerCapita = new GdpPerCapita();
		gdpPerCapita.setCountry(country);
		gdpPerCapita.setIndicator(indicator);
		gdpPerCapita.setValue(Double.parseDouble(object.getString("value")));
		gdpPerCapita.setYear(object.getString("date"));
		gdpPerCapita.setCountryiso3code(object.getString("countryiso3code"));

		return gdpPerCapita;
	}

	public static City getWeather(String data) throws JSONException {
		JSONObject object = new JSONObject(data);
		JSONObject jsonObject = object.getJSONObject("main");

		Weather weather = new Weather();
		weather.setCurrentTemperature(jsonObject.getString("temp"));
		weather.setMinTemperature(jsonObject.getString("temp_min"));
		weather.setMaxTemperature(jsonObject.getString("temp_max"));
		weather.setHumidity(jsonObject.getString("humidity"));

		City city = new City();
		city.setName(object.getString("name"));

		JSONObject jsonSys = object.getJSONObject("sys");

		city.setCountryCode(jsonSys.getString("country"));

		city.setWeather(weather);

		return city;
	}

	@SuppressWarnings("rawtypes")
	public static ExchangeRate getExchangeRate(String data) throws JSONException {
		JSONObject object = new JSONObject(data);
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase(object.getString("base"));
		JSONObject jsonObject = object.getJSONObject("rates");
		Collection<Rate> rates = new ArrayList<Rate>();
		for (Iterator iterator = jsonObject.keys(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String value = jsonObject.getString(key);
			Rate rate = new Rate();
			rate.setId(key);
			rate.setValue(value);
			rates.add(rate);
		}
		exchangeRate.setRates(rates);
		return exchangeRate;
	}

}
