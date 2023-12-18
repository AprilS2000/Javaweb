package mvc.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 會議室預訂系統(Web API)
 * 假設您正在為一家公司開發一個會議室預訂系統。您需要實現一個控制器，該控制器可以處理會議室的預訂請求、取消請求以及查詢當前預訂狀態。
 * 
 * 功能要求：
 * -----------------------------------------------------------------------------------------------
 * 1.預訂會議室：
 * 路徑：/booking/bookRoom
 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
 * 返回：預訂成功或失敗的消息
 * 透過AOP
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=101&name=Tom&date=2023-12-04
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=102&name=Mary&date=2023-12-05
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=201&name=Jack&date=2023-12-06
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=403&name=Rose&date=2023-12-06
 * 
 * -----------------------------------------------------------------------------------------------
 * 2.取消預訂：
 * 路徑：/booking/cancelBooking/{bookingId}
 * 參數：預訂ID (bookingId)
 * 返回：取消成功或失敗的消息
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/cancelBooking/1
 * 
 * -----------------------------------------------------------------------------------------------
 * 3.查看所有預訂：
 * 路徑：/booking/{bookingId}/viewBookings
 * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/viewBookings
 * 
 * CR 需求變更
 * 4. 修改預約人
 * 路徑：/booking/updateName
 * 返回是否成功
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/booking/updateName?name=Tom
 * */

@Controller
@RequestMapping("/booking")
public class BookingController {
	/**
	 * 預約紀錄
	 +-----------+--------+-------+------------+
	 | bookingId | roomId | name  |    date    |
	 +-----------+--------+-------+------------+
	 |     1     |  101   |  Tom  | 2023-12-04 |
	 |     2     |  102   |  Mary | 2023-12-05 |
	 +-----------+--------+-------+------------+
	*/
	private static List<Map<String, Object>> bookings = new CopyOnWriteArrayList<>();
	
	//預約號碼: 每次可用bookingIdCount.incrementAndGet() 來取得
	private AtomicInteger bookingIdCount = new AtomicInteger(0);
	
	/**
	 * 1.預訂會議室：
	 * 路徑：/booking/bookRoom
	 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
	 * 返回：預訂成功或失敗的消息
	 * 
	 * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=101&name=Tom&date=2023-12-04
	 */
	
	//@GetMapping(value = "/bookRoom", produces = "text/plain, charset=utf-8")
	@RequestMapping(value = "/bookRoom", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String bookingBookRoom(@RequestParam(name = "roomId") Integer roomId,
				@RequestParam(name = "name") String name, 
				@RequestParam(name = "date") String date) {
		
		//判斷會議室是否被預約
		boolean isBooked = bookings.stream().
				anyMatch(booking -> booking.get("roomId").equals(roomId) &&
						booking.get("date").equals(date));
		
		if(isBooked) {
			return String.format("預定失敗 (roomId = %d date = %s 已被預約)", roomId, date);
		}
			//預約號碼
		int bookingId = bookingIdCount.incrementAndGet();
		    //預約資訊
		Map<String, Object> bookRoom = new LinkedHashMap<>();
		bookRoom.put("bookingId", bookingId);
		bookRoom.put("roomId", roomId);
		bookRoom.put("name", name);
		bookRoom.put("date", date);
		//bookRoom.put(date, new SimpleDateFormat("yyyy-MM-dd").parse(date));
		//放到預約集合
		bookings.add(bookRoom);
			
		return String.format("預定成功 (預約號碼 = %d)", bookingId);
	}
		
	
	
	/** 
	 * 2.取消預訂：
	 * 路徑：/booking/cancelBooking/{bookingId}
	 * 參數：預訂ID (bookingId)
	 * 返回：取消成功或失敗的消息
	 * 範例：http://localhost:8080/SpringMVC/mvc/booking/cancelBooking/1 
	 */
	@GetMapping(value = "/cancelBooking/{bookingId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	
	public String cancelBooking(@PathVariable("bookingId") Integer bookingId) {
		//根據bookingId 透過 bookings 集合找到該筆 booking 紀錄
		Optional<Map<String, Object>> mapOpt =  bookings.stream()
				.filter(booking -> booking.get("bookingId").equals(bookingId)).findFirst();
		
		if(mapOpt.isPresent()) {
			Map<String, Object> booking = mapOpt.get();
			bookings.remove(booking);
			return String.format("取消成功 (預約號碼 = %d)", bookingId);
		}
		return String.format("取消失敗 (預約號碼 = %d)", bookingId);
	}
	
	@GetMapping(value = "/autoCancelFirstBooking/{bookingId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String autoCancelFirstBooking() {
		if(!bookings.isEmpty()) {
			bookings.remove(0);
			return "自動取消第一筆成功";
		}
		return "自動取消第一筆失敗";
	}
	
	/**
	 * 3.查看所有預訂：
	 * 路徑：/booking/viewBookings
	 * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
	 * -----------------------------------------------------------------------------------------------
	 * 範例：http://localhost:8080/SpringMVC/mvc/booking/viewBookings
	 * */
	
	@GetMapping(value = "viewBookings", produces = "text/plain;charset=utf-8")
	//@ResponseBody
	public String bookingViewBooking(Model model) {
		//StringBuilder sb = new StringBuilder("預約紀錄:\n");
		//bookings.forEach(book -> sb.append(book).append("\n"));

		//ModelAndView mv = new ModelAndView();
		//jsp + model 資料配置好
		//mv.addObject("bookings", bookings);
		//mv.setViewName("/WEB-INF/views/booking/list.jsp");
		//return mv;
		model.addAttribute("bookings", bookings);
		return "booking/list";
	}
	
	/**
	 * 4. 修改預約人
	 * 路徑：/booking/updateName
	 * 返回是否成功
	 * -----------------------------------------------------------------------------------------------
	 * 範例：http://localhost:8080/SpringMVC/mvc/booking/1/updateName?name=Tom
	 * */
	
	@GetMapping(value = "/{bookingId}/updateName", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateName(@PathVariable("bookingId") Integer bookingId, 
			@RequestParam("name") String newName) {
		Optional<Map<String, Object>> mapOpt =  bookings.stream()
				.filter(booking -> booking.get("bookingId").equals(bookingId)).findFirst();
		if(mapOpt.isEmpty()) {
			return "查無此預約，修改失敗";
		}
		Map<String, Object> booking = mapOpt.get();
		booking.put("name", newName);
		return "預約人修改成功"+newName;
	}
}

