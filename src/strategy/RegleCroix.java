package strategy;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.ScoreController;
import dao.DAO;
import dao.GamerDAO;
import frames.Jeu;
import graphique.Case;
import graphique.Grille;
import model.Gamer;
import model.Strategy;
import dao.StrategyDAO;
import utilitaires.Parametres;
import utilitaires.SystemeCache;

public class RegleCroix extends Strategy {

	private Grille grille;
	private String savedPseudo = "";
	public static SystemeCache cache = new SystemeCache();

	public RegleCroix() {

	}

	public RegleCroix(Grille grille) {
		this.grille = grille;
		Jeu.cache.putStrategyId(2);
	}

	public void apply() {
		for (int i = 0; i < grille.getCases().length; i++) {
			for (int j = 0; j < grille.getCases()[0].length; j++) {
				grille.getCaseIJ(i, j).addActionListener(new RegleCroixListener());
			}
		}
	}

	class RegleCroixListener implements ActionListener {

		@Override
		public void actionPerformed(java.awt.event.ActionEvent arg0) {
			Case source = (Case) arg0.getSource();

			for (int i = 0; i < grille.getCases().length; i++) {
				for (int j = 0; j < grille.getCases()[0].length; j++) {

					if (i == source.getI() || j == source.getJ())
						grille.getCaseIJ(i, j).changerCouleur();
				}
			}

			if (isDone(grille)) {
				grille.forTheWin();

				/*
				 * Pour gérer le dialogue:
				 * https://imss-www.upmf-grenoble.fr/prevert/Prog/Java/swing/JOptionPane.html
				 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
				 * http://www.iro.umontreal.ca/~dift1170/A09/docPDF/chapit11.pdf
				 */

				savedPseudo = (String) JOptionPane.showInputDialog(null,
						new JLabel("Dans le mille!! Tu a réussis en " + Grille.compteurCoup
								+ " coups. \n Entrez votre pseudo: ", JLabel.RIGHT),
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
				// Modifier le 313 pour avoir un vrai temps, donc rajouter un timer

			} else {
				Grille.compteurCoup++;
			}

		}

	}

}
