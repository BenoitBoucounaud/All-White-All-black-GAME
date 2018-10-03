package strategy;

import graphique.Grille;
import utilitaires.Parametres;

public interface Regle {

	public void apply() ;
	
	public default boolean isDone(Grille grille) {
		for(int i = 0 ; i < grille.getCases().length ; i++) {
			for(int j = 0 ; j < grille.getCases()[0].length ; j++) {
				if(grille.getCaseIJ(i, j).getBackground().equals(Parametres.COULEUR_DEB)) {
					return false ;
				}
			}
		}
		return true ;
	}
}
