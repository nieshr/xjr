<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>Document</title>
    <link href="/client/css/form.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
</head>
<body style="background: none;">
    <table class="score">
        <caption>项目得分汇总表</caption>
        <tr class="tr01">
            <#if expert0_grade??>
                <#list expert0_grade as item>
                    <td>${item.number!''}</td>
                </#list>
            </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert0!''}</td>
        <#if expert0_grade??>
            <#list expert0_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert1!''}</td>
        <#if expert1_grade??>
            <#list expert1_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert2!''}</td>
        <#if expert2_grade??>
            <#list expert2_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert3!''}</td>
        <#if expert3_grade??>
            <#list expert3_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert4!''}</td>
        <#if expert4_grade??>
            <#list expert4_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert5!''}</td>
        <#if expert5_grade??>
            <#list expert5_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
        </tr>
        
        <tr class="tr02">
        <td>${expert6!''}</td>
        <#if expert6_grade??>
            <#list expert6_grade as item>
                <#if item.totalPoint??>
                    <td>${item.totalPoint?string("0.00")}</td>
                <#else>
                    <td></td>
                </#if>
            </#list>
        </#if>
    </table>
    <input style="margin-left: 640px; margin-top: 20px; width:100px;height: 30px; font-size: 14px;" type="button" value="打印评分表" />
</body>
</html>