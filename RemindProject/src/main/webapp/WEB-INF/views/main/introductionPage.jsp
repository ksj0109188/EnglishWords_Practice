<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>소개 페이지</title>
    <script>
        <c:if test="${isFinish eq true}">
        window.onload = function () {
            alert("학습할 것이 없습니다.");
        }
        </c:if>
    </script>
</head>
<body>
<div class="container">
    <div class="row mt-auto">
        <div class="card align-items-center  mt-5">
            <div class="card-body">
                <p>
                    Remind 웹 사이트는 효율적인 영어 단어를 학습할 수 있는 웹사이트입니다.
                    <a href="https://ko.wikipedia.org/wiki/%EB%A7%9D%EA%B0%81_%EA%B3%A1%EC%84%A0">헤르만 에빙하우스의 망각이론</a>을
                    통해 복습 주기를 계산하고,
                    <a href="https://www.google.com/search?q=%EB%9D%BC%EC%9D%B4%ED%8A%B8%EB%84%88+%EC%95%94%EA%B8%B0%EB%B2%95&hl=en&biw=1360&bih=664&sxsrf=ALeKk03YdHrTKUYiOj7zlh1opuC8Cba2hw%3A1617582582146&ei=9llqYKuzCMnywQPzp4PQCA&oq=%EB%9D%BC%EC%9D%B4%ED%8A%B8%EB%84%88+&gs_lcp=Cgdnd3Mtd2l6EAMYAzIECCMQJzIECCMQJzIECCMQJzICCAAyAggAMgIIADIECAAQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjoHCCMQsAMQJzoHCAAQRxCwA1C7F1jvGGCIJWgBcAJ4AIABmgGIAfICkgEDMS4ymAEAoAEBqgEHZ3dzLXdpesgBBcABAQ&sclient=gws-wiz">
                        라이트너</a> 박스 이론처럼 플래시 카드 게임 형식으로 학습이 진행됩니다.
                </p>
                <p>
                    저장하신 영어 단어는 사용자가 맞춘 횟수에 따라 1일, 1주, 1달, 6달, 1년씩 복습 주기가 증가됩니다.
                    중간에 틀린다면 해당 단어는 10분 후에 다시 등장하며 영어 단어의 복습 주기 시간 또한 초기화가 됩니다. 이런 방식으로 학습하게 되면 모르는 단어들만 집중적으로 학습하실 수
                    있습니다.
                </p>
                <p>단어를 직접 저장하시기 번거로우시다면, 매일 오전 8시에 업데이트되는 오늘의 단어를 내 단어장으로 추가하거나 해당 웹사이트에 적용된 자동 번역 기능을 이용해보세요.</p>
                <p>Remind 웹 사이트의 개선점이나, 오류, 문의사항은 문의 게시판에 작성해 주세요.</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
