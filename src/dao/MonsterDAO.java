package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Monster;

public class MonsterDAO {

	/**
	 * すべてのモンスターを検索して返します。
	 * @param
	 * @return List<Monster>
	*/
	public List<Monster> findAll(){
		List<Monster> monsterList = new ArrayList<Monster>();

		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {

			// SELECT文の準備
			String sql = "SELECT * FROM rpgdb.m_monster";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				String name = rs.getString("monster_name");
				int id = rs.getInt("monster_id");
				int hp = rs.getInt("monster_hp");
				int mp = rs.getInt("monster_mp");
				int attack = rs.getInt("monster_attack");
				String url = rs.getString("monster_url");
				Monster monster = new Monster(id, name, hp, mp, attack, url);
				monsterList.add(monster);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return monsterList;
	}

	/**
	 * 指定されたゲームモードによって対象となる値を決定しモンスターテーブルを更新、成否により真偽値を返します。
	 * @param Stringクラスで指定されたゲームモード
	 * @return boolean
	 */
	public boolean updateDevil(String mode) {
		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {
			// UPDATE文の準備
			String sql = "UPDATE m_monster SET monster_hp=?, monster_mp=? WHERE monster_name='魔王'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// UPDATE文中の「?」に使用する値を設定しSQLを完成
			if(mode.equals("easy")) { //easyモード時
				pStmt.setInt(1, 66);
				pStmt.setInt(2, 66);
			}else if(mode.equals("normal")) { //normanlモード時
				pStmt.setInt(1, 666);
				pStmt.setInt(2, 666);
			}
			// UPDATE文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) { return false; }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}