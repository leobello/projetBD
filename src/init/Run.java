package init;
import BDD.Article;
import BDD.Client;
import BDD.Commande;
import Controler.ArticleControler;
import Controler.CommandeControler;
import affichage.InteractionSystem;
import serviceBD.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Run {
	public static void main(String args[]) {
		InteractionSystem.run();
		/*
		BuildReq br = new BuildReq();
		String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10.50");
		System.out.println(req);
		*/
	}
}
