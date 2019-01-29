package BDD;

import java.sql.Statement;

public interface CRUDInterface<T> {

	public boolean create(T object);

	public T read(int identifiant);

	public boolean update(T object);

	public boolean delete(int idenTtifiant);

}
