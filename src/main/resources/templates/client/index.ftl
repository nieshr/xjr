<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
	<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>科技小巨人-首页</title>
<!--css-->
<link rel="shortcut icon" href="/images/icon.ico" />
<link href="/client/css/swiper.min.css" rel="stylesheet" type="text/css" >
<link href="/client/css/l_base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/l_main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
    function collectOnline(){
        
    }
</script>

</head>
<body>
 
<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide" id="s1">
        <div id="go_top" class="first">
            <!-- 导航 -->
              <div class="wrapper">
                <ul class="wrapper-nav" id="wrapper-nav">
                  <a href="/"><li class="active">首页</li></a>
                  <a href="#"><li>专项行动</li></a>
                  <a href="#"><li>培育活动</li></a>
                  <a href="#"><li>新闻动态</li></a>
                  <a href="#"><li>企业项目</li></a>
                  <a href="#"><li>专家资源</li></a>
                  <a href="#"><li>合作机构</li></a>
                  <a href="#"><li>联系方式</li></a>
                </ul>
                <#if username??>
                    <div class="logoin"><a href="/user" style="color:#ff4040;">${username}</a><span>|</span><a href="/logout">退出</a></div>
                <#else>
                    <div class="logoin"><a href="/login">登录</a><span>|</span><a href="/reg">注册</a></div>
                </#if>
              </div>
            <!-- 导航end -->

            <div class="clear"></div>

            <div class="banner1">
                <img src="/client/l_images/QR_code.png" alt="二维码" />
                <div style="position:absolute; left:0px; top:723px; width:100%;" >
                    <a style="margin:0 auto; border:white 2px solid;height:64px;width:280px; display:block;line-height: 64px; border-radius: 10px; color:white; font-size:38px;" href="/enterprise/check">在线征集</a>
                </div>
            </div>

        </div>
            </div>
<!--  
        <div class="clear"></div>

        <div class="swiper-slide" id="s2">
        <div class="second">
            <div class="section1">
                <div class="div1">
                    <p class="p1">组织体系</p>
                    <p class="p2">Organization system</p>
                </div>
                <ul>
                <#if organization_0_list??>
                    <#list organization_0_list as item>
                        <#if item_index == 0>
                            <li class="li1">
                                <p class="p1">${item.title!''}</p>
                                <#if ("organization_level1_"+item.id)?eval??>
                                    <#list ("organization_level1_"+item.id)?eval as article>
                                        <#if article_index < 1>
                                            <p>${atricle.title!''}</p>
                                        </#if>
                                    </#list>
                                </#if>
                                                                
                        <#elseif item_index == 1>
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                  <#list ("organization_level1_"+item.id)?eval as article>
                                        <#if article_index < 5>
                                            <p>${atricle.title!''}</p>
                                        </#if>
                                  </#list>
                            </#if>                              
                            </li>
                        <#elseif item_index == 2>
                            <li class="li2">
                                <p class="p1">${item.title!''}</p>
                                <#if ("organization_level1_"+item.id)?eval??>
                                    <#list ("organization_level1_"+item.id)?eval as article>
                                        <#if article_index < 6>
                                            <p>${atricle.title!''}</p>
                                        </#if>
                                    </#list>
                                </#if>                                
                            </li>
                            <li class="li3 current">
                                <p class="p1">${item.title!''}</p>
                                <#if ("organization_level1_"+item.id)?eval??>
                                    <#list ("organization_level1_"+item.id)?eval as article>
                                        <#if article_index gt 5>
                                            <p>${atricle.title!''}</p>
                                        </#if>
                                    </#list>
                                </#if>                                
                            </li>
                        <#else>
                            <li class="li4">
                                <p class="p1">${item.title!''}</p>
                                <#if ("organization_level1_"+item.id)?eval??>
                                    <#list ("organization_level1_"+item.id)?eval as article>
                                        <#if article_index < 6>
                                            <p>${atricle.title!''}</p>
                                        </#if>
                                    </#list>
                                </#if>
                                
                            </li>
                        </#if>
                    </#list>
                </#if>                    
                </ul>
            </div>
            <div class="section2">
                <div class="div1">
                    <p class="p1">行动概况</p>
                    <p class="p2">Introduction</p>
                    <p class="p3">“重庆市科技小巨人企业培育专项行动面向科技型创业团队、处于初创期的科技型企业，
连续3年开展创投每周行、创业训练营、年度创业SHOW等系列活动，
并与入营培训、落地培育相结合，通过线上推广、
项目辅导、项目路演、线下培育等多种形式，发掘、评选优秀创新创业项目和企业，
力争3年内发掘遴选3000个优秀创新创业项目，
培育1000家科技小巨人企业。</p>
                    <p class="p4"><a href="#">查看更多</a></p>
                </div>
            </div>
        </div>
        </div>

        <div class="swiper-slide" id="s3">
            <div class="three">
                <div class="section1">
                    <!-- <div>speed</div> -->
                    <div class="parnet">
                      <div class="child">
                        <div class="child1">
                          <div class="child1_1">
                              <p class="p1">科技小巨人培育专项</p>
                              <p class="p2">每周行</p>
                              <p class="p3">国际青年科技创业大赛是由北京市海淀区人民政府和北京大学携手发起</p>
                              <p class="p4">
                                  <a href="#" class="a1">查看详情&rarr;</a>
                                  <a href="#" class="a2">了解更多信息</a>
                              </p>  
                          </div>
                          <img src="/client/l_images/pic1.png" width="420" height="280" /></div>
                          <div class="child1">
                          <div class="child1_1">
                              <p class="p1">科技小巨人培育专项</p>
                              <p class="p2">每周行</p>
                              <p class="p3">国际青年科技创业大赛是由北京市海淀区人民政府和北京大学携手发起</p>
                              <p class="p4">
                                  <a href="#" class="a1">查看详情&rarr;</a>
                                  <a href="#" class="a2">了解更多信息</a>
                              </p>  
                          </div>
                          <img src="/client/l_images/pic1.png" width="420" height="280" /></div>
                          <div class="child1">
                          <div class="child1_1">
                              <p class="p1">科技小巨人培育专项</p>
                              <p class="p2">每周行</p>
                              <p class="p3">国际青年科技创业大赛是由北京市海淀区人民政府和北京大学携手发起</p>
                              <p class="p4">
                                  <a href="#" class="a1">查看详情&rarr;</a>
                                  <a href="#" class="a2">了解更多信息</a>
                              </p>  
                          </div>
                          <img src="/client/l_images/pic1.png" width="420" height="280" /></div>
                          <div class="child1">
                          <div class="child1_1">
                              <p class="p1">科技小巨人培育专项</p>
                              <p class="p2">每周行</p>
                              <p class="p3">国际青年科技创业大赛是由北京市海淀区人民政府和北京大学携手发起</p>
                              <p class="p4">
                                  <a href="#" class="a1">查看详情&rarr;</a>
                                  <a href="#" class="a2">了解更多信息</a>
                              </p>  
                          </div>
                          <img src="/client/l_images/pic1.png" width="420" height="280" /></div>
                      </div>      
                    </div>
                    <a class="prev" href="#"></a>
                    <a class="next" href="#"></a>
                    <script type="text/javascript" src="/client/js/jquery.js"></script>
                    <script src="/client/js/scroll.js"></script>
                </div>
                
                <div class="clear"></div>

                <div class="section2">
                    <div class="div1">
                        <p class="p1_1">五大支持</p>
                        <p class="p2_1">Support</p>
                    </div>
                    <div class="div2">
                        <p class="p1">组建创业种子投资引导基金<br />对创投每周行评选出的<br />优秀项目及企业<br />给予至少10万元的种子基金支持</p>
                        <p class="p2">
                            <i class="i1"></i>
                            <b>基金支持</b>
                        </p>
                    </div>
                    <div class="div3">
                        <p class="p1">组建”重庆市科技小巨人创业导师辅导团”<br />为优秀项目和企业提供创业训练与服务。</p>
                        <p class="p2">
                            <i class="i2"></i>
                            <b>交流辅导</b>
                        </p>
                    </div>
                    <div class="div4">
                        <p class="p2">
                            <i class="i3"></i>
                            <b>媒体推广</b>
                        </p>
                        <p class="p1">在市级主流媒体上对特色项目<br />优秀创业人物及团队进行推广报道</p>
                    </div>
                    <div class="div5">
                        <p class="p2">
                            <i class="i4"></i>
                            <b>落地培育</b>
                        </p>
                        <p class="p1">评选出的优秀项目<br />入驻所在推荐单位区域内的<br />创客空间、孵化器、加速器进行落地培育</p>
                    </div>
                    <div class="div6">
                        <p class="p2">
                            <i class="i5"></i>
                            <b>推荐创赛</b>
                        </p>
                        <p class="p1">推荐优秀项目进入<br />中国创新创业大赛重庆赛区决赛</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="swiper-slide" id="s4">
            <div class="four">
                <div class="sect1">
                    <div class="div1">
                        <p class="p1">新闻动态</p>
                        <p class="p2">News dynamics</p>
                    </div>
                    <div class="div2">
                        <video width="480" height="400" src="/images/20151014153104349.mp4" controls="controls" id="video" autoplay="autoplay">
                                                
                        </video>
                        <ul>
                        <#if news_list??>                   
                            <#list news_list as item>
<script>

function showVideo${item_index}(){
    $("#video").text("<embed src='/images/20151014153310798.mp4' type='video/x-ms-asf-plugin' width='550' height='400' autostart='false' loop='true' />");
}     

</script> 
                                <#if item_index < 3>
                                    <li class="li1"><a href="javascript:showVideo${item_index}();">
                                        <img src="${item.imgUrl!''}" width="173" height="120" alt="新闻图片" />
                                        <div class="newsmess">
                                            <p class="p1"><#if item.title?length lt 18>
                                                ${item.title!''}
                                            <#else>
                                                ${item.title[0..17]?default("")}...
                                            </#if></p>
                                            <p class="p2">${item.brief!''}</p>
                                            <p class="p3">
                                                <span class="span2"><i class="i2"></i>${item.viewCount!'0'}</span>
                                                <span class="span1"><i class="i1"></i>03:22</span>
                                            </p>
                                        </div>
                                    </a></li>
                                </#if>
                            </#list>    
                        </#if>                            
                        </ul>
                    </div>
                </div>
                <div class="sect2">
                    <ol>
                    <#if news_list??>
                        <#list news_list as item>
                            <#if item_index < 4>
                                <li class="li1"><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
                                   <img src="${item.imgUrl!''}" width="173" height="120" alt="新闻图片" />
                                    <div class="newsmess1">
                                        <p class="p1"><#if item.title?length lt 18>
                                            ${item.title!''}
                                        <#else>
                                            ${item.title[0..17]?default("")}...
                                        </#if></p>
                                        <p class="p2">${item.createTime?string("yyyy-MM-dd")}</p>
                                        <p class="p3">${item.brief!''}</p>
                                    </div> 
                                </a></li>
                            </#if>
                        </#list>
                    </#if>                       
                    </ol>
                </div>
            </div>
        </div>

        <div class="swiper-slide footer" id="s5" >
            <div class="five">
                <!-- 底部 -->
                <#include "/client/news_common_footer.ftl" />
                <!-- 底部end -->
            </div>
        </div>
-->

    </div>
    <!--右侧按钮-->
    <div class="swiper-pagination"></div>
    <!--右侧浮动导航开始-->
    <div class="floatbox">
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=1981148933&site=qq&menu=yes" target="_blank" title="申报入口">
        <img src="/client/l_images/float_ico02.png" width="42" height="42" alt="申报入口">
      </a>
      <a href="http://weibo.com/ynsite" title="搜索" target="_blank" rel="nofollow">
        <img src="/client/l_images/float_ico01.png" width="42" height="42" alt="搜索">
      </a>
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=1981148933&site=qq&menu=yes" target="_blank" title="在线客服">
        <img src="/client/l_images/float_ico06.png" width="42" height="42" alt="在线客服">
      </a>
      <a href="javascript:loginWinOpen('weixin_win','myselfbox',200);" title="官方微信">
        <img src="/client/l_images/float_ico03.png" width="42" height="42" alt="官方微信">
        <span class="qr"><img src="/client/l_images/QR_code.png"></span>
      </a>
      
      <a title="客服电话">
        <img src="/client/l_images/float_ico04.png" width="42" height="42" alt="客服电话">
        <span class="phone_num">023-8888 8888</span>
      </a>
      <a href="#go_top" title="到顶部">
        <img src="/client/l_images/float_ico05.png" width="42" height="42" alt="到顶部">
      </a>
    </div>
    <!--右侧浮动导航结束-->
</div>
    <!--底部按钮-->
    <div class="swiper-button-next">
        <div class="next-but-arraw"><img src="/client/l_images/btn-next.png"></div>
    </div>
 
    <script src="/client/js/swiper.min.js"></script>
    <script type="text/javascript">
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            direction: 'vertical',
    		// 如果需要前进后退按钮
    		nextButton:'.swiper-button-next',
    		keyboardControl:true,
    		mousewheelControl:true,
    		direction:'vertical',
    		mousewheelForceToAxis:true,
    		//自动滚动 autoplay:3000
        });
    </script>
</body>
</html>