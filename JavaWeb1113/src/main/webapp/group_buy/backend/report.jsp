<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網後台首頁</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu_backend.jspf" %>
		
		<div style="padding: 15px">
			<table >
				<tr>
					<td valign="top" style="padding-left: 15px">
						<!-- report list -->
						<%@include file="./report_list.jspf" %>
					</td>
						<!-- report detail -->
					<td valign="top" style="padding-left: 15px">
						<%@include file="./report_detail.jspf" %>
					</td>
				</tr>
			</table>
		</div>
		
	</body>
</html>