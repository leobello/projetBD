package BDD;

import java.util.HashSet;
import java.util.Set;

public class Photo {
	private int idPhoto;
	private FichierImage fichierImage;
	private String retouche; 
	private String description;
	private Set<Couple<Agenda>> agendas = new HashSet<Couple<Agenda>>();
	private Set<Couple<AlbumPhoto>> albumPhotos = new HashSet<Couple<AlbumPhoto>>();
	private Set<Couple<Cadre>> cadres = new HashSet<Couple<Cadre>>();
	private Set<Couple<Calendrier>> calendriers = new HashSet<Couple<Calendrier>>();
	private Set<Couple<Tirage>> tirages = new HashSet<Couple<Tirage>>();
	
	public Photo(int idPhoto, String retouche, String description) {
		this.idPhoto = idPhoto;
		this.retouche = retouche;
		this.description = description;
	}
	public int getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}
	public FichierImage getFichierImage() {
		return fichierImage;
	}
	public void setFichierImage(FichierImage fichierImage) {
		this.fichierImage = fichierImage;
	}
	public String getRetouche() {
		return retouche;
	}
	public void setRetouche(String retouche) {
		this.retouche = retouche;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Couple<Agenda>> getAgendas() {
		return agendas;
	}
	public void setAgendas(Set<Couple<Agenda>> agendas) {
		this.agendas = agendas;
	}
	public Set<Couple<AlbumPhoto>> getAlbumPhotos() {
		return albumPhotos;
	}
	public void setAlbumPhotos(Set<Couple<AlbumPhoto>> albumPhotos) {
		this.albumPhotos = albumPhotos;
	}
	public Set<Couple<Cadre>> getCadres() {
		return cadres;
	}
	public void setCadres(Set<Couple<Cadre>> cadres) {
		this.cadres = cadres;
	}
	public Set<Couple<Calendrier>> getCalendriers() {
		return calendriers;
	}
	public void setCalendriers(Set<Couple<Calendrier>> calendriers) {
		this.calendriers = calendriers;
	}
	public Set<Couple<Tirage>> getTirages() {
		return tirages;
	}
	public void setTirages(Set<Couple<Tirage>> tirages) {
		this.tirages = tirages;
	}
	public void ajouterDansAgendas(Couple<Agenda> agenda){
		this.agendas.add(agenda);
	}
	public void supprimerDansAgendas(Couple<Agenda> agenda){
		this.agendas.remove(agenda);
	}
	public void ajouterDansAlbumPhotos(Couple<AlbumPhoto> albumPhoto){
		this.albumPhotos.add(albumPhoto);
	}
	public void supprimerDansAlbumPhotos(Couple<AlbumPhoto> albumPhoto){
		this.albumPhotos.remove(albumPhoto);
	}
	public void ajouterDansCadres(Couple<Cadre> cadre){
		this.cadres.add(cadre);
	}
	public void supprimerDansCadres(Couple<Cadre> cadre){
		this.cadres.remove(cadre);
	}
	public void ajouterDansCalendrier(Couple<Calendrier> calendrier){
		this.calendriers.add(calendrier);
	}
	public void supprimerDansCalendriers(Couple<Calendrier> calendrier){
		this.calendriers.remove(calendrier);
	}
	public void ajouterDansTirages(Couple<Tirage> tirage){
		this.tirages.add(tirage);
	}
	public void supprimerDansTirages(Couple<Tirage> tirage){
		this.tirages.remove(tirage);
	}
}
