package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Level;

public class LevelDAO extends DAO<Level> {

	public static final String COLUMN_INT = "id_level";
	public static final String COLUMN_LIBELLE = "libelle";
	public static final String COLUMN_NBL = "nbL";
	public static final String COLUMN_NBC = "nbC";

	@Override
	public Level findById(int id) {
		Level level = null;
		try {
			PreparedStatement prepare = connexion.prepareStatement("SELECT * FROM level WHERE id_level = (?)");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {

				level = new Level();
				level.setId(result.getInt(COLUMN_INT));
				level.setLibelle(result.getString(COLUMN_LIBELLE));
				level.setNbL(result.getInt(COLUMN_NBL));
				level.setNbC(result.getInt(COLUMN_NBC));
			}
			prepare.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return level;
	}

	public static Level findByLibelle(String libelle) {
		Level level = null;
		try {
			PreparedStatement prepare = connexion.prepareStatement("SELECT * FROM level WHERE libelle = ? ");
			prepare.setString(1, libelle);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {

				level = new Level();
				level.setId(result.getInt(COLUMN_INT));
				level.setLibelle(result.getString(COLUMN_LIBELLE));
				level.setNbL(result.getInt(COLUMN_NBL));
				level.setNbC(result.getInt(COLUMN_NBC));
			}
			prepare.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return level;
	}

	@Override
	public List<Level> findAll() {
		// Not used
		return null;
	}

	@Override
	public int insert(Level object) {
		// Not used
		return 0;
	}

	@Override
	public int delete(Level object) {
		// Not used
		return 0;
	}

	@Override
	public int update(Level object) {
		/// Not used
		return 0;
	}

}
