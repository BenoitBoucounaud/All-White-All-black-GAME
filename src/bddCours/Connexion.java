package bddCours;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.xdevapi.Statement;

public class Connexion{

	public static String[] nomCol;
	
	public static void main(String[] args) {
		try {
			// Chargement du Driver MySQL
			Class.forName( "com.mysql.cj.jdbc.Driver" );
			
			// L'adresse de la base de donneÌ�es, localhost parce quâ€™on est en local, 3306 le port par deÌ�faut.
			String bddAdresse = "//localhost:3306/TNTB" ;
			
			// Options de connections, par exemple, le Time Zone
			String optionsConnection = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ";
			
			// L'url compleÌ€te de connexion
			String url = "jdbc:mysql:" + bddAdresse + optionsConnection;
			
			// Identifiants de connexion aÌ€ la base de donneÌ�es
			String user = "root"; 
			String password = "";
			
			// Lâ€™objet connexion qui nous permet de communiquer avec la base de donneÌ�es.
			Connection connexion = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion effective !"); 
		
		///////////////////
		
		//CreÌ�ation d'un objet Statement
		java.sql.Statement state = connexion.createStatement();
		//L'objet ResultSet contient le reÌ�sultat de la requeÌ‚te SQL
		ResultSet result = state.executeQuery("SELECT * FROM scores"); 
		//On reÌ�cupeÌ€re les Meta Data
		java.sql.ResultSetMetaData resultMeta = result.getMetaData();
		
		
		System.out.println("\n______________________________________________");
		
		
		// nom des colonnes
		String nomCol="";
		for(int i = 1; i <= resultMeta.getColumnCount(); i++) { 
			//nomCol = nomCol + ("\t" +resultMeta.getColumnName(i).toUpperCase() + "\t |");
			nomCol = nomCol + ('"'+resultMeta.getColumnName(i).toUpperCase()+'"'+", " );
		}
		nomCol ="{" +  nomCol.substring(0,nomCol.lastIndexOf(",")) + "}" ;
		//System.out.println(nomCol);
		
		
		
		System.out.println("\n______________________________________________");
		
		// contenu des colonnes
		String resCol="";
		String resCol2="";
		while(result.next()) {
			resCol = "";
			for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
				resCol = resCol + ("\t" + result.getString(i) + "\t |");
				//resCol = resCol + ('"'+ result.getString(i) +'"'+", ");	
			} 
			resCol2 =resCol2 +  "{" +  resCol.substring(0,resCol.lastIndexOf(",")) + "}" ;
			resCol2 =  resCol2 + ", ";
		}
		resCol2 = "{" + nomCol + ", "+ resCol2.substring(0,resCol2.lastIndexOf(",")) + "}";
		System.out.println(resCol2);
		
		
		result.close();
		state.close();
		connexion.close();
		////////////////////
		
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage()); }
	}
}
