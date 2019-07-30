var path=$("#path").val();

$(function(){
	//点击审核通过
	$("#pass").on("click",function(){
		var obj=$(this);
		if(confirm("确定审核通过吗？")){
			$("#content").show().delay(3000).hide(300);
			
			window.location.href = path+"/backend/updateAPPStatus?appInfoId="+obj.attr("appInfoId")+"&status="+obj.attr("status");
			return true;
		}
		else{
			return false;
		}
	});
	
	//点击审核不通过
	$("#noPass").on("click",function(){
		var obj=$(this);
		if(confirm("确定审核不通过吗？")){
			$("#content").show().delay(3000).hide(300);
			
			window.location.href = path+"/backend/updateAPPStatus?appInfoId="+obj.attr("appInfoId")+"&status="+obj.attr("status"); 
			return true;
		}
		else{
			return false;
		}
	});
	
	//点击返回按钮
	$("#back").on("click",function(){
		window.location.href = path+"/backend/appList";  
	});
	
	$("#downloadAPK").on("click",function(){
		var obj=$(this);
		window.location.href = path+"/backend/downloadAPK?apkFileName="+obj.attr("apkFileName");
	});
});