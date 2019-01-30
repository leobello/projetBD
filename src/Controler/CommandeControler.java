package Controler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Commande;
import serviceBD.BuildReq;

public class CommandeControler implements CRUDInterface<Commande>{

	private Commande commande;
	private static Statement stmt;

	public CommandeControler(Statement stmt) {
<<<<<<< HEAD
		CommandeControler.stmt = stmt;
=======
		this.stmt = stmt;
>>>>>>> parent of 0f5b4bb... changes
	}

	@Override
	public boolean create(Commande object) {
		BuildReq br = new BuildReq();
		ResultSet rs;
<<<<<<< HEAD
		String cp = (object.getCodePromo() == null) ? "NULL" : object.getCodePromo().getCode();
=======
>>>>>>> parent of 0f5b4bb... changes
		//String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10");
		String req = br.insert("COMMANDE",
				object.getDate().toString(),
				object.getModeLivraison(),
				object.getStatutCommande(),
				String.valueOf(object.getNumCommande()),
<<<<<<< HEAD
				cp,
=======
				object.getCodePromo().getCode(),
>>>>>>> parent of 0f5b4bb... changes
				object.getClient().getMailClient(),
				String.valueOf(object.getMontant()));
		try {
			stmt.executeQuery(req);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Commande read(int identifiant) {
		String req = "SELECT * FROM COMMANDE WHERE NUMCOMMANDE  = ";
		req += String.valueOf(identifiant);
		ResultSet rs;
		Date date;
		String modeLivraison, statut, codePromo, mail;
		Float montant;
		Integer numCommande;
		try {
			rs = stmt.executeQuery(req);
			if (rs.first()){
				date = rs.getDate("DATEC");
				modeLivraison = rs.getString("MODELIVRAISON");
				statut = rs.getString("STATUT_COMMANDE");
				numCommande = rs.getInt("NUMCOMMANDE");
				codePromo = rs.getString("CODEPROMO");
				mail = rs.getString("MAILCLIENT");
				montant = rs.getFloat("PRIXTOTAL");
				System.out.println(date.toString()
						+ " " + modeLivraison
						+ " " + statut
						+ " " + numCommande.toString()
						+ " " + codePromo
						+ " " + mail
						+ " " + montant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return commande;
	}

	@Override
	public boolean update(Commande object) {
		// TODO Auto-generated method stub
		String req = "UPDATE COMMANDE SET" +
					 " DATEC = " + object.getDate().toString() +
					 " MODELIVRAISON = " + object.getModeLivraison() +
					 " STATUT_COMMANDE = " + object.getStatutCommande() +
					 " CODEPROMO = " + object.getCodePromo() +
					 " MAILCLIENT = " + object.getClient().getMailClient() +
					 " PRIX TOTAL = " + object.getMontant() +
					 " WHERE NUMCOMMANDE = " + object.getNumCommande();
		System.out.println(req);
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
