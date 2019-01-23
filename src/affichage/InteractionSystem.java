package affichage;

import serviceBD.LectureClavier;

/**
 * 
 * @author PC_Louis_Pavillon
 * Premi�re classe d'interaction avec le syst�me.
 */
public class InteractionSystem {

	private static TypeUtilisateur utilisateur;
	
	public static void run() {
		
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println("/**********************************************************************************************/");
			System.out.println("\nBonjour, � quel service souhaitez-vous acc�der?\n");
			System.out.println(	"Application Client = 2\n"
							+	"Application Gestionnaire de Stock = 1\n"
							+	"Quitter l'application = 0");
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
