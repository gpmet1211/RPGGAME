/*
 * JDBC_07_DAO3
 * sample.ConnectionManager.java
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * コネクションマネージャー
 */
public class ConnectionManager {

	/**
	 * データベースURL
	 */
	private final static String URL = "jdbc:mysql://localhost:3306/rpgdb?useSSL=false";

	/**
	 * ユーザ
	 */
	private final static String USER = "rpgadmin";

	/**
	 * パスワード
	 */
	private final static String PASSWORD = "rpgadmin";

	/**
	 * データベースへの接続を取得して返します。
	 *
	 * @return コネクション
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		// JDBCドライバの読み込み
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);

	}
}