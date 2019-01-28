package BDD;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Article {
	private int idArticle;
	private float prix;
	private int quantite;
	private Commande commande;
	private List<Stock> stocks = new ArrayList<Stock>();
	private Impression impression;
	
	public Article(int idArticle, float prix, int quantite) {
		this.idArticle = idArticle;
		this.prix = prix;
		this.quantite = quantite;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	public Impression getImpression() {
		return impression;
	}
	public void setImpression(Impression impression) {
		this.impression = impression;
	}
	public void ajouterDansStocks(Stock stock){
		this.stocks.add(stock);
	}
	public void supprimerDansStocks(Stock stock){
		this.stocks.remove(stock);
	}
}
