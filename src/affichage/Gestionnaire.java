<<<<<<< HEAD
package affichage;

import java.sql.Date;
import java.sql.Timestamp;
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
		commandes.add(new Commande(new Date(new Timestamp(System.currentTimeMillis()).getTime()), "Domicile", "Prête à l'envoi", 51478, 10));
		commandes.add(new Commande(new Date(new Timestamp(System.currentTimeMillis()).getTime()), "En retrait", "Prête à l'envoi", 69854, 10));
		/*requête des différentes commandes dans l'état "En cours" et "Prête à l'envoi"*/
		commandesToString(commandes);
		reponse = LectureClavier.lireEntier("\nChoix :");
		for(int num = 0; num<commandes.size(); num++) {
			if(reponse-1 == num) {
				return commandes.get(num);
			}
			if(reponse == 0)return null;
		}
		return null;
	}

	private void commandesToString(ArrayList<Commande> commandes) {
		for(int i=0; i<commandes.size(); i++) {
			System.out.println(commandes.size()-i+" - Commande n°"+commandes.get(commandes.size()-i).getNumCommande()+" - Etat = "+commandes.get(commandes.size()-i).getStatutCommande()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
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
			/*Requête des impressions en attente de réalisation*/Impression imp = new Impression(25894, "lepathdelimpression"); impressions.add(imp); impressions.add(imp);
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
		for(int i=0; i<impressions.size(); i++) {
			System.out.println(impressions.size()-i+" - Impression n°"+impressions.get(impressions.size()-i).getNumImpression()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
	}

	private void supprimerFichierImage() {
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/**************** Suppression d'un fichier image ****************/\n"
							+ 	"Voici les fichiers images effacables...\n");
			ArrayList<FichierImage> fichiersImage = new ArrayList<FichierImage>();
			/*Requête des impressions en attente de réalisation*/FichierImage fi = new FichierImage("512587/photo/vacance.png", "", "", 0, new Date(new Timestamp(System.currentTimeMillis()).getTime())); fi.setProprietaire(new Client("louisreynaud26@gmail.com", "Reynaud", "Louis", "MotDePasse"));fichiersImage.add(fi); fichiersImage.add(fi);
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
		for(int i=0; i<fichiersImage.size(); i++) {
			System.out.println(fichiersImage.size()-i+" - fichier Image : "+fichiersImage.get(fichiersImage.size()-i).getPath()+" - Propriétaire : "+fichiersImage.get(fichiersImage.size()-i).getProprietaire(). getMailClient()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
	}

	private void supprimerClient() {
		System.out.println();
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/********************* Suppression d'un client ******************/\n"
							+ 	"Voici les clients supprimables...\n");
			ArrayList<BDD.Client> clients = new ArrayList<BDD.Client>();
			/*Requête des impressions en attente de réalisation*/BDD.Client fi = new BDD.Client("louisreynaud@gmail.com", "Louis", "Reynaud", "motdepasse"); clients.add(fi); clients.add(fi);
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
		System.out.println(	"2 - Client : "+clients.get(1).getMailClient()+" - Nom : Louis REYNAUD\n"
				+ 	"1 - Client : "+clients.get(0).getMailClient()+"- Nom : Louis REYNAUD\n\n"
				+ 	"0 - Retour au menu principal");
		for(int i=0; i<clients.size(); i++) {
			System.out.println(clients.size()-i+" - Client : "+clients.get(clients.size()-i).getMailClient()+" - Nom : "+clients.get(clients.size()-i).getNom()+" "+clients.get(clients.size()-i).getPrenom()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
		
	}

	private void quitter() {
		System.out.println("A bientôt");
	}
}
=======
package affichage;

import java.util.ArrayList;

import serviceBD.LectureClavier;

public class Gestionnaire extends TypeUtilisateur {

	private Object commande;

	@Override
	public void run() {
		int reponse;
		while(true) 
		{
			reponse = -1;
			System.out.println(	"/**********************************************************************************************/\n"
							+ 	"Bienvenue dans l'application dï¿½diï¿½e au gestionnaire. \n\n"
							+	"Que souhaitez vous faire?\n\n"
							+	"6 - Supprimer un client\n"
							+	"5 - Supprimer un fichier image\n"
							+	"4 - Rï¿½aliser une impression\n"
							+	"3 - Mettre ï¿½ jour le stock\n"
							+	"2 - Mettre ï¿½ jour une commande\n"
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
						+ 	"Selon quels critï¿½res souhaitez-vous ces statistiques?\n"
						+ 	"3 - Par catï¿½gorie\n"
						+ 	"2 - Par format\n"
						+ 	"1 - Par qualitï¿½\n\n"
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
		/*requï¿½te de statistiques sur les ventes des produits ordonnï¿½s par qualitï¿½es*/
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
		/*requï¿½te de statistiques sur les ventes des produits ordonnï¿½s par Format*/
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
		/*requï¿½te de statistiques sur les ventes des produits ordonnï¿½s par Catï¿½gories*/
		System.out.println(	"/******* Statistiques de vente des produits par Catï¿½gories *******\n"
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
		System.out.println(	"/****************** Mise ï¿½ jour d'une commande ******************/\n"
						+ 	"Quelle commande souhaitez-vous mettre ï¿½ jour?");
		this.commande = choixCommandeAMaj();
	}					   

	private Object choixCommandeAMaj() {
		int reponse = -1;
		ArrayList<Object> commandes = new ArrayList<Object>();
		/*requï¿½te des diffï¿½rentes commandes pret Ã  l'envoi disponibles*/
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
		System.out.println("Voilï¿½ les commandes!");
	}

	private void majStock() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************* Mise ï¿½ jour du stock *********************/\n"
							+ 	"Quel ï¿½lï¿½ment du stock voulez-vous mettre ï¿½ jour?\n"
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
							+ 	"5 - Recevoir une livraison de "+ type_impression +" format A4 Qualitï¿½ Supï¿½rieure\n"
							+ 	"4 - Recevoir une livraison de "+ type_impression +" format A4 Qualitï¿½ Moyenne\n"
							+ 	"3 - Recevoir une livraison de "+ type_impression +" format A5 Qualitï¿½ Supï¿½rieure\n"
							+ 	"2 - Recevoir une livraison de "+ type_impression +" format A5 Qualitï¿½ Moyenne\n\n"
							+ 	"1 - Retour\n"
							+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			int nombreMaj = 0;
			String format = "";
			String qualite = "";
			switch(reponse) 
			{
				case 5 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantitï¿½ recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Supï¿½rieure"; 
							break; 
				case 4 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantitï¿½ recevez-vous?\n\nChoix :"); 
							format="A4"; 
							qualite="Moyenne"; 
							break; 
				case 3 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantitï¿½ recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Supï¿½rieure"; 
							break;
				case 2 : 	nombreMaj=LectureClavier.lireEntier("Quelle quantitï¿½ recevez-vous?\n\nChoix :"); 
							format="A5"; 
							qualite="Moyenne"; 
							break;
				case 1 : return true;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
			/*Requï¿½te de maj du stock de  format 'format' et de qualite 'qualite' en quantitï¿½ 'nombreMaj'
			 * "UPDATE STOCK 
					SET QUANTITESTOCK = QUANTITESTOCK + "+nombreMaj+"
					WHERE QUALITE = '"+qualite+"' AND format = '"+format+"' and type_impression = '"+type_impression+"';  */
			System.out.println( nombreMaj+" exemplaires ont ï¿½tï¿½s ajoutï¿½s au stock "+type_impression+" format "+format+" en qualitï¿½ "+qualite+".\n");
		}
	}

	private void realiserImpression() {
		System.out.println(	"/***************** Rï¿½alisation d'une impression *****************/\n"
						+ 	"Voici les impressions en attente...\n");
		ArrayList<Object> impressions = new ArrayList<Object>();
		/*Requï¿½te des impressions en attente de rï¿½alisation*/
		Object impression = impressions.get(LectureClavier.lireEntier("\nChoix :"));
		
	}

	private void supprimerFichierImage() {
		System.out.println("/*********** Suppression d'un/des fichier(s) image(s) ***********/\n");
	}

	private void supprimerClient() {
		System.out.println("/****************** Suppression d'un/des clients ****************/\n");
	}

	private void quitter() {
		System.out.println("A bientï¿½t");
	}
}
>>>>>>> master
