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

public class GenreList {

	private  static Logger log = LogManager.getLogger(GenreList.class);
	private static final String GENRES_KEY = "genres";
	
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
//get genres: http://api.themoviedb.org/3/genre/movie/list?api_key=8c92bdaa90b74ce495f535d3bb9849bb
/*{
  "genres": [{
    "id": 28,
    "name": "Action"
  }, {
    "id": 12,
    "name": "Adventure"
  }, {
    "id": 16,
    "name": "Animation"
  }, {
    "id": 35,
    "name": "Comedy"
  }, {
    "id": 80,
    "name": "Crime"
  }, {
    "id": 99,
    "name": "Documentary"
  }, {
    "id": 18,
    "name": "Drama"
  }, {
    "id": 10751,
    "name": "Family"
  }, {
    "id": 14,
    "name": "Fantasy"
  }, {
    "id": 10769,
    "name": "Foreign"
  }, {
    "id": 36,
    "name": "History"
  }, {
    "id": 27,
    "name": "Horror"
  }, {
    "id": 10402,
    "name": "Music"
  }, {
    "id": 9648,
    "name": "Mystery"
  }, {
    "id": 10749,
    "name": "Romance"
  }, {
    "id": 878,
    "name": "Science Fiction"
  }, {
    "id": 10770,
    "name": "TV Movie"
  }, {
    "id": 53,
    "name": "Thriller"
  }, {
    "id": 10752,
    "name": "War"
  }, {
    "id": 37,
    "name": "Western"
  }]
}*/