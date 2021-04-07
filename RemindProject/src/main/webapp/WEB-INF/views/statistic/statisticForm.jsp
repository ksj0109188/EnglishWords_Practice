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
                        for (var i = 0; i < data.length; i++) {
                            drawChart(i,data[i].word, data[i].savedDate, data[i].wrongCount, data[i].correctCount);
                        }
                    }
                }, error: function () {
                    alert("잠시후 다시 시도해주세요.");
                }
            });
        }

        function drawChart(index, word, savedDate, wrongCount, correctCount) {
            var pieChartDiv = document.createElement("div");
            pieChartDiv.setAttribute("id","piechart"+index);
            pieChartDiv.setAttribute("class","form-group mb-3");
            var cardBody = document.getElementById("cardBody");
            cardBody.appendChild(pieChartDiv);

            var pieData = {"틀림": wrongCount, "맞음": correctCount};
            var chartDonut = c3.generate({
                bindto: "#piechart"+index,
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

            var piechartDiv = document.getElementById("piechart"+index);
            var wrongCountSpan=document.createElement("span");
            wrongCountSpan.setAttribute("id","wrongCountSpan"+index);
            wrongCountSpan.setAttribute("class","badge badge-danger");
            wrongCountSpan.innerHTML = "틀린 횟수:"+wrongCount;

            var correctCountCountSpan=document.createElement("span");
            correctCountCountSpan.setAttribute("id","correctCountCountSpan"+index);
            correctCountCountSpan.setAttribute("class","badge badge-success")
            correctCountCountSpan.innerHTML = "맞은 횟수:"+correctCount

            var savedDateSpan=document.createElement("span");
            savedDateSpan.setAttribute("id","savedDateSpan"+index);
            savedDateSpan.setAttribute("class","badge badge-primary");
            var date = new Date(savedDate);
            savedDateSpan.innerHTML = "저장 날짜 :"+date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();

            piechartDiv.appendChild(wrongCountSpan);
            piechartDiv.appendChild(correctCountCountSpan);
            piechartDiv.appendChild(savedDateSpan);
        }

    </script>
</head>
<body>
<div class="container">
    <div class="card shadow-lg border-0 rounded-lg mt-5">
        <div class="card-header text-center">
            <h3 class="font-weight-light my-4">통계</h3>
            <div class="input-group">
                <input class="form-control" id="searchText" type="text"
                       placeholder="내가 저장한 단어를 입력하면 틀림, 맞은 횟수가 나옵니다">
                <input class="btn btn-primary" type="button" value="검색" onclick="searchStatistic()">
            </div>
            <span class="badge badge-primary mt-5">저장한 카드수:${countWord}</span>
        </div>

        <div class="card-body text-center" id="cardBody">

        </div>

        <div class="card-footer text-center">
            <a href="${contextPath}/word/wordBoardForm.do">
                <button class="btn btn-primary">단어수정 페이지로</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
