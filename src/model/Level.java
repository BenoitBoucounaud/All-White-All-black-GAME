package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.LevelDAO;

public class Level {

	private int id;
	private String libelle;
	private int nbL;
	private int nbC;

	public Level() {
		libelle = "";
		nbL = 5;
		nbC = 5;

	}

	public Level(ResultSet result) {
		try {
			id = result.getInt(LevelDAO.COLUMN_INT);
			libelle= result.getString(LevelDAO.COLUMN_LIBELLE);
			nbL = result.getInt(LevelDAO.COLUMN_NBL);
			nbC = result.getInt(LevelDAO.COLUMN_NBC);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Level(String libelle, int nbL, int nbC) {
		super();
		this.libelle = libelle;
		this.nbL = nbL;
		this.nbC = nbC;
	}

	public Level(String libelle) {
		this();
		this.libelle=libelle;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getNbL() {
		return nbL;
	}

	public void setNbL(int nbL) {
		this.nbL = nbL;
	}

	public int getNbC() {
		return nbC;
	}

	public void setNbC(int nbC) {
		this.nbC = nbC;
	}
	
	
	
}
