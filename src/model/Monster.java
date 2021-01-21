package model;

import java.io.Serializable;

public class Monster implements Serializable {

	private int id; //id
	private String name; // 名前
	private int hp; //hp
	private int mp; //mp
	private int attack; //攻撃力
	private int exp; //倒された際の経験値

	public Monster() {

	}

	public Monster(int id, String name, int hp, int mp, int attack, int exp) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.attack = attack;
		this.exp = exp;
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

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int attack(Hero hero) {
		int damege = Math.min(hero.getHp(), this.getAttack());
		hero.setHp(hero.getHp() - damege);
		return damege;
	}

}
