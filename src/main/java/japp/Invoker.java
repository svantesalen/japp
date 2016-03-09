package japp;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import control.network.HttpBrowserController;
import control.network.HttpCommunicator;
import control.network.UrlBuild;
import control.network.UrlBuild.Attribute;
import control.network.UrlBuild.Paths;


public class Invoker {

	private static Logger log = LogManager.getLogger(Invoker.class);

	public static void main(String[] args) {	
		Invoker invoker = new Invoker();
		invoker.authenticate();
	}
	
	@Test
	public void authenticate() {
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
	
	private void authenticationFailure() {
		JOptionPane.showMessageDialog(null, "Could not authenticate.", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

//	@Test
//	public void login() {
//		log.debug("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
//
//		String jsonObjectString = HttpCommunicator.sendLogin("authentication/token/new");
//		if(jsonObjectString.isEmpty()) {
//			return;
//		}
//		String requestToken = getToken(jsonObjectString);
//		askUserToVerify(requestToken);
//		JOptionPane.showMessageDialog(null, "Press OK when you have logged in.", "INFO", JOptionPane.INFORMATION_MESSAGE);
//		String sessionId = getSessionId(requestToken);
//		log.debug("Session id="+sessionId);
//	}

	

	//	JsonObject jsonObject = new JsonParser().parse("{\"name\": \"John\"}").getAsJsonObject();
	//	System.out.println(jsonObject.get("name").getAsString()); //John
	//
	//	JSONObject obj = new JSONObject("{\"name\": \"John\"}");
	//	System.out.println(obj.getString("name")); //John
	//	String loudScreaming = json.getJSONObject("LabelData").getString("slogan");

	//	DEBUG 2016-03-08 11:58:22,033 [main] japp.Invoker:login(36): body:{"success":true,"expires_at":"2016-03-08 11:58:21 UTC","request_token":"2a659ea2fea5aefcaf9aeda1b360c9d3fc17bd02"}

	//	
	//	@Test
	//	public void getAccound() {
	//		log.debug("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
	//		Client client = ClientBuilder.newClient();
	//		Response response;
	//		try {
	//			response = client.target("http://api.themoviedb.org/3/account?api_key=8c92bdaa90b74ce495f535d3bb9849bb")
	//					.request(MediaType.TEXT_PLAIN_TYPE)
	//					.header("Accept", "application/json")
	//					.get();
	//
	//			log.debug("status: " + response.getStatus());
	//			log.debug("headers: " + response.getHeaders());
	//			log.debug("body:" + response.readEntity(String.class));
	//
	//		} catch (Exception e) {
	//			log.error("Exception at contacting server.", e);
	//			return;
	//		} finally {
	//			client.close();
	//		}
	//	}
	//	
	//	@Test
	//	public void getConfiguration() {	
	//		log.debug("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
	//		Client client = ClientBuilder.newClient();
	//		Response response;
	//		try {
	//			response = client.target("http://api.themoviedb.org/3/configuration?api_key=8c92bdaa90b74ce495f535d3bb9849bb")
	//					.request(MediaType.TEXT_PLAIN_TYPE)
	//					.header("Accept", "application/json")
	//					.get();
	//
	//			log.debug("status: " + response.getStatus());
	//			log.debug("headers: " + response.getHeaders());
	//			log.debug("body:" + response.readEntity(String.class));
	//
	//		} catch (Exception e) {
	//			log.error("Exception at contacting server.", e);
	//			return;
	//		} finally {
	//			client.close();
	//		}
	//	}


}

/*Step 1: Create a new request token
The first step as a developer is to request a new token. 
This is a temporary token that is required to ask the user for permission to access their account. 
This token will auto expire after 60 minutes if it's not used. We strongly recommend using the process outlined in 2a.

Step 2a: Ask the user for permission via the website
The next step is to take the token you got from step #1 and direct your user to the following URL:

https://www.themoviedb.org/authenticate/REQUEST_TOKEN

This callback URL is also accessible via the Authentication-Callback header that gets returned in step #1. You can also pass in a redirect_to param when making this call which will redirect the user once the authentication flow has been completed.

This step is where the user gets involved, authorizing your API key access to their account.

Step 2b: Ask the user for permission via the API
If you would like to authenticate your request token on the API (as opposed to the webiste method above), you can do so by asking your user for their TMDb username and password.

Please note: that we do not encourage you to use this method as it will transmit a valid username and password combination over tha air. This process should only be used on devices and environments that don't have access to a web browser.

An example of this request looks like:

https://api.themoviedb.org/3/authentication/token/validate_with_login? api_key=API_KEY&request_token=REQUEST_TOKEN&username=###&password=###

Step 3: Create a sessrequestion ID
Assuming the  token was authorized via step 2a or 2b, you can now go and request a session ID.

The results of this query will return a session_id value. You should treat this value like a password. Store it securely. This is the value required in all of our write methods.

 */