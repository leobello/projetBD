package init;
import serviceBD.*;
import affichage.*;

import java.sql.SQLException;

public class Run {
	public static void main(String args[]) {
		InteractionSystem.run();
		
		BuildReq br = new BuildReq();
		String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10.50");
		System.out.println(req);
		
	}
}
