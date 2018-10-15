package controllers;

import java.sql.Timestamp;
import java.util.List;

import dao.DAO;
import dao.GamerDAO;
import dao.LevelDAO;
import dao.ScoreDAO;
import ecrans.EcranJeu;
import frames.Jeu;
import graphique.Grille;
import model.Gamer;
import model.Level;
import model.Score;

public class ScoreController extends Controllers {

	private static DAO<Score> scoreDAO = new ScoreDAO();
	private static DAO<Gamer> gamerDAO = new GamerDAO();
	private static DAO<Level> levelDAO =new LevelDAO();
	
	public static void addNewScore() {
		Score score = new Score(
				EcranJeu.chrono.elapsed(),
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
			System.out.println(score.getGamer().getPseudo() + " ; " + score.getNbClick() + " ; " + score.getDureeSeconde() + " ; " + score.getLevel().getLibelle() + " ; " + score.getStrategy() + " ; " + score.getCreatedAt() + " ; " + score.getScoreTotal());
		}
			
	}
	
}
