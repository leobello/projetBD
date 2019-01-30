package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.CRUDInterface;
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
<<<<<<< HEAD
		//String requete = "INSERT INTO TIRAGE VALUES ("+
=======
		try {
			String requete = "INSERT INTO TIRAGE VALUES ("+ tirage.getNumImpression()+")";               
			int insert = this.bd.getReadCommittedSTMT().executeUpdate(requete);
			if (insert>0) {
				checkCreate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
>>>>>>> 1e856eddeaee0388bbc239d231fcb0fc079d7f4f
		return false;
	}

	@Override
	public Tirage read(int identifiant) {
		String requete = "SELECT * FROM TIRAGE NATURAL JOIN IMPRESSION WHERE NUMIMPRESSION = "+ identifiant; ;
		ResultSet rs;
		try {
			rs = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while (rs.next()) {
			tirage = new Tirage (rs.getInt("NUMIMPRESSION"),
						rs.getString("PATH_IMPRESSION"),
						_GlobalControler.getClientControler().readClient(rs.getString("MAILCLIENT")),
						rs.getBoolean("IMPRESSION_OK"),
						rs.getString("QUALITE"),
						rs.getString("FORMAT"));
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
