package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

//顯示今天日期與天氣
//設計網址:http://localhost:8080/JavaWeb1113/servlet/TodayServlet
@WebServlet(value = "/servlet/TodayServlet")
public class TodayServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		//設定支援utf-8編碼
		res.setCharacterEncoding("urf-8");
		res.setContentType("text/html; charset=utf-8");
		
		Date today = new Date();
		//格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss E");
		String todayString = sdf.format(today);//2023-11-13 pm 12:10:50 星期一
		
		//天氣
		int temp = new Random().nextInt(10)+10;
		
		//交結果回應給前端
		res.getWriter().print("<H3>");
		res.getWriter().print("今天: "+todayString);
		res.getWriter().print("氣溫: "+ temp);
		res.getWriter().print("</H3>");

	}
}
