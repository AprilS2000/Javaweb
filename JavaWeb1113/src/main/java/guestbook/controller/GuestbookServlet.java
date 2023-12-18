package guestbook.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.model.Guestbook;
import guestbook.model.GuestbookDao;
import guestbook.model.GuestbookDaoInMemory;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet{
	private GuestbookDao guestbookDao;
	{
		guestbookDao = new GuestbookDaoInMemory();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取得所有留言紀錄
		List<Guestbook> guestbooks = guestbookDao.readAll();
		
		// 重導到 /WEB-INF/view/guestbook.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/guestbook/guestbook.jsp");
		req.setAttribute("guestbooks", guestbooks);
		rd.forward(req, resp);
	}


	//表單新增
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String nickname = req.getParameter("nickname");
		Integer age =  Integer.parseInt(req.getParameter("age")); //直接轉型int
		String sex = req.getParameter("sex");
		String message = req.getParameter("message");
		
		//建立 Guestbook 物件
		Guestbook guestbook = new Guestbook();
		int maxId = guestbookDao.readAll()   //取得所有guestbook集合
								.stream()	//轉為stream串流，以利分析
								//.mapToInt(gb -> gb.getId())
								.mapToInt(Guestbook::getId)
								.max()
								.orElse(0); //如果資料為空，則回傳1
		guestbook.setId(maxId+1);
		guestbook.setNickname(nickname);
		guestbook.setAge(age);
		guestbook.setSex(sex);
		guestbook.setMessage(message.trim());//去除空白
		guestbook.setDate(new Date());
		
		//加到資料庫中
		guestbookDao.create(guestbook);
		
		//重導至新增完成頁面 /WEB-INF/view/guestbook/guestbook_result.jsp
		//RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/guestbook/guestbook_result.jsp");
		//rd.forward(req, resp);
		
		//令瀏覽器根據下列網址自行重導
		resp.sendRedirect("http://localhost:8080/JavaWeb1113/guestbook");
	}

	
}
