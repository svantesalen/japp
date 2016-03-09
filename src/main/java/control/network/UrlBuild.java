package control.network;

public class UrlBuild {

	/**
	 * Holds the keys for a key-value pair.
	 * @author svante
	 *
	 */
	public enum Attribute{
		REQUEST_TOKEN("request_token"),
		SESSION_ID("session_id");
		
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
	 * Holds the relative path for 
	 * @author svante
	 *
	 */
	public enum Paths {
		REQUEST_TOKEN("authentication/token/new"),
		SESSION_ID("authentication/session/new");
		
		String relativePath;
		Paths(String relativePath) {
			this.relativePath = relativePath;
		}

		public String buildUrl() {
			return HOST_URL+relativePath+API_KEY_PARAM;			
		}
		
		public String buildUrl(String key, String value) {
			return HOST_URL+relativePath+API_KEY_PARAM+"&"+key+"="+value;
		}
		
		@Override
		public String toString() {
			return name();
		}
	}
	
	public static final String API_KEY = "8c92bdaa90b74ce495f535d3bb9849bb";
	public static final String API_KEY_PARAM = "?api_key=8c92bdaa90b74ce495f535d3bb9849bb";
	
	public static final String HOST_PRODUCTION_URL = "http://api.themoviedb.org/3/";
	public static final String HOST_DEBUG_URL = "http://private-anon-6626a6239-themoviedb.apiary-proxy.com/3/";
	public static final String HOST_URL = HOST_DEBUG_URL;
	public static final String HOST_FOR_USER_LOGIN_VERIFICATION_URL = "https://www.themoviedb.org/authenticate/";
	
	private UrlBuild() {/* empty */}

	public static String getLoginVerificationUrl(String token) {
		return HOST_FOR_USER_LOGIN_VERIFICATION_URL+token;
	}

	
	// OLD
	
//	public static String buildLoginUrl(String relativePath) {
//		return HOST_URL+relativePath+API_KEY_PARAM;
//	}
//
//	public static String buildLoginUrl(String relativePath, String key, String value) {
//		return HOST_DEBUG_URL+relativePath+API_KEY_PARAM+"&"+key+"="+value;
//	}
	
}
