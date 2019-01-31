package Controler;

import java.sql.ResultSet;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Couple;
import BDD.Photo;
import BDD.Stock;
import BDD.Tirage;
import serviceBD.BD;

public class PhotoControler implements CRUDInterface<Photo> {
	private Photo photo;
	private BD bd;

	public PhotoControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Photo object) {
		boolean createOK = false;
		try {
			String requete = "INSERT INTO ADRESSE VALUES ( ADRESSES_SEQ.NEXTVAL," + photo.getFichierImage().getPath()
					+ "," + photo.getFichierImage().getProprietaire().getMailClient() + "," + photo.getRetouche() + ","
					+ photo.getDescription() + ")";

			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return createOK;
	}

	@Override
	public Photo read(int identifiant) {
		try {
			String requete = "SELECT * FROM PHOTO NATURAL JOIN PHOTOTIRAGE NATURAL JOIN TIRAGE NATURAL JOIN IMPRESSION "
					+ "where IDPHOTO = '" + identifiant + "'";
			ResultSet result = this.bd.getReadCommittedSTMT().executeQuery(requete);
			if (result.first()) {
				photo = new Photo(result.getInt("ID_PHOTO"), result.getString("RETOUCHE"),
						result.getString("DESCRIPTION"));

				while (result.next()) {
					photo.ajouterDansTirages(new Couple<Tirage>(new Tirage(result.getInt("NUMIMPRESSION"),
							result.getString("PATH_IMPRESSION"), result.getBoolean("IMPRESSION_OK"),
							result.getString("QUALITE"), result.getString("FORMAT")), result.getInt("NBEXEMPLAIRE")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photo;
	}

	@Override
	public boolean update(Photo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
