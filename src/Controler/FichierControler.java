package Controler;

import java.sql.ResultSet;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Client;
import BDD.Couple;
import BDD.FichierImage;
import BDD.Photo;
import BDD.Tirage;
import serviceBD.BD;

public class FichierControler implements CRUDInterface<FichierImage> {
	private FichierImage fichier;
	private BD bd;

	public FichierControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(FichierImage object) {
		boolean createOK = false;
		try {
			String requete = "INSERT INTO FICHIERIMAGE VALUES (" + object.getPath() + ","
					+ object.getProprietaire().getMailClient() + "," + object.getInfoPriseDeVue() + ","
					+ object.getResoluton() + "," + object.getInfoPriseDeVue() + "," + object.getInfoPriseDeVue() + ")";

			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return createOK;
	}

	@Override
	public FichierImage read(int identifiant) {

		return fichier;
	}

	public FichierImage readFichier(String path, String proprietaire) {
		try {
			String requete = "SELECT * FROM FICHIERIMAGE " + 
							"NATURAL JOIN CLIENT " + 
							"where PATH = '" + path
							+ "' AND PROPRIETAIRE = '" + proprietaire + "'";
			
			ResultSet result = this.bd.getReadCommittedSTMT().executeQuery(requete);
			
			if (result.next()) {
				fichier = new FichierImage(result.getString("PATH"), result.getString("INFOPRISEDEVUE"),
						result.getString("RESOLUTION"), result.getInt("PARTAGE"), result.getDate("DATEACCES"));

				while (result.next())
					fichier.setProprietaire(new Client(result.getString("MAILCLIENT"), result.getString("NOM"),
							result.getString("PRENOM"), result.getString("MOTDEPASSE")));
				String requete2 = "SELECT * FROM FICHIERIMAGE NATURAL JOIN PHOTO where PATH='" + path
						+ "' AND PROPRIETAIRE ='" + proprietaire + "'";
				ResultSet result2 = this.bd.getReadCommittedSTMT().executeQuery(requete2);
				while (result2.next()) {
					fichier.ajouterPhoto(new Photo(result.getInt("ID_PHOTO"), result.getString("RETOUCHE"),
							result.getString("DESCRIPTION")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fichier;
	}

	@Override
	public boolean update(FichierImage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
