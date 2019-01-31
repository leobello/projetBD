package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Jour;
import serviceBD.BD;

public class JoursControler implements CRUDInterface<Jour> {
	private Jour jour;
	private BD bd;
	// read commited
	public JoursControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Jour object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Jour read(int identifiant) {
		// TODO Auto-generated method stub
		return jour;
	}

	@Override
	public boolean update(Jour object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
