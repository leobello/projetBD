package serviceBD;

import java.sql.Connection;
import java.sql.SQLException;

public class BD {
	private Connection connexion = null;
	
	// initialisation de la BD
	public void init() {
		Connexion connexion = new Connexion();
		//System.out.print("id oracle: ");
		//LectureClavier.lireChaine();
		//System.out.print("password oracle: ");
		//password = LectureClavier.lireChaine();
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
	public Connection getConnection() throws SQLException {
		return null;
	}
}
