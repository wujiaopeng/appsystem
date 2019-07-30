var path = $("#path").val();
var errorMsg=null;
$(function(){
	errorMsg=$("#errorMsg");
	//动态加载所属平台列表
	$.ajax({
		type:"GET",//请求类型
		url:path+"/dev/getAppFlatform",//请求的url
		data:{typeCode:"APP_FLATFORM"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			$("#flatformId").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				options += "<option value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
			}
			$("#flatformId").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载平台列表失败！");
		}
	});  
	//动态加载一级分类列表
	$.ajax({
		type:"GET",//请求类型
		url:path+"/dev/categorylevellist",//请求的url
		data:{pid:0},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			$("#categoryLevel1").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
			}
			$("#categoryLevel1").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载一级分类列表失败！");
		}
	});  
	//动态加载二级分类列表
	$("#categoryLevel1").change(function(){
		var categoryLevel1 = $("#categoryLevel1").val();
		if(categoryLevel1 != '' && categoryLevel1 != null){
			$.ajax({
				type:"GET",//请求类型
				url:path+"/dev/categorylevellist",//请求的url
				data:{pid:categoryLevel1},//请求参数
				dataType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//data：返回数据（json对象）
					$("#categoryLevel2").html("");
					var options = "<option value=\"\">--请选择--</option>";
					for(var i = 0; i < data.length; i++){
						options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
					}
					$("#categoryLevel2").html(options);
				},
				error:function(data){//当访问时候，404，500 等非200的错误状态码
					alert("加载二级分类失败！");
				}
			});
		}else{
			$("#categoryLevel2").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel2").html(options);
		}
		$("#categoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#categoryLevel3").html(options);
	});
	//动态加载三级分类列表
	$("#categoryLevel2").change(function(){
		var categoryLevel2 = $("#categoryLevel2").val();
		if(categoryLevel2 != '' && categoryLevel2 != null){
			$.ajax({
				type:"GET",//请求类型
				url:path+"/dev/categorylevellist",//请求的url
				data:{pid:categoryLevel2},//请求参数
				dataType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//data：返回数据（json对象）
					$("#categoryLevel3").html("");
					var options = "<option value=\"\">--请选择--</option>";
					for(var i = 0; i < data.length; i++){
						options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
					}
					$("#categoryLevel3").html(options);
				},
				error:function(data){//当访问时候，404，500 等非200的错误状态码
					alert("加载三级分类失败！");
				}
			});
		}else{
			$("#categoryLevel3").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel3").html(options);
		}
	});
	
	$("#back").on("click",function(){
		window.location.href = "appInfoList";
	});
	$("#APKName").bind("blur",function(){
		//ajax后台验证--APKName是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:path+"/dev/apkexist",//请求的url
			data:{APKName:$("#APKName").val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.APKName == "empty"){//参数APKName为空，错误提示
					//alert("APKName为不能为空！");
					$(".message2").html("APKName为不能为空！");
				}else if(data.APKName == "exist"){//账号不可用，错误提示
					$(".message2").html("该APKName已存在，不能使用！");
				}else if(data.APKName == "noexist"){//账号可用，正确提示
					alert("该APKName可以使用！");
					$(".message2").html("");
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("请求错误！");
			}
		});
	});
	$("#softwareName").bind("blur",function(){
		if($("#softwareName").val() == null || $("#softwareName").val() == ""){
			$(".message1").html("软件名不能为空！");
		}else{
			$(".message1").html("");
		}
	});
	$("#supportROM").bind("blur",function(){
		if($("#supportROM").val() == null || $("#supportROM").val() == ""){
			$(".message3").html("软件支持的ROM不能为空！");
		}else{
			$(".message3").html("");
		}
	});
	$("#interfaceLanguage").bind("blur",function(){
		if($("#interfaceLanguage").val() == null || $("#interfaceLanguage").val() == ""){
			$(".message4").html("软件的界面语言不能为空！");
		}else{
			$(".message4").html("");
		}
	});
	$("#softwareSize").bind("blur",function(){
		if($("#softwareSize").val() == null || $("#softwareSize").val() == ""){
			$(".message5").html("软件的大小不能为空！");
		}else{
			$(".message5").html("");
		}
	});
	//判断是否有错误
	if(errorMsg.val()!=null && errorMsg.val()!=""){
		$(".message10").html(errorMsg.val());
	}else{
		$(".message10").html("");
	}
});
      
      
      