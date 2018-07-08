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
<s:include value="../base.jsp"></s:include>
<s:if test="#session.current_user!=null">
<label><span class="icon-bar brand">你关注的人:</span> 共有<s:property value="#session.current_user.getFullowed.size"/>人</label>
<div class="span4">
 
				<table class="table table-hover">
					<thead>
						<tr>
						    <th>用户名</th>
							<th>时间</th>
							<th>是否屏蔽</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="fd" value="#session.current_user.getFullowed()">
						<tr class="info">
						    
						   <td><a  href=""><s:property value="#fd.getFullow_name" /></a> </td>
							<td><a href=""><s:property value="#fd.getCreate_time" /></a></td>
			
				           <s:url var="editeUrl" action="#">
                            <s:param name="id" value="#fd.getId()"/>
                            </s:url>
							<td><a href="${editeUrl}"><s:property value="#fd.isIs_fullowed" /></a></td>
							<s:url var="delUrl" action="del_fullow">
                            <s:param name="id" value="#fd.getId()"/>
                            </s:url>
							<td><a href="${delUrl}">删除</a></td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			
<!-- 关注你的人 -->
<label><span class="icon-bar brand">你的粉丝:</span> 共有<s:property value="#session.current_user.getFullower.size"/>人</label>
<div class="span4">
 
				<table class="table table-hover">
					<thead>
						<tr>
						    <th>用户名</th>
							<th>时间</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="fd" value="#session.current_user.getFullower">
					     
						<tr class="info">
						    <s:url var="showUrl" action="#">
                            <s:param name="id" value="#fd.getFullowers_id()"/>
                            </s:url>
						   <td><a  href="${showUrl}"><s:property value="#fd.getFullowers_name" /></a> </td>
							<td><a href=""><s:property value="#fd.getCreate_time" /></a></td>
						</s:iterator>
					</tbody>
				</table>
			</div>
			
			
						
			
</s:if>
<s:debug></s:debug>
</body>
</html>