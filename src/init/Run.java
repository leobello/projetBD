package init;
import BDD.Article;
import BDD.Client;
import BDD.Commande;
import Controler.ArticleControler;
import Controler.ClientControler;
import Controler.CommandeControler;
import Controler.StockControler;
import affichage.InteractionSystem;
import serviceBD.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Run {
	public static void main(String args[]) {
		//InteractionSystem.run();
		int choix;
		int quantite;
		String yesNo = "y";
		CommandeControler cc = new CommandeControler(BD.getInstance());
		ArticleControler ac = new ArticleControler(BD.getInstance());
		StockControler sc = new StockControler(BD.getInstance());

		do {
			choix = LectureClavier.lireEntier("Choix du client (1 ou 2): ");
		} while(choix != 1 && choix != 2);
		System.out.println("Resumer de la commande du client n° "+choix);
		if(choix == 1) {
			// creation de la commande
			Commande c1 = cc.read(1);
			Article a1 = ac.read(1);
			a1.setQuantite(155);
			c1.ajouterDansArticles(a1);

			// affichage de la commande
			System.out.println("article: "+ a1.getIdArticle());
			System.out.println("quantite: "+ a1.getQuantite());
			System.out.println();
			System.out.println("verification du stock...");
			while (!c1.stockSufisant() && yesNo.equalsIgnoreCase("y")) {
				do {
					yesNo = LectureClavier.lireChaine("saisir une nouvelle quantité (y/n):  ?");
				} while (yesNo.equalsIgnoreCase("n") && yesNo.equalsIgnoreCase("y"));
				if (yesNo.equalsIgnoreCase("y")){
					quantite = LectureClavier.lireEntier("quantite: ");
					a1.setQuantite(quantite);
					c1.reload(a1);
					sc.updateStock(a1.getIdArticle(),quantite);
				}
			}
			// changer le stock



		// client 2
		} else {

		}

		/***
		 *
		 INSERT INTO COMMANDE VALUES ('2019-01-20', 'ADRESSE', 'EN COURS', 1,  NULL ,'LREOOF@ORANGE.FR', 50 );
		 *
		 */



	}
}
