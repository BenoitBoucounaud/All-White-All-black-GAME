package strategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.ScoreController;
import frames.Jeu;
import graphique.Case;
import graphique.Grille;
import model.Strategy;
import utilitaires.Parametres;

public class RegleSimple extends Strategy {

	private Grille grille;

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
