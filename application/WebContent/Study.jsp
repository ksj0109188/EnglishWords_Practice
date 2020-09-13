<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Model01.ex01.WordVo"%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<% String S_ID=(String)session.getAttribute("S_ID"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Word_Study</title>
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
	<script>
		window.onload=function(){
			var input = document.getElementById("focus").focus();
			var form = document.from_card;
			<% ArrayList<WordVo> list =(ArrayList<WordVo>)request.getAttribute("wordList");
			int switch_count=1;
			for(WordVo s:list){ %>
				form.card_front.value="<%=s.getwORD()%>"; 
				form.card_back.value="<%=s.getMean()%>"; 		
			<%}%>
		}
		function fn_again(){
			var form = document.from_card;
			form.command.value="again";
			form.method = "post";
			form.action = "MemberServlet";
			form.submit();
			window.location.reload(true);
		}
 		function fn_currect(){
 			var form = document.from_card;
 			form.command.value="currect";
			form.method = "post";
			form.action = "MemberServlet";
			form.submit();
			window.location.reload(true);
		} 
</script>
<meta charset="UTF-8">
<title>공부 페이지</title>
</head>
<body>
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
                <li><a href="javascript:history.go(-1)" class="nav-link">Content</a></li>
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
                  <h1 data-aos="fade-up" data-aos-delay="0">단어저장</h1>
                  <p data-aos="fade-up" data-aos-delay="100">4 Lessons / 12 Week &bullet; 2,193 students &bullet; <a href="#" class="text-white">6 comments</a></p>
                </div>

                
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
<div class="site-section pb-0">

      <div class="future-blobs">
        <div class="blob_2">
          <img src="images/blob_2.svg" alt="Image">
        </div>
        <div class="blob_1">
          <img src="images/blob_1.svg" alt="Image">
        </div>
      </div>
      <div class="container">
        <div class="row mb-5 justify-content-center" data-aos="fade-up" data-aos-delay="">
          <div class="col-lg-7 text-center">
            <h2 class="section-title">Study World!</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 ml-auto align-self-start"  data-aos="fade-up" data-aos-delay="100">

            <div class="p-4 rounded bg-white why-choose-us-box">
				<form name="from_card" class="form-box">
					<div class="form-group">
						<input class="form-control" type="text" name="card_front">
					</div>
					<div class="form-group">
						<input  class="form-control" type="text" name="card_back">
					</div>
					<div class="row">
						<div class="form-group col-sm-4">
							<input class="btn btn-primary btn-pill" type="button" name="again" onclick="fn_again()" value="다시" id="focus">
						</div>
						<div class="form-group col-sm-4">
							<input class="btn btn-primary btn-pill" type="button" name="currect" onclick="fn_currect()" value="알맞음">
						</div>
					</div>
					<input type="hidden" name="command">
					<input type="hidden" name="id" value=<%=S_ID%>>
				</form>
            </div>


          </div>
          <div class="col-lg-7 align-self-end"  data-aos="fade-left" data-aos-delay="200">
            <img src="images/person_transparent.png" alt="Image" class="img-fluid">
          </div>
        </div>
      </div>
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