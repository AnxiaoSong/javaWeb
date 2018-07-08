<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- 新 Bootstrap 核心 CSS 文件 -->  
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">  
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->  
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>  
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->  
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>  
        
<script src="https://cherrymonth.top/static/js/jquery-1.11.1.min.js"></script>
<script src="https://cherrymonth.top/static/js/jquery.validate.min.js"></script>
<script src="https://cherrymonth.top/static/js/messages_zh.js"></script>
</head>
<body>
<s:include value="base.jsp"></s:include>
<label><span class="icon-bar brand">文章:</span>
</label>
<div>
<ul class="list-group">
<s:iterator var="pt" value="#session.post_list" >
 <li class="list-group-item list-group-item-info">
 
 <s:url var="readArctleUrl" action="show_arctle">
  <s:param name="arctle_id" value="#pt.getArctle_id"/>
  </s:url>
  
 <a href="${readArctleUrl} "> <label>题目: </label><s:property value="#pt.getAuthor_title"/></a></li>
 <li class="list-group-item list-group-item-info">
  <s:url var="readAuthorUrl" action="show_userInfo">
  <s:param name="id" value="#pt.author_id"/>
  </s:url>
  <s:property value="#pt.author_id"/>
  <a href="${readArctleUrl}"><s:property value="#pt.getAuthor_header"/></a></li>
 <li class="list-group-item list-group-item-success">最后发表:&nbsp;on&nbsp;<s:property value="#pt.getPub_time"/>&nbsp;by&nbsp;
 <a href="${readAuthorUrl}"><s:property value="#pt.getAuthor_name"/></a></li>
 <br>
</s:iterator>
</ul>
</div>
<s:debug></s:debug>
</body>
</html>