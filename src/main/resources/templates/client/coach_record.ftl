<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改资料</title>
    <link rel="shortcut icon" href="/images/icon.ico" />
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
    window.location.href = "/expert/coach/save?content="+content+"&enterpriseId=${enterpriseId}";
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
                        <dd><a href="/expert/enterprise/list">活动列表</a></dd>
                        <dd><a href="/expert/enterprises">辅导企业</a></dd>
                        <dd><a href="/expert/lyfd">路演辅导</a></dd>
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
                                <a href="#">档案跟踪</a>
                            </dd>
                            <dt class="crumb_back"><a></a></dt>
                        </dl>
                        <div class="change_inform">
                            <span>审核状态：已通过</span>
                        </div>
                    </div>  
                    <input id="text_show_btn" style="width: 80px; height:30px; border: none; background: #e67817; color: white; border-radius: 6px; margin-left: 30px; margin-top: 30px;" type="button" value="显示资料" />
                    <p id="text" style=" margin-left: 30px; font-size: 14px; color: red;">*点击按钮查看资料</p>
                    <form id="apply_change_show" style=" display: none;">
                        <dl class="apply_change" >
                        <dt class="dt01"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
                        <dd>
                            <div><span>企业名称：</span><input type="text" value="${enterprise.title!''}" disabled="" /></div>
                            <div><span>成立时间：</span><input type="text" value="${establish?string('yyyy-MM-dd')}" disabled="" /></div>
                            <div><span>注册资本：</span><input type="text" value="${enterprise.capital}" disabled="" />&nbsp;&nbsp;(万元)</div>
                            <div><span>法定代表人：</span><input type="text" value="${enterprise.representative!''}" disabled="" /></div>
                            <div><span>股东结构：</span><textarea disabled="${enterprise.sharehodler!''}" ></textarea></div>
                            <div><span>所在地区：</span>
                            <select disabled="" >
                                <option>江北</option>
                                <option>南岸</option>
                                <option>巴南</option>
                                <option>合川</option>
                                <option>荣昌</option>
                            </select>
                            </div>
                            <div><span>职工人数：</span><input type="text" value="${enterprise.staffNumber!'0'}" disabled="" />&nbsp;&nbsp;(人)</div>
                            <div>
                                <span>行业归属：</span>
                                <input style="margin-top: -3px; width:15px;" type="checkbox" value="" disabled="" ><p>电子信息</p>
                                <input style="margin-top: -3px; width:15px;" type="checkbox" value="" disabled="" ><p>生物医药</p>
                                <input style="margin-top: -3px; width:15px;" type="checkbox" value="" disabled="" ><p>光电一体化</p>
                                <input style="margin-top: -3px; width:15px;" type="checkbox" value="" disabled="" ><p>资源与环境</p>
                                <input style="margin-top: -3px; width:15px;" type="checkbox" value="" disabled="" ><p>新能源、新材料</p>
                                <input style="margin-top: -3px; width:15px;" type="checkbox" value="" disabled="" ><p>其他</p>		
                            </div>
                            <div><span>邮箱：</span><input type="text" value="${enterprise.email!''}" disabled="" /></div>
                            <div><span>联系人：</span><input type="text" value="${enterprise.contact!''}" disabled="" /></div>
                            <div><span>公司网站：</span><input type="text" value="${enterprise.website!''}" disabled="" /></div>
                            <div><span>联系电话：</span><input type="text" value="${enterprise.telephone!''}" disabled="" /></div>
                            <div><span>传真：</span><input type="text" value="${enterprise.fax!''}" disabled="" /></div>
                            <div><span>QQ/MSN：</span><input type="text" value="${enterprise.shat!''}" disabled="" /></div>
                            <div><span>手机：</span><input type="text" value="${enterprise.mobile}" disabled="" /></div>
                            <div><span>企业简介：</span><textarea disabled="" >${enterprise.profile!''}</textarea><span>(200字以内)</span></div>
                            <div><span>公司团队：</span><textarea disabled="" >${enterprise.teamlntroduction!''}</textarea><span>(200字以内)</span></div>
                            <div><span>技术特点及优势：</span><textarea disabled="" >${enterprise.advantage!''}</textarea><span>(200字以内)</span></div>
                            <div><span>市场规模行业地位：</span><textarea disabled="" >${enterprise.size}</textarea><span>(200字以内)</span></div>
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
                            <input type="text" value="${enterprise.lastAssets3!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastNetAssets3!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastSale3!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastProfit3!''}"  disabled="" />
                        </div>
                        <div>
                            <span>2013</span>
                            <input type="text" value="${enterprise.lastAssets2!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastNetAssets2!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastSale2!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastProfit2!''}"  disabled="" />
                        </div>
                        <div>
                            <span>2014</span>
                            <input type="text" value="${enterprise.lastAssets!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastNetAssets!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastSale!''}"  disabled="" />
                            <input type="text" value="${enterprise.lastProfit!''}"  disabled="" />
                        </div>
                    </dd>
                    <dt class="dt03"><span>三、知识产权基本情况</span><br/><p>此信息将自动生成到报名表中</p></dt>
                    <dd>
                        <div><span>发明专利</span><input type="text" value="${enterprise.inventiPatent!''}"  disabled="" /></div>
                        <div><span>实用新型专利</span><input type="text" value="${enterptise.newPatent!''}"  disabled="" /></div>
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
                        <input type="text" value="${enterprise.expectEquityDate?string('yyyy-MM-dd')}"  disabled="" />
                        <input type="text" value="${enterprise.expectEquityAmount}"  disabled="" />
                        <input type="text" value="${enterprise.expectEquityUse!''}"  disabled="" />
                        </div>
                        <div>
                        <span>（二）债权融资</span>
                        <input type="text" value="${enterprise.expectBondDate?string('yyyy-MM-dd')}"  disabled="" />
                        <input type="text" value="${enterprise.expectBondAmount}"  disabled="" />
                        <input type="text" value="${enterprise.expectBondUse!''}"  disabled="" />
                        </div>
                        <div>
                        <p>是否愿意将贵公司所填以上信息向投资金融平台披露</p>
                        <input style=" width:15px;"  type="radio"  <#if enterprise?? && enterprise.isShow>checked="checked"</#if> name="team" value="" disabled="" />
                        <span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">是（同意请加盖公司公章）</span>
                        <input style=" width:15px;" type="radio"  name="team"<#if enterprise?? && enterprise.isShow><#else>checked="checked"</#if> value="" disabled="" />
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
