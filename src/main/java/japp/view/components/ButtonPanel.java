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
	private FocusableButton findCopiesButton;
	private FocusableButton breakButton;
	private JButton helpButton;
	private JButton languageButton;
	private boolean isFinding = false;

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

	public boolean isFinding() {
		return isFinding;
	}

	private void addComponents() {
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		findCopiesButton = new FindButton("FIND");
		findCopiesButton.setBackground(JappTheme.bgColor);
		findCopiesButton.setForeground(JappTheme.button_txt);
		findCopiesButton.setFont(JappTheme.buttons);
		setBorder(findCopiesButton);
		jPanel.add(findCopiesButton);
	}

	private void setBorder(JButton button) {
		button.setBorder(
				BorderFactory.createCompoundBorder(button.getBorder(), 
						BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	public JPanel getPanel() {
		return jPanel;
	}

	public void setFindFocus() {
		findCopiesButton.requestFocus();
	}

	public void finding(boolean isFinding) {
		this.isFinding = isFinding;
		findCopiesButton.setVisible(!isFinding);
		breakButton.setVisible(isFinding);
	}
}
