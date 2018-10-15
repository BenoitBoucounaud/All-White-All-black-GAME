package utilitaires;

import java.awt.Color;
import java.awt.Dimension;
import java.util.prefs.Preferences;

import frames.Jeu;
import model.Level;
import dao.LevelDAO;

public class Parametres {

	private static Preferences prefs;

	public static void setPreferences() {
		prefs = Preferences.userNodeForPackage(Parametres.class);

	}

	/*
	 * Pour les parametres
	 * https://docs.oracle.com/javase/7/docs/api/java/util/prefs/Preferences.html
	 * http://deptinfo.unice.fr/~grin/messupports/java/Preferences6.pdf
	 * https://www.oreilly.com/library/view/learning-java-4th/9781449372477/ch11s06.
	 * html
	 */

	/* Difficulté */
	public static String NOM_DIFF = "normal";
	public static String NB_L_DEFAUT = "5";
	public static String NB_C_DEFAUT = "5";
	public static int NB_L = 5;
	public static int NB_C = 5;

	/* - Constantes de la grille - */
	public static Color COULEUR_DEB = new Color(134, 24, 24);
	public static Color COULEUR_FIN = Color.BLACK;
	public static Color COULEUR_GRILLE = Color.CYAN;
	public static String URL_IMAGE = "./ressources/images/Cobra.jpg";

	/* Constantes de la fenêtre de victoire*/
	public static Dimension TAILLE_FENETRE_DEFAUT = new Dimension(500, 500);
	public static String APP_NAME = "Retourne les tuiles jeune ingénue!";
	public static String IMAGE_WIN = "ressources/images/CobraDansLeMille.jpg";

	/* Acceuil */
	public static String NOM_JEU = "Tuiles à retourner";

	/* Tables */
	public static String TABLE_SCORES = "scores";
	public static String TABLE_STRAT = "strategy";
	public static String TABLE_GAMER = "gamer";
	public static String TABLE_LEVEL = "level";


	public static void putMode(String mode, int L, int C) {
		setPreferences();
		
		prefs.put(NOM_DIFF, mode);
		prefs.putInt(NB_L_DEFAUT, L);
		prefs.putInt(NB_C_DEFAUT, C);
		
		NOM_DIFF = prefs.get(NOM_DIFF, mode);
		NB_L = prefs.getInt(NB_L_DEFAUT, L);
		NB_C = prefs.getInt(NB_C_DEFAUT, C);
		
		Level up = new Level(mode);
		Jeu.cache.putLevelId(LevelDAO.findByLibelle(up.getLibelle()).getId());
		
		Jeu.updateGrille();
		
//		//Debug
//		System.out.println("mode: " + NOM_DIFF);
//		System.out.println(NB_L);
//		System.out.println(NB_C);
		
	}


}
