<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>填写资料</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<style>
.apply_content dd div .Validform_wrong span{ text-align:left;}
</style>

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>

<script>
$(document).ready(function(){

	$("#step1").Validform({
			tiptype:4,
	  	    ajaxPost:true,
            callback: function (data) { 
	            if (data.code == 0)
	            {
					alert("提交成功");
					location.href="/enterprise/print";
				}
	            else 
	            {
	                alert(data.msg);
	                if (data.check == 0)
	                {
	                	location.href='/login';
	                }
	                else if (data.check ==1)
	                {
	                	location.href='/enterprise/check';
	                }
	            }
       		 }
	});
});

function showEnter(){
	$(".enter").css("display","block");
	$(".pro").css("display","none");
}
function showPro(){
	$(".pro").css("display","block");
	$(".enter").css("display","none");
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
            <dd><a href="/enterprise/info">基本资料</a></dd>
            <dd><a href="/enterprise/activity/list">活动列表</a></dd>
            <dd><a href="#">申请展示</a></dd>

		</dl>
	</div>
<!--right-->
    <div class="right_content">

    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
                <a href="#">企业/团队</a>
                <p>&gt;</p>
                <a href="#">基本资料</a>
                <p>&gt;</p>
                <a href="#">填写资料</a>
            </dd>
            <dt class="crumb_back"><a  href="#">返回上一页</a></dt>
        </dl>
        <dl class="team_apply">
        	<dt><a class="a1" href="">第一步<br/>资料填写</a></dt>
        	<dd></dd>
        	<dt><a class="a2" href="" title="请先填写并提交资料">第二步<br />生成打印</a></dt>
        	<dd></dd>
        	<dt><a class="a3" href="" title="请先填写并提交资料">第三步<br />上传报名表</a></dt>
        </dl>

       
    </div>  
    <#if enterprise??>
    <input type="hidden" name="id" value="${enterprise.id?c!''}" />
    <input type="hidden" name="username" value="${enterprise.username!''}"/>
    <dl class="apply_content">
             <form action="/enterprise/info/submit" id="step1" method="post">
        	<div style="margin: 20px 0 20px 50px ;width:100%;">
        		<input type="radio" <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>checked="checked"</#if> name="formType" value="0" onClick="javascript:showEnter();"/><span>企业组表格</span>
        		<input type="radio" <#if enterprise.formType??&&enterprise.formType==1>checked="checked"</#if> name="formType" value="1" onClick="javascript:showPro();"/><span>项目团队表格</span>
        	</div>
    	<dt class="dt01"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div>
    			     <span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">企业名称：</span>
    			     <span class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">项目名称：</span>
    			     <input type="text" name="title" value="<#if enterprise.formType??>${enterprise.title!''}</#if>" datatype="*"/>
    		    </div>
    			<div><span>成立时间：</span>
    			  <input  type="text" id="date" value="<#if enterprise.formType??>${enterprise.establish?string("yyyy年MM月dd日")!''}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日',vel:'date_2',lang:'zh-cn'})" datatype="*" / >
    			  <input id="date_2" name="establish" value="<#if enterprise.formType??>${enterprise.establish?string("yyyy-MM-dd")!''}</#if>" type="hidden" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " />
    			<#--<input type="text" name="establish" value="<#if enterprise.formType??>${enterprise.establish!''}</#if>" datatype="*" ignore="ignore" /> -->
    			</div>
    			<div><span>注册资本<b style="color:#999;font-size:0.6em;">(万元)</b>：</span><input type="text" name="capital" value="<#if enterprise.formType??>${enterprise.capital!''}</#if>" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  errormsg="请填写数字！" /></div>
    			<div><span>法定代表人：</span><input type="text" name="representative" datatype="*"value="<#if enterprise.formType??>${enterprise.representative!''}</#if>" /></div>
    			<div><span>股东结构：</span><textarea name="shareholder" datatype="*"><#if enterprise.formType??>${enterprise.shareholder!''}</#if></textarea></div>
    			<div><span>所在地区：</span>
    				<select name="area" datatype="*">
    					<#if region_list??>
    						<#list region_list as item>
    							<option value="${item.title!''}" <#if enterprise.formType??&&enterprise.area==item.title>selected="selected"</#if>>${item.title!''}</option>
    						</#list>
    					</#if>	
    				</select>
    			</div>
    			<div  class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>"><span>职工人数：</span>	<input type="text" name="staffNumber" datatype="n"  ignore="ignore" value="<#if enterprise.formType??>${enterprise.staffNumber!''}</#if>" /></div>
    			<div><span>行业归属：</span>
    				<#if enterpriseType_list??>
    					<#list enterpriseType_list as item>
    						<input style="margin-top: -3px; width:15px;" name="type" type="checkbox" value="${item.title!''}" 
    							<#if enterprise.formType?? && enterpriseType??>
	    			     			<#list enterpriseType as type>
	    			     				<#if type == item.title>
	    			     					checked="checked"
	    			     				</#if>
	    			     			</#list>
	    			     		</#if>			
    							/><p>${item.title!''}</p>
    					</#list>
    				</#if>		
				</div>
				<div class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>"><span>主要负责人：</span><input type="text"  name="inCharge" datatype="*" ignore="ignore" value="<#if enterprise.formType??>${enterprise.inCharge!''}</#if>" /></div>
				<div><span>邮箱：</span><input type="text" name="email" datatype="e"  ignore="ignore" value="<#if enterprise.formType??>${enterprise.email!''}</#if>" errormsg="请填写邮箱"/></div>
    			<div><span>联系人：</span><input type="text"  name="contact" datatype="*"  value="<#if enterprise.formType??>${enterprise.contact!''}</#if>" /></div>
    			<div><span>公司网站：</span><input type="text" name="website" datatype="url"  ignore="ignore" value="<#if enterprise.formType??>${enterprise.website!''}</#if>" /></div>
    			<div><span>联系电话：</span><input type="text"  name="telephone" datatype="*" value="<#if enterprise.formType??>${enterprise.telephone!''}</#if>" /></div>
    			<div><span>传真：</span><input type="text"  name="fax" datatype="*"  ignore="ignore" value="<#if enterprise.formType??>${enterprise.fax!''}</#if>" /></div>
    			<div><span>QQ/MSN：</span><input type="text" name="chat" datatype="*" ignore="ignore"  value="<#if enterprise.formType??>${enterprise.chat!''}</#if>" /></div>
    			<div><span>手机：</span><input type="text"  name="mobile" datatype="m"  value="<#if enterprise.formType??>${enterprise.mobile!''}</#if>" errormsg="请填写手机！"/></div>
    			<div>
	    			<span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">企业简介：</span>
	    			<span class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">团队简介：</span><textarea name="profile" datatype="*5-200"  errormsg="输入5到200字" tip="200字以内"><#if enterprise.formType??>${enterprise.profile!''}</#if></textarea>
    			</div>
    			<div  class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>"><span>公司团队：</span><textarea name="teamIntroduction" datatype="*5-200" ignore="ignore" errormsg="输入5到200字"  tip="200字以内"><#if enterprise.formType??>${enterprise.teamIntroduction!''}</#if></textarea></div>
    			<div><span>技术特点及优势：</span><textarea name="advantage" datatype="*5-200" errormsg="输入5到200字"  tip="200字以内"><#if enterprise.formType??>${enterprise.advantage!''}</#if></textarea></div>
    			<div><span>市场规模行业地位：</span><textarea name="size" datatype="*5-200" errormsg="输入5到200字" tip="200字以内"><#if enterprise.formType??>${enterprise.size!''}</#if></textarea></div>

    	</dd>
    	<dt class="dt02"><span>二、近三年财务状况（单位：万元）</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div>
    				<span>年限</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">总资产</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">净资产</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">销售收入</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">毛利润</span>
    			</div>
    			<div>
    				<span>2012</span>
    				<input type="text" name="lastAssets3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" value="<#if enterprise.formType??>${enterprise.lastAssets3!''}</#if>"  errormsg="请填写数字！"/>
    				<input type="text" name="lastNetAssets3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastNetAssets3!''}</#if>"   errormsg="请填写数字！"/>
    				<input type="text" name="lastSale3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastSale3!''}</#if>"  errormsg="请填写数字！" />
    				<input type="text" name="lastProfit3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastProfit3!''}</#if>"   errormsg="请填写数字！"/>
    			</div>
    			<div>
    				<span>2013</span>
    				<input type="text" name="lastAssets2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastAssets2!''}</#if>"   errormsg="请填写数字！"/>
    				<input type="text" name="lastNetAssets2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastNetAssets2!''}</#if>"   errormsg="请填写数字！"/>
    				<input type="text" name="lastSale2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastSale2!''}</#if>"  errormsg="请填写数字！" />
    				<input type="text" name="lastProfit2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastProfit2!''}</#if>"   errormsg="请填写数字！"/>
    			</div>
    			<div>
    				<span>2014</span>
    				<input type="text" name="lastAssets1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastAssets1!''}</#if>"  errormsg="请填写数字！" />
    				<input type="text" name="lastNetAssets1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastNetAssets1!''}</#if>"   errormsg="请填写数字！"/>
    				<input type="text" name="lastSale1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastSale1!''}</#if>"  errormsg="请填写数字！" />
    				<input type="text" name="lastProfit1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise.formType??>${enterprise.lastProfit1!''}</#if>"  errormsg="请填写数字！" />
    			</div>
    	</dd>
    	<dt class="dt03"><span>三、知识产权基本情况</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>
    			<div><span>发明专利数量</span><input type="text" name="inventiPatent" datatype="n" style="width:40px;" value="<#if enterprise.formType??>${enterprise.inventiPatent!''}</#if>"  /></div>
    			<div><span>实用新型专利数量</span><input type="text"name="newPatent" datatype="n"  style="width:40px;" value="<#if enterprise.formType??>${enterprise.newPatent!''}</#if>"  /></div>
    			<div><span>外观设计专利数量</span><input type="text" name="designPatent" datatype="n" style="width:40px;" value="<#if enterprise.formType??>${enterprise.designPatent!''}</#if>"  /></div>
    	</dd>
    	<dt class="dt04"><span>四、融资信息（单位：万元）</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>
    			<div>
    				<span>期望融资方式</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">期望获得资金的时间</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">期望融资金额</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">融资用途</span>
    			</div>
    			<div>
    				<span>（一）股权融资</span>
    				<input  type="text" id="equi" value="<#if enterprise.formType??>${enterprise.establish?string("yyyy年MM月dd日")!''}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日',vel:'expectEquityDate',lang:'zh-cn'})" datatype="*" ignore="ignore"/ >
    			  	<input id="expectEquityDate" name="expectEquityDate" value="<#if enterprise.formType??>${enterprise.expectEquityDate?string("yyyy-MM-dd")!''}</#if>" type="hidden" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " />
    				
    				<input type="text" name="expectEquityAmount" value="<#if enterprise.formType??>${enterprise.expectEquityAmount!''}</#if>"  ignore="ignore" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  errormsg="请填写数字！"/>
    				<input type="text" name="expectEquityUse" value="<#if enterprise.formType??>${enterprise.expectEquityUse!''}</#if>"  />
    			</div>
    			<div>
    				<span>（二）债权融资</span>
    				<input  type="text" id="bond" value="<#if enterprise.formType??>${enterprise.establish?string("yyyy年MM月dd日")!''}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日',vel:'expectBondDate',lang:'zh-cn'})" datatype="*" ignore="ignore"/ >
    			  	<input id="expectBondDate" name="expectBondDate" value="<#if enterprise.formType??>${enterprise.expectBondDate?string("yyyy-MM-dd")!''}</#if>" type="hidden" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " />
    				
    				<input type="text" name="expectBondAmount" value="<#if enterprise.formType??>${enterprise.expectBondAmount!''}</#if>" ignore="ignore" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  errormsg="请填写数字！" />
    				<input type="text" name="expectBondUse" value="<#if enterprise.formType??>${enterprise.expectBondUse!''}</#if>"  />
    			</div>
    			<div>
    				<p>是否愿意将贵公司所填以上信息向投资金融平台披露</p>
    			<input style=" width:15px;"  type="radio" <#if enterprise.formType??&& enterprise.isShow ||!enterprise.formType??> checked="checked"</#if> name="isShow" value="true" />
    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">是（同意请加盖公司公章）</span>
        		<input style=" width:15px;" type="radio"  <#if enterprise.formType??&& !enterprise.isShow> checked="checked"</#if> name="isShow" value="false" />
        		<span style=" width:auto; display: block; margin-left: 10px; margin-top:3px;">否</span>
    			</div>
    	</dd>
    	<dt class="dt05">
    		<input type="submit" style="cursor:pointer;" value="确定" />
    	</dt>
    	</form>
    </dl>
    <#else>
    <a href="/login">请登录</a>
    </#if>

    </div>

</div><!--content_end-->
</div><!--main-->
</body>
</html>
