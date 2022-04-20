var validatejjj=false;
var uname=false;
var nickname=false;
var upass=false;
var uqpass=false;
var uphone=false;
var uage=false;
var uimg=false;
(function($){
    $.fn.drag = function(options){
        var x, drag = this, isMove = false, defaults = {
        };
        var options = $.extend(defaults, options);
        //添加背景，文字，滑块
        var html = '<div class="drag_bg"></div>'+
                    '<div class="drag_text" onselectstart="return false;" unselectable="on">拖动滑块验证</div>'+
                    '<div class="handler handler_bg"></div>';
        this.append(html);
        
        var handler = drag.find('.handler');
        var drag_bg = drag.find('.drag_bg');
        var text = drag.find('.drag_text');
        var maxWidth = drag.width() - handler.width();  //能滑动的最大间距
        
     
        
        //鼠标按下时候的x轴的位置
        handler.mousedown(function(e){
            isMove = true;
            x = e.pageX - parseInt(handler.css('left'), 10);
        });
        
        //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
        $(document).mousemove(function(e){
            var _x = e.pageX - x;
            if(isMove){
                if(_x > 0 && _x <= maxWidth){
                    handler.css({'left': _x});
                    drag_bg.css({'width': _x});
                   
                }else if(_x > maxWidth){  //鼠标指针移动距离达到最大时清空事件
                    dragOk();                
                 
                }
            }
        }).mouseup(function(e){
            isMove = false;
            var _x = e.pageX - x;
            if(_x < maxWidth){ //鼠标松开时，如果没有达到最大距离位置，滑块就返回初始位置
                handler.css({'left': 0});
                drag_bg.css({'width': 0});
                //验证失败
                validatejjj=false;
            }
        });
        
        //清空事件
        function dragOk(){
            handler.removeClass('handler_bg').addClass('handler_ok_bg');
            text.text('验证通过');          
            drag.css({'color': '#fff'});
            handler.unbind('mousedown');
            $(document).unbind('mousemove');
            $(document).unbind('mouseup');
           //验证成功
           $("#xyb1").css("background","#ff0036");
            validatejjj=true;            
        }
    };
     
})(jQuery);
$(function(){

	$("input[class='yhmip'][type='text']").focus(function(){
		$("#uname").css("color","#999999");
		$("#uname").html("长度4—12(数字字母下划线)");
	});
	$("input[class='yhmip'][type='text']").blur(function(){
		uname=false;
		if($(this).val().length==0){
			$("#uname").html("账户名为空");
			$("#uname").css("color","#C40000");
			uname=false;
			return;
		}
		var namereg=/^\w{4,12}$/;
		if(namereg.test($(this).val())){
			$("#uname").html("账户名格式正确");
			$("#uname").css("color","#2DA77A");
			uname=true;
			$.post("UserServlet",{"method":"registerAJAX","uname":$(this).val()},function(obj){				
				var jsonObj=eval("("+obj+")");
				if(jsonObj.msg=="true"){
					$("#uname").html("账户名已存在");
					$("#uname").css("color","#C40000");
					uname=false;
					return;
				}else{
					$("#uname").html("账户名可以使用");
					uname=true;
					return;
				}
			});			
			return;
		}else{			
			$("#uname").html("账户名格式错误");
			$("#uname").css("color","#C40000");
			uname=false;
			return;
		}
	});
	
	$("#nickname").focus(function(){
		$("#tnickname").css("color","#999999");
		$("#tnickname").html("长度2—7(汉字或数字字母下划线)");
	});
	$("#nickname").blur(function(){
		nickname=false;
		if($(this).val().length==0){
			$("#tnickname").html("昵称为空");
			$("#tnickname").css("color","#C40000");
			nickname=false;
			panduan2();
			return;
		}
		var nicknamereg=/^[\w\u4e00-\u9fa5]{2,7}$/;
		if(nicknamereg.test($(this).val())){
			$("#tnickname").html("昵称格式正确");
			$("#tnickname").css("color","#2DA77A");
			nickname=true;
			panduan2();
			return;
		}else{
			$("#tnickname").html("昵称格式错误");
			$("#tnickname").css("color","#C40000");
			nickname=false;
			panduan2();
			return;
		}
	});
	
	$("#pass").focus(function(){
		$("#tpass").css("color","#999999");
		$("#tpass").html("长度6—10(数字字母下划线)");
	});
	$("#pass").blur(function(){
		upass=false;
		if($(this).val().length==0){
			$("#tpass").html("密码为空");
			$("#tpass").css("color","#C40000");
			upass=false;
			panduan2();
			return;
		}
		var nicknamereg=/^\w{6,10}$/;
		if(nicknamereg.test($(this).val())){
			$("#tpass").html("密码格式正确");
			$("#tpass").css("color","#2DA77A");
			if($(this).val()==$("#qpass").val()){
				$("#tqpass").html("确认密码正确");
				$("#tqpass").css("color","#2DA77A");
				uqpass=true;
				panduan2();
			}
			upass=true;
			panduan2();
			return;
		}else{
			$("#tpass").html("密码格式错误");
			$("#tpass").css("color","#C40000");
			upass=false;
			panduan2();
			return;
		}
	});
	
	$("#qpass").focus(function(){
		$("#tqpass").css("color","#999999");
		$("#tqpass").html("确认密码必须和密码一致");
	});
	$("#qpass").blur(function(){
		uqpass=false;
		if($(this).val().length==0){
			$("#tqpass").html("确认密码为空");
			$("#tqpass").css("color","#C40000");
			uqpass=false;
			panduan2();
			return;
		}
		var pass=$("#pass").val();
		if(pass==$(this).val()){
			$("#tqpass").html("确认密码正确");
			$("#tqpass").css("color","#2DA77A");
			uqpass=true;
			panduan2();
			return;
		}else{
			$("#tqpass").html("确认密码错误");
			$("#tqpass").css("color","#C40000");
			uqpass=false;
			panduan2();
			return;
		}
	});
		
	$("#phone").focus(function(){
		$("#tphone").css("color","#999999");
		$("#tphone").html("长度11");
	});
	$("#phone").blur(function(){
		uphone=false;
		if($(this).val().length==0){
			$("#tphone").html("手机号为空");
			$("#tphone").css("color","#C40000");
			uphone=false;
			panduan2();
			return;
		}
		var phonereg=/^1([38]\d|5[0-35-9]|7[3678])\d{8}$/;
		if(phonereg.test($(this).val())){
			$("#tphone").html("手机号正确");
			$("#tphone").css("color","#2DA77A");
			uphone=true;
			panduan2();
			return;
		}else{
			$("#tphone").html("手机号错误");
			$("#tphone").css("color","#C40000");
			uphone=false;
			panduan2();
			return;
		}
	});
	
	$("#age").focus(function(){
		$("#tage").css("color","#999999");
		$("#tage").html("1-120(数字)");
	});
	$("#age").blur(function(){
		uage=false;
		if($(this).val().length==0){
			$("#tage").html("年龄为空");
			$("#tage").css("color","#C40000");
			uage=false;
			panduan2();
			return;
		}
		var phonereg=/^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
		if(phonereg.test($(this).val())){
			$("#tage").html("年龄格式正确");
			$("#tage").css("color","#2DA77A");
			uage=true;
			panduan2();
			return;
		}else{
			$("#tage").html("年龄格式错误");
			$("#tage").css("color","#C40000");
			uage=false;
			panduan2();
			return;
		}
	});	
	
    $().ready(function () {
        $("#uimg").blur(function () {
            var file = $("#uimg").val();
            if (file == "") {
                $("#timg").html("请选择要上传的文件");
                $("#timg").css("color","#C40000");
                uimg=false;
                panduan2();
                return;
            } else {
                //检验文件类型是否正确
                var exec = (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
                if (exec != "jpg") {
                    $("#timg").html("文件格式不对，请上传jpg文件!");
                    $("#timg").css("color","#C40000");
                    uimg=false;
                    panduan2();
                    return;
                }
            }
            $("#timg").html("图片已上传");
            $("#timg").css("color","#2DA77A");
            uimg=true;
            panduan2();
            return;
        });
    });

	
	$("button[class='tyxy']").click(function(){
		$("div[class='xieyi']").css("display","none");
		$("div[class='register']").css("display","block");
	}); 	
});
function panduan1(){
	if(uname==true&&validatejjj==true){
		document.getElementById("qruname").innerHTML=document.getElementById("inputuname").value;
		document.getElementById("register1").style.display="none";
		document.getElementById("register2").style.display="block";
		document.getElementById("titli1").className="registertitleindexm";
		document.getElementById("titspan1").className="registertitlespanm";
		document.getElementById("titli2").className="registertitleindex xiahuaxian";
		document.getElementById("titspan2").className="registertitlespan";
	}
}
function shangyb2(){
	document.getElementById("register2").style.display="none";
	document.getElementById("register1").style.display="block";
	document.getElementById("titli2").className="registertitleindexm";
	document.getElementById("titspan2").className="registertitlespanm";
	document.getElementById("titli1").className="registertitleindex xiahuaxian";
	document.getElementById("titspan1").className="registertitlespan";
}
function shangyb3(){
	document.getElementById("register3").style.display="none";
	document.getElementById("register2").style.display="block";
	document.getElementById("titli3").className="registertitleindexm";
	document.getElementById("titspan3").className="registertitlespanm";
	document.getElementById("titli2").className="registertitleindex xiahuaxian";
	document.getElementById("titspan2").className="registertitlespan";
}
function xiayb2(){
	if(nickname==true&&upass==true&&uqpass==true&&uphone==true&&uage==true&&uimg==true){
		document.getElementById("register2").style.display="none";
		document.getElementById("register3").style.display="block";
		document.getElementById("titli2").className="registertitleindexm";
		document.getElementById("titspan2").className="registertitlespanm";
		document.getElementById("titli3").className="registertitleindex xiahuaxian";
		document.getElementById("titspan3").className="registertitlespan";
	}
}
function panduan2(){
	if(nickname==true&&upass==true&&uqpass==true&&uphone==true&&uage==true&&uimg==true){
		document.getElementById("xyb2").style.background="#ff0036";
	}else{
		document.getElementById("xyb2").style.background="#ededed";
	}
}

