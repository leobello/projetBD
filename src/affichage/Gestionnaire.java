package affichage;

import java.util.ArrayList;

import BDD.*;
import BDD.Client;
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
							+	"2 - Envoyer une commande\n"
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
				case 3 : if(getStats("Categorie")) break; else return;
				case 2 : if(getStats("Format")) break; else return;
				case 1 : if(getStats("Qualite")) break; else return;
				case 0 : quitter(); return;
				default : General.erreurDeChoix(); break;
			}
		}
	}						

	private boolean getStats(String filtre) {
		int reponse = -1;
		/*requêtes des impressions vendues ordonnées selon le filtre*/ 
			/*en attendant on simule pour qualité*/
			ArrayList<String> typeStats = new ArrayList<String>();
			typeStats.add("Supérieur");
			typeStats.add("Moyenne");
			ArrayList<Double> stats = new ArrayList<Double>();
			stats.add(10.0);
			stats.add(0.25);
			stats.add(30.0);
			stats.add(0.75);
		System.out.println(	"/******* Statistiques de vente des produits par "+filtre+" *******\n");
		statsToString(typeStats, stats, filtre);
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

	private void statsToString(ArrayList<String> typeStats, ArrayList<Double> stats, String filtre) {
		for(int i=0; i<typeStats.size(); i++) {
			System.out.println(	"|------------------------------------------------------------------\n"
							+ 	"| Nombre de vente de "+filtre+" "+typeStats.get(i)+" : "+stats.get(2*i)+"\n"
							+ 	"| Pourcentage de vente de "+filtre+" "+typeStats.get(i)+" : "+stats.get(2*i+1)+"\n"
							+ 	"|------------------------------------------------------------------\n");
		}
	}

	private void majCommande() {
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
			String choix = validationEtatCommandeAMaj(this.commande);
			switch(choix) 
			{
				case "E" : /*Requête de maj de la commande choisie à l'état Envoyé*/
						System.out.println("La commande n°"+this.commande.getNumCommande()+" à été envoyée\n");
						break;
				case "return" : break;
				case "returnMenu" : return;
			} 
		}
	}

	private String validationEtatCommandeAMaj(Commande commande) {
		int reponse;
		while(true) {
			System.out.println("Voulez-vous vraiment envoyer la commande n°"+commande.getNumCommande()+" ?\n\n"
							+	"2 - Envoyer\n\n"
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
	}					   

	private Commande choixCommandeAMaj() {
		int reponse = -1;
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		commandes.add(new Commande());
		/*requête des différentes commandes dans l'état "En cours" et "Prête à l'envoi"*/
		commandesToString(commandes);
		reponse = LectureClavier.lireEntier("\nChoix :");
		for(int num = 0; num<commandes.size(); num++) {
			/*if(reponse-1 == num) {
				return commandes.get(num);
			}*/
			if(reponse == 0)return null;
			Commande commande = new Commande();
			commande.setNumCommande(25894);
			return commande;
		}
		return null;
	}

	private void commandesToString(ArrayList<Commande> commandes) {
		// TODO Auto-generated method stub
		System.out.println(	"2 - Commande n°25894 - Etat = Prête à l'envoi\n"
						+ 	"1 - Commande n°53886 - Etat = Prête à l'envoi\n\n"
						+ 	"0 - Retour au menu principal");
	}

	private void majStock() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************* Mise à jour du stock *********************/\n"
							+ 	"Quel élément du stock voulez-vous mettre à jour?\n"
							+ 	"7 - Cadre"
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
				case 7 : if(majStockElement("Cadre")) break; else return;
				case 6 : if(majStockElement("Tirage")) break; else return;
				case 5 : if(majStockElement("Album")) break; else return;
				case 4 : if(majStockElement("Mural")) break; else return;
				case 3 : if(majStockElement("Bureau")) break; else return;
				case 2 : if(majStockElement("Jours")) break; else return;
				case 1 : if(majStockElement("Semaines")) break; else return;
				case 0 : return;
				default : General.erreurDeChoix(); break;
			}
		}
	}

	private boolean majStockElement(String element) {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************************** Tirage ****************************/\n"
							+ 	"5 - Recevoir une livraison de "+element+" format A4 Qualité Supérieure\n"
							+ 	"4 - Recevoir une livraison de "+element+" format A4 Qualité Moyenne\n"
							+ 	"3 - Recevoir une livraison de "+element+" format A5 Qualité Supérieure\n"
							+ 	"2 - Recevoir une livraison de "+element+" format A5 Qualité Moyenne\n\n"
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
			System.out.println( nombreMaj+" exemplaires ont étés ajoutés au stock de "+element+" format "+format+" en qualité "+qualite+".\n");
		}
	}

	private void realiserImpression() {
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/***************** Réalisation d'une impression *****************/\n"
							+ 	"Voici les impressions en attente...\n");
			ArrayList<Impression> impressions = new ArrayList<Impression>();
			/*Requête des impressions en attente de réalisation*/Impression imp = new Impression(); imp.setNumImpression(25894); impressions.add(imp); impressions.add(imp);
			impressionsToString(impressions);
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<=impressions.size(); num++) {
				if(reponse-1 == num) {
					Impression impression = impressions.get(num);
					String choix = faireImpression(impression);
					if(choix.equals("V")) {
						/*Requête de mise à jour d'une impression avec mise à jour de la commande si nécessaire.*/
						System.out.println("L'impression n° "+impression.getNumImpression()+" à été réalisée.");
					}else if(choix.equals("returnMenu")) {
						return;
					}
				}else if(reponse-1 == -1) {
					return;
				}
			}
		}
	}
	
	private String faireImpression(Impression impression) {
		int reponse;
		while(true) {
			System.out.println("Voulez-vous vraiment effectuer l'impression n°"+impression.getNumImpression()+" ?\n\n"
							+	"2 - Valider\n\n"
							+	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : return "V";
				case 1 : return "return";
				case 0 : return "returnMenu";
				default : General.erreurDeChoix(); break;
			}	
		}
	}

	private void impressionsToString(ArrayList<Impression> impressions) {
		System.out.println(	"2 - Impression n°25894 - Commande n°25874\n"
						+ 	"1 - Impression n°53886 - Commande n°98536\n\n"
						+ 	"0 - Retour au menu principal");
	}

	private void supprimerFichierImage() {
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/**************** Suppression d'un fichier image ****************/\n"
							+ 	"Voici les fichiers images effacables...\n");
			ArrayList<FichierImage> fichiersImage = new ArrayList<FichierImage>();
			/*Requête des impressions en attente de réalisation*/FichierImage fi = new FichierImage(); fi.setPath("512587/photo/vacance.png"); fichiersImage.add(fi); fichiersImage.add(fi);
			fichiersImageToString(fichiersImage);
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<=fichiersImage.size(); num++) {
				if(reponse-1 == num) {
					FichierImage fichierImage = fichiersImage.get(num);
					String choix = validerEffacerFichierImage(fichierImage);
					if(choix.equals("V")) {
						/*Requête de suppression d'un fichier image avec retour différent si il est effacé ou mis en attente*/
						System.out.println("Le fichier image n° "+fichierImage.getPath()+" à été effacé.");
					}else if(choix.equals("returnMenu")) {
						return;
					}
				}else if(reponse-1 == -1) {
					return;
				}
			}
		}
	}

	private String validerEffacerFichierImage(FichierImage fichierImage) {
		int reponse;
		while(true) {
			System.out.println("Voulez-vous vraiment effacer le fichier image : "+fichierImage.getPath()+" ?\n\n"
							+	"2 - Valider\n\n"
							+	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : return "V";
				case 1 : return "return";
				case 0 : return "returnMenu";
				default : General.erreurDeChoix(); break;
			}	
		}
	}

	private void fichiersImageToString(ArrayList<FichierImage> fichiersImage) {
		System.out.println(	"2 - fichier Image : "+fichiersImage.get(1).getPath()+" - Client Louis REYNAUD\n"
						+ 	"1 - fichier Image : "+fichiersImage.get(0).getPath()+"- Client Louis REYNAUD\n\n"
						+ 	"0 - Retour au menu principal");
	}

	private void supprimerClient() {
		System.out.println();
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/********************* Suppression d'un client ******************/\n"
							+ 	"Voici les clients supprimables...\n");
			ArrayList<BDD.Client> clients = new ArrayList<BDD.Client>();
			/*Requête des impressions en attente de réalisation*/BDD.Client fi = new BDD.Client(); fi.setMailClient("louisreynaud@gmail.com"); clients.add(fi); clients.add(fi);
			clientsToString(clients);
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<=clients.size(); num++) {
				if(reponse-1 == num) {
					BDD.Client client = clients.get(num);
					String choix = validerEffacerClient(client);
					if(choix.equals("V")) {
						/*Requête de suppression d'un client et de ses fichiers image supprimables*/
						System.out.println("Le client "+client.getMailClient()+" à été désactivé.");
					}else if(choix.equals("returnMenu")) {
						return;
					}
				}else if(reponse-1 == -1) {
					return;
				}
			}
		}
	}

	private String validerEffacerClient(Client client) {
		int reponse;
		while(true) {
			System.out.println("Voulez-vous vraiment supprimer le client : "+client.getMailClient()+" ?\n\n"
							+	"2 - Valider\n\n"
							+	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : return "V";
				case 1 : return "return";
				case 0 : return "returnMenu";
				default : General.erreurDeChoix(); break;
			}	
		}
	}

	private void clientsToString(ArrayList<Client> clients) {
		System.out.println(	"2 - fichier Image : "+clients.get(1).getMailClient()+" - Client Louis REYNAUD\n"
				+ 	"1 - Client : "+clients.get(0).getMailClient()+"- Client Louis REYNAUD\n\n"
				+ 	"0 - Retour au menu principal");
		
	}

	private void quitter() {
		System.out.println("A bientôt");
	}
}
