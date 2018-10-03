package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Gamer;

public class GamerDAO extends DAO<Gamer> {

	public static final String COLUMN_ID = "id_gamer";
	public static final String COLUM_PSEUDO = "pseudo";
	public static final String COLUM_CREATED_AT = "created_at";

	@Override
	public Gamer findById(int id) {
		Gamer gamer = null;
		try {
			String sql = "SELECT * FROM gamer WHERE id_gamer = (?)";
			PreparedStatement prepare = connexion.prepareStatement(sql);
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			if (result.first()) {
				gamer = new Gamer();
				gamer.setId(result.getInt(COLUMN_ID));
				gamer.setPseudo(result.getString(COLUM_PSEUDO));
				gamer.setCreatedAt(result.getTimestamp(COLUM_CREATED_AT));

			}

			result.close();
			prepare.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return gamer;
	}

	public static Gamer findByPseudo(String pseudo) {
		Gamer gamer = null;
		try {
			String sql = "SELECT * FROM gamer WHERE pseudo = ?";
			PreparedStatement prepare = connexion.prepareStatement(sql);
			prepare.setString(1, pseudo);
			ResultSet result = prepare.executeQuery();
			if (result.first()) {
				gamer = new Gamer();
				gamer.setId(result.getInt(COLUMN_ID));
				gamer.setPseudo(result.getString(COLUM_PSEUDO));
				gamer.setCreatedAt(result.getTimestamp(COLUM_CREATED_AT));

			}

			result.close();
			prepare.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return gamer;
	}

	private boolean exists(String pseudo) {
		boolean res = false;
		try {
			String sql = "SELECT * FROM gamer WHERE pseudo = ?";
			PreparedStatement prepare = connexion.prepareStatement(sql);
			prepare.setString(1, pseudo);
			ResultSet result = prepare.executeQuery();

			res = result.first();

			result.close();
			prepare.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	@Override
	public List<Gamer> findAll() {
		List<Gamer> gamers = new ArrayList<Gamer>();
		String sql = "SELECT * FROM gamer";

		try {
			Statement state = connexion.createStatement();
			ResultSet result = state.executeQuery(sql);

			while (result.next()) {
				gamers.add(new Gamer(result));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gamers;
	}

	@Override
	public int insert(Gamer gamer) {
		String sql = "INSERT INTO gamer(pseudo) VALUES (?)";
		int nbLignes = 0;

		if (exists(gamer.getPseudo())) {
			return -1;
		}

		try {
			PreparedStatement prepare = connexion.prepareStatement(sql);
			prepare.setString(1, gamer.getPseudo());
			nbLignes = prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nbLignes;
	}

	@Override
	public int delete(Gamer object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Gamer object) {
		// TODO Auto-generated method stub
		return 0;
	}

}
