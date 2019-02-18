
$(function(){
	$("#imgCode").click(function(){
		$("#imgCode").attr('src',$.baseData.basePath+"/image/code?v="+Math.random());
	})
	$("#mobile_value").blur(function(){
		var bl = checkMobile();
		if(bl){
			isImgCodeShow();
		}
	})
	$("#img_code_value").blur(function(){
		checkImgCode();
	})
//	$("#password_value").blur(function(){
//		checkPassword();
//	})
	$("#login_button").click(function(){
		$(".loginVerify").hide();
		var cm = checkMobile();
		var cimgCode = checkImgCode();
		var cpwd = checkPassword();
		if(cm && cimgCode && cpwd){
			$("#login_button").attr('disabled',true);
			login();
		}
	})
})

function login(){
	$.ajax({
		url: $.baseData.basePath+"/login",
		type: "POST",
		data:{
			mobile:$("#mobile_value").val(),
			imgCode:$("#img_code_value").val(),
			password:$("#password_value").val()
		},
		success : function(result) {
			if(result.code == "ok"){
				if(result.backurl != null){
					window.location.href=result.backurl;
				}else{
					window.location.href=$.baseData.basePath+"/";
				}
			}else{
				if("图形验证码错误" == result.message){
					$(".imgCodeVerify").show();
				}else{
					$(".loginVerify").text(result.message);
					$(".loginVerify").show();
				}
				isImgCodeShow();
			}
			$("#login_button").attr('disabled',false);
		}
	});
}

var imgCodeShow = false;
function isImgCodeShow(){
	$.ajax({
		url: $.baseData.basePath+"/image/code/show",
		type: "GET",
		data:{
			mobile:$("#mobile_value").val()
		},
		success : function(result) {
			if(result.code == "SHOW"){
				$("#img_code_div").show();
				imgCodeShow = true;
				$("#imgCode").click();
			}else{
				$("#img_code_div").hide();
				imgCodeShow = false;
			}
		}
	});
}

function checkMobile(){
	if(!mobileVerify($("#mobile_value").val())){
		$(".mobileVerify").show();
		$(".mobileVerify").text("手机号码格式不正确");
		return false;
	}else{
		$(".mobileVerify").hide();
		return true;
	}
}

function checkImgCode(){
	if(imgCodeShow){
		var img_code_value = $("#img_code_value").val();
		if(imgCodeVerify(img_code_value)){
			$(".imgCodeVerify").hide();
			return true;
		}else{
			$(".imgCodeVerify").show();
			$(".imgCodeVerify").text("图形验证码错误");
			return false;
		}
	}else{
		return true;
	}
}

function checkPassword(){
	var password_value = $("#password_value").val();
	if(passwordVerify(password_value)){
		$(".passwordVerify").hide();
		return true;
	}else{
		$(".passwordVerify").show();
		$(".passwordVerify").text("密码格式错误，请输入6~20位密码，且必须含数字/字母/字符中的至少两种组合");
		return false;
	}
}