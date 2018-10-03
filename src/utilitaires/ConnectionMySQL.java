package utilitaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL implements ConnectionBDD {

	private static final String user = "root";
	private static final String mdp = "";
	private static final String host = "//localhost:3306/";
	private static final String dataBaseName = "tntb";
	private static final String optionsConnection = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ";

	private static final String url = "jdbc:mysql:" + host + dataBaseName + optionsConnection;

	private static Connection instance = null;

	private ConnectionMySQL() {

	}

	public static Connection getInstance() {
		// TODO Auto-generated method stub

		if (instance == null) {
			synchronized (ConnectionMySQL.class) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Driver chargé");
					
					instance = DriverManager.getConnection(url, user, mdp);
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		return instance;
	}

}
