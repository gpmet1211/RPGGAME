package model;

import java.io.Serializable;

public class Sword implements Serializable{

	private int id; //id
	private String name; //名前
	private int attack; //攻撃力

	public Sword() {

	}

	public Sword(int id, String name,int attack) {
		this.id = id;
		this.name = name;
		this.attack = attack;
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

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

}
