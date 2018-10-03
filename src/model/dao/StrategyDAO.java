package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Gamer;
import model.Strategy;
import strategy.RegleCroix;
import strategy.RegleSimple;

public class StrategyDAO extends DAO<Strategy> {

	
	public static Strategy findByTitle(String titre) {
		return null;
	}
	
	
	@Override
	public Strategy findById(int id) {
		Strategy strategy = null;
		
		try {
			
			PreparedStatement prepare = connexion.prepareStatement("SELECT * FROM strategy WHERE id_strategy = ?");
			prepare.setInt(1, id);
			ResultSet result =prepare.executeQuery();
			
			if(result.first()) {
				strategy = id ==1 ? new RegleSimple() : new RegleCroix();
			}
			result.close();
			prepare.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return strategy;
	}

	@Override
	public List<Strategy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Strategy object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Strategy object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Strategy object) {
		// TODO Auto-generated method stub
		return 0;
	}

}
