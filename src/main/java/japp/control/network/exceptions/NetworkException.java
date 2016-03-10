package japp.control.network.exceptions;

@SuppressWarnings("serial")
public class NetworkException extends Exception {

	private static final String BASE_MSG = "Problem when contacting server. ";

	public NetworkException() {
		super(BASE_MSG);
	}
	public NetworkException(String reason) {
		super(BASE_MSG+reason);
	}
}
