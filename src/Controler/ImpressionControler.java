package Controler;

import java.sql.ResultSet;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Client;
import BDD.Impression;
import serviceBD.BD;

public class ImpressionControler implements CRUDInterface<Impression> {
	private Impression impression;
	private static BD bd;

	public ImpressionControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Impression impression) {
		boolean checkCreate = false;
		try {
			int i = 0;
			if(impression.isImpression_ok()) {
				 i = 1;
			}
			String requete = "INSERT INTO IMPRESSION VALUES (IMPRESSIONS_SEQ.NEXTVAL"+
					impression.getNumImpression()
					+"'"+impression.getClient().getMailClient()+"','"
					+impression.getPathImpression()+"',"
					+impression.getArticle(i)+",'"
					+impression.getQualite()+"','"
					+impression.getFormat()+"'";
			int insert = this.bd.getReadCommittedSTMT().executeUpdate(requete);
			
			if (insert>0) {
				checkCreate = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkCreate;
	}

	@Override
	public Impression read(int identifiant) {
		try {
			String requete = "SELECT * FROM IMPRESSION "
							+ "NATURAL JOIN CLIENT "
							+ "WHERE NUMIMPRESSION = "+ identifiant;               
			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while(rs.next()) {
				impression = new Impression(rs.getInt("NUMIPRESSION"),
								rs.getString("PATH_IMPRESSION"),
								rs.getBoolean("IMPRESSION_OK"),
								rs.getString("QUALITE"),
								rs.getString("FORMAT"));
				impression.setClient(new Client(rs.getString("MAILCLIENT"), rs.getString("NOM"), rs.getString("PRENOM"),
						rs.getString("MOTDEPASSE")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return impression;
	}

	
	//
	@Override
	public boolean update(Impression object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	

}
