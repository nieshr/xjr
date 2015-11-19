<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动管理员-操作活动</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/active.css" rel="stylesheet" type="text/css" />
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/client/css/ios6alert.css">

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/ios6alert.js"></script>
<script>

function activityPass(activityId)
{
    $("body").ios6alert({
        content :"请稍后...",
        autoClose:5000
    });
         $.ajax({
             type: "GET",
             url: "/activity/pass",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             $("body").ios6alert({
                                 content : "审核成功",
	                          	 onClose : function(){
	                          	               location.reload();
	                          	            }
                             });
                            
                         }
                         else 
                         {
                             $("body").ios6alert({
                                 content : data.msg
                             });
                         }
                      }
         });
}

function sendSms(id,activityId,roleId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sendSms",
             contentType: "application/json; charset=utf-8",
             data: {"id":id,"activityId" : activityId,"roleId":roleId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             $("body").ios6alert({
                                 content : "已发送"
                             });
                         }
                         else 
                         {
                             $("body").ios6alert({
                                 content : data.msg
                             });
                         }
                      }
         });
}

function passCheck(activityId) {
    
	$("body").ios6alert({
	    title : "通过审核",
	    content : "更新列表到评分表，确认吗？",
	    type : 1,
	    onClickYes : function(){
	    	 $.ajax({
	             type: "GET",
	             url: "/activity/pass",
	             contentType: "application/json; charset=utf-8",
	             data: {"activityId" : activityId},
	             dataType: "json",
	             success: function(data){
	                         if (data.code == 0)
	                         {
	                             $("body").ios6alert({
	                                 content : "更新成功",
	                                 onClose : function(){
                                         location.reload();
                                      }
	                             });
	                         }
	                         else 
	                         {
	                             $("body").ios6alert({
	                                 content : data.msg
	                             });
	                         }
	                      }
	         });
	    }
	});

}

function cancelCheck(activityId) {
   $("body").ios6alert({
        title : "取消审核",
        content : "取消审核后将可以修改活动信息，确认吗？",
        type : 1,
        onClickYes : function(){
         $.ajax({
             type: "GET",
             url: "/activity/cancel",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             $("body").ios6alert({
                                 content : "取消成功",
                                 onClose : function(){
                                     location.reload();
                                  }
                             });
                         }
                         else 
                         {
                             $("body").ios6alert({
                                 content : data.msg
                             });
                         }
                      }
         });
       }
    });
}

function resetCheck(activityId) {
  $("body").ios6alert({
       title : "重置评分",
       content : "重置评分后将删除之前的评分表，确认吗？",
       type : 1,
       onClickYes : function(){	
         $.ajax({
             type: "GET",
             url: "/activity/reset",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             $("body").ios6alert({
                                 content : "重置成功",
                                 onClose : function(){
                                     location.reload();
                                  }
                             });
                         }
                         else 
                         {
                             $("body").ios6alert({
                                 content : data.msg
                             });
                         }
                      }
         });
       }
    });
}


function sortUp(id , activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sortUp",
             contentType: "application/json; charset=utf-8",
             data: {"id":id ,"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.reload();
                         }
                         else 
                         {
                             $("body").ios6alert({
                                 content : data.msg
                             });
                         }
                      }
         });
}

function sortDown(id , activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sortDown",
             contentType: "application/json; charset=utf-8",
             data: {"id":id ,"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.reload();
                         }
                         else 
                         {
                             $("body").ios6alert({
                                 content : data.msg
                             });
                         }
                      }
         });
}


</script>
<style>
.Validform_wrong {  background-position: 20px center;}
.Validform_right {  background-position: 7px center;}
</style>
</head>
<body>
<!--main-->
<div class="main">
<!--header-->
<#include "/client/user_common_header.ftl" />
<!--header_end-->
<!--content-->
<div class="content">
<!--left-->
    <div class="leftbar">
       <dl class="nav">
           <dd><a href="/activity/create">创建活动</a></dd>
           <dd><a href="/activity/list">活动列表</a></dd>
       </dl>
    </div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
        <dl class="crumb">
            <dt><a href="#"></a></dt>
            <dd>
                <p>当前所在位置:</p>
                <a href="#">活动操作</a>
                <p>&gt;</p>
                <a href="#">${activity.title!''}</a>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        
    </div>  
    <form action="/activity/submit" id="form1">
    <dl class="active_content">
        <dd>
                <#if activity.statusId??>
                    <#if activity.statusId==1>
                        <div><h3 style="margin:0 0 10px 50px;">活动状态：已审核</h3></div>
                    <#elseif activity.statusId==2>
                        <div><h3 style="margin:0 0 10px 50px;">活动状态：已结束</h3></div>
                    <#elseif activity.statusId==0>
                        <div><h3 style="margin:0 0 10px 50px;">活动状态：未审核</h3></div>
                    </#if>       
                </#if>         
                 
                <#if recommend_list??>
                <div>
                    <span style="margin-top: 6px;"><#if activity.statusId??&&activity.statusId==0||!activity.statusId??>推荐项目：<#else>路演项目：</#if></span>
                    <ul class="active_project_text">
                        <#if recommend_list??>
                            <#list recommend_list as item>
                                <li>
                                    <p class="p01" style="width: 380px; float: left;text-align:left;"><b style="float:left;">${item_index+1}.${item.enterpriseTitle!''}</b><#if item.win??&&item.win==1><img src="/client/images/n0.png" style="width:12px; height:12px;margin-left:3px;" title="胜出项目" alt="胜出" /> </#if></p>
                                    <a href="/activity/enterprise/check/${item.enterpriseId?c!''}"   target=_blank>查看</a>
                                    <a>丨</a>
                                    <#if item.isGrade??&&item.isGrade>
                                        <a  href="/enterprise/grade/?activityId=${item.activityId?c!''}&enterpriseId=${item.enterpriseId?c!''}" title="查看该项目的得分"   target="_blank">得分</a>
                                    <#else>
                                        <a   href="javascript:void(0)" title="评分尚未开始"  style="color:#666; " >得分</a>
                                    </#if>         
                                        <#if activity??&&activity.statusId??&&activity.statusId==1>
                                        	  <a>丨</a> 
                                            <a href="javascript:sendSms(${item.enterpriseId?c!''},${item.activityId?c!''},1);">短信通知</a>
                                           
                                        </#if>
                                        <#if activity.statusId??&&(activity.statusId==1|| activity.statusId == 0)>
                                         <a>丨</a> 
	                                        <#if item_has_next>
	                                            <a href="javascript:sortDown(${item.id?c!''} , ${item.activityId?c!''});"><img style="width:10px;height:13px;margin-top: 8px;"src="/client/images/down1.png" alt="下移" title="下移排序"/></a>
	                                        </#if>                                  
	                                        <#if item_index != 0>
	                                            <a href="javascript:sortUp(${item.id?c!''} , ${item.activityId?c!''});"><img style="width:10px;height:13px;margin-top: 8px; <#if item_has_next>margin-left:3px;</#if>" src="/client/images/up1.png" alt="上移" title="上移排序"/></a>
	                                        </#if>
                                        </#if>
			    						<#if item.enterpriseFileUrl??>
				    						<a>丨</a>
				    						<a href="/download/data?name=${item.enterpriseFileUrl!''} " title="点击下载文件">扫描件</a>
			    						</#if>
			    						<#if item.dataBusiness??>
				    						<a>丨</a>
				    						<a href="/download/data?name=${item.dataBusiness!''}" title="点击下载文件">商业计划书</a>
			    						</#if>
			    						<#if item.dataPossible??>
				    						<a>丨</a>
				    						<a href="/download/data?name=${item.dataPossible!''}" title="点击下载文件">可行性报告</a>
			    						</#if>
			    						<#if item.dataOther??>
				    						<a>丨</a>
				    						<a href="/download/data?name=${item.dataOther!''}" title="点击下载文件">其他资料</a>
			    						</#if>

                                </li>
                            </#list>
                        </#if>    
                        <#if pagetype??&& pagetype == "check">
                            <#if activity??&&activity.fileUrl??>
                                <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff;" type="button" onclick="location.href='/download/data?name=${activity.fileUrl!''}'" value="推荐表下载" />
                            <#else>
                                <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#666;color:#fff;" type="button"  value="推荐表下载" />
                            </#if>  
                            <#if mark??&&mark="activity">
                                <#if activity??&&activity.statusId??&&activity.statusId==1>
                                    <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:green;color:#fff; " type="button" class="area_batch" onclick="location.href='/activity/getCoach?activityId=${activity.id?c!''}'"  value="分配路演辅导" />
                                    <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#E4574E;color:#fff; " type="button" onclick="javascript:cancelCheck(${activity.id?c!''});"  value="取消审核" />
                                    <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff; " type="button" onclick="javascript:resetCheck(${activity.id?c!''});"  value="重置评分" />
                                <#elseif activity??&&activity.statusId??&&activity.statusId==0> 
                                    <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:green;color:#fff; " type="button" class="area_batch" onclick="location.href='/activity/getCoach?activityId=${activity.id?c!''}'"  value="分配路演辅导" />
                                    <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff; " type="button" class="area_batch" onclick="javascript:activityPass(${activity.id?c!''});" value="通过审核" />
                                <#elseif activity??&&activity.statusId??&&activity.statusId==2>     
                                    <input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff; " type="button" onclick="javascript:resetCheck(${activity.id?c!''});"  value="重置评分" />
                                </#if>
                            </#if>
                            <#if type??&& type=="supervisor">
                            	<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff; " type="button" onclick="location.href='/region/recommendEnterprise?id=${activity.id?c!''}'"  value="推荐项目" />
                        	</#if>
                        <#else>
                                <input id="selectEnterprise" style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加项目" />
                        </#if>
                    </ul>
                </div> 
                </#if>
                <div style="margin-top:50px;">
                    <span style="margin-top: 6px;">评委专家：</span>
                    <ul class="active_project_text">
                        <#if selected_expert_list??>
                            <#list selected_expert_list as item>
                                <li>
                                    <p class="p01" style="  width: 250px;float: left; text-align: left;">${item_index+1}.${item.name!''}</p>
                                    <a style="display:block;  width:100px;"></a>

                                    <#if activity.statusId??&&activity.statusId==0>
                                        <a href="javascript:void(0)" title="评分尚未开始" style="color:#666;" target="_blank">评分情况</a>
                                    <#else>
                                        <a href="/activity/search/grade?activityId=${activity.id?c!''}&expertId=${item.expertId?c!''}" title="查看该评委的评分详情并导出表格" target="_blank">评分情况</a>
                                    </#if>
                                    <#if activity??&&activity.statusId??&&activity.statusId==1&&mark??&&mark="activity">
                                        <a>丨</a> 
                                        <a href="javascript:sendSms(${item.expertId?c!''},${item.activityId?c!''},3);">短信通知</a>
                                    </#if>
                                </li>
                            </#list>
                        </#if>    
                        <#if pagetype??&& pagetype == "check">
                        <#else>
                           <input id="selectExpert" style="cursor:pointer;width:100px; height:30px; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button"  value="添加评委" />
                        </#if>
                    </ul>
                </div>
               <#if roadshow_list??>
               <div style="margin-top:50px;">
                    <span style="margin-top: 6px;">路演辅导：</span>
                    <ul class="active_project_text">
                            <#list roadshow_list as item>
                                <li>
                                    <p class="p01" style="  width: 250px;float: left; text-align: left;">${item_index+1}.${item.expertName!''}</p>
                                    <a style="display:block;  width:100px;"></a>
                                </li>
                            </#list>
                    </ul>
                </div>
                </#if> 
            <!-- 评分汇总 -->
                <#if activity??&&activity.statusId??&&activity.statusId==1>
                    <div style="margin-top:20px;">
                        <span style="margin-top: 6px;">评分结果：</span>
                        <ul class="active_project_text">
                            <li>
                                <input type="button" class="area_batch" onclick="javascript:window.open('/activity/getGrade?activityId=${activity.id?c!''}&mark=activity')"  class="p01" value="选择胜出项目" />
                                <a style="display:block;  width:60px;"></a>
                            </li>
                        </ul>
                    </div>
                <#elseif activity??&&activity.statusId??&&activity.statusId==2>
                     <div style="margin-top:20px;">
                        <span style="margin-top: 6px;">路演结果：</span>
                        <ul class="active_project_text">
                            <li>
                                <input type="button" class="area_batch" onclick="javascript:window.open('/activity/getGrade?activityId=${activity.id?c!''}&mark=activity')"  class="p01" value="查看排名" />
                                <a style="display:block;  width:60px;"></a>
                            </li>
                        </ul>
                    </div>                   
                </#if>    
                <!-- 评分汇总 end-->
    </dl>
    </form>
    <#if activity??>
     <h2 style="margin:20px 0 20px 20px;">附件：</h2>
    <dl class="active_content">
        <dd>
         <#if pagetype?? && pagetype == "check">
         <#else>
        <form id="upload" enctype="multipart/form-data" action="/client/activity/upload" method="post">
        <input type="hidden" id="id" name="id" <#if activity??>value="${activity.id?c}"</#if>></input>
        <div>
            <span>添加文件：</span>
            <input name="Filedata" type="file" id="file" value="" />
            <input  style="margin-left:20px;    border-radius: 8px;" class="area_save_btn" type="button" onclick="javascript:submitCheck();" value="上传" />
        </div>
        </form>
        </#if>
        <#if activity?? && activity.download??>
            <div>
                <span style="margin-top: 10px;">相关下载：</span>
                <ul class="active_add_file">
                    <li>
                        <img src="/client/images/active_file.png" />
                        <p class="p01">${activity.download!''}</p>
                        <a href="/download/data?name=${activity.download!''}">下载</a>
                    </li>
                </ul>
            </div>
        </#if>  
        </dl>
        
        <#if pagetype??&&pagetype == "check">
    <dl class="active_content">
        <dd>
        <input type="button" value="PPT模板下载" onclick="location.href='/download/data?name=${activity.pptUrl!''}'"  style="margin:10px 0 30px 48px;    border-radius: 8px;
                                                float: left;
                                                height: 26px;
                                                line-height: 26px;
                                                width: 170px;"></input>
        </dd>
        </dl>
        <#else>
            <dl class="active_content" style="margin-top:50px;">
        <dd>
        <form id="pptupload" enctype="multipart/form-data" action="/client/activity/pptupload" method="post">
        <input type="hidden" id="id" name="id" <#if activity??>value="${activity.id?c}"</#if>></input>
        <div>
            <span>添加PPT模板：</span>
            <input name="Filedata" type="file" id="pptfile" value="" />
            <input type="button" value="PPT模板上传" onclick="javascript:pptSubmitCheck();"  style="margin:-5px 0 30px 48px;
                                                             border-radius: 8px;
                                                            float: left;
                                                            height: 26px;
                                                            line-height: 26px;
                                                            width: 170px;"></input>
        
        </div>
        </form>
        <#if activity??&&activity.pptUrl??>
             <div>
                <span style="margin-top: 10px;">已上传PPT模板：</span>
                <ul class="active_add_file">
                    <li>
                        <img src="/client/images/active_file.png" />
                        <p class="p01">${activity.pptUrl!''}</p>
                        <a href="/download/data?name=${activity.pptUrl!''}">下载</a>
                    </li>
                </ul>
            </div>
        </#if>

        </#if>

    </#if>
        
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
