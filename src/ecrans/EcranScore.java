package ecrans;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.ScoreController;
import model.Score;

public class EcranScore extends Ecran{

	private static final long serialVersionUID = 1L;
	
	private JButton retour ;
	private JTable table ;	
	private JLabel title ;
	private String[] columnNames = {"Pseudo","NB Click","Durée S","Level", "Strategy", "Date"};
	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	public EcranScore(MainCard mainCard) {
		this.mainCard = mainCard ;
		this.mainCard.add(this,MainCard.ECRAN_SCORE) ;
		setLayout(new BorderLayout());
		buildComponent();
		addListeners();
	}

	private void buildComponent() {

		title = new JLabel("Tableau de scores") ;
		add(title,BorderLayout.NORTH) ;

		JPanel center = new JPanel() ;
		center.setPreferredSize(new Dimension(600, 600));
		table = new JTable(tableModel) ;

		for(Score score : ScoreController.getAllScores()) {
			Object [] row = {score.getGamer().getPseudo(), score.getNbClick(), score.getDureeSeconde(), score.getLevel().getLibelle(), score.getStrategy(), score.getCreatedAt()} ;
			tableModel.addRow(row);
		}

		add(new JScrollPane(table),BorderLayout.CENTER) ;

		retour = new JButton("Précédent") ;		
		
		add(retour,BorderLayout.SOUTH) ;
	}

	private void addListeners() {
		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainCard.showEcranAccueil();
			}
		});
	}

}
