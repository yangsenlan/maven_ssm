
			var logincheck=false;
			var passcheck=false;
			$(function(){
				
				$("#login").blur(function(){
					logincheck=false;
					if($(this).val().length==0){
						$("#yanzheng").html("账户为空");
						$("#isloginyes").html("<img style='height:15px;width:15px' src='img/错.png'>");
						logincheck=false;
					}
					var loginreg=/^\w{4,12}$/;
					if(loginreg.test($(this).val())){
						$("#yanzheng").html("账户格式正确");
						$("#isloginyes").html("<img style='height:15px;width:15px' src='img/对.png'>");
						logincheck=true;
					}else{
						$("#yanzheng").html("账户格式错误");
						$("#isloginyes").html("<img style='height:15px;width:15px' src='img/错.png'>");
						logincheck=false;
					}
				});
				$("#login").focus(function(){
					$("#yanzheng").html("长度4—12(数字字母下划线)");
					$("#isloginyes").html("");
				});
				$("#pass").blur(function(){
					passcheck=false;
					if($(this).val().length==0){
						$("#yanzheng").html("密码为空");
						$("#ispassyes").html("<img style='height:15px;width:15px' src='img/错.png'>");
						passcheck=false;
					}
					var loginreg=/^\w{6,10}$/;
					if(loginreg.test($(this).val())){
						$("#yanzheng").html("密码格式正确");
						$("#ispassyes").html("<img style='height:15px;width:15px' src='img/对.png'>");
						passcheck=true;
					}else{
						$("#yanzheng").html("密码格式错误");
						$("#ispassyes").html("<img style='height:15px;width:15px' src='img/错.png'>");
						passcheck=false;
					}
				});
				$("#pass").focus(function(){
					$("#yanzheng").html("长度6—10(数字字母下划线)");
					$("#ispassyes").html("");
				});
			});
			function checkForm(){
				if(passcheck&&logincheck){
					return true;
				}
				return false;
			}
