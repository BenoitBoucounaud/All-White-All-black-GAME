package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Gamer;
import utilitaires.ConnectionMySQL;

public abstract class DAO<T> {

	protected static Connection connexion = ConnectionMySQL.getInstance();

	public abstract T findById(int id);

	public abstract List<T> findAll();

	public abstract int insert(T object);

	public abstract int delete(T object);

	public abstract int update(T object);

	public void close() {
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
