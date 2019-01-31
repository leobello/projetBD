import java.sql.Date;
import java.sql.SQLException;

import BDD.Tirage;
import Controler._GlobalControler;
import BDD.*;

public class Test {
	public static _GlobalControler myGlobalControler = new _GlobalControler();
	
	public static void main(String[] args) throws SQLException {
		
		/*Adresse test = myGlobalControler.getAdresseControler().read(11);
		System.out.println("la ville est : "+ test.getVille());
		
		Adresse createTest = new Adresse(12,13013,"Marseille","test","test","rue");
		boolean testCreate = myGlobalControler.getAdresseControler().create(createTest);
		System.out.println(testCreate);*/
		
		//Stock stock = _GlobalControler.getStockControler().readStock("JOURS", "MOYENNE", "A4");
		//System.out.println(stock.getQuantiteStock());
		
		/*Stock stock = _GlobalControler.getStockControler().readStock("SEMAINES", "MOYENNE", "A4");
		System.out.println("stock avant : "+ stock.getQuantiteStock());
		
		stock = new Stock ("SEMAINES",200,"MOYENNE","A4");
		boolean stockOk = _GlobalControler.getStockControler().update(stock);
		stock = _GlobalControler.getStockControler().readStock("SEMAINES", "MOYENNE", "A4");
		System.out.println("stock apres : "+stock.getQuantiteStock());*/
		
		Client client = _GlobalControler.getClientControler().readClient("WILLY@ORANGE.FR");
		System.out.println(client.getMotDePasse());
		
		/*Tirage tir = _GlobalControler.getTirageControler().read(11);
		System.out.println("Le nom du client : "+tir.getClient().getNom());
		
		Photo ph = _GlobalControler.getPhotoControler().read(2);
		System.out.println(ph.getDescription());
		System.out.println(ph.getCoupleTirage(0).getGenerique().getFormat());*/
		
		/*FichierImage f = _GlobalControler.getFichierControler().readFichier("/HGJ/DG","JEAP@GMAIL.COM");
		System.out.println(f.getInfoPriseDeVue());*/
		//Date d = Date(31/01/2019);
		Commande c = new Commande("31/01/2019","ADRESSE","EN COURS",235,(float)33.54);
		c.setCodePromo(new CodePromo("AKJFKJJDHFJHKJSF545666SDF", 0, false, null));
		c.setClient(client);
		boolean testCom = _GlobalControler.getCommandeControler().create(c);
		System.out.println(testCom);
		
		
	}

}
