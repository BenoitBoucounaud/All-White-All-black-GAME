package model;

import java.sql.Timestamp;

public class Score {

	private int id;
	private double dureeSeconde;
	private int nbClick;
	private Gamer gamer;
	private Level level;
	private int strategy;
	private Timestamp createdAt;
	private double pointsCoup;
	private double pointsTemps;
	private int scoreTotal;

	public Score() {
		dureeSeconde = 0;
		nbClick = 0;
		gamer = null;
		level = null;
		strategy = 1;
		createdAt = new Timestamp(System.currentTimeMillis());
	}

	public Score(double dureeSeconde, int nbClick, Gamer gamer, Level level, int strategy, Timestamp createdAt) {
		this.dureeSeconde = dureeSeconde;
		this.nbClick = nbClick;
		this.gamer = gamer;
		this.level = level;
		this.strategy = strategy;
		this.createdAt = createdAt;
		this.pointsCoup = 300 - nbClick;
		this.pointsTemps = (5 * 60) - dureeSeconde;
		this.scoreTotal = (int)(this.pointsCoup + this.pointsTemps) * level.getId()* 2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDureeSeconde() {
		return dureeSeconde;
	}

	public void setDureeSeconde(double dureeSeconde) {
		this.dureeSeconde = dureeSeconde;
	}

	public int getNbClick() {
		return nbClick;
	}

	public void setNbClick(int nbClick) {
		this.nbClick = nbClick;
	}

	public Gamer getGamer() {
		return gamer;
	}

	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getStrategy() {
		return strategy;
	}

	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	public double getPointsCoup() {
		return pointsCoup;
	}

	public double getPointsTemps() {
		return pointsTemps;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public double getScoreTotal() {
		return scoreTotal;
	}

}
