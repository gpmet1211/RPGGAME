package model;

import java.io.Serializable;

public class Clear implements Serializable{

	private int id; //id
	private String name; //名前
	private long time; //クリア時間

	public Clear() {

	}

	public Clear(String name,long time) {
		this.name = name;
		this.time = time;
	}

	public Clear(int id, String name,long time) {
		this.id = id;
		this.name = name;
		this.time = time;
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
