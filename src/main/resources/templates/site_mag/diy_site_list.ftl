<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>角色列表</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>

<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/order/setting/diysite/list" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}">
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}">
</div>
<script type="text/javascript">
var theForm = document.forms['form1'];
if (!theForm) {
    theForm = document.form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
</script>
<!--导航栏-->
<div class="location">
  <a href="/Verwalter/order/setting/diysite/list" class="back"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>角色列表</span>
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
  <div id="floatHead" class="toolbar">
    <div class="l-list">
      <ul class="icon-list">
        <li><a class="add" href="/Verwalter/order/setting/diysite/edit"><i></i><span>新增</span></a></li>
        <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
        <#--<li><a id="btnSave" class="save" href="javascript:__doPostBack('btnSave','')"><i></i><span>保存</span></a></li>-->
        <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
      </ul>
    </div>
    <#--
    <div class="r-list">
      <input name="keywords" type="text" class="keyword" value="${keywords!''}">
      <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
    </div>
    -->
  </div>
</div>
<!--/工具栏-->

<!--列表-->

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  <tr class="odd_bg">
    <th width="8%">选择</th>
    <th align="left">登录名</th>
    <th align="left">角色类型</th>
    <th align="left">密码</th>
    <th width="15%" align="left">所属</th>
    <th align="left">负责人</th>
    <th align="left">手机号</th>
    <th align="left" width="5%">排序</th>
    <#--<th width="8%">是否启用</th>-->
    <th width="12%">操作</th>
  </tr>

    <#if diy_site_page??>
        <#list diy_site_page.content as item>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${item_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${item.id}">
                </td>
                <td><a href="/Verwalter/order/setting/diysite/edit?id=${item.id}">${item.username!""}</a></td>
                <td>${item.title!""}</td>
                 <td>${item.password!''}</td>
                <td>${item.region!''}</td>
                <td>${item.inCharge!""}</td>
                <td>${item.mobile!''}</td>
                <#--><td>${item.city!""}</td>
                <td>${item.address!""}</td>-->
                <td><input name="listSortId" type="text" value="${item.sortId!""}" disabled="" class="sort" onkeydown="return checkNumber(event);"></td>

                <#--<td align="center"><#if item.statusId?? && item.statusId == 1>是<#else>否</#if></td>-->
                <td align="center">
                    <a href="/Verwalter/order/setting/diysite/edit?id=${item.id}">修改</a>
                    <a href="/Verwalter/order/diysite/role?id=${item.id}">进入</a>
                </td>
              </tr>
        </#list>
    </#if>
 
</tbody></table>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=diy_site_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>

</body></html>