<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>档案跟踪</title>
    <link rel="shortcut icon" href="/client/images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/expert.css" rel="stylesheet" type="text/css" />
    <script src="/client/js/jquery-1.9.1.min.js"></script>
    <script src="/client/js/main.js"></script>

</head>
<script>
window.onload = function(){
	var oBtn = document.getElementById('text_show_btn');
	var oShow = document.getElementById('apply_change_show');
	var oBtn01 = document.getElementById('text_show_btn01');
	var oP = document.getElementById('text');

	
	oBtn.onclick = function(){
		
		if(oShow.style.display =='none'){
			oShow.style.display = 'block';
			this.value = '隐藏资料';
			oP.innerHTML = '*点击按钮隐藏资料';
		}else{
			oShow.style.display = 'none';
			this.value = '显示资料';
			oP.innerHTML = '*点击按钮查看资料';
		};

		

	};
	
	oBtn01.onclick = function(){
		
			oShow.style.display = 'none';

			oBtn.value = '显示资料';
			oP.innerHTML = '*点击按钮查看资料';

	};
};

function record(){
    var content = document.getElementById("coach_record").value;
    window.location.href = "/region/record/save?content="+content+"&enterpriseId=${enterpriseId}";
}
</script>



<body>
    <!--main-->
    <div class="main">
        <!--header-->
        <#include "/client/user_common_header.ftl">
        <!--header_end-->
            <!--content-->
            <div class="content">
                <!--left-->
                <div class="leftbar">
                    <dl class="nav">
			            <dd><a href="/region/enterprise/list">企业列表</a></dd>
			            <dd><a href="/region/activity/list">活动列表</a></dd>
                    </dl>
                </div>
                <!--right-->
                <div class="right_content">
                    <div class="right_box">
                        <dl class="crumb">
                            <dt><a href="#"></a></dt>
                            <dd>
                                <p>当前所在位置:</p>
                                <a href="/expert/enterprises">辅导企业</a>
                                <p>&gt;</p>
                                <a>档案跟踪</a>
                            </dd>
                            <dt class="crumb_back"><a></a></dt>
                        </dl>
					 <div class="change_inform">
					            <#if enterprise.statusId??&&enterprise.statusId == 0 >
					            <span>审核状态： 待审核</span>
					            <input style="cursor:pointer;" type="button" value="审核通过 " onclick="location.href='/region/enterprise/pass/${enterprise.id?c!''}'"/>          
					            <input style="cursor:pointer;" type="button" value="审核未通过 " onclick="location.href='/region/enterprise/cancel/${enterprise.id?c!''}'"/>         
					            <#elseif enterprise.statusId??&& enterprise.statusId == 1>
					            <span>审核状态：已通过</span>
					            <input style="cursor:pointer;" type="button" value="重新审核 " onclick="location.href='/region/enterprise/recall/${enterprise.id?c!''}'"/>
					            <input style="cursor:pointer;" type="button" value="审核未通过 " onclick="location.href='/region/enterprise/cancel/${enterprise.id?c!''}'"/>       
					            <#elseif enterprise.statusId??&& enterprise.statusId == 2>
					            <span>审核状态： 用户申请了重新审核</span>
					            <input style="cursor:pointer;" type="button" value="重新审核 " onclick="location.href='/region/enterprise/recall/${enterprise.id?c!''}'"/> 
					             <#elseif  enterprise.statusId??&&enterprise.statusId == 3>
					            <span>审核状态：未通过</span>
					            <input style="cursor:pointer;" type="button" value="重新审核 " onclick="location.href='/region/enterprise/recall/${enterprise.id?c!''}'"/>
					            <input style="cursor:pointer;" type="button" value="审核通过 " onclick="location.href='/region/enterprise/pass/${enterprise.id?c!''}'"/> 
					             <#elseif  enterprise.statusId??&&enterprise.statusId == 4>
					             <span>审核状态：重新审核（更新中）</span>
					              <#else>
					             <span>用户未完善资料</span>
					            </#if>
					        </div>
					        <div class="change_inform">
					        	<span>
					        		<#if enterprise.fileUrl??>	<a style="font-size:12px;  text-decoration: underline;" href="/download/data?name=${enterprise.fileUrl!''}">【申请表附件】</a></#if>
					        	</span>
					        </div>
                    </div>  
                    <input id="text_show_btn" style="width: 80px; height:30px; border: none; background: #e67817; color: white; border-radius: 6px; margin-left: 30px; margin-top: 30px;" type="button" value="显示资料" />
                    <p id="text" style=" margin-left: 30px; font-size: 14px; color: red;">*点击按钮查看资料</p>
                    <form id="apply_change_show" style=" display: none;">
                         <dl class="apply_change">
    	<dt class="dt01"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd>

    			<div>
    			<span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">企业名称</span>
    			<span  class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">项目名称</span>
    			<input type="text" value="${enterprise.title!''}" disabled="" /></div>
    			<div><span>编号：</span><input type="text" value="${enterprise.number!''}" disabled="" /></div>
    			<div>
	    			<span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">成立时间：</span>
    				<span class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">（拟）成立时间：</span>
	    			<input type="text" value="<#if enterprise??&&enterprise.establish??>${enterprise.establish?string("yyyy年MM月dd日")!''}</#if>" disabled="" />
    			</div>
    			<div>
	    			<span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">注册资本：</span>
	    			<span class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">（拟）注册资本：</span>
    				<input type="text" value="<#if enterprise??&&enterprise.capital??>${enterprise.capital?c!''}</#if>" disabled="" />&nbsp;&nbsp;<b style="color:#999;font-size:0.6em;">(万元)</b>
    			</div>
    			<div>
	    			<span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">法定代表人：</span>
	    			<span  class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">（拟）法定代表人：</span>
    				<input type="text" value="${enterprise.representative!''}" disabled="" />
    			</div>
    			<div>
	    			<span class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">股东结构：</span>
	    			<span class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">（拟）股东结构：</span>
	    			<textarea disabled="" >${enterprise.shareholder!''}</textarea>
    			</div>
    			<div><span>所在地区：</span>
    				<select disabled="" >
                        <#if region_list??>
                            <#list region_list as item>
                                <option value="${item.title!''}" <#if enterprise.area??&&enterprise.area==item.title>selected="selected"</#if>>${item.title!''}</option>
                            </#list>
                        </#if>  
    				</select>
    			</div>
    			<div><span>地址：</span><input type="text" name="address" datatype="*"value="<#if enterprise.address??>${enterprise.address!''}</#if>"  disabled="" /></div>
    			<#if enterprise.formType??&& enterprise.formType == 0>
    			<div><span>职工人数：</span><input type="text" value="<#if enterprise.staffNumber??>${enterprise.staffNumber?c!''}</#if>" disabled="" />&nbsp;&nbsp;(人)</div>
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
				<#if enterprise.formType??&& enterprise.formType == 1>
					<div><span>主要负责人：</span><input type="text"  name="inCharge" datatype="*" ignore="ignore" disabled=""  value="${enterprise.inCharge!''}" /></div>
				</#if>
				<div><span>邮箱：</span><input type="text" value="${enterprise.email!''}" disabled="" /></div>
    			<div><span>联系人：</span><input type="text" value="${enterprise.contact!''}" disabled="" /></div>
    			<div><span>公司网站：</span><input type="text" value="${enterprise.website!''}" disabled="" /></div>
    			<div><span>联系电话：</span><input type="text" value="${enterprise.telephone!''}" disabled="" /></div>
    			<div><span>传真：</span><input type="text" value="${enterprise.fax!''}" disabled="" /></div>
    			<div><span>QQ/MSN：</span><input type="text" value="${enterprise.chat!''}" disabled="" /></div>
    			<div><span>手机：</span><input type="text" value="${enterprise.mobile!''}" disabled="" /></div>
    			<div><span><#if enterprise.formType??&& enterprise.formType == 0>企业简介<#else>团队简介</#if>：</span><textarea disabled="" >${enterprise.profile!''}</textarea><span>(200字以内)</span></div>
    			<#if enterprise.formType??&& enterprise.formType == 0>
    				<div><span>公司团队：</span><textarea disabled="" >${enterprise.teamIntroduction!''}</textarea><span>(200字以内)</span></div>
    			</#if>
    			<div><span>技术特点及优势：</span><textarea disabled="" >${enterprise.advantage!''}</textarea><span>(200字以内)</span></div>
    			<div><span>市场规模行业地位：</span><textarea disabled="" >${enterprise.size!''}</textarea><span>(200字以内)</span></div>

    	</dd>
        <dt class="dt02 enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>"><span>二、近三年财务状况（单位：万元）</span><br/><p>此信息将自动生成到报名表中</p></dt>
        <dd class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">
            <div class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">
    

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
    		</div>	
    	</dd>
        <dt class="dt03 enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">
            <span>三、知识产权基本情况</span>
            <br/><p>此信息将自动生成到报名表中</p>
        </dt>
        <dt class="dt03 pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">
            <span>二、知识产权基本情况</span>
            <br/><p>此信息将自动生成到报名表中</p>
        </dt>   
    	<dd>

    			<div><span>发明专利</span><input type="text" value="${enterprise.inventiPatent!'0'}"  disabled="" /></div>
    			<div><span>实用新型专利</span><input type="text" value="${enterprise.newPatent!'0'}"  disabled="" /></div>
    			<div><span>外观设计专利</span><input type="text" value="${enterprise.designPatent!'0'}"  disabled="" /></div>
    	</dd>
        <dt class="dt04 enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>"><span>四、融资信息（单位：万元）</span>
                                    <br/><p>此信息将自动生成到报名表中</p>
        </dt>
        <dt class="dt04 pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>"><span>三、融资信息（单位：万元）</span>
                                    <br/><p>此信息将自动生成到报名表中</p>
        </dt>    
    	<dd style="margin-bottom: 30px;">
    			<div>
    				<span>期望融资方式</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">期望获得资金的时间</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">期望融资金额</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px; disabled="" ">融资用途</span>
    			</div>
    			<div>
    				<span>（一）股权融资</span>
    				<input type="text" value="<#if enterprise.expectEquityDate??>${enterprise.expectEquityDate?string("yyyy年MM月dd日")!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.expectEquityAmount??>${enterprise.expectEquityAmount?c!''}</#if>"  disabled="" />
    				<input type="text" value="${enterprise.expectEquityUse!''}"  disabled="" />
    			</div>
    			<div>
    				<span>（二）债权融资</span>
    				<input type="text" value="<#if enterprise.expectBondDate??>${enterprise.expectBondDate?string("yyyy年MM月dd日")!''}</#if>"  disabled="" />
    				<input type="text" value="<#if enterprise.expectBondAmount??>${enterprise.expectBondAmount?c!''}</#if>"  disabled="" />
    				<input type="text" value="${enterprise.expectBondUse!''}"  disabled="" />
    			</div>
    			<div>
    				<p>项目可供资料</p>
	    			<input style=" width:15px;"  type="checkbox"  name="dataAble" value="商业计划书" disabled=""
	    				    		<#if enterprise.dataAble?? && dataAble??>
		    			     			<#list dataAble as item>
		    			     				<#if item == "商业计划书">
		    			     					checked="checked"
		    			     				</#if>
		    			     			</#list>
		    			     		</#if>		
	    			 />
	    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">商业计划书</span>
	    			
	    			 <input style=" width:15px;"  type="checkbox"  name="dataAble" value="可行性报告" disabled=""
	    				    		<#if enterprise.dataAble?? && dataAble??>
		    			     			<#list dataAble as item>
		    			     				<#if item == "可行性报告">
		    			     					checked="checked"
		    			     				</#if>
		    			     			</#list>
		    			     		</#if>		
	    			 />
	    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">可行性报告</span>
	    			
	    		    <input style=" width:15px;"  type="checkbox"  name="dataAble" value="其他说明资料" disabled=""
	    				    		<#if enterprise.dataAble?? && dataAble??>	
		    			     			<#list dataAble as item>
		    			     				<#if item == "其他说明资料">
		    			     					checked="checked"
		    			     				</#if>
		    			     			</#list>
		    			     		</#if>		
	    			 />
	    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">其他说明资料</span>
    			</div>    			
    			
    			<div>
    				<p>是否愿意将贵公司所填以上信息向投资金融平台披露</p>
    			<input style=" width:15px;"  type="radio" <#if enterprise.isShow?? &&enterprise.isShow> checked="checked" </#if>name="team" value="" disabled="" />
    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">是（同意请加盖公司公章）</span>
        		<input style=" width:15px;" type="radio" <#if !enterprise.isShow??|| enterprise.isShow??&&!enterprise.isShow> checked="checked" </#if>  name="team" value="" disabled="" />
        		<span style=" width:auto; display: block; margin-left: 10px; margin-top:3px;">否</span>
    			</div>
    	</dd>

    </dl>
                <dl>
                    <input type="button"style="width: 80px; height:30px; border: none; background: #e67817; color: white; border-radius: 6px;  margin-left: 454px;" name="" id="text_show_btn01" value="隐藏资料" />
                </dl>
            </form>
            <dl class="expert_date">
                <dt><span style=" font-size: 16px;">档案跟踪:</span></dt>
                <div id="records">
                    <#include "/client/records.ftl">
                </div>
                <dt>
                    <span>跟踪留言:</span>
                    <textarea id="coach_record"></textarea>
                    <input type="button" value="发表"  onclick="record();"/>
                </dt>
            </dl>
        </div>
    </div><!--content_end-->
    </div><!--main-->
</body>
</html>
