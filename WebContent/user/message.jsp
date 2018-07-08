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
<label><span class="icon-bar brand">与你相关的消息:</span></label>
<div class="span4">
 
				<table class="table table-hover">
					<thead>
						<tr>
						    <th>发送人</th>
							<th>内容主题</th>
							<th>时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="mess" value="#session.current_user.getUser_message()" >
						<tr class="info">
						   <td><a  href=""><s:property value="#mess.getSend_user_name()" /></a> </td>
							<td><a href=""><s:property value="#mess.getContext()" /></a></td>
							<td><s:property value="#mess.getCreate_time()" /></td>
				           <s:url var="editeUrl" action="#">
                            <s:param name="id" value="#mess.getId()"/>
                            </s:url>
							<td><a href="${editeUrl}"><s:property value="#mess.getIs_read()" /></a></td>
							<s:url var="delUrl" action="del_message">
                            <s:param name="id" value="#mess.getId()"/>
                            </s:url>
							<td><a href="${delUrl}">删除</a></td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
</s:if>
<s:debug></s:debug>
</body>
</html>