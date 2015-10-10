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
	<title>添加项目</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/area.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
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
			<dd><a href="#">创建活动</a></dd>
		</dl>
	</div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>

                <a href="#">活动列表</a>
				<p>&gt;</p>
                <a href="#">预选企业/团队</a>
            </dd>
            <dt class="crumb_back"><a  href="#">返回上一页</a></dt>
        </dl>
        <div class="area_choice">
        		<input class="area_text" name="keywords" type="text" value="" />
        		<span>关键字:</span>
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
        		<select name="statusId">
        			<option value="true">通过</option>
        			<option value="false">未通过</option>
        		</select>
        		<input class="area_Btn02" type="submit" value="确认筛选" />
        </div>
        <div class="list_base2">
        	<dl>
        		<dt>备选企业列表</dt>
        		<dd><input type="checkbox" value=""/><a href="#">太阳能电风有限公司</a></dd>
        		<dd><input type="checkbox" value=""/><a href="#"> 变频振动器有限公司</a></dd>
        		<dd><input type="checkbox" value=""/><a href="#">专业飞行模拟驾驶有限公司</a></dd>
        		<dd><input type="checkbox" value=""/><a href="#">显示屏代理加盟有限公司</a></dd>
        	</dl>
        	<dl class="list_color_blue">
        		<dt>地址</dt>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        	</dl>
        	<dl class="list_color_yellow">
        		<dt>专利</dt>
        		<dd><a href="#">白色UR概预算</a></dd>
        		<dd><a href="#">自杀杜御风v</a></dd>
        		<dd><a href="#">董事长高中时代</a></dd>
        		<dd><a href="#">dkj.tijhdlr</a></dd>
        	</dl>
			<dl>
        		<dt>操作</dt>
        		<dd><a href="#">添加预选</a></dd>
        		<dd><a href="#">添加预选</a></dd>
        		<dd><a href="#">添加预选</a></dd>
        		<dd><a href="#">添加预选</a></dd>
        	</dl>
        
        </div>
        <div class="area_add_btn">
		<input style=" margin-left: 0px;" type="button" value="批量加入备选" />
		<input type="button" value="批量取消备选" />
		</div>
		
		<div class="page">
		  	
			<a class="page_next">上一页</a>
		  	<a>1</a>
		  	<a>2</a>
		  	<a>3</a>
		  	<a>4</a>
			<a>5</a>
			<a>6</a>
		  	
		  	<a class="page_last">下一页</a>
		  	
		  	<p>共6页  24条</p>
		  </div>
		
		
		
		<div class="list_base2">
        	<dl>
        		<dt>备选企业列表</dt>
        		<dd><input type="checkbox" value=""/><a href="#">太阳能电风有限公司</a></dd>
        		<dd><input type="checkbox" value=""/><a href="#"> 变频振动器有限公司</a></dd>
        		<dd><input type="checkbox" value=""/><a href="#">专业飞行模拟驾驶有限公司</a></dd>
        		<dd><input type="checkbox" value=""/><a href="#">显示屏代理加盟有限公司</a></dd>
        	</dl>
        	<dl class="list_color_blue">
        		<dt>地址</dt>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        		<dd><a href="#">重庆市渝中区七星岗</a></dd>
        	</dl>
        	<dl class="list_color_yellow">
        		<dt>专利</dt>
        		<dd><a href="#">白色UR概预算</a></dd>
        		<dd><a href="#">自杀杜御风v</a></dd>
        		<dd><a href="#">董事长高中时代</a></dd>
        		<dd><a href="#">dkj.tijhdlr</a></dd>
        	</dl>
			<dl>
        		<dt>操作</dt>
        		<dd><a href="#">添加预选</a></dd>
        		<dd><a href="#">添加预选</a></dd>
        		<dd><a href="#">添加预选</a></dd>
        		<dd><a href="#">添加预选</a></dd>
        	</dl>
        
        </div>
		<input class="area_save_btn" type="button" value="保存" />
    </div> 
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>