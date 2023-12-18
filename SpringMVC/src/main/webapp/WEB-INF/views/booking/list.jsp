<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking List</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
	</head>
	<body>
		
	 	Booking List
	 	<table class = "pure-table pure-table-bordered"> 
	 	<thead>
	 		<tr><th>cancel</th><th>bookingId</th><th>roomId</th><th>name</th><th>date</th></tr>
	 	</thead>
	 	<tbody>
	 		<c:forEach var="booking" items="${bookings}">
		 		<tr>
		 			<form method="post" action="/SpringMVC/mvc/booking/${booking.bookingId}/updateName">
			 			<td><a href="javascript:void(0)" title="點擊即可取消" 
			 			onclick="location.href='/SpringMVC/mvc/booking/cancelBooking/${booking.bookingId }'">
			 			cancel
			 			</a></td>
			 			<td>${booking.bookingId }</td>
			 			<td>${booking.roomId }</td>
			 			<td>
			 				<input type="text" id="name${booking.bookingId}" name="name" 
			 				value="${ booking.name }" size="10">
			 				<button type="button" 
			 				onclick="location.href='/SpringMVC/mvc/booking/${booking.bookingId}/updateName?name='+document.getElementById('name${booking.bookingId}').value">
			 				更名</button>
			 				<button type="submit">更名2</button>
			 			</td>
			 			<td>${booking.date }</td>
		 			</form>
		 		</tr>
	 		</c:forEach>
	 	</tbody>
	 	</table>
	</body>
	<script>
		document.getElementById('cancelBooking').addEventListener('click', function() {
		    var bookingId = document.getElementById('bookingId').value;
		
		    // 建構取消預約的URL
		    var cancelBookingUrl = '/SpringMVC/mvc/booking/cancelBooking/' + bookingId;
		
		    // 跳轉頁面
		    window.top.resultFrame.location.href = cancelBookingUrl;
		});
	</script>
</html>