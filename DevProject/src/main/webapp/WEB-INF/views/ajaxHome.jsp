<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	//모든 html이 dom객체가 준비 되었다면 함수 시작
	$(document).ready(function(){
		//putBtn 버튼을 클릭 하면 해당 함수작동 (서버에 데이터를 전송(ajax로), 전송 성공유무를 return받아 출력)
		$("#putBtn").on("click", function(){
			//사용자가 입력한 데이터를 읽어와서 객체를 만듦
			let boardNo = $("#boardNo");
			let title = $("#title");
			let content = $("#content");
			let writer = $("#writer");
			
			//객체에서 값 읽어오기
			let boardNoVal = boardNo.val();  // val => value
			let titleVal = title.val();  // val => value
			let contentVal = content.val();  // val => value
			let writerVal = writer.val();  // val => value
			
			//전송 할 객체 생성
			let boardObj = {
				boardNo : boardNoVal,
				title : titleVal,
				content : contentVal,
				writer : writerVal
			};
			
			$.ajax({
				type : "post",
				url : "/board/" + boardNoVal,
				headers : {"X-HTTP-Method-Override" : "PUT"},
				data : JSON.stringify(boardObj),
				contentType : "application/json; charset=utf-8",
				success : (result) => {
					console.log("result : " + result);
					if (result === "SUCCESS") {
						alert("성공했습니다." + boardObj.boardNo);
						alert("성공했습니다." + boardObj.title);
						alert("성공했습니다." + boardObj.content);
						alert("성공했습니다." + boardObj.writer);
					}
				}
			})
			
		})
		
	})
	
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
		<button id="putBtn">수정(put)</button>
		<button id="putHeaderBtn">수정(put with header)</button>
	</div>
</body>
</html>