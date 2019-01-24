package Controler;

import BDD.CRUDInterface;
import BDD.Mural;

public class MuralControler implements CRUDInterface<Mural>{
	private Mural mural;

	@Override
	public boolean create(Mural object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mural read(int identifiant) {
		// TODO Auto-generated method stub
		return mural;
	}

	@Override
	public boolean update(Mural object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}



}
