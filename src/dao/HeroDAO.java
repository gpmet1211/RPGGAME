package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hero;
import model.Sword;

public class HeroDAO {

	/**
	 * すべての勇者を検索して返します。※現状一人
	 * @param
	 * @return List<Hero>
	*/
	public List<Hero> findAll()  {
		List<Hero> heroList = new ArrayList<Hero>();

		// データベース接続
		try (Connection conn = ConnectionManager.getConnection()) {
			// SELECT文の準備
			String sql = "SELECT * FROM rpgdb.m_hero INNER JOIN rpgdb.m_sword ON rpgdb.m_hero.sword_id = rpgdb.m_sword.sword_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("hero_id");
				String heroName = rs.getString("hero_name");
				int hp = rs.getInt("hero_hp");
				int mp = rs.getInt("hero_mp");
				int stockExp = rs.getInt("hero_stockexp");
				int swordId = rs.getInt("sword_id");
				String swordName = rs.getString("sword_name");
				int swordAttack = rs.getInt("sword_attack");
				Sword sword = new Sword(swordId, swordName, swordAttack);
				Hero hero = new Hero(id, heroName, hp, mp, stockExp, sword);
				heroList.add(hero);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return heroList;
	}




}