<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
<center>
	<div>
		<div>
			用户名称:<input type="text" name="name" /><span></span><br/>
			用户密码:<input type="password" name="pass" /><span></span><br/>
			记住密码:<input type="checkbox" name="memberPass" value="yes" /><span></span><br/>
			<span style="color:red" id="tips"></span>
			<button type="submit" class="btn" />提交</button>
		</div>
	</div>
	</center>
</body>

<!--导入JS文件-->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//用户名称验证
	var isCheckName = false;
	$("button[class='btn']").click(function(){
		var name=$("input[name='name']").val();
		var pass=$("input[name='pass']").val();
		
		$.ajax({
			url:"${pageContext.request.contextPath}/login/login",
			type:"post",
			contentType:"application/json;charset=utf-8",
			data:'{"loginName":"'+name+'","password":"'+pass+'"}',
			dataType:"json",
			success:function(data){
				
				if(data!='0'){
					window.location.href="${pageContext.request.contextPath}/goods/goodsList";
				}else{
					$("#tips").html("用户名或密码错误");
				}
				
			}
		});

	});
	 
});
</script>

</html>