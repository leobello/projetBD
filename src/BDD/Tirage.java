package BDD;

import java.util.ArrayList;
import java.util.List;

public class Tirage extends Impression {
	private List<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();

	public Tirage(int numImpression, String pathImpression, Client client, boolean Impression_ok, String qualite, String format) {
<<<<<<< HEAD
<<<<<<< HEAD
		super(numImpression, pathImpression, client, Impression_ok, qualite, format);
=======
=======
>>>>>>> parent of 0f5b4bb... changes
		super(numImpression, pathImpression, client, Impression_ok);
		this.qualite = qualite;
		this.format = format;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
>>>>>>> parent of 0f5b4bb... changes
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
