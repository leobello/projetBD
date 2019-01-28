package BDD;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FichierImage {
	private String path; 
	private String infoPriseDeVue; 
	private String resoluton;
	private int partage;
	private Date dateAcces;
	private Client proprietaire; 
	private List<Photo> photos = new ArrayList<Photo>();
	
	public FichierImage(String path, String infoPriseDeVue, String resoluton, int partage, Date dateAcces) {
		super();
		this.path = path;
		this.infoPriseDeVue = infoPriseDeVue;
		this.resoluton = resoluton;
		this.partage = partage;
		this.dateAcces = dateAcces;
	}
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
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
}
