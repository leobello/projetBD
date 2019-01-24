package BDD;

import java.util.HashSet;
import java.util.Set;

public class AlbumPhoto extends Impression{
	private int idPhoto;
	private String titre;
	private String qualite;
	private String formatAlbum;
	private Set<Couple<Photo>> photos = new HashSet<Couple<Photo>>();
	
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
	public String getQualite() {
		return qualite;
	}
	public void setQualite(String qualite) {
		this.qualite = qualite;
	}
	public String getFormatAlbum() {
		return formatAlbum;
	}
	public void setFormatAlbum(String formatAlbum) {
		this.formatAlbum = formatAlbum;
	}
	public Set<Couple<Photo>> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Couple<Photo>> photos) {
		this.photos = photos;
	}
}
