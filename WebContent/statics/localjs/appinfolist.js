var path = $("#path").val();
function  loadCategoryLevel(pid,cl,categoryLevel){
	$.ajax({
		type:"GET",//请求类型
		url:path+"/dev/categorylevellist",//请求的url
		data:{pid:pid},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			
			$("#"+categoryLevel).html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				if(cl != null && cl != undefined && data[i].id == cl ){
					options += "<option selected=\"selected\" value=\""+data[i].id+"\" >"+data[i].categoryName+"</option>";
				}else{
					options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
				}
			}
			$("#"+categoryLevel).html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载分类列表失败！");
		}
	});
}
$(function(){
	var cl1 = $("#cl1").val();
	var cl2 = $("#cl2").val();
	var cl3 = $("#cl3").val();
	//动态加载一级分类列表
	loadCategoryLevel(0,cl1,"categoryLevel1");
	//动态加载二级分类列表
	loadCategoryLevel(cl1,cl2,"categoryLevel2");
	//动态加载三级分类列表
	loadCategoryLevel(cl2,cl3,"categoryLevel3");
	
	//联动效果：动态加载二级分类列表
	$("#categoryLevel1").change(function(){
		var categoryLevel1 = $("#categoryLevel1").val();
		if(categoryLevel1 != '' && categoryLevel1 != null){
			loadCategoryLevel(categoryLevel1,cl2,"categoryLevel2");
		}else{
			$("#categoryLevel2").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel2").html(options);
		}
		$("#categoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#categoryLevel3").html(options);
	});
	//联动效果：动态加载三级分类列表
	$("#categoryLevel2").change(function(){
		var categoryLevel2 = $("#categoryLevel2").val();
		if(categoryLevel2 != '' && categoryLevel2 != null){
			loadCategoryLevel(categoryLevel2,cl3,"categoryLevel3");
		}else{
			$("#categoryLevel3").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel3").html(options);
		}
	});
	
	//动态加载所属平台列表
	$.ajax({
		type:"GET",//请求类型
		url:path+"/dev/getAppFlatform",//请求的url
		data:{typeCode:"APP_FLATFORM"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			var fid = $("#fid").val();
			$("#flatformId").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				if(fid != null && fid != undefined && data[i].valueId == fid ){
					options += "<option selected=\"selected\" value=\""+data[i].valueId+"\" >"+data[i].valueName+"</option>";
				}else{
					options += "<option value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
				}
			}
			$("#flatformId").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载平台列表失败！");
		}
	});
	
	//动态加载APP状态
	$.ajax({
		type:"GET",//请求类型
		url:path+"/dev/getAppFlatform",//请求的url
		data:{typeCode:"APP_STATUS"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			var sta = $("#sta").val();
			$("#status").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				if(sta != null && sta != undefined && data[i].valueId == sta ){
					options += "<option selected=\"selected\" value=\""+data[i].valueId+"\" >"+data[i].valueName+"</option>";
				}else{
					options += "<option value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
				}
			}
			$("#status").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载平台列表失败！");
		}
	});
});

$("#addAppInfo").on("click",function(){
	window.location.href="addAppInfo";
});

$(".addVersion").on("click",function(){
	var obj = $(this);
	window.location.href="addAppversion?appId="+obj.attr("appinfoid");
});
$(".modifyVersion").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	var versionid = obj.attr("versionid");
	var appinfoid = obj.attr("appinfoid");
	if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
		if(versionid == null || versionid == ""){
			alert("该APP应用无版本信息，请先增加版本信息！");
		}else{
			window.location.href="modifyAppversion?versionId="+ versionid + "&appId="+ appinfoid;
		}
	}else{
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改其版本信息，只可进行【新增版本】操作！");
	}
});
$(".modifyAppInfo").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
		window.location.href="modifyAppInfo?id="+ obj.attr("appinfoid");
	}else{
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改！");
	}
});

//上下架判断
$(document).on("click",".saleSwichOpen,.saleSwichClose",function(){
	var obj = $(this);
	var appinfoid = obj.attr("appinfoid");
	var saleSwitch = obj.attr("saleSwitch");
	var status= obj.attr("status");
	var appsoftwarename=obj.attr("appsoftwarename");
	if("open" == saleSwitch){
		saleSwitchAjax(appinfoid,obj);
	}
	if("close" == saleSwitch){
		if(confirm("你确定要下架您的APP应用【"+appsoftwarename+"】吗？")){
			saleSwitchAjax(appinfoid,obj);
		}
	}
});

var saleSwitchAjax=function(appId,obj){
	$.ajax({
		type:"GET",
		url:path+"/dev/sale",
		data:{id:appId,status:obj.attr("status")},
		dataType:"json",
		success:function(data){
			if(data.errorCode == "0"){
				if(data.resultMsg == "success"){//操作成功
					if("open" == obj.attr("saleSwitch")){
						$("#appInfoStatus" + obj.attr("appinfoid")).html("已上架");
						obj.className="saleSwichClose";
						obj.html("下架");
						obj.attr("saleSwitch","close");
						obj.attr("status","4");
						$("#appInfoStatus" + obj.attr("appinfoid")).css({
							'background':'green',
							'color':'#fff',
							'padding':'3px',
							'border-radius':'3px'
						});
						$("#appInfoStatus" + obj.attr("appinfoid")).hide();
						$("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
					}else if("close" == obj.attr("saleSwitch")){
						$("#appInfoStatus" + obj.attr("appinfoid")).html("已下架");
						obj.className="saleSwichOpen";
						obj.html("上架");
						obj.attr("saleSwitch","open");
						obj.attr("status","5");
						$("#appInfoStatus" + obj.attr("appinfoid")).css({
							'background':'red',
							'color':'#fff',
							'padding':'3px',
							'border-radius':'3px'
						});		
						$("#appInfoStatus" + obj.attr("appinfoid")).hide();
						$("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
					}
				}else if(data.resultMsg == "failed"){//删除失败
					if("open" == obj.attr("saleSwitch")){
						alert("【"+obj.attr("appsoftwarename")+"】的【上架】操作失败");
					}else if("close" == obj.attr("saleSwitch")){
						alert("【"+obj.attr("appsoftwarename")+"】的【下架】操作失败");
					}
				}
			}else{
				if(data.errorCode == 'exception000001'){
					alert("对不起，系统出现异常，请联系IT管理员");
				}else if(data.errorCode == 'param000001'){
					alert("对不起，参数出现错误，您可能在进行非法操作");
				}
			}
		},
		error:function(data){
			if("open" == obj.attr("saleSwitch")){
				alert("【"+obj.attr("appsoftwarename")+"】的【上架】操作失败");
			}else if("close" == obj.attr("saleSwitch")){
				alert("【"+obj.attr("appsoftwarename")+"】的【下架】操作失败");
			}
		}
	});
};


$(".viewApp").on("click",function(){
	var obj = $(this);
	window.location.href="appinfoView/"+ obj.attr("appinfoid");
});

$(".deleteApp").on("click",function(){
	var obj = $(this);
	if(confirm("你确定要删除APP应用【"+obj.attr("appsoftwarename")+"】及其所有的版本吗？")){
		$.ajax({
			type:"GET",
			url:path+"/dev/delapp",
			data:{id:obj.attr("appinfoid")},
			dataType:"json",
			success:function(data){
				if(data.delResult == "true"){//删除成功：移除删除行
					alert("删除成功");
					obj.parents("tr").remove();
				}else if(data.delResult == "false"){//删除失败
					alert("对不起，删除AAP应用【"+obj.attr("appsoftwarename")+"】失败");
				}else if(data.delResult == "notexist"){
					alert("对不起，APP应用【"+obj.attr("appsoftwarename")+"】不存在");
				}
			},
			error:function(data){
				alert("对不起，删除失败");
			}
		});
	}
});

	
