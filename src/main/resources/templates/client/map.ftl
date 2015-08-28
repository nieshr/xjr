<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>${menu_name!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/news_body.css" rel="stylesheet" type="text/css" />
<link href="/client/css/news_center.css" rel="stylesheet" type="text/css" />
<link href="/client/css/teacher.css" rel="stylesheet" type="text/css" />
<link href="/client/css/teacher_center.css" rel="stylesheet" type="text/css" />
<link href="/client/css/course.css" rel="stylesheet" type="text/css" />
<link href="/client/css/join_us.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/info-list.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>

<style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=sloQ0GqMsUdlG0SLcOVrO49F"></script>
<script type="text/javascript">
$(document).ready(function(){
        loadMap(${site.longitude!'102.718072'}, ${site.latitude!'25.048034'});
});

function loadMap(x, y)
{
    // 百度地图API功能
    /*
    var map = new BMap.Map("allmap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(x, y), 16);  // 初始化地图,设置中心点坐标和地图级别
    
    map.setCurrentCity("昆明");          // 设置地图显示的城市 此项是必须设置的
    
    map.addOverlay(new BMap.Marker(new BMap.Point(x, y)); // 创建点
    */
    
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(x, y);
    map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    var marker = new BMap.Marker(new BMap.Point(x, y)); // 创建点
    map.addOverlay(marker);
}
</script>
</head>
<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--main-->
<div class="main">
    <!--left_content-->
       <#include "/client/common_menu.ftl" />  
    
    <!--right_content-->
    <div class="right_content">
         <div id="allmap" style="height:650;width:800;">  
            
         </div>
    </div>

</div>
<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
