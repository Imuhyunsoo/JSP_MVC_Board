<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/05
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table width="500" cellpadding="0" cellspacing="0" border="1">
        <form action="reply.do" method="post">
            <input type="hidden" name="BId" value="${reply_view.BId}">
            <input type="hidden" name="BGroup" value="${reply_view.BGroup}">
            <input type="hidden" name="BStep" value="${reply_view.BStep}">
            <input type="hidden" name="BIndent" value="${reply_view.BIndent}">
            <tr>
                <td>번호</td>
                <td>${reply_view.BId}</td>
            </tr>
            <tr>
                <td>히트</td>
                <td>${reply_view.BHit}</td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" name="BName" value="${reply_view.BName}"></td>
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text" name="BTitle" value="${reply_view.BTitle}"></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea rows="10" name="BContent">${reply_view.BContent}</textarea></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="답변">
                <a href="list.do">목록</a></td>
            </tr>
        </form>
    </table>
</body>
</html>
