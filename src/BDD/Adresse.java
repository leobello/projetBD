package BDD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Adresse {
	private int idAdresse;
	private int codePostal;
	private String ville;
	private String nomAdresse;
	private String prenomAdresse;
	private String rue;
	private Set<Commande> adresseCommande = new HashSet<Commande>();
	private Set<Client> clients = new HashSet<Client>();
	
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNomAdresse() {
		return nomAdresse;
	}
	public void setNomAdresse(String nomAdresse) {
		this.nomAdresse = nomAdresse;
	}
	public String getPrenomAdresse() {
		return prenomAdresse;
	}
	public void setPrenomAdresse(String prenomAdresse) {
		this.prenomAdresse = prenomAdresse;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public Set<Commande> getAdresseCommande() {
		return adresseCommande;
	}
	public void setAdresseCommande(Set<Commande> adresseCommande) {
		this.adresseCommande = adresseCommande;
	}
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
}
