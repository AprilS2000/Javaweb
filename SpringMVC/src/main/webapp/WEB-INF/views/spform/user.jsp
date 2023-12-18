<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<!-- 
設計一表單
id: 序號 (input hidden)
姓名: name (input text)
年齡: age (input number)
生日: birth (input date)
學歷: education [小學, 國中, 高中, 大學, 研究所]
性別: sex [男, 女] radio button
興趣: interest [看書, 跑步, 看電影] checkboxs
履歷: resume (textarea)

 -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Spring Form</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
		<style type="text/css">
			.element-margin{
				margin-right: 2px;
				margin-left: 5px;
			}
			.error {
				color: red;
			}
		</style>
		<script type="text/javascript">
			function deleteUser(userId) {
				const url = '${pageContext.request.contextPath}/mvc/user/' + userId;
				if(confirm('是否要刪除 ?')) {
					fetch(url, {method: 'DELETE'})
					.then(response => {
						//console.log(response);
						//console.log(response.redirected);
						if(response.ok || response.redirected) {
							console.log(response);
							// 刪除成功, 更新網頁
							location.href = '${pageContext.request.contextPath}/mvc/user/';
						} else {
							console.log('delete fail');
						}
					})
					.catch(error => {
						console.log('delete error: ', error);
					});
				}
			}
		</script>
	
	</head>
	<body style="padding: 15px">
	
		<table>
			<tr>
			  <!-- User Spring Form -->
			  <td valign="top" style="padding: 10px">
			  	<%@include file="userform.jspf" %>
			  </td>
			  <!-- User Spring List-->
			  <td valign="top" style="padding: 10px">
			  	<%@include file="userlist.jspf" %>
			  </td>
			</tr>
		</table>
		
	
	</body>
</html>