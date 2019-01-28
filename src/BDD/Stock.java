package BDD;

import java.util.ArrayList;
import java.util.List;

public class Stock{
	private String typeImression;
	private int quantiteStock;
	private List<Article> articles = new ArrayList<Article>();
	
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
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public void ajouterDansArticles(Article article){
		this.articles.add(article);
	}
	public void supprimerDansArticles(Article article){
		this.articles.remove(article);
	}
	
}