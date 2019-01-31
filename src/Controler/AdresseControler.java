package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.Adresse;
import BDD.CRUDInterface;
import serviceBD.BD;

public class AdresseControler implements CRUDInterface <Adresse>{
	// read commited
	private Adresse adresse ;
	private BD bd;

	public AdresseControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Adresse adresse) {
		boolean createOK = false;
		int misAj = 0;
		try {
			String requete = "INSERT INTO ADRESSE VALUES (ADRESSES_SEQ.NEXTVAL,"
					+adresse.getCodePostal()+",'"
					+adresse.getVille()+"','"
					+adresse.getNomAdresse()+"','"
					+adresse.getPrenomAdresse()+"','"
					+adresse.getRue()+"')";
			misAj = this.bd.getReadCommittedSTMT().executeUpdate(requete);
			
			if(misAj>0) {
				createOK = true;
			}
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createOK;
	}

	@Override
	public Adresse read(int identifiant) {
		try {
			ResultSet result = this.bd.getReadCommittedSTMT().executeQuery("SELECT * FROM ADRESSE WHERE ID_ADRESSE = "+ 
			identifiant);
			while(result.next()) {
				adresse = new Adresse(identifiant, 
						result.getInt("CODEPOSTAL"), 
						result.getString("VILLE"),
						result.getString("NOM_ADRESSE"),
						result.getString("PRENOM_ADRESSE"),
						result.getString("RUE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresse;
	}

	@Override
	public boolean update(Adresse adresse) {
		boolean updateTest = false;
		try {
			int idAd = adresse.getIdAdresse();
			int cp = adresse.getCodePostal();
			String ville = adresse.getVille();
			String nomAd = adresse.getNomAdresse();
			String prenAd = adresse.getPrenomAdresse();
			String rue = adresse.getRue();
			
			String requete = "Update ADRESSE set CODEPOSTAL = '"+ cp+"',"+ 
					"VILLE = '"+ville +
					"',NOM_ADRESSE = '"+ nomAd+
					"',PRENOM_ADRESSE = '"+prenAd+
					"',RUE = '"+rue+"' where ID_ADRESSE = '"+idAd+"'";                      
			int update = this.bd.getReadCommittedSTMT().executeUpdate(requete);
			if (update>0) {
				updateTest = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateTest;
	}

	@Override
	public boolean delete(int identifiant) {
		try {
			Statement stmt = this.bd.getReadCommittedSTMT();
			stmt.executeQuery("delete from ADRESSE where ID_ADRESSE = " + identifiant);
			System.out.println("C'est pass�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
