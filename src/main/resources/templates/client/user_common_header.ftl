<div class="header">
    <a href="/"><div class="logo" style="background-image:url('/client/images/logo01.png')"></div></a>
    <form>
    <div class="search">
    	<input type="text" value=" &nbsp;请输入搜索内容" />
        <input style="width:40px; background:url(/client/images/search.png) no-repeat center;" type="submit" value="" />
    </div>
    </form>
    <dl class="porfile">
    	<dt><a>帮助</a></dt>
    	<#if user??>
    		<#if user.roleId == 1>
        		<dt><a href="/enterprise/message">站内信息</a></dt>
        	<#elseif user.roleId == 2>
        		<dt><a href="/region/message">站内信息</a></dt>
        	</#if>	
        </#if>
        <dd>
        	<div><img src="/client/images/user.png" /></div>
        	<span>${username!''}</span>
            <p>管理</p>
            <span id="nav_guide"></span>
        </dd>
        <ul id="porfile_subnav">
        	<#--<li><a href="#">帐号资料</a></li>-->
            <#--<li><a href="#">头像设置</a></li>-->
            <li><a href="/user/password">修改密码</a></li>
            <li><a href="/logout">注销</a></li>     
        </ul>
    </dl>
</div>