package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet/hello")
public class HelloServlet extends HttpServlet{
	//doGet取資源 doPost傳送資料 doPut修改資料 doDelete刪除
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//編碼
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		//String name = "Ann";
		//範例:http://localhost:8080/JavaWeb1113/servlet/hello?name=Mary
		//取得網址列上的請求參數
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		PrintWriter out = resp.getWriter();
		out.println("嗨 ! " + name +"<br>");
		out.println("年齡: " + age);
	}
	
	
}
