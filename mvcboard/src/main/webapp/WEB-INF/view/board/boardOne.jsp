<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr><!-- board 의(.) boardTitle을 출력. -->
			<td colspan="2"><h3>${boardOne.boardTitle}</h3> 글 상세</td>
		</tr>
		<tr>
			<td>memberId</td>
			<td>${boardOne.memberId}</td>
		</tr>
		<tr>
			<td>boardNo</td>
			<td>${boardOne.boardNo}</td>
		</tr>
		<tr>
			<td>localName</td>
			<td>${boardOne.localName}</td>
		</tr>
		<tr>
			<td>boardTitle</td>
			<td>${boardOne.boardTitle}</td>
		</tr>
		<tr>
			<td>boardContent</td>
			<td>${boardOne.boardContent}</td>
		</tr>
		<!-- 파일 미리보기 및 다운로드 -->
	    <c:forEach var="s" items="${saveFilename}">
            <tr>
            	<td>boardfile</td>
            	<td>
            		<a href="/upload/${s.saveFilename}">미리보기</a> <a href="/upload/${s.saveFilename}" download="${s.saveFilename}">다운로드</a>
            	</td>
            </tr>
	    </c:forEach>
	    <!-- 파일이 존재하지 않을 경우 메세지 표시 -->
	    <c:if test="${empty saveFilename}">
		        <tr>
				    <td>boardfile</td>
		            <td>파일이 존재하지 않습니다.</td>
	            </tr>
        </c:if>
		<tr>
			<td>createdate</td>
			<td>${boardOne.createdate}</td>
		</tr>
		<tr>
			<td>updatedate</td>
			<td>${boardOne.updatedate}</td>
		</tr>
		<tr><!-- 상세 글을 수정/삭제할 수 있는 Form으로 이동 -->
			<td colspan="2">
				<a href="/board/modifyBoard?boardNo=${boardOne.boardNo}">수정</a><!-- memberId=${memberId}& -->
				<a href="/board/removeBoard?boardNo=${boardOne.boardNo}">삭제</a><!-- memberId=${memberId}& -->
			</td>
		</tr>
	</table>
</body>
</html>