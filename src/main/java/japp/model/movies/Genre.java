package japp.model.movies;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import japp.control.json.JsonHelper;

public class Genre {
	public enum Keys {
		ID("id"),
		RESULTS("results"),
		PAGE("page"),
		TOTAL_PAGES("total_pages"),
		TOTAL_RESULT("total_results");
		private String key;
		Keys(String key) {
			this.key =  key;
		}
		protected String getKey() {
			return key;
		}

	}

	private  static Logger log = LogManager.getLogger(Genre.class);

	// Genre identification.
	private String id;
	private String name;

	private String page;
	private List<Movie> movies = new ArrayList<>();
	private String totalPages;
	private String totalResults;

	/**
	 * CTOR, 
	 * @param jsonString
	 */
	public Genre(String jsonString) {
		parse(jsonString);
	}

	private void parse(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		JSONArray results = obj.getJSONArray(Keys.RESULTS.getKey());
		String movieString="";
		Movie movie;
		for(int i = 0 ; i < results.length(); i++) {
			try {
				movie = new Movie((JSONObject)results.get(i));
				movies.add(movie);
			} catch (JSONException e) {
				log.error("Cannot create Movie. Not a valid json object: "+movieString, e);
			}
			movieString = "";  // avoid previous film to be logged at exception
		} 

		id = JsonHelper.getValue(Keys.ID.getKey(), jsonString);
		page = JsonHelper.getValue(Keys.PAGE.getKey(), jsonString);
		totalPages = JsonHelper.getValue(Keys.TOTAL_PAGES.getKey(), jsonString);
		totalResults = JsonHelper.getValue(Keys.TOTAL_RESULT.getKey(), jsonString);	
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id="+id);
		sb.append("\n");
		sb.append("name="+name);
		sb.append("\n");
		sb.append("page="+page);
		sb.append("\n");
		sb.append("totalPages="+totalPages);
		sb.append("\n");
		sb.append("totalResults="+totalResults);
		for(Movie movie: movies) {
			sb.append("\n");
			sb.append("movie="+movie.toString());
		}
		return sb.toString();
	}	

	//@Override
	//	public String toString() {
	//		StringBuilder sb = new StringBuilder();
	//		sb.append("id="+id);
	//		sb.append("\n");
	//		sb.append("page="+page);
	//		sb.append("\n");
	//		sb.append("totalPages="+totalPages);
	//		sb.append("\n");
	//		sb.append("totalResults="+totalResults);
	//		sb.append("\n");
	//		sb.append("\n");
	//		for(Movie movie: movies) {
	//			sb.append(movie.toString());
	//			sb.append("\n---------------------\n");
	//		}
	//		return sb.toString();
	//	}
}

/* 
	get genre (with id = 53): http://api.themoviedb.org/3/genre/53/movies?api_key=8c92bdaa90b74ce495f535d3bb9849bb
{
  "id": 53,
  "page": 1,
  "results": [{
    "adult": false,
    "backdrop_path": "/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg",
    "genre_ids": [878, 53, 28, 12],
    "id": 76341,
    "original_language": "en",
    "original_title": "Mad Max: Fury Road",
    "overview": "An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.",
    "release_date": "2015-05-13",
    "poster_path": "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
    "popularity": 35.748131,
    "title": "Mad Max: Fury Road",
    "video": false,
    "vote_average": 7.4,
    "vote_count": 4040
  }, {
    "adult": false,
    "backdrop_path": "/uS1SkjVviraGfFNgkDwe7ohTm8B.jpg",
    "genre_ids": [37, 18, 12, 53],
    "id": 281957,
    "original_language": "en",
    "original_title": "The Revenant",
    "overview": "In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.",
    "release_date": "2015-12-25",
    "poster_path": "/5W794ugjRwYx6IdFp1bXJqqMWRg.jpg",
    "popularity": 21.610659,
    "title": "The Revenant",
    "video": false,
    "vote_average": 7.3,
    "vote_count": 1871
  }, {
    "adult": false,
    "backdrop_path": "/dkMD5qlogeRMiEixC4YNPUvax2T.jpg",
    "genre_ids": [28, 12, 878, 53],
    "id": 135397,
    "original_language": "en",
    "original_title": "Jurassic World",
    "overview": "Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.",
    "release_date": "2015-06-09",
    "poster_path": "/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg",
    "popularity": 21.430124,
    "title": "Jurassic World",
    "video": false,
    "vote_average": 6.7,
    "vote_count": 4060
  }, {
    "adult": false,
    "backdrop_path": "/sSvgNBeBNzAuKl8U8sP50ETJPgx.jpg",
    "genre_ids": [18, 9648, 53, 37],  "total_pages": 799,
  "total_results": 15971

    "id": 273248,
    "original_language": "en",
    "original_title": "The Hateful Eight",
    "overview": null,
    "release_date": "2015-12-25",
    "poster_path": "/fqe8JxDNO8B8QfOGTdjh6sPCdSC.jpg",
    "popularity": 16.352982,
    "title": "The Hateful Eight",
    "video": false,
    "vote_average": 7.3,
    "vote_count": 1164
  }, {
    "adult": false,
    "backdrop_path": "/bIlYH4l2AyYvEysmS2AOfjO7Dn8.jpg",
    "genre_ids": [878, 28, 53, 12],
    "id": 87101,
    "original_language": "en",
    "original_title": "Terminator Genisys",
    "overview": "The year is 2029. John Connor, leader of the resistance continues the war against the machines. At the Los Angeles offensive, John's fears of the unknown future begin to emerge when TECOM spies reveal a new plot by SkyNet that will attack him from both fronts; past and future, and will ultimately change warfare forever.",
    "release_date": "2015-06-23",
    "poster_path": "/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg",
    "popularity": 16.645606,
    "title": "Terminator Genisys",
    "video": false,
    "vote_average": 6.1,
    "vote_count": 1851
  }, {
    "adult": false,
    "backdrop_path": "/t3Oea7KbSpOvuqddMnlFtZ4WHn.jpg",
    "genre_ids": [18, 36, 53],
    "id": 314365,
    "original_language": "en",
    "original_title": "Spotlight",
    "overview": "The true story of how The Boston Globe uncovered the massive scandal of child molestation and cover-up within the local Catholic Archdiocese, shaking the entire Catholic Church to its core.",
    "release_date": "2015-11-06",
    "poster_path": "/ngKxbvsn9Si5TYVJfi1EGAGwThU.jpg",
    "popularity": 16.540798,
    "title": "Spotlight",
    "video": false,
    "vote_average": 7.7,
    "vote_count": 588
  }, {
    "adult": false,
    "backdrop_path": "/cBlnfR0n1GA2vPoUQNcbL9pb3VW.jpg",
    "genre_ids": [28, 53],
    "id": 336004,
    "original_language": "en",
    "original_title": "Heist",
    "overview": "A father is without the means to pay for his daughter's medical treatment. As a last resort, he partners with a greedy co-worker to rob a casino. When things go awry they're forced to hijack a city bus.",
    "release_date": "2015-11-13",
    "poster_path": "/t5tGykRvvlLBULIPsAJEzGg1ylm.jpg",
    "popularity": 16.235353,
    "title": "Heist",
    "video": false,
    "vote_average": 5.5,
    "vote_count": 116
  }, {
    "adult": false,
    "backdrop_path": "/mFb0ygcue4ITixDkdr7wm1Tdarx.jpg",
    "genre_ids": [28, 53],
    "id": 245891,
    "original_language": "en",
    "original_title": "John Wick",
    "overview": "After the sudden death of his beloved wife, John Wick receives one last gift from her, a beagle puppy named Daisy, and a note imploring him not to forget how to love. But John's mourning is interrupted when his 1969 Boss Mustang catches the eye of sadistic thug Iosef Tarasov who breaks into his house and steals it, beating John unconscious in the process. Unwittingly, he has just reawakened one of the most brutal assassins the underworld has ever known.",
    "release_date": "2014-10-22",
    "poster_path": "/b9uYMMbm87IBFOq59pppvkkkgNg.jpg",
    "popularity": 14.188395,
    "title": "John Wick",
    "video": false,
    "vote_average": 7.1,
    "vote_count": 1819
  }, {
    "adult": false,
    "backdrop_path": "/L5QRL1O3fGs2hH1LbtYyVl8Tce.jpg",
    "genre_ids": [878, 53, 12],
    "id": 262500,
    "original_language": "en",
    "original_title": "Insurgent",
    "overview": "Beatrice Prior must confront her inner demons and continue her fight against a powerful alliance which threatens to tear her society apart.",
    "release_date": "2015-03-18",
    "poster_path": "/aBBQSC8ZECGn6Wh92gKDOakSC8p.jpg",
    "popularity": 10.873043,
    "title": "Insurgent",
    "video": false,
    "vote_average": 6.6,
    "vote_count": 1652
  }, {
    "adult": false,
    "backdrop_path": "/ypyeMfKydpyuuTMdp36rMlkGDUL.jpg",
    "genre_ids": [28, 80, 53],
    "id": 168259,
    "original_language": "en",
    "original_title": "Furious 7",
    "overview": "Deckard Shaw seeks revenge against Dominic Toretto and his family for his comatose brother.",
    "release_date": "2015-04-01",
    "poster_path": "/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg",
    "popularity": 10.502418,
    "title": "Furious 7",
    "video": false,
    "vote_average": 7.4,
    "vote_count": 2284
  }, {
    "adult": false,
    "backdrop_path": "/a8V8xk4eLOt3EEz1LHAGz2EC2UJ.jpg",
    "genre_ids": [53, 18],
    "id": 296098,
    "original_language": "en",
    "original_title": "Bridge of Spies",
    "overview": "During the Cold War, the Soviet Union captures U.S. pilot Francis Gary Powers after shooting down his U-2 spy plane. Sentenced to 10 years in prison, Powers' only hope is New York lawyer James Donovan, recruited by a CIA operative to negotiate his release. Donovan boards a plane to Berlin, hoping to win the young man's freedom through a prisoner exchange. If all goes well, the Russians would get Rudolf Abel, the convicted spy who Donovan defended in court.",
    "release_date": "2015-10-15",
    "poster_path": "/xPCNA8zJxyyFKTj47QpvkXHukzB.jpg",
    "popularity": 10.266959,
    "title": "Bridge of Spies",
    "video": false,
    "vote_average": 6.9,
    "vote_count": 846
  }, {
    "adult": false,
    "backdrop_path": "/vZMSji6u1Kfg5TcWWi4IAzfqXfC.jpg",
    "genre_ids": [28, 12, 53],
    "id": 177677,
    "original_language": "en",
    "original_title": "Mission: Impossible – Rogue Nation",
    "overview": "Ethan and team take on their most impossible mission yet, eradicating the Syndicate - an International rogue organization as highly skilled as they are, committed to destroying the IMF.",
    "release_date": "2015-07-23",
    "poster_path": "/z2sJd1OvAGZLxgjBdSnQoLCfn3M.jpg",
    "popularity": 9.734822,
    "title": "Mission: Impossible – Rogue Nation",
    "video": false,
    "vote_average": 7.1,
    "vote_count": 1838
  }, {
    "adult": false,
    "backdrop_path": "/nnMC0BM6XbjIIrT4miYmMtPGcQV.jpg",
    "genre_ids": [18, 28, 80, 53],
    "id": 155,
    "original_language": "en",
    "original_title": "The Dark Knight",
    "overview": "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.",
    "release_date": "2008-07-16",
    "poster_path": "/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg",
    "popularity": 9.71648,
    "title": "The Dark Knight",
    "video": false,
    "vote_average": 8.0,
    "vote_count": 6801
  }, {
    "adult": false,
    "backdrop_path": "/tPAV8vSLIlEDHDPWg7jicNR2qsk.jpg",
    "genre_ids": [28, 80, 53],
    "id": 267860,
    "original_language": "en",
    "original_title": "London Has Fallen",
    "overview": "In London for the Prime Minister's funeral, Mike Banning discovers a plot to assassinate all the attending world leaders.",
    "release_date": "2016-01-22",
    "poster_path": "/nK7poFj5zwywfOaWMUkVByvDMMl.jpg",
    "popularity": 6.416536,
    "title": "London Has Fallen",
    "video": false,
    "vote_average": 4.0,
    "vote_count": 96
  }, {
    "adult": false,
    "backdrop_path": "/83nHcz2KcnEpPXY50Ky2VldewJJ.jpg",
    "genre_ids": [12, 878, 53],
    "id": 131631,
    "original_language": "en",
    "original_title": "The Hunger Games: Mockingjay - Part 1",
    "overview": "Katniss Everdeen reluctantly becomes the symbol of a mass rebellion against the autocratic Capitol.",
    "release_date": "2014-11-19",
    "poster_path": "/cWERd8rgbw7bCMZlwP207HUXxym.jpg",
    "popularity": 8.16945,
    "title": "The Hunger Games: Mockingjay - Part 1",
    "video": false,
    "vote_average": 6.8,
    "vote_count": 2605
  }, {
    "adult": false,
    "backdrop_path": "/cA6NLdvWAhqJrNLSvh7hYtY1t0s.jpg",
    "genre_ids": [28, 878, 53],
    "id": 271110,
    "original_language": "en",
    "original_title": "Captain America: Civil War",
    "overview": "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.",
    "release_date": "2016-04-27",
    "poster_path": "/kdXCb1Km4r7Om2G2uvYZPQJy4wG.jpg",
    "popularity": 9.149567,
    "title": "Captain America: Civil War",
    "video": false,
    "vote_average": 7.6,
    "vote_count": 17
  }, {
    "adult": false,
    "backdrop_path": "/yTbPPmLAn7DiiM0sPYfZduoAjB.jpg",
    "genre_ids": [28, 9648, 878, 53],
    "id": 198663,
    "original_language": "en",
    "original_title": "The Maze Runner",
    "overview": "Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow \"runners\" for a shot at escape.",
    "release_date": "2014-09-10",
    "poster_path": "/rtQmqGnO0q1IdwdjFL6WdunQU6i.jpg",
    "popularity": 8.319456,
    "title": "The Maze Runner",
    "video": false,
    "vote_average": 7.0,
    "vote_count": 2668
  }, {
    "adult": false,
    "backdrop_path": "/cUfGqafAVQkatQ7N4y08RNV3bgu.jpg",
    "genre_ids": [28, 18, 53],
    "id": 254128,
    "original_language": "en",
    "original_title": "San Andreas",
    "overview": "In the aftermath of a massive earthquake in California, a rescue-chopper pilot makes a dangerous journey across the state in order to rescue his estranged daughter.",
    "release_date": "2015-05-27",
    "poster_path": "/qey0tdcOp9kCDdEZuJ87yE3crSe.jpg",
    "popularity": 8.180433,
    "title": "San Andreas",
    "video": false,
    "vote_average": 6.1,
    "vote_count": 1550
  }, {
    "adult": false,
    "backdrop_path": "/qcb6z1HpokTOKdjqDTsnjJk0Xvg.jpg",
    "genre_ids": [36, 18, 53, 10752],
    "id": 205596,
    "original_language": "en",
    "original_title": "The Imitation Game",
    "overview": "Based on the real life story of legendary cryptanalyst Alan Turing, the film portrays the nail-biting race against time by Turing and his brilliant team of code-breakers at Britain's top-secret Government Code and Cypher School at Bletchley Park, during the darkest days of World War II.",
    "release_date": "2014-11-14",
    "poster_path": "/noUp0XOqIcmgefRnRZa1nhtRvWO.jpg",
    "popularity": 8.022757,
    "title": "The Imitation Game",
    "video": false,
    "vote_average": 8.0,
    "vote_count": 2601
  }, {
    "adult": false,
    "backdrop_path": "/vj4IhmH4HCMZYYjTMiYBybTWR5o.jpg",
    "genre_ids": [53],
    "id": 241251,
    "original_language": "en",
    "original_title": "The Boy Next Door",
    "overview": "A recently cheated on married woman falls for a younger man who has moved in next door, but their torrid affair soon takes a dangerous turn.",
    "release_date": "2015-01-23",
    "poster_path": "/h28t2JNNGrZx0fIuAw8aHQFhIxR.jpg",
    "popularity": 7.924886,
    "title": "The Boy Next Door",
    "video": false,
    "vote_average": 5.2,
    "vote_count": 279
  }],
  "total_pages": 799,
  "total_results": 15971
}
 */