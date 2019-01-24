package Controler;

import BDD.CRUDInterface;
import BDD.Photo;

public class PhotoControler implements CRUDInterface<Photo> {
	private Photo photo ;

	@Override
	public boolean create(Photo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Photo read(int identifiant) {
		// TODO Auto-generated method stub
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
