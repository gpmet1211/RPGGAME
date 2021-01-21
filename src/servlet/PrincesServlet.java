package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrincesServlet
 */
@WebServlet("/PrincesServlet")
public class PrincesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nothing
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		String princesName = request.getParameter("princesname");
		// フォワード用
		String url;

		if (princesName.equals("ニョッフォリアミンテリッーヌコッテリーッヌ")) {  /*！！！！！ プリンセス名有　！！！*/
			// 正しい場合は魔王戦へ
			url = "/forwordlast.jsp";
		}else {
			// 間違っている場合、戻る
			url = "/princes_dead.jsp";
		}
		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
