package japp;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.control.network.SessionLess;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.Genre;
import japp.model.movies.GenreList;
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
				mainWindow.populateListPanel(genreList);
			} catch (NetworkException e) {
				log.error("Could not read genres.", e);
			}
			
		}
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

	/**
	 * Method called when user hits the button.
	 */ 
	public void onGenresButtonClick() {
		populateGenres();
		return;
	}

	public void handleExit() {
		System.exit(0); // NOSONAR			
	}

}
