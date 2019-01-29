package BDD;

import java.util.ArrayList;
import java.util.List;

public class Stock {


	private String typeImression;
	private String qualite;
	private String format;
	private int quantiteStock;
	private List<Article> articles = new ArrayList<Article>();

	public Stock(String typeImression, int quantiteStock, String qualite, String format) {
		super();
		this.typeImression = typeImression;
		this.quantiteStock = quantiteStock;
		this.format = format;
		this.qualite = qualite;
	}
	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	public String getTypeImression() {
		return typeImression;
	}

	public void setTypeImression(String typeImression) {
		this.typeImression = typeImression;
	}

	public int getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void ajouterDansArticles(Article article) {
		this.articles.add(article);
	}

	public void supprimerDansArticles(Article article) {
		this.articles.remove(article);
	}

	public Article getArticle(int i) {
		return articles.get(i);
	}

}