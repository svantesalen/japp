package japp.workerthreads;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.control.network.SessionLess;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.Genre;
import japp.model.movies.Movie;
import japp.view.MainWindow;


/**
 * Swing worker thread to avoid user interface to hang meanwhile.
 * @author svante
 *
 */
public class PostersFetcher extends SwingWorker<Void, String> {
	private static Logger log = LogManager.getLogger(PostersFetcher.class);
	private SessionLess sessionLess;
	private Genre genre;


	/**
	 * CTOR. 
	 */
	public PostersFetcher(SessionLess sessionLess, Genre genre) {
		this.sessionLess = sessionLess;
		this.genre = genre;
	}

	@Override
	protected Void doInBackground() throws NetworkException {
		MainWindow.getInstance().fetchingDataOngoing(true);
		for(Movie movie: genre.getMovies().values()) {
			 log.debug("Fetching poster for the movie "+movie.getTitle());
			 movie.setPoster(sessionLess.fetchPoster(movie));
		}
		log.debug("Done fetching posters for genre.");
		MainWindow.getInstance().fetchingDataOngoing(false);
		return null;
	}
}