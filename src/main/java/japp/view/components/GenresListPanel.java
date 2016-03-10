package japp.view.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import japp.model.movies.GenreList;
import japp.view.look.Colors;
import japp.view.look.JappTheme;

/**
 * A list of {@link Genres}.
 * 
 * @author svante
 *
 */
public class GenresListPanel implements ListSelectionListener {
	private static final int MAX_ROWS = 18;
	private static String borderName = "GENRES";

	private static Logger log = LogManager.getLogger(GenresListPanel.class);
	private static GenresListPanel instance;

	private JPanel jPanel = new JPanel();
	private DefaultListModel<String> jListModel = new DefaultListModel<>();
	private JList<String> jList;
	private TitledBorder border;
	private String cellWidth = "                                  ";
	private String selectedValue;

	/**
	 * CTOR
	 * @param genreList
	 */
	public GenresListPanel() {
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
		return new DefaultListCellRenderer() { 
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
		border = BorderFactory.createTitledBorder(borderName);
		border.setTitleFont(JappTheme.borderTitleFont);
		border.setTitleColor(JappTheme.borderTitleColor);
		jPanel.setBorder(border);
	}

	private void addListeners() {
		jList.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped( KeyEvent e ) {/* EMPTY */}
			@Override
			public void keyPressed( KeyEvent e ) {/* EMPTY */}
			@Override
			public void keyReleased( KeyEvent e ) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Controller.getInstance().handleExit();
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Controller.getInstance().handleSelectGenre(selectedValue);
				}
			}
		} );
	}

	public JPanel getPanel() {
		return jPanel;
	}

	public static GenresListPanel getInstance() {	
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
	}

	public void repaint() {
		border.setTitle(borderName);
		jPanel.repaint();
	}

}




