package BDD;

import java.util.List;

public class CodePromo {
	private String code;
	private int idCodePromo;
	private boolean dejaUtiliser;
	private List<Client> clients;
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
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
