package Controler;

import BDD.CRUDInterface;
import BDD.FichierImage;


public class FichierControler implements CRUDInterface<FichierImage> {
	private FichierImage fichier;

	@Override
	public boolean create(FichierImage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FichierImage read(int identifiant) {
		// TODO Auto-generated method stub
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
