package japp.view.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.Controller;
import japp.model.movies.Genre;
import japp.model.movies.GenreList;
import japp.model.movies.Movie;
import japp.view.look.Colors;
import japp.view.look.JappTheme;

/**
 * A list of {@link Genres}.
 * 
 * @author svante
 *
 */
public class JappListPanel implements ListSelectionListener {
	private static final int MAX_ROWS = 18;

	private static Logger log = LogManager.getLogger(JappListPanel.class);
	private static JappListPanel instance;

	private JPanel jPanel = new JPanel();
	private DefaultListModel<String> jListModel = new DefaultListModel<>();
	private JList<String> jList;
	private TitledBorder border;
	private String cellWidth = "                                                          ";
	private String selectedValue;

	private boolean populatedWithMovies=false;

	/**
	 * CTOR
	 * @param genreList
	 */
	public JappListPanel() {
		instance=this;
		jList = new JList<>(jListModel);
		jList.setPrototypeCellValue(cellWidth);
		setupScrollableSelectionList(jListModel);
		JScrollPane listScrollPane = new JScrollPane(jList);
		initiateBorders(listScrollPane);
		jPanel.add(listScrollPane, BorderLayout.CENTER);
		jPanel.setBackground(JappTheme.bgColor);
		addListeners();
	}

	public void populate(GenreList genreList) {
		jListModel.clear();
		if(genreList.size() == 0) {
			return;
		}

		for(Entry<String, String> entry: genreList.getGenres().entrySet()) {
			jListModel.addElement(entry.getValue());
			jList.setSelectedIndex(0);
		}
		jList.requestFocus();
		populatedWithMovies=false;
	}

	// TODO need to keep track of what movie corresponds to what list entry. Is a better way?
	private List<Movie> movies;

	public void populate(Genre genre) {
		jListModel.clear();
		if(genre.size() == 0) {
			return;
		}
		movies = new ArrayList<>();
		Movie movie;
		for(Entry<String, Movie> entry: genre.getMovies().entrySet()) {
			movie = entry.getValue();
			jListModel.addElement(movie.getShortInfo());
			movies.add(movie);
			jList.setSelectedIndex(0);
		}
		jList.requestFocus();
		populatedWithMovies=true;
	}

	public void clear() {
		jListModel.clear();
	}

	private void setupScrollableSelectionList(DefaultListModel<String> jListModel) {
		jList.setModel(jListModel);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setSelectedIndex(0);
		jList.addListSelectionListener(this);
		jList.setVisibleRowCount(MAX_ROWS);
		jList.setFont(JappTheme.jListFont);
		jList.setBackground(JappTheme.bgColor);
		jList.setForeground(Colors.white);
		jList.setCellRenderer(getRenderer());
	}

	/**
	 * So we can set font and colors to a list cell.
	 * @return
	 */
	@SuppressWarnings("serial")
	private ListCellRenderer<? super String> getRenderer() {
		return new DefaultListCellRenderer() {  // NOSONAR
			@Override
			public Component getListCellRendererComponent(
					JList<?> list,
					Object value, 
					int index, 
					boolean isSelected,
					boolean cellHasFocus) 
			{
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
				listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(4, 1, 6, 4,JappTheme.bgColor));
				if(isSelected) {
					listCellRendererComponent.setBackground(JappTheme.jListFontSelectedItemColor);
					listCellRendererComponent.setFont(JappTheme.jListFontSelectedItem);
				}
				return listCellRendererComponent;
			}
		};
	}

	private void initiateBorders(JScrollPane listScrollPane) {		
		listScrollPane.setBorder(BorderFactory.createEmptyBorder());
		border = BorderFactory.createTitledBorder("...WAIT...");
		border.setTitleFont(JappTheme.borderTitleFont);
		border.setTitleColor(JappTheme.borderTitleColor);
		jPanel.setBorder(border);
	}

	/**
	 * List items can be selected by pressing RETURN or by double-click.
	 */
	private void addListeners() {
		// RETURN?
		jList.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased( KeyEvent e ) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleUserSelection();
				}
			}
			@Override
			public void keyTyped( KeyEvent e ) {/* EMPTY */}
			@Override
			public void keyPressed( KeyEvent e ) {/* EMPTY */}
		} );

		// Double-click?
		jList.addMouseListener(new MouseAdapter(){ // NOSONAR
		    @Override
		    public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
					handleUserSelection();
		        }
		    }
		});
	}
	
	private void handleUserSelection() {
		if(!populatedWithMovies) {
			Controller.getInstance().onUserActionSelectGenre(selectedValue);						
		}
	}

	public JPanel getPanel() {
		return jPanel;
	}

	public static JappListPanel getInstance() {	
		return instance; 
	}

	/**
	 * User selected another jList cell.
	 * @param e
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()||jList.getSelectedIndex()<0) {
			return;
		}
		selectedValue = jList.getSelectedValue();		
		log.debug("selectedValue: "+selectedValue);
		if(populatedWithMovies) {
			Movie selectedMovie = movies.get(jList.getSelectedIndex());
			Controller.getInstance().onUserActionSelectMovie(selectedMovie);
		}
	}

	public void repaint(String borderName) {
		border.setTitle(borderName);
		jPanel.repaint();
	}
}




