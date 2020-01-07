$(function(){
	$("input[type='button']").on("click",function(){
		var usercode = $("#usercode").val();
		var password = $("#password").val();
		//对用户代码的初步验证
		if(isNaN(usercode.substring(2)) || !isNaN(usercode.substring(0,2)) || usercode.length != 10){
			alert("用户不存在");
			$("#usercode").val("");
			return false;
		}
		//对密码的初步验证
		if(password.length > 18 || password < 6){
			alert("密码错误");
			$("#password").val("");
			return false;
		}
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/checklogin",
			data : {"userCode" : usercode,"password" : password},
			success: function(result){
				if(result == true){
					window.location.href="/index";
				}else{
					alert("登录失败");
				}
			}
		})
	});
})

