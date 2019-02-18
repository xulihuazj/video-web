$(function() {
	
	$(".div-1").show();
	
	$(".biyan").click(function(){
		$(".zyan").show();
		$(".biyan").hide();
		$("#password_value").attr("type","text");
	})
	
	$(".zyan").click(function(){
		$(".zyan").hide();
		$(".biyan").show();
		$("#password_value").attr("type","password");
	})
	
	$("#imgCode").click(function(){
		$("#imgCode").attr('src',$.baseData.basePath+"/image/code?v="+Math.random());
	})
	
	$("#next_page").click(function(){
		nextPage();
	})
	$("#mobile_value").blur(function(){
		checkMobile();
	})
	$("#img_code_value").blur(function(){
		checkImgCode();
	})
//	$("#mobile_code_value").blur(function(){
//		checkMobileCode();
//	})
	$("#mobile_code_button").click(function(){
		var cm = checkMobile();
		var cimgCode = checkImgCode();
		if(cm && cimgCode){
			mobileCodeButton();
		}
	})
	$("#reset_password_button").click(function(){
		if(checkPassword()){
			resetPassword();
		}
	})
});

function resetPassword(){
	$.ajax({
		url: $.baseData.basePath+"/user/password/mobile/code",
		type: "POST",
		data:{
			mobile:$("#mobile_value").val(),
			mobileCode:$("#mobile_code_value").val(),
			password:$("#password_value").val()
		},
		success : function(result) {
			if(result.code == "ok"){
				$(".div-2").hide();
				$(".div-3").show();
				$(".div-3-span").css("background","rgba(0, 184, 212, 1)");
			}else{
				$(".passwordVerify").text(result.message);
				$(".passwordVerify").show();
			}
		}
	});
}

function nextPage(){
	var cm = checkMobile();
	var cimgCode = checkImgCode();
	var cmc = checkMobileCode();
	if(cm && cimgCode && cmc){
		$(".div-2-span").css("background","rgba(0, 184, 212, 1)");
		$(".div-1").hide();
		$(".div-2").show();
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
	var bl = false;
	var mobile_code_value = $("#mobile_code_value").val();
	if(mobile_code_value != null && mobile_code_value.length > 0){
		$.ajax({
			url: $.baseData.basePath+"/user/check/mobile/code",
			type: "POST",
			async: false,
			data:{
				mobile:$("#mobile_value").val(),
				mobileCode:$("#mobile_code_value").val(),
				serviceType:"FORGET"
			},
			success : function(result) {
				if(result.code == "ok"){
					$(".mobileCodeVerify").hide();
					bl = true;
				}else{
					$(".mobileCodeVerify").text(result.message);
					$(".mobileCodeVerify").show();
				}
			}
		});
	}else{
		$(".mobileCodeVerify").text("短信验证码错误");
		$(".mobileCodeVerify").show();
	}
	return bl;
}
function mobileCodeButton(){
	$("#mobile_code_button").removeClass("bgd4");
	$("#mobile_code_button").addClass("bgda");
	$("#mobile_code_button").attr('disabled',true);
	$.ajax({
		url: $.baseData.basePath+"/user/mobile/code",
		type: "POST",
		data:{
			mobile:$("#mobile_value").val(),
			imgCode:$("#img_code_value").val(),
			serviceType:1
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
				$("#mobile_code_button").removeClass("bgda");
				$("#mobile_code_button").addClass("bgd4");
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
		$("#mobile_code_button").removeClass("bgda");
		$("#mobile_code_button").addClass("bgd4");
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
