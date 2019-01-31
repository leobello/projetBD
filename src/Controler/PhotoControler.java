package Controler;

import java.sql.ResultSet;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Couple;
import BDD.Photo;
import BDD.Tirage;
import serviceBD.BD;

public class PhotoControler implements CRUDInterface<Photo> {
	private Photo photo;
	private BD bd;
	// seriazable
	public PhotoControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Photo object) {
		boolean createOK = false;
		try {
			String requete = "INSERT INTO PHOTO VALUES (PHOTOS_SEQ.NEXTVAL," 
					+ photo.getFichierImage().getPath()
					+ "," + photo.getFichierImage().getProprietaire().getMailClient() + "," 
					+ photo.getRetouche() + ","
					+ photo.getDescription() + ")";

			ResultSet rs = this.bd.getSerializableSTMT().executeQuery(requete);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return createOK;
	}

	@Override
	public Photo read(int identifiant) {
		try {
			String requete = "SELECT * FROM PHOTO where ID_PHOTO = '" + identifiant + "'";
			
			ResultSet result = this.bd.getSerializableSTMT().executeQuery(requete);
			if (result.next()) {
				photo = new Photo(result.getInt("ID_PHOTO"), result.getString("RETOUCHE"),
						result.getString("DESCRIPTION"));

				String requete2 = "SELECT * FROM PHOTO "
						+ "NATURAL JOIN PHOTOTIRAGE "
						+ "NATURAL JOIN TIRAGE "
						+ "NATURAL JOIN IMPRESSION "
						+ "where ID_PHOTO = '" + identifiant + "'";
				ResultSet result2 = this.bd.getSerializableSTMT().executeQuery(requete2);
				while (result2.next()) {
					photo.ajouterDansTirages(new Couple<Tirage>(new Tirage(result2.getInt("NUMIMPRESSION"),
							result2.getString("PATH_IMPRESSION"), result2.getBoolean("IMPRESSION_OK"),
							result2.getString("QUALITE"), result2.getString("FORMAT")), result2.getInt("NBEXEMPLAIRE")));
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
