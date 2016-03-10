package japp.control.network;

import javax.security.sasl.AuthenticationException;
import javax.swing.JOptionPane;

import japp.control.json.JsonHelper;
import japp.control.network.UrlBuild.Attribute;
import japp.control.network.UrlBuild.Paths;
import japp.control.network.exceptions.NetworkException;

/**
 * Handles the server interaction for session-id request. <br>
 * The session-id is stored here after a successful "login".
 * @author svante
 *
 */
public class Session {

	private String sessionId;

	/**
	 * CTOR.
	 * @throws NetworkException 
	 * @throws AuthenticationException 
	 */
	public Session() throws NetworkException, AuthenticationException {
		authenticate();
	}

	/**
	 * Authentication is made in these steps:
	 * <ol>
	 * <li>Get a request token</li>
	 * <li>Ask user to verify in web browser</li>
	 * <li>Wait for user confirm</li>
	 * <li>Get the session ID (outcome from successful login)</li>
	 * </ol>
	 * @throws NetworkException
	 * @throws AuthenticationException
	 */
	private void authenticate() throws NetworkException, AuthenticationException {

		// 1. Get a request token.
		String requestUrl = Paths.REQUEST_TOKEN.buildUrl();
		String jsonString = HttpCommunicator.send(requestUrl);
		String token = JsonHelper.getValue(Attribute.REQUEST_TOKEN.key(), jsonString);
		if(token.isEmpty()) {
			throw new AuthenticationException("No token in server response.");
		}

		// 2. Ask user to verify in web browser.
		requestUrl = UrlBuild.buildLoginVerificationUrl(token);
		HttpBrowserController.open(requestUrl);

		// 3. Wait for user confirm.
		JOptionPane.showMessageDialog(null, "Press OK when you have logged in.", "INFO", JOptionPane.INFORMATION_MESSAGE);

		// 4. Get the session ID (outcome from successful login).
		requestUrl = Paths.SESSION_ID.buildUrl(Attribute.REQUEST_TOKEN.key(), token); // add token as key-value
		jsonString = HttpCommunicator.send(requestUrl);
		this.sessionId = JsonHelper.getValue(Attribute.SESSION_ID.key(), jsonString);
		if(sessionId.isEmpty()) {
			throw new AuthenticationException("No session id in server response.");
		}	
		JOptionPane.showMessageDialog(null, "Your session ID: "+sessionId, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}
}
