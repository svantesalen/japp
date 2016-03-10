package japp.view.components;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.model.movies.Movie;
import japp.view.look.JappTheme;


/**
 * A text area used for:
 * <ul>
 * <li>list all copies within the selected {@link FileWithCopies}</li>
 * <li>show messages during search</li>
 * </ul>
 * 
 * @author svante
 * 
 */
public class JappTextArea  {
	private static Logger log = LogManager.getLogger(JappTextArea.class);
	
	private static final String BORDER_NAME = "DETAILS";
	private static JappTextArea instance;
	private JTextPane textPane  = new JTextPane();
	private JScrollPane scrollPane;
	private JappTextAreaStyledDocument styledDoc;
	private TitledBorder border;
	private StyleWriter styleWriter;

	/**
	 * CTOR
	 */
	public JappTextArea() {
		instance = this;

		textPane.setText("");
		textPane.setBackground(JappTheme.bgColor);
		scrollPane  = new JScrollPane(textPane);
		scrollPane.setBackground(JappTheme.bgColor);

		initiateBorders();
		initiateContents();

	}

	public static JappTextArea getInstance() {
		return instance;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setCaretTopOfDoc() {
		textPane.setCaretPosition(0);
	}

	public JappTextAreaStyledDocument getjappTextAreaStyledDocument() {
		return styledDoc;
	}

	public void initiateContents() {
		textPane.setEditable(false);
		textPane.setText("");
		styledDoc = new JappTextAreaStyledDocument(textPane);
		styleWriter = new StyleWriter(this, styledDoc);
	}

	public void setText(Movie movie) {
		textPane.setText("");
		styleWriter.writeMovie(movie);
		repaint(movie.getTitle());
	}

	public void clear() {
		log.debug("#######################CLEAR##########");
		textPane.setText(" ");		
		textPane.setText("");		
	}
	
	public String getText() {
		return textPane.getText();
	}

	private void initiateBorders() {
		border = BorderFactory.createTitledBorder(BORDER_NAME);
		border.setTitleFont(JappTheme.borderTitleFont);
		border.setTitleColor(JappTheme.borderTitleColor);
		textPane.setBorder(border);

		Border emptyBorder = BorderFactory.createEmptyBorder();
		scrollPane.setBorder(emptyBorder);		
	}


	public void repaint() {
		repaint(BORDER_NAME);
	}
	public void repaint(String borderName) {
		border.setTitle(borderName);
		textPane.repaint();
	}

}