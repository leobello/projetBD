package BDD;

public class Couple<T> {
	private T generique;
	private int numero;
	
	public void setPhoto(T photo) {
		this.generique = photo;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public T getPhoto(){
		return generique;
	}
	
	public int getNumero(){
		return numero;
	}
	
}
