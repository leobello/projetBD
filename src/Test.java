import java.sql.SQLException;

import BDD.Adresse;
import Controler._GlobalControler;


public class Test {
	public static _GlobalControler myGlobalControler = new _GlobalControler();
	
	public static void main(String[] args) throws SQLException {
		
				
		
		boolean test = myGlobalControler.getAdresseControler().delete(0);
		System.out.println(test);
		
		
	}

}
