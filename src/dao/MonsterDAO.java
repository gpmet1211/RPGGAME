package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Monster;

public class MonsterDAO {

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

}