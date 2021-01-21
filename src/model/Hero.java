package model;

import java.io.Serializable;
import java.util.Random;

public class Hero implements Serializable {

	private int id; //id
	private String name; // 名前
	private int hp; //hp
	private int mp; //mp
	private Sword sword; //剣を持つ
	private int stockExp; //獲得済み経験値

	public Hero() {

	}

	public Hero(int id, String name, int hp, int mp, int stockExp, Sword sword) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.stockExp = stockExp;
		this.sword = sword;
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
		if (hp < 0) {
			this.hp = 0;
		} else {
			this.hp = hp;
		}
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		//マイナスMPを防ぐ
		if (mp < 0) {
			this.mp = 0;
		} else {
			this.mp = mp;
		}
	}

	public int getStockExp() {
		return stockExp;
	}

	public void setStockExp(int stockExp) {
		this.stockExp = stockExp;
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	//攻撃メソッド
	public int attack(Monster monster) {
		//ダメージはswordの攻撃力に影響する
		int damege = Math.min(monster.getHp(), sword.getAttack());
		monster.setHp(monster.getHp() - damege);
		return damege;
	}

	//はなつ攻撃メソッド
	public int releaseAttack(long resultTime, Monster monster) {
		// 勇者の攻撃をする（差が0.15なら3倍攻撃、それ以外なら通常攻撃)
		int damege = 0;
		if (resultTime <= 150) {
			damege += this.attack(monster);
			damege += this.attack(monster);
			damege += this.attack(monster);
//			System.out.println("damege:" + damege);
		} else {
			damege += this.attack(monster);
		}
		return damege;
	}

	//はなつ補給メソッド
	public int releaseBonus(long resultTime) {
		// 勇者のMPを回復する(誤差0.00～0.30なら10、0.31～0.75なら5、0.76～1.25なら1)
		int bonusMp = 0;
		if (resultTime <= 300) {
			bonusMp = 10;
		} else if (resultTime >= 301 && resultTime <= 750) {
			bonusMp = 5;
		} else if (resultTime >= 751 && resultTime <= 1250) {
			bonusMp = 1;
		} else {
			//それ以外はMP補助なし
		}
		this.setMp(this.getMp() + bonusMp);

		return bonusMp;
	}

	//坂東エイドメソッド
	public int bandoAid() {
		//HPを30～100回復
		int useMp = 4; //消費MP
		int aidPoint = 0; //回復ポイント（初期化）
		// MP判定
		if(this.getMp() >= useMp) {
			// MP消費
			this.setMp(this.getMp() - useMp);
			// 効果処理
			aidPoint = new Random().nextInt(71)+30; //30～100の値
			this.setHp(this.getHp() + aidPoint);
		}
		return aidPoint;
	}

	//イケおじturboメソッド
	public boolean ikeTurbo(){
		// 5ターンの間攻撃力2倍 ※ターン制御はサーブレットで行う
		boolean ikeFlag = false; //使用完了フラグ
		int useMp = 9; //消費MP
		// MP判定
		if(this.getMp() >= useMp) {
			// MP消費
			this.setMp(this.getMp() - useMp);
			// 効果処理
			this.sword.setAttack(this.sword.getAttack() * 2);
			// 使用完了フラグをONにする
			ikeFlag = true;
		}
		return ikeFlag;
	}

	//怨念がおんねんメソッド
	public int RemorseAttack(Monster monster) {
		//攻撃力の10倍で攻撃
		int useMp = 35; //消費MP
		int magni = 10; //与える攻撃の倍率
		int damege = 0;
		// MP判定
		if(this.getMp() >= useMp) {
			// MP消費
			this.setMp(this.getMp() - useMp);
			// 効果処理
			for (int i = 0; i < magni; i++) { //攻撃を倍率数だけ
				damege += this.attack(monster);
			}
		}
		return damege;
	}

	//武器生成メソッド
	public boolean swordCreate(Sword sword) {
		// 5ターンの間武器を変える ※ターン制御はサーブレットで行う
		boolean scFlag = false; //使用完了フラグ
		int useMp = 1; //消費MP
		// MP判定
		if(this.getMp() >= useMp) {
			// MP消費
			this.setMp(this.getMp() - useMp);
			// 効果処理
			this.setSword(sword);
			// 使用完了フラグをONにする
			scFlag = true;
		}
		return scFlag;
	}

	//超武器生成メソッド
	public boolean superSwordCreate(Sword sword) {
		// 5ターンの間武器を変える ※ターン制御はサーブレットで行う
		boolean sscFlag = false; //使用完了フラグ
		int useMp = 7; //消費MP
		// MP判定
		if(this.getMp() >= useMp) {
			// MP消費
			this.setMp(this.getMp() - useMp);
			// 効果処理
			this.setSword(sword);
			// 使用完了フラグをONにする
			sscFlag = true;
		}
		return sscFlag;
	}
}
