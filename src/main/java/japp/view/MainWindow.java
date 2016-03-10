package japp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.model.movies.GenreList;
import japp.view.components.ButtonPanel;
import japp.view.components.GenresListPanel;
import japp.view.components.JappTextArea;


/**
 * The applications main window.
 * @author svante
 *
 */
public class MainWindow {
	private static MainWindow instance;
	private static Logger log = LogManager.getLogger(MainWindow.class);

	private JappTextArea  jappTextArea = new JappTextArea();
	private GenresListPanel genreListPanel = new GenresListPanel();
	private ButtonPanel buttonPanel =  new ButtonPanel();

	private JFrame mainFrame;

	private MainWindow() {
		setup(); 
	}

	public static void createAndShowGui() {	
		instance =  new MainWindow();
	}

	public static MainWindow getInstance() {
		return instance;
	}

	private void setup() {
		JComponent contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setOpaque(true); // Content panes has to be opaque.
		contentPane.setLayout(new BorderLayout());
		addComponentsToPane(contentPane);

		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(1000,710));
		mainFrame.add(contentPane);
		mainFrame.setContentPane(contentPane);
		mainFrame.pack();
		mainFrame.setVisible(true);		

		buttonPanel.setFindFocus();
//		GuiHelper.center(mainFrame);
	}

	public void setText(String text) {
		jappTextArea.setText(text);
	}

	public void addText(String text) {
		jappTextArea.addText(text);
	}

	public void addSearchInfoText(String text) {
		jappTextArea.addSearchInfoText(text);
	}

	private void addComponentsToPane(JComponent contentPane) {
		contentPane.add(genreListPanel.getPanel(), BorderLayout.WEST);
		contentPane.add(jappTextArea.getScrollPane(), BorderLayout.CENTER);
		contentPane.add(buttonPanel.getPanel(), BorderLayout.SOUTH);
	}

	public void populateListPanel(GenreList genreList) {
		genreListPanel.populate(genreList);
	}
	
	public void startFinding() {
		buttonPanel.finding(true);
		genreListPanel.clear();
}

	public void stopFinding() {
		buttonPanel.finding(false);
}

	public void repaint() {
		genreListPanel.repaint();
		jappTextArea.repaint();
	}
	
}
