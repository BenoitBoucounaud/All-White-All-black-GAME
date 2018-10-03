package ecrans;

import java.awt.CardLayout;

import javax.swing.JPanel;

import utilitaires.Parametres;

public class MainCard extends JPanel {

	private static final long serialVersionUID = 1L;

	public final static String NOM_ECRAN_JEU = "ecranJeu";
	public final static String NOM_ACCUEIL = "ecranAccueil";
	public final static String ECRAN_PARAMETRES = "ecranPara";
	public final static String ECRAN_SCORE = "ecranScore";

	private static CardLayout layout = new CardLayout();

	public MainCard() {

		setLayout(layout);
	}

	public void showEcranJeu() {
		layout.show(this, NOM_ECRAN_JEU);
	}

	public void showEcranAccueil() {

		layout.show(this, NOM_ACCUEIL);
	}

	public void showEcranParametres() {
		layout.show(this, ECRAN_PARAMETRES);
	}

	public void showEcranScore() {
		layout.show(this, ECRAN_SCORE);
	}

}
