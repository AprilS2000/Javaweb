<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網－購物車</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu.jspf" %>
		
		<!-- 購物車列表 -->
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>John 的購物車</legend>
					<table class="pure-table pure-table-bordered">
						<thead>
							<tr>
								<th>序號</th><th>商品</th><th>價格</th><th>單位</th><th>數量</th><th>小計</th><th>修改數量</th><th>刪除</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td><td>雞腳凍</td><td>50</td><td>包</td>
								<td><input type="number" value="5" /></td>
								<td>250</td>
								<td><button class="pure-button button-success">修改</button></td>
								<td><button class="pure-button button-error">刪除</button></td>
							</tr>
							<tr>
								<td>2</td><td>可樂</td><td>100</td><td>打</td>
								<td><input type="number" value="3" /></td>
								<td>300</td>
								<td><button class="pure-button button-success">修改</button></td>
								<td><button class="pure-button button-error">刪除</button></td>
							</tr>
							<tr>
								<td>3</td><td>漢堡</td><td>250</td><td>箱</td>
								<td><input type="number" value="2" /></td>
								<td>500</td>
								<td><button class="pure-button button-success">修改</button></td>
								<td><button class="pure-button button-error">刪除</button></td>
							</tr>
							<tr>
								<td colspan="5" align="right">總計</td><td>1050</td>
								<td colspan="2">
									<button type="button" class="pure-button pure-button-primary" onclick="window.location.href='./finish.jsp'">結帳</button></td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</form>
		</div>
	</body>
</html>