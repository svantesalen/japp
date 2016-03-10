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
 * ---------------------------------------------------------
 * From genre:
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
 *
 * @author svante
 *
 */

public class Movie {
	private  static Logger log = LogManager.getLogger(Movie.class);
	public enum Keys {
		// String
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

		// boolean
		ADULT("adult"),
		VIDEO("video"),

		// number
		POPULARITY("popularity"),
		ID("id"),
		BUDGET("budget"),
		REVENUE("revenue"),
		RUNTIME("runtime"),
		VOTE_AVERAGE("vote_average"),
		VOTE_COUNT("vote_count");

		private String key;
		Keys(String key) {
			this.key =  key;
		}
		protected String getKey() {
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
/*overview : As a cowardly farmer begins to fall for the mysterious new woman in town, he must put his new-found courage to the test when her husband, a notorious gun-slinger, announces his arrival.
original_language : en
original_title : A Million Ways to Die in the West
video : false
title : A Million Ways to Die in the West
poster_path : /12fqfvUmBOPg2pA0RsEhc31P28O.jpg
backdrop_path : /tkxiEwG9xJIbyzvJSGYUyTkz3Mj.jpg
release_date : 2014-05-28
popularity : 3.560446
vote_average : 5.9
id : 188161
adult : false
vote_count : 894
*/

// TODO: implement extra details:
//	http://private-anon-6626a6239-themoviedb.apiary-proxy.com/3/movie/293660?api_key=8c92bdaa90b74ce495f535d3bb9849bb
//public static String jsonString = 
/*
// String
"backdrop_path"
"homepage"
"imdb_id"
"original_language"
"original_title"
"overview"
"poster_path"


"release_date"
"status"
"tagline"
"title"

// boolean
"adult"
"video"

// number
"popularity"
"id"
"budget"
"revenue"
"runtime"
"vote_average"
"vote_count"
{
  "adult": false,
  "backdrop_path": "/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg",
  "belongs_to_collection": {
    "id": 8945,
    "name": "Mad Max Collection",
    "poster_path": "/jZowUf4okNYuSlgj5iURE7CDMho.jpg",
    "backdrop_path": "/zI0q2ENcQOLECbe0gAEGlncVh2j.jpg"
  },
  "budget": 150000000,
  "genres": [{
    "id": 878,
    "name": "Science Fiction"
  }, {
    "id": 53,
    "name": "Thriller"
  }, {
    "id": 28,
    "name": "Action"
  }, {
    "id": 12,
    "name": "Adventure"
  }],
  "homepage": "http://www.madmaxmovie.com/",
  "id": 76341,
  "imdb_id": "tt1392190",
  "original_language": "en",
  "original_title": "Mad Max: Fury Road",
  "overview": "An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.",
  "popularity": 34.324309,
  "poster_path": "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
  "production_companies": [{
    "name": "Village Roadshow Pictures",
    "id": 79
  }, {
    "name": "Kennedy Miller Productions",
    "id": 2537
  }],
  "production_countries": [{
    "iso_3166_1": "AU",
    "name": "Australia"
  }, {
    "iso_3166_1": "US",
    "name": "United States of America"
  }],
  "release_date": "2015-05-13",
  "revenue": 374736354,
  "runtime": 120,
  "spoken_languages": [{
    "iso_639_1": "en",
    "name": "English"
  }],
  "status": "Released",
  "tagline": "What a Lovely Day.",
  "title": "Mad Max: Fury Road",
  "video": false,
  "vote_average": 7.4,
  "vote_count": 4049
}
 */