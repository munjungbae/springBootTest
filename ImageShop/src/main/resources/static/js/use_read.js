let useReadObject = {
	//익명함수, 화살표 함수
	init: function() {
		let _this = this;
		let formObj = $("#member");
		console.log(formObj);

		$("#btnEdit").on("click", function() {
			let userNo = $("#userNo");
			let userNoVal = userNo.val();
			self.location = "/user/modify?userNo=" + userNoVal;
		});

		$("#btnRemove").on("click", function() {
			formObj.attr("action", "remove");
			formObj.submit();
		});

		$("#btnList").on("click", function() {
			self.location = "list";
		});
		$("#btnAjax").on("click", function() {
			_this.listReply();
		});

	},

	listReply: function() {
		alert("내용 리스트 요청됨.")
		let userNo = $("#userNo").val();

		$.ajax({
			type: "GET",
			url: "/user/member" + userNo

		}).done(function(response) {
			let message = response["dataList"];
			alert(message);
			location = "/user"
		}).fail(function(error) {
			alert("에러발생 : " + error);
			location = "/"
		});
		//ajax사용하여 서버에 전송 후 리턴 값 할당
	}
};

useReadObject.init();