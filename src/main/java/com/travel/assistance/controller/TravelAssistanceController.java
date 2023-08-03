package com.travel.assistance.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistance.config.RestTemplateConfig;
import com.travel.assistance.model.City;
import com.travel.assistance.model.ExchangeRate;
import com.travel.assistance.model.GdpPerCapita;
import com.travel.assistance.model.TravelAssistanceClass;
import com.travel.assistance.services.ITravelAssistanceService;
import com.travel.assistance.utils.JsonUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/search")
public class TravelAssistanceController {

	@Value("${WORLD_BANK_GDP_API_URL}")
	private String worldBankApiUrl;

	@Value("${OPEN_WEATHER_MAP_API_URL}")
	private String openWeatherApiUrl;

	@Value("${OPEN_WEATHER_MAP_API_KEY}")
	private String openWeatherApiKey;

	@Value("${EXCHANGE_RATE_API_URL}")
	private String exchangeRateApiUrl;

	@Value("${EXCHANGE_RATE_API_KEY}")
	private String exchangeRateApiKey;

	@Autowired
	private RestTemplateConfig restTemplate;

	@Autowired
	private ITravelAssistanceService travelAssistanceService;

	@GetMapping(path = "/city/{cityName}")
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<TravelAssistanceClass> searchDestinationCity(@PathVariable String cityName) {

		try {
			final String weatherResponse = restTemplate.getForObject(
					openWeatherApiUrl + "?q=" + cityName + "&appid=" + openWeatherApiKey + "&units=metric",
					String.class);

			final City city = JsonUtils.getWeather(weatherResponse);

			final String gdpResponse = restTemplate.getForObject(
					worldBankApiUrl + city.getCountryCode() + "/indicator/NY.GDP.PCAP.CD?format=json", String.class);
			GdpPerCapita gdpPerCapita = JsonUtils.getGdpPerCapita(gdpResponse);

			String currencyCode = travelAssistanceService.getCurrencyByCountryCode(city.getCountryCode());

			// "&base=" + currencyCode +
			final String exchangeRateResponse = restTemplate.getForObject(exchangeRateApiUrl + "?access_key="
					+ exchangeRateApiKey + "&symbols=USD,EUR,GBP,CAD,JPY,ZAR," + currencyCode, String.class);

			final ExchangeRate exchangeRate = JsonUtils.getExchangeRate(exchangeRateResponse);

			TravelAssistanceClass travelAssistanceClass = new TravelAssistanceClass();
			travelAssistanceClass.setGdpPerCapita(gdpPerCapita);
			travelAssistanceClass.setCity(city);
			travelAssistanceClass.setExchangeRate(exchangeRate);

			return ResponseEntity.ok(travelAssistanceClass);
		} catch (JSONException jsonException) {
			return ResponseEntity.ok(null);
		}

	}

}
