package BDD;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class FichierImage {
	private String path; 
	private String infoPriseDeVue; 
	private String resoluton;
	private int partage;
	private Date dateAcces;
	private Client proprietaire; 
	private Set<Photo> photos = new HashSet<Photo>();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getInfoPriseDeVue() {
		return infoPriseDeVue;
	}
	public void setInfoPriseDeVue(String infoPriseDeVue) {
		this.infoPriseDeVue = infoPriseDeVue;
	}
	public String getResoluton() {
		return resoluton;
	}
	public void setResoluton(String resoluton) {
		this.resoluton = resoluton;
	}
	public int getPartage() {
		return partage;
	}
	public void setPartage(int partage) {
		this.partage = partage;
	}
	public Date getDateAcces() {
		return dateAcces;
	}
	public void setDateAcces(Date dateAcces) {
		this.dateAcces = dateAcces;
	}
	public Client getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
}
