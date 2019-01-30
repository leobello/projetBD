package BDD;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Impression {
	private int numImpression;
	private String pathImpression;
	private List<Article> articles = new ArrayList<Article>();

	public Impression(int numImpression, String pathImpression) {
		this.numImpression = numImpression;
		this.pathImpression = pathImpression;
	}

	public int getNumImpression() {
		return numImpression;
	}

	public void setNumImpression(int numImpression) {
		this.numImpression = numImpression;
	}

	public String getPathImpression() {
		return pathImpression;
	}

	public void setPathImpression(String pathImpression) {
		this.pathImpression = pathImpression;
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
