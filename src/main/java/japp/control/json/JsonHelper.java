package japp.control.json;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

	private static final Logger log = LogManager.getLogger(JsonHelper.class);

	private JsonHelper() {/* empty */}

	/**
	 * Get the value from a json string. Convert non-String objects to String.
	 * @param key
	 * @param jsonObjectString
	 * @return the value of the key if found, else empty string
	 */
	public static String getValue(String key, String jsonObjectString) {
		if(jsonObjectString.isEmpty()) {
			return "";
		}
		try {
			JSONObject obj = new JSONObject(jsonObjectString);
			return getValue(key, obj);
		} catch (JSONException e) {
			log.error("Not a Json String: "+jsonObjectString, e);
			return "";
		}
	}	

	/**
	 * Get the value from a json object. Convert non-String objects to String if possible.
	 * 
	 * @param key
	 * @param obj
	 * @return the value of the key if found, else empty string
	 */
	public static String getValue(String key, JSONObject obj) {
		try {
			String value = castToString(obj.get(key));
			log.debug(key+"="+value);
			return value;
		} catch (JSONException e) { 
			// TODO - not logging exception since too much output. Need to change Movie so it does not call for the "extra info" keys.
			log.warn("Could not read key="+key);
			return "";
		}
	}

	/**
	 * Cast to String in a brutal manner.
	 * @param obj
	 * @return The String value, or empty string if cast was impossible.
	 */
	private static String castToString(Object obj) {
		try {
			return (String) obj;
		} catch (ClassCastException e) {/*empty*/} // NOSONAR
		try {
			return Integer.toString((int) obj);
		} catch (ClassCastException e) {/*empty*/} // NOSONAR
		try {
			return Long.toString((long) obj);
		} catch (ClassCastException e) {/*empty*/} // NOSONAR
		try {
			return Float.toString((float) obj);
		} catch (ClassCastException e) {/*empty*/} // NOSONAR
		try {
			return Double.toString((double) obj);
		} catch (ClassCastException e) {/*empty*/} // NOSONAR
		try {
			return Boolean.toString((boolean) obj);
		} catch (ClassCastException e) {/*empty*/} // NOSONAR
		log.error("Object could not be casted to String: "+obj.toString());
		return "";		
	}

	/**
	 * Get a JSONArray from a json string.
	 * 
	 * @param key The key of the array
	 * @param jsonObjectString
	 * @return the array-value for the key if found, else empty array
	 */
	public static JSONArray getArray(String key, String jsonObjectString) {
		if(jsonObjectString.isEmpty()) {
			return new JSONArray();
		}
		try {
			JSONObject obj = new JSONObject(jsonObjectString);
			return obj.getJSONArray(key);
		} catch (JSONException e) {
			log.error("Could not read key="+key, e);
			log.error("From: "+jsonObjectString);
			return new JSONArray();
		}
	}
}
