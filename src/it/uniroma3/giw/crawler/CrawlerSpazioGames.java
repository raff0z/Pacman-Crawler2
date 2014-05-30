package it.uniroma3.giw.crawler;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlerSpazioGames extends Crawler {

    private String firstPage;
    private String folderName;

    public CrawlerSpazioGames(String firstPage, String folderName) {
	this.firstPage = firstPage;
	this.folderName = folderName;
    }

    private String modifyUrl(String urlToModify) {

	String[] urlA = urlToModify.split("/");

	String urlToGet = this.firstPage + urlA[urlA.length - 3] + "/"
		+ urlA[urlA.length - 2];

	return urlToGet;
    }

    private String modifyNext(String urlToModify) {

	String[] urlA = urlToModify.split("/");

	String urlToGet = this.firstPage + urlA[urlA.length - 1];

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
		.getByXPath("//*[@id='tab_indice']//*[@title]");
    }

    @Override
    public String getHref(HtmlElement he) {
	// TODO Auto-generated method stub
	String url = he.getAttribute("href");

	return modifyUrl(url);
    }

    @Override
    public String getNextUrl(HtmlPage htmlPage) {
	// TODO Auto-generated method stub
	HtmlElement elemNextPage = htmlPage
		.getFirstByXPath("//*[@class='td_avanti']");

	List<HtmlElement> enpList = elemNextPage.getHtmlElementsByTagName("a");

	HtmlElement enp;

	// prendo l'ultimo link che sarebbe il tasto per la pagina successiva
	enp = enpList.get(enpList.size() - 1);

	String urlNext = enp.getAttribute("href");

	urlNext = this.modifyNext(urlNext);

	return urlNext;
    }

}
