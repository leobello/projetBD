import java.sql.SQLException;

<<<<<<< HEAD
=======
import BDD.Adresse;
>>>>>>> parent of 0f5b4bb... changes
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
		
<<<<<<< HEAD
		Stock stock = _GlobalControler.getStockControler().readStock("BUREAU", "SUPERIEURE", "A5");
=======
		Stock stock = myGlobalControler.getStockControler().readStock("BUREAU", "SUPERIEURE", "A5");
>>>>>>> parent of 0f5b4bb... changes
		
		System.out.println(stock.getQuantiteStock());

	}

}
