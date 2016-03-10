package japp;

import javax.security.sasl.AuthenticationException;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.control.network.Session;
import japp.control.network.SessionLess;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.Genre;
import japp.model.movies.GenreList;

public class MainClass {

	private static Logger log = LogManager.getLogger(MainClass.class);

	public static void main(String[] args) {	
		Controller controller = Controller.getInstance();
		
//		MainClass mainClass = new MainClass();
////		mainClass.authenticate();
//		mainClass.getGenres();

	}

	private Session authenticate() {
		Session session;
		try {
			session = new Session();			
		} catch (NetworkException|AuthenticationException e) {
			failure(e);
			return null;
		}

		return session;
	}

	private static void failure(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	}


	public void getGenres() {
		SessionLess sessionLess = new SessionLess();
		try {
			GenreList genres = sessionLess.fetchAllGenres();
			log.debug(genres.toString());
		} catch (NetworkException e) {
			log.error("ERROR ERROR", e);
		}
		getComedyMovies();
	}
	public void getComedyMovies() {
		SessionLess sessionLess = new SessionLess();
		try {
			Genre genre = sessionLess.fetchMoviesInGenre("35");
			log.debug(genre.toString());
		} catch (NetworkException e) {
			log.error("ERROR ERROR", e);
		}
	}

	//	public void getMovies(String genreId) {
	//		SessionLess sessionLess = new SessionLess();
	//		try {
	//			Genre genre = sessionLess.fetchMoviesInGenre(genreId);
	//			log.debug(genres.toString());
	//		} catch (NetworkException e) {
	//			log.error("ERROR ERROR", e);
	//		}
	//	}


}
