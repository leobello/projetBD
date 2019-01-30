package affichage;

import Controler.*;
import serviceBD.LectureClavier;

/**
 * 
 * @author PC_Louis_Pavillon
 * Première classe d'interaction avec le système.
 */
public class InteractionSystem {

	private static TypeUtilisateur utilisateur;
	
	public static void run() {
		_GlobalControler myGlobalControler = new _GlobalControler();
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println(	"/**********************************************************************************************/\n"
							+	"\nBonjour, à quel service souhaitez-vous accéder?\n"
							+	"2 - Application Client\n"
							+	"1 - Application Gestionnaire de Stock\n"
							+	"0 - Quitter l'application");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) {
					//Client
				case 2 : utilisateur = new Client(); utilisateur.run(); break;
					//Gestionnaire
				case 1 : utilisateur = new Gestionnaire(); utilisateur.run();break;	
				
				case 0 : System.out.println("A bientôt"); return;
				
				default: General.erreurDeChoix();
			}
		}
	}
}
