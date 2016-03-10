package japp;

import javax.security.sasl.AuthenticationException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.control.network.Session;
import japp.control.network.SessionLess;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.Genre;
import japp.model.movies.GenreList;
import japp.model.movies.Movie;
import japp.view.MainWindow;
import japp.view.look.LookAndFeel;

/**
 * Top controller.
 * 
 * Makes everything on the EDT. For a real application use a {@link SwingWorker}.
 * 
 * @author svante
 *
 */
public class Controller {

	private static Logger log = LogManager.getLogger(Controller.class);
	private static Controller instance;
	private MainWindow mainWindow;
	private SessionLess sessionLess;
	private GenreList genreList;

	private boolean showHint = true;

	/**
	 * CTOR
	 */
	private Controller() {
		LookAndFeel.set();
		mainWindow = MainWindow.createAndShowGui();
		sessionLess = new SessionLess();
		populateGenres();
	}

	public static void start() {
		instance = new Controller();		
		JOptionPane.showMessageDialog(null, "View genre contents by pressing RETURN when selected", "INFO", JOptionPane.INFORMATION_MESSAGE);
	}

	public static Controller getInstance() {
		if(instance==null) {
			instance = new Controller();
		}
		return instance;
	}

	private void populateGenres() {
		if(genreList == null) {
			try {
				this.genreList = sessionLess.fetchAllGenres();
			} catch (NetworkException e) {
				log.error("Could not read genres.", e);
			}
		}
		mainWindow.populateGenresListPanel(genreList);
		mainWindow.repaint("ALL GENRES");
	}

	private void populateMovies(String genreName) {
		String genreId = genreList.getIdFromName(genreName);
		log.debug("found id="+genreId+" from name="+genreName);
		Genre genre;
		try {
			genre = sessionLess.fetchMoviesInGenre(genreId);
			mainWindow.populateMoviesListPanel(genre);
			mainWindow.repaint(genreName.toUpperCase()+ " MOVIES");
		} catch (NetworkException e) {
			log.error("Failed to fetch data for genre: name="+genreName+", id="+genreId, e);
			return;
		}
		if(showHint) {
			JOptionPane.showMessageDialog(null, "View movie contents by selecting a movie", "INFO", JOptionPane.INFORMATION_MESSAGE);
			showHint = false;
		}
	}

	// TODO: authenticate not used right now, but it works.
	/**
	 * Authenticate. For some parts of the TMDb API you need a session ID. 
	 * @return Session containing session ID.
	 */
	public Session authenticate() {
		Session session;
		try {
			session = new Session();			
		} catch (NetworkException|AuthenticationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			log.error("Could not start session.", e);
			return null;
		}
		return session;
	}

	/**
	 * Method called when user hits the button.
	 */ 
	public void onUserActionButtonClick() {
		populateGenres();
		mainWindow.clearTextArea();
		return;
	}

	/**
	 * Method called when user hits RETURN to select a genre.
	 */ 
	public void onUserActionSelectGenre(String genreName) {
		populateMovies(genreName);
	}

	/**
	 * Method called when user moves to another list cell in the movie list.
	 */ 
	public void onUserActionSelectMovie(Movie movie) {
		mainWindow.presentMovie(movie);		
	}
}
