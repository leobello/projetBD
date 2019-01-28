package serviceBD;

import java.sql.SQLException;
import java.sql.Statement;

public class BdConn {
    private static BdConn bd;
    private static Connexion connexion = new Connexion("bellole", "HNear1984");

    private BdConn() { }

    public static BdConn getInstance() {
        if(bd == null) {
            bd = new BdConn();
        }
        return bd;
    }

    public static Statement getSTMT() throws SQLException {
        return connexion.getConnection().createStatement();
    }
}
