<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>合作加盟</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<link rel="shortcut icon" href="/client/images/lixue.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/cooperation.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/info-list.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        
        //初始化表单验证
        $("#form1").Validform({
            tiptype: 3,
            ajaxPost:true,
            callback: function(data) {
                if (data.code==0)
                {
                    alert("提交成功");
                    window.location.reload();
                }
                else
                {
                    alert(data.message);
                }
            }
        });
       $("#Validform_msg").hide();
    });
    </script>
    
    <script type="text/javascript">
function changePage(page)
{
    $.ajax({
        type:"get",
        url:"/cooperation",
        data:{"page":page},
        success:function(data){
            $(".right_content").html(data);
        },
        error:function(e){
        alert(e);
        }
    });
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
    	<#include "/client/cooperation_detail.ftl" />  
    </div>

</div>
<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
