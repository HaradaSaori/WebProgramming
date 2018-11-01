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
import javax.xml.bind.DatatypeConverter;

import dao.UserDao;

/**
 * Servlet implementation class UserNew
 */
@WebServlet("/UserNew")
public class UserNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usernew.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");
		String passwordcon = request.getParameter("passwordcon");
		String username = request.getParameter("username");
		String birthday = request.getParameter("birthday");


		if(!(password.equals(passwordcon) )){
			request.setAttribute("errMsg", "パスワードが一致していません");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usernew.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if(loginid.isEmpty() || password.isEmpty() || passwordcon.isEmpty() || username.isEmpty() || birthday.isEmpty())
        {
			request.setAttribute("errMsg", "必須項目を入力してください");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usernew.jsp");
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

		try {
			userDao.userNewDate(loginid,result,username,birthday);
		} catch (SQLException e) {
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usernew.jsp");
			dispatcher.forward(request, response);
			return;
		}
		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");


	}
}