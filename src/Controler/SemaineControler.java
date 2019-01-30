package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Semaine;

public class SemaineControler implements CRUDInterface<Semaine>{
	
	private Semaine semaine;
	private static Statement stmt;

	public SemaineControler(Statement stmt) {
		SemaineControler.stmt = stmt;
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
