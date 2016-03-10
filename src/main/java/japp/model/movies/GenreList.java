package japp.model.movies;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import japp.control.json.JsonHelper;

/**
 * All genres on TMDb.
 * 
 * @author svante
 *
 */
public class GenreList {

	private static final String GENRES_KEY = "genres";
	private  static Logger log = LogManager.getLogger(GenreList.class);
	
	Map<String, String> genres = new HashMap<>();

	/**
	 * CTOR
	 * @param jsonString
	 */
	public GenreList(String jsonString) {
		parse(jsonString);
	}
	
	private void parse(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		JSONArray results = obj.getJSONArray(GENRES_KEY);
		String genreId;
		String genreName;
		JSONObject genreObject;
		for(int i = 0 ; i < results.length(); i++) {
			try {
				genreObject = (JSONObject)results.get(i);
				genreId = JsonHelper.getValue("id", genreObject);
				genreName =JsonHelper.getValue("name", genreObject);
				genres.put(genreId, genreName);
			} catch (JSONException e) {
				log.error("Cannot create Genre.", e);
			}
		} 	
	}

	public int size() {
		return genres.size();
	}
	
	public Map<String, String> getGenres() {
		return genres;
	}

	// TODO: maybe exception instead? And maybe keep a separate list as for movies. See JappListPanel.
	public String getIdFromName(String name) {
		for(Entry<String, String> entry: genres.entrySet()) {
			if(entry.getValue().trim().equals(name.trim())) {
				return entry.getKey();
			}
		}
		log.error("No genre found with name: "+name);
		return "";
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for(Entry<String, String> entry: genres.entrySet()) {
			sb.append(entry.getKey()+ " = "+entry.getValue());
			sb.append("\n---------------------\n");
		}
		return sb.toString();
	}
}
