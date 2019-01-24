package Controler;

import BDD.Adresse;
import BDD.CRUDInterface;

public class AdresseControler implements CRUDInterface <Adresse>{
	private Adresse adresse;

	@Override
	public boolean create(Adresse object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adresse read(int identifiant) {
		// TODO Auto-generated method stub
		return adresse;
	}

	@Override
	public boolean update(Adresse object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
