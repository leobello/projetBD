package BDD;

import java.util.ArrayList;
import java.util.List;

public class Calendrier extends Impression {
	private List<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();

	public Calendrier(int numImpression, String pathImpression) {
		super(numImpression, pathImpression);
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
