package BDD;

/**
 * Cette classe sert a g�rer les associations entre Photo et les diff�rens types
 * d'impression Elle contient un type g�n�rique pour le type avec lequel est
 * fait l'impression et un int qui correspond � l'attribut supl�mentaire de
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
