package BDD;

public class Photo {
	private int idPhoto;
	private FichierImage fichierImage;
	private String retouche; 
	private String description;
	
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
}
