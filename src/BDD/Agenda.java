package BDD;

import java.util.ArrayList;
import java.util.List;

public class Agenda extends Impression {

	private int noPageAgenda;
	private List<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();

	public Agenda(int numImpression, String pathImpression, int noPageAgenda) {
		super(numImpression, pathImpression);
		this.noPageAgenda = noPageAgenda;
	}

	public int getNoPageAgenda() {
		return noPageAgenda;
	}

	public void setNoPageAgenda(int noPageAgenda) {
		this.noPageAgenda = noPageAgenda;
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
