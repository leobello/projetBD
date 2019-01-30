<<<<<<< HEAD
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
=======
package init;
import BDD.Client;
import BDD.CodePromo;
import BDD.Commande;
import Controler.CommandeControler;
import serviceBD.*;

<<<<<<< HEAD
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

=======
>>>>>>> aac3f56b8212432fa35085957d4fc716fda69a7c
public class Run {
	public static void main(String args[]) {
		BD bd = BD.getInstance();
		try {
			Statement st = bd.getSerializableSTMT();
			Client client = new Client("HAHA@GMAIL.COM", "leo", "bello", "password");
			Commande cmd = new Commande(new Date(1990, 12, 31),
					"ADRESSE",
					"EN COURS",
					666,
					(float)99.99);
			//CodePromo cp = new CodePromo("XR12", 0.5, "PERSONNEL");
			//cmd.setCodePromo(cp);
			cmd.setClient(client);
			CommandeControler cc = new CommandeControler(st);
			//cc.create(cmd);
			cc.read(666);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		BuildReq br = new BuildReq();
		String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10.50");
		System.out.println(req);
		*/
	}
>>>>>>> master
}
