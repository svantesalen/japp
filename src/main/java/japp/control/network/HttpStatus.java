package japp.control.network;
//package japp.control;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * The status code corresponding to the HTTP response message.
// * @author svante
// *
// */
//public class HttpStatus {
//	private int httpStatusCode;
//	private String httpStatusMessage;
//
//	private static Map<Integer, HttpStatus> httpStatusCodes= new HashMap<>();
//
//	/**
//	 * CTOR
//	 * @param httpStatusCode
//	 * @param httpStatusMessage
//	 */
//	public HttpStatus(int httpStatusCode, String httpStatusMessage) {
//		this.httpStatusCode = httpStatusCode;
//		this.httpStatusMessage = httpStatusMessage;
//	}
//	
//	public int getHttpStatusCode() {
//		return httpStatusCode;
//	}
//	
//	public String getHttpStatusMessage() {
//		return httpStatusMessage;
//	}
//	
//	public static HttpStatus getHttpStatusCode(int responseCode) {
//		 return httpStatusCodes.get(new Integer(responseCode));
//	}
//	
//	/**
//	 * Add all status responses received in JSON to map.
//	 */
//	static {
//		httpStatusCodes.put(new Integer(1), new HttpStatus(200, "Success."));
//		httpStatusCodes.put(new Integer(2), new HttpStatus(501, "Invalid service: this service does not exist."));
//		httpStatusCodes.put(new Integer(3), new HttpStatus(401, "Authentication failed: You do not have permissions to access the service."));
//		httpStatusCodes.put(new Integer(4), new HttpStatus(405, "Invalid format: This service doesn't exist in that format."));
//		httpStatusCodes.put(new Integer(5), new HttpStatus(422, "Invalid parameters: Your request parameters are incorrect."));
//		httpStatusCodes.put(new Integer(6), new HttpStatus(404, "Invalid id: The pre-requisite id is invalid or not found."));
//		httpStatusCodes.put(new Integer(7), new HttpStatus(401, "Invalid API key: You must be granted a valid key."));
//		httpStatusCodes.put(new Integer(8), new HttpStatus(403, "Duplicate entry: The data you tried to submit already exists."));
//		httpStatusCodes.put(new Integer(9), new HttpStatus(503, "Service offline: This service is temporarily offline, try again later."));
//		httpStatusCodes.put(new Integer(10), new HttpStatus(401, "Suspended API key: Access to your account has been suspended, contact TMDb."));
//		httpStatusCodes.put(new Integer(11), new HttpStatus(500, "Internal error: Something went wrong, contact TMDb."));
//		httpStatusCodes.put(new Integer(12), new HttpStatus(201, "The item/record was updated successfully."));
//		httpStatusCodes.put(new Integer(13), new HttpStatus(200, "The item/record was deleted successfully."));
//		httpStatusCodes.put(new Integer(14), new HttpStatus(401, "Authentication failed."));
//		httpStatusCodes.put(new Integer(15), new HttpStatus(500, "Failed."));
//		httpStatusCodes.put(new Integer(16), new HttpStatus(401, "Device denied."));
//		httpStatusCodes.put(new Integer(17), new HttpStatus(401, "Session denied."));
//		httpStatusCodes.put(new Integer(18), new HttpStatus(400, "Validation failed."));
//		httpStatusCodes.put(new Integer(19), new HttpStatus(406, "Invalid accept header."));
//		httpStatusCodes.put(new Integer(20), new HttpStatus(422, "Invalid date range: Should be a range no longer than 14 days."));
//		httpStatusCodes.put(new Integer(21), new HttpStatus(200, "Entry not found: The item you are trying to edit cannot be found."));
//		httpStatusCodes.put(new Integer(22), new HttpStatus(400, "Invalid page: Pages start at 1 and max at 1000. They are expected to be an integer."));
//		httpStatusCodes.put(new Integer(23), new HttpStatus(400, "Invalid date: Format needs to be YYYY-MM-DD."));
//		httpStatusCodes.put(new Integer(24), new HttpStatus(504, "Your request to the backend server timed out. Try again."));
//		httpStatusCodes.put(new Integer(25), new HttpStatus(429, "Your request count (#) is over the allowed limit of (40)."));
//		httpStatusCodes.put(new Integer(26), new HttpStatus(400, "You must provide a username and password."));
//		httpStatusCodes.put(new Integer(27), new HttpStatus(400, "Too many append to response objects: The maximum number of remote calls is 20."));
//		httpStatusCodes.put(new Integer(28), new HttpStatus(400, "Invalid timezone: Please consult the documentation for a valid timezone."));
//		httpStatusCodes.put(new Integer(29), new HttpStatus(400, "You must confirm this action: Please provide a confirm=true parameter."));
//		httpStatusCodes.put(new Integer(30), new HttpStatus(401, "Invalid username and/or password: You did not provide a valid login."));
//		httpStatusCodes.put(new Integer(31), new HttpStatus(401, "Account disabled: Your account is no longer active. Contact TMDb if this is an error."));
//		httpStatusCodes.put(new Integer(32), new HttpStatus(401, "Email not verified: Your email address has not been verified."));
//		httpStatusCodes.put(new Integer(33), new HttpStatus(401, "Invalid request token: The request token is either expired or invalid."));
//		httpStatusCodes.put(new Integer(34), new HttpStatus(401, "The resource you requested could not be found."));		
//	}
//}
//
//
//
