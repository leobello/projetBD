package BDD;

import java.util.HashSet;
import java.util.Set;

public class Calendrier extends Impression{
	private Set<Couple<Photo>> photos = new HashSet<Couple<Photo>>();
	
	public Calendrier(int numImpression, String pathImpression) {
		super(numImpression, pathImpression);
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
