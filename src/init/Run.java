package init;
import BDD.Article;
import BDD.Client;
import BDD.Commande;
import Controler.ArticleControler;
import Controler.CommandeControler;
import serviceBD.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Run {
	public static void main(String args[]) {
		BD bd = BD.getInstance();
		Client client = new Client("HAHA@GMAIL.COM", "leo", "bello", "password");
		Commande cmd = new Commande(new Date(new Timestamp(System.currentTimeMillis()).getTime()),
				"ADRESSE",
				"EN COURS",
				666,
				(float)99.99);
		//CodePromo cp = new CodePromo("XR12", 0.5, "PERSONNEL");
		//cmd.setCodePromo(cp);
		cmd.setClient(client);
		CommandeControler cc = new CommandeControler(bd);
		//cc.create(cmd);
		cc.read(666);
		cc.update(cmd);
		ArticleControler ac = new ArticleControler(bd);
		Article article = new Article(1988, (float)10.0, 1);
		ac.create(article);
		/*
		BuildReq br = new BuildReq();
		String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10.50");
		System.out.println(req);
		*/
	}
}
