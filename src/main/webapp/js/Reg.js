//页面加载
$(function() {

	$("button[type='button']").click(function() {
		alert("点击了按钮" + $(this).attr("id"));
	});

	$("#userName").blur(function() {
		/*alert("0");*/
	});

});

function validateName() {
	//获取元素 
	var inputName = $("#userName");
	//获取表单元素的值使用val()
	var name = inputName.val();
	var zhengze = /^[0-9a-zA-Z_]{4,9}$/;
	if(name == "") {
		//非表单元素获取内容html()或者text()
		$("#msgName").html("登陆名不能为空!");
		$("#msgName").addClass("red");
	} else if(!zhengze.test(name)) {
		$("#msgName").html("登陆名不合法!")
		$("#msgName").addClass("red");
	} else {
		$("#msgName").html("该登陆可使用!");
		$("#msgName").addClass("green");
		return true;
	}
	return false;
}

function validatePass() {
	//获取元素
	var inputName = $("#password");
	//获取表单元素的值使用val()
	var name = inputName.val();
	var zhengze = /^(\w){6,15}$/;
	if(name == "") {
		//非表单元素获取内容html()或者text()
		$("#msgPass").html("密码不能为空!");
		$("#msgPass").addClass("red");
	} else if(!zhengze.test(name)) {
		$("#msgPass").html("密码不合法!")
		$("#msgPass").addClass("red");
	} else {
		$("#msgPass").html("该密码可使用!");
		$("#msgPass").addClass("green");
		return true;
	}
	return false;
}

function validatePass2() {
	//获取元素
	var inputName = $("#querenpassword");
	//获取表单元素的值使用val()
	var name = inputName.val();
	var zhengze = /^(\w){6,15}$/;
	if(name == "") {
		//非表单元素获取内容html()或者text()
		$("#msgPass2").html("密码不能为空!");
		$("#msgPass2").addClass("red");
	} else if(!zhengze.test(name)) {
		$("#msgPass2").html("密码不合法!")
		$("#msgPass2").addClass("red");
	} else {
		$("#msgPass2").html("该密码可使用!");
		$("#msgPass2").addClass("green");
		return true;
	}
	return false;
}

function reg() {
	return validateName() && validatePass() && validatePass2();
}