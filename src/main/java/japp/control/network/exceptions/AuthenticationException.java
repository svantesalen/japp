package japp.control.network.exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception {

	private static final String BASE_MSG = "Could not authenticat user. ";
	public AuthenticationException() {
		super(BASE_MSG);
	}
	public AuthenticationException(String reason) {
		super(BASE_MSG+reason);
	}

}
