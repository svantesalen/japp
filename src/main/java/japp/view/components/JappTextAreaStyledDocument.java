package japp.view.components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import japp.view.look.JappTheme;


/**
 * Document used to write formatted text in the text pane.
 * @author svante
 *
 */
public class JappTextAreaStyledDocument {

	private static Logger log = LogManager.getLogger(JappTextAreaStyledDocument.class);
	private StyledDocument doc;
	private Style titleStyle;
	private Style overviewStyle;
	private Style infoStyle;
	private Style tagLineStyle;
	private Style underlinedInfoStyle;

	/**
	 * CTOR
	 * @param textPane
	 */
	public JappTextAreaStyledDocument(JTextPane textPane) {		
		textPane.setEditable(false);
		textPane.setText("");
		doc = textPane.getStyledDocument();
		setTitleStyle();
		setOverviewStyleStyle();
		setTaglineStyle();
		setInfoStyle();
		setUnderlinedInfoStyle();
	}

	private void setTitleStyle() {
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		titleStyle = doc.addStyle("titleStyle",def);

		StyleConstants.setForeground(titleStyle,JappTheme.textAreaTitleColor);
		StyleConstants.setFontFamily(titleStyle, JappTheme.getDefaultFontName());
		StyleConstants.setBold(titleStyle, false);
		StyleConstants.setFontSize(titleStyle, 20);
	}

	private void setOverviewStyleStyle() {
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		overviewStyle = doc.addStyle("overviewStyle",def);

		StyleConstants.setForeground(overviewStyle,JappTheme.textAreaOverviewColor);
		StyleConstants.setFontFamily(overviewStyle, JappTheme.getDefaultFontName());
		StyleConstants.setBold(overviewStyle, false);
		StyleConstants.setFontSize(overviewStyle, 14);
	}

	private void setTaglineStyle() {
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		tagLineStyle = doc.addStyle("tagLineStyle",def);

		StyleConstants.setForeground(tagLineStyle,JappTheme.textAreaTaglineColor);
		StyleConstants.setFontFamily(tagLineStyle, JappTheme.getDefaultFontName());
		StyleConstants.setItalic(tagLineStyle, false);
		StyleConstants.setBold(tagLineStyle, false);
		StyleConstants.setFontSize(tagLineStyle, 14);
	}
	
	private void setInfoStyle() {
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		infoStyle = doc.addStyle("infoStyle",def);

		StyleConstants.setForeground(infoStyle,JappTheme.textAreaInfoColor);
		StyleConstants.setFontFamily(infoStyle, JappTheme.getDefaultFontName());
		StyleConstants.setItalic(infoStyle, false);
		StyleConstants.setBold(infoStyle, false);
		StyleConstants.setFontSize(infoStyle, 14);
	}
	
	private void setUnderlinedInfoStyle() {
		underlinedInfoStyle = doc.addStyle("underlinedInfoStyle",infoStyle);
		StyleConstants.setUnderline(underlinedInfoStyle, true);

	}

	public void insertImage(Image image) {
		if(image == null) {
	    	log.error("No image.");
			return;
		}
	    try {
	        Style style = doc.addStyle("imageStyle", null);
	        StyleConstants.setIcon(style, new ImageIcon(image));
	        doc.insertString(doc.getLength(), "ignored text", style);
	    } catch (Exception e){
	    	log.error("Could not load image.", e);
	    }
		}
	
	public void insertTitleText(String text) {
		insertStyledText(text, titleStyle);
	}

	public void insertOverviewText(String text) {
		insertStyledText(text, overviewStyle);
	}

	public void insertInfoText(String text) {
		insertStyledText(text, infoStyle);
	}

	public void insertUnderlinedInfoText(String text) {
		insertStyledText(text, underlinedInfoStyle);
	}

	public void insertStyledText(String text, Style style) {
		try {
			doc.insertString(doc.getLength(), text, style );
		} catch (BadLocationException e) {
			log.error(e);
		}
	}

}
