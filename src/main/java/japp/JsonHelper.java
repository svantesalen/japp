package japp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {
	
	private  static Logger log = LogManager.getLogger(JsonHelper.class);

	private JsonHelper() {/* empty */}

	/**
	 * 
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
		String value = (String) obj.get(key);
		log.debug(key+"="+value);
		return value;
		} catch (JSONException e) {
			log.error("Could not red key="+key, e);
			log.error("From: "+jsonObjectString);
			return "";
		}
	}
	

}
