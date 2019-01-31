package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Client;
import BDD.Tirage;
import serviceBD.BD;

public class TirageControler implements CRUDInterface<Tirage> {
	private Tirage tirage;
	private BD bd;
	
	public  TirageControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Tirage tirage) {
		boolean checkCreate = false;
		try {
			String requete = "INSERT INTO TIRAGE VALUES ("+ tirage.getNumImpression()+")";               
			int insert = this.bd.getReadCommittedSTMT().executeUpdate(requete);
			if (insert>0) {
				checkCreate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Tirage read(int identifiant) {
		String requete = "SELECT * FROM TIRAGE "
						+ "NATURAL JOIN IMPRESSION "
						+ "NATURAL JOIN CLIENT "
						+ "WHERE NUMIMPRESSION = "+ identifiant; ;
		ResultSet rs;
		try {
			rs = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while (rs.next()) {
			tirage = new Tirage (rs.getInt("NUMIMPRESSION"),
						rs.getString("PATH_IMPRESSION"),
						rs.getBoolean("IMPRESSION_OK"),
						rs.getString("QUALITE"),
						rs.getString("FORMAT"));
			tirage.setClient(new Client(rs.getString("MAILCLIENT"), rs.getString("NOM"), rs.getString("PRENOM"),
					rs.getString("MOTDEPASSE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tirage;
	}

	@Override
	public boolean update(Tirage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}



}
