package BDD;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Commande {
	private Date date;
	private String modeLivraison;
	private int statutCommande;
	private int numCommande;
	private float montant;
	private CodePromo codePromo;
	private Client client;
	private Set<Adresse> adresses = new HashSet<Adresse>();
	private Set<Article> articles = new HashSet<Article>();
	
	public Commande(Date date, String modeLivraison, int statutCommande, int numCommande, float montant) {
		this.date = date;
		this.modeLivraison = modeLivraison;
		this.statutCommande = statutCommande;
		this.numCommande = numCommande;
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getModeLivraison() {
		return modeLivraison;
	}
	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}
	public int getStatutCommande() {
		return statutCommande;
	}
	public void setStatutCommande(int statutCommande) {
		this.statutCommande = statutCommande;
	}
	public int getNumCommande() {
		return numCommande;
	}
	public void setNumCommande(int numCommande) {
		this.numCommande = numCommande;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Set<Adresse> getCommandeAdresse() {
		return adresses;
	}
	public void setCommandeAdresse(Set<Adresse> commandeAdresse) {
		this.adresses = commandeAdresse;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public CodePromo getCodePromo() {
		return codePromo;
	}
	public void setCodePromo(CodePromo codePromo) {
		this.codePromo = codePromo;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<Adresse> getAdresses() {
		return adresses;
	}
	public void setAdresses(Set<Adresse> adresses) {
		this.adresses = adresses;
	}
	public float getMontant() {
		return montant;
	}
	public void ajouterDansAdresses(Adresse adresse){
		this.adresses.add(adresse);
	}	
	public void supprimerDansAdresses(Adresse adresse){
		this.adresses.remove(adresse);
	}
	public void ajouterDansArticles(Article article){
		this.articles.add(article);
	}
	public void supprimerDansArticles(Article article){
		this.articles.remove(article);
	}

}
