package pck.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapingWeb {
	/**
	 * Metodo que obtine el codigo HTML de la pagina web
	 * @param url de la pagina
	 * @return codigo html
	 */
	public static Document getHTML(String url){
		Document html = null;
		try {
			//se obtiene el codigo HTML de una pagina web
			html = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (Exception e) {
			System.out.println("Error al obtener el codigo HTML");
		}
		return html;
	}
	/**
	 * Metodo para Scrapiar pagina del DiarioADN 
	 */
	public void scraping(){
		//obtine los articlos de la pagina
		Elements articulos = ScrapingWeb.getHTML("http://diarioadn.co").select("article.noticias_home");
		for (Element noticia : articulos) {
			try {
				//se obtiene la url de la pagina de la notica y el codigo HTML
				String urlNoticia =  noticia.select("a").attr("abs:href");
				Document htmlNoticia = ScrapingWeb.getHTML(urlNoticia);
				//se obtiene el titulo y la infomacion de la noticia 
				String titulo =  htmlNoticia.select("h1").text();
				String informacion = htmlNoticia.select("p").not("p.lead").text();
				//se muestra la informacion 
				System.out.println(titulo);
				System.out.println(informacion);
				System.out.println("----------------------");
			} catch (Exception e) {
				System.out.println("Error al entrar en la notica");
			}
		}
	}
	public static void main(String[] args) {
		new ScrapingWeb().scraping();
	}
}
