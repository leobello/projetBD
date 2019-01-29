package BDD;

import java.util.HashSet;
import java.util.Set;

public class CodePromo {
	private String code;
	private int idCodePromo;
	private boolean dejaUtiliser;
	private Set<Client> clients = new HashSet<Client>();
	
	
	public CodePromo(String code, int idCodePromo, boolean dejaUtiliser) {
		this.code = code;
		this.idCodePromo = idCodePromo;
		this.dejaUtiliser = dejaUtiliser;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getIdCodePromo() {
		return idCodePromo;
	}
	public void setIdCodePromo(int idCodePromo) {
		this.idCodePromo = idCodePromo;
	}
	public boolean isDejaUtiliser() {
		return dejaUtiliser;
	}
	public void setDejaUtiliser(boolean dejaUtiliser) {
		this.dejaUtiliser = dejaUtiliser;
	}
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	public void ajouterDansClients(Client client){
		this.clients.add(client);
	}
	public void supprimerDansClients(Client client){
		this.clients.remove(client);
	}
}
