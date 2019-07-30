
$(function(){  
	$("#back").on("click",function(){
		window.location.href = "appInfoList";
	});
	$("#versionNo").bind("blur",function(){
		if($("#versionNo").val() == null || $("#versionNo").val() == ""){
			$(".message1").html("版本号不能为空！");
		}else{
			$(".message1").html("");
		}
	});
	$("#versionSize").bind("blur",function(){
		if($("#versionSize").val() == null || $("#versionSize").val() == ""){
			$(".message2").html("版本大小不能为空！");
		}else{
			$(".message2").html("");
		}
	});
	$("#versionInfo").bind("blur",function(){
		if($("#versionInfo").val() == null || $("#versionInfo").val() == ""){
			$(".message3").html("版本简介不能为空！");
		}else{
			$(".message3").html("");
		}
	});
});
      
      
      