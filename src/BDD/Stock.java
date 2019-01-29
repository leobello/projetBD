package BDD;

import java.util.HashSet;
import java.util.Set;

public class Stock{
	private String typeImression;
	private int quantiteStock;
	private Set<Article> articles = new HashSet<Article>();
	
	public Stock(String typeImression, int quantiteStock) {
		super();
		this.typeImression = typeImression;
		this.quantiteStock = quantiteStock;
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