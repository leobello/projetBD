package BDD;

import java.util.ArrayList;
import java.util.List;

public class AlbumPhoto extends Impression {
	private int idPhoto;
	private String titre;
	private List<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();

	public AlbumPhoto(int numImpression, String pathImpression,Client client, boolean Impression_ok, int idPhoto, String titre, String qualite,
			String format) {
		super(numImpression, pathImpression, client, Impression_ok, qualite, format);
		this.idPhoto = idPhoto;
		this.titre = titre;
	}

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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
