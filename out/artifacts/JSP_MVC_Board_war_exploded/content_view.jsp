<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/05
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 내용</title>
</head>
<body>
    <table width="500" cellspacing="0" cellpadding="0" border="1">
        <form action="content_view.do" method="post">
            <input type="hidden" name="BId" value="${content_view.BId}">  <%-- 무슨 의미인지 모르겠음 --%>
            <tr>
                <td>번호</td>
                <td>${content_view.BId}</td>
            </tr>
            <tr>
                <td>히트</td>
                <td>${content_view.BHit}</td>
            </tr>
            <tr>
                <td>이름</td>
                <td>${content_view.BName}</td>
            </tr>
            <tr>
                <td>제목</td>
                <td>${content_view.BTitle}</td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea rows="10" name="BContent">${content_view.BContent}</textarea></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="수정">
                    <a href="list.do">목록</a>
                    <a href="delete.do?BId=${content_view.BId}">삭제</a>
                    <a href="reply_view.do?BId=${content_view.BId}">답변</a></td>
                <!-- 삭제와 답변은 지금 다루지 않아서 delete.do와 reply_view.do 로 두고 보류함. -->
            </tr>
        </form>
    </table>
</body>
</html>
