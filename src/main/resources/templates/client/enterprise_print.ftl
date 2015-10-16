<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>导出表格</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>

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
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        <dl class="team_apply">
        	<dt><a class="a1" href="/enterprise/info">第一步<br/>资料填写</a></dt>
        	<dd></dd>
        	<dt><a class="a2" href="/enterprise/print">第二步<br />生成打印</a></dt>
        	<dd></dd>
        	<dt><a class="a3" href="/enterprise/upload">第三步<br />上传报名表</a></dt>
        </dl>
        <dl class="apply_step2">
        	<dt>*点击按钮，生成当前填写的报名表进行打印盖章</dt>
        	<dd><input style="display:block;"  type="button" value="生成表格并下载打印" onclick="location.href='/enterprise/export/enter'"/></dd>
        </dl>
        <dl class="apply_step2" style="margin-top:20px ; ">
        	<dd><input style="background:#529c15;" type="button" value="上传报名表" onclick="location.href='/enterprise/upload'"/></dd>
        </dl>
    </div>  
    
    <div style="float:left;	">
      <form>
    <dl class="apply_change">
    	<dt class="dt01"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div><span><#if enterprise.formType == 0>企业名称<#else> 项目名称</#if>：</span><input type="text" value="${enterprise.title!''}" disabled="" /></div>
    			<div><span>编号：</span><input type="text" value="${enterprise.number!''}" disabled="" /></div>
    			<div><span>成立时间：</span><input type="text" value="${enterprise.establish!''}" disabled="" /></div>
    			<div><span>注册资本：</span><input type="text" value="${enterprise.capital!''}" disabled="" />&nbsp;&nbsp;(万元)</div>
    			<div><span>法定代表人：</span><input type="text" value="${enterprise.representative!''}" disabled="" /></div>
    			<div><span>股东结构：</span><textarea disabled="" >${enterprise.shareholder!''}</textarea></div>
    			<div><span>所在地区：</span>
    				<select disabled="" >
                        <#if region_list??>
                            <#list region_list as item>
                                <option value="${item.title!''}" <#if enterprise.area==item.title>selected="selected"</#if>>${item.title!''}</option>
                            </#list>
                        </#if>  
    				</select>
    			</div>
    			<#if enterprise.formType == 0>
    			<div><span>职工人数：</span><input type="text" value="${enterprise.staffNumber!''}" disabled="" />&nbsp;&nbsp;(人)</div>
    			</#if>

    			<div><span>行业归属：</span>
    				<#if enterpriseType_list??>
    					<#list enterpriseType_list as item>
    						<input disabled="" style="margin-top: -3px; width:15px;" name="type" type="checkbox" value="${item.title!''}"
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
				<#if enterprise.formType == 1>
					<div><span>主要负责人：</span><input type="text"  name="inCharge" datatype="*" ignore="ignore" disabled=""  value="${enterprise.inCharge!''}" /></div>
				</#if>
				<div><span>邮箱：</span><input type="text" value="${enterprise.email!''}" disabled="" /></div>
    			<div><span>联系人：</span><input type="text" value="${enterprise.contact!''}" disabled="" /></div>
    			<div><span>公司网站：</span><input type="text" value="${enterprise.website!''}" disabled="" /></div>
    			<div><span>联系电话：</span><input type="text" value="${enterprise.telephone!''}" disabled="" /></div>
    			<div><span>传真：</span><input type="text" value="${enterprise.fax!''}" disabled="" /></div>
    			<div><span>QQ/MSN：</span><input type="text" value="${enterprise.chat!''}" disabled="" /></div>
    			<div><span>手机：</span><input type="text" value="${enterprise.mobile!''}" disabled="" /></div>
    			<div><span><#if enterprise.formType == 0>企业简介<#else>团队简介</#if>：</span><textarea disabled="" >${enterprise.profile!''}</textarea><span>(200字以内)</span></div>
    			<#if enterprise.formType == 0>
    				<div><span>公司团队：</span><textarea disabled="" >${enterprise.teamIntroduction!''}</textarea><span>(200字以内)</span></div>
    			</#if>
    			<div><span>技术特点及优势：</span><textarea disabled="" >${enterprise.advantage!''}</textarea><span>(200字以内)</span></div>
    			<div><span>市场规模行业地位：</span><textarea disabled="" >${enterprise.size!''}</textarea><span>(200字以内)</span></div>

    	</dd>
    	<dt class="dt02"><span>二、近三年财务状况（单位：万元）</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div>
    				<span>年限</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">总资产</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">净资产</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">销售收入</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">毛利润</span>
    			</div>
    			<div>
    				<span><#if lastyear3??>${lastyear3?string("yyyy")}</#if></span>
    				<input type="text" value="<#if enterprise.lastAssets3??>${enterprise.lastAssets3?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastNetAssets3??>${enterprise.lastNetAssets3?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastSale3??>${enterprise.lastSale3?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastProfit3??>${enterprise.lastProfit3?c!''}</#if>"  disabled="" />
    			</div>
    			<div>
    				<span><#if lastyear2??>${lastyear2?string("yyyy")}</#if></span>
    				<input type="text" value="<#if enterprise.lastAssets2??>${enterprise.lastAssets2?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastNetAssets2??>${enterprise.lastNetAssets2?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastSale2??>${enterprise.lastSale2?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastProfit2??>${enterprise.lastProfit2?c!''}</#if>"  disabled="" />
    			</div>
    			<div>
    				<span><#if lastyear1??>${lastyear1?string("yyyy")}</#if></span>
    				<input type="text" value="<#if enterprise.lastAssets1??>${enterprise.lastAssets1?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastNetAssets1??>${enterprise.lastNetAssets1?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastSale1??>${enterprise.lastSale1?c!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.lastProfit1??>${enterprise.lastProfit1?c!''}</#if>"  disabled="" />
    			</div>
    	</dd>
    	<dt class="dt03"><span>三、知识产权基本情况</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div><span>发明专利</span><input type="text" value="${enterprise.inventiPatent!''}"  disabled="" /></div>
    			<div><span>实用新型专利</span><input type="text" value="${enterprise.newPatent!''}"  disabled="" /></div>
    			<div><span>外观设计专利</span><input type="text" value="${enterprise.designPatent!''}"  disabled="" /></div>
    	</dd>
    	<dt class="dt04"><span>四、融资信息（单位：万元）</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd style="margin-bottom: 30px;">
    			<div>
    				<span>期望融资方式</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">期望获得资金的时间</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">期望融资金额</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">融资用途</span>
    			</div>
    			<div>
    				<span>（一）股权融资</span>
    				<input type="text" value="${enterprise.expectEquityDate?string("yyyy年MM月dd日")!''}"  disabled="" />
    				<input type="text" value="${enterprise.expectEquityAmount?c!''}"  disabled="" />
    				<input type="text" value="${enterprise.expectEquityUse!''}"  disabled="" />
    			</div>
    			<div>
    				<span>（二）债权融资</span>
    				<input type="text" value="<#if enterprise.expectBondDate??>${enterprise.expectBondDate?string("yyyy年MM月dd日")!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.expectBondAmount??>${enterprise.expectBondAmount?c!''}</#if>"  disabled="" />
    				<input type="text" value="${enterprise.expectBondUse!''}"  disabled="" />
    			</div>
    			<div>
    				<p>是否愿意将贵公司所填以上信息向投资金融平台披露</p>
    			<input style=" width:15px;"  type="radio" <#if enterprise.isShow?? &&enterprise.isShow> checked="checked" </#if>name="team" value="" disabled="" />
    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">是（同意请加盖公司公章）</span>
        		<input style=" width:15px;" type="radio" <#if !enterprise.isShow|| !enterprise.isShow> checked="checked" </#if>  name="team" value="" disabled="" />
        		<span style=" width:auto; display: block; margin-left: 10px; margin-top:3px;">否</span>
    			</div>
    	</dd>

    </dl>
    </form>
    </div>
    
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
