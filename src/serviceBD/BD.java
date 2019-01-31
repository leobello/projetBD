package serviceBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Connection.*;

public class BD {

    private static BD bd;    
    private static Connexion connexion = new Connexion("saadiw", "Williams13"); // rentrer ses propres indentifiants

    private BD() { }


    public static BD getInstance() {
        if(bd == null) {
            bd = new BD();
        }
        return bd;
    }

    /* niveau d'isolation par défaut d'oracle */
    public Statement getReadCommittedSTMT () throws SQLException {
        Connection tmpConnexion = connexion.getConnection();
        tmpConnexion.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        return tmpConnexion.createStatement();

    }

    public Statement getReadUncommittedSTMT () throws SQLException {
        Connection tmpConnexion = connexion.getConnection();
        tmpConnexion.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
        return tmpConnexion.createStatement();
    }

    public Statement getRepeatableReadSTMT () throws SQLException {
        Connection tmpConnexion = connexion.getConnection();
        tmpConnexion.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
        return tmpConnexion.createStatement();
    }

    public Statement getSerializableSTMT () throws SQLException {
        Connection tmpConnexion = connexion.getConnection();
        tmpConnexion.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        return tmpConnexion.createStatement();
    }
}
