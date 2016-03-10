package japp.control.network;

import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.control.network.exceptions.NetworkException;

public class HttpCommunicator {

	private  static Logger log = LogManager.getLogger(HttpCommunicator.class);

	private HttpCommunicator() {}

	public static String send(String requestUrl) throws NetworkException {
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
			throw new NetworkException();
		} finally {
			try {
				client.close(); 
			} catch (Exception e) {
				log.error("Error at close connection.", e);
			}
		}
	}	
}
