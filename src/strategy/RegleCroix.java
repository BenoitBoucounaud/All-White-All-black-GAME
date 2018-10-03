package strategy;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.ScoreController;
import frames.Jeu;
import graphique.Case;
import graphique.Grille;
import model.Strategy;
import model.dao.StrategyDAO;
import utilitaires.Parametres;

public class RegleCroix extends Strategy {

	private Grille grille;

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
				// On peux essayer de rajouter une image en icone
				ImageIcon ImageIcon = new ImageIcon(Parametres.IMAGE_WIN);
				JOptionPane
						.showMessageDialog(null,
								new JLabel("Dans le mille!! Tu a réussis en " + Grille.compteurCoup + " coups.",
										ImageIcon, JLabel.RIGHT),
								"C'est une victoire", JOptionPane.INFORMATION_MESSAGE);
				ScoreController.addNewScore(313);
				// Modifier le 313 pour avoir un vrai temps, donc rajouter un timer

			} else {
				Grille.compteurCoup++;
			}

		}

	}

}
