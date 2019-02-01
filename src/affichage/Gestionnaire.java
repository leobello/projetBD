package affichage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import BDD.*;
import BDD.Client;
import Controler._GlobalControler;
import serviceBD.GestionStock;
import serviceBD.LectureClavier;

public class Gestionnaire extends TypeUtilisateur {

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
						+ 	"Selon quels crit�res souhaitez-vous ces statistiques?\n"
						+ 	"3 - Par cat�gorie\n"
						+ 	"2 - Par format\n"
						+ 	"1 - Par qualit�\n\n"
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
		/*requ�tes des impressions vendues ordonn�es selon le filtre*/ 
			/*en attendant on simule pour qualit�*/
			ArrayList<String> typeStats = new ArrayList<String>();
			typeStats.add("Sup�rieur");
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
			System.out.println(	"/****************** Envoi d'une commande ******************/\n"
							+ 	"Quelle commande souhaitez-vous envoyer?\n");
			Commande commande = choixCommandeAMaj();
			if(commande == null) 
			{
				return;
			} 
			String choix = validationEtatCommandeAMaj(commande);
			switch(choix) 
			{
				case "E" :
						commande.setStatutCommande("Envoy�e");
						//CRUDInterface<Commande> commandeControler = _GlobalControler.getCommandeControler();
						//commandeControler.update(commande);
						System.out.println("La commande n�"+commande.getNumCommande()+" � �t� envoy�e\n");
						break;
				case "return" : break;
				case "returnMenu" : return;
			} 
		}
	}

	private String validationEtatCommandeAMaj(Commande commande) {
		int reponse = -1;
		while(true) {
			System.out.println("Voulez-vous vraiment envoyer la commande n�"+commande.getNumCommande()+" ?\n\n"
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
		//commandes.add(new Commande(General.getDateNow(), "Domicile", "Pr�te � l'envoi", 51478, (float) 10));
		//commandes.add(new Commande(General.getDateNow(), "En retrait", "Pr�te � l'envoi", 69854, (float) 10));
		/*requ�te des diff�rentes commandes dans l'�tat "Pr�te � l'envoi"*/
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
			System.out.println(commandes.size()-i+" - Commande n�"+commandes.get(commandes.size()-1-i).getNumCommande()+" - Etat = "+commandes.get(commandes.size()-1-i).getStatutCommande()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
	}

	private void majStock() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/********************* Mise � jour du stock *********************/\n"
							+ 	"Quel �l�ment du stock voulez-vous mettre � jour?\n"
							+ 	"7 - Cadre\n"
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
							+ 	"5 - Recevoir une livraison de "+element+" format A4 Qualit� Sup�rieure\n"
							+ 	"4 - Recevoir une livraison de "+element+" format A4 Qualit� Moyenne\n"
							+ 	"3 - Recevoir une livraison de "+element+" format A5 Qualit� Sup�rieure\n"
							+ 	"2 - Recevoir une livraison de "+element+" format A5 Qualit� Moyenne\n\n"
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
			//GestionStock stockControl = new GestionStock();
			//stockControl.incrStock(nombreMaj, element, qualite, format);
			System.out.println( nombreMaj+" exemplaires ont �t�s ajout�s au stock de "+element+" format "+format+" en qualit� "+qualite+".\n");
		}
	}

	private void realiserImpression() {
		int reponse = -1;
		while(true)
		{
			System.out.println(	"/***************** R�alisation d'une impression *****************/\n"
							+ 	"Voici les impressions en attente...\n");
			ArrayList<Impression> impressions = new ArrayList<Impression>();
			/*Requ�te des impressions en attente de r�alisation*/Impression imp = new Impression(25894, "lepathdelimpression", false, "SUPERIEUR", "A4");
			imp.setClient(new Client("l@r.com", "Reynaud", "Louis", "mdp"));
			impressions.add(imp); 
			impressions.add(imp);
			impressionsToString(impressions);
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<=impressions.size(); num++) {
				if(reponse-1 == num) {
					Impression impression = impressions.get(num);
					String choix = faireImpression(impression);
					if(choix.equals("V")) {
						impression.setImpression_ok(true);
						//CRUDInterface<Impression> impressionControler = _GlobalControler.getImpressionControler();
						// impressionControler.update(impression);
						System.out.println("L'impression n� "+impression.getNumImpression()+" � �t� r�alis�e.");
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
			System.out.println("Voulez-vous vraiment effectuer l'impression n�"+impression.getNumImpression()+" ?\n\n"
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
			System.out.println(impressions.size()-i+" - Impression n�"+impressions.get(impressions.size()-1-i).getNumImpression());
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
			/*Requ�te des impressions en attente de r�alisation*/FichierImage fi = new FichierImage("512587/photo/vacance.png", "", "", 0, General.getDateNow()); fi.setProprietaire(new Client("louisreynaud26@gmail.com", "Reynaud", "Louis", "MotDePasse"));fichiersImage.add(fi); fichiersImage.add(fi);
			fichiersImageToString(fichiersImage);
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<=fichiersImage.size(); num++) {
				if(reponse-1 == num) {
					FichierImage fichierImage = fichiersImage.get(num);
					String choix = validerEffacerFichierImage(fichierImage);
					if(choix.equals("V")) {
						//CRUDInterface<FichierImage> fiControler = _GlobalControler.getFichierControler();
						//fiControler.delete(fichierImage.getPath(), fichierImage.getProprietaire().getMailClient());
						System.out.println("Le fichier image n� "+fichierImage.getPath()+" � �t� effac�.");
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
		/*System.out.println(	"2 - fichier Image : "+fichiersImage.get(1).getPath()+" - Client Louis REYNAUD\n"
						+ 	"1 - fichier Image : "+fichiersImage.get(0).getPath()+"- Client Louis REYNAUD\n\n"
						+ 	"0 - Retour au menu principal");*/
		for(int i=0; i<fichiersImage.size(); i++) {
			System.out.println(fichiersImage.size()-i+" - fichier Image : "+fichiersImage.get(fichiersImage.size()-1-i).getPath()+" - Propri�taire : "+fichiersImage.get(fichiersImage.size()-1-i).getProprietaire(). getMailClient());
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
			/*Requ�te des impressions en attente de r�alisation*/BDD.Client fi = new BDD.Client("louisreynaud@gmail.com", "Louis", "Reynaud", "motdepasse"); clients.add(fi); clients.add(fi);
			clientsToString(clients);
			reponse = LectureClavier.lireEntier("\nChoix :");
			for(int num = 0; num<=clients.size(); num++) {
				if(reponse-1 == num) {
					BDD.Client client = clients.get(num);
					String choix = validerEffacerClient(client);
					if(choix.equals("V")) {
						//ClientInterface clientControler = _GlobalControler.getClientControler();
						//fiControler.delete(.getPath(), fichierImage.getProprietaire().getMailClient());
						System.out.println("Le client "+client.getMailClient()+" � �t� d�sactiv�.");
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
		/*System.out.println(	"2 - Client : "+clients.get(1).getMailClient()+" - Nom : Louis REYNAUD\n"
				+ 	"1 - Client : "+clients.get(0).getMailClient()+"- Nom : Louis REYNAUD\n\n"
				+ 	"0 - Retour au menu principal");*/
		for(int i=0; i<clients.size(); i++) {
			System.out.println(clients.size()-i+" - Client : "+clients.get(clients.size()-1-i).getMailClient()+" - Nom : "+clients.get(clients.size()-1-i).getNom()+" "+clients.get(clients.size()-1-i).getPrenom());
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
		
	}

	private void quitter() {
		System.out.println("A bient�t");
	}
}