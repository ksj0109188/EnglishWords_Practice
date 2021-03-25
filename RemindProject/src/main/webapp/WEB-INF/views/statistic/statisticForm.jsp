<%--
  Created by IntelliJ IDEA.
  User: kim
  Date: 2021/02/18
  Time: 7:06 오후창
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="countWord" value="${staMap.countWord}"/>

<html>
<head>
    <title>회원가입창</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css"/>
    <script src="https://d3js.org/d3.v3.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>
        function searchStatistic() {
            var searchText = document.getElementById("searchText");
            $.ajax({
                method: "get",
                url: "${contextPath}/statistic/search/" + searchText.value,
                success: function (data) {
                    if (data == "Empty") {
                        alert("해당하는 단어가 없습니다.");
                    } else {
                        drawChart(data.word, data.wrongCount, data.correctCount);
                    }
                }, error: function () {
                    alert("잠시후 다시 시도해주세요.");
                }
            });
        }

        function drawChart(word, wrongCount, correctCount) {
            var pieData = {"틀린 횟수": wrongCount, "맞은 횟수": correctCount};
            var chartDonut = c3.generate({
                bindto: "#piechart",
                data: {
                    json: [pieData],
                    keys: {
                        value: Object.keys(pieData),
                    },
                    type: "donut",
                },
                donut: {
                    title: word
                }
            });
            var chartDonutColors = chartDonut.data.colors();
            $("#wrongCount").html("틀릿 횟수:"+wrongCount);
            $("#correctCount").html("맞은 횟수:"+correctCount);
            $("#wrongCount").css("color", chartDonutColors["틀린 횟수"]);
            $("#correctCount").css("color", chartDonutColors["맞은 횟수"]);
        }

    </script>
</head>
<body>

<div>
    <input id="searchText" type="text" placeholder="내가 저장한 단어를 입력하면 틀림과 맞은 횟수가 나옵니다.">
    <input type="button" value="검색" onclick="searchStatistic()">
</div>

<div>
    <span>저장한 단어의 총개수:${countWord}</span>
</div>
<div>
    <div id="pieChart"></div>
    <div style="text-align:center">
        <span id="wrongCount"></span>
        <span id="correctCount"></span>
    </div>
</div>
</body>
</html>
