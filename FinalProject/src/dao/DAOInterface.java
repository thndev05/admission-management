package dao;

public interface DAOInterface<T> {
	public boolean insert(T t);
	public boolean delete(T t);
}
