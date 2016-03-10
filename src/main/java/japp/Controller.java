package japp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is in charge of the searching, though it delegates the job to a SwingWorker thread.
 * 
 * @author svante
 *
 */
public class Controller {

	private static Logger log = LogManager.getLogger(Controller.class);
	private static Controller instance;
	private Controller() {}

	public static Controller getInstance() {
		if(instance==null) {
			instance = new Controller();
		}
		return instance;
	}

	/**
	 * Method called when user hits the button.
	 */ 
	public void onFindCopiesButtonClick() {
		log.error("Not implemented");
		return;
	}

	public void handleExit() {
		System.exit(0); // NOSONAR			
	}

}
