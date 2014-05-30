package it.uniroma3.giw.main;

import it.uniroma3.giw.crawler.Crawler;
import it.uniroma3.giw.crawler.CrawlerMyMovies;
import it.uniroma3.giw.crawler.CrawlerRepubblica;
import it.uniroma3.giw.crawler.CrawlerSpazioGames;
import it.uniroma3.giw.crawler.CrawlerTmw;

public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis()/1000;
		
		String sportPage = "http://www.tuttomercatoweb.com/serie-a/";
		String newsPage = "http://www.repubblica.it/cronaca/";
		String moviePage = "http://www.mymovies.it/film/2013/";
		String gamePage = "http://www.spaziogames.it/recensioni_videogiochi/";
		
		Crawler crawlerTmw = new CrawlerTmw(sportPage,"sport");
		crawlerTmw.doCrawling();
		
		Crawler crawlerRepubblica = new CrawlerRepubblica(newsPage, "news");
		crawlerRepubblica.doCrawling();
		
		Crawler crawlerMyMovies = new CrawlerMyMovies(moviePage, "movie");
		crawlerMyMovies.doCrawling();
		
		Crawler crawlerSpazioGames = new CrawlerSpazioGames(gamePage, "game");
		crawlerSpazioGames.doCrawling();
		
		long end = System.currentTimeMillis()/1000;		
		System.out.println("Tempo totale impiegato: " + (end-start));
		
	}	
	
}
