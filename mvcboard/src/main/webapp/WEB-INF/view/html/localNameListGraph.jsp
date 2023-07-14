<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.min.js"></script>
<script>
	$(document).ready(function(){
		/*
		let html = `<tr>
						<td>GDJ66</td>
						<td>25</td>
					</tr>`; //AJAX API 결과물(자료구조) 출력
		$('#target').html(html);
		*/
		const x = [];
		const y = [];
		const barColors = ["red", "green", "blue", "orange", "brown", "navy", "pink", "yellow"]
		
		// 동기 호출로 x, y값을 세팅
		$.ajax({
			async : false,  // true(비동기 | ex. 버튼 누르면 값을 받아옴), false(동기 | 페이지가 열리는 시점에 데이터 출력)
			url : '/rest/localNameListGraph',
			type : 'get',
			success : function(model) {
				// model -> {'model':[{localName:'부산', cnt:10} {localName:'부산', cnt:10}, ... ]} model에 키와 값으로 된 객체의 배열이 있음.
				model.forEach(function(item, index){ // for ... in -> foreach
					$('#target').append('<tr>');
					$('#target').append('<td>'+item.localName+'</td>');
					$('#target').append('<td>'+item.cnt+'</td>');
					$('#target').append('</tr>');
					
					// chart모델 생성
					x.push(item.localName);
					y.push(item.cnt);
					
				});
				/*let html = ''; // AJax API 결과물(자료구조) 출력
				$('#target').html(html);*/
			}
		});
		
	    
		new Chart("target2", {
			type : "bar",
			data: {
				    labels: x,
				    datasets: [{
				      backgroundColor: barColors,
				      data: y
				    }]
				  }
			});
		});
</script>
<!-- 이 페이지에 데이터를 뿌릴 것 -->
</head>
<body>
	<h1>AJax API사용으로 localNameList 가져오기</h1>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>지역이름</th>
					<th>게시글수 </th>
				</tr>
			</thead>
			<tbody id="target">
			</tbody>
		</table>
		<canvas id="target2" style="width:100%;max-width:700px"></canvas>
	</div>
</body>
</html>