
$(function(){

	$("#imgCode").click(function(){
		$("#imgCode").attr('src',$.baseData.basePath+"/image/code?v="+Math.random());
	})
	
	$("#register_button").click(function(){
		$("#register_button").off("click");
		registerButton();
	})
	$("#mobile_value").blur(function(){
		checkMobile();
	})
	$("#img_code_value").blur(function(){
		checkImgCode();
	})
	$("#mobile_code_value").blur(function(){
		checkMobileCode();
	})
//	$("#password_value").blur(function(){
//		checkPassword();
//	})
	$("#mobile_code_button").click(function(){
		if(checkMobile() && checkImgCode()){
			mobileCodeButton();
		}
	})
	$(".login_page").click(function(){
		window.location.href=$.baseData.basePath+"/login/page";
	})
})

function mobileCodeButton(){
	$("#mobile_code_button").attr('disabled',true);
	$.ajax({
		url: $.baseData.basePath+"/user/mobile/code",
		type: "POST",
		data:{
			mobile:$("#mobile_value").val(),
			imgCode:$("#img_code_value").val()
		},
		success : function(result) {
			if(result.code == "ok"){
				mobileCodeButtonTime();
			}else{
				$("#imgCode").click();
				if("图形验证码错误" == result.message){
					$(".imgCodeVerify").show();
				}else{
					$(".mobileVerify").text(result.message);
					$(".mobileVerify").show();
				}
				$("#mobile_code_button").attr('disabled',false);
			}
		}
	});
}
var timeNumber = 60;
function mobileCodeButtonTime(){
	timeNumber--;
	$("#mobile_code_button").text(timeNumber+"s后获取");
	if(timeNumber > 0){
		setTimeout(mobileCodeButtonTime,1000);
	}else{
		timeNumber = 60;
		$("#mobile_code_button").text("获取验证码");
		$("#mobile_code_button").attr('disabled',false);
	}
}

function registerUser(){
	$.ajax({
		url: $.baseData.basePath+"/user/register",
		type: "POST",
		data:{
			mobile:$("#mobile_value").val(),
			password:$("#password_value").val(),
			mobileCode:$("#mobile_code_value").val()
		},
		success : function(result) {
			if(result.code == "ok"){
				$("#reg_info").hide();
				$("#reg_success").show();
			}else{
				$("#register_button").on("click",function(){
					registerButton();
				})
				if("短信验证码错误" == result.message){
					$(".mobileCodeVerify").show();
				}else if("账号已经注册" == result.message){
					$(".mobileVerify").text(result.message);
					$(".mobileVerify").show();
				}
			}
		}
	});
}

function registerButton(){
	var cm = checkMobile();
	var cimgCode = checkImgCode();
	var cmc = checkMobileCode();
	var cpwd = checkPassword();
	if(cm && cimgCode && cmc && cpwd){
		registerUser();
	}else{
		$("#register_button").on("click",function(){
			registerButton();
		})
	}
}

function checkMobile(){
	if(!mobileVerify($("#mobile_value").val())){
		$(".mobileVerify").text("手机号码格式不正确")
		$(".mobileVerify").show();
		return false;
	}else{
		$(".mobileVerify").hide();
		return true;
	}
}

function checkImgCode(){
	var img_code_value = $("#img_code_value").val();
	if(imgCodeVerify(img_code_value)){
		$(".imgCodeVerify").hide();
		return true;
	}else{
		$(".imgCodeVerify").text("图形验证码错误");
		$(".imgCodeVerify").show();
		return false;
	}
}

function checkMobileCode(){
	var mobile_code_value = $("#mobile_code_value").val();
	if(mobile_code_value != null && mobile_code_value.length > 0){
		$(".mobileCodeVerify").hide();
		return true;
	}else{
		$(".mobileCodeVerify").text("短信验证码错误");
		$(".mobileCodeVerify").show();
		return false;
	}
}

function checkPassword(){
	var password_value = $("#password_value").val();
	if(passwordVerify(password_value)){
		$(".passwordVerify").hide();
		return true;
	}else{
		$(".passwordVerify").text("密码格式错误，请输入6~20位密码，且必须含数字/字母/字符中的至少两种组合");
		$(".passwordVerify").show();
		return false;
	}
}





