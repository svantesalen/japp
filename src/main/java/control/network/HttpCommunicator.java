package control.network;

import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpCommunicator {

	private  static Logger log = LogManager.getLogger(HttpCommunicator.class);

//	public static String sendLogin(String relativePath) {
//		String requestUrl = UrlBuild.buildLoginUrl(relativePath);
////		Paths.REQUEST_TOKEN
//		return send(requestUrl);
//	}
//
//	public static String sendLogin(String relativePath, String requestToken) {
//		String requestUrl = UrlBuild.buildLoginUrl(relativePath, "request_token", requestToken);
//		return send(requestUrl);
//	}

	public static String send(String requestUrl) {
		log.info("Trying this URL:"+requestUrl);
		Client client = ClientBuilder.newClient();
		Response response;
		try {
			response = client.target(requestUrl)
					.request(MediaType.TEXT_PLAIN_TYPE)
					.header("Accept", "application/json")
					.get();
			String jsonObjectString = response.readEntity(String.class);

			log.debug("status: " + response.getStatus());
			log.debug("headers: " + response.getHeaders());
			log.debug("body:" + jsonObjectString);	
			return jsonObjectString;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not send request to server.", "ERROR", JOptionPane.ERROR_MESSAGE);
			log.error("Exception at contacting server.", e);
		} finally {
			try {
				client.close(); 
			} catch (Exception e) {
				log.error("Error at close connection.", e);
			}
		}
		return "";
	}

	
	
}
