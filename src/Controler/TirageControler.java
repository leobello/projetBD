package Controler;

import BDD.CRUDInterface;
import BDD.Tirage;

public class TirageControler implements CRUDInterface<Tirage> {
	private Tirage tirage;

	@Override
	public boolean create(Tirage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tirage read(int identifiant) {
		// TODO Auto-generated method stub
		return tirage;
	}

	@Override
	public boolean update(Tirage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}



}
