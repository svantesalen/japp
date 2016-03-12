package japp.workerthreads;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.control.network.SessionLess;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.Genre;
import japp.view.MainWindow;


/**
 * Swing worker thread to avoid user interface to hang meanwhile.
 * @author svante
 *
 */
public class MovieListPopulater extends SwingWorker<Genre, String> {
	private static Logger log = LogManager.getLogger(MovieListPopulater.class);
	private SessionLess sessionLess;
	private String genreId;
	private String genreName;

	/**
	 * CTOR. 
	 */
	public MovieListPopulater(SessionLess sessionLess, String genreId, String genreName) {
		this.sessionLess = sessionLess;
		this.genreId = genreId;
		this.genreName = genreName;
	}

	@Override
	protected Genre doInBackground() throws NetworkException {
		MainWindow.getInstance().fetchingDataOngoing(true);
		return sessionLess.fetchMoviesInGenre(genreId);
	}

	/**
	 * Receive the return value from {@link MovieListPopulater#doInBackground()}
	 */
	@Override
	protected void done() {
		MainWindow.getInstance().fetchingDataOngoing(false);

		Genre genre;
		try {
			genre = get();
			MainWindow.getInstance().populateMoviesListPanel(genre);
			MainWindow.getInstance().repaint(genreName.toUpperCase()+ " MOVIES");
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error at fetching genres.", e);
		}
	}	
}