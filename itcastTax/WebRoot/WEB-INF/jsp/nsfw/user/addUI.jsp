<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js">
    </script>
    <script type="text/javascript">
    var vResult = false;
    	//校驗帳號唯一性
    	function onVerifyAccount() {
    		//獲取帳號的值
    		var account = $("#account").val();
    		if (account != "") {
    			//校驗 get post getJson ajax
    			$.ajax({
    				url:"${basePath}nsfw/user_verifyAccount.action",
    				data:{"user.account" : account},
    				type:"post",
    				async:false,//強制Ajax同步
    				success:function(msg) {
    					if ("true" != msg) {
    						//帳號已經存在請使用其他的帳號
    						alert("帳號已經存在，請使用其他帳號！");
    						//提示完成后定焦，這樣對用戶友好
    						$("#account").focus();
    						vResult = false;
    					} else {
    						vResult = true;
    					}
    				}
    			});
    		}
    	}
    	function onSubmit() {
    		
    		var name = $("#username");
    		if (name.val() == "") {
				alert("用戶名不可以為空！");
				name.focus();
				return false;//不準提交表單
    		}
    		var pass = $("#password");
    		if (pass.val() == "") {
				alert("密碼不可以為空！");
				pass.focus();
				return false;//不準提交表單
    		}
    		//帳號校驗
    		onVerifyAccount();
    		if (vResult) {
    			//提交表單
    			document.forms[0].submit();
    		}
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}nsfw/user/user_add.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部門A':'部門A','部門B':'部門B'}"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield id="username" name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield id="account" name="user.account"
            onchange="onVerifyAccount()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield id="password" name="user.password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
            	<s:checkboxlist list="#roleList" name="roleIds" listKey="roleId" listValue="name" />
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <!-- 日期字段是不可以改動的！ 所以設置了一個readonly字段 再使用WdatePicker組件 -->
            <td><s:textfield id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen', 'dateFmt':'yyyy-MM-dd'})"/></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="onSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>