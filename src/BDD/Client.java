package BDD;

import java.util.HashSet;
import java.util.Set;

public class Client {
	private String mailClient; 
	private String nom;
	private String prenom; 
	private String motDePasse; 
	private Set<FichierImage> fichierImages = new HashSet<FichierImage>();
	private Set<Adresse> adresses = new HashSet<Adresse>();
	private Set<CodePromo> codePromos = new HashSet<CodePromo>();
	
	public Client(String mailClient, String nom, String prenom, String motDePasse) {
		this.mailClient = mailClient;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
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
	public Set<FichierImage> getFichierImage() {
		return fichierImages;
	}
	public void setFichierImage(Set<FichierImage> fichierImage) {
		this.fichierImages = fichierImage;
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
	public void ajouterDansFichierImage(FichierImage fichierImage){
		this.fichierImages.add(fichierImage);
	}
	public void supprimerDansFichierImage(FichierImage fichierImage){
		this.fichierImages.remove(fichierImage);
	}
	public void ajouterDansCodePromos(CodePromo codePromo){
		this.codePromos.add(codePromo);
	}
	public void supprimerDansCodePromos(CodePromo codePromo){
		this.codePromos.remove(codePromo);
	}
	public void ajouterDansAdresses(Adresse adresse){
		this.adresses.add(adresse);
	}	
	public void supprimerDansAdresses(Adresse adresse){
		this.adresses.remove(adresse);
	}
	
}
