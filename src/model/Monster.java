package model;

import java.io.Serializable;

public class Monster implements Serializable {

	private int id; //id
	private String name; // 名前
	private int hp; //hp
	private int mp; //mp
	private int attack; //攻撃力
	private String url; //モンスターのファイル名

	public Monster() {

	}

	public Monster(int id, String name, int hp, int mp, int attack, String url) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.attack = attack;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		//マイナスHPを防ぐ
		if(hp < 0) {
			this.hp = 0;
		}else {
			this.hp = hp;
		}
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		//マイナスMPを防ぐ
		if(mp < 0) {
			this.mp = 0;
		}else {
			this.mp = mp;
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int attack(Hero hero) {
		int damege = Math.min(hero.getHp(), this.getAttack());
		hero.setHp(hero.getHp() - damege);
		return damege;
	}

}
