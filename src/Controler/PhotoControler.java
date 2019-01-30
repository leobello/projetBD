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
	private Photo photo ;
	private BD bd;
	private static Statement stmt;

	public PhotoControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Photo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Photo read(int identifiant) {
		try {
		String requete = "SELECT * FROM PHOTO NATURAL JOIN PHOTOTIRAGE NATURAL JOIN TIRAGE NATURAL JOIN IMPRESSION "
				+ "where IDPHOTO = '"+identifiant+"'";
		ResultSet result = this.bd.getReadCommittedSTMT().executeQuery(requete);
		while (result.next()) {
			photo = new Photo (result.getInt("ID_PHOTO"),
					result.getString("RETOUCHE"), 
					result.getString("DESCRIPTION"));
		}
	      if(result.first()){
			photo = new Photo (result.getInt("ID_PHOTO"),
						result.getString("RETOUCHE"), 
						result.getString("DESCRIPTION"));
	          CRUDInterface<Tirage> tirContr = _GlobalControler.getTirageControler();
	              
	          while(result.next())
	            photo.ajouterDansTirages(new Couple<Tirage>(tirContr.read(result.getInt("NUMIMPRESSION")), result.getInt("NBEXEMPLAIRE")));
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
