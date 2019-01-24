package Controler;

import BDD.Bureau;
import BDD.CRUDInterface;

public class BureauControler implements CRUDInterface<Bureau>{
	private Bureau bureau;

	@Override
	public boolean create(Bureau object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bureau read(int identifiant) {
		// TODO Auto-generated method stub
		return bureau;
	}

	@Override
	public boolean update(Bureau object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	
}
