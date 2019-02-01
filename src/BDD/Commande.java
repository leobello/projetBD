package BDD;

import Controler.StockControler;
import serviceBD.BD;

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Commande {
	private String date;
	private String modeLivraison;
	private String statutCommande;
	private int numCommande;
	private float prixTotal;
	private CodePromo codePromo;
	private Client client;

	private List<Adresse> adresses = new ArrayList<Adresse>();
	private List<Article> articles = new ArrayList<Article>();

	public Commande(String date, String modeLivraison, String statutCommande, int numCommande, Float montant) {
		this.date = date;
		this.modeLivraison = modeLivraison;
		this.statutCommande = statutCommande;
		this.numCommande = numCommande;
		this.prixTotal = montant;
	}

	public boolean stockSufisant() {
		Statement st = null;
		try {
			st = BD.getInstance().getSerializableSTMT();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs;
		int quantiteTotal = 0;
		for(Article article: articles) {
			try {
				rs = st.executeQuery(reqArticle(article.getIdArticle()));
				while(rs.next()) {
					quantiteTotal = rs.getInt("QUANTITESTOCK");
				}
				if(article.getQuantite() > quantiteTotal) {
					System.out.println("L'article: " + article.getIdArticle() + " est en quantitée insufisant en stock");
					System.out.println("Stock: "+ quantiteTotal );
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Commande validée!");
		return true;
	}
	public void reload(Article article) {
		this.articles.clear();
		this.articles.add(article);
	}
	private String reqArticle(int id) {
		String req = "SELECT QUANTITESTOCK FROM STOCK " +
				"NATURAL JOIN STOCKARTICLE " +
				"NATURAL JOIN ARTICLE " +
				"WHERE ID_ARTICLE = " + id;
		return req;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getModeLivraison() {
		return modeLivraison;
	}

	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}

	public String getStatutCommande() {
		return statutCommande;
	}

	public void setStatutCommande(String statutCommande) {
		this.statutCommande = statutCommande;
	}

	public int getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(int numCommande) {
		this.numCommande = numCommande;
	}

	public void setMontant(float montant) {
		this.prixTotal = montant;
	}

	public List<Adresse> getCommandeAdresse() {
		return adresses;
	}

	public void setCommandeAdresse(List<Adresse> commandeAdresse) {
		this.adresses = commandeAdresse;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
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

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public float getMontant() {
		return prixTotal;
	}

	public void ajouterDansAdresses(Adresse adresse) {
		this.adresses.add(adresse);
	}


	public void supprimerDansAdresses(Adresse adresse) {
		this.adresses.remove(adresse);
	}

	public void ajouterDansArticles(Article article) {
		this.articles.add(article);
	}

	public void supprimerDansArticles(Article article) {
		this.articles.remove(article);
	}


	public Adresse getAdresse(int i) {
		return adresses.get(i);
	}

	public Article getArticle(int i) {
		return articles.get(i);
	}

	public float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}
}
