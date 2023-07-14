<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyBoard</title>
</head>
<body>
	<form action="/board/modifyBoard?boardNo=${board.boardNo}" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr><!-- board 의(.) boardTitle을 출력. -->
				<td colspan="2"><h3>${board.boardTitle}</h3> 글 상세</td>
			</tr>
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId" value="user1" readonly="readonly"></td><!-- ${boardOne.memberId} -->
			</tr>
			<tr>
				<td>boardNo</td>
				<td><input type="text" name="boardNo" value="${board.boardNo}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>localName</td>
				<td><input type="text" name="localName" value="${board.localName}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>boardTitle</td>
				<td><input type="text" name="boardTitle" value="${board.boardTitle}"></td>
			</tr>
			<tr>
				<td>boardContent</td>
				<td><input type="text" name="boardContent" value="${board.boardContent}"></td>
			</tr>
			<tr>
				<td>createdate</td>
				<td><input type="text" name="createdate" value="${board.createdate}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>updatedate</td>
				<td><input type="text" name="updatedate" value="${board.updatedate}" readonly="readonly"></td>
			</tr>
			<tr><!-- 상세 글을 수정/삭제할 수 있는 Form/Action -->
				<td colspan="2">
					<button type="submit">수정</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>