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
 * All info for a genre, including a list of movies (and movie date). 
 * <p>
 * NOTE: current implementation only fetches the first page of movies for a genre.
 * 
 * @author svante
 *
 */
public class Genre {
	private  static Logger log = LogManager.getLogger(Genre.class);

	/**
	 * The keys (names) in the key-value pairs for data in the json string.
	 * @author svante
	 *
	 */
	private enum Keys {
		ID("id"),
		RESULTS("results"),
		PAGE("page"),
		TOTAL_PAGES("total_pages"),
		TOTAL_RESULT("total_results");
		private String key;
		private Keys(String key) {
			this.key =  key;
		}
		private String getKey() {
			return key;
		}

	}

	private String id;
	private String name;
	private String page;
	private Map<String, Movie> mopvieMap = new HashMap<>();
	private String totalPages;
	private String totalResults;

	/**
	 * CTOR
	 * @param jsonString
	 */
	public Genre(String jsonString) {
		parse(jsonString);
	}

	private void parse(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		JSONArray results = obj.getJSONArray(Keys.RESULTS.getKey());
		Movie movie;
		for(int i = 0 ; i < results.length(); i++) {
			try {
				movie = new Movie((JSONObject)results.get(i));
				log.debug("Adding movie"+movie.getId());
				mopvieMap.put(movie.getId(), movie);
			} catch (JSONException e) {
				log.error("Cannot create Movie. Not a valid json object."+results.getDouble(i), e);
			}
		} 

		id = JsonHelper.getValue(Keys.ID.getKey(), jsonString);
		page = JsonHelper.getValue(Keys.PAGE.getKey(), jsonString);
		totalPages = JsonHelper.getValue(Keys.TOTAL_PAGES.getKey(), jsonString);
		totalResults = JsonHelper.getValue(Keys.TOTAL_RESULT.getKey(), jsonString);	
	}

	public int size() {
		return mopvieMap.size();
	}

	public Map<String, Movie> getMovies() {
		return mopvieMap;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id="+id);
		sb.append("\n");
		sb.append("name="+name);
		sb.append("\n");
		sb.append("page="+page);
		sb.append("\n");
		sb.append("totalPages="+totalPages);
		sb.append("\n");
		sb.append("totalResults="+totalResults);
		for(Entry<String, Movie> entry: mopvieMap.entrySet()) {
			sb.append("\n");
			sb.append("movie="+entry.getValue().toString());
		}
		return sb.toString();
	}	
}