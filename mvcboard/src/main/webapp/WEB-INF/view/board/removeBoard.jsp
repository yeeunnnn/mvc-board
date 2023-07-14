<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeBoard</title>
</head>
<body>
	정말 삭제하시겠습니까?
	<form action="/board/removeBoard" method="post">
		<!-- input type="hidden" name="memberId" value="${memberId}"-->
		<input type="hidden" name="boardNo" value="${board.boardNo}">
		<button type="submit">삭제</button>
	</form>
</body>
</html>