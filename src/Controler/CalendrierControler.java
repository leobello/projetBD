package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Calendrier;
import serviceBD.BD;

public class CalendrierControler implements CRUDInterface<Calendrier> {
	private Calendrier calendrier;
	private BD bd;
	// read commited
	public CalendrierControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Calendrier object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Calendrier read(int identifiant) {
		// TODO Auto-generated method stub
		return calendrier;
	}

	@Override
	public boolean update(Calendrier object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
