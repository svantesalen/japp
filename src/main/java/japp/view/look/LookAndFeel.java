package japp.view.look;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Set basic look and feel for the application.
 * 
 * @author svante
 *
 */
public class LookAndFeel {

	private static Logger log = LogManager.getLogger(LookAndFeel.class);

	/**
	 * No CTOR needed.
	 */
	private LookAndFeel() {}

	public static void set() {
		setUIManagerLook();
	}
	
	private static void setUIManagerLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			log.error(e);
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (InstantiationException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		}

		UIManager.put("MenuItem.selectionBackground", Colors.lightWhite);
		UIManager.put("MenuItem.selectionForeground", JappTheme.bgColor);
		UIManager.put("Menu.selectionBackground", Colors.lightWhite);
		UIManager.put("Menu.selectionForeground", JappTheme.bgColor);
		UIManager.put("Menu.selectionForeground", JappTheme.bgColor);

		UIManager.put("ToolTip.background", Colors.white);    
		UIManager.put("ToolTip.foreground", JappTheme.bgColor);    
		Border border = BorderFactory.createLineBorder(Colors.white, 2);
		UIManager.put("ToolTip.border", border);

		UIManager.put("Panel.background", Colors.lighterGray);

		UIManager.put("Button.background",Colors.white);
		UIManager.put("Button.foreground",JappTheme.bgColor);

		Font font = new Font(JappTheme.getDefaultFontName(), Font.PLAIN, 20);
		UIManager.put("OptionPane.font", font);
		UIManager.put("OptionPane.messageFont", font);
		UIManager.put("OptionPane.buttonFont", font);
		UIManager.put("OptionPane.background", Colors.lighterGray);
		UIManager.put("OptionPane.errorDialog.border.background", JappTheme.bgColor);
		UIManager.put("OptionPane.errorDialog.titlePane.background", JappTheme.bgColor);
		UIManager.put("OptionPane.errorDialog.titlePane.foreground", JappTheme.bgColor);
		UIManager.put("OptionPane.errorDialog.titlePane.shadow", JappTheme.bgColor);
		UIManager.put("OptionPane.foreground", JappTheme.bgColor);
		UIManager.put("OptionPane.messageBackground", Colors.lighterGray);
		UIManager.put("OptionPane.messageForeground", JappTheme.bgColor);
		UIManager.put("OptionPane.questionDialog.titlePane.background", Colors.lighterGray);
		UIManager.put("OptionPane.questionDialog.titlePane.foreground",  JappTheme.bgColor);
		UIManager.put("OptionPane.questionDialog.titlePane.shadow",  JappTheme.bgColor);
		UIManager.put("OptionPane.questionDialog.border.background", Colors.lighterGray);
		UIManager.put("OptionPane.infoDialog.titlePane.background", Colors.lighterGray);
		UIManager.put("OptionPane.infoDialog.titlePane.foreground",  JappTheme.bgColor);
		UIManager.put("OptionPane.infoDialog.titlePane.shadow",  JappTheme.bgColor);
		UIManager.put("OptionPane.infoDialog.border.background", Colors.lighterGray);
		UIManager.put("OptionPane.warningDialog.border.background", Colors.lighterGray);
		UIManager.put("OptionPane.warningDialog.titlePane.background", Colors.lighterGray);
		UIManager.put("OptionPane.warningDialog.titlePane.foreground",  JappTheme.bgColor);
		UIManager.put("OptionPane.warningDialog.titlePane.shadow", JappTheme.bgColor);

//		border = BorderFactory.createEmptyBorder();
//		UIManager.put("ProgressBar", border);
		UIManager.put("ProgressBar.background", Colors.darkYellow);
		UIManager.put("ProgressBar.foreground", Colors.blueGreen);
		UIManager.put("ProgressBar.border", Colors.blueGreen);
		UIManager.put("ProgressBar.selectionBackground", Colors.red);
		UIManager.put("ProgressBar.selectionForeground", Colors.green);
	}
}