package graphique;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ecrans.EcranJeu;
import utilitaires.Parametres;
import utilitaires.StopWatch;

public class Grille extends JPanel {

	private static final long serialVersionUID = 1L;

	private final GridLayout layout;
	public static int compteurCase = 0;
	public static int compteurCoup = 0;

	private Case[][] cases;

	public Grille() {
		layout = new GridLayout(Parametres.NB_L, Parametres.NB_C);
		cases = new Case[Parametres.NB_L][Parametres.NB_C];

		initGrille();
	}

	public Grille(int nb_lignes, int nb_colonnes) {
		layout = new GridLayout(nb_lignes, nb_colonnes);
		cases = new Case[nb_lignes][nb_colonnes];
		initGrille();
	}

	private void initGrille() {
		setLayout(layout);
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				Case uneCase = new Case(i, j);
				uneCase.setBackground(Parametres.COULEUR_DEB);
				cases[i][j] = uneCase;
				add(uneCase);
			}
		}
	}

	/*
	 * Pour mettre une image en fond
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		// Chargement de l"image de fond
		try {
			Image img = ImageIO.read(new File(Parametres.URL_IMAGE));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur image de fond: " + e.getMessage());
		}
	}

	public Case[][] getCases() {
		return cases;
	}

	public Case getCaseIJ(int i, int j) {
		return cases[i][j];
	}

	public Case getCaseGauche(Case source) {
		Case gauche = null;
		if (source.getJ() - 1 >= 0)
			gauche = cases[source.getI()][source.getJ() - 1];
		return gauche;
	}

	public Case getCaseDroite(Case source) {
		Case droite = null;
		if (source.getJ() + 1 < cases[0].length)
			droite = cases[source.getI()][source.getJ() + 1];
		return droite;
	}

	public Case getCaseHaut(Case source) {
		Case haut = null;
		if (source.getI() - 1 >= 0)
			haut = cases[source.getI() - 1][source.getJ()];
		return haut;
	}

	public Case getCaseBas(Case source) {
		Case bas = null;
		if (source.getI() + 1 < cases.length)
			bas = cases[source.getI() + 1][source.getJ()];
		return bas;
	}

	public void reset() {
		
		EcranJeu.chrono.stop();
		EcranJeu.chrono = new StopWatch();
		EcranJeu.chrono.start();
		
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				cases[i][j].setBackground(Parametres.COULEUR_DEB);
				cases[i][j].setOpaque(true);
				cases[i][j].setBorderPainted(true);
				cases[i][j].setBorderPainted(true);
				compteurCase = 0;
				compteurCoup = 0;
			}
		}
	}

	public void forTheWin() {
		
		EcranJeu.chrono.stop();
		System.out.println(EcranJeu.chrono.toString());
		
		
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				cases[i][j].setBackground(Parametres.COULEUR_DEB);
				cases[i][j].setOpaque(false);
			}
		}
	}

}
