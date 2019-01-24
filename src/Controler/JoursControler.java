package Controler;

import BDD.CRUDInterface;
import BDD.Jours;

public class JoursControler implements CRUDInterface<Jours>{
	private Jours jour;

	@Override
	public boolean create(Jours object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Jours read(int identifiant) {
		// TODO Auto-generated method stub
		return jour;
	}

	@Override
	public boolean update(Jours object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	

}
