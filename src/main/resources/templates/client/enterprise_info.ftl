<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>填写资料</title>
<link rel="shortcut icon" href="/client/images/index/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
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
				}
	            else 
	            {
	                alert(data.msg);
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
<div class="header">
    <div class="logo"></div>
    <form>
    <div class="search">
    	<input type="text" value="请输入搜索内容" onfocus="if(this.value=='请输入搜索内容'){this.value='';}" onblur="if(this.value==''){this.value='请输入搜索内容'}" />
        <input style="width:40px; background:url(/client/images/search.png) no-repeat center;" type="submit" value="" />
    </div>
    </form>
    <dl class="porfile">
    	<dt><a>帮助</a></dt>
        <dt><a>站内信息</a></dt>
        <dd>
        	<div><img src="/client/images/porfile_img.png" /></div>
        	<span>学友</span>
            <p>管理员</p>
            <span id="nav_guide"></span>
        </dd>
        <ul id="porfile_subnav">
                	<li><a href="#">帐号资料</a></li>
                    <li><a href="#">帐号资料</a></li>
                    <li><a href="#">密码修改</a></li>
                    <li><a href="/logout">注销</a></li>   
        </ul>
    </dl>
</div><!--header_end-->
<!--content-->
<div class="content">
<!--left-->
	<div class="leftbar">
		<dl class="nav">
            <dd><a href="#">基本资料</a></dd>
            <dd><a href="#">活动列表</a></dd>
            <dd><a href="#">申请展示</a></dd>

		</dl>
	</div>
<!--right-->
    <div class="right_content">
    <form action="/enterprise/info/submit" id="step1" method="post">
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
        	<dt><a class="a1" href="#">第一步<br/>资料填写</a></dt>
        	<dd></dd>
        	<dt><a class="a2" href="#">第二步<br />生成打印</a></dt>
        	<dd></dd>
        	<dt><a class="a3" href="#">第三步<br />上传报名表</a></dt>
        </dl>
     
        	<div style="width:100%;">
        		<input type="radio"  checked="checked" name="formType" value="0" onClick="javascript:showEnter();"/><span>项目团队表格</span>
        		<input type="radio"  name="formType" value="1" onClick="javascript:showPro();"/><span>企业组表格</span>
        	</div>
       
    </div>  
    <#if enterprise??>
    <input type="hidden" name="id" value="${enterprise.id?c!''}" />
    <input type="hidden" name="username" value="${enterprise.username!''}"/>
    <dl class="apply_content">
    	<dt class="dt01"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div><span class="enter">企业名称：</span><span class="pro hide">项目名称：</span><input type="text" name="title" value="" datatype="*"/></div>
    			<div><span>成立时间：</span><input type="text" name="establish" value="" datatype="*" ignore="ignore" /></div>
    			<div><span>注册资本<b style="color:#999;font-size:0.6em;">(万元)</b>：</span><input type="text" name="capital" value="" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"/></div>
    			<div><span>法定代表人：</span><input type="text" name="representative" datatype="*"value="" /></div>
    			<div><span>股东结构：</span><textarea name="shareholder" datatype="*"></textarea></div>
    			<div><span>所在地区：</span>
    				<select name="area" datatype="*">
    					<option value="江北">江北</option>
    					<option value="南岸">南岸</option>
    					<option value="巴南">巴南</option>
    					<option value="合川">合川</option>
    					<option value="荣昌">荣昌</option>
    				</select>
    			</div>
    			<div  class="enter"><span>职工人数：</span>	<input type="text" name="staffNumber" datatype="n" value="" /></div>
    			<div><span>行业归属：</span>
    				<#if type_list??>
    					<#list type_list as item>
    						<input style="margin-top: -3px; width:15px;" name="type" type="radio" value="${item.title!''}"/><p>${item.title!''}</p>
    					</#list>
    				</#if>		
				</div>
				<div class="pro hide"><span>主要负责人：</span><input type="text"  name="inCharge" datatype="*" ignore="ignore" value="" /></div>
				<div><span>邮箱：</span><input type="text" name="email" datatype="e"  ignore="ignore" value="" errormsg="请填写邮箱"/></div>
    			<div><span>联系人：</span><input type="text"  name="contact" datatype="*"  value="" /></div>
    			<div><span>公司网站：</span><input type="text" name="website" datatype="url"  ignore="ignore" value="" /></div>
    			<div><span>联系电话：</span><input type="text"  name="telephone" datatype="*" value="" /></div>
    			<div><span>传真：</span><input type="text"  name="fax" datatype="*"  ignore="ignore" value="" /></div>
    			<div><span>QQ/MSN：</span><input type="text" name="chat" datatype="*" ignore="ignore"  value="" /></div>
    			<div><span>手机：</span><input type="text"  name="mobile" datatype="m"  value="" errormsg="请填写手机"/></div>
    			<div><span class="enter">企业简介：</span><span class="pro hide">团队简介：</span><textarea name="profile" datatype="*5-200"  errormsg="输入5到200字" tip="200字以内"></textarea></div>
    			<div  class="enter"><span>公司团队：</span><textarea name="teamIntroduction" datatype="*5-200" ignore="ignore" errormsg="输入5到200字"  tip="200字以内"></textarea></div>
    			<div><span>技术特点及优势：</span><textarea name="advantage" datatype="*5-200" errormsg="输入5到200字"  tip="200字以内"></textarea></div>
    			<div><span>市场规模行业地位：</span><textarea name="size" datatype="*5-200" errormsg="输入5到200字" tip="200字以内"></textarea></div>

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
    				<input type="text" name="lastAssets3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" value=""  />
    				<input type="text" name="lastNetAssets3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastSale3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastProfit3" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    			</div>
    			<div>
    				<span>2013</span>
    				<input type="text" name="lastAssets2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastNetAssets2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastSale2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastProfit2" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    			</div>
    			<div>
    				<span>2014</span>
    				<input type="text" name="lastAssets1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastNetAssets1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastSale1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    				<input type="text" name="lastProfit1" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value=""  />
    			</div>
    	</dd>
    	<dt class="dt03"><span>三、知识产权基本情况</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>
    			<div><span>发明专利</span><input type="text" name="inventiPatent" datatype="*" ignore="ignore" value=""  /></div>
    			<div><span>实用新型专利</span><input type="text"name="newPatent" datatype="*"  ignore="ignore" value=""  /></div>
    			<div><span>外观设计专利</span><input type="text" name="designPatent" datatype="*"  ignore="ignore"value=""  /></div>
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
    				<input type="text" name="expectEquityDate"  value=""  />
    				<input type="text" name="expectEquityAmount" value=""  />
    				<input type="text" name="expectEquityUse" value=""  />
    			</div>
    			<div>
    				<span>（二）债权融资</span>
    				<input type="text" name="expectBondDate" value=""  />
    				<input type="text" name="expectBondAmount" value=""  />
    				<input type="text" name="expectBondUse" value=""  />
    			</div>
    			<div>
    				<p>是否愿意将贵公司所填以上信息向投资金融平台披露</p>
    			<input style=" width:15px;"  type="radio"  checked="checked" name="isShow" value="true" />
    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">是（同意请加盖公司公章）</span>
        		<input style=" width:15px;" type="radio"  name="isShow" value="false" />
        		<span style=" width:auto; display: block; margin-left: 10px; margin-top:3px;">否</span>
    			</div>
    	</dd>
    	<dt class="dt05">
    		<input type="submit" value="确定" />
    	</dt>
    </dl>
    <#else>
    <a href="/login">请登录</a>
    </#if>

    </div>
</form>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
