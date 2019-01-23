package affichage;

import serviceBD.LectureClavier;

public class Client extends TypeUtilisateur {
	
	public void run() {
		System.out.println(	"/**********************************************************************************************************/\n"
						+ 	"Bienvenue dans l'application dédiée au client. \n");
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println("Que souhaitez vous faire?\n");
			if(this.connecte) {
				System.out.println(	"/*************     Gestion des commandes     *************/\n"
								+ 	"Visualiser les détails d'une commande = 11\n"
								+ 	"Créer une commande = 10\n"
								+ 	"/*****     Gestion des fichiers images et photos     *****/\n"
								+	"Visualiser le chemin des photos les plus utilisées = 9\n"
								+ 	"Visualiser ma liste d'images partagées = 8\n"
								+ 	"Supprimer un fichier image = 7\n"
								+ 	"/************     Gestion des impressions     ************/\n"
								+	"Visualiser mes impressions = 6\n"
								+	"Supprimer une impression = 5\n"
								+ 	"Modifier une impression = 4\n"
								+ 	"Créer une impression = 3\n"
								+ 	"/*********************     Autre     *********************/\n"
								+ 	"Visualiser les informations de mon compte = 2\n"
								+ 	"Se déconnecter = 1\n"
								+ 	"Quitter l'application Client = 0");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{	
					case 11 : visualiserDetailsCommande(); break;
					case 10 : creerCommande(); break;
					case 9 : visualiserPhotosPopulaires(); break;
					case 8 : visualiserListeImagesPartagees(); break;
					case 7 : supprimerFichierImage(); break;
					case 6 : visualiserImpressions(); break;
					case 5 : supprimerImpression(); break;
					case 4 : modifierImpression(); break;
					case 3 : creerImpression(); break;
					case 2 : visualiserInfosCompte(); break;
					case 1 : seDeconnecter(); break;
					case 0 : System.out.println("A bientôt"); return;
					default: General.erreurDeChoix();
				}
			}
			else {
				System.out.println(	"S'inscrire = 2\n"
								+	"Se Connecter = 1\n"
								+ 	"Quitter l'application Client = 0");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 2 : sInscrire(); break;
					case 1 : seConnecter(); break;
					case 0 : System.out.println("A bientôt"); return;
					default: General.erreurDeChoix();
				}
			}
		}
	}
	
	private void supprimerImpression() {
		// TODO Auto-generated method stub
		
	}

	private void modifierImpression() {
		// TODO Auto-generated method stub
		
	}

	private void supprimerFichierImage() {
		// TODO Auto-generated method stub
		
	}

	private void creerCommande() {
		// TODO Auto-generated method stub
		
	}

	private void visualiserDetailsCommande() {
		// TODO Auto-generated method stub
		
	}

	private void visualiserInfosCompte() {
		// TODO Auto-generated method stub
		
	}

	private void visualiserImpressions() {
		// TODO Auto-generated method stub
		
	}

	private void visualiserListeImagesPartagees() {
		// TODO Auto-generated method stub
		
	}

	private void visualiserPhotosPopulaires() {
		// TODO Auto-generated method stub
		
	}

	public void seConnecter() {
		this.connecte = true;
		System.out.println("Se Connecter\n");
	}
	public void sInscrire() {
		System.out.println("S'inscrire\n");
	}
	public void seDeconnecter() {
		this.connecte = false;
		System.out.println("Se Déconnecter\n");
	}
	
	public void creerImpression() {
		System.out.println("Créer une impression\n");
	}

}
