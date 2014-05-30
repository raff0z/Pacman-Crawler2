package it.uniroma3.giw.crawler;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlerMyMovies extends Crawler {

	private String firstPage;
	private String folderName;

	public CrawlerMyMovies(String firstPage, String folderName) {
		this.firstPage = firstPage;
		this.folderName = folderName;
	}

	private String modifyUrl(String urlToModify) {

		String urlToGet = this.firstPage + "/" + urlToModify;

		return urlToGet;
	}

	@Override
	public String getFirstPage() {
		// TODO Auto-generated method stub
		return this.firstPage;
	}

	@Override
	public String getFolderName() {
		// TODO Auto-generated method stub
		return this.folderName;
	}

	@Override
	public List<HtmlElement> getListHtmlElem(HtmlPage htmlPage) {
		// TODO Auto-generated method stub
		return (List<HtmlElement>) htmlPage
				.getByXPath("//tr/td[@valign = 'top']//h2");
	}

	@Override
	public String getHref(HtmlElement he) {
		// TODO Auto-generated method stub
		HtmlElement h1a = he.getHtmlElementsByTagName("a").get(0);

		return h1a.getAttribute("href");
	}

	@Override
	public String getNextUrl(HtmlPage htmlPage) {
		// TODO Auto-generated method stub
		HtmlElement elemNextPage = htmlPage
				.getFirstByXPath("//*[contains(text(),'successiva')]");

		String urlNext = elemNextPage.getAttribute("href");

		urlNext = this.modifyUrl(urlNext);
		return urlNext;
	}

}
