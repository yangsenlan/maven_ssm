
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/shop.css">		
<script src="<%=request.getContextPath() %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/shop.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!-- 纵向导航栏 -->
<nav class="bs-docs-sidebar  affix " >
		<ul class="nav bs-docs-sidenav">
		<li>
			<a >欢迎${user.name}</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/shop/carShop.action">购物车 </a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/order/orderAll.action">订单列表</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/shop/index.action">首页</a>
		</li>
	
	</nav>
	<div class="gouwubig">
		<form action="<%=request.getContextPath()%>/order/buyprod.action" method="post">
		<table border="0" class="gouwutable" cellspacing="0" cellpadding="0">
			<tr class="gouwutitle">
				<td colspan="6">已选商品<input type="button"  class="deleteall" onclick="deleteAll1()" value="全部删除"/></td>
			</tr>
			<tr class="prodtitle">
				<td class="prodcol1"><input type="checkbox" class="checkAll" value="" onclick="quanxuan(this.checked)"/>全选</td>
				<td class="prodcol2">商品信息</td>
				<td class="prodcol3">单价</td>
				<td class="prodcol4">数量</td>
				<td class="prodcol5">金额</td>
				<td class="prodcol6">操作</td>
			</tr>
			<!-- 在这循环购物车商品 -->
			 <c:forEach items="${list}" var="goods">
			<tr class="prodRow">
			
				<td class="prodcol1"><input type="checkbox" class="checkprod" name="checkProdId" value="商品id" onclick="danxuan()"/><img class="prodImg" src="${goods.imgUrl}"/></td>
				<td class="prodcol2"><a href="javascript:">${goods.goodName}</a><br/><img src="img/creditcard.png"/><img src="img/7day.png"/><img src="img/promise.png"/></td>
				<td class="prodcol3"><span class="xianjia">${goods.goodPrice}</span></td>
				<td class="prodcol4"><span class="gouwufuhao jian" id="jian">-</span><!-- input value值是数量 --><input class="gouwunum" type="text" value="1"/><span class="gouwufuhao jia" id="jia">+</span></td>
				<td class="prodcol5"></td>
				<td class="prodcol6"><a href="javascript:">删除</a></td>
			</tr>
			</c:forEach>
			<!-- 在这结束购物车商品循环 -->
		</table>
		<div class="jiesuan">
			<input type="checkbox" class="qunxuan" onclick="quanxuan(this.checked)"/>
			<span>全选</span>
			<div class="jiesuanRight">
				<span>已选商品<span class="prodzong">选取件数</span>件</span>
				<span>合计 (不含运费): </span>
				<span class="prodPrice">￥全部总价格</span>
				<button type="submit" class="jiesuanButton">结算</button>
			</div>
		</div>
		</form>
	</div>
</body>
</html>