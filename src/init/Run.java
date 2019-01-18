package init;
import connexion.*;

public class Run {
	public static void main(String args[]) {
		Connexion conn = new Connexion();
		conn.connect();
	}
}
