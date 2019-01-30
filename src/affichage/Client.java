package affichage;

import serviceBD.LectureClavier;

public class Client extends TypeUtilisateur {
	
	@Override
	public void run() {
		System.out.println(	"/**********************************************************************************************/\n"
						+ 	"Bienvenue dans l'application dédiée au client. \n");
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println("Que souhaitez vous faire?\n");
			if(this.connecte) {
				System.out.println(	"/*************     Gestion des commandes     *************/\n"
								+ 	"11 - Visualiser les détails d'une commande\n"
								+ 	"10 - Créer une commande\n"
								+ 	"/*****     Gestion des fichiers images et photos     *****/\n"
								+	"9 - Visualiser le chemin des photos les plus utilisées\n"
								+ 	"8 - Visualiser ma liste d'images partagées\n"
								+ 	"7 - Supprimer un fichier image\n"
								+ 	"/************     Gestion des impressions     ************/\n"
								+	"6 - Visualiser mes impressions\n"
								+	"5 - Supprimer une impression\n"
								+ 	"4 - Modifier une impression\n"
								+ 	"3 - Créer une impression\n"
								+ 	"/*********************     Autre     *********************/\n"
								+ 	"2 - Visualiser les informations de mon compte\n"
								+ 	"1 - Se déconnecter\n"
								+ 	"0 - Quitter l'application Client");
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
					case 0 : quitter(); return;
					default: General.erreurDeChoix();
				}
			}
			else {
				System.out.println(	"2 - S'inscrire\n"
								+	"1 - Se Connecter\n"
								+ 	"0 - Quitter l'application Client");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 2 : sInscrire(); break;
					case 1 : seConnecter(); break;
					case 0 : quitter(); return;
					default: General.erreurDeChoix();
				}
			}
		}
	}

	public void quitter() {
		System.out.println("A bientôt");
	}
	
	private void supprimerImpression() {
		System.out.println("Supprimer une impression\n");
	}

	private void modifierImpression() {
		System.out.println("Modifier une impression\n");
	}

	private void supprimerFichierImage() {
		System.out.println("Supprimer les fichiers images\n");
	}

	private void creerCommande() {
		System.out.println("Créer une commande\n");
	}

	private void visualiserDetailsCommande() {
		System.out.println("Visualiser les détails de commande\n");
	}

	private void visualiserInfosCompte() {
		System.out.println("Visualiser les infos du compte\n");	}

	private void visualiserImpressions() {
		System.out.println("Visualiser les impressions\n");
	}

	private void visualiserListeImagesPartagees() {
		System.out.println("Visualiser la liste des images partagées\n");
	}

	private void visualiserPhotosPopulaires() {
		System.out.println("Visualiser les photos populaires\n");
	}

	private void seConnecter() {
		this.connecte = true;
		System.out.println("Se Connecter\n");
	}
	private void sInscrire() {
		System.out.println("S'inscrire\n");
	}
	private void seDeconnecter() {
		this.connecte = false;
		System.out.println("Se Déconnecter\n");
	}
	
	private void creerImpression() {
		System.out.println("Créer une impression\n");
	}

}
