package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class Userdelete
 */
@WebServlet("/Userdelete")
public class Userdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userdelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
				HttpSession session = request.getSession();
				if (null == session.getAttribute("userInfo")){
					// ログイン画面へ遷移(リダイレクト).
					response.sendRedirect( "LoginServlet" );
					return;
					}

		// URLからGETパラメータとしてIDを受け取る
				String id = request.getParameter("id");

				// 確認用：idをコンソールに出力
			       System.out.println(id);


				//idを引数にして、idに紐づくユーザ情報を出力する
			       UserDao userDao = new UserDao();
				   User userdata = userDao.userData(id);

				   // ユーザ情報をリクエストスコープにセットしてjspにフォワード
				   request.setAttribute("userdata",userdata);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userdelete.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginid = request.getParameter("loginid");

		UserDao userDao = new UserDao();
		userDao.Userdelete(loginid);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}
