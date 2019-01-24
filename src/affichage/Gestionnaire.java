package affichage;

import java.util.ArrayList;

import serviceBD.LectureClavier;

public class Gestionnaire extends TypeUtilisateur {

	private Object commande;

	public void run() {
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println(	"/**********************************************************************************************/\n"
							+ 	"Bienvenue dans l'application d�di�e au gestionnaire. \n\n"
							+	"Que souhaitez vous faire?\n\n"
							+	"6 - Supprimer un client\n"
							+	"5 - Supprimer un fichier image\n"
							+	"4 - R�aliser une impression\n"
							+	"3 - Mettre � jour le stock\n"
							+	"2 - Mettre � jour une commande\n"
							+	"1 - Visualiser les statistiques des produits\n"
							+ 	"0 - Quitter l'application Gestionnaire");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 6 : supprimerClient(); break;
				case 5 : supprimerFichierImage(); break;
				case 4 : realiserImpression(); break;
				case 3 : majStock(); break;
				case 2 : majCommande(); break;
				case 1 : visualiserStatProduit(); break;
				case 0 : quitter(); return;
				default : General.erreurDeChoix(); break;
			}
			
		}
	}

	private void visualiserStatProduit() {
		while(true) {
			int reponse = -1;
			System.out.println(	"/******* Visualisation des statistiques de vente des produits *******\n"
						+ 	"Selon quels crit�res souhaitez-vous ces statistiques?\n"
						+ 	"3 - Par cat�gorie\n"
						+ 	"2 - Par format\n"
						+ 	"1 - Par qualit�\n"
						+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 3 : if(getStatByCategorie())
							break;
						 else	
							return;
				case 2 : if(getStatByFormat())
							break;
						 else	
							return;
				case 1 :  if(getStatByQualite())
							break;
						 else	
							return;
				case 0 : quitter(); return;
				default : General.erreurDeChoix(); break;
			}
		}
	}						

	private boolean getStatByQualite() {
		int reponse = -1;
		/*requ�te de statistiques sur les ventes des produits ordonn�s par qualit�es*/
		System.out.println(	"/******* Statistiques de vente des produits par Qualite *******\n"
						+ 	"Les bonnes stats!\n");
		while(true) {
			System.out.println(	"1 - retour\n"
							+ 	"0 - retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
	}

	private boolean getStatByFormat() {
		int reponse = -1;
		/*requ�te de statistiques sur les ventes des produits ordonn�s par Format*/
		System.out.println(	"/******* Statistiques de vente des produits par Format *******\n"
						+ 	"Les bonnes stats!\n");
		while(true) {
			System.out.println(	"1 - retour\n"
							+ 	"0 - retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
	}

	private boolean getStatByCategorie() {
		int reponse = -1;
		/*requ�te de statistiques sur les ventes des produits ordonn�s par Cat�gories*/
		System.out.println(	"/******* Statistiques de vente des produits par Cat�gories *******\n"
						+ 	"Les bonnes stats!\n");
		while(true) {
			System.out.println(	"1 - retour\n"
							+ 	"0 - retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
	}

	private void majCommande() {
		System.out.println(	"/****************** Mise � jour d'une commande ******************/\n"
						+ 	"Quelle commande souhaitez-vous mettre � jour?");
		this.commande = choixCommandeAMaj();
		
		
	}					   

	private Object choixCommandeAMaj() {
		int reponse = -1;
		ArrayList<Object> commandes = new ArrayList<Object>();
		/*requ�te des diff�rentes commandes disponibles*/
		commandesToString(commandes);
		reponse = LectureClavier.lireEntier("\nChoix :");
		for(int num = 0; num<commandes.size(); num++) {
			if(reponse == num) {
				return commandes.get(num);
			}
		}
		return null;
	}

	private void commandesToString(ArrayList<Object> commandes) {
		// TODO Auto-generated method stub
		
	}

	private void majStock() {
		System.out.println("/********************* Mise � jour le stock *********************/\n");
	}

	private void realiserImpression() {
		System.out.println("/***************** R�alisation d'une impression *****************/\n");
	}

	private void supprimerFichierImage() {
		System.out.println("/*********** Suppression d'un/des fichier(s) image(s) ***********/\n");
	}

	private void supprimerClient() {
		System.out.println("/****************** Suppression d'un/des clients ****************/\n");
	}

	private void quitter() {
		System.out.println("A bient�t");
	}
}
