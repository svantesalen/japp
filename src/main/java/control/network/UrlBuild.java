package control.network;

public class UrlBuild {

	/**
	 * The host web addresses. <br>
	 * At production change to use the production URL:
	 */
	public static final String HOST_PRODUCTION_URL = "http://api.themoviedb.org/3/";
	public static final String HOST_DEBUG_URL = "http://private-anon-6626a6239-themoviedb.apiary-proxy.com/3/";
	public static final String HOST_URL = HOST_DEBUG_URL;
	//	public static final String HOST_URL = HOST_PRODUCTION_URL; // NOSONAR
	public static final String HOST_FOR_USER_LOGIN_VERIFICATION_URL = "https://www.themoviedb.org/authenticate/";

	/**
	 * The API key has to be manually replaced if another TMDb account is used.
	 */
	public static final String API_KEY = "8c92bdaa90b74ce495f535d3bb9849bb";
	public static final String API_KEY_PARAM = "?api_key=8c92bdaa90b74ce495f535d3bb9849bb";
	
	/**
	 * Holds some keys for a key-value pair need when building an url.
	 * @author svante
	 *
	 */
	public enum Attribute{
		REQUEST_TOKEN("request_token"),
		SESSION_ID("session_id"),
		INCLUDE_ALL_MOVIES("include_all_movies"),
		INCLUDE_ADULT("include_adult"),
		PAGE("page");
		
		String key;
		Attribute(String key) {
			this.key = key;
		}
		public String key() {
			return key;
		}
		@Override
		public String toString() {
			return name();
		}
	} 
	
	/**
	 * Build a URL for a given path, with or without ID.
	 * @author svante
	 * 
	 */
	public enum Paths {
		REQUEST_TOKEN("authentication/token/new"),
		SESSION_ID("authentication/session/new"),
		GENRE_LIST("genre/movie/list"),
		GENRE("genre/","/movies"),
		MOVIE("movie/", "");
		
		String relativePath;
		String relativePath2;
		Paths(String relativePath) {
			this.relativePath = relativePath;
		}
		Paths(String relativePath, String relativePath2) {
			this.relativePath = relativePath;
			this.relativePath2 = relativePath2;
		}

		/**
		 * Build a url string NOT containing an ID.
		 * @return url 
		 */
		public String buildUrl() {
			return HOST_URL+relativePath+API_KEY_PARAM;			
		}

		/**
		 * Build a url string containing an ID.
		 * @param id The id for e.g. a movie or a genre.
		 * @return url
		 */
		public String buildUrl(String id) {
			return HOST_URL+relativePath+id+relativePath2+API_KEY_PARAM;			
		}
		
		/**
		 * Build a url string containing parameter (key value pairs)
		 * @param key
		 * @param value
		 * @return url
		 */
		public String buildUrl(String key, String value) {
			return HOST_URL+relativePath+API_KEY_PARAM+"&"+key+"="+value;
		}
		
		@Override
		public String toString() {
			return name();
		}
	}
	
	private UrlBuild() {/* empty */}

	/**
	 * Add a parameter (key-value pair) to the URL.
	 * @param url
	 * @param key
	 * @param value
	 * @return
	 */
	public static String addParameter(String url, String key, String value) {
		return url+"&"+key+"="+value;
	}

	/**
	 * Build the URL needed at login verification.
	 * @param token
	 * @return
	 */
	public static String buildLoginVerificationUrl(String token) {
		return HOST_FOR_USER_LOGIN_VERIFICATION_URL+token;
	}
}
