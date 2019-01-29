package BDD;

import java.util.HashSet;
import java.util.Set;

public class Tirage extends Impression{
	private String qualitePapier;
	private String formatTirage;
	private Set<Couple<Photo>> photos = new HashSet<Couple<Photo>>();
	
	public Tirage(int numImpression, String pathImpression, String qualitePapier, String formatTirage) {
		super(numImpression, pathImpression);
		this.qualitePapier = qualitePapier;
		this.formatTirage = formatTirage;
	}
	public String getQualitePapier() {
		return qualitePapier;
	}
	public void setQualitePapier(String qualitePapier) {
		this.qualitePapier = qualitePapier;
	}
	public String getFormatTirage() {
		return formatTirage;
	}
	public void setFormatTirage(String formatTirage) {
		this.formatTirage = formatTirage;
	}
	public Set<Couple<Photo>> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Couple<Photo>> photos) {
		this.photos = photos;
	}
	public void ajouterDansPhotos(Couple<Photo> photo){
		this.photos.add(photo);
	}
	public void supprimerDansPhotos(Couple<Photo> photo){
		this.photos.remove(photo);
	}
}
