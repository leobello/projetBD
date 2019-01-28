package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Mural;

public class MuralControler implements CRUDInterface<Mural>{
	private Mural mural;
	private static Statement stmt;

	public MuralControler(Statement stmt) {
		this.stmt = stmt;
	}

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
