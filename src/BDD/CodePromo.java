package BDD;

import java.util.ArrayList;
import java.util.List;

public class CodePromo {
	private String code;
	private int pourcentage;
	private String typeCodePromo;
	private boolean dejaUtiliser;
	private List<Client> clients = new ArrayList<Client>();

	public CodePromo(String code, int pourcentage, boolean dejaUtiliser, String typeCodePromo) {
		this.code = code;
		this.pourcentage = pourcentage;
		this.dejaUtiliser = dejaUtiliser;
		this.typeCodePromo = typeCodePromo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDejaUtiliser() {
		return dejaUtiliser;
	}

	public void setDejaUtiliser(boolean dejaUtiliser) {
		this.dejaUtiliser = dejaUtiliser;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getTypeCodePromo() {
		return typeCodePromo;
	}

	public void setTypeCodePromo(String typeCodePromo) {
		this.typeCodePromo = typeCodePromo;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public void ajouterDansClients(Client client) {
		this.clients.add(client);
	}

	public void supprimerDansClients(Client client) {
		this.clients.remove(client);
	}


	public Client getClient(int i) {
		return clients.get(i);
	}
}
