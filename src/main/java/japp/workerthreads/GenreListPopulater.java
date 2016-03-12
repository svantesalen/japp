package japp.workerthreads;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.Controller;
import japp.control.network.SessionLess;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.GenreList;
import japp.view.MainWindow;


/**
 * Swing worker thread to avoid user interface to hang meanwhile.
 * @author svante
 *
 */
public class GenreListPopulater extends SwingWorker<GenreList, String> {
	private static Logger log = LogManager.getLogger(GenreListPopulater.class);
	private SessionLess sessionLess;

	/**
	 * CTOR. 
	 */
	public GenreListPopulater(SessionLess sessionLess) {
		this.sessionLess = sessionLess;
	}

	@Override
	protected GenreList doInBackground() throws NetworkException {
		MainWindow.getInstance().fetchingDataOngoing(true);
		return sessionLess.fetchAllGenres();
	}

	/**
	 * Receive the return value from {@link GenreListPopulater#doInBackground()}
	 */
	@Override
	protected void done() {
		MainWindow.getInstance().fetchingDataOngoing(false);

		GenreList genreList;
		try {
			genreList = get();
			Controller.getInstance().setGenreList(genreList);
			MainWindow.getInstance().populateGenresListPanel(genreList);
			MainWindow.getInstance().repaint("ALL GENRES");
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error at fetching genres.", e);
		}
	}	
}