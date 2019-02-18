
/**
 * 手机号码格式校验
 * @param mobile
 * @returns
 */
function mobileVerify(mobile){
	return mobile.match(/^1[0-9]{10}$/); 
}

function imgCodeVerify(img_code_value){
	if(img_code_value != null && img_code_value.length <= 5 && img_code_value.length > 0 ){
		return true;
	}else{
		return false;
	}
}

function passwordVerify(password){
	var bl = false;
	var reg = new RegExp(/^[0-9A-Za-z_]{6,20}/);
    if (reg.test(password)) {
    	reg1 = new RegExp(/[A-Za-z_].*[0-9_]|[0-9_].*[A-Za-z_]/);
        bl = reg1.test(password);
    }
    return bl;
}