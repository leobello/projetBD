package Controler;

import BDD.CRUDInterface;
import BDD.Client;

public class ClientControler implements CRUDInterface<Client> {
	private Client client;

	@Override
	public boolean create(Client object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client read(int identifiant) {
		// TODO Auto-generated method stub
		return client;
	}

	@Override
	public boolean update(Client object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	
}
