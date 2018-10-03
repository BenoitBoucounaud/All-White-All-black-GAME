package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Gamer;
import model.Level;
import model.Score;

public class ScoreDAO extends DAO<Score> {

	@Override
	public Score findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Score> findAll() {
		List<Score> scores = new ArrayList<Score>();

		try {
			Statement state = connexion.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM scores ");

			DAO<Gamer> gamerDAO = new GamerDAO();
			DAO<Level> levelDAO = new LevelDAO();

			while (result.next()) {
				scores.add(new Score(
						result.getDouble("duree_seconde"), 
						result.getInt("nb_click"),
						gamerDAO.findById(result.getInt("id_gamer")), 
						levelDAO.findById(result.getInt("id_level")),
						result.getInt("id_strategy"), 
						result.getTimestamp("created_at")));
			}

			result.close();
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return scores;
	}

	@Override
	public int insert(Score score) {
		int nbLignes = 0;

		try {
			String sql = "INSERT INTO scores(duree_seconde, nb_click, id_gamer, id_level, id_strategy) VALUES (?,?,?,?,?)";
			PreparedStatement prepare = connexion.prepareStatement(sql);
			prepare.setDouble(1, score.getDureeSeconde());
			prepare.setInt(2, score.getNbClick());
			prepare.setInt(3, score.getGamer().getId());
			prepare.setInt(4, score.getLevel().getId());
			prepare.setInt(5, score.getStrategy());

			nbLignes = prepare.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return nbLignes;
	}

	@Override
	public int delete(Score object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Score object) {
		// TODO Auto-generated method stub
		return 0;
	}

}
