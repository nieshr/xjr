<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>企业列表</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/area.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/client/css/ios6alert.css">
	<script src="/client/js/jquery-1.9.1.min.js"></script>
	<script src="/client/js/main.js"></script>
	<script src="/client/js/ios6alert.js"></script>
    <style type="text/css">
        .page{ width: 600px; float: right; margin-top: 30px;}
        .page *{ float: left;}
        .page span{ color: #333333; line-height: 20px; display: block;}
        .page a{  width: 20px; height: 20px;}
        .page .page_next{ width: 60px;}
        .page .page_last{width: 40px;}
        .page p{  margin-left: 10px;}
    </style>
    <script>
		function searchSubmit()
		{
			$("#searchform").submit();
		}
		
		   document.onkeydown = function(event){
			    if((event.keyCode || event.which) == 13){
			        $("#selectSubmit").click();
			    }
		   }    
		   
		function actionBtn(value)
		{
				$("body").ios6alert({
			    content : "将批量发送短信通知，确认操作？",
			    type : 1,
			    buttonText : {
					Yes : "确认",
					No : "取消"
				},
		    	onClickYes : function(){
					$("#actionValue").val(value);
					$("#actionForm").submit();
					 $("body").ios6alert({
	                          content : "操作成功！"
	                  });
				}
				});
		}   
		
		//全选取消按钮函数-初选
		function checkAll(chkobj) {
		    if ($(chkobj).text() == "全选") {
		        $(chkobj).children("span").text("取消");
		        $(".checkall input:enabled").prop("checked", true);
		    } else {
		        $(chkobj).children("span").text("全选");
		        $(".checkall input:enabled").prop("checked", false);
		    }
		}
		
		//全选取消按钮函数-取消
		function checkAll2(chkobj) {
		    if ($(chkobj).text() == "全选") {
		        $(chkobj).children("span").text("取消");
		        $(".checkall2 input:enabled").prop("checked", true);
		    } else {
		        $(chkobj).children("span").text("全选");
		        $(".checkall2 input:enabled").prop("checked", false);
		    }
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
            <dd><a href="/region/enterprise/list">企业列表</a></dd>
            <dd><a href="/region/activity/list">活动列表</a></dd>
             <#--<dd><a href="#">档案跟踪</a></dd>-->

		</dl>
	</div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
                <a href="#">区县管理</a>
                <p>&gt;</p>
                <a href="#">企业列表</a>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
       <div class="area_choice">
        	<form action="/region/enterprise/list" id="searchform">
        		<span>关键字:</span>
        		<input style="margin:0 14px 0 0;" class="area_text" name="keywords" type="text" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>" />
        		<select name="statusId" style="margin-left: 0px;"  onchange="javascript:searchSubmit(this);">>
        			<option value="">审核状态</option>
        			<option  value="0" <#if statusId??&&statusId?length gt 0&&statusId==0>selected="selected"</#if>>待审核</option>
        			<option  value="1" <#if statusId??&&statusId?length gt 0&&statusId==1>selected="selected"</#if>>已通过审核</option>
        			<option  value="3" <#if statusId??&&statusId?length gt 0&&statusId==3>selected="selected"</#if>>未通过审核</option>
        			<option  value="4" <#if statusId??&&statusId?length gt 0&&statusId==4>selected="selected"</#if>>未完善信息</option>
        		</select>
        		<select name="formType"  onchange="javascript:searchSubmit(this);">
        			<option value="" >类型</option>
        			<option value="0" <#if formType??&&formType?length gt 0&&formType==0>selected="selected"</#if> >企业  </option>
        			<option value="1" <#if formType??&&formType?length gt 0&&formType==1>selected="selected"</#if> >团队  </option>
        		</select>
        		<input id="selectSubmit" style="cursor:pointer;" class="area_Btn02" type="submit" value="确认筛选" />
        	</form>
        </div>        
       <#-- <div class="area_choice">
        		<span>关键字:</span>
        		<input style="margin: 0 14px 0 0;"  name="keywords"  class="area_text" type="text" value="" />
				<select name="keyword1" style="margin-left: 0px;">
        			<#if region_list??>
        				<#list region_list as item>
        					<option value="${item.title!''}">${item.title!''}</option>
        				</#list>
        			</#if>		
        		</select>
        		<select name="keyword2">
        			<#if enterpriseType_list??>
        				<#list enterpriseType_list as item>
        					<option value="${item.title!''}">${item.title!''}</option>
        				</#list>
        			</#if>		
        		</select>
        		<select name="isEnable">
        			<option value="true">通过</option>
        			<option value="false">未通过</option>
        		</select>
        		<input class="area_Btn02" type="submit" value="确认筛选" />
        </div>
        <div class="area_choice_two">
        	<span>一键筛选:</span>
        	<a href="#">未核验项目</a>
        	<p>丨</p>
        	<a href="#">已核验项目</a>
        	<p>丨</p>
        	<a href="#">已参加每周行活动</a>
        	<p>丨</p>
        	<a href="#">胜出项目</a>

        </div>
        -->
        <form action="/region/enterprise/list" id="actionForm" method=POST>
	        <input name="keywords" type="hidden" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>" />
	        <input name="statusId" type="hidden" value="<#if statusId??>${statusId}</#if>" />
	        <input name="formType" type="hidden" value="<#if formType??>${formType}</#if>" />
	        <input name="page" type="hidden" value="<#if page??>${page}</#if>" />
	        <input name="__ACTION" id="actionValue" type="hidden" value="" />
        
		<table class="new_list">
        	<tr class="list_title">
        		<th></th>
        		<th width="10%">编号</th>
        		<th  style="text-align : left; padding-left:40px ;">名称<th>
        		<th width="13%">手机</th>
        		<th width="12%">注册资本</th>
        		<th width="15%">法人代表</th>
        		<th width="20%">操作</th>
        	</tr>
        <#if enterprise_page??>
        	<#list enterprise_page.content as item>
	        	<tr>
	        		<td>
		        		<span class="checkall" style="vertical-align:middle;">
			        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;"  id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
			        		<input type="hidden" name="listId" id="listId" value="${item.id}">
			        	</span>	
	        		</td>		        	
	        		<td>${item.number!''}</td>
	        		<td style="text-align : left; padding-left:10px ;">
	        		<#if item.formType??&&item.formType == 0>
	        			<#if item.statusId ?? && item.statusId == 1>
	        				<img src="/client/images/n0.png" alt="已过审企业"  title="已过审企业"/>
	        			<#elseif item.statusId ?? && item.statusId == 0 >
	        				<img src="/client/images/n00.gif"   alt="待审核企业"  title="待审核企业"/>
	        			</#if>
	        		<#elseif item.formType??&&item.formType == 1>
	        			<#if item.statusId ?? && item.statusId == 1>
	        				<img src="/client/images/n1.png" alt="已过审项目"   title="已过审团队"/>
	        			<#elseif item.statusId ?? && item.statusId == 0 >
	        				<img src="/client/images/n11.gif" alt="待审核项目"   title="待审核团队"/>
	        			</#if>	      
	        		</#if>	  					
	        		<a href="/region/enterprise/check/${item.id?c!''}">${item.title!''}</a>
	        		<td>
	        		<td>${item.usermobile!''}</td>
	        		<td style="color:#529c15;"><#if item.capital??>${item.capital?c!''}</#if>万元</td>
	        		<td style="color:#e67817;">${item.representative!''}</td>
	        		<td>
	        		     <a href="/region/enterprise/check/${item.id?c!''}">详情</a>
	        		     | <a href="/region/message/detail?enterpriseId=${item.id?c!''}">站内信</a>
	        		     | <a href="/region/record/${item.id?c!''}">档案跟踪</a>
	        		   
	        		</td>
	        	</tr>
        	</#list>
        </#if>	   
        </table>
        </form>
        	<div class="area_add_btn" style="display: block; float: left;">
		        <a class="all" style="margin-left:0px;" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
				<input style=" margin-left: 10px; cursor:pointer;" class="area_batch" type="button" onclick="javascript:actionBtn('pass');" value="通过" />
				<input style=" margin-left: 10px; cursor:pointer;" class="area_batch" type="button" onclick="javascript:actionBtn('cancel');" value="未通过" />
				<input style=" margin-left: 10px; cursor:pointer;" class="area_batch" type="button" onclick="javascript:actionBtn('recall');" value="重新审核" />
			</div>
    </div> 
    
        <div class="page">
        <#if enterprise_page??>
        <#assign PAGE_DATA = enterprise_page>
             <#if PAGE_DATA??>
                 <#if PAGE_DATA.number+1 == 1>
                      <a disabled="disabled"  class="page_next">上一页</a>               
                 <#else>
                     <a href="/region/enterprise/list?page=${PAGE_DATA.number-1}&statusId=${statusId!''}&formType=${formType!''}&keywords=${keywords!''}"  class="page_next">上一页</a>                
                 </#if>
                 
                 <#assign continueEnter=false>
                 
                 <#if PAGE_DATA.totalPages gt 0>
                     <#list 1..PAGE_DATA.totalPages as page>
                         <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                             <#if page == PAGE_DATA.number+1>
                                 <a  class ="current" style="color:#e67817;">${page }</a>
                             <#else>
                                 <a href="/region/enterprise/list?page=${page-1}&statusId=${statusId!''}&formType=${formType!''}&keywords=${keywords!''}">${page}</a> 
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
                     <a href="/region/enterprise/list?page=${PAGE_DATA.number+1}&statusId=${statusId!''}&formType=${formType!''}&keywords=${keywords!''}" class="page_last">下一页</a> 
                 </#if>
             </#if>
            <p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
            </#if>
          </div>
    
    
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>