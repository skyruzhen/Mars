<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/22
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>用户信息详情</title>
    <meta name=viewport content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
</head>
<body>
微信用户信息<br/>
<div>用户昵称：${userinfo.nickname}</div>
<div>用户头像：<img style="vertical-align: top" width="100" height="100" src="${userinfo.headimgurl}"></div>
</body>
</html>
