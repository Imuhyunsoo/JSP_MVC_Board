<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/05
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성</title>
</head>
<body>
    <table width="500" cellpadding="0" cellspacing="0" border="1">
        <form action="write.do" method="post">
            <tr>
                <td>이름</td>
                <td><input type="text" name="BName" size="50"></td>
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text" name="BTitle" size="50"></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea name="BContent" rows="10"></textarea></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="입력">
                    <a href="list.do">목록</a></td>
            </tr>
        </form>
    </table>
</body>
</html>
