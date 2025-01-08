<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		//버튼 id 설정 후 객체 지정
		let postBtn = document.getElementById("postBtn");
		let postJsonBtn = document.getElementById("postJsonBtn");

		//설정 한 객체를 가지고 eveneListener 설정
		postBtn.addEventListener("click", function() {
			
			//input id값을 가져와 해당 value 를 객체에 지정
			let boardNo = document.getElementById("boardNo").value;
			let title = document.getElementById("title").value;
			let content = document.getElementById("content").value;
			let writer = document.getElementById("writer").value;

			//boardObject에 설정 한 value값을 넣어 객체 생성
			let boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			};
			
			//XML형태로 서버에게 요쳥하기위한 함수
			let xhr = new XMLHttpRequest();
			let url = "/board/" + boardNo;
			
			//tpye, url, true / false 설정 (true = 비동기, false = 동기)
			xhr.open("POST", url, true);
			
			//content-type 설정
			xhr.setRequestHeader("Content-Type",
					"application/json; charset=utf-8");

			//서버로 부터 답변을 받을 때 실행하는 핸들러 함수 (callback 함수)
			xhr.onload = function() {
				if (xhr.status === 200) {
					let result = xhr.responseText;
					console.log("result: " + result);
					if (result === "SUCCESS") {
						alert("정상 성공");
					} else {
					}
				} else {
					console.error("Request failed. Status: " + xhr.status);
				}
			};
			
			//서버로 부터 요청 시 문제점 발생 처리 함수 
			xhr.onerror = function() {
				console.error("Request failed. Network error.");
			};
			//그 외 정상 처리 되었을 때 JSON.stringify 파일로 해당 객체 전송
			xhr.send(JSON.stringify(boardObject));
		});
		

		postJsonBtn.addEventListener("click", function() {
			let boardNo = document.getElementById("boardNo").value;
			let title = document.getElementById("title").value;
			let content = document.getElementById("content").value;
			let writer = document.getElementById("writer").value;

			let boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			};

			let xhr = new XMLHttpRequest();
			let url = "/board/" + boardNo;

			xhr.open("PUT", url, true);
			xhr.setRequestHeader("Content-Type",
					"application/json; charset=utf-8");

			xhr.onload = function() {
				if (xhr.status === 200) {

					let result = xhr.responseText;
					console.log("result: " + result);
					if (result === "SUCCESS") {
						alert("PUT방식 정상 성공");
					} else {
					}
				} else {
					console.error("Request failed. Status: " + xhr.status);
				}
			};

			xhr.onerror = function() {
				console.error("Request failed. Network error.");
			};

			xhr.send(JSON.stringify(boardObject));
		});

	});
</script>
<body>
	<h1>Ajax Home</h1>
	<form>
		boardNo: <input type="text" name="boardNo" value="" id="boardNo"><br>
		title: <input type="text" name="title" value="" id="title"><br>
		content: <input type="text" name="content" value="" id="content"><br>
		writer: <input type="text" name="writer" value="" id="writer"><br>
	</form>
	<div>
		<button id="postBtn">수정(put)</button>
		<button id="postJsonBtn">수정(put with header)</button>
	</div>
</body>
</html>