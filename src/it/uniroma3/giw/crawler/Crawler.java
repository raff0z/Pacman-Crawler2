package it.uniroma3.giw.crawler;

import java.io.IOException;
import java.util.List;

import it.uniroma3.giw.main.DocumentSaver;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class Crawler {

    protected WebClient webClient;
    protected final int CRAWLER_MAX_LENGTH = 35;

    public Crawler() {

	this.webClient = getNewWebClient();

    }

    public abstract String getFirstPage();

    public abstract String getFolderName();

    public WebClient getNewWebClient() {
	WebClient webClient = new WebClient();
	webClient.getOptions().setCssEnabled(false);
	webClient.getOptions().setAppletEnabled(false);
	webClient.getOptions().setJavaScriptEnabled(false);
	return webClient;
    }

    public void setCrawlerRules() {

	DocumentSaver saver = getDocumentSaver();

	HtmlPage htmlPage = null;

	List<HtmlElement> list = null;

	int i = 0;

	// prendo la pagina principale
	try {
	    htmlPage = this.webClient.getPage(getFirstPage());
	} catch (FailingHttpStatusCodeException | IOException e) {
	    System.out.println(e.getMessage());
	}

	while (i < this.CRAWLER_MAX_LENGTH) {

	    try {

		list = getListHtmlElem(htmlPage);

		String urlToGet;

		boolean finito = false;

		// per ogni elemento tag a ne prendo l' url, gli levo
		// "/qualcosa/" d'avanti e gli concateno avanti la firstPage
		for (HtmlElement he : list) {

		    urlToGet = getHref(he);

		    System.out.println(urlToGet);

		    // faccio la get della pagina e la salvo
		    HtmlPage htmlp = this.webClient.getPage(urlToGet);

		    saver.save(htmlp);
		    i++;

		    // faccio il controllo se il numero dei link della pagina ha
		    // raggiunto il CRAWLER_MAX_LENGTH.
		    // Se s� devo fermare entrambi i cicli, quindi setto a true
		    // finito e faccio break;
		    if (i == this.CRAWLER_MAX_LENGTH) {
			finito = true;
			break;
		    }
		}

		// se finito � true faccio il break anche qui
		if (finito) {
		    break;
		} else {
		    String urlNext = this.getNextUrl(htmlPage);

		    // prendo la pagina successiva
		    htmlPage = this.webClient.getPage(urlNext);

		    System.out.println("cambio pagina");
		}

	    } catch (FailingHttpStatusCodeException | IOException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    public void doCrawling() {
	this.webClient = getNewWebClient();

	setCrawlerRules();

	this.webClient.closeAllWindows();

    }

    public abstract List<HtmlElement> getListHtmlElem(HtmlPage htmlPage);

    public abstract String getHref(HtmlElement he);

    public abstract String getNextUrl(HtmlPage htmlPage);

    public DocumentSaver getDocumentSaver() {
	return new DocumentSaver(getFolderName());
    }
}
