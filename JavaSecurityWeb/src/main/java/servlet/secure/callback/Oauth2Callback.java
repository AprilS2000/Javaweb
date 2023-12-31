package servlet.secure.callback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import servlet.util.OAuth2Util;

@WebServlet(value = "/secure/callback/oauth2")
public class Oauth2Callback  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String code = req.getParameter("code");
		resp.getWriter().println("code: " + code);
		
		// 已有授權碼(code)之後，可以跟 Github 來得到 token (訪問令牌)
		// 有了 token 就可以得到客戶的公開資訊例如: userInfo
		
		// 1. 根據 code 得到 token
		String token = OAuth2Util.getGitHubAccessToken(code);
		resp.getWriter().println("token: " + token);
		
		// 2. 透過 token 裡面的 access_token 來取的用戶資訊
		String accessToken = OAuth2Util.parseAccessToken(token);
		resp.getWriter().println("accessToken");
		
		// 3. 得到用戶在 Github 上的公開資料
		String userInfo = OAuth2Util.getUserInfoFromGitHub(accessToken);
		resp.getWriter().println("userInfo: " + userInfo);
		
		// 4. 利用 JSONObject 來分析資料
		JSONObject userInfoObject = new JSONObject(userInfo);
		resp.getWriter().println("login: " + userInfoObject.getString("login"));
		resp.getWriter().println("id: " + userInfoObject.getInt("id"));
		resp.getWriter().println("email: " + userInfoObject.getString("email"));
		resp.getWriter().println("name: " + userInfoObject.getString("name"));
		resp.getWriter().println("bio: " + userInfoObject.getString("bio"));
		
		// 5. 檢查會員資料表中是否有此人, 若無則將該會員資料自動新增到資料表
		
		// 6. 新增成功就自行自動登入 (例如: 建立 user 物件並存放到 session 中)
		
		// 7. 重導到登入成功頁面
	}

	
}
