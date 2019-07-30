var path=$("#path").val();


function  loadCategoryLevel(pid,cl,categoryLevel){
	$.ajax({
		type:"GET",//请求类型
		url:path+"/backend/categorylevellist",//请求的url
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
		url:path+"/backend/getAppFlatform",//请求的url
		data:{},//请求参数
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
	
	
	$(".checkApp").on("click",function(){
		var obj = $(this);
		var status = obj.attr("status");
		var vid = obj.attr("versionid");
		$.ajax({
			type:"post",
			url:path+"/backend/checkAPP",
			data:{id:$(this).attr("appinfoid"),versionid:$(this).attr("versionid")},
			dataType:"json",
			success:function(data){
				$("#name1").val(data.appInfo.id);
				$("#name2").val(data.appInfo.softwareName);
				$("#name3").val(data.appInfo.aPKName);
				$("#name4").val(data.appInfo.supportROM);
				$("#name5").val(data.appInfo.interfaceLanguage);
				$("#name6").val(data.appInfo.softwareSize);
				$("#name7").val(data.appInfo.downloads);
				$("#name8").val(data.appInfo.flatformName);
				$("#name9").val(data.appInfo.categoryLevel1Name+"->"
						+data.appInfo.categoryLevel2Name+"->"
						+data.appInfo.categoryLevel3Name);
				$("#name10").val(data.appInfo.statusName);
				$("#name11").val(data.appInfo.appInfo);
				$(".logo").attr("src",data.appInfo.logoPicPath);
			

	            $("#versionNo").val(data.appVersion.versionNo);
	            $("#versionSize").val(data.appVersion.versionSize);
	            $("#publishStatusName").val(data.appVersion.publishStatusName);
	            $("#versionInfo").val(data.appVersion.versionInfo);
	            $("#apkFileName").html(data.appVersion.apkFileName);
	            $("#downloadAPK").attr("apkFileName",data.appVersion.apkFileName);
	            
	            $(".changeStatus").attr("appInfoId",data.appInfo.id);
	            $(".changeStatus").attr("versionid",data.appInfo.versionId);
			}
		})
	});
	
	$(".changeStatus").on("click",function(){
		var versionid=$(this).attr("versionid")
		if(versionid==null || versionid=="" ){
			alert("审核失败，没有上传APK");
		}else{
			if($(this).attr("status")==2){
				if(confirm("确定审核通过吗？")){
					$.ajax({
						url:path+"/backend/changeStuatus",
						data:{appInfoId:$(this).attr("appInfoId"),status:$(this).attr("status")},
						type:"post",
						dataType:"json",
						success:function(data){
							if(data.result=="false"){
								alert("对不起，审核失败");
							}else{
								alert("审核成功");
								$("#myModal").modal('hide');
								window.location.reload();
							}
						}
					})
				}
			}else{
				if(confirm("确定审核不通过吗？")){
					$.ajax({
						url:path+"/backend/changeStuatus",
						data:{appInfoId:$(this).attr("appInfoId"),status:$(this).attr("status")},
						type:"post",
						dataType:"json",
						success:function(data){
							if(data.result=="false"){
								alert("对不起，审核失败");
							}else{
								alert("审核成功");
								$("#myModal").modal('hide');
								window.location.reload();
							}
						}
					})
				}
			}
			
		}
		
	})
	
	$("#downloadAPK").on("click",function(){
		var obj=$(this);
		window.location.href = path+"/backend/downloadAPK?apkFileName="+obj.attr("apkFileName");
	});
	

});



	
