package model;

import strategy.Regle;

public abstract class Strategy implements Regle {

	protected int id;
	protected String titre;
	protected String description;

	protected Strategy() {

	}

	protected Strategy(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
