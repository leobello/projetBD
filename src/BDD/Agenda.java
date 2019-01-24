package BDD;

import java.util.HashSet;
import java.util.Set;

public class Agenda extends Impression{

	private int noPageAgenda;
	private Set<Couple<Photo>> photos = new HashSet<Couple<Photo>>();
	
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
