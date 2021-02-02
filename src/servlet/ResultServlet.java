package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SwordDAO;
import model.Hero;
import model.Monster;
import model.Sword;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得
		String select = request.getParameter("select");
		// 味方生死判定初期化
		boolean judgeAllyLife = true;
		// 敵生死判定初期化
		boolean judgeEnemyLife = true;
		// フォワードするURL
		String url = "";

		//セッション確立
		HttpSession session = request.getSession();
		//セッションから味方情報を取得
		List<Hero> heroList = (List<Hero>) session.getAttribute("heroList");
		Hero hero = heroList.get(0);
		//セッションから敵情報を取得
		Monster monster = (Monster) session.getAttribute("monster");
		//セッションからターン数を取得
		int encountTurn = (Integer) session.getAttribute("encountTurn");
		//セッションからイケおじ用ターン数を取得
		int ikeStartTurn = (Integer) session.getAttribute("ikeStartTurn");
		//セッションから武器生成ターン数を取得
		int swordCreateTurn = (Integer) session.getAttribute("swordCreateTurn");
		//セッションから場面の情報を取得
		String scene = (String) session.getAttribute("scene");

		// 共通変数の準備
		int chargeTargetTime; // チャージ目標時間
		long chargeResultTime; // チャージ時間
		String chargeMessage; // チャージメッセージ
		StringBuilder battleSb = new StringBuilder(); //バトルメッセージ用
		String battleMessage = ""; // バトルメッセージ
		SwordDAO swordDAO = new SwordDAO(); //武器のDAO
		Sword sword = swordDAO.getSword(1); //武器（初期値として普通の剣(ID:1)とする

		// 各情報の初期化とセッションスコープへの格納
		chargeResultTime = 0; // チャージ時間
		request.setAttribute("chargeResultTime", chargeResultTime);
		chargeMessage = ""; // チャージメッセージ
		session.setAttribute("chargeMessage", chargeMessage);

		/****** ↓ボタンで渡された情報により分岐 ******/
		switch (select) {
		case "ためる"://ためる時の処理

			// [設定]チャージ時間の上限値
			int chargeRandom = 3; //一旦3

			// 開始時刻を取得
			long chargeStartTime = System.currentTimeMillis();
			// ランダムで秒数を生成し、チャージメッセージを変更
			chargeTargetTime = new Random().nextInt(chargeRandom) + 1;
			chargeMessage = chargeTargetTime + "秒ちょうどで『はなつ』ボタンを押せ！<br>";
			// 情報をセッションスコープに保存
			session.setAttribute("chargeTargetTime", chargeTargetTime);
			session.setAttribute("startTime", chargeStartTime);
			session.setAttribute("chargeMessage", chargeMessage);
			break;

		case "はなつ"://はなつ時の処理
			// セッションスコープからチャージ目標時間を取得
			chargeTargetTime = (Integer) session.getAttribute("chargeTargetTime");

			if (chargeTargetTime != 0) { //はなつ処理
				// 終了時刻を取得
				long endTime = System.currentTimeMillis();
				// 開始時刻をセッションスコープから取得
				chargeStartTime = (long) session.getAttribute("startTime");
				// 差分の時間を計算
				long longChargeTargetTime = (long) chargeTargetTime * 1000;
				chargeResultTime = Math.abs(longChargeTargetTime - (endTime - chargeStartTime)); //絶対値を求める

				// 結果とモンスターを渡して、はなつ処理
				int damege = hero.releaseAttack(chargeResultTime, monster);
				int bonusMp = hero.releaseBonus(chargeResultTime);
				// バトルメッセージに結果を反映
				if (damege >= 35 && bonusMp >= 10) { //初期装備で3倍ダメージかつMP10回復想定
					battleSb.append("とてもよい攻撃、大ダメージ！");
				} else if (bonusMp >= 5) {
					battleSb.append("少しよい攻撃！");
				} else {
					battleSb.append("普通の攻撃！");
				}
				// バトルメッセージの追加
				if (damege > 0) {
					battleSb.append("勇者は" + damege + "ダメージを" + monster.getName() + "に与えた<br>");
				}
				if (bonusMp > 0) {
					battleSb.append("勇者のMPが" + bonusMp + "回復した<br>");
				}
				// 時刻情報をセッションスコープに保存
				request.setAttribute("chargeResultTime", chargeResultTime);

				// チャージ状態とスタート時間を初期化し、セッションスコープに登録
				chargeStartTime = 0; //チャージ開始時間
				chargeTargetTime = 0; //チャージ目標時間
				session.setAttribute("chargeTargetTime", chargeTargetTime);
				session.setAttribute("startTime", chargeStartTime);

			} else { //先にためるをしていない場合
				// チャージメッセージ変更
				chargeMessage = "先に『ためる』必要がある！<br>";
				// 情報をセッションスコープに保存
				session.setAttribute("chargeMessage", chargeMessage);
			}
			break;
		case "坂東エイド"://坂東エイド時の処理

			int aidPoint = hero.bandoAid();

			if (aidPoint != 0) { //回復できた場合
				battleSb.append("勇者のHPが" + aidPoint + "回復した<br>");
			} else { //回復できなかった場合
				battleSb.append("MPが足りない！<br>");
			}

			break;
		case "イケおじturbo"://イケおじturbo時の処理

			boolean ikeFlag = hero.ikeTurbo();
			if (ikeFlag) { //処理できた場合
				battleSb.append("イケおじモード！武器の攻撃力が2倍になる！<br>");
				//現在のターンをセッションスコープに保存
				session.setAttribute("ikeStartTurn", encountTurn);
			} else { //処理できなかった場合
				battleSb.append("MPが足りない！<br>");
			}

			break;

		case "怨念がおんねん"://怨念がおんねん時の処理

			int damege = hero.RemorseAttack(monster);

			if (damege != 0) { //攻撃できた場合
				battleSb.append("勇者の怨念が振りかかる！" + damege + "ダメージを" + monster.getName() + "に与えた<br>");
			} else { //攻撃できなかった場合
				battleSb.append("MPが足りない！<br>");
			}

			break;

		case "武器生成"://武器生成時の処理

			if (ikeStartTurn == 0) { //イケおじモードではないとき
				//ランダムで武器を選ぶ
				int sword_id = new Random().nextInt(10) + 1; //1～10
				sword = swordDAO.getSword(sword_id);

				//武器生成メソッド
				boolean scFlag = hero.swordCreate(sword);
				if (scFlag) { //処理できた場合
					battleSb.append("武器生成！武器を" + hero.getSword().getName() + "に変更した！<br>");
					//現在のターンをセッションスコープに保存
					session.setAttribute("swordCreateTurn", encountTurn);
				} else { //処理できなかった場合
					battleSb.append("MPが足りない！<br>");
				}
			} else { //イケおじモードのとき
				battleSb.append("イケおじモードでは武器生成できない！<br>");
			}
			break;

		case "超武器生成"://武器生成時の処理

			if (ikeStartTurn == 0) { //イケおじモードではないとき
				// リクエストパラメータの取得
				String swordId = request.getParameter("swordId");
				// ユーザが選んだ情報から武器を選ぶ
				sword = swordDAO.getSword(Integer.parseInt(swordId));

				//超武器生成メソッド
				boolean sscFlag = hero.superSwordCreate(sword);
				if (sscFlag) { //処理できた場合
					battleSb.append("超武器生成！武器を" + hero.getSword().getName() + "に変更した！<br>");
					//現在のターンをセッションスコープに保存
					session.setAttribute("swordCreateTurn", encountTurn);
				} else { //処理できなかった場合
					battleSb.append("MPが足りない！<br>");
				}
			} else { //イケおじモードのとき
				battleSb.append("イケおじモードでは武器生成できない！<br>");
			}
			break;

		default:
			break;
		}

		/* ↑ここまで、ボタンで渡された情報により分岐 */
		/* ↓ここから共通 */
		//敵が死んだか判定(HPが0か)
		if (monster.getHp() == 0) {
			judgeEnemyLife = false;
		}
		//敵が生きれば、敵の攻撃
		if (judgeEnemyLife) {
			int monsterDamege = monster.attack(hero);
			battleSb.append("勇者は" + monsterDamege + "ダメージを" + monster.getName() + "から受けた<br>");
		}
		//味方が死んだか判定（HPが0）
		if (hero.getHp() == 0) {
			judgeAllyLife = false;
		}

		//画面遷移判定
		if (!(judgeEnemyLife)){ //敵が死んだのであれば、敵死亡画面
			//場面の情報により切り分け
			if (scene.equals("steppe")) {
				url = "smallhillmonster_dead.jsp";
				hero.setSword(sword); //武器を普通の剣に戻す
			} else {
				url = "devilbattlemonster_dead.jsp";
				hero.setSword(sword); //武器を普通の剣に戻す
			}
		} else if (!(judgeAllyLife)) { //味方が死んだのであれば、ゲームオーバー画面
			url = "hero_dead.jsp";
		} else { //両方無事であれば、元の戦闘画面
			//場面の情報により切り分け
			if (scene.equals("steppe")) {
				url = "smallhill.jsp";
			} else {
				url = "devilbattle.jsp";
			}
		}

		// イケおじモード判定
		if (ikeStartTurn != 0) {
			if (encountTurn - ikeStartTurn >= 5) { //5ターン設定
				hero.getSword().setAttack(hero.getSword().getAttack() / 2); //攻撃力を半分にする（元に戻す）
				battleSb.append("普通のおじさんに戻った！<br>");
				ikeStartTurn = 0; //モード終了し、値を初期化
				session.setAttribute("ikeStartTurn", ikeStartTurn);
			} else {
				//イケおじモード継続
			}
		}

		// 武器生成判定
		if (swordCreateTurn != 0) {
			if (encountTurn - swordCreateTurn >= 5) { //5ターン設定
				hero.setSword(sword); //武器を普通の剣に戻す）
				swordCreateTurn = 0; //モード終了し、値を初期化
				session.setAttribute("swordCreateTurn", swordCreateTurn);
				battleSb.append("武器が元にもどった<br>");
			} else {
				//武器生成中
			}
		}

		// 戦闘情報をセッションスコープに保存
		battleMessage = battleSb.toString();
		request.setAttribute("battleMessage", battleMessage);

		//ターン数を追加し、セッションスコープに保存
		encountTurn++;
		session.setAttribute("encountTurn", encountTurn);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//nothing
	}

}
