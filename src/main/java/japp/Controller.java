package japp;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	private Controller() {
		LookAndFeel.set();
		mainWindow = MainWindow.createAndShowGui();
		sessionLess = new SessionLess();
		populateGenres();
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
			log.debug("###### "+genre.toString());
		} catch (NetworkException e) {
			log.error("Failed to fetch data for genre: name="+genreName+", id="+genreId);
		}
	}

	/**
	 * Method called when user hits the button.
	 */ 
	public void onGenresButtonClick() {
		populateGenres();
		mainWindow.clearTextArea();
		return;
	}

	public void handleSelectGenre(String genreName) {
		populateMovies(genreName);
	}

	public void handleSelectMovie(Movie movie) {
		mainWindow.presentMovie(movie);		
	}
	public void handleExit() {
		System.exit(0); // NOSONAR			
	}

}
