//package japp.model.movies;
//
//import java.awt.Image;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.json.JSONObject;
//
//import japp.control.json.JsonHelper;
//
///**
// * Poster and backdrops for a movie.
// * <pre>
// * ---------------------------------------------------------
// * Example:
// *---------------------------------------------------------
//
//{
//  "id": 550,
//  "backdrops": [
//    {
//      "file_path": "/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
//      "width": 1280,
//      "height": 720,
//      "iso_639_1": null,
//      "aspect_ratio": 1.78,
//      "vote_average": 6.647058823529412,
//      "vote_count": 17
//    },
//    {
//      "file_path": "/fuSeIUKsizmfiPIwDH7lKiFNQoD.jpg",
//      "width": 1280,
//      "height": 720,
//      "iso_639_1": null,
//      "aspect_ratio": 1.78,
//      "vote_average": 4.3,
//      "vote_count": 5
//    }
//  ],
//  "posters": [
//    {
//      "file_path": "/2lECpi35Hnbpa4y46JX0aY3AWTy.jpg",
//      "width": 1000,
//      "height": 1500,
//      "iso_639_1": "en",
//      "aspect_ratio": 0.67,
//      "vote_average": 6.1395348837209305,
//      "vote_count": 43
//    },
//    {
//      "file_path": "/shFj1K58Tn55Qz2p2v0RqxXiXyo.jpg",
//      "width": 1000,
//      "height": 1408,
//      "iso_639_1": "en",
//      "aspect_ratio": 0.71,
//      "vote_average": 6,
//      "vote_count": 2
//    }
//  ]
//}
// *---------------------------------------------------------
// *</pre>
// *
// * NOTE: The API allows extra info if you ask the TMDb for a particular movie. Not implemented here.
// * 
// * @author svante
// *
// */
//
//public class MovieImages {
//	private  static Logger log = LogManager.getLogger(MovieImages.class);
//	private  enum Keys {
//		// String:
//		BACK_DROP_PATH("backdrop_path"),
//		HOMEPAGE("homepage"),
//		IMDB_ID("imdb_id"),
//		ORIG_LANG("original_language"),
//		ORIG_TITLE("original_title"),
//		OVERVIEW("overview"),
//		POSTER_PATH("poster_path"),
//		RELEASE_DATE("release_date"),
//		STATUC("status"),
//		TAGLINE("tagline"),
//		TITLE("title"),
//		// boolean:
//		ADULT("adult"),
//		VIDEO("video"),
//		// number:
//		POPULARITY("popularity"),
//		ID("id"),
//		BUDGET("budget"),
//		REVENUE("revenue"),
//		RUNTIME("runtime"),
//		VOTE_AVERAGE("vote_average"),
//		VOTE_COUNT("vote_count");
//
//		private String key;
//		private Keys(String key) {
//			this.key =  key;
//		}
//		private String getKey() {
//			return key;
//		}
//
//		public String getValue(String jsonString) {
//			return JsonHelper.getValue(key, jsonString);
//		}
//	}
//	
//	private Map<String, String> details = new HashMap<>();
//	private Image poster;
//	
//	/**
//	 * CTOR
//	 * @param jsonString
//	 */
//	public MovieImages(JSONObject jsonObject) {
//		for(Keys key: Keys.values()) {
//			details.put(key.getKey(), JsonHelper.getValue(key.getKey(), jsonObject));
//			log.debug("### Added movie detail: "+ key.getKey()+"="+details.get(key.getKey()));
//		}
//	}
//	
////	@Override
////	public String toString() {
////		StringBuilder sb = new StringBuilder();
////		String value;
////		for(Entry<String, String> entry: details.entrySet()) {
////			value = entry.getValue();
////			if(!value.isEmpty()) {
////				sb.append(entry.getKey()+" : ");
////				sb.append(entry.getValue());
////				sb.append("\n");
////			}
////		}
////		return sb.toString();
////	}
//}
