package ecrans;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphique.Grille;
import utilitaires.StopWatch;

public class EcranJeu extends Ecran {

	private static final long serialVersionUID = 1L;

	private JButton reset;
	private JButton retour;
	private Grille grille;
	public static StopWatch chrono;

	public EcranJeu(MainCard mainCard) {
		this.mainCard = mainCard;
		this.mainCard.add(this,MainCard.NOM_ECRAN_JEU);
		build(new Grille());
	}

	public EcranJeu(Grille grille, MainCard mainCard) {
		this.mainCard = mainCard;
		this.mainCard.add(this,MainCard.NOM_ECRAN_JEU);
		build(grille);
	}

	private void build(Grille grille) {
		this.grille = grille;

		chrono = new StopWatch();
		chrono.start();
		
		reset = new JButton("RESET");
		retour = new JButton("PRECEDENT");
		setLayout(new BorderLayout());
		add(reset, BorderLayout.NORTH);
		add(grille, BorderLayout.CENTER);
		add(retour, BorderLayout.SOUTH);
		addListener();

	}

	public Grille getGrille() {
		return grille;
	}

	private void addListener() {
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grille.reset();
			}
		});

		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainCard.showEcranAccueil();
			}
		});
	}

}
