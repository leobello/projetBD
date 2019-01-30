package BDD;

import java.util.ArrayList;
import java.util.List;

public class Tirage extends Impression {
	private String qualitePapier;
	private String formatTirage;
	private List<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();

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

	public List<Couple<Photo>> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Couple<Photo>> photos) {
		this.photos = photos;
	}

	public void ajouterDansPhotos(Couple<Photo> photo) {
		this.photos.add(photo);
	}

	public void supprimerDansPhotos(Couple<Photo> photo) {
		this.photos.remove(photo);
	}

	public Couple<Photo> getPhoto(int i) {
		return photos.get(i);
	}
}
