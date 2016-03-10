package japp.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.Controller;

/**
 * A button that starts the find for duplicates.

 * @author svante
 *
 */
@SuppressWarnings("serial")
public class GenresButton extends FocusableButton { // NOSONAR

	private static Logger log = LogManager.getLogger(GenresButton.class);

	private static final String NAME = "GENRES";
	public GenresButton() {
		super(NAME);
	}

	@Override
	protected void handleKeyEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			log.debug("CR key code:"+e.getKeyCode());					
			onClick();

		} else {
			log.debug("Not a CR key code:"+e.getKeyCode());					
		}				
	}

	@Override
	protected void handleActionEvent(ActionEvent e) {
		onClick();
	} 

	private void onClick() {
		Controller.getInstance().onUserActionButtonClick();
	}

}
