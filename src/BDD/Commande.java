package BDD;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Commande {
	private Date date;
	private String modeLivraison;
	private int statutCommande;
	private int numCommande;
	private int idCodePromo;
	private String mailClient;
	private int idAdresse;
	private float montant;
	private Set<Adresse> commandeAdresse = new HashSet<Adresse>();
	private Set<Article> articles = new HashSet<Article>();
	
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
	public int getIdCodePromo() {
		return idCodePromo;
	}
	public void setIdCodePromo(int idCodePromo) {
		this.idCodePromo = idCodePromo;
	}
	public String getMailClient() {
		return mailClient;
	}
	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Set<Adresse> getCommandeAdresse() {
		return commandeAdresse;
	}
	public void setCommandeAdresse(Set<Adresse> commandeAdresse) {
		this.commandeAdresse = commandeAdresse;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
}
