package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    public void userNewDate(String loginid, String password, String username, String birthday) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

         // INSERT文を準備
            String sql = "INSERT INTO user(login_id, password, name, birth_date, create_date, update_date) VALUES(?,?,?,?,now(),now())";

         // INSERTを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginid);
            pStmt.setString(2, password);
            pStmt.setString(3, username);
            pStmt.setString(4, birthday);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }
    public User userData(String id) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

    //SELECT文
	String sql = "SELECT login_id, name, birth_date, create_date, update_date FROM user WHERE id = ?";

	// SELECTを実行し、結果表（ResultSet）を取得
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, id);
	ResultSet rs = pStmt.executeQuery();

	User user  = null;

    while (rs.next()) {
        String login_id = rs.getString("login_id");
        String name = rs.getString("name");
        Date birth_date = rs.getDate("birth_date");
        String create_date = rs.getString("create_date");
        String update_date = rs.getString("update_date");

        user = new User(Integer.parseInt(id), login_id, name, birth_date, null, create_date, update_date);

    }


    pStmt.close();

    return user;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
        return null;
    }

    public void UserF5(String password, String name, String birth_date, String loginid) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

         // UPDATE文を準備
            String sql = "UPDATE user SET password = ?,name = ?,birth_date = ?,update_date = now() WHERE login_id = ?";

         // UPDATEを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, password);
            pStmt.setString(2, name);
            pStmt.setString(3, birth_date);
            pStmt.setString(4, loginid);
            pStmt.executeUpdate();

            pStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    public void Userdelete(String loginid) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

         // DELETE文を準備
            String sql = "DELETE FROM user WHERE login_id = ?";

         // DELETEを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginid);
            pStmt.executeUpdate();

            pStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
