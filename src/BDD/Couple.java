package BDD;

/**
 * Cette classe sert a gérer les associations entre Photo et les différens types
 * d'impression Elle contient un type générique pour le type avec lequel est
 * fait l'impression et un int qui correspond à l'attribut suplémentaire de
 * l'association
 */

public class Couple<T> {
	private T generique;
	private int numero;

	public Couple(T generique, int numero) {
		this.generique = generique;
		this.numero = numero;
	}

	public void setGenerique(T photo) {
		this.generique = photo;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public T getGenerique() {
		return generique;
	}

	public int getNumero() {
		return numero;
	}

}
