package affichage;

import Controler.*;
import serviceBD.LectureClavier;

/**
 * 
 * @author PC_Louis_Pavillon
 * Premi�re classe d'interaction avec le syst�me.
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
							+	"\nBonjour, � quel service souhaitez-vous acc�der?\n"
							+	"2 - Application Client\n"
							+	"1 - Application Gestionnaire de Stock\n"
							+	"0 - Quitter l'application");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) {
					//Client
				case 2 : utilisateur = new Client(); utilisateur.run(); break;
					//Gestionnaire
				case 1 : utilisateur = new Gestionnaire(); utilisateur.run();break;	
				
				case 0 : System.out.println("A bient�t"); return;
				
				default: General.erreurDeChoix();
			}
		}
	}
}
