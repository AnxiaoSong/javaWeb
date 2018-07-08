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
<s:include value="/base.jsp"></s:include>
<label><span class="icon-bar brand">作者的基本信息:</span>
</label>
<div>
<ul class="list-group">
 <li class="list-group-item list-group-item-info"> <label for="">用户名: </label><s:property value="user.username"/></li>
 <li class="list-group-item list-group-item-info">  <label for="">Email: </label> <s:property value='user.email'/></li>
</ul>
</div>
<div>

<label><span class="icon-bar brand">他的的文章:</span></label>
共有<s:property value="user.getPost().size()"/>篇 
<table class="table table-hover">
					<thead>
						<tr>
						    <th>题目</th>
							<th>内容主题</th>
							<th>创作时间</th>
							<th>状态</th>
							<th>发布时间</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="post" value="user.post" >
						<tr class="info" >
						 <s:url var="readArctleUrl" action="show_arctle">
                          <s:param name="arctle_id" value="#post.getArctle_id"/>
                           </s:url>
						   <td><a  href="${readArctleUrl}"><s:property  value="#post.getAuthor_title" /></a> </td>
							<td><a href=""><s:property value="#post.getAuthor_header" /></a></td>
							<td><s:property value="#post.Create_time" /></td>
							<td><s:property value="#post.Is_pub"/></td>
							<td><s:property value="#post.Pub_time"/></td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
<s:debug></s:debug>
</body>
</html>