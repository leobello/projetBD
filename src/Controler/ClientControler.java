package Controler;

<<<<<<< HEAD
=======
import java.sql.Statement;

>>>>>>> parent of 0f5b4bb... changes
import BDD.CRUDInterface;
import BDD.Client;
import serviceBD.BD;

public class ClientControler implements CRUDInterface<Client> {
	private Client client;
	private BD bd;
	
	
	public ClientControler(BD bd) {
		this.bd = bd;
	}


	@Override
	public boolean create(Client client) {
		boolean createOK = false;
		try {
			String requete = "INSERT INTO ADRESSE VALUES ("+ 
					client.getMailClient()+","+
					client.getNom()+","+
					client.getPrenom()+";"+
					client.getMotDePasse()+")";                                  
			
			createOK = this.bd.getReadCommittedSTMT().execute(requete);
			 
					} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createOK;
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
