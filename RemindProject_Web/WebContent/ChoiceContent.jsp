<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!-- 로그인한 아이디 바인딩  -->
<% 
	String S_ID = request.getParameter("S_ID"); 
	session.setAttribute("S_ID",S_ID);%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨텐츠 선택 사이트</title>
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
          <div class="ml-auto w-25">
            <nav class="site-navigation position-relative text-right" role="navigation">
              <ul class="site-menu main-menu site-menu-dark js-clone-nav mr-auto d-none d-lg-block m-0 p-0">
                <li class="cta"><a href="#MainPage.html" class="nav-link"><span><%= session.getAttribute("S_ID") %></span></a></li>
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
                <div class="col-lg-6" >
                	<h1 data-aos="fade-up" data-aos-delay="0">Choose Content</h1>
                	<p data-aos="fade-up" data-aos-delay="100">4 Lessons / 12 Week &bullet; 2,193 students &bullet; <a href="#" class="text-white">6 comments</a></p>
                	<div class="row" data-aos="fade-up" data-aos-delay="500">
	                  	<div class="col-sm-4"><a href="SaveWord.jsp"><button class="btn btn-primary btn-pill">단어저장</button></a></div>
	                  	<div class="col-sm-4">
							<form action="MemberServlet">
								<input class="btn btn-primary btn-pill" type="submit" value="학습페이지" id="Use_anchor">
								<input type="hidden" name="command" value="study">
								<input type="hidden" name="id" value=<%=S_ID%>>
							</form>
						</div>
						<div class="col-sm-4">
							<form action="MemberServlet" >
								<input class="btn btn-primary btn-pill" type="submit" value="단어편집" >
								<input type="hidden" name="command" value="modify">
								<input type="hidden" name="id" value=<%=S_ID%>>
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
    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mb-5">
			<div class="mb-5">
              <h3 class="text-black">Description</h3>
              <p class="mb-4">
                <strong class="text-black mr-3">Schedule: </strong>9:30 - 11:00
              </p>
              <p>망각을 극복하는 암기의 기본은 반복 학습에서 시작한다. 이를 뒷받침하는 에빙하우스의 망각 곡선 연구는 시간이 경과함에 따라 어떤 정보를 다시 기억할 때 소요되는 시간의 절약율를 조사한 것이다. 한 번 기억하는 정보는 시간이 지나면 망각이 일어나는데, 에빙 하우스의 일정 시간이 지난 시점에서 얼마나 빠르게 다시 외울 수 있는가를 수치화 했다. 연구에 따르면 20분후에는 58%, 1일 후에는34%, 6일 후에는 25%의 절약율을 나타냈다. 하루 이내에 반복하면 처음보다 34% 이상의 암기 시간을 줄일 수 있다는 뜻이다.</p>
           <div class="row mb-4">
                <div class="col-md-6">
                  <img src="images/img_1.jpg" alt="Image" class="img-fluid rounded">
                </div>
                <div class="col-md-6">
                  <img src="images/img_2.jpg" alt="Image" class="img-fluid rounded">
                </div>
              	</div>
              <strong class="text-black mr-3">Step1  </strong>
              <p>먼저 맨 위에 있는 단어저장 버튼을 이용하여 내가 학습할 단어들을 직접 저장합니다.</p>
              <p>이 웹 사이트는 Papago API를 사용해 영어단어만 입력해 번역 버튼을 누르면 자동으로 그 단어가 해석되어 따로 해당 단어의 뜻을 찾을 필요가 없어 시간을 절약 할 수 있습니다.</p>
              <strong class="text-black mr-3">Step2  </strong>
              <p>단어를 저장후 학습페이지 버튼을 눌러 복습을 실시합니다.</p>
              <p>학습페이지에서 다시하기 버튼을 누르면 10분후 다시 해당 단어가 등장합니다.<br>알맞음 버튼일시 하루가 지나 등장하며, 7일, 1달, 3달, 1년, 3년 간격으로 단어의 등장 기간이 증가하게 됩니다.</p>
             
      		</div>
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