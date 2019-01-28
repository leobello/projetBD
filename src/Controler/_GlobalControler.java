package Controler;

import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.Connection;
import BDD.*;
import serviceBD.*;

public class _GlobalControler {

	private static Statement stmt;
	
	// Constructeur qui crée une connexion à la base et qui permet à tous les autres"controler" 
	//d'utiliser la même connexion
	public _GlobalControler () 
	{
		
		
		BD bd = new BD();
		try {
			bd.init();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println(bd);
			this.stmt = bd.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//public Statement getStatment() {
		//return this.stmt;
	//}
	
	// Initialisation de tous les controler
		
	public static CRUDInterface<Adresse> getAdresseControler() {
		return new AdresseControler(stmt); 
	}
	
	public static CRUDInterface<Agenda> getAgendaControler() {
		return new AgendaControler(stmt);  
	}
	
	public static CRUDInterface<AlbumPhoto> getAlbumPhotoControler() {
		return new AlbumPhotoControler(stmt);
	}
	
	public static CRUDInterface<Article> getArticleControler() {
		return new ArticleControler(stmt);
	}
	
	public static CRUDInterface<Bureau> getBureauControler(){
		return new BureauControler(stmt);
	}
	
	public static CRUDInterface<Cadre> getCadreControler() {
		return new CadreControler(stmt);
	}
	
	public static CRUDInterface<Calendrier> getCalendrierControler() {
		return new CalendrierControler(stmt);
	}
	
	public static CRUDInterface<Client> getClientControler() {
		return new ClientControler(stmt);
	}
	
	public static CRUDInterface<CodePromo> getCodePromoControler(){
		return new CodePromoControler(stmt);
	}
	
	public static CRUDInterface<Commande> getCommandeControler(){
		return new CommandeControler(stmt);
	}
	
	public static CRUDInterface<FichierImage> getFichierControler() {
		return new FichierControler(stmt);
	}
	
	public static CRUDInterface<Impression> getImpressioncontroler() {
		return new ImpressionControler(stmt);
	}
	
	public static CRUDInterface<Jours> getJourControler() {
		return new JoursControler(stmt);
	}
	
	public static CRUDInterface<Mural> getMuralControler() {
		return new MuralControler(stmt);
	}
	
	public static CRUDInterface<Photo> getPhotoControler () {
		return new PhotoControler(stmt);
	}
	
	public static CRUDInterface<Semaines> getSemaineControler() {
		return new SemaineControler(stmt);
	}
	
	public static CRUDInterface<Stock> getStockControler() {
		return new StockControler(stmt);
	}
	
	public static CRUDInterface<Tirage> getTirageControler() {
		return new TirageControler(stmt);
	}
}
