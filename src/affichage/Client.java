package affichage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import BDD.*;
import Controler.*;
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
						+ 	"Bienvenue dans l'application d�di�e au client. \n");
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
								+ 	"11 - Visualiser les d�tails d'une commande\n"
								+ 	"10 - Cr�er une commande\n"
								+ 	"/*****     Gestion des fichiers images et photos     *****/\n"
								+	"9 - Visualiser le chemin des photos les plus utilis�es\n"
								+ 	"8 - Visualiser ma liste d'images partag�es\n"
								+ 	"7 - Supprimer un fichier image\n"
								+ 	"/************     Gestion des impressions     ************/\n"
								+	"6 - Visualiser mes impressions\n"
								+	"5 - Supprimer une impression\n"
								+ 	"4 - Modifier une impression\n"
								+ 	"3 - Cr�er une impression\n"
								+ 	"/*********************     Autre     *********************/\n"
								+ 	"2 - Visualiser les informations de mon compte\n"
								+ 	"1 - Se d�connecter\n"
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
		System.out.println("A bient�t");
	}
	
	private void supprimerImpression() {
		System.out.println("Supprimer une impression\n");
		
	}

	private void modifierImpression() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************** Cr�ation d'une impression ***************/\n\n"
					+ "Quelle est le type de l'impression que vous souhaitez modifier?"
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
		/*Requ�te des fichiers images appartenant au client*/
			//EN ATTENDANT
			FichierImage fi = new FichierImage("path/1", "", "", 0, General.getDateNow()); fichierImages.add(fi);fichierImages.add(fi);fichierImages.add(fi);
		while(!flagUp) {
			fiToString(fichierImages);
			reponse = LectureClavier.lireEntier("Choisissez le fichier � supprimer\n");
			if(reponse>1 && reponse<fichierImages.size()) {
				FichierImage fiToDelete = fichierImages.get(reponse-1);
				String choix = validerSuppression(fiToDelete);
				if(choix.equals("V")) {
					//_GlobalControler<FichierImage> FichierImageControler = _GlobalControler.getFichierControler();
					// FichierImageControler.delete(fiToDelete);
					System.out.println("Le fichier Image : "+fiToDelete.getPath()+" � �t� supprim�.");
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

	private String validerSuppression(FichierImage fiToDelete) {
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
		System.out.println("/****************** Cr�ation d'une commande *******************/\n\n");
		/*Requ�te des impressions appartenant aux clients.*/
			//EN ATTENDANT
			ArrayList<Impression> impressions = new ArrayList<Impression>();
			Impression imp1 = new Tirage(0, "imp1", this.clientActuel, false,"SUPERIEUR", "A4");
			Impression imp2 = new Tirage(1, "imp2", this.clientActuel, false,"MOYENNE", "A4");
			Impression imp3 = new Tirage(2, "imp3", this.clientActuel, false,"SUPERIEUR", "A5");
			impressions.add(imp1);impressions.add(imp2);impressions.add(imp3);
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
				impressionsToString(impressions, nbTaken, true);
				reponse = LectureClavier.lireEntier("\nChoix :");
				if(reponse>1 && reponse<impressions.size()+2) {
					do {
						nbTaken.set(reponse-2, LectureClavier.lireEntier("En combien d'exemplaire souhaitez-vous ce produit?\n"));
					}while(nbTaken.get(reponse-2)<0);
				}if(reponse == 0) {
					return;
				}if(reponse == 1) {
					flagUp = true;
				}
			}else {
				impressionsToString(impressions, nbTaken, false);
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
			System.out.println("Comment souhaitez-vous �tre livr�?\n"
					+ 	"2 - En d�pot relais\n"
					+ 	"1 - A domicile\n\n"
					+ 	"0 - Retour au menu principal\n");
			reponse = LectureClavier.lireEntier("\nChoix :");
			switch(reponse) 
			{
				case 2 : modelivraison = "D�pot Relais"; flagUp = true; break;
				case 1 : modelivraison = "A domicile"; flagUp = true; break;
				case 0 : return;
				default : General.erreurDeChoix(); break;
			}
		}
		Couple<ArrayList<Article>> articlesMontant = getMontantArticles(impressions,nbTaken);
		Commande cmd = new Commande(General.getDateNow(), modelivraison, "En Cours", 0, (float) articlesMontant.getNumero());
		cmd.setArticles(articlesMontant.getGenerique());
		do{
			System.out.println("Veuillez r�gler votre commande :\n"
					+ "1 - Valider\n"
					+ "0 - Annuler et retour au menu Principal\n");
			reponse = LectureClavier.lireEntier("Choix : \n");
			switch(reponse) {
				case 1 : break;
				case 0 : return;
			}
		}while(!stockSuffisant());
		//CRUDInterface<Commande> commandeControler = _GlobalControler.getCommandeControler();
		//commandeControler.create(cmd);
	}
	
	private boolean stockSuffisant() {
		
		return false;
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
				if(impressions.get(i).getQualite().matches("MOYENNE"))
					prixU = 1;
				else
					prixU = 2;
			}
			Impression imp = impressions.get(i);
			if(imp instanceof Tirage) {
				nbPages=0;
				List<Couple<Photo>> photos = ((Tirage) imp).getPhotos();
				for(int nb=0; nb<photos.size(); nb++) {
					nbPages+=photos.get(nb).getNumero();
				}
			}
			montant+= nbTaken.get(i)*prixU*nbPages;
			Article article = new Article(0, nbTaken.get(i)*prixU*nbPages, nbTaken.get(i));
			article.setImpression(impressions.get(i));
			//CRUDInterface<Article> articleControler = _GlobalControler.getArticleControler();
			//articles.add(article);
		}
		return new Couple<ArrayList<Article>>(articles, montant);
	}

	private void impressionsToString(ArrayList<Impression> impressions, ArrayList<Integer> nbTaken, boolean asExemplaires) {
		if(asExemplaires) {
			for(int i=0; i<impressions.size();i++) {
					System.out.println(impressions.size()+1-i+" - Impression n�"+impressions.get(impressions.size()-1-i).getNumImpression()+" - "+impressions.get(impressions.size()-1-i).getPathImpression()+"("+nbTaken.get(impressions.size()-1-i)+")");
			}System.out.println("1 - Valider la commande\n"
							+ 	"0 - Retour au menu principal\n");
		} else {
			for(int i=0; i<impressions.size();i++) {
				System.out.println(impressions.size()-i+" - Impression n�"+impressions.get(impressions.size()-1-i).getNumImpression()+" - "+impressions.get(impressions.size()-1-i).getPathImpression()+"("+nbTaken.get(impressions.size()-1-i)+")");
			}System.out.println("0 - Retour au menu principal\n");
		}
	}

	private void visualiserDetailsCommande() {
		System.out.println("Visualiser les d�tails de commande\n");
	}

	private void visualiserImpressions() {
		System.out.println("Visualiser les impressions\n");
	}

	private void visualiserListeImagesPartagees() {
		System.out.println("Visualiser la liste des images partag�es\n");
	}

	private void visualiserPhotosPopulaires() {
		System.out.println("Visualiser les photos populaires\n");
	}
	
	private void creerImpression() {
		int reponse = -1;
		while(true) {
			System.out.println(	"/**************** Cr�ation d'une impression ***************/\n\n"
					+ "Quel type d'impression voulez-vous cr�er?\n"
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
		// TODO Auto-generated method stub
		return false;
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
				/*Requ�te de tous les agenda jours du client*/
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
			System.out.println("/**************** Cr�ation d'un Agenda Jours ***************/\n\n");
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
								//CRUDInterface<Jour> jourInterface = _GlobalControler.getJourControler();
								//jourInterface.update(jourToMod);
							}
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
		/*Requ�te qui v�rifie qu'il n'existe pas d'album avec ce chemin.*/
			//EN ATTENDANT
			resultRequest = true;
		if(resultRequest) {
			boolean flag = false;
			while(!flag) {
				System.out.println("En quelle qualit� souhaitez-vous cr�er votre calendrier Mural?\n"
						+ 	"2 - Moyenne\n"
						+ 	"1 - Sup�rieur\n"
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
				System.out.println("En quel format souhaitez-vous cr�er votre calendrier Mural?\n"
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
			//CRUDInterface<Jour> joursControler = _GlobalControler.getJoursControler();
			//joursControler.create(toCreate);
			System.out.println("Agenda Jour enregistr�. \n");
			return true;
		}else
		{
			System.out.println("Attention ce Agenda Jour existe d�j�, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerCalendrierBureau(boolean isInModifMode) {
		// TODO Auto-generated method stub
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
				System.out.println("/************* Modification d'un Calendrier Mural ************/\n\n");
				ArrayList<Mural> muralClient = new ArrayList<Mural>();
				/*Requ�te de tous les agenda jours du client*/
				System.out.println("Veuillez choisir quel agenda jour vous souhaitez modifier :\n");
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
			System.out.println("/************** Cr�ation d'un calendrier Mural *************/\n\n");
			path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n");
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
								//CRUDInterface<Mural> jourInterface = _GlobalControler.getMuralControler();
								//jourInterface.update(muralToMod);
							}
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

	private void MuralPathToString(ArrayList<Mural> muralClient, boolean b) {
		// TODO Auto-generated method stub
		
	}

	private boolean enregistrerMural(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		/*Requ�te qui v�rifie qu'il n'existe pas d'album avec ce chemin.*/
			//EN ATTENDANT
			boolean resultRequest = true;
		if(resultRequest) {
			boolean flag = false;
			while(!flag) {
				System.out.println("En quelle qualit� souhaitez-vous cr�er votre calendrier Mural?\n"
						+ 	"2 - Moyenne\n"
						+ 	"1 - Sup�rieur\n"
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
				System.out.println("En quel format souhaitez-vous cr�er votre calendrier Mural?\n"
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
			//CRUDInterface<Mural> muralControler = _GlobalControler.getMuralControler();
			//muralControler.create(toCreate);
			System.out.println("calendrier mural enregistr�. \n");
			return true;
		}else
		{
			System.out.println("Attention ce calendrier mural existe d�j�, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerAlbum(boolean b) {
		int reponse = -1;
		System.out.println("/******************* Cr�ation d'un album *******************/\n\n");
		String path = LectureClavier.lireChaine("Veuillez renseigner le chemin de l'album :\n");
		int idPhotoCouv = LectureClavier.lireEntier("Veuillez renseigner l'ID de la photo que vous souhaitez mettre en couverture :\n");
		ArrayList<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();
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
					case 6 : if(enregistrerAlbum(path, photos, idPhotoCouv)) return true; else break;
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
	
	private boolean enregistrerAlbum(String path, ArrayList<Couple<Photo>> photos, int idCouv) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		String titreCouv = "";
		/*Requ�te qui v�rifie qu'il n'existe pas d'album avec ce chemin.*/
			//EN ATTENDANT
			boolean resultRequest = true;
		if(resultRequest) {
			boolean flag = false;
			titreCouv = LectureClavier.lireChaine("Quel titre souhaitez-vous mettre sur la couverture de votre album?\n");
			while(!flag) {
				System.out.println("En quelle qualit� souhaitez-vous cr�er votre album?\n"
						+ 	"2 - Moyenne\n"
						+ 	"1 - Sup�rieur\n"
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
				System.out.println("En quel format souhaitez-vous cr�er votre album?\n"
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
			/*Requ�te de cr�ation de l'album avec pour chemin path, pour photos l'array photos, 
			 * avec comme photo de couv idCouv et titre de couv titreCouv, 
			 * pour le format et la qualit� donn�e, et li�e au client connect�*/
			System.out.println("album enregistr�. \n");
			return true;
		}else
		{
			System.out.println("Attention cet album existe d�j�, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerTirage(boolean isInModifMode) {
		int reponse = -1;
		String path = "";
		ArrayList<Couple<Photo>> photos = null;
		if(!isInModifMode) {
			System.out.println("/****************** Cr�ation d'un tirage *******************/\n\n");
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
					case 5 : if(enregistrerTirage(path, photos)) return true; else break;
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

	private boolean enregistrerTirage(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		/*Requ�te qui v�rifie qu'il n'existe pas de tirage avec ce chemin.*/
			//EN ATTENDANT
			boolean resultRequest = true;
		if(resultRequest) {
			boolean flag = false;
			while(!flag) {
				System.out.println("En quelle qualit� souhaitez-vous cr�er votre tirage?\n"
						+ 	"2 - Moyenne\n"
						+ 	"1 - Sup�rieur\n"
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
				System.out.println("En quel format souhaitez-vous cr�er votre tirage?\n"
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
			Tirage toCreate = new Tirage(0, path, clientActuel, false, qualite, format);
			//CRUDInterface<Tirage> tirageControler = _GlobalControler.getTirageControler();
			//tirageControler.create(toCreate);
			System.out.println("tirage enregistr�. \n");
			return true;
		}else
		{
			System.out.println("Attention ce tirage existe d�j�, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private boolean creerCadre(boolean b) {
		int reponse = -1;
		System.out.println("/******************* Cr�ation d'un cadre *******************/\n\n");
		String path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n");
		ArrayList<Couple<Photo>> photos = new ArrayList<Couple<Photo>>();
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
					case 5 : if(enregistrerCadre(path, photos)) return true; else break;
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

	private boolean enregistrerCadre(String path, ArrayList<Couple<Photo>> photos) {
		int reponse = -1;
		String qualite = "";
		String format = "";
		String modele = "";
		/*Requ�te qui v�rifie qu'il n'existe pas de cadre avec ce chemin.*/
			//EN ATTENDANT
			boolean resultRequest = true;
		if(resultRequest) {
			boolean flag = false;
			while(!flag) {
				System.out.println("En quelle qualit� souhaitez-vous cr�er votre cadre?\n"
						+ 	"2 - Moyenne\n"
						+ 	"1 - Sup�rieur\n"
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
				System.out.println("En quel format souhaitez-vous cr�er votre cadre?\n"
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
				System.out.println("Quel mod�le souhaitez-vous pour votre cadre?\n"
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
			/*Requ�te de cr�ation de cadre avec pour chemin path, pour photos l'array photos,
			 * avec un mod�le modele, 
			 * pour le format et la qualit� donn�e, et li�e au client connect�*/
			System.out.println("Cadre enregistr�. \n");
			return true;
		}else
		{
			System.out.println("Attention ce cadre existe d�j�, veuillez modifier le chemin.\n");
		}
		return false;
	}

	private void ImpressionPhotosToString(ArrayList<Couple<Photo>> photos, boolean toConcat , boolean asPages, boolean prefix) {
		//Pas sp�cialement important de sort mais si j'ai le temps je ferais un meilleur affichage
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
				photos.remove(photos.size()-reponse);
				return photos;
			}
		}
	}
	
	private ArrayList<Couple<Photo>> AjouterPhotoAPage(int nbMaxPage, ArrayList<Couple<Photo>> photos, int id, String questionNumero) {
		int idphoto = id;
		/*Requ�te pour v�rifier si la photo appartient au client*/
			//EN ATTENDANT
			boolean isUsableByClient = true;
		
		if(isUsableByClient) {

			int numero = -1;
			do {
				numero = LectureClavier.lireEntier(questionNumero);
			}while(numero<1 && nbMaxPage!= 0 || numero>nbMaxPage && nbMaxPage!= 0);
			/*Requ�te de r�cup�ration de la photo d'id idphoto*/
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
			/*Requ�te pour v�rifier si la photo appartient au client*/
				//EN ATTENDANT
				boolean isUsableByClient = true;
			
			if(isUsableByClient) {
				/*Requ�te de r�cup�ration de la photo d'id idphoto*/
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
		/*Requ�te du nombre de commandes pass�es.*/
			//EN ATTENDANT
			int commandes = 10; 
		/*Requ�te du nombre de fichiers images utilis�es*/
			//EN ATTENDANT
			int fi = 50; 
		/*Requ�te du nombre de fichier images partag�es*/
			//EN ATTENDANT
			int fiPartages = 15; 
		System.out.println(	"/***************** Informations du compte *****************/\n\n"
						+ 	"Mail : "+this.clientActuel.getMailClient()+"\n"
						+	"Prenom : "+this.clientActuel.getPrenom()+"\n"
						+ 	"Nom : "+this.clientActuel.getNom()+"\n"
						+ 	"Nombre de commandes pass�es : "+commandes+"\n"
						+ 	"Nombre de fichiers images mises en ligne : "+fi+"\n"
						+ 	"Nombre de photos partag�es : "+fiPartages+"\n\n");	
	}
	
	private void seConnecter() {
		System.out.println(	"/************************ Connexion ***********************/\n\n"
						+ 	"Veuillez saisir vos identifiants : \n");
		String email = LectureClavier.lireChaine("\nEmail : ");
		String mdp = LectureClavier.lireChaine("\nMot De Passe : ");
		/*Requ�te de v�rification de l'existence du client*/
			/*EN ATTENDANT*/
			this.clientActuel = new BDD.Client(email, "Louis", "Reynaud", mdp);
		this.connecte = true;
		
		System.out.println("Vous �tes connect�.\n");
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
		String prenom = LectureClavier.lireChaine("\nPr�nom : ");
		/*Requ�te de v�rification de non existence du client avec les informations renseign�es*/
			/*EN ATTENDANT*/
			System.out.println("Mail envoy� � "+email+", merci de votre inscription.\n");
		/*Requ�te de cr�ation du nouveau client avec les informations renseign�es.*/
	}
	
	private void seDeconnecter() {
		this.connecte = false;
		this.clientActuel = null;
		System.out.println("Se D�connecter\n");
	}

}