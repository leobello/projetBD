package BDD;

import java.util.HashSet;
import java.util.Set;

public class Calendrier extends Impression{
	private Set<Couple<Photo>> photos = new HashSet<Couple<Photo>>();

	public Set<Couple<Photo>> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Couple<Photo>> photos) {
		this.photos = photos;
	}
}
