$("#submit_login").click(function(){
	//사용자 계정 정보 암호화 전 평문
	var id = $("#user_id").val();
	var password = $("#user_password").val();
	
	//RSA 암호화 생성
	var rsa = new RSAKey();
	rsa.setPublic($("#OASISKeyModulus").val(), $("OASISKeyExponent").val());
	
	//사용자 계정 정보를 암호화 처리
	id = rsa.encrypt(id);
	password = rsa.encrypt(password);
	
	$.ajax({
		//암호화된 계정 정보를 서버로 전송
		type: "POST",
		url: "/com.april.controller.LoginProc.java", //./proc/login/proc
		data: {user_id:id, user_password:password},
		dataType: "json",
		success: function(msg){
			if(msg.state == "true"){
				location.href = "/view/board/boardList.jsp";
				alert("로그인 성공");
			}else if(msg.state == "false"){
				alert("로그인에 실패했습니다. <br> 아이디, 비밀번호를 확인하세요.");
			}else{
				alert("잘못된 경로입니다. <br> 암호화 인증에 실패했습니다.");
			}
		}
	});
});