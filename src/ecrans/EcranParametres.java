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

public class EcranParametres extends Ecran {

	static final long serialVersionUID = 1L;

	private JButton btEasy;
	private JButton btNormal;
	private JButton btHard;
	private JButton btAccueil;
	private MainCard mainCard;
	private JLabel titreParametres;

	public EcranParametres(MainCard mainCard) {
		this.mainCard = mainCard;
		this.mainCard.add(this, MainCard.ECRAN_PARAMETRES);
		init();
	}

	void init() {
		titreParametres = new JLabel("Paramètres");
		titreParametres.setForeground(Color.BLACK);
		titreParametres.setFont(new Font(titreParametres.getFont().getFontName(), Font.BOLD, 30));
		titreParametres.setHorizontalAlignment(SwingConstants.CENTER);
		titreParametres.setVerticalAlignment(SwingConstants.CENTER);

		btEasy = new JButton("Easy Mode");
		btNormal = new JButton("Normal Mode");
		btHard = new JButton("Hard Mode");
		btAccueil = new JButton("PRECEDENT");
		addListener();

		setLayout(new BorderLayout());

		GridLayout layoutMenu = new GridLayout(4, 1);
		layoutMenu.setVgap(30);
		JPanel center = new JPanel(layoutMenu);

		center.add(btEasy);
		center.add(btNormal);
		center.add(btHard);
		center.add(btAccueil);

		add(titreParametres, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

	}

	private void addListener() {
		btEasy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Parametres.putMode("Easy", 3, 3);

			}

		});
		btNormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Parametres.putMode("Normal", 5, 5);

			}

		});
		btHard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Parametres.putMode("Hard", 7, 7);

			}

		});

		btAccueil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainCard.showEcranAccueil();
			}
		});
	}

}
