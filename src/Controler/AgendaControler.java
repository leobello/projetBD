package Controler;

import BDD.Agenda;
import BDD.CRUDInterface;

public class AgendaControler implements CRUDInterface <Agenda> {
	private Agenda agenda;

	@Override
	public boolean create(Agenda object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Agenda read(int identifiant) {
		// TODO Auto-generated method stub
		return agenda;
	}

	@Override
	public boolean update(Agenda object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
