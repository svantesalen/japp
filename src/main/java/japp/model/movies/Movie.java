package japp.model.movies;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import japp.control.json.JsonHelper;

/**
 * Movie details at it looks when asking for movies in a genre:
 * <pre>
 * ---------------------------------------------------------
 * Example:
 *---------------------------------------------------------
 *		"adult": false,
 *		"backdrop_path": "/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg",
 *		"genre_ids": [878, 53, 28, 12],
 *		"id": 76341,
 *		"original_language": "en",
 *		"original_title": "Mad Max: Fury Road",
 *		"overview": "An apocalyptic story set in the furthest reaches of our planet...
 *		"release_date": "2015-05-13",
 *		"poster_path": "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
 *		"popularity": 35.748131,
 *		"title": "Mad Max: Fury Road",
 *		"video": false,
 *		"vote_average": 7.4,
 *		"vote_count": 4040
 *---------------------------------------------------------
 *</pre>
 *
 * NOTE: The API allows extra info if you ask the TMDb for a particular movie. Not implemented here.
 * 
 * @author svante
 *
 */

public class Movie {
	private  static Logger log = LogManager.getLogger(Movie.class);
	private  enum Keys {
		// String:
		BACK_DROP_PATH("backdrop_path"),
		HOMEPAGE("homepage"),
		IMDB_ID("imdb_id"),
		ORIG_LANG("original_language"),
		ORIG_TITLE("original_title"),
		OVERVIEW("overview"),
		POSTER_PATH("poster_path"),
		RELEASE_DATE("release_date"),
		STATUC("status"),
		TAGLINE("tagline"),
		TITLE("title"),
		// boolean:
		ADULT("adult"),
		VIDEO("video"),
		// number:
		POPULARITY("popularity"),
		ID("id"),
		BUDGET("budget"),
		REVENUE("revenue"),
		RUNTIME("runtime"),
		VOTE_AVERAGE("vote_average"),
		VOTE_COUNT("vote_count");

		private String key;
		private Keys(String key) {
			this.key =  key;
		}
		private String getKey() {
			return key;
		}

		public String getValue(String jsonString) {
			return JsonHelper.getValue(key, jsonString);
		}
	}
	
	private Map<String, String> details = new HashMap<>();

	/**
	 * CTOR
	 * @param jsonString
	 */
	public Movie(JSONObject jsonObject) {
		for(Keys key: Keys.values()) {
			details.put(key.getKey(), JsonHelper.getValue(key.getKey(), jsonObject));
			log.debug("### Added movie detail: "+ key.getKey()+"="+details.get(key.getKey()));
		}
	}

	public String getId() {
		return details.get(Keys.ID.getKey());
	}

	public String getTitle() {
		return details.get(Keys.TITLE.getKey());
	}

	public String getTagline() {
		return details.get(Keys.TAGLINE.getKey());
	}

	public String getOverview() {
		return details.get(Keys.OVERVIEW.getKey());
	}

	public String getPopulatity() {
		return details.get(Keys.POPULARITY.getKey());
	}

	public String getReleaseDate() {
		return details.get(Keys.RELEASE_DATE.getKey());
	}

	public String getVoteAverage() {
		StringBuilder sb = new StringBuilder();
		sb.append(details.get(Keys.VOTE_AVERAGE.getKey()));
		return sb.toString();
	}

	public String getVoteCount() {
		StringBuilder sb = new StringBuilder();
		sb.append(details.get(Keys.VOTE_COUNT.getKey()));
		return sb.toString();
	}
	
	public String getShortInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(getReleaseYear());
		sb.append(": ");
		sb.append(details.get(Keys.TITLE.getKey()));
		return sb.toString();
	}
	
	private String getReleaseYear() {
		String date = details.get(Keys.RELEASE_DATE.getKey());
		if(date.isEmpty()) {
			return "Year?";
		}
		return date.substring(0, 4);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String value;
		for(Entry<String, String> entry: details.entrySet()) {
			value = entry.getValue();
			if(!value.isEmpty()) {
				sb.append(entry.getKey()+" : ");
				sb.append(entry.getValue());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
