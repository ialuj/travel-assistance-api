package com.travel.assistance.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service(value = ITravelAssistanceService.NAME)
public class TravelAssistanceServiceImpl implements ITravelAssistanceService {

	@SuppressWarnings("rawtypes")
	private LinkedHashMap currencyList;

	@SuppressWarnings("rawtypes")
	public TravelAssistanceServiceImpl() {

		try (FileReader reader = new FileReader("/home/ialuj/git/personal/travel-assistance-api/src/main/resources/currencies.json")) {
			JSONParser jsonParser = new JSONParser(reader);
			currencyList = (LinkedHashMap) jsonParser.parse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.apache.tomcat.util.json.ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getCurrencyByCountryCode(String countryCode) throws JSONException {
		@SuppressWarnings("unchecked")
		Set<String> keys = currencyList.keySet();
		for (String key : keys) {
			if(currencyList.get(key).toString().equalsIgnoreCase(countryCode)) {
				return key;
			}
		}
		return null;
	}

}
