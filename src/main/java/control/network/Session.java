package control.network;

import javax.security.sasl.AuthenticationException;
import javax.swing.JOptionPane;

import control.network.UrlBuild.Attribute;
import control.network.UrlBuild.Paths;
import control.network.exceptions.NetworkException;
import japp.JsonHelper;

/**
 * Class that logs you in and stores the session id that is a result of that login.
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
	
	private void authenticate() throws NetworkException, AuthenticationException {

		// 1. Get a request token.
		String requestUrl = Paths.REQUEST_TOKEN.buildUrl();
		String jsonString = HttpCommunicator.send(requestUrl);
		String token = JsonHelper.getValue(Attribute.REQUEST_TOKEN.key(), jsonString);
		if(token.isEmpty()) {
			throw new AuthenticationException("No token in server response.");
		}

		// 2. Ask user to verify in web browser.
		requestUrl = UrlBuild.getLoginVerificationUrl(token);
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
