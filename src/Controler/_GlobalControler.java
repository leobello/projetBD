package Controler;

import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.Connection;
import BDD.*;
import serviceBD.*;

public class _GlobalControler {
	private static Statement stmt;

	private static BD bd = BD.getInstance();

	
	// Initialisation de tous les controler
		
	public static CRUDInterface<Adresse> getAdresseControler() {
		return new AdresseControler(bd); 
	}
	
	/*public static CRUDInterface<Agenda> getAgendaControler() {
		return new AgendaControler(bd);  
	}*/
	
	/*public static CRUDInterface<AlbumPhoto> getAlbumPhotoControler() {
		return new AlbumPhotoControler(bd);
	}*/
	
	/*public static CRUDInterface<Article> getArticleControler() {
		return new ArticleControler(bd);
	}*/
	
	//public static CRUDInterface<Bureau> getBureauControler(){
		//return new BureauControler(bd);
	//}
	
	//public static CRUDInterface<Cadre> getCadreControler() {
		//return new CadreControler(bd);
	//}
	
	//public static CRUDInterface<Calendrier> getCalendrierControler() {
		//return new CalendrierControler(bd);
	//}
	
	//public static CRUDInterface<Client> getClientControler() {
		//return new ClientControler(bd);
	//}
	
	//public static CRUDInterface<CodePromo> getCodePromoControler(){
	//	return new CodePromoControler(bd);
	//}
	
	//public static CRUDInterface<Commande> getCommandeControler(){
	//	return new CommandeControler(bd);
	//}
	
	//public static CRUDInterface<FichierImage> getFichierControler() {
	//	return new FichierControler(bd);
	//}

	//public static CRUDInterface<Impression> getImpressioncontroler() {
	//	return new ImpressionControler(bd);
	//}
	
	//public static CRUDInterface<Jour> getJourControler() {
	//	return new JoursControler(bd);
	//}
	
	//public static CRUDInterface<Mural> getMuralControler() {
	//	return new MuralControler(bd);
	//}
	
	//public static CRUDInterface<Photo> getPhotoControler () {
	//	return new PhotoControler(bd);
	//}
	
	//public static CRUDInterface<Semaine> getSemaineControler() {
	//	return new SemaineControler(stmt);
	//}
	
	public static StockControler getStockControler() {
		return new StockControler(bd);
	}
	
	/*
	public static CRUDInterface<Tirage> getTirageControler() {
		return new TirageControler(stmt);
	}
	*/
}
