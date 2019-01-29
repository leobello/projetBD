import java.sql.SQLException;

import BDD.Adresse;
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
		
		Stock stock = myGlobalControler.getStockControler().readStock("BUREAU", "SUPERIEURE", "A5");
		
		System.out.println(stock.getQuantiteStock());

	}

}
