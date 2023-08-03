package com.travel.assistance.services;

import org.json.JSONException;

public interface ITravelAssistanceService {

	public static String NAME = "com.travel.assistance.services.ITravelAssistanceService";

	public String getCurrencyByCountryCode(final String countryCode) throws JSONException;

}
