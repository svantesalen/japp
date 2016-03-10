package japp.control.network;

import japp.control.network.UrlBuild.Attribute;
import japp.control.network.UrlBuild.Paths;
import japp.control.network.exceptions.NetworkException;
import japp.model.movies.Genre;
import japp.model.movies.GenreList;

/**
 * Handles the server interaction for session-less requests.
 * @author svante
 *
 */
public class SessionLess {

	/**
	 * Get a list of all genres.
	 * @return
	 * @throws NetworkException
	 */
	public GenreList fetchAllGenres() throws NetworkException {
		String requestUrl = Paths.GENRE_LIST.buildUrl();
		String jsonString = HttpCommunicator.send(requestUrl);
		return new GenreList(jsonString);		
	}

	/**
	 * Get contents for a given genre. <br>
	 * This is default. Fetch only first page with only top rated and no adult.
	 * 
	 * @param id The id of the genre.
	 * @return A {@link Genre} that contains a list films in that genre.
	 * @throws NetworkException
	 */
	public Genre fetchMoviesInGenre(String id) throws NetworkException {
		String requestUrl = Paths.GENRE.buildUrl(id);
		String jsonString = HttpCommunicator.send(requestUrl);
		return new Genre(jsonString);
	}

	/**
	 * Fetch contents for a given genre. <br>
	 * @param id The id of the genre
	 * @param page The page number (contents is divided into pages)
	 * @param includeAllMoview if false then only top rated are fetched
	 * @param includeAdult if false then no dirty shit is fetched
	 * @return The {@link Genre} object holding all data.
	 * @throws NetworkException In case there is no Internet connection.
	 */
	public Genre fetchMoviesInGenre(String id, int page, 
			boolean includeAllMoview, boolean includeAdult ) throws NetworkException {
		String requestUrl = Paths.GENRE.buildUrl(id);
		requestUrl = UrlBuild.addParameter(requestUrl, Attribute.PAGE.key(), Integer.toString(page));
		requestUrl = UrlBuild.addParameter(requestUrl, Attribute.INCLUDE_ADULT.key(), Boolean.toString(includeAllMoview));
		requestUrl = UrlBuild.addParameter(requestUrl, Attribute.INCLUDE_ALL_MOVIES.key(), Boolean.toString(includeAdult));
		String jsonString = HttpCommunicator.send(requestUrl);
		return new Genre(jsonString);
	}

//	/**
//	 * Fetch movie details.
//	 * @param id
//	 * @return
//	 * @throws NetworkException
//	 */
//	public Movie fetchMovieDetails(String id) throws NetworkException {
//		String requestUrl = Paths.GENRE.buildUrl(id);
//		String jsonString = HttpCommunicator.send(requestUrl);
//		return new Movie(jsonString);
//	}
}


