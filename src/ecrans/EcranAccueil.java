package ecrans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitaires.Parametres;

public class EcranAccueil extends Ecran {

	private static final long serialVersionUID = 1L;

	private JButton btJouer;
	private JButton btScores;
	private JButton btParametres;
	private JButton btUtilisateur;
	private JButton btAPropos;
	private MainCard mainCard;
	private JLabel titreJeu;

	public EcranAccueil(MainCard mainCard) {
		this.mainCard = mainCard;
		this.mainCard.add(this, MainCard.NOM_ACCUEIL);
		init();
		addListener();
	}

	void init() {

		titreJeu = new JLabel(Parametres.NOM_JEU);
		titreJeu.setForeground(Color.BLACK);
		titreJeu.setFont(new Font(titreJeu.getFont().getFontName(), Font.BOLD, 30));
		titreJeu.setHorizontalAlignment(SwingConstants.CENTER);
		titreJeu.setVerticalAlignment(SwingConstants.CENTER);

		btJouer = new JButton("Jouer");
		btScores = new JButton("Scores");
		btParametres = new JButton("Parametres");
		btUtilisateur = new JButton("Utilisateur");
		btAPropos = new JButton("A Propos");
		addListener();

		setLayout(new BorderLayout());

		GridLayout layoutMenu = new GridLayout(4, 1);
		layoutMenu.setVgap(30);
		JPanel center = new JPanel(layoutMenu);

		center.add(btJouer);
		center.add(btScores);
		center.add(btParametres);
		center.add(btUtilisateur);
		center.add(btAPropos);

		add(titreJeu, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

	}

	private void addListener() {
		btJouer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainCard.showEcranJeu();
			}
		});

		btParametres.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainCard.showEcranParametres();

			}
		});

		btScores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainCard.showEcranScore();

			}
		});
		
		btUtilisateur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainCard.showEcranUtilisateur();

			}
		});
		
	}

}
