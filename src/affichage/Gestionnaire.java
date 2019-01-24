package affichage;

import java.util.ArrayList;
import BDD.*;
import serviceBD.LectureClavier;

public class Gestionnaire extends TypeUtilisateur {

	private Commande commande;

	public void run() {
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println(	"/**********************************************************************************************/\n"
							+ 	"Bienvenue dans l'application dédiée au gestionnaire. \n\n"
							+	"Que souhaitez vous faire?\n\n"
							+	"6 - Supprimer un client\n"
							+	"5 - Supprimer un fichier image\n"
							+	"4 - Réaliser une impression\n"
							+	"3 - Mettre à jour le stock\n"
							+	"2 - Mettre à jour une commande\n"
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
						+ 	"Selon quels critères souhaitez-vous ces statistiques?\n"
						+ 	"3 - Par catégorie\n"
						+ 	"2 - Par format\n"
						+ 	"1 - Par qualité\n\n"
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
		/*requête de statistiques sur les ventes des produits ordonnés par qualitées*/
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
		/*requête de statistiques sur les ventes des produits ordonnés par Format*/
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
		/*requête de statistiques sur les ventes des produits ordonnés par Catégories*/
		System.out.println(	"/******* Statistiques de vente des produits par Catégories *******\n"
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
		int reponse = -1;
		while(true) 
		{
			System.out.println(	"/****************** Mise à jour d'une commande ******************/\n"
							+ 	"Quelle commande souhaitez-vous mettre à jour?\n");
			this.commande = choixCommandeAMaj();
			if(this.commande == null) 
			{
				return;
			} 
			else 
			{
				/*Vérification nécessaire de l'état de la commande, 
				 * si elle est "Prête à l'envoi" ne proposer que "Mettre la commande en état 'Envoyé' et 'En Cours'"*/
			}
			String choix = choixEtatCommandeAMaj(this.commande);
			switch(choix) 
			{
				case "E" : /*Requête de maj de la commande choisie à l'état Envoyé*/
						System.out.println("La commande n°"+commande.getNumCommande()+" à été envoyée\n");
						break;
				case "return" : break;
				case "returnMenu" : return;
			} 
		}
	}

	private String choixEtatCommandeAMaj(Commande commande) {
		int reponse;
		while(true) {
			System.out.println("Voulez-vous vraiment envoyer la commande n°"+commande.getNumCommande()+"\n");
			if(true/*A remplacer par la vérification de l'état de la commande*/) 
			{
					System.out.println(	"2 - Envoyer\n\n"
									+	"1 - Retour\n"
									+ 	"0 - Retour au menu principal\n");
					reponse = LectureClavier.lireEntier("\nChoix :");
					switch(reponse) 
					{
						case 2 : return "E";
						case 1 : return "return";
						case 0 : return "returnMenu";
						default : General.erreurDeChoix(); break;
					}
			}	
			else 
			{ //Cas "Prête à l'envoi"
				System.out.println( "3 - En Cours\n"
								+ 	"2 - Envoyée\n\n"
								+	"1 - Retour\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : return "EC";
					case 2 : return "E";
					case 1 : return "return";
					case 0 : return "returnMenu";
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}					   

	private Commande choixCommandeAMaj() {
		int reponse = -1;
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		/*requête des différentes commandes dans l'état "En cours" et "Prête à l'envoi"*/
		commandesToString(commandes);
		reponse = LectureClavier.lireEntier("\nChoix :");
		for(int num = 0; num<commandes.size(); num++) {
			/*if(reponse-1 == num) {
				return commandes.get(num);
			}*/
			Commande commande = new Commande();
			commande.setNumCommande(25894);
			return commande;
		}
		return null;
	}

	private void commandesToString(ArrayList<Commande> commandes) {
		// TODO Auto-generated method stub
		System.out.println(	"2 - Commande n°25894 - Etat = En Cours\n"
						+ 	"1 - Commande n°53886 - Etat = Prête à l'envoi\n\n"
						+ 	"0 - Retour au menu principal");
	}

	private void majStock() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************* Mise à jour du stock *********************/\n"
							+ 	"Quel élément du stock voulez-vous mettre à jour?\n"
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
				case 6 : if(majStockTirage()) break; else return;
				case 5 : if(majStockAlbum()) break; else return;
				case 4 : if(majStockCalendrierMural()) break; else return;
				case 3 : if(majStockCalendrierBureau()) break; else return;
				case 2 : if(majStockAgenda365J()) break; else return;
				case 1 : if(majStockAgenda52S()) break; else return;
				case 0 : quitter(); return;
				default : General.erreurDeChoix(); break;
			}
		}
	}

	private boolean majStockTirage() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************************** Tirage ****************************/\n"
							+ 	"5 - Recevoir une livraison de feuilles format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison de feuilles format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison de feuilles format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison de feuilles format A5 Qualité Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supérieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supérieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requête de maj du stock de format 'format' et de qualite 'qualite' en quantité 'nombreMaj'*/
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock de tirage format "+format+" en qualité "+qualite+".\n");
		}
	}

	private boolean majStockAlbum() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************************** Albums ****************************/\n"
							+ 	"5 - Recevoir une livraison d'albums format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison d'albums format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison d'albums format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison d'albums format A5 Qualité Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supérieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supérieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requête de maj du stock de format 'format' et de qualite 'qualite' en quantité 'nombreMaj'*/
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock d'albums format "+format+" en qualité "+qualite+".\n");
		}
	}

	private boolean majStockCalendrierMural() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************** Calendriers Muraux **********************/\n"
							+ 	"5 - Recevoir une livraison de calendriers muraux format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison de calendriers muraux format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison de calendriers muraux format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison de calendriers muraux format A5 Qualité Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supérieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supérieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requête de maj du stock de format 'format' et de qualite 'qualite' en quantité 'nombreMaj'*/
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock de calendriers muraux format "+format+" en qualité "+qualite+".\n");
		}
	}

	private boolean majStockCalendrierBureau() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************** Calendriers Bureau **********************/\n"
							+ 	"5 - Recevoir une livraison de calendriers bureau format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison de calendriers bureau format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison de calendriers bureau format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison de calendriers bureau format A5 Qualité Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supérieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supérieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requête de maj du stock de format 'format' et de qualite 'qualite' en quantité 'nombreMaj'*/
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock de calendriers bureau format "+format+" en qualité "+qualite+".\n");
		}
	}

	private boolean majStockAgenda365J() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/*********************** Agenda 365 Jours ***********************/\n"
							+ 	"5 - Recevoir une livraison d'agendas 365 Jours format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison d'agendas 365 Jours format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison d'agendas 365 Jours format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison d'agendas 365 Jours format A5 Qualité Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supérieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supérieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requête de maj du stock de format 'format' et de qualite 'qualite' en quantité 'nombreMaj'*/
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock d'agendas 365 Jours format "+format+" en qualité "+qualite+".\n");
		}
	}

	private boolean majStockAgenda52S() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************** Agenda 52 Semaines **********************/\n"
							+ 	"5 - Recevoir une livraison d'agendas 52 Semaines format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison d'agendas 52 Semaines format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison d'agendas 52 Semaines format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison d'agendas 52 Semaines format A5 Qualité Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supérieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supérieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantité recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requête de maj du stock de format 'format' et de qualite 'qualite' en quantité 'nombreMaj'*/
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock d'agendas 52 Semaines format "+format+" en qualité "+qualite+".\n");
		}
	}

	private void realiserImpression() {
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/***************** Réalisation d'une impression *****************/\n"
							+ 	"Voici les impressions en attente...\n");
			ArrayList<Impression> impressions = new ArrayList<Impression>();
			/*Requête des impressions en attente de réalisation*/
			
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<impressions.size(); num++) {
				if(reponse-1 == num) {
					Impression impression = impressions.get(reponse);
				}else if(reponse == -1) {
					return;
				}
			}
		}
	}
	
	private void ImpressionsToString(ArrayList<Commande> commandes) {
		// TODO Auto-generated method stub
		System.out.println(	"2 - Impression n°25894 - Commande n°25874\n"
						+ 	"1 - Impression n°53886 - Commande n°98536\n\n"
						+ 	"0 - Retour au menu principal");
	}

	private void supprimerFichierImage() {
		System.out.println("/*********** Suppression d'un/des fichier(s) image(s) ***********/\n");
	}

	private void supprimerClient() {
		System.out.println("/****************** Suppression d'un/des clients ****************/\n");
	}

	private void quitter() {
		System.out.println("A bientôt");
	}
}
