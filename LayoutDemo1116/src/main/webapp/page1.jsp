<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="mx-5 mt-5">
	<form action="./page1" method="post" onsubmit="return check();">
	<h4 class="fw-bold text-center">假單申請</h4>
		<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Email address</label>
		    <input type="email" class="form-control" id="email" 
		    name="email" placeholder="name@example.com">
		    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
		</div>
		<div class="mb-3">
		  <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
		  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
		</div>
		<div class="d-flex justify-content-center">
			<button type="submit" class="btn btn-primary me-2">送出</button>
			<button type="reset" class="btn btn-success ms-3">清除</button>
		</div>
	</form>
</div>

<script>
	
	function check() {
		let email = $("#email").val();
		if(email == ''){
			alert("email未輸入");
			return false;
		}
		console.log("執行check()"+email);
		return true;
	}
</script>

<%@ include file="/WEB-INF/view/footer.jsp" %>