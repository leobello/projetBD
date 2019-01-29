package BDD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cadre extends Impression {
	private List<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();
	private String format;
	private String qualite;

	public Cadre(int numImpression, String pathImpression, Client client, boolean Impression_ok, String qualite, String format) {
		super(numImpression, pathImpression, client, Impression_ok);
		this.format = format;
		this.qualite = qualite;
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
