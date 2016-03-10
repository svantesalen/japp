package japp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.model.movies.Genre;
import japp.model.movies.GenreList;
import japp.model.movies.Movie;
import japp.view.components.ButtonPanel;
import japp.view.components.JappListPanel;
import japp.view.components.JappTextArea;


/**
 * The applications main window.
 * 
 * @author svante
 *
 */
public class MainWindow {
	private static MainWindow instance;
	private static Logger log = LogManager.getLogger(MainWindow.class);

	private JappTextArea  jappTextArea = new JappTextArea();
	private JappListPanel jappListPanel = new JappListPanel();
	private ButtonPanel buttonPanel =  new ButtonPanel();

	private JFrame mainFrame;

	private MainWindow() {
		setup(); 
	}

	public static MainWindow createAndShowGui() {	
		instance =  new MainWindow();
		return instance;
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
		buttonPanel.setGenresFocus();
		GuiHelper.center(mainFrame);
	}

	public void clearTextArea() {
		jappTextArea.clear();
	}

	private void addComponentsToPane(JComponent contentPane) {
		contentPane.add(jappListPanel.getPanel(), BorderLayout.WEST);
		contentPane.add(jappTextArea.getScrollPane(), BorderLayout.CENTER);
		contentPane.add(buttonPanel.getPanel(), BorderLayout.SOUTH);
	}

	public void populateGenresListPanel(GenreList genreList) {
		jappListPanel.populate(genreList);
	}

	public void populateMoviesListPanel(Genre genre) {
		jappListPanel.populate(genre);
	}

	public void presentMovie(Movie movie) {
		jappTextArea.setText(movie);
		jappTextArea.repaint(movie.getTitle());
	}

	public void repaint(String borderName) {
		jappListPanel.repaint(borderName);
		jappTextArea.repaint();
	}
	
}
