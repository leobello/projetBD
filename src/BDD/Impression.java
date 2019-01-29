package BDD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Impression {
	private int numImpression;
	private String pathImpression;
	private Set<Article> articles = new HashSet<Article>();
	
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
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public void ajouterDansArticles(Article article){
		this.articles.add(article);
	}
	public void supprimerDansArticles(Article article){
		this.articles.remove(article);
	}
}
