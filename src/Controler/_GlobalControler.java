package Controler;

//import java.sql.Connection;
import BDD.*;

public class _GlobalControler {
	//private static final Connection connexion = ;
	//lkehlksdhflkdsf
		
	public static CRUDInterface<Adresse> getAdresseControler() {
		return new AdresseControler(); 
	}
	
	public static CRUDInterface<Agenda> getAgendaControler() {
		return new AgendaControler();  
	}
	
	
	public static CRUDInterface<AlbumPhoto> getAlbumPhotoControler() {
		return new AlbumPhotoControler();
	}
	
	
	public static CRUDInterface<Article> getArticleControler() {
		return new ArticleControler();
	}
	
	public static CRUDInterface<Bureau> getBureauControler(){
		return new BureauControler();
	}
	
	public static CRUDInterface<Cadre> getCadreControler() {
		return new CadreControler();
	}
	
	public static CRUDInterface<Calendrier> getCalendrierControler() {
		return new CalendrierControler();
	}
	
	public static CRUDInterface<Client> getClientControler() {
		return new ClientControler();
	}
	
	public static CRUDInterface<CodePromo> getCodePromoControler(){
		return new CodePromoControler();
	}
	
	public static CRUDInterface<Commande> getCommandeControler(){
		return new CommandeControler();
	}
	
	public static CRUDInterface<FichierImage> getFichierControler() {
		return new FichierControler();
	}
	
	public static CRUDInterface<Impression> getImpressioncontroler() {
		return new ImpressionControler();
	}
	
	public static CRUDInterface<Jours> getJourControler() {
		return new JoursControler();
	}
	
	public static CRUDInterface<Mural> getMuralControler() {
		return new MuralControler();
	}
	
	public static CRUDInterface<Photo> getPhotoControler () {
		return new PhotoControler();
	}
	
	public static CRUDInterface<Semaines> getSemaineControler() {
		return new SemaineControler();
	}
	
	public static CRUDInterface<Stock> getStockControler() {
		return new StockControler();
	}
	
	public static CRUDInterface<Tirage> getTirageControler() {
		return new TirageControler();
	}
}
