<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/05
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>글 목록</title>
</head>
<body>
  <table width="500" border="1">
    <thead>
      <tr>
        <td>번호</td>
        <td>이름</td>
        <td>제목</td>
        <td>날짜</td>
        <td>히트</td>
      </tr>
    </thead>
    <tbody>
      <!-- 이때 list 는 BListCommand 에서 메모리에 올린 list 를 뜻함.
           포워딩시까지 살아있으므로 forEach 문을 이용해 데이터를 꺼낼 수 있다. -->
      <c:forEach var="board" items="${list}">
          <tr>
              <td>${board.BId}</td>
              <td>${board.BName}</td>
              <td><c:forEach begin="1" end="${board.BIndent}">[Re]</c:forEach>
                  <a href="content_view.do?BId=${board.BId}">${board.BTitle}</a>
              </td>
              <td>${board.BDate}</td>
              <td>${board.BHit}</td>
          </tr>
      </c:forEach>
      <tr>
        <td colspan="5"><a href="write_view.do">새 글 작성</a></td>
      </tr>
    </tbody>
  </table>
</body>
</html>
