package it.uniroma3.giw.crawler;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlerRepubblica extends Crawler {

    private String firstPage;
    private String folderName;

    public CrawlerRepubblica(String fp, String fn) {
	this.firstPage = fp;
	this.folderName = fn;
    }

    private String modifyUrl(String urlToModify) {

	String[] urlA = urlToModify.split("/");

	String urlToGet = this.firstPage + urlA[urlA.length - 1];

	return urlToGet;
    }

    @Override
    public String getFirstPage() {
	// TODO Auto-generated method stub
	return firstPage;
    }

    @Override
    public String getFolderName() {
	// TODO Auto-generated method stub
	return this.folderName;
    }

    @Override
    public List<HtmlElement> getListHtmlElem(HtmlPage htmlPage) {
	// TODO Auto-generated method stub
	// prendo l'elemento tag <ul> contenente i link da visitare e salvare
	HtmlElement elem = htmlPage
		.getFirstByXPath("//div[@class = 'sub-content-1']");

	// prendo gli elementi con tag a

	return elem.getHtmlElementsByTagName("h1");
    }

    @Override
    public String getHref(HtmlElement he) {
	// TODO Auto-generated method stub
	String url;
	HtmlElement h1a = he.getHtmlElementsByTagName("a").get(0);

	url = h1a.getAttribute("href");

	return url;
    }

    @Override
    public String getNextUrl(HtmlPage htmlPage) {
	// TODO Auto-generated method stub
	HtmlElement elemNextPage = htmlPage
		.getFirstByXPath("//div[@class = 'pagination']/ul");

	List<HtmlElement> enpList = elemNextPage.getHtmlElementsByTagName("a");

	HtmlElement enp;

	// prendo l'ultimo link che sarebbe il tasto per la pagina successiva
	enp = enpList.get(enpList.size() - 1);

	String urlNext = enp.getAttribute("href");

	urlNext = this.modifyUrl(urlNext);

	return urlNext;
    }

}
