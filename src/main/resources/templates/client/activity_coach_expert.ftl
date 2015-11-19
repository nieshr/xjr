<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<style type="text/css">
		.page{ width: 600px; float: right; margin-top: 30px;}
		.page *{ float: left;}
		.page span{ color: #333333; line-height: 20px; display: block;}
		.page a{  width: 20px; height: 20px;}
		.page .page_next{ width: 60px;}
		.page .page_last{width: 40px;}
		.page p{  margin-left: 10px;}
	</style>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>活动管理员-添加路演辅导</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/area.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript">
    function addCoachExpert(id , activityId)
	{
	    $.ajax({
	        type:"post",
	        url:"/activity/addCoach",
	        data:{"expertId":id,"activityId":activityId},
	        success:function(data){
				if (data.code == 0)
				{
					location.reload();
				}
				else
				{
					alert(data.msg);
				}
	           
	        }
	    });
	}
	
function removeCoachExpert(id , activityId)
	{
	    $.ajax({
	        type:"post",
	        url:"/activity/removeCoach",
	        data:{"expertId":id,"activityId":activityId},
	        success:function(data){
				if (data.code == 0)
				{
					location.reload();
				}
				else
				{
					alert(data.msg);
				}
	           
	        }
	    });
	}
</script>
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

                <a href="#">添加路演辅导</a>
				<p>&gt;</p>
                <a href="#">${activity.title!''}</a>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        <div class="area_choice">
        	<form action="/activity/getCoach" >
        		<input type="hidden" name="activityId" value="${activityId?c!''}" />
        		<span>姓名检索:</span>
        		<input style="margin:0 14px 0 0;" class="area_text" name="keywords" type="text" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>" />
        		<input style="cursor:pointer;" class="area_Btn02" type="submit" value="搜索" />
        	</form>
        </div>
        
        
       <!-- <form name="addExpert" action="/activity/addExperts">-->
	        <div class="list_base2" style="padding-top:0;">
				<table class="new_list">
		        	<tr class="list_title">
		        		<th width="30%">姓名</th>
		        		<th width="25%">联系电话</th>
		        		<th width="25%">邮箱</th>
		        		<th width="20%">操作</th>
		        	</tr>
		        <#if ExpertPage??>
		        	<#list ExpertPage.content as item>
			        	<tr>
			        		<#--
			        		<td>
				        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
				        		<input type="hidden" name="listId" id="listId" value="${item.id}">
			        		</td>
			        		-->
			        		<td>${item.name!''}</td>
			        		<td style="color:#0ab2cb;">${item.usermobile!''}</td>
			        		<td style="color:#e67817;">${item.email!''}</td>
			        		<#if item.roadshowActivityId??&&item.roadshowActivityId==activityId>
			        			<td><a href="javascript:removeCoachExpert('${item.id?c}' , ${activityId?c!''})" style="color:#666;" title="取消该路演辅导">取消</td>
			        		<#else>
			        			<td><a href="javascript:addCoachExpert('${item.id?c}' , ${activityId?c!''})">确定</a></td>
			        		</#if>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>
	        </div>
	        <div class="area_add_btn">
			<!--<input style=" margin-left: 0px; cursor:pointer;" type="submit" value="批量加入预选" />-->
			</div>
		<!--</form>-->
		<div class="page">
		<#if ExpertPage??>
		<#assign PAGE_DATA = ExpertPage>
		  	 <#if PAGE_DATA??>
				 <#if PAGE_DATA.number+1 == 1>
			          <a disabled="disabled"  class="page_next">上一页</a>               
			     <#else>
			         <a href="/activity/getCoach?page=${PAGE_DATA.number-1}&activityId=${activityId?c!''}&keywords=${keywords!''}"  class="page_next">上一页</a>                
			     </#if>
			     
			     <#assign continueEnter=false>
			     
			     <#if PAGE_DATA.totalPages gt 0>
			         <#list 1..PAGE_DATA.totalPages as page>
			             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
			                 <#if page == PAGE_DATA.number+1>
			                     <a  class ="current" style="color:#e67817;">${page }</a>
			                 <#else>
			                     <a href="/activity/getCoach?page=${page-1}&activityId=${activityId?c!''}&keywords=${keywords!''}">${page}</a> 
			                 </#if>
			                 <#assign continueEnter=false>
			             <#else>
			                 <#if !continueEnter>
			                     ...
			                     <#assign continueEnter=true>
			                 </#if>
			             </#if>
			         </#list>
			     </#if>
			     
			     
			     <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
			         <a disabled="disabled" class="page_last">下一页</a> 
			     <#else>
			         <a href="/activity/getCoach?page=${PAGE_DATA.number+1}&activityId=${activityId?c!''}&keywords=${keywords!''}" class="page_last">下一页</a> 
			     </#if>
			 </#if>
		  	<p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
		  	</#if>
		  </div>
		  <input style="cursor:pointer;" class="area_save_btn" style="margin-left:45%;"type="button" onclick="location.href='/activity/check?id=${activityId?c!''}'" value="完成" />
    </div> 
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>