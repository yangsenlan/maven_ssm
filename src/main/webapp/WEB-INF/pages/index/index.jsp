<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 导入样式表-->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/bootstrap.css" type="text/css" rel="stylesheet" />
<!--导入JS文件-->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.form-control{width:200px;float: left};

</style>
<script type="text/javascript">
	function addShop(id){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/shop/addShop?goodsId="+id,
			success:function(data){
				   if(data=='1'){
					alert("添加进购物车成功!");
				   }else if(data=='2'){
					   alert("你已经添加过该商品")
				   }
				}
		});
	}	
		
</script>
</head>
<body>
<div class="container">
<!-- 纵向导航栏 -->
<nav class="bs-docs-sidebar  affix " >
		<ul class="nav bs-docs-sidenav">
		<li>
			<a >欢迎${user.loginName}</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/shop/shopList">购物车 </a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/orders/ordersList">订单列表</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/goods/goodsList">商品</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/kill/killList">抢购秒杀</a>
		</li>
	
	</nav>
	<!-- 头文件搜索 -->
	<form action="<%=request.getContextPath()%>/goods/goodsList" method="get" name="searchBiaodan">
		
		<input class="form-control" type="text" name="goodsName"    
			<c:if test="${vo.goodsName!=null}"> placeholder="${vo.goodsName}" value="${vo.goodsName}"</c:if>
		    <c:if test="${vo.goodsName==null||vo.goodsName==''}"> placeholder="请输入商品名称"</c:if>
		>
	
		<input class="form-control" type="text" name="minPrice" 
			<c:if test="${vo.minPrice!=null}"> placeholder="${vo.minPrice}" </c:if>
			<c:if test="${vo.minPrice==null}"> placeholder="请输入最小商品价格" </c:if>
		>
		<input class="form-control" type="text" name="maxPrice" 
			<c:if test="${vo.maxPrice!=null}"> placeholder="${vo.maxPrice}" </c:if>
			<c:if test="${vo.maxPrice==null}"> placeholder="请输入最大商品价格" </c:if>
		>
		
		
		

		<input type="submit" class="btn btn-default" value="搜索">
		<input type="hidden" name="pages" value="1">
	<!-- 搜索结束 -->
	<!-- 表格主体开始 -->
	</form >
	
		<table class="table table-bordered table-hover table-striped " >
			<tr>
				<td><input type="checkbox" id="all"></td>
				<td>商品ID</td>
				<td>商品图片</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>上架时间</td>
				<td>库存数量</td>
				<td>商品操作</td>
				<td>商品详情</td>
				<td>购物车</td>
			</tr>
			<c:forEach var="shop" items="${page.list}">
				<tr id="xuan">
				<td><input type="checkbox" class="xuan" name="checkbox" value="${shop.goods.id}"></td>
					<td>${shop.goods.id}</td>
					<td>
					 <img  src="${shop.goods.imgUrl}" width="100px" height="100px">
					</td>
					<td>${shop.goods.goodName}</td>
					<td>${shop.goods.goodPrice}</td>
					<td>${shop.goods.updateTime}</td>
					<td>${shop.avaliableCount}</td>
					
						
					<td class="form-group">
					    <button class="btn-primary" data-toggle="modal"  data-target="#updateShop">修改</button>
					    
					  </td>
					  <td><a href="<%=request.getContextPath()%>/goods/goodsShow?id=${shop.goods.id}">点击查看</a></td>
					  <td><a href="javascript:addShop('${shop.goods.id}')">添加进购物车</a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- 分页开始 -->
		
			 <!-- 分页的信息 -->
			 <ul class="pagination">
					<li><a href="">&lt;&lt;</a></li>
					<c:forEach var="i" begin="1" end="${page.pages}"> 
					<li
					<c:if test="${page.pageNum==i }"> style="color:red;"</c:if>
					><a href="<%=request.getContextPath()%>/goods/goodsList?pageNo=${i}&goodsName=${vo.goodsName}&minPrice=${vo.minPrice}&maxPrice=${vo.maxPrice}">${i}</a></li>
					</c:forEach>
					<li class="disabled"><a href="">&gt;&gt;</a></li>
			</ul>

	</div>

</body>
</html>