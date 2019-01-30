package BDD;

import java.util.ArrayList;
import java.util.List;

public class Photo {
	private int idPhoto;
	private FichierImage fichierImage;
	private String retouche;
	private String description;
	private List<Couple<Agenda>> agendas = new ArrayList<Couple<Agenda>>();
	private List<Couple<AlbumPhoto>> albumPhotos = new ArrayList<Couple<AlbumPhoto>>();
	private List<Couple<Cadre>> cadres = new ArrayList<Couple<Cadre>>();
	private List<Couple<Calendrier>> calendriers = new ArrayList<Couple<Calendrier>>();
	private List<Couple<Tirage>> tirages = new ArrayList<Couple<Tirage>>();

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

	public List<Couple<Agenda>> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Couple<Agenda>> agendas) {
		this.agendas = agendas;
	}

	public List<Couple<AlbumPhoto>> getAlbumPhotos() {
		return albumPhotos;
	}

	public void setAlbumPhotos(List<Couple<AlbumPhoto>> albumPhotos) {
		this.albumPhotos = albumPhotos;
	}

	public List<Couple<Cadre>> getCadres() {
		return cadres;
	}

	public void setCadres(List<Couple<Cadre>> cadres) {
		this.cadres = cadres;
	}

	public List<Couple<Calendrier>> getCalendriers() {
		return calendriers;
	}

	public void setCalendriers(List<Couple<Calendrier>> calendriers) {
		this.calendriers = calendriers;
	}

	public List<Couple<Tirage>> getTirages() {
		return tirages;
	}

	public void setTirages(List<Couple<Tirage>> tirages) {
		this.tirages = tirages;
	}

	public void ajouterDansAgendas(Couple<Agenda> agenda) {
		this.agendas.add(agenda);
	}

	public void supprimerDansAgendas(Couple<Agenda> agenda) {
		this.agendas.remove(agenda);
	}

	public void ajouterDansAlbumPhotos(Couple<AlbumPhoto> albumPhoto) {
		this.albumPhotos.add(albumPhoto);
	}

	public void supprimerDansAlbumPhotos(Couple<AlbumPhoto> albumPhoto) {
		this.albumPhotos.remove(albumPhoto);
	}

	public void ajouterDansCadres(Couple<Cadre> cadre) {
		this.cadres.add(cadre);
	}

	public void supprimerDansCadres(Couple<Cadre> cadre) {
		this.cadres.remove(cadre);
	}

	public void ajouterDansCalendrier(Couple<Calendrier> calendrier) {
		this.calendriers.add(calendrier);
	}

	public void supprimerDansCalendriers(Couple<Calendrier> calendrier) {
		this.calendriers.remove(calendrier);
	}

	public void ajouterDansTirages(Couple<Tirage> tirage) {
		this.tirages.add(tirage);
	}

	public void supprimerDansTirages(Couple<Tirage> tirage) {
		this.tirages.remove(tirage);
	}

	public Couple<Agenda> getCoupleAgenda(int i) {
		return agendas.get(i);
	}

	public Couple<AlbumPhoto> getCoupleAlbumPhoto(int i) {
		return albumPhotos.get(i);
	}

	public Couple<Cadre> getCoupleCadre(int i) {
		return cadres.get(i);
	}

	public Couple<Calendrier> getCoupleCalendrier(int i) {
		return calendriers.get(i);
	}

	public Couple<Tirage> getCoupleTirage(int i) {
		return tirages.get(i);
	}
}
