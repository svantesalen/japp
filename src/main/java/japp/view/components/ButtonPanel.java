package japp.view.components;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.view.look.JappTheme;

/**
 * Panel with buttons.
 * 
 * @author svante
 *
 */
public class ButtonPanel {

	private static Logger log = LogManager.getLogger(ButtonPanel.class);
	private static ButtonPanel instance;

	private JPanel jPanel = new JPanel();
	private FocusableButton genresButton;

	/**
	 * CTOR
	 */
	public ButtonPanel() {
		instance = this;
		addComponents();
		jPanel.setBackground(JappTheme.bgColor);
	}

	public static ButtonPanel getInstance() {
		return instance;
	}
	private void addComponents() {
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		genresButton = new GenresButton();
		genresButton.setBackground(JappTheme.bgColor);
		genresButton.setForeground(JappTheme.button_txt);
		genresButton.setFont(JappTheme.buttons);
		setBorder(genresButton);
		jPanel.add(genresButton);
	}

	public void setGenresFocus() {
		genresButton.requestFocus();
	}

	public void fetchingDataOngoing(boolean isFetching)  {
		genresButton.setEnabled(!isFetching);
	}


	private void setBorder(JButton button) {
		button.setBorder(
				BorderFactory.createCompoundBorder(button.getBorder(), 
						BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	public JPanel getPanel() {
		return jPanel;
	}
}
