package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Semaine;
import serviceBD.BD;

public class SemaineControler implements CRUDInterface<Semaine> {

	private Semaine semaine;
	private BD bd;
	// read commited
	public SemaineControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Semaine object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Semaine read(int identifiant) {
		// TODO Auto-generated method stub
		return semaine;
	}

	@Override
	public boolean update(Semaine object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
