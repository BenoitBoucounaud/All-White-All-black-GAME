package controllers;

import java.sql.Timestamp;
import java.util.List;

import frames.Jeu;
import graphique.Grille;
import model.Gamer;
import model.Level;
import model.Score;
import model.dao.DAO;
import model.dao.GamerDAO;
import model.dao.LevelDAO;
import model.dao.ScoreDAO;

public class ScoreController extends Controllers {

	private static DAO<Score> scoreDAO = new ScoreDAO();
	private static DAO<Gamer> gamerDAO = new GamerDAO();
	private static DAO<Level> levelDAO =new LevelDAO();
	
	public static void addNewScore(double duree) {
		Score score = new Score(duree,
				Grille.compteurCoup,
				gamerDAO.findById(Jeu.cache.getGamerId()), 
				levelDAO.findById(Jeu.cache.getLevelId()),
				Jeu.cache.getStrategyId(),
				new Timestamp(System.currentTimeMillis())
				);
		

		scoreDAO.insert(score);
		Jeu.updateScore();
		System.out.println("Nouveau score enregistré");
	}


	public static List<Score> getAllScores() {
		return scoreDAO.findAll();
	}

	public static void printScores() {
		for(Score score : scoreDAO.findAll()) {
			System.out.println(score.getGamer().getPseudo() + " ; " + score.getNbClick() + " ; " + score.getDureeSeconde() + " ; " + score.getLevel().getLibelle() + " ; " + score.getStrategy() + " ; " + score.getCreatedAt());
		}
			
	}
	
}
