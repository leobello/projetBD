package BDD;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Impression {
	

	private int numImpression;
	private String pathImpression;
	private Client client;
	private boolean impression_ok;
	private List<Article> articles = new ArrayList<Article>();
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Impression(int numImpression, String pathImpression, Client client, boolean Impression_ok) {
		this.numImpression = numImpression;
		this.pathImpression = pathImpression;
		this.client = client;
		this.impression_ok = impression_ok;
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
