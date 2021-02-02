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

import dao.ClearDAO;
import dao.MonsterDAO;
import model.Clear;

/**
 * Servlet implementation class StartServlet
 */
@WebServlet("/MeasureServlet")
public class MeasureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int rank = 3; //一覧で表示するランキング順位

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータからゲームエンドフラグの取得
		String endFlag = request.getParameter("endFlag");
		// フォワード先url
		String url = "";
		//セッション確立
		HttpSession session = request.getSession();
		//変数の宣言
		long gameStartTime;

		if (endFlag == null) { //ゲーム開始時
			// ゲーム開始時刻を取得
			gameStartTime = System.currentTimeMillis();
			// 情報をセッションスコープに保存
			session.setAttribute("gameStartTime", gameStartTime);
			// リクエストからゲームモード情報を取得し、セッションスコープに保存
			String mode = request.getParameter("mode");
			session.setAttribute("mode", mode);
			// モードを渡してDBのボス情報を変更（UPDATE)
			MonsterDAO monsterDAO = new MonsterDAO();
			monsterDAO.updateDevil(mode);
			// フォワード先の指定
			url = "prologue.jsp";
		} else if (endFlag.equals("end")) { //エンディング前
			//セッションから敵情報を取得
			gameStartTime = (long) session.getAttribute("gameStartTime");
			// ゲーム終了時刻を取得
			long gameEndTime = System.currentTimeMillis();
			long gameClearTime = gameEndTime - gameStartTime;
			// 情報をセッションスコープに保存
			session.setAttribute("gameClearTime", gameClearTime);
			//クリアリストを作成し、セッションスコープに保存
			ClearDAO cleardao = new ClearDAO();
			List<Clear> clearList = cleardao.findRanker(rank);
			long clearAvg = cleardao.findAvg();
			request.setAttribute("clearList", clearList);
			request.setAttribute("clearAvg", clearAvg);
			// フォワード先の指定
			url = "ending.jsp";
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

		//セッション確立
		HttpSession session = request.getSession();
		//セッションからクリア時間を取得
		long gameClearTime = (long) session.getAttribute("gameClearTime");
		//リクエストパラメータからゲームエンドフラグの取得
		String userName = request.getParameter("userName");

		// 入力値チェック
		if (userName != null && userName.length() != 0) {

			//クリア情報の作成
			Clear clear = new Clear(userName, gameClearTime);
			//クリア情報をDBに登録する
			ClearDAO cleardao = new ClearDAO();
			cleardao.create(clear);
			//クリアリストを作成し、セッションスコープに保存
			List<Clear> clearList = cleardao.findRanker(rank);
			long clearAvg = cleardao.findAvg();
			request.setAttribute("clearList", clearList);
			request.setAttribute("clearAvg", clearAvg);
			//クリア時間を0にし、セッションスコープに保存（複数登録防止のために必要）
			gameClearTime = 0;
			session.setAttribute("gameClearTime", gameClearTime);
		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザ名が入力されていません");
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("ending.jsp");
		dispatcher.forward(request, response);
	}

}
