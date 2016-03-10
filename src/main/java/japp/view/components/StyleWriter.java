package japp.view.components;

import japp.model.movies.Movie;

/**
 * Writes styled text to be put out in a text area. Style will depend on the contents.
 * 
 * @author svante
 *
 */
public class StyleWriter {

	private JappTextArea textArea;
	private JappTextAreaStyledDocument styledDoc;

	public StyleWriter(JappTextArea textArea, JappTextAreaStyledDocument styledDoc) {
		this.textArea = textArea;
		this.styledDoc = styledDoc;
	}

	/**
	 * Write movie details.
	 * @param movie 
	 */
	public void writeMovie(Movie movie) {
		textArea.initiateContents();
		styledDoc.insertInfoText("\n");
		styledDoc.insertTitleText(movie.getTitle());
		styledDoc.insertInfoText("\n");
		styledDoc.insertOverviewText(movie.getOverview());
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText(movie.getTagline());
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText("From "+movie.getReleaseDate());
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText("Popularity: "+movie.getPopulatity());
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText("Vote average: "+movie.getVoteAverage());
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText("Vote cont: "+movie.getVoteCount());
		styledDoc.insertInfoText("\n");
		styledDoc.insertInfoText("ID: "+movie.getId());
		styledDoc.insertInfoText("\n");
		textArea.setCaretTopOfDoc();
	}
}
