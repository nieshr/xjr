<div class="header">
    <a href="/"><div class="logo" style="background-image:url('/client/images/logo01.png')"></div></a>
    <form action="/info/search">
    <div class="search">
    	<input type="text" name="keywords" value="&nbsp;请输入搜索内容" onfocus="if(this.value=='&nbsp;请输入搜索内容'){this.value='';}" onblur="if(this.value==''){this.value='&nbsp;请输入搜索内容'}" />
        <input style="cursoe:pointer;width:40px; background:url(/client/images/search.png) no-repeat center;" type="submit" value="" />
    </div>
    </form>
    <dl class="porfile">
    	<dt>
    		<#if user??>
    			<#if user.roleId??&&user.roleId == 1>
    				<a href="http://www.cqgczx.com/cqkjxjr/" target="_blank">帮助</a>
    			<#elseif user.roleId??&&user.roleId == 2>
    				<a href="javascript:void(0)" >区县管理员</a>
    			<#elseif user.roleId??&&user.roleId == 3>
    				<a href="javascript:void(0)" >专家</a>
    			<#elseif user.roleId??&&user.roleId == 4>
    				<a href="javascript:void(0)" >活动管理员</a>    				    				
    			</#if>
    		</#if>	
    	</dt>
    	<#if user??>
    		<#if user.roleId == 1>
        		<dt><a href="/enterprise/message">站内信息<#if message_num??&&message_num gt 0><b style="float:right;  color: darkorange; margin: 1px 12px 0 0;">${message_num!''}</b></#if></a></dt>
        	<#elseif user.roleId == 2>
        		<dt><a href="/region/message">站内信息<#if message_num??&&message_num gt 0><b style="float:right;  color: darkorange; margin: 1px 12px 0 0;">${message_num!''}</b></#if></a></dt>
        	</#if>	
        </#if>
        <dd>
        	<div><img src="/client/images/user.png" /></div>
        	<span><a style="color:#fff;" href="javascript:void(0)" title="${username!''}">${username!''}</a></span>
        	<#--
       		<span style="width:70px;">
	           <a style="width:70px;line-height: 60px; display: block;  color: white;  font-size: 14px;  " href="/user/password">修改密码</a>
      		</span>
      		<span style="width:70px;">
	           <a style="width:70px;line-height: 60px; display: block;  color: white;  font-size: 14px;  " href="/logout">注销</a>
      		</span>
      		-->
        
            <p>管理</p>
            <span id="nav_guide"></span>
        </dd>
        <ul id="porfile_subnav">
            <li><a href="/user/password">修改密码</a></li>
            <li><a href="/logout">注销</a></li>     
        </ul>
       
    </dl>
</div>