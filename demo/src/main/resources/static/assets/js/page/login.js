$(function(){
	$("input[type='button']").on("click",function(){
		var usercode = $("#usercode").val();
		var password = $("#password").val();
		var identity = usercode.substring(0,2);
		//对用户代码的初步验证
		console.log(usercode);
		if(isNaN(usercode.substring(2)) || !isNaN(usercode.substring(0,2)) || usercode.length != 12){
			alert("用户不存在");
			$("#usercode").val("");
			return false;
		}
		//对密码的初步验证
		if(password.length > 18 || password < 5){
			alert("密码错误");
			$("#password").val("");
			return false;
		}
		$.ajax({
			type:"POST",
			url:"/checklogin",
			data : {userCode : usercode,password : password},
			success: function(message){
				if(message == ""){
					if(identity=="st"){
						window.location.href="/student";
					}else{
						window.location.href="/admin";
					}
					
				}else{
					alert(message);
				}
			}
		})
	});
})

