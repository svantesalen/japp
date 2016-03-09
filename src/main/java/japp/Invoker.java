package japp;

import javax.security.sasl.AuthenticationException;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import control.network.HttpBrowserController;
import control.network.HttpCommunicator;
import control.network.Session;
import control.network.UrlBuild;
import control.network.UrlBuild.Attribute;
import control.network.UrlBuild.Paths;
import control.network.exceptions.NetworkException;


public class Invoker {

	private static Logger log = LogManager.getLogger(Invoker.class);

	public static void main(String[] args) {	

		Session session;
		try {
			session = new Session();			
		} catch (NetworkException|AuthenticationException e) {
			authenticationFailure(e.getMessage());
			return;
		}
		
//		Invoker invoker = new Invoker();
//		try {
//			invoker.authenticate();
//		} catch (NetworkException e) {
//			log.error(e.getMessage(), e);
//		}
	}
	
	@Test
	public void authenticate() throws NetworkException {
		// 1. Get a request token.
		String requestUrl = Paths.REQUEST_TOKEN.buildUrl();
		String jsonString = HttpCommunicator.send(requestUrl);
		String token = JsonHelper.getValue(Attribute.REQUEST_TOKEN.key(), jsonString);
		if(token.isEmpty()) {
			authenticationFailure();
			return;
		}
		
		// 2. Ask user to verify in web browser.
		requestUrl = UrlBuild.getLoginVerificationUrl(token);
		HttpBrowserController.open(requestUrl);
		
		// 3. Wait for user confirm.
		JOptionPane.showMessageDialog(null, "Press OK when you have logged in.", "INFO", JOptionPane.INFORMATION_MESSAGE);

		// 4. Get the session ID (outcome from successful login).
		requestUrl = Paths.SESSION_ID.buildUrl(Attribute.REQUEST_TOKEN.key(), token); // add token as key-value
		jsonString = HttpCommunicator.send(requestUrl);
		String sessionId = JsonHelper.getValue(Attribute.SESSION_ID.key(), jsonString);
		if(sessionId.isEmpty()) {
			authenticationFailure();
			return;
		}	
		JOptionPane.showMessageDialog(null, "Your session ID: "+sessionId, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static void authenticationFailure(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	private void authenticationFailure() {
		JOptionPane.showMessageDialog(null, "Could not authenticate.", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}
