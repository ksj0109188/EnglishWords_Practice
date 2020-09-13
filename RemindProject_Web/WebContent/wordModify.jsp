<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Model01.ex01.WordVo"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% String S_ID=(String)session.getAttribute("S_ID"); %>
    <% ArrayList<WordVo> list =(ArrayList<WordVo>)request.getAttribute("wordList"); %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	window.onload = function() {
		var input= document.getElementById("searchbar_focus").focus();
	}
	function fn_search(){
		var frmMember = document.frmMember;
		var searchWord = frmMember.search_Word.value;
		if(searchWord.length==0 || searchWord==""){
			alert('찾을 단어를 입력해주세요.');
		}else{
			frmMember.method = "post";
			frmMember.action = "MemberServlet";
			frmMember.submit();
		}
	}
	</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>WordModify</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    
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
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
	<div class="site-wrap">

    <div class="site-mobile-menu site-navbar-target">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>
   
    
    <header class="site-navbar py-4 js-sticky-header site-navbar-target" role="banner">
      
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="site-logo mr-auto w-25"><a href="MainPage.html">Remind</a></div>
          
          <div class="mx-auto text-center">
            <nav class="site-navigation position-relative text-right" role="navigation">
              <ul class="site-menu main-menu js-clone-nav mx-auto d-none d-lg-block  m-0 p-0">
                <li><a href="MainPage.html" class="nav-link">Home</a></li>
                <li><a href="ChoiceContent.jsp?S_ID=<%=S_ID %>"  class="nav-link">Content</a></li>
              </ul>
            </nav>
          </div>
          
          <div class="ml-auto w-25">
            <nav class="site-navigation position-relative text-right" role="navigation">
              <ul class="site-menu main-menu site-menu-dark js-clone-nav mr-auto d-none d-lg-block m-0 p-0">
                <li class="cta"><a href="#contact-section" class="nav-link"><span><%= session.getAttribute("S_ID") %></span></a></li>
              </ul>
            </nav>
            <a href="#" class="d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black float-right"><span class="icon-menu h3"></span></a>
          </div>
        </div>
      </div>
      
    </header>

    <div class="intro-section single-cover" id="home-section">
      
      <div class="slide-1 " style="background-image: url('images/img_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-12">
              <div class="row justify-content-center align-items-center text-center">
                <div class="col-lg-6">
                  <h1 data-aos="fade-up" data-aos-delay="0">Word Modify</h1>
                  <p data-aos="fade-up" data-aos-delay="100">4 Lessons / 12 Week &bullet; 2,193 students &bullet; <a href="#" class="text-white">6 comments</a></p>
               		 <div class="row" data-aos="fade-up" data-aos-delay="500">
               		 	<div class="col-sm">
	               		 <form name="frmMember">
							<input class="form-control" type="text" id="searchbar_focus" name="search_Word" placeholder="Search MyWord"><br>
							<input class="btn btn-primary btn-pill" type="button" value="찾기" onclick="fn_search()">
							<input type="hidden" name="command" value="find">
							<input type="hidden" name="S_ID" value="<%=S_ID %>">
						</form>
						</div>
					</div>
				</div>
               </div>
             </div>
           	</div>            
          </div>
        </div>
      </div>
    </div>

	<form name="frmMember_table" data-aos="fade">
	<div class="form-group row" align="center">
	<input type="hidden" name="command">
	<input type="hidden" name="S_ID" value="<%=S_ID %>">
	<table align="center">
 		<tr>
 			<td><p data-aos="fade-up" data-aos-delay="100">Front_Card</p></td>
 			<td><p data-aos="fade-up" data-aos-delay="100">Back_Card</p></td>
 			<td><p align="center" data-aos="fade-up" data-aos-delay="100">저장날짜</p></td>
 		</tr>
		<%for(WordVo s:list){%>
			<tr>
				<div class="col-md-4 mb-3 mb-lg-0">
				<td><input class="form-control" type="text" name="word" value="<%= s.getwORD() %>"></td>
				</div>
				<div class="col-md-4">
				<td><input class="form-control" type="text" name="mean" value="<%= s.getMean() %>"></td>
				</div>
				<td><%= s.getSaveDate() %></td>
				<td>
				<div class="col-md">
				<a href="wordModify_window.jsp?word=<%=s.getwORD()%>&mean=<%=s.getMean()%>&S_ID=<%=S_ID %>" onClick="window.open(this.href, '', 'width=400, height=430'); return false;">수정</a></td>
				</div>
			</tr>
		<%}%>
	</table>
	</div> 
</form>

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