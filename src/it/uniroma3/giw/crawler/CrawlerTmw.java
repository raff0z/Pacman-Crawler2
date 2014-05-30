package it.uniroma3.giw.crawler;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlerTmw extends Crawler {

    private String firstPage;
    private String folderName;

    public CrawlerTmw(String fp, String fn) {
	this.firstPage = fp;
	this.folderName = fn;
    }

    @Override
    public List<HtmlElement> getListHtmlElem(HtmlPage htmlPage) {
	HtmlElement elem = htmlPage.getFirstByXPath("//ul");

	// prendo gli elementi con tag a
	return elem.getHtmlElementsByTagName("a");
    }

    @Override
    public String getHref(HtmlElement he) {

	String url = he.getAttribute("href");

	return this.modifyUrl(url);
    }

    @Override
    public String getNextUrl(HtmlPage htmlPage) {

	HtmlElement elemNextPage = htmlPage
		.getFirstByXPath("//div[@class = 'paging']");

	List<HtmlElement> enpList = elemNextPage.getHtmlElementsByTagName("a");

	// Qui faccio il controllo per prendere il tasto avanti e non quello
	// indietro
	HtmlElement enp;
	if (enpList.size() == 1) {
	    enp = enpList.get(0);
	} else
	    enp = enpList.get(1);

	String urlNext = enp.getAttribute("href");

	urlNext = this.modifyUrl(urlNext);

	return null;
    }

    public String modifyUrl(String urlToModify) {

	String[] urlA = urlToModify.split("/");

	return this.firstPage + urlA[urlA.length - 1];
    }

    @Override
    public String getFirstPage() {
	return this.firstPage;
    }

    @Override
    public String getFolderName() {
	return this.folderName;
    }
}
