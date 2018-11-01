package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import common.Common;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserF5
 */
@WebServlet("/UserF5")
public class UserF5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserF5() {
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userf5.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String password = request.getParameter("password");
		String passwordcon = request.getParameter("passwordcon");
		String name = request.getParameter("name");
		String birth_date = request.getParameter("birth_date");
		String loginid = request.getParameter("loginid");

		if(!(password.equals(passwordcon) )){
			request.setAttribute("errMsg", "パスワードが一致していません");

			User user = new User(loginid,name,Common.c_date(birth_date));
			request.setAttribute("userdata",user);



			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userf5.jsp");
			dispatcher.forward(request, response);
			return;

		}else if(name.isEmpty() || birth_date.isEmpty())
        {
			request.setAttribute("errMsg", "必須項目を入力してください");

			User user = new User(loginid,name,Common.c_date(birth_date));
			request.setAttribute("userdata",user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userf5.jsp");
			dispatcher.forward(request, response);
			return;

        }

		UserDao userDao = new UserDao();

		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
		} catch (NoSuchAlgorithmException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		String result = DatatypeConverter.printHexBinary(bytes);


		if(password.isEmpty() && passwordcon.isEmpty())
        {
			try {
				userDao.UserF5pass(name, birth_date,loginid);
			} catch (SQLException e) {
				e.printStackTrace();

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userf5.jsp");
				dispatcher.forward(request, response);
				return;
			}
        } else {

			try {
				userDao.UserF5(result, name, birth_date,loginid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}
