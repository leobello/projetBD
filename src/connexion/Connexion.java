package connexion;
import java.sql.*;
public class Connexion {
	
	static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
	
	static final String USER = "bellole";
	static final String PASSWD = "HNear1984";

	static Connection conn;
		
	public void connect() {
		try {
			        
	  	    // Enregistrement du driver Oracle
	  	    System.out.print("Loading Oracle driver... "); 
	  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  	    
	  	    System.out.println("loaded");
	  	    
	  	    // Etablissement de la connection
	  	    System.out.print("Connecting to the database... "); 
	 	    conn = DriverManager.getConnection(CONN_URL,USER,PASSWD);
	   	    System.out.println("connected");
	  	    
	   	    /*
	  	    // Desactivation de l'autocommit
		  	conn.setAutoCommit(true);
	  	    System.out.println("Autocommit disabled");
	  	    
	  	    
	  	    LectureClavier lectureClavier = new LectureClavier();
	  	    Statement req = conn.createStatement();
	  	    */
			
		} catch (SQLException e) {
	        System.err.println("failed");
	        System.out.println("Affichage de la pile d'erreur");
	        e.printStackTrace(System.err);
	        System.out.println("Affichage du message d'erreur");
	        System.out.println(e.getMessage());
	        System.out.println("Affichage du code d'erreur");
	        System.out.println(e.getErrorCode());	    
	
	    }
	}

}
