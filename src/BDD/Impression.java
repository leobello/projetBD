package BDD;

import java.util.ArrayList;
import java.util.List;

public class Impression {
	


	private int numImpression;
	private String pathImpression;
	private Client client;
	private String qualite;
	private String format;                                                                                                   
	private boolean impression_ok;
	private List<Article> articles = new ArrayList<Article>();

	
	public Impression(int numImpression, String pathImpression, Client client, boolean impression_ok, String qualite, String format) {
		this.numImpression = numImpression;
		this.pathImpression = pathImpression;
		this.client = client;
		this.impression_ok = impression_ok;
		this.qualite = qualite;
		this.format = format;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	public String getQualite() {
		return qualite;
	}
<<<<<<< HEAD
<<<<<<< HEAD

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public String getFormat() {
		return format;
	}
=======
>>>>>>> parent of 0f5b4bb... changes
=======
>>>>>>> parent of 0f5b4bb... changes

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isImpression_ok() {
		return impression_ok;
	}

	public void setImpression_ok(boolean impression_ok) {
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
