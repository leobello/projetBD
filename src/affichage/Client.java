package affichage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import BDD.*;
import Controler.*;
import serviceBD.GestionStock;
import serviceBD.LectureClavier;

public class Client extends TypeUtilisateur {
	
	private BDD.Client clientActuel;
	private _GlobalControler globalControler;
	private CRUDInterface crudControl;
	
	public Client() {
		this.clientActuel = null;
	}
	
	public void run() {
		System.out.println(	"/**********************************************************************************************/\n"
						+ 	"Bienvenue dans l'application dédiée au client. \n");
		int reponse;
		while(true) 
		{
			reponse = -1;
			if(clientActuel != null) {
				System.out.println("Session Actuelle : "+this.clientActuel.getPrenom()+" "+this.clientActuel.getNom()+"\n");
			}
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
								+	"6 - Visualiser mes impressions (Work in progress)\n"
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
		int reponse = -1;
		boolean flagUp = false;
		System.out.println("/**************** Suppression d'une impression ***************/\n\n");
		ArrayList<Impression> impressions = new ArrayList<Impression>();
		/*Requête des impressions appartenant au client*/
			//EN ATTENDANT
			Impression impression1 = new Impression(1, "path/1", false, "SUPERIEUR", "A4"); 
			impression1.setClient(this.clientActuel);
			Impression impression2 = new Impression(2, "path/2", false, "SUPERIEUR", "A4");
			impression2.setClient(this.clientActuel);
			Impression impression3 = new Impression(3, "path/3", false, "SUPERIEUR", "A4");
			impression3.setClient(this.clientActuel);
			impressions.add(impression1);
			impressions.add(impression2);
			impressions.add(impression3);
		while(!flagUp) {
			impressionsToString(impressions);
			reponse = LectureClavier.lireEntier("Choisissez le fichier à supprimer\n");
			if(reponse>1 && reponse<=impressions.size()) {
				Impression impToDelete = impressions.get(reponse-1);
				String choix = validerSuppressionImpression(impToDelete);
				if(choix.equals("V")) {
					CRUDInterface<Impression> impressionControler = _GlobalControler.getImpressioncontroler();
					impressionControler.delete(impToDelete.getNumImpression());
					System.out.println("Le fichier Image : "+impToDelete.getPathImpression()+" à été supprimé.");
				}else if(choix.equals("returnMenu")) {
					return;
				}
			}if(reponse == 0) {
				return;
			}if(reponse == 1) {
				flagUp = true;
			}
		}	
	}

	private String validerSuppressionImpression(Impression impToDelete) {
		int reponse = -1;
		while(true) {
			System.out.println("Voulez-vous vraiment supprimer l'impression :"+impToDelete.getPathImpression()+" ?\n\n"
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

	private void impressionsToString(ArrayList<Impression> impressions) {
		for(int i=0; i<impressions.size(); i++) {
			System.out.println(impressions.size()-i+" - Impression : "+impressions.get(impressions.size()-1-i).getPathImpression()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
	}

	private void modifierImpression() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/************** Modification d'une impression *************/\n\n"
					+ "Quelle est le type de l'impression que vous souhaitez modifier?\n"
					+	"7 - Cadre\n"
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
				case 7 : if(creerCadre(true)) break; else return;
				case 6 : if(creerTirage(true)) break; else return;
				case 5 : if(creerAlbum(true)) break; else return;
				case 4 : if(creerCalendrierMuraux(true)) break; else return;
				case 3 : if(creerCalendrierBureau(true)) break; else return;
				case 2 : if(creerAgendaJours(true)) break; else return;
				case 1 : if(creerAgendaSemaines(true)) break; else return;
				case 0 : return;
				default : General.erreurDeChoix(); break;
			}
		}
	}

	private void supprimerFichierImage() {
		int reponse = -1;
		boolean flagUp = false;
		System.out.println("/*************** Suppression d'un fichier Image **************/\n\n");
		ArrayList<FichierImage> fichierImages = new ArrayList<FichierImage>();
		/*Requête des fichiers images appartenant au client*/
			//EN ATTENDANT
			FichierImage fi = new FichierImage("path/1", "", "", 0, General.getDateNow()); fichierImages.add(fi);fichierImages.add(fi);fichierImages.add(fi);
		while(!flagUp) {
			fiToString(fichierImages);
			reponse = LectureClavier.lireEntier("Choisissez le fichier à supprimer\n");
			if(reponse>1 && reponse<=fichierImages.size()) {
				FichierImage fiToDelete = fichierImages.get(reponse-1);
				String choix = validerSuppressionFichierImage(fiToDelete);
				if(choix.equals("V")) {
					CRUDInterface<FichierImage> fichierImageControler = _GlobalControler.getFichierControler();
					//fichierImageControler.delete(fiToDelete.getPath());
					System.out.println("Le fichier Image : "+fiToDelete.getPath()+" à été supprimé.");
				}else if(choix.equals("returnMenu")) {
					return;
				}
			}if(reponse == 0) {
				return;
			}if(reponse == 1) {
				flagUp = true;
			}
		}
	}

	private String validerSuppressionFichierImage(FichierImage fiToDelete) {
		int reponse = -1;
		while(true) {
			System.out.println("Voulez-vous vraiment supprimer le fichier image :"+fiToDelete.getPath()+" ?\n\n"
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

	private void fiToString(ArrayList<FichierImage> fi) {
		for(int i=0; i<fi.size(); i++) {
			System.out.println(fi.size()-i+" - Fichier Image : "+fi.get(fi.size()-1-i).getPath()+"\n");
		}
		System.out.println( 	"\n0 - Retour au menu principal\n");
	}

	private void creerCommande() {
		int reponse = -1;
		boolean flagUp = false;
		System.out.println("/****************** Création d'une commande *******************/\n\n");
		/*Requête des impressions appartenant aux clients.*/
			//EN ATTENDANT
			ArrayList<Impression> impressions = (ArrayList<Impression>) this.clientActuel.getImpressions();
			/*Impression imp1 = new Tirage(0, "imp1", false,"SUPERIEUR", "A4");
			imp1.setClient(this.clientActuel);
			Impression imp2 = new Tirage(1, "imp2", false,"MOYENNE", "A4");
			imp2.setClient(this.clientActuel);
			Impression imp3 = new Tirage(2, "imp3", false,"SUPERIEUR", "A5");
			imp3.setClient(this.clientActuel);
			impressions.add(imp1);impressions.add(imp2);impressions.add(imp3);*/
		ArrayList<Integer> nbTaken = new ArrayList<Integer>();
		for(int i =0; i<impressions.size(); i++) {
			nbTaken.add(0);
		}
		while(!flagUp) {
			int nbExemplaireTotal = 0;
			for(int i=0; i<nbTaken.size();i++)
				nbExemplaireTotal +=nbTaken.get(i);
			System.out.println("Commande : \n"
							+ "Choisissez vos impressions : \n");
			if(nbExemplaireTotal!=0) {
				commandesImpressionsToString(impressions, nbTaken, true);
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse>1 && reponse<=impressions.size()+1) {
					do {
						nbTaken.set(reponse-2, LectureClavier.lireEntier("En combien d'exemplaire souhaitez-vous ce produit?\n"));
					}while(nbTaken.get(reponse-2)<0);
				}if(reponse == 0) {
					return;
				}if(reponse == 1) {
					flagUp = true;
				}
			}else {
				commandesImpressionsToString(impressions, nbTaken, false);
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse>0 && reponse<impressions.size()+1) {
					do {
						nbTaken.set(reponse-1, LectureClavier.lireEntier("En combien d'exemplaire souhaitez-vous ce produit?\n"));
					}while(nbTaken.get(reponse-1)<0);
				}if(reponse == 0) {
					return;
				}
			}
		}
		flagUp=false;
		String modelivraison = "";
		while(!flagUp) {
			System.out.println("Comment souhaitez-vous être livré?\n"
					+ 	"2 - En dépot relais\n"
					+ 	"1 - A domicile\n\n"
					+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : modelivraison = "POINT RELAIS"; flagUp = true; break;
				case 1 : modelivraison = "ADRESSE"; flagUp = true; break;
				case 0 : return;
				default : General.erreurDeChoix(); break;
			}
		}
		Couple<ArrayList<Article>> articlesMontant = getMontantArticles(impressions,nbTaken);
		Commande cmd = new Commande(General.getDateNow().toString(), modelivraison, "EN COURS", (int) new Timestamp(System.currentTimeMillis()).getTime(), (float) articlesMontant.getNumero());
		cmd.setClient(this.clientActuel);
		do{
			System.out.println("Veuillez régler votre commande :\n"
					+ "1 - Valider\n"
					+ "0 - Annuler et retour au menu Principal\n");
			reponse = LectureClavier.lireEntier("Choix : \n");
			switch(reponse) {
				case 1 : break;
				case 0 : return;
				default : General.erreurDeChoix(); break;
			}
		}while(!stockSuffisant(articlesMontant));
		CRUDInterface<Commande> commandeControler = _GlobalControler.getCommandeControler();
		commandeControler.create(cmd);
	}
	
	private boolean stockSuffisant(Couple<ArrayList<Article>> articlesMontant) {
		GestionStock gestStock = new GestionStock();
		for(int i = 0; i<articlesMontant.getGenerique().size(); i++) {
			if(	articlesMontant.getGenerique().get(i).getImpression() instanceof Tirage 
				&& !gestStock.decrStock(articlesMontant.getGenerique().get(i).getQuantite(), 
										"TIRAGE", 
										articlesMontant.getGenerique().get(i).getImpression().getQualite(), 
										articlesMontant.getGenerique().get(i).getImpression().getFormat()))
				return false;
			if( articlesMontant.getGenerique().get(i).getImpression() instanceof Cadre 
				&& !gestStock.decrStock(articlesMontant.getGenerique().get(i).getQuantite(), 
										"CADRE", 
										articlesMontant.getGenerique().get(i).getImpression().getQualite(), 
										articlesMontant.getGenerique().get(i).getImpression().getFormat()))
				return false;
			if( articlesMontant.getGenerique().get(i).getImpression() instanceof Mural 
				&& !gestStock.decrStock(articlesMontant.getGenerique().get(i).getQuantite(), 
										"MURAL", 
										articlesMontant.getGenerique().get(i).getImpression().getQualite(), 
										articlesMontant.getGenerique().get(i).getImpression().getFormat()))
				return false;
			if( articlesMontant.getGenerique().get(i).getImpression() instanceof Bureau 
				&& !gestStock.decrStock(articlesMontant.getGenerique().get(i).getQuantite(), 
										"BUREAU", 
										articlesMontant.getGenerique().get(i).getImpression().getQualite(), 
										articlesMontant.getGenerique().get(i).getImpression().getFormat()))
				return false;
			if( articlesMontant.getGenerique().get(i).getImpression() instanceof Jour 
				&& !gestStock.decrStock(articlesMontant.getGenerique().get(i).getQuantite(), 
										"JOUR", 
										articlesMontant.getGenerique().get(i).getImpression().getQualite(), 
										articlesMontant.getGenerique().get(i).getImpression().getFormat()))
				return false;
			if( articlesMontant.getGenerique().get(i).getImpression() instanceof Semaine 
				&& !gestStock.decrStock(articlesMontant.getGenerique().get(i).getQuantite(), 
										"SEMAINE", 
										articlesMontant.getGenerique().get(i).getImpression().getQualite(), 
										articlesMontant.getGenerique().get(i).getImpression().getFormat()))
				return false;
		}
		return true;
	}

	/**
	 * @param impressions
	 * @param nbTaken
	 * @return
	 */
	private Couple<ArrayList<Article>> getMontantArticles(ArrayList<Impression> impressions, ArrayList<Integer> nbTaken) {
		int montant = 0;
		ArrayList<Article> articles = new ArrayList<Article>();
		for(int i =0; i<impressions.size(); i++) {
			int prixU = 0;
			int nbPages = 1;
			if(nbTaken.get(i)!=0) {
				if(impressions.get(i).getQualite().equals("MOYENNE"))
					prixU = 1;
				else
					prixU = 2;
				Impression imp = impressions.get(i);
				if(imp instanceof Tirage) {
					nbPages=0;
					List<Couple<Photo>> photos = ((Tirage) imp).getPhotos();
					for(int nb=0; nb<photos.size(); nb++) {
						nbPages+=photos.get(nb).getNumero();
					}
				}
				montant+= nbTaken.get(i)*prixU*nbPages;
				Article article = new Article((int) new Timestamp(System.currentTimeMillis()).getTime(), nbTaken.get(i)*prixU*nbPages, nbTaken.get(i));
				article.setImpression(impressions.get(i));
				CRUDInterface<Article> articleControler = _GlobalControler.getArticleControler();
				articleControler.create(article);
				articles.add(article);
			}
		}
		return new Couple<ArrayList<Article>>(articles, montant);
	}

	private void commandesImpressionsToString(ArrayList<Impression> impressions, ArrayList<Integer> nbTaken, boolean asExemplaires) {
		if(asExemplaires) {
			for(int i=0; i<impressions.size();i++) {
					System.out.println(impressions.size()+1-i+" - Impression n°"+impressions.get(impressions.size()-1-i).getNumImpression()+" - "+impressions.get(impressions.size()-1-i).getPathImpression()+"("+nbTaken.get(impressions.size()-1-i)+")");
			}System.out.println("1 - Valider la commande\n"
							+ 	"0 - Retour au menu principal\n");
		} else {
			for(int i=0; i<impressions.size();i++) {
				System.out.println(impressions.size()-i+" - Impression n°"+impressions.get(impressions.size()-1-i).getNumImpression()+" - "+impressions.get(impressions.size()-1-i).getPathImpression()+"("+nbTaken.get(impressions.size()-1-i)+")");
			}System.out.println("0 - Retour au menu principal\n");
		}
	}

	private void visualiserDetailsCommande() {
		//Visualiser.detailsCommande();
		System.out.println("Fonctionnalité en cours de développement.\n");
	}

	private void visualiserImpressions() {
		//Visualiser.impressions();
		System.out.println("Fonctionnalité en cours de développement.\n");
	}

	private void visualiserListeImagesPartagees() {
		//Visualiser.listeImagesPartagees();
		System.out.println("Fonctionnalité en cours de développement.\n");
	}

	private void visualiserPhotosPopulaires() {
		//Visualiser.photosPopulaires();
		System.out.println("Fonctionnalité en cours de développement.\n");
	}
	
	private void creerImpression() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************** Création d'une impression ***************/\n\n"
					+ "Quel type d'impression voulez-vous créer?\n"
					+	"7 - Cadre\n"
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
				case 7 : if(creerCadre(false)) break; else return;
				case 6 : if(creerTirage(false)) break; else return;
				case 5 : if(creerAlbum(false)) break; else return;
				case 4 : if(creerCalendrierMuraux(false)) break; else return;
				case 3 : if(creerCalendrierBureau(false)) break; else return;
				case 2 : if(creerAgendaJours(false)) break; else return;
				case 1 : if(creerAgendaSemaines(false)) break; else return;
				case 0 : return;
				default : General.erreurDeChoix(); break;
			}
		}
	}
	
	private boolean creerAgendaSemaines(boolean isInModifMode) {
		int reponse = -1;
		String path = null;
		ArrayList<Couple<Photo>> photos = null;
		Semaine semaineToMod = null;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/************** Modification d'un Agenda Jours *************/\n\n");
				ArrayList<Semaine> semainesClient = new ArrayList<Semaine>();
				/*Requête de tous les agenda semaines du client*/
				System.out.println("Veuillez choisir quel agenda jour vous souhaitez modifier :\n");
				semainePathToString(semainesClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=semainesClient.size()){
					semaineToMod = semainesClient.get(reponse-1);
					path = semaineToMod.getPathImpression();
					photos = (ArrayList<Couple<Photo>>) semaineToMod.getPhotos();
					flagUp = true;
				}
			}
		}
		else {
			System.out.println("/*************** Création d'un Agenda Semaine **************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'agenda Semaines :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Agenda Semaines : "+path+"\n"
					+ "Photos :");
			ImpressionPhotosToString(photos, false, true, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'agenda Semaines :\n"); break;
					case 2 : 	int length = photos.size();
								photos = AjouterPhotoAPage(52, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"5 - Enregistrer l'agenda Semaine\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 5 : if(!isInModifMode) {
								if(enregistrerSemaine(path, photos)) return true; else break;
							}else {
								semaineToMod.setPathImpression(path);
								semaineToMod.setPhotos(photos);
								CRUDInterface<Semaine> semaineInterface = _GlobalControler.getSemaineControler();
								semaineInterface.update(semaineToMod);
							}break;
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'agenda Semaines :\n"); break;
					case 3 : photos = RetirerPhoto(photos, false, true, true); break;
					case 2 :	int length = photos.size();
								photos = AjouterPhotoAPage(52, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private boolean enregistrerSemaine(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		boolean resultRequest = false;
		CRUDInterface<Semaine> semaineControler = _GlobalControler.getSemaineControler();
		boolean flag = false;
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre Agenda Semaine?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre Agenda Semaine?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		Semaine toCreate = new Semaine(0, path, this.clientActuel, false, 52, qualite, format);
		if(semaineControler.create(toCreate)) {
			System.out.println("Agenda Semaine enregistré. \n");
			return true;
		}else{
			System.out.println("Attention cet Agenda Semaine existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private void semainePathToString(ArrayList<Semaine> semainesClient, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<semainesClient.size();i++) {
				System.out.println(semainesClient.size()-i+" - "+semainesClient.get(semainesClient.size()-1-i).getNumImpression()+" - "+semainesClient.get(semainesClient.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<semainesClient.size();i++) {
				System.out.println(semainesClient.get(semainesClient.size()-1-i).getNumImpression()+" - "+semainesClient.get(semainesClient.size()-1-i).getPathImpression());
			}
			return;
		}
	}

	private boolean creerAgendaJours(boolean isInModifMode) {
		int reponse = -1;
		String path = null;
		ArrayList<Couple<Photo>> photos = null;
		Jour jourToMod = null;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/************** Modification d'un Agenda Jours *************/\n\n");
				ArrayList<Jour> joursClient = new ArrayList<Jour>();
				/*Requête de tous les agenda jours du client*/
				System.out.println("Veuillez choisir quel agenda jour vous souhaitez modifier :\n");
				JoursPathToString(joursClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=joursClient.size()){
					jourToMod = joursClient.get(reponse-1);
					path = jourToMod.getPathImpression();
					photos = (ArrayList<Couple<Photo>>) jourToMod.getPhotos();
					flagUp = true;
				}
			}
		}
		else {
			System.out.println("/**************** Création d'un Agenda Jours ***************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'agenda Jours :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Agenda Jours : "+path+"\n"
					+ "Photos :");
			ImpressionPhotosToString(photos, false, true, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'agenda Jours :\n"); break;
					case 2 : 	int length = photos.size();
								photos = AjouterPhotoAPage(365, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"5 - Enregistrer l'agenda Jours\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 5 : if(!isInModifMode) {
								if(enregistrerJours(path, photos)) return true; else break;
							}else {
								jourToMod.setPathImpression(path);
								jourToMod.setPhotos(photos);
								CRUDInterface<Jour> jourInterface = _GlobalControler.getJourControler();
								jourInterface.update(jourToMod);
							}break;
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'agenda Jours :\n"); break;
					case 3 : photos = RetirerPhoto(photos, false, true, true); break;
					case 2 :	int length = photos.size();
								photos = AjouterPhotoAPage(365, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private void JoursPathToString(ArrayList<Jour> clientJours, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<clientJours.size();i++) {
				System.out.println(clientJours.size()-i+" - "+clientJours.get(clientJours.size()-1-i).getNumImpression()+" - "+clientJours.get(clientJours.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<clientJours.size();i++) {
				System.out.println(clientJours.get(clientJours.size()-1-i).getNumImpression()+" - "+clientJours.get(clientJours.size()-1-i).getPathImpression());
			}
			return;
		}
	}

	private boolean enregistrerJours(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		boolean resultRequest = false;
		CRUDInterface<Jour> jourControler = _GlobalControler.getJourControler();
		boolean flag = false;
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre Agenda Jour?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre Agenda Jour?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		Jour toCreate = new Jour(0, path, this.clientActuel, false, 365, qualite, format);
		if(jourControler.create(toCreate)) {
			System.out.println("Agenda Jour enregistré. \n");
			return true;
		}else
		{
			System.out.println("Attention ce Agenda Jour existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerCalendrierBureau(boolean isInModifMode) {
		int reponse = -1;
		String path = null;
		ArrayList<Couple<Photo>> photos = null;
		Bureau bureauToMod = null;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/*********** Modification d'un Calendrier Bureau ***********/\n\n");
				ArrayList<Bureau> bureauClient = new ArrayList<Bureau>();
				/*Requête de tous les Calendrier Bureau du client*/
				System.out.println("Veuillez choisir quel calendrier Bureau vous souhaitez modifier :\n");
				bureauPathToString(bureauClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=bureauClient.size()){
					bureauToMod = bureauClient.get(reponse-1);
					path = bureauToMod.getPathImpression();
					photos = (ArrayList<Couple<Photo>>) bureauToMod.getPhotos();
					flagUp = true;
				}
			}
		}else {
			System.out.println("/************** Création d'un calendrier Bureau *************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin du calendrier Bureau :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Calendrier Bureau : "+path+"\n"
					+ "Photos :");
			ImpressionPhotosToString(photos, false, true, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du calendrier Bureau :\n"); break;
					case 2 : 	int length = photos.size();
								photos = AjouterPhotoAPage(12, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"5 - Enregistrer le calendrier Bureau\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 5 : if(!isInModifMode) {
								if(enregistrerBureau(path, photos)) return true; else break;
							}else {
								bureauToMod.setPathImpression(path);
								bureauToMod.setPhotos(photos);
								CRUDInterface<Bureau> bureauInterface = _GlobalControler.getBureauControler();
								bureauInterface.update(bureauToMod);
							}break;
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du calendrier bureau :\n"); break;
					case 3 : photos = RetirerPhoto(photos, false, true, true); break;
					case 2 :	int length = photos.size();
								photos = AjouterPhotoAPage(12, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private void bureauPathToString(ArrayList<Bureau> bureauClient, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<bureauClient.size();i++) {
				System.out.println(bureauClient.size()-i+" - "+bureauClient.get(bureauClient.size()-1-i).getNumImpression()+" - "+bureauClient.get(bureauClient.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<bureauClient.size();i++) {
				System.out.println(bureauClient.get(bureauClient.size()-1-i).getNumImpression()+" - "+bureauClient.get(bureauClient.size()-1-i).getPathImpression());
			}
			return;
		}	
	}

	private boolean enregistrerBureau(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		CRUDInterface<Bureau> bureauInterface = _GlobalControler.getBureauControler();
		boolean flag = false;
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre calendrier Bureau?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre calendrier Bureau?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		Bureau toCreate = new Bureau(0, path, clientActuel, false, qualite, format);
		if(bureauInterface.create(toCreate)) {
			System.out.println("calendrier Bureau enregistré. \n");
			return true;
		}else
		{
			System.out.println("Attention ce calendrier Bureau existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerCalendrierMuraux(boolean isInModifMode) {
		int reponse = -1;
		String path = null;
		ArrayList<Couple<Photo>> photos = null;
		Mural muralToMod = null;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/************ Modification d'un Calendrier Mural ***********/\n\n");				
				ArrayList<Mural> muralClient = new ArrayList<Mural>();
				/*Requête de tous les calendrier mural du client*/
				System.out.println("Veuillez choisir quel calendrier Mural vous souhaitez modifier :\n");
				MuralPathToString(muralClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=muralClient.size()){
					muralToMod = muralClient.get(reponse-1);
					path = muralToMod.getPathImpression();
					photos = (ArrayList<Couple<Photo>>) muralToMod.getPhotos();
					flagUp = true;
				}
			}
		}else {
			System.out.println("/************** Création d'un calendrier Mural *************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin du calendrier mural :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Calendrier Mural : "+path+"\n"
					+ "Photos :");
			ImpressionPhotosToString(photos, false, true, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du calendrier mural :\n"); break;
					case 2 : 	int length = photos.size();
								photos = AjouterPhotoAPage(12, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"5 - Enregistrer le calendrier mural\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 5 : if(!isInModifMode) {
								if(enregistrerMural(path, photos)) return true; else break;
							}else {
								muralToMod.setPathImpression(path);
								muralToMod.setPhotos(photos);
								CRUDInterface<Mural> muralInterface = _GlobalControler.getMuralControler();
								muralInterface.update(muralToMod);
							}break;
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du calendrier mural :\n"); break;
					case 3 : photos = RetirerPhoto(photos, false, true, true); break;
					case 2 :	int length = photos.size();
								photos = AjouterPhotoAPage(12, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private void MuralPathToString(ArrayList<Mural> muralClient, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<muralClient.size();i++) {
				System.out.println(muralClient.size()-i+" - "+muralClient.get(muralClient.size()-1-i).getNumImpression()+" - "+muralClient.get(muralClient.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<muralClient.size();i++) {
				System.out.println(muralClient.get(muralClient.size()-1-i).getNumImpression()+" - "+muralClient.get(muralClient.size()-1-i).getPathImpression());
			}
			return;
		}	
	}

	private boolean enregistrerMural(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		CRUDInterface<Mural> muralControler = _GlobalControler.getMuralControler();
		boolean flag = false;
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre calendrier Mural?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre calendrier Mural?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		Mural toCreate = new Mural(0, path, clientActuel, false, qualite, format);
		if(muralControler.create(toCreate)) {
			System.out.println("calendrier mural enregistré. \n");
			return true;
		}else
		{
			System.out.println("Attention ce calendrier mural existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerAlbum(boolean isInModifMode) {
		int reponse = -1;
		String path = null;
		ArrayList<Couple<Photo>> photos = null;
		AlbumPhoto albumToMod = null;
		int idPhotoCouv = 0;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/***************** Modification d'un Album *****************/\n\n");
				ArrayList<AlbumPhoto> albumClient = new ArrayList<AlbumPhoto>();
				/*Requête de tous les albums du client*/
				System.out.println("Veuillez choisir quel album vous souhaitez modifier :\n");
				AlbumPhotoPathToString(albumClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=albumClient.size()){
					albumToMod = albumClient.get(reponse-1);
					path = albumToMod.getPathImpression();
					idPhotoCouv = albumToMod.getIdPhoto();
					photos = (ArrayList<Couple<Photo>>) albumToMod.getPhotos();
					flagUp = true;
				}
			}
		}else {
			System.out.println("/******************* Création d'un album *******************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'album :\n");
			idPhotoCouv = LectureClavier.lireEntier("Veuillez renseigner l'ID de la photo que vous souhaitez mettre en couverture :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Album : "+path+"\n"
					+ "Photos :	Couverture - "+idPhotoCouv);
			ImpressionPhotosToString(photos, false, true, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"4 - Changer de photo de couverture\n"
								+ 	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 4 : idPhotoCouv = LectureClavier.lireEntier("Veuillez renseigner l'ID de la photo que vous souhaitez mettre en couverture :\n"); break;
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'album :\n"); break;
					case 2 : 	int length = photos.size();
								photos = AjouterPhotoAPage(0, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"6 - Enregistrer l'album\n"
								+ 	"5 - Changer de photo de couverture\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 6 : if(!isInModifMode) {
								if(enregistrerAlbum(path, photos, idPhotoCouv)) return true; else break;
							}else {
								albumToMod.setPathImpression(path);
								albumToMod.setPhotos(photos);
								CRUDInterface<AlbumPhoto> albumInterface = _GlobalControler.getAlbumPhotoControler();
								albumInterface.update(albumToMod);
							}break;
					case 5 : idPhotoCouv = LectureClavier.lireEntier("Veuillez renseigner l'ID de la photo que vous souhaitez mettre en couverture :\n"); break;
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'album :\n"); break;
					case 3 : photos = RetirerPhoto(photos, false, true, true); break;
					case 2 :	int length = photos.size();
								photos = AjouterPhotoAPage(0, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Dans quelle page souhaitez-vous mettre cette photo?");
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}
	
	private void AlbumPhotoPathToString(ArrayList<AlbumPhoto> albumClient, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<albumClient.size();i++) {
				System.out.println(albumClient.size()-i+" - "+albumClient.get(albumClient.size()-1-i).getNumImpression()+" - "+albumClient.get(albumClient.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<albumClient.size();i++) {
				System.out.println(albumClient.get(albumClient.size()-1-i).getNumImpression()+" - "+albumClient.get(albumClient.size()-1-i).getPathImpression());
			}
			return;
		}	
	}

	private boolean enregistrerAlbum(String path, ArrayList<Couple<Photo>> photos, int idCouv) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		String titreCouv = "";
		CRUDInterface<AlbumPhoto> albumControler = _GlobalControler.getAlbumPhotoControler();
		boolean flag = false;
		titreCouv = LectureClavier.lireChaine("Quel titre souhaitez-vous mettre sur la couverture de votre album?\n");
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre album?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre album?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		AlbumPhoto toCreate = new AlbumPhoto(0, path, clientActuel, false, idCouv, titreCouv, qualite, format);
		if(albumControler.create(toCreate)) {
			System.out.println("album enregistré. \n");
			return true;
		}else
		{
			System.out.println("Attention cet album existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerTirage(boolean isInModifMode) {
		int reponse = -1;
		String path = "";
		ArrayList<Couple<Photo>> photos = null;
		Tirage tirageToMod = null;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/***************** Modification d'un Tirage ****************/\n\n");
				ArrayList<Tirage> tirageClient = new ArrayList<Tirage>();
				/*Requête de tous les albums du client*/
					//EN ATTENDANT
					Couple<Photo> unePhoto = new Couple<Photo>(new Photo(54785, "", ""), 10);
					Couple<Photo> deuxPhoto = new Couple<Photo>(new Photo(12548, "", ""), 5);
					Couple<Photo> troisPhoto = new Couple<Photo>(new Photo(35694, "", ""), 2);
					Tirage untirage = new Tirage(5064, "un chemin", false, "SUPERIEUR", "A4");
					untirage.setClient(this.clientActuel);
					Tirage deuxtirage = new Tirage(1598, "un autre chemin", false, "SUPERIEUR", "A4");
					deuxtirage.setClient(this.clientActuel);
					ArrayList<Couple<Photo>> photoTirage1 = new ArrayList<Couple<Photo>>();
					ArrayList<Couple<Photo>> photoTirage2 = new ArrayList<Couple<Photo>>();
					photoTirage1.add(unePhoto);
					photoTirage1.add(deuxPhoto);
					photoTirage1.add(troisPhoto);
					photoTirage2.add(unePhoto);
					photoTirage2.add(deuxPhoto);
					untirage.setPhotos(photoTirage1);
					deuxtirage.setPhotos(photoTirage2);
					tirageClient.add(untirage);
					tirageClient.add(deuxtirage);
				System.out.println("Veuillez choisir quel tirage vous souhaitez modifier :\n");
				tiragePathToString(tirageClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=tirageClient.size()){
					tirageToMod = tirageClient.get(reponse-1);
					path = tirageToMod.getPathImpression();
					photos = (ArrayList<Couple<Photo>>) tirageToMod.getPhotos();
					flagUp = true;
				}
			}
		}else {
			System.out.println("/****************** Création d'un tirage *******************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin du tirage :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Tirage : "+path+"\n"
					+ "Photos :");
			ImpressionPhotosToString(photos, true, false, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du tirage :\n"); break;
					case 2 : photos = AjouterPhotoAPage(0, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Combien d'exemplaires souhaitez-vous ajouter?"); break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"5 - Enregistrer le tirage\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 5 : if(!isInModifMode) {
								if(enregistrerTirage(path, photos)) return true; else break;
							}else {
								tirageToMod.setPathImpression(path);
								tirageToMod.setPhotos(photos);
								CRUDInterface<Tirage> tirageInterface = _GlobalControler.getTirageControler();
								tirageInterface.update(tirageToMod);
								System.out.println("Tirage modifié.\n");
								return true;
							}
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du tirage :\n"); break;
					case 3 : photos = RetirerPhoto(photos, true, false, true); break;
					case 2 : photos = AjouterPhotoAPage(0, photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"), "Combien d'exemplaires souhaitez-vous ajouter?"); break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private void tiragePathToString(ArrayList<Tirage> tirageClient, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<tirageClient.size();i++) {
				System.out.println(tirageClient.size()-i+" - "+tirageClient.get(tirageClient.size()-1-i).getNumImpression()+" - "+tirageClient.get(tirageClient.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<tirageClient.size();i++) {
				System.out.println(tirageClient.get(tirageClient.size()-1-i).getNumImpression()+" - "+tirageClient.get(tirageClient.size()-1-i).getPathImpression());
			}
			return;
		}
	}

	private boolean enregistrerTirage(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		CRUDInterface<Tirage> tirageControler = _GlobalControler.getTirageControler();
		boolean flag = false;
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre tirage?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre tirage?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		Tirage toCreate = new Tirage(0, path, false, qualite, format);
		toCreate.setClient(this.clientActuel);
		if(tirageControler.create(toCreate)) {
			System.out.println("tirage enregistré. \n");
			return true;
		}else
		{
			System.out.println("Attention ce tirage existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerCadre(boolean isInModifMode) {
		int reponse = -1;
		String path = "";
		ArrayList<Couple<Photo>> photos = null;
		Cadre cadreToMod = null;
		boolean flagUp = false;
		if(isInModifMode){
			while(!flagUp) {
				System.out.println("/***************** Modification d'un Cadre *****************/\n\n");
				ArrayList<Cadre> cadreClient = new ArrayList<Cadre>();
				/*Requête de tous les albums du client*/
				System.out.println("Veuillez choisir quel cadre vous souhaitez modifier :\n");
				cadrePathToString(cadreClient, true);
				System.out.println("0 - Retour\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse == 0) {
					return true;
				}else if(reponse>0 && reponse<=cadreClient.size()){
					cadreToMod = cadreClient.get(reponse-1);
					path = cadreToMod.getPathImpression();
					photos = (ArrayList<Couple<Photo>>) cadreToMod.getPhotos();
					flagUp = true;
				}
			}
		}else {
			System.out.println("/******************* Création d'un cadre *******************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n");
			photos = new ArrayList<Couple<Photo>>();
		}
		while(true) {
			System.out.println("Cadre : "+path+"\n"
					+ "Photos :");
			ImpressionPhotosToString(photos, false, false, false);
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"3 - Changer le chemin\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n"); break;
					case 2 : 	int length = photos.size();
								photos = AjouterPhoto(photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"));
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"5 - Enregistrer le cadre\n"
								+ 	"4 - Changer le chemin\n"
								+ 	"3 - Retirer une photo\n"
								+ 	"2 - Ajouter une photo\n"
								+ 	"1 - Retour au choix d'impression\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 5 : if(!isInModifMode) {
								if(enregistrerCadre(path, photos)) return true; else break;
							}else {
								cadreToMod.setPathImpression(path);
								cadreToMod.setPhotos(photos);
								CRUDInterface<Cadre> cadreInterface = _GlobalControler.getCadreControler();
								cadreInterface.update(cadreToMod);
							}break;
					case 4 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n"); break;
					case 3 : photos = RetirerPhoto(photos, false, false, true); break;
					case 2 :	int length = photos.size();
								photos = AjouterPhoto(photos, LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n"));
								if(photos.size()==length)
									System.out.println("Cette photo n'existe pas ou vous n'avez pas la permission de l'utiliser.\n");
								break;
					case 1 : return true;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private void cadrePathToString(ArrayList<Cadre> cadreClient, boolean prefix) {
		if(prefix)
		{
			for(int i=0; i<cadreClient.size();i++) {
				System.out.println(cadreClient.size()-i+" - "+cadreClient.get(cadreClient.size()-1-i).getNumImpression()+" - "+cadreClient.get(cadreClient.size()-1-i).getPathImpression());
			}
			return;
		}
		else 
		{
			for(int i=0; i<cadreClient.size();i++) {
				System.out.println(cadreClient.get(cadreClient.size()-1-i).getNumImpression()+" - "+cadreClient.get(cadreClient.size()-1-i).getPathImpression());
			}
			return;
		}
	}

	private boolean enregistrerCadre(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		String modele = "";
		CRUDInterface<Cadre> cadreControler = _GlobalControler.getCadreControler();
		boolean flag = false;
		while(!flag) {
			System.out.println("En quelle qualité souhaitez-vous créer votre cadre?\n"
					+ 	"2 - Moyenne\n"
					+ 	"1 - Supérieur\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : qualite = "MOYENNE"; flag = true; break;
				case 1 : qualite = "SUPERIEUR"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("En quel format souhaitez-vous créer votre cadre?\n"
					+ 	"2 - A4\n"
					+ 	"1 - A5\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : format = "A4"; flag = true; break;
				case 1 : format = "A5"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		flag = false;
		while(!flag) {
			System.out.println("Quel modèle souhaitez-vous pour votre cadre?\n"
					+ 	"2 - Grille\n"
					+ 	"1 - Cercle\n"
					+ 	"0 - Annuler l'enregistrement\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : modele = "Grille"; flag = true; break;
				case 1 : modele = "Cercle"; flag = true; break;
				case 0 : return false;
				default : General.erreurDeChoix(); break;
			}
		}
		Cadre toCreate = new Cadre(0, path, this.clientActuel, false, qualite, format);
		if(cadreControler.create(toCreate)) {
				System.out.println("tirage enregistré. \n");
				return true;
		}else
		{
			System.out.println("Attention ce cadre existe déjà, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private void ImpressionPhotosToString(ArrayList<Couple<Photo>> photos, boolean toConcat , boolean asPages, boolean prefix) {
		//Pas spécialement important de sort mais si j'ai le temps je ferais un meilleur affichage
		if(prefix&&toConcat)
		{
			for(int i=0; i<photos.size();i++) {
				System.out.println(photos.size()-i+" - "+photos.get(photos.size()-1-i).getGenerique().getIdPhoto()+" - nombre d'exemplaires : "+photos.get(photos.size()-1-i).getNumero());
			}
			return;
		}
		if(prefix)
		{
			for(int i=0; i<photos.size();i++) {
				System.out.println(photos.size()-i+" - "+photos.get(photos.size()-1-i).getGenerique().getIdPhoto());
			}
			return;
		}
		if(prefix&&asPages)
		{
			for(int i=0; i<photos.size();i++) {
				System.out.println(photos.size()-i+" - Page "+photos.get(photos.size()-1-i).getNumero()+" - "+photos.get(photos.size()-1-i).getGenerique().getIdPhoto());
				}
			return;
		}
		if(toConcat)
		{
			for(int i=0; i<photos.size();i++) {
				System.out.println(photos.get(photos.size()-1-i).getGenerique().getIdPhoto()+" - nombre d'exemplaires : "+photos.get(photos.size()-1-i).getNumero());
			}
			return;
		}
		if(asPages)
		{
			for(int i=0; i<photos.size();i++) {
				System.out.println("Page "+photos.get(photos.size()-1-i).getNumero()+" - "+photos.get(photos.size()-1-i).getGenerique().getIdPhoto());
			}
			return;
		}
		else 
		{
			for(int i=0; i<photos.size();i++) {
				System.out.println(photos.get(photos.size()-1-i).getGenerique().getIdPhoto());
			}
			return;
		}
	}
	
	private ArrayList<Couple<Photo>> RetirerPhoto(ArrayList<Couple<Photo>> photos, boolean toConcat, boolean asPages, boolean prefix) {
		int reponse = -1;
		while(true) {
			System.out.println(	"Quelle photo souhaitez-vous retirer?\n");
			ImpressionPhotosToString(photos, toConcat, asPages, prefix);
			System.out.println("0 - Retour\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			if(reponse == 0) {
				return photos;
			}else if(reponse>0 && reponse<=photos.size()){
				photos.remove(reponse-1);
				return photos;
			}
		}
	}
	
	private ArrayList<Couple<Photo>> AjouterPhotoAPage(int nbMaxPage, ArrayList<Couple<Photo>> photos, int id, String questionNumero) {
		int idphoto = id;
		/*Requête pour vérifier si la photo appartient au client ou qu'il peut utiliser une photo partagée*/
			//EN ATTENDANT
			boolean isUsableByClient = true;
		
		if(isUsableByClient) {

			int numero = -1;
			do {
				numero = LectureClavier.lireEntier(questionNumero);
			}while(numero<1 && nbMaxPage!= 0 || numero>nbMaxPage && nbMaxPage!= 0);
			/*Requête de récupération de la photo d'id idphoto*/
				//EN ATTENDANT
				Photo photo = new Photo(idphoto,"","");
			 	Couple<Photo> cPhoto = new Couple<Photo>(photo,numero);
			 	
			photos.add(cPhoto);
			return photos;
		}
		else {
			return photos;
		}
	}
	
	private ArrayList<Couple<Photo>> AjouterPhoto(ArrayList<Couple<Photo>> photos, int id) {
			int idphoto = id;
			/*Requête pour vérifier si la photo appartient au client*/
				//EN ATTENDANT
				boolean isUsableByClient = true;
			
			if(isUsableByClient) {
				/*Requête de récupération de la photo d'id idphoto*/
					//EN ATTENDANT
					Photo photo = new Photo(idphoto, "", "");
					Couple<Photo> cPhoto = new Couple<Photo>(photo,0);
				photos.add(cPhoto);
				return photos;
			}
			else {
				return photos;
			}	
	}

	private void visualiserInfosCompte() {
		/*Requête du nombre de commandes passées.*/
			//EN ATTENDANT
			int commandes = 10; 
		/*Requête du nombre de fichiers images utilisées*/
			//EN ATTENDANT
			int fi = 50; 
		/*Requête du nombre de fichier images partagées*/
			//EN ATTENDANT
			int fiPartages = 15; 
		System.out.println(	"/***************** Informations du compte *****************/\n\n"
						+ 	"Mail : "+this.clientActuel.getMailClient()+"\n"
						+	"Prenom : "+this.clientActuel.getPrenom()+"\n"
						+ 	"Nom : "+this.clientActuel.getNom()+"\n"
						+ 	"Nombre de commandes passées : "+commandes+"\n"
						+ 	"Nombre de fichiers images mises en ligne : "+fi+"\n"
						+ 	"Nombre de photos partagées : "+fiPartages+"\n\n");	
	}
	
	private void seConnecter() {
		System.out.println(	"/************************ Connexion ***********************/\n\n"
						+ 	"Veuillez saisir vos identifiants : \n");
		String email = LectureClavier.lireChaine("\nEmail : ");
		String mdp = LectureClavier.lireChaine("\nMot De Passe : ");

		ClientControler clientCtrler = _GlobalControler.getClientControler();
		try {
			this.clientActuel = clientCtrler.readClient(email);
		}catch(Exception e) {
			
		}
		if(this.clientActuel != null && this.clientActuel.getMotDePasse().equals(mdp)) {
			this.connecte = true;
			System.out.println("Vous êtes bien connecté\n");
		}else {
			this.clientActuel = null;
			System.out.println("Erreur de connexion, Client inconnu\n");
		}
			/*EN ATTENDANT*/
			//this.clientActuel = new BDD.Client(email, "Louis", "Reynaud", mdp);		
			//System.out.println("Vous êtes connecté.\n");
	}
	
	private void sInscrire() {
		System.out.println(	"/********************** Inscription **********************/\n\n"
						+ 	"Veuillez saisir vos informations : \n");
		String email = "";
		do {
		email = LectureClavier.lireChaine("\nEmail : ");
		}while(!email.matches("[^@]+@[^\\.]+\\..+"));
		String mdp = LectureClavier.lireChaine("\nMot De Passe : ");
		String nom = LectureClavier.lireChaine("\nNom : ");
		String prenom = LectureClavier.lireChaine("\nPrénom : ");
		/*Requête de vérification de non existence du client avec les informations renseignées*/
			/*EN ATTENDANT*/
			System.out.println("Mail envoyé à "+email+", merci de votre inscription.\n");
		/*Requête de création du nouveau client avec les informations renseignées.*/

	}
	
	private void seDeconnecter() {
		this.connecte = false;
		this.clientActuel = null;
		System.out.println("Se Déconnecter\n");
	}

}