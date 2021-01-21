package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Sword;

public class SwordDAO {

	public List<Sword> findAll() {
		List<Sword> swordList = new ArrayList<Sword>();

		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {

			// SELECT文の準備
			String sql = "SELECT * FROM rpgdb.m_sword";//TEXTを消した
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("sword_id");
				String name = rs.getString("sword_name");
				int attack = rs.getInt("sword_attack");
				Sword sword = new Sword(id, name, attack);
				swordList.add(sword);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return swordList;
	}

	/**
	 * 指定されたidから武器を検索して返します。
	 * @param swordId 武器のコード
	 * @return Swordクラス
	 * @throws SQLException,ClassNotFoundException
	 */
	public Sword getSword(Integer swordId) {

		Sword sword = new Sword(); //return用
		String sql = "SELECT * FROM rpgdb.m_sword WHERE sword_id = ?";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setString(1, swordId.toString());
			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();
			// 結果の操作
			// SELECT文の結果をArrayListに格納
			if (res.next()) {
				int id = res.getInt("sword_id");
				String name = res.getString("sword_name");
				int attack = res.getInt("sword_attack");
				sword.setId(id);
				sword.setName(name);
				sword.setAttack(attack);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return sword;
	}

}