<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가</title>
</head>
<body>
	<form action="/board/addBoard" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td colspan="2"><h4>게시글 추가</h4></td>
			</tr>
			<tr>
				<td>memberId</td><!-- ${memberId} -->
				<td><input type="text" name="memberId" value="user1" readonly="readonly"></td>
			</tr>
			<tr>
				<td>localName</td>
				<td>
					<select name="localName">
						<option>-- 선택 --</option>
						<c:forEach var="b" items="${localNameList}">
							  <option>${b.localName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>boardTitle</td>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<td>boardContent</td>
				<td><textarea rows="5" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<td>파일 추가</td>
				<td><input type="file" name="multipartFile"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">입력</button></td>
			</tr>
		</table>
	</form>
</body>
</html>