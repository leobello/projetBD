package affichage;

public abstract class TypeUtilisateur {
	
	protected boolean connecte;
	
	public TypeUtilisateur() {
		this.connecte = false;
	}
	
	public abstract void run();
}
