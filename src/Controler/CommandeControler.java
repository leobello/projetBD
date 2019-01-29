package Controler;

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
		this.stmt = stmt;
	}

	@Override
	public boolean create(Commande object) {
		BuildReq br = new BuildReq();
		ResultSet rs;
		//String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10");
		String req = br.insert("COMMANDE",
				object.getDate().toString(),
				object.getModeLivraison(),
				object.getStatutCommande(),
				String.valueOf(object.getNumCommande()),
				object.getCodePromo().getCode(),
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
		// TODO Auto-generated method stub
		return commande;
	}

	@Override
	public boolean update(Commande object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	

}
