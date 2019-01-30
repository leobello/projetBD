import java.sql.SQLException;

import BDD.Adresse;
import BDD.Client;
import BDD.Stock;
import Controler._GlobalControler;


public class Test {
	public static _GlobalControler myGlobalControler = new _GlobalControler();
	
	public static void main(String[] args) throws SQLException {
		
		//Adresse test = new Adresse() ;
		/*Adresse test = myGlobalControler.getAdresseControler().read(11);
		System.out.println("la ville est : "+ test.getVille());
		
		Adresse createTest = new Adresse(12,13013,"Marseille","test","test","rue");
		boolean testCreate = myGlobalControler.getAdresseControler().create(createTest);
		System.out.println(testCreate);*/
		
		//Stock stock = _GlobalControler.getStockControler().readStock("JOURS", "MOYENNE", "A4");
		//System.out.println(stock.getQuantiteStock());
		
		Stock stock = _GlobalControler.getStockControler().readStock("SEMAINES", "MOYENNE", "A4");
		System.out.println("stock avant : "+ stock.getQuantiteStock());
		
		stock = new Stock ("SEMAINES",200,"MOYENNE","A4");
		boolean stockOk = _GlobalControler.getStockControler().update(stock);
		stock = _GlobalControler.getStockControler().readStock("SEMAINES", "MOYENNE", "A4");
		System.out.println("stock apres : "+stock.getQuantiteStock());
		
		//Client client = _GlobalControler.getClientControler().readClient("WILLY@ORANGE.FR");
		//System.out.println(client.getNom());
		
		

	}

}
