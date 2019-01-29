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
							+	"1 - Visualiser les statistiques des produits\n\n"
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
						+ 	"1 - Par qualit�\n\n"
						+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 3 : if(getStatByCategorie()) break; else return;
				case 2 : if(getStatByFormat()) break; else return;
				case 1 : if(getStatByQualite()) break; else return;
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
		/*requ�te des diff�rentes commandes pret à l'envoi disponibles*/
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
		System.out.println("Voil� les commandes!");
	}

	private void majStock() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************* Mise � jour du stock *********************/\n"
							+ 	"Quel �l�ment du stock voulez-vous mettre � jour?\n"
							+ 	"6 - Tirage\n"
							+ 	"5 - Album\n"
							+ 	"4 - Calendrier Muraux\n"
							+ 	"3 - Calendrier Bureau\n"
							+ 	"2 - Agenda 365 jours\n"
							+ 	"1 - Agenda 52 semaines\n\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 6 : if(majStock("TIRAGE")) break; else return;
				case 5 : if(majStock("ALBUM")) break; else return;
				case 4 : if(majStock("MURAL")) break; else return;
				case 3 : if(majStock("BUREAU")) break; else return;
				case 2 : if(majStock("JOURS")) break; else return;
				case 1 : if(majStock("SEMAINES")) break; else return;
				case 0 : quitter(); return;
				default : General.erreurDeChoix(); break;
			}
		}
	}
	
	private boolean majStock(String type_impression) {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************************** Tirage ****************************/\n"
							+ 	"5 - Recevoir une livraison de "+ type_impression +" format A4 Qualit� Sup�rieure\n"
							+ 	"4 - Recevoir une livraison de "+ type_impression +" format A4 Qualit� Moyenne\n"
							+ 	"3 - Recevoir une livraison de "+ type_impression +" format A5 Qualit� Sup�rieure\n"
							+ 	"2 - Recevoir une livraison de "+ type_impression +" format A5 Qualit� Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantit� recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Sup�rieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantit� recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantit� recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Sup�rieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantit� recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requ�te de maj du stock de format 'format' et de qualite 'qualite' en quantit� 'nombreMaj'
			 * "UPDATE STOCK 
					SET QUANTITESTOCK = QUANTITESTOCK + "+nombreMaj+"
					WHERE QUALITE = '"+qualite+"' AND format = '"+format+"' and type_impression = '"+type_impression+"';  */
			System.out.println( nombreMaj+" exemplaires ont �t�s ajout�s au stock "+type_impression+" format "+format+" en qualit� "+qualite+".\n");
		}
	}

	private void realiserImpression() {
		System.out.println(	"/***************** R�alisation d'une impression *****************/\n"
						+ 	"Voici les impressions en attente...\n");
		ArrayList<Object> impressions = new ArrayList<Object>();
		/*Requ�te des impressions en attente de r�alisation*/
		Object impression = impressions.get(LectureClavier.lireEntier("\nChoix :"));
		
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
