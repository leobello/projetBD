package serviceBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	private Connection connexion = null;
	private final String id = "bellole";
	private final String password = "HNear1984";
	
	// initialisation de la BD
	public void init() throws SQLException {
		ResultSet resultat = null;
		// connexion
		/*
		System.out.print("id oracle: ");
		id = LectureClavier.lireChaine();
		System.out.print("password oracle: ");
		password = LectureClavier.lireChaine();
		*/
		Connexion connexion = new Connexion(this.id, this.password);
		this.connexion = connexion.getConnection();
		/*
		// création des tables à ajouter ici
		Statement stmt = this.connexion.createStatement();
		System.out.println("Creation des tables... ");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String query = "start /Users/leobello/eclipse-workspace/projetBD/src/sql/creation.sql";
		String query2 = "start ./projetBD/src/sql/creation.sql";
		String query3 = "start projetBD/src/sql/creation.sql";
		stmt.execute(query2);
		System.out.println("tables crées");
		*/

	}
	// recuper la bd
	public Connection getConnection() throws SQLException {
		return this.connexion;
	}
}
