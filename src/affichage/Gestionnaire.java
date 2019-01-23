package affichage;

import serviceBD.LectureClavier;

public class Gestionnaire extends TypeUtilisateur {

	public void run() {
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println("Bienvenue dans l'application dédiée au client. \nQue souhaitez vous faire?\n");
			if(this.connecte) {
				System.out.println(	"Faire des trucs = 0\n"
								+ 	"Quitter l'application Gestionnaire = 1");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 0 : System.out.println("Ca fait des trucs!\n"); break;
					case 1 : System.out.println("A bientôt"); return;
				}
			}
			else {
				System.out.println(	"S'inscrire = 0\n"
								+	"Se Connecter = 1\n"
								+ 	"Quitter l'application Gestionnaire = 2");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 0 : System.out.println("Ca fait des trucs!\n"); break;
					case 1 : System.out.println("Ca fait des trucs!\n"); break;
					case 2 : System.out.println("A bientôt"); return;
					default: General.erreurDeChoix();
				}
			}
		}
	}
}
