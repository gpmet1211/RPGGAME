package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HeroDAO;
import dao.MonsterDAO;
import dao.SwordDAO;
import model.Hero;
import model.Monster;
import model.Sword;

/**
 * Servlet implementation class Fightservlet
 */
@WebServlet("/FightServlet1")
public class FightServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータからゲームオーバーフラグの取得
		String endFlag = request.getParameter("endFlag");
		// フォワード先url
		String url = "";
		//セッションの確立
		HttpSession session = request.getSession();
		//セッションから場面の情報を取得
		String scene = (String) session.getAttribute("scene");

		if (endFlag == null) { //ゲーム継続
			//場面の情報により切り分け
			if (scene.equals("steppe")) { //草原→魔王城
				//セッションスコープから敵情報のみを削除
				session.removeAttribute("monster");
				url = "devilcastle.jsp";
			} else { //魔王→エンディング
				//セッションスコープ自体の削除
				session.invalidate();
				url = "ending.jsp";
			}
		} else if (endFlag.equals("end")) { //ゲーム終了
			//セッションスコープ自体の削除
			session.invalidate();
			url = "start.jsp";
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得（場面情報）
		String scene = request.getParameter("scene");

		// セッションの確立
		HttpSession session = request.getSession();
		// 各情報の初期化
		long resultTime = 0; // 計測したチャージ時間
		String chargeMessage = ""; // チャージメッセージ
		int chargeTargetTime = 0; // チャージ目標時間
		boolean statusCharge = false; //チャージ状態
		int encountTurn = 1; // ターン数
		String battleMessage = ""; //戦闘メッセージ
		int ikeStartTurn = 0; //イケおじ用ターン数
		int swordCreateTurn = 0; //武器生成用ターン数
		session.setAttribute("resultTime", resultTime);
		session.setAttribute("chargeMessage", chargeMessage);
		session.setAttribute("chargeTargetTime", chargeTargetTime);
		session.setAttribute("statusCharge", statusCharge);
		session.setAttribute("encountTurn", encountTurn);
		session.setAttribute("battleMessage", battleMessage);
		session.setAttribute("ikeStartTurn", ikeStartTurn);
		session.setAttribute("swordCreateTurn", swordCreateTurn);

		if (scene.equals("steppe")) { //初回（草原→小高い丘）のみ
			//セッションスコープに主人公の情報を登録
			HeroDAO herodao = new HeroDAO();
			List<Hero> heroList = herodao.findAll();
			session.setAttribute("heroList", heroList);
		}

		//セッションスコープに武器の情報を登録
		SwordDAO sworddao = new SwordDAO();
		List<Sword> swordList = sworddao.findAll();
		session.setAttribute("swordList", swordList);

		//モンスターを作成(スライム(ID:0)で固定)
		MonsterDAO monsterdao = new MonsterDAO();
		List<Monster> monsterList = monsterdao.findAll();
		/*			//敵をランダムで選ぶ
				int target = new Random().nextInt(monsterList.size());*/
		//場面情報からモンスターを選択
		Monster monster = null;
		switch (scene) {
		case "steppe": //草原→スライム
			monster = monsterList.get(0);
			break;
		case "last": //魔王戦→魔王
			monster = monsterList.get(1);
			break;

		default:
			break;
		}

		//セッションスコープに敵の情報を登録
		session.setAttribute("monster", monster);

		//セッションスコープに場面の情報を登録
		session.setAttribute("scene", scene);

		//場面情報からフォワード先をセレクト
		String url = "";
		switch (scene) {
		case "steppe": //草原→小高い丘
			url = "smallhill.jsp";
			break;
		case "last": //魔王登場→魔王戦
			url = "devilbattle.jsp";
			break;

		default:
			break;
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
