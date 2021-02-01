# RPGGAME
# DBの準備
データベースが必要となりますので、mysqlより以下コマンドを実行願います。

/* DB作成 */
CREATE DATABASE rpgdb CHARACTER SET utf8 COLLATE utf8_general_ci;
/* ユーザを作成 */
DROP USER IF EXISTS rpgadmin;
CREATE USER rpgadmin IDENTIFIED BY 'rpgadmin';
/* 権限付与 */
GRANT ALL PRIVILEGES ON rpgdb.* TO rpgadmin;
/* DB選択 */
USE rpgdb;

/* ソードマスタ作成（ヒーローマスタより先に作ること） */
CREATE TABLE rpgdb.m_sword
( 
	sword_id   INT PRIMARY KEY,
	sword_name VARCHAR(16),
	sword_attack      INT
);
/* ヒーローマスタ作成 */
CREATE TABLE rpgdb.m_hero
( 
	hero_id   INT PRIMARY KEY,
	hero_name VARCHAR(16),
	hero_hp      INT,
	hero_mp      INT,
	hero_stockexp INT,
	sword_id INT,
	FOREIGN KEY(sword_id)
	REFERENCES m_sword(sword_id)
);
/* モンスターマスタ作成 */
CREATE TABLE rpgdb.m_monster
( 
	monster_id   INT PRIMARY KEY,
	monster_name VARCHAR(16),
	monster_hp      INT,
	monster_mp      INT,
	monster_attack  INT,
	monster_url VARCHAR(32)
);
/* クリアマスタ作成 */
CREATE TABLE rpgdb.m_clear
( 
	clear_id   INT PRIMARY KEY AUTO_INCREMENT,
	clear_name VARCHAR(16),
	clear_time BIGINT
);


/* モンスター登録 */
INSERT INTO rpgdb.m_monster VALUES(1,'スライム',33,0,7,'Slime_G.PNG');
INSERT INTO rpgdb.m_monster VALUES(2,'ケドル',44,10,12,'BlueKedoru.PNG');
INSERT INTO rpgdb.m_monster VALUES(3,'魔王',666,666,28,'Devil.png');

/* ソード登録 */
INSERT INTO rpgdb.m_sword VALUES(1,'普通の剣',12);
INSERT INTO rpgdb.m_sword VALUES(2,'弱い剣',2);
INSERT INTO rpgdb.m_sword VALUES(3,'強い剣',20);
INSERT INTO rpgdb.m_sword VALUES(4,'枝',5);
INSERT INTO rpgdb.m_sword VALUES(5,'枯れ葉付き枝',6);
INSERT INTO rpgdb.m_sword VALUES(6,'楊枝',4);
INSERT INTO rpgdb.m_sword VALUES(7,'割り箸',7);
INSERT INTO rpgdb.m_sword VALUES(8,'マシュマロ',17);
INSERT INTO rpgdb.m_sword VALUES(9,'テニスボール',15);
INSERT INTO rpgdb.m_sword VALUES(10,'ケチャップ',1);

/* ヒーロー登録 */
INSERT INTO rpgdb.m_hero VALUES(1,'勇者',50,10,0,1);

/* クリア登録 */
INSERT INTO rpgdb.m_clear(clear_name, clear_time) VALUES('もとき',52329);
INSERT INTO rpgdb.m_clear(clear_name, clear_time) VALUES('あんまん',240000);

#ゲーム開始
start.jspより実行願います。
