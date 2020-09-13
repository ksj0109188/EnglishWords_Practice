<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% String word=request.getParameter("word");
    	String mean=request.getParameter("mean");
    	String S_ID = request.getParameter("S_ID");
    %>
<!DOCTYPE html>
<html>
<head>
<script>
	function fn_modify(){
		var frmMember = document.frmMember_table;
		frmMember.command.value="Modify_Card"
		frmMember.method = "post";
		frmMember.action = "MemberServlet";
		frmMember.submit();
	}
	function fn_delete(){
		var frmMember = document.frmMember_table;
		frmMember.command.value="delete_Card"
		frmMember.method = "post";
		frmMember.action = "MemberServlet";
		frmMember.submit();
	}
</script>
<meta charset="UTF-8">
<title>단어수정</title>
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="fonts/icomoon/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/jquery.fancybox.min.css">
    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="col-lg-5 ml-auto" data-aos="fade-up" data-aos-delay="500">
	<form name="frmMember_table" class="form-box">
	<h3 class="h4 text-black mb-4">Modify</h3>
		<div class="form-group">
		<input class="form-control" type="text" name="word" value="<%=word%>"><br>
		</div>
		<div class="form-group">
		<input class="form-control" type="text" name="mean" value="<%=mean%>"><br>
		</div>
		<div class="row">
			<div class="form-group col-4">
			<input class="btn btn-primary customNextBtn " type="button" name="modify_button" onclick="fn_modify()" value="수정">
			</div>
			<div class="form-group col-4">
			<input class="btn btn-primary customNextBtn" type="button" name="delete_button" onclick="fn_delete()" value="삭제">
			</div>
		</div>
		<input type="hidden" name="first_word" value="<%=word%>">
		<input type="hidden" name="first_mean" value="<%=mean%>">
		<input type="hidden" name="S_ID" value="<%=S_ID%>">
		<input type="hidden" name="command">
		
	</form>
	</div>
<script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/jquery.countdown.min.js"></script>
  <script src="js/bootstrap-datepicker.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.fancybox.min.js"></script>
  <script src="js/jquery.sticky.js"></script>
  <script src="js/main.js"></script>
</body>
</html>