package init;
import serviceBD.*;

public class Run {
	public static void main(String args[]) {
		System.out.println("heho");
		BD bd = new BD();
		bd.init();
	}
}
