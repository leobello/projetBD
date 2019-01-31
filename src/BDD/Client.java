package BDD;

import serviceBD.BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Client {
	private String mailClient;
	private String nom;
	private String prenom;
	private String motDePasse;
	private boolean actif;
	private List<Impression> impressions = new ArrayList<Impression>();
	private List<FichierImage> fichierImages = new ArrayList<FichierImage>();
	private List<Adresse> adresses = new ArrayList<Adresse>();
	private List<CodePromo> codePromos = new ArrayList<CodePromo>();

	public Client(String mailClient, String nom, String prenom, String motDePasse) {
		this.mailClient = mailClient;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.actif = true;
		try {
			this.loadImpressions(mailClient);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadImpressions(String idClient) throws SQLException {
		Statement stmt = BD.getInstance().getReadCommittedSTMT();
		String req = "SELECT * FROM IMPRESSION WHERE MAILCLIENT = '" + idClient+"'";
		ResultSet rs = stmt.executeQuery(req);
		boolean impressionOK;
		while(rs.next()) {
			if(Integer.parseInt(rs.getString("IMPRESSION_OK")) == 1){
				impressionOK = true;
			} else {
				impressionOK = false;
			}
			impressions.add(new Impression(Integer.parseInt(rs.getString("NUMIMPRESSION")),
					                       rs.getString("PATH_IMPRESSION"),
									       impressionOK,
										   rs.getString("QUALITE"),
									       rs.getString("FORMAT")));
		}
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<FichierImage> getFichierImage() {
		return fichierImages;
	}

	public void setFichierImage(List<FichierImage> fichierImage) {
		this.fichierImages = fichierImage;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public List<CodePromo> getCodePromos() {
		return codePromos;
	}

	public void setCodePromos(List<CodePromo> codePromos) {
		this.codePromos = codePromos;
	}

	public void ajouterDansFichierImage(FichierImage fichierImage) {
		this.fichierImages.add(fichierImage);
	}

	public void supprimerDansFichierImage(FichierImage fichierImage) {
		this.fichierImages.remove(fichierImage);
	}

	public void ajouterDansCodePromos(CodePromo codePromo) {
		this.codePromos.add(codePromo);
	}

	public void supprimerDansCodePromos(CodePromo codePromo) {
		this.codePromos.remove(codePromo);
	}

	public void ajouterDansAdresses(Adresse adresse) {
		this.adresses.add(adresse);
	}

	public void supprimerDansAdresses(Adresse adresse) {
		this.adresses.remove(adresse);
	}

	public FichierImage getFichierImage(int i) {
		return fichierImages.get(i);
	}

	public Adresse getAdresse(int i) {
		return adresses.get(i);
	}

	public CodePromo getCodePromo(int i) {
		return codePromos.get(i);
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public List<Impression> getImpressions() {
		return impressions;
	}

	public void setImpressions(List<Impression> impressions) {
		this.impressions = impressions;
	}
	
	public Impression getImpression(int i){
		return impressions.get(i);
	}

}
