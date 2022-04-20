<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js" ></script>
		<style type="text/css">
			.prodBig{width: 1080px;margin: 0px auto; background: white; padding: 50px 30px;}
			.prodTop,.prodCenter,.prodBottom{width: 1080px;}
			.prodTop{height: 500px;}
			#imgBig{width: 300px; height: 300px;border:1px solid #F2F2F2;padding: 20px;}
			.imgxiao{width: 55px;height: 55px;border: solid 2px white;}
			.imgxiao:hover{border:solid 2px black;}
			.prodTopLeft,.prodTopRight{float: left;height: 500px;}
			.prodTopLeft{width: 40%;background: white; text-align: center;}
			.prodTopRight{padding:0px 10px;background: white;}
			.prodTitle{color: black;font-size: 16px;font-weight: bold;margin: 0px 10px;}
			.prodTitle2{color: #DD2727;font-size: 12px;margin: 0px 10px;}
			.prodTime{background: #2DA77A;color: white;text-align: center;height: 40px;line-height: 40px;margin-top: 10px;}
			#jhs{font-size: 18px;font-weight: bold;}
			.jhsInfo{font-size: 12px;}
			#jhsTime{font-size: 12px;color: #FFC057;font-weight: bold;}
			.prodInfo{height: 80px;background: url(img/priceBackground.png);padding: 10px;color: #666666;font-size: 12px;}
			.prodInfo img{width: 58px;height: 16px;float: left;margin-right: 5px;}
			.prodInfoLeft{width: 68px; display: inline-block;}
			.prodInfoRight1{text-decoration:line-through;color: black;}
			.prodInfoRight2{color:#c40000;font-size:25px;font-weight:bold;}
			.proddetail{border-top: dotted 1px #C9C9C9;border-bottom: dotted 1px #C9C9C9;margin:20px 0px;height:15px;padding: 10px;}
			.proddetailLeft,.proddetailRight{float: left;text-align: center;font-size: 12px;color: #C9C9C9;width: 252px;}
			.proddetailLeft{border-right:solid 1px #E5DFDA;}
			.prodnum{color: #c40000;font-weight: bold;}
			.prodNumber{color: #999999;font-size: 12px;}
			.prodNumber input{width: 38px;height: 26px;padding-left: 5px;position: relative;}
			.updown{display: inline-block;height: 32px;width: 22px;vertical-align: top;}
			.updownspan{border: 1px solid #999;display: block;width: 20px;text-align: center;height: 12px;background: white;}
			.updownspan2{margin-top: 4px;}
			.updownimg{vertical-align: top;margin: 4px 0px;}
			.prodfuwu{margin: 10px 0px;padding: 15px 0px;font-size: 12px;}
			.prodfuwu1{color: #999999;}
			.prodfuwu2 a{text-decoration: none; color: #666666;}
			.prodfuwu2 a:hover{color: #C40000;}
			.prodInput{text-align: center;margin: 10px 0px;padding: 10px 0px;}
			.button1{width: 166px;height: 36px;border: 1px solid #C40000;background:#FFEDED;color: #C40000;font-size:14px;border-radius: 5px;font-weight: bold;}
			.button2{width: 166px;height: 36px;background: #C40000;border: none;color: white;font-weight: bold;font-size: 14px;border-radius:5px;}
			.button1,.button2{margin: 0px 20px;}
			.button1,.button2:hover{cursor: pointer;}
			.prodCenter{height: 50px;}
			.prodChoose{height: 48px;width: 790px;margin: 0px auto;border: 1px solid #DFDFDF;}
			.choosespan{width: 90px;height: 46px;display: inline-block;font-size:12px;line-height: 46px;color: #3355B9;text-align: center;}
			.choosea{color: #C40000;text-decoration: none;font-weight: bold;}
			.choosea2{color: #999999;}
			.choosespan1{border: 1px solid #999999;border-top: #C40000 solid 2px;}
			.prodChooseHeight{height: 206px;padding:40px;width: 710px;margin: 0px auto;border: 1px solid #DFDFDF;display: none;}
			.prodInfotable{width: 708px;font-size: 12px;color: #666666;}
			.prodInfotable tr td{height: 27px;width: 220px;margin-top:10px;display: inline-block;}
			.prodInfotabletitle{font-weight: bold;color: #999999;}
			.prodChooseLong{width: 790px;margin: 0px auto;display: none;}
			.comment{width: 770px;font-size: 12px;margin: 0px auto;border: 1px solid #DFDFDF;color: #666666;padding-left: 20px;padding-top: 20px;}
			.commentInfo{margin-left: 20px;border-bottom: dashed 1px #999999;margin-top: 17px;display: inline-block;width: 700px;font-size: 14px;}
			.fyul li{float: left;list-style: none;height: 30px;display: inline-block;border: solid 1px #C0C0C0;width: 30px;line-height:30px;text-align:center; vertical-align: middle;}
			.fyul li a{text-decoration: none;color: #31B0D5;}
			.up{border-top-left-radius: 5px;border-bottom-left-radius: 5px;}
			.down{border-top-right-radius: 5px;border-bottom-right-radius: 5px;}
			.active{background: #31B0D5;}
			.active a{color: white !important;}
			.fyul{display: inline-block;}
			.fydiv{height: 50px;display: inline-block;width: 700px;text-align: center;}
			.hpdiv,.zpdiv,.cpdiv{display: none;}
		</style>
		<script type="text/javascript">
			function prodxianshi(op){
				var imgobj=document.getElementById("imgBig");
				
				imgobj.src="img/"+op;
			}
			function checkpl(op){
				var prodimgobj=document.getElementById("prodInfoImg");
				var prodInfocanshu=document.getElementById("prodInfocanshu");
				var prodcomment=document.getElementById("prodcomment");
				var xqspan=document.getElementById("xqspan");
				var xqa=document.getElementById("xqa");
				var plspan=document.getElementById("plspan");
				var pla=document.getElementById("pla");
				switch(op){
					case 1:
						prodcomment.style.display="none";
						prodimgobj.style.display="block";
						prodInfocanshu.style.display="block";
						xqspan.className="choosespan choosespan1";
						xqa.className="choosea choosea1";
						plspan.className="choosespan choosespan2";
						pla.className="choosea choosea2";
					break;
					case 2:
						prodcomment.style.display="block";
						prodimgobj.style.display="none";
						prodInfocanshu.style.display="none";
						xqspan.className="choosespan choosespan2";
						xqa.className="choosea choosea2";
						plspan.className="choosespan choosespan1";
						pla.className="choosea choosea1";
					break;
				}
			}
			
			function findpl(op){
				var qbdiv=document.getElementById("qupldiv");
				var hpdiv=document.getElementById("hpdiv");
				var zpdiv=document.getElementById("zpdiv");
				var cpdiv=document.getElementById("cpdiv");
				var pid=document.getElementById("pid").value;
				switch(op){
					case 'qb':
						hpdiv.style.display="none";
						zpdiv.style.display="none";
						cpdiv.style.display="none";
						qbdiv.style.display="block";
						location.href="ProductServlet?method=prodMain&&dengji=1&&pid="+pid;
						location.submit();
					break;
					case 'hp':
						hpdiv.style.display="block";
						zpdiv.style.display="none";
						cpdiv.style.display="none";
						qbdiv.style.display="none";
						location.href="ProductServlet?method=prodMain&&dengji=2&&pid="+pid;
						location.submit();
					break;
					case 'zp':
						hpdiv.style.display="none";
						zpdiv.style.display="block";
						cpdiv.style.display="none";
						qbdiv.style.display="none";
						location.href="ProductServlet?method=prodMain&&dengji=3&&pid="+pid;
						location.submit();
					break;
					case 'cp':
						hpdiv.style.display="none";
						zpdiv.style.display="none";
						cpdiv.style.display="block";
						qbdiv.style.display="none";
						location.href="ProductServlet?method=prodMain&&dengji=4&&pid="+pid;
						location.submit();
					break;
				}
			}
			$(function(){
				if($("#pl").prop("checked")){
					$("#hpdiv").css("display","none");
					$("#zpdiv").css("display","none");
					$("#cpdiv").css("display","none");
					$("#qupldiv").css("display","block");
				}
				if($("#hp").prop("checked")){
					$("#hpdiv").css("display","block");
					$("#zpdiv").css("display","none");
					$("#cpdiv").css("display","none");
					$("#qupldiv").css("display","none");
				}
				if($("#zp").prop("checked")){
					$("#hpdiv").css("display","none");
					$("#zpdiv").css("display","block");
					$("#cpdiv").css("display","none");
					$("#qupldiv").css("display","none");
				}
				if($("#cp").prop("checked")){
					$("#hpdiv").css("display","none");
					$("#zpdiv").css("display","none");
					$("#cpdiv").css("display","block");
					$("#qupldiv").css("display","none");
				}
				
			})
		</script>
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
		<div class="prodBig">
			<input type="hidden" value="${product.pid }" id="pid"/>
			<div class="prodTop">
				<div class="prodTopLeft">
					<!-- 这是大图片 --><img id="imgBig" src="${shopShow.img }"/><br /><br />
					
					<c:forEach var="productBigimage" items="${shopShow.img }"><!-- 这是小图片的循环 -->
						<!-- 这是商品的小图片 --><img class="imgxiao" onmouseover="prodxianshi('图片名')" src="图片路径"/>
					</c:forEach>
				</div>
				<div class="prodTopRight">
					<div class="prodTitle">
						<!-- 这里是商品名称 -->${shopShow.shopName }
					</div>
					<div class="prodTitle2">
						<!-- 商品小标题 -->${shopShow.shopName }
					</div>
					<div class="prodTime">
						<span id="jhs">
							聚划算
						</span>
						<span class="jhsInfo">
							此商品即将参加聚划算，
						</span>
						<span id="jhsTime">
							1天19小时
						</span>
						<span class="jhsInfo">
							后开始。
						</span>
					</div>
					<div class="prodInfo">
						<img src="img/gouwujuan.png"/>
						<span>
							全天猫实物商品通用
						</span>
						<br />
						<br />
						<span class="prodInfoLeft">
							<!-- 原价 -->
						</span>
						<span class="prodInfoRight1">
							 <!-- 本来是原价 -->
						</span>
						<br />
						<span class="prodInfoLeft">
							原价<!-- 促销价 -->
						</span>
						<span class="prodInfoRight2">
							 ¥99999<!--本来是促销价  -->
						</span>
					</div>
					<div class="proddetail">
						<div class="proddetailLeft">
							销量
							<span class="prodnum">
								<!-- 销量 -->0
							</span>
						</div>
						<div class="proddetailRight">
							累计评价
							<span class="prodnum">
								<!-- 评价数 -->0
							</span>
						</div>
					</div>
					<div class="prodNumber">
						库存<!-- 库存件数 -->100件
					</div>
					<div class="prodfuwu">
						<span class="prodfuwu1">
							服务承诺
						</span>
						<span class="prodfuwu2">
							<a href="">正品保证</a>
							<a href="">极速退款</a>
							<a href="">赠运费险</a>
							<a href="">七天无理由退换</a>
						</span>
					</div>
					<div class="prodInput">
						<a href="<%=request.getContextPath()%>/shop/carShop.action"><button class="button1">立即购买</button></a><!-- 死按钮，没写立即购买 -->
						<a href="<%=request.getContextPath()%>/shop/carShop.action"><button class="button2">加入购物车</button></a>
					</div>
				</div>
			</div>
			<div class="prodCenter">
				<div class="prodChoose">
					<span class="choosespan choosespan2" id="xqspan">
						<a href="javascript:checkpl(1)" class="choosea choosea2"  id="xqa">商品详情</a>
					</span>
					<span class="choosespan choosespan1" id="plspan">
						<a href="javascript:checkpl(2)" class="choosea choosea1" id="pla">累计评论</a>
						<!-- 评论数量 -->0
					</span>
				</div>
			</div>
			<div class="prodBottom">
				<div class="prodChooseHeight"  id="prodInfocanshu">
					<table border="0" class="prodInfotable">
						<tr>
							<td colspan="3" class="prodInfotabletitle">产品参数：</td>
						</tr>
						<tr>
							<c:forEach var="propertyDemo" items="">
							<td>商品属性名：商品属性值</td>
							</c:forEach>
						</tr>
					</table>
				</div>
				<div class="prodChooseLong"  id="prodInfoImg">
					<!--<c:forEach var="productShopimage" items="${productShopimageList }">
						<img src="img/${productShopimage.imgvalue }"/><br />
					</c:forEach>  这里是一些商品活动图-->
				</div>
				  <div class="comment" id="prodcomment" style="display: block;">
					<!-- 这里面checked是临时加上的，本来controller会返回一个值判断 --><input type="radio" name="pl" id="pl" value="qb" checked="checked" />全部评论
					<input type="radio" name="pl" id="hp" value="hp"/>好评
					<input type="radio" name="pl" id="zp" value="zp"/>中评
					<input type="radio" name="pl" id="cp" value="cp"/>差评
					<!-- 四个单选按钮上有onclick="findpl('qb')"  onclick="findpl('hp')" onclick="findpl('zp')" onclick="findpl('cp')" -->
					<div class="qupldiv" id="qupldiv">
						<c:forEach var="pev" items="${qbreviewList }">
						<div class="commentInfo">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr style="display: inline-block;width: 700px;">
									<td>${pev.nickname }</td>
									<td>${pev.createDate }</td>
									<td>评论等级${pev.dengji }</td>
								</tr>
								<tr style="display: inline-block;width: 700px;">
									<td colspan="3" style="display: inline-block;width: 700px;font-size: 12px;">${pev.content }</td>
								</tr>
							</table>
						</div>
						</c:forEach>
						<div class="fydiv">
							<ul class="fyul">
							<li class="up"><a href="javascript:">&lt;&lt;</a></li>
							<c:forEach var="i" begin="1" end="${qbcouunt }">
							<li class="active"><a href="">1</a></li>
							</c:forEach>
							<li class="down"><a href="javascript:">&gt;&gt;</a></li>
							</ul>
						</div>
					</div>
					</div>
				</div>
			
			</div>
		
</body>
</html>