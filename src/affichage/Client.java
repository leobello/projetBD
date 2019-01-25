package affichage;

import java.util.ArrayList;

import BDD.Photo;
import serviceBD.LectureClavier;

public class Client extends TypeUtilisateur {
	
	private BDD.Client clientActuel;
	
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

	private void visualiserImpressions() {
		System.out.println("Visualiser les impressions\n");
	}

	private void visualiserListeImagesPartagees() {
		System.out.println("Visualiser la liste des images partagées\n");
	}

	private void visualiserPhotosPopulaires() {
		System.out.println("Visualiser les photos populaires\n");
	}
	
	private void creerImpression() {
		int reponse = -1;
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
			case 7 : if(creerCadre()) break; else return;
			case 6 : if(creerTirage()) break; else return;
			case 5 : if(creerAlbum()) break; else return;
			case 4 : if(creerCalendrierMuraux()) break; else return;
			case 3 : if(creerCalendrierBureau()) break; else return;
			case 2 : if(creerAgendaJours()) break; else return;
			case 1 : if(creerAgendaSemaines()) break; else return;
			case 0 : return;
			default : General.erreurDeChoix(); break;
		}
	}
	
	private boolean creerAgendaSemaines() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean creerAgendaJours() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean creerCalendrierBureau() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean creerCalendrierMuraux() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean creerAlbum() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean creerTirage() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean creerCadre() {
		int reponse = -1;
		System.out.println("/******************* Création d'un cadre *************/\n\n");
		String path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n");
		ArrayList<Photo> photos = new ArrayList<Photo>();
			/*test*/
			Photo photo = new Photo(); photo.setIdPhoto(5); photos.add(photo);
			Photo photo2 = new Photo(); photo2.setIdPhoto(4); photos.add(photo2);
			Photo photo3 = new Photo(); photo3.setIdPhoto(3); photos.add(photo3);
			Photo photo4 = new Photo(); photo4.setIdPhoto(2); photos.add(photo4);
			Photo photo5 = new Photo(); photo5.setIdPhoto(1); photos.add(photo5);
		while(true) {
			System.out.println("Cadre : "+path+"\n"
					+ "Photos :");
			for(int i=0; i<photos.size();i++) {System.out.println(photos.get(i).getIdPhoto());}
			System.out.println("\nQue souhaitez-vous faire?\n");
			if(photos.size()==0) {
				System.out.println(	"2 - Changer le nom\n"
								+ 	"1 - Ajouter une photo\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 2 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n"); break;
					case 1 : photos = AjouterPhoto(photos); break;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
			else {
				System.out.println(	"3 - Changer le nom\n"
								+ 	"2 - Retirer une photo\n"
								+ 	"1 - Ajouter une photo\n"
								+ 	"0 - Retour au menu principal\n");
				reponse = LectureClavier.lireEntier("\nChoix :");
				switch(reponse) 
				{
					case 3 : path = LectureClavier.lireChaine("Veuillez renseigner le chemin du cadre :\n"); break;
					case 2 : photos = RetirerPhoto(photos); break;
					case 1 : photos = AjouterPhoto(photos); break;
					case 0 : return false;
					default : General.erreurDeChoix(); break;
				}
			}
		}
	}

	private ArrayList<Photo> RetirerPhoto(ArrayList<Photo> photos) {
		int reponse = -1;
		while(true) {
			System.out.println(	"Quelle photo souhaitez-vous retirer?\n");
			for(int i=0; i<photos.size();i++) {
				System.out.println(photos.size()-i+" - "+photos.get(i).getIdPhoto());
			}
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

	private ArrayList<Photo> AjouterPhoto(ArrayList<Photo> photos) {
			int idphoto = LectureClavier.lireEntier("Entrez l'ID de la photo que vous souhaitez ajouter?\n");
			/*Requête pour vérifier si la photo appartient au client*/
				//EN ATTENDANT
				Photo photo = new Photo(); photo.setIdPhoto(idphoto);
			photos.add(photo);
			return photos;
	}

	private void visualiserInfosCompte() {
		/*Requête du nombre de commandes passées.*/
			int commandes = 10; 
		/*Requête du nombre de fichiers images utilisées*/
			int fi = 50; 
		/*Requête du nombre de fichier images partagées*/
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
		/*Requête de vérification de l'existence du client*/
			/*EN ATTENDANT*/
			this.clientActuel = new BDD.Client();
			this.clientActuel.setMailClient("louisreynaud26@gmail.com");
			this.clientActuel.setPrenom("Louis");
			this.clientActuel.setNom("Reynaud");
		this.connecte = true;
		
		System.out.println("Vous êtes connecté.\n");
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
