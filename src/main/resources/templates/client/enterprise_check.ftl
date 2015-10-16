<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看资料</title>
<link rel="shortcut icon" href="images/icon.ico" />
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

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
            <dd><a href="/enterprise/check">基本资料</a></dd>
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

                <a href="#">基本资料</a>

            </dd>

        </dl>
        <div class="change_inform">
	        <#if enterprise.statusId == 1>
	    		<span>审核状态：已通过</span>
	    		<input style="cursor:pointer;" type="button" value="申请重新审核" onclick="location.href='/enterprise/recall'"/>
	        <#elseif enterprise.statusId == 0>
	    		<span>审核状态：待审核</span>
	    		<input style="cursor:pointer;" type="button" value="修改基本资料" onclick="location.href='/enterprise/info'"/>	    	
	        <#elseif enterprise.statusId == 2>
	    		<span>审核状态：已申请重新审核，等待区县管理员处理</span>
	    	</#if>    			
    	</div>
    </div>  
 
      <form>
    <dl class="apply_change">
        <dt class="dt01"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
        <dd>

                <div><span><#if enterprise.formType == 0>企业名称<#else> 项目名称</#if>：</span><input type="text" value="${enterprise.title!''}" disabled="" /></div>
                <div><span>编号：</span><input type="text" value="${enterprise.number!''}" disabled="" /></div>
                <div><span>成立时间：</span><input type="text" value="${enterprise.establish?string("yyyy年MM月dd日")!''}" disabled="" /></div>
                <div><span>注册资本：</span><input type="text" value="${enterprise.capital?string("0.00")!''}" disabled="" />&nbsp;&nbsp;(万元)</div>
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
                    <div><span>主要负责人：</span><input disabled="" type="text"  name="inCharge" datatype="*" ignore="ignore" value="${enterprise.inCharge!''}" /></div>
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
                    <div><span>公司团队：</span><textarea disabled="" >${enterprise.teamInroduction!''}</textarea><span>(200字以内)</span></div>
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
                    <span>2012</span>
                    <input type="text" value="${enterprise.lastAssets3?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastNetAssets3?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastSale3?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastProfit3?string("0.00")}"  disabled="" />
                </div>
                <div>
                    <span>2013</span>
                    <input type="text" value="${enterprise.lastAssets2?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastNetAssets2?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastSale2?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastProfit2?string("0.00")}"  disabled="" />
                </div>
                <div>
                    <span>2014</span>
                    <input type="text" value="${enterprise.lastAssets1?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastNetAssets1?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastSale1?string("0.00")}"  disabled="" />
                    <input type="text" value="${enterprise.lastProfit1?string("0.00")}"  disabled="" />
                </div>
        </dd>
        <dt class="dt03"><span>三、知识产权基本情况</span><br/><p>此信息将自动生成到报名表中</p></dt>
        <dd>

                <div><span>发明专利</span><input type="text" style="width:40px;"value="${enterprise.inventiPatent!''}"  disabled="" />&nbsp;个</div>
                <div><span>实用新型专利</span><input type="text" style="width:40px;"value="${enterprise.newPatent!''}"  disabled="" />&nbsp;个</div>
                <div><span>外观设计专利</span><input type="text" style="width:40px;" value="${enterprise.designPatent!''}"  disabled="" />&nbsp;个</div>
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
                    <input type="text" value="<#if enterprise.expectEquityAmount??>${enterprise.expectEquityAmount?string("0.00")}</#if>"  disabled="" />
                    <input type="text" value="${enterprise.expectEquityUse!''}"  disabled="" />
                </div>
                <div>
                    <span>（二）债权融资</span>
                    <input type="text" value="${enterprise.expectBondDate?string("yyyy年MM月dd日")!''}"  disabled="" />
                    <input type="text" value="<#if enterprise.expectBondAmount??>${enterprise.expectBondAmount?string("0.00")}</#if>"  disabled="" />
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
</div><!--content_end-->
</div><!--main-->
</body>
</html>
