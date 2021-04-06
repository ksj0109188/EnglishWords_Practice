<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>dailyWord Page</title>
    <script>
        function moveToMyWord(index) {
            var word = document.getElementsByName("td_word")[index].innerText;
            var mean = document.getElementsByName("td_mean")[index].innerText;
            var dailyId = document.getElementsByName("dailyId")[index].value;
            $.ajax({
                type: "post",
                async: true,
                url: "${contextPath}/word/DailyWord",
                data: {
                    dailyId: dailyId,
                    word: word,
                    mean: mean
                },
                success: function () {
                    alert("추가되었습니다.");
                },
                error: function (request, error) {
                    alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div class="mt-4">
        <div class="card shadow-lg border-0 rounded-lg">
            <div class="card-head align-self-center">
                <h3 class="text-center font-weight-light my-4">오늘의 단어</h3>
            </div>
            <div class="card-body">
                <div class="table">
                    <table class="table text-center" width="100%" cellpadding="0"
                           role="grid" style="width: 100%;">
                        <thead class="thead-dark">
                        <tr>
                            <th>단어</th>
                            <th>의미</th>
                            <th>내 단어장 추가</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${dailyWordVO}" var="item" varStatus="itemStatus">
                            <tr>
                                <td name="td_word">${item.word}</td>
                                <td name="td_mean">${item.mean}</td>
                                <td><input class="btn btn-primary" type="button" value="새 카드로 이동"
                                           onclick="moveToMyWord(${itemStatus.index})"></td>
                                <input type="hidden" name="dailyId" value="${item.dailyId}">
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer">
                오늘의 단어는 매일 오전 1시에 갱신됩니다.<br>
                출처 :<a href="https://learn.dict.naver.com/m/endic/today/words.nhn">네이버 사전</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
