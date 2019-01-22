package BDD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Article {
	private int idArticle;
	private float prix;
	private int quantite;
	private Commande commande;
	private Set<Stock> stocks = new HashSet<Stock>();
	private Impression impression;
	
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
	public Set<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}
	public Impression getImpression() {
		return impression;
	}
	public void setImpression(Impression impression) {
		this.impression = impression;
	}
}
