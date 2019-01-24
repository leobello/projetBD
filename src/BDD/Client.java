package BDD;

import java.util.HashSet;
import java.util.Set;

public class Client {
	private String mailClient; 
	private String nom;
	private String prenom; 
	private String motDePasse; 
	private Set<FichierImage> proprietes = new HashSet<FichierImage>();
	private Set<Adresse> adresses = new HashSet<Adresse>();
	private Set<CodePromo> codePromos = new HashSet<CodePromo>();
	
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
	public Set<FichierImage> getProprietes() {
		return proprietes;
	}
	public void setProprietes(Set<FichierImage> proprietes) {
		this.proprietes = proprietes;
	}
	public Set<Adresse> getAdresses() {
		return adresses;
	}
	public void setAdresses(Set<Adresse> adresses) {
		this.adresses = adresses;
	}
	public Set<CodePromo> getCodePromos() {
		return codePromos;
	}
	public void setCodePromos(Set<CodePromo> codePromos) {
		this.codePromos = codePromos;
	}
}
