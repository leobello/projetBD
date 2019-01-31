package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Cadre;
import serviceBD.BD;

public class CadreControler implements CRUDInterface<Cadre>{
	private Cadre cadre;
	private BD bd;
	// read commited
	public CadreControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Cadre object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cadre read(int identifiant) {
		// TODO Auto-generated method stub
		return cadre;
	}

	@Override
	public boolean update(Cadre object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
