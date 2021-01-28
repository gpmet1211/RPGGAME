package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Clear;

public class ClearDAO {

	public List<Clear> findAll() {
		List<Clear> clearList = new ArrayList<Clear>();

		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {

			// SELECT文の準備
			String sql = "SELECT * FROM rpgdb.m_clear";//TEXTを消した
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("clear_id");
				String name = rs.getString("clear_name");
				long time = rs.getInt("clear_time");
				Clear clear = new Clear(id, name, time);
				clearList.add(clear);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return clearList;
	}

	/**
	 * 指定されたlimit値からClearリストを検索して返します。
	 * @param list 1位～表示したいランキング値
	 * @return List<Clear>
	 * @throws SQLException,ClassNotFoundException
	 */
	public List<Clear> findRanker(int limit) {
		List<Clear> clearList = new ArrayList<Clear>();

		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {

			// SELECT文の準備
			String sql = "SELECT * FROM rpgdb.m_clear ORDER BY clear_time ASC LIMIT ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setInt(1, limit);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("clear_id");
				String name = rs.getString("clear_name");
				long time = rs.getInt("clear_time");
				Clear clear = new Clear(id, name, time);
				clearList.add(clear);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return clearList;
	}

	/**
	 * 全レコードからをClear_timeの平均値を検索して返します。
	 * @param list 1位～表示したいランキング値
	 * @return long
	 * @throws SQLException,ClassNotFoundException
	 */
	public long findAvg() {
		long avg = 0;

		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {

			// SELECT文の準備
			String sql = "SELECT AVG(clear_time) FROM m_clear;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果からavgを取得
			while (rs.next()) {
				avg = rs.getInt("AVG(clear_time)");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
		return avg;
	}
	/**
	 * 指定されたClearインスタンスをレコードとしてテーブルに挿入して、成否により真偽値を返します。
	 * @param Clear テーブルに挿入したいインスタンス
	 * @return List<Clear>
	 * @throws SQLException,ClassNotFoundException
	 */
	public boolean create(Clear clear) {
		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO m_clear(clear_name, clear_time) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, clear.getName());
			pStmt.setLong(2, clear.getTime());
			// INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) { return false; }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}