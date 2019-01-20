package serviceBD;

import java.sql.Connection;

public class BD {
	private Connection connexion = null;
	
	// initialisation de la BD
	public void init() {
		String id;
		String password;;
		Connexion connexion;
		System.out.println("id oracle: ");
		id = LectureClavier.lireChaine();
		System.out.println("");
		System.out.println("password oracle: ");
		password = LectureClavier.lireChaine();
		connexion = new Connexion(id, password);
		this.connexion = connexion.getConnection();
		// script de création des tables à ajouter ici
	}
	// insertion des tuples dans la BD
	void insert() {
		
	}
	// drop de toutes les tables
	void erase() {
		
	}
	// recuper la bd
	Connection getConnection() {
		return this.connexion;
	}
}
