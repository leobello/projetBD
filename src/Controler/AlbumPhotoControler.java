package Controler;

import java.sql.Statement;

import BDD.AlbumPhoto;
import BDD.CRUDInterface;

public class AlbumPhotoControler implements CRUDInterface<AlbumPhoto> {

	private AlbumPhoto albumPhoto;
	private static Statement stmt;

	public AlbumPhotoControler(Statement stmt) {
		AlbumPhotoControler.stmt = stmt;
	}

	@Override
	public boolean create(AlbumPhoto object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AlbumPhoto read(int identifiant) {
		// TODO Auto-generated method stub
		return albumPhoto;
	}

	@Override
	public boolean update(AlbumPhoto object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	
}
