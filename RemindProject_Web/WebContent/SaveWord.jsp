<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String S_ID=(String)session.getAttribute("S_ID"); 
	session.setAttribute("S_ID",S_ID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>SaveWord!</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script>
window.onload = function() {
	  var input = document.getElementById("send_text").focus();
	}
function fn_SendWord() {	
		var frmMember = document.word_fm;
		var word = frmMember.word.value;
		var mean = frmMember.mean.value;

		if(word.length==0 || word==""){
			alert("저장할 단어를 입력해 주세요");
		}
		else if(mean.length==0 || mean == ""){
			alert("저장할 단어의 뜻을 입력해주세요.");
		}
		else
			{
			frmMember.method = "post";
			frmMember.action = "MemberServlet";
			frmMember.submit();
			}
	}
	
function fn_PaPaClick() {
	var test = {
			"original_str": $("#send_text").val()
			};
		jsonSend(test);
}

function jsonSend(test){
	$.ajax({
		type: "POST",
		url: "nmtReturnResult",
		data : test,
		success : function (data){
			var result_obj = JSON.parse(data);
			$("#result_text").val(result_obj.message.result.translatedText);
		},
		error:function(e){
			console.log(e);
			alert("실패했습니다.");
		}
	});
}
function fn_redirect(){
	window.location.href = "ChoiceContent.jsp";
}
</script>
</head>
<body>
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
                  <h1 data-aos="fade-up" data-aos-delay="0">단어저장</h1>
                  <p data-aos="fade-up" data-aos-delay="100">4 Lessons / 12 Week &bullet; 2,193 students &bullet; <a href="#" class="text-white">6 comments</a></p>
                </div>

                
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
<div class="site-section" id="programs-section">
      <div class="container">
        <div class="row mb-5 justify-content-center">
          <div class="col-lg-7 text-center"  data-aos="fade-up" data-aos-delay="">
            <h2 class="section-title">SaveWord!</h2>
            <p>저장할 단어를 입력해 보세요.</p><p> 번역 버튼을 누르면 따로 해당 단어의 뜻을 찾을 필요없이 바로 번역이 됩니다!</p>
          </div>
        </div>
        <div class="row mb-5 align-items-center">
          <div class="col-lg-7 mb-5" data-aos="fade-up" data-aos-delay="100">
            <img src="images/undraw_youtube_tutorial.svg" alt="Image" class="img-fluid">
          </div>
          <div class="col-lg-4 ml-auto" data-aos="fade-up" data-aos-delay="200">
            <h2 class="text-black mb-4">PaPago</h2>
            <p class="mb-4">네이버 PaPago API를 통해 신속한 번역을 제공합니다.</p>

            <div class="d-flex align-items-center custom-icon-wrap mb-3">
            
              	<form name="word_fm" class="form-box">
              		<div class="form-group">
						<input class="form-control" type="text" name="word" id="send_text">
					</div>
					<div class="form-group">
						<input class="form-control" type="text" name="mean" id="result_text">
					</div>
					<div class="row">
						<div class="form-group col-sm-6">
								<input class="btn btn-primary btn-pill" type ="button" value="저장" onclick="fn_SendWord()">
							</div>
							
							<div class="form-group col-sm-6">
								<input class="btn btn-primary btn-pill" type ="button" value="번역" onclick="fn_PaPaClick()">
							</div>
						</div>
					</div>
					<input type="hidden" name="command" value="saveWord">
					<input type="hidden" name="S_ID" value=<%=S_ID%>>
				</form>
				
            </div>

            </div></div></div></div>
	
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