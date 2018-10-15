package strategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.ScoreController;
import dao.DAO;
import dao.GamerDAO;
import ecrans.EcranJeu;
import frames.Jeu;
import graphique.Case;
import graphique.Grille;
import model.Gamer;
import model.Score;
import model.Strategy;
import utilitaires.Parametres;
import utilitaires.SystemeCache;

public class RegleSimple extends Strategy {

	private Grille grille;
	private String savedPseudo = "";
	public static SystemeCache cache = new SystemeCache();

	public RegleSimple() {

	}

	public RegleSimple(Grille grille) {
		this.grille = grille;
		Jeu.cache.putStrategyId(1);
	}

	@Override
	public void apply() {
		for (int i = 0; i < grille.getCases().length; i++) {
			for (int j = 0; j < grille.getCases()[0].length; j++) {
				grille.getCaseIJ(i, j).addActionListener(new RegleSimpleListener());
			}
		}
	}

	class RegleSimpleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Case source = (Case) e.getSource();
			source.changerCouleur();
			Case haut = grille.getCaseHaut(source);
			Case bas = grille.getCaseBas(source);
			Case gauche = grille.getCaseGauche(source);
			Case droite = grille.getCaseDroite(source);

			if (haut != null)
				haut.changerCouleur();

			if (bas != null)
				bas.changerCouleur();

			if (gauche != null)
				gauche.changerCouleur();

			if (droite != null)
				droite.changerCouleur();

			if (isDone(grille)) {
				grille.forTheWin();

				/*
				 * Pour gérer le dialogue:
				 * https://imss-www.upmf-grenoble.fr/prevert/Prog/Java/swing/JOptionPane.html
				 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
				 * http://www.iro.umontreal.ca/~dift1170/A09/docPDF/chapit11.pdf
				 */

				savedPseudo = (String) JOptionPane.showInputDialog(null,
						("Dans le mille!! Tu a réussis en " + Grille.compteurCoup + " coups et "
								+ EcranJeu.chrono.toString() + "." + "\n" + "Entrez votre pseudo: "),
						"C'est une victoire", JOptionPane.QUESTION_MESSAGE, new ImageIcon(Parametres.IMAGE_WIN), null,
						"Pseudo");
				;

				// Pour ajouter le pseudo
				DAO<Gamer> gamerDAO = new GamerDAO();
				Gamer enteredPseudo = new Gamer(savedPseudo);
				gamerDAO.insert(enteredPseudo);

				cache.putGamerId(GamerDAO.findByPseudo(enteredPseudo.getPseudo()).getId());
				cache.putGamerPseudo(enteredPseudo.getPseudo());

				ScoreController.addNewScore();

			} else {
				Grille.compteurCoup++;
			}
		}

	}

}
