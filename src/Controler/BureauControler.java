package Controler;

import java.sql.Statement;

import BDD.Bureau;
import BDD.CRUDInterface;
import serviceBD.BD;

public class BureauControler implements CRUDInterface<Bureau> {
	private Bureau bureau;
	private BD bd;

	public BureauControler(BD bd) {
		this.bd = bd;
	}

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
