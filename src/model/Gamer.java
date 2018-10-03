package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.dao.GamerDAO;

public class Gamer {

	private int id;
	private String pseudo;
	private Timestamp createdAt;

	public Gamer() {
		pseudo = "";
		createdAt = new Timestamp(System.currentTimeMillis());
	}

	public Gamer(ResultSet result) {
		try {
			id = result.getInt(GamerDAO.COLUMN_ID);
			pseudo = result.getString(GamerDAO.COLUM_PSEUDO);
			createdAt = result.getTimestamp(GamerDAO.COLUM_CREATED_AT);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	
	public String toString() {
		String res ="Je suis "+ pseudo;
		return res;
	}
	
	public Gamer(String pseudo) {
		this();
		this.pseudo = pseudo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
