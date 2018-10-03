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
import ecrans.MainCard;
import model.Gamer;
import model.Level;
import model.Strategy;
import model.dao.DAO;
import model.dao.GamerDAO;
import model.dao.LevelDAO;
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

		DAO<Gamer> gamerDAO = new GamerDAO();
		DAO<Level> levelDAO = new LevelDAO();

		Gamer Nebiot = new Gamer("Nebiot");
		gamerDAO.insert(Nebiot);

		cache.putGamerId(GamerDAO.findByPseudo(Nebiot.getPseudo()).getId());
		cache.putGamerPseudo(Nebiot.getPseudo());

		Level defaut = new Level("normal");
		cache.putLevelId(LevelDAO.findByLibelle(defaut.getLibelle()).getId());

		List<Gamer> gamers = gamerDAO.findAll();

		for (Gamer gamer : gamers) {
			System.out.println(gamer.toString());
		}

		ScoreController.printScores();

		ecranAccueil = new EcranAccueil(cards);
		ecranScore = new EcranScore(cards);
		ecranParametres = new EcranParametres(cards);

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
