package frames;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

import controllers.ScoreController;
import ecrans.Ecran;
import ecrans.EcranAccueil;
import ecrans.EcranJeu;
import ecrans.EcranParametres;
import ecrans.EcranScore;
import ecrans.EcranUtilisateur;
import ecrans.MainCard;
import model.Gamer;
import model.Level;
import model.Strategy;
import dao.DAO;
import dao.GamerDAO;
import dao.LevelDAO;
import strategy.Regle;
import strategy.RegleSimple;
import utilitaires.Parametres;
import utilitaires.SystemeCache;

public class Jeu extends JFrame {

	private static final long serialVersionUID = 1L;

	private EcranAccueil ecranAccueil;
	private static EcranJeu ecranJeu;
	private static EcranScore ecranScore;
	private EcranParametres ecranParametres;
	private static EcranUtilisateur ecranUtilisateur;
	private static Strategy regleSimple;

	private Strategy regleCroix;
	public static MainCard cards;
	public static SystemeCache cache = new SystemeCache();

	public Jeu() {

		setTitle(Parametres.APP_NAME);
		setPreferredSize(Parametres.TAILLE_FENETRE_DEFAUT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// On set le regroupeur
		cards = new MainCard();
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		cards.setBorder(padding);

		DAO<Level> levelDAO = new LevelDAO();
		Level defaut = new Level("normal");
		cache.putLevelId(LevelDAO.findByLibelle(defaut.getLibelle()).getId());

		// Pour montrer tous les pseudo dans la console
		DAO<Gamer> gamerDAO = new GamerDAO();
		List<Gamer> gamers = gamerDAO.findAll();
		for (Gamer gamer : gamers) {
			System.out.println(gamer.toString());
		}

		// Pour montrer tous les scores dabs la console
//		ScoreController.printScores();

		ecranAccueil = new EcranAccueil(cards);
		ecranScore = new EcranScore(cards);
		ecranParametres = new EcranParametres(cards);
		ecranUtilisateur = new EcranUtilisateur(cards);

		ecranJeu = new EcranJeu(cards);
		regleSimple = new RegleSimple(ecranJeu.getGrille());
		regleSimple.apply();

		cards.showEcranAccueil();

		getContentPane().add(cards);
		pack();
		setVisible(true);
	}

	public static void updateScore() {
		ecranScore = new EcranScore(cards);
	}

	public static void updateGrille() {

		ecranJeu = new EcranJeu(cards);
		regleSimple = new RegleSimple(ecranJeu.getGrille());
		regleSimple.apply();
	}

}
