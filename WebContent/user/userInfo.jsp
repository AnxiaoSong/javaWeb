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
<label><span class="icon-bar brand">你的基本信息:</span>
</label>
<div>
<ul class="list-group">
 <li class="list-group-item list-group-item-info"> <label for="">用户名: </label><s:property value="#session.current_user.getUsername()"/></li>
 <li class="list-group-item list-group-item-info"> <label for="">密码: </label> <s:property  value='#session.current_user.getPassword()'/></li>
 <li class="list-group-item list-group-item-info">  <label for="">Email: </label> <s:property value='#session.current_user.getEmail()'/></li>
<li><a  class="info" data-toggle="modal" data-target="#userModal" href="#resgister" ><label class="navbar-brand" for="">修改</label></a></li>
</ul>
</div>
  <div id="userModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">个人信息</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" action="update_user_info" method='post'>
                            <s:hidden name="id" value="%{#session.current_user.getId}"></s:hidden>
                            <div class="form-group">
                            
                                <label for="">用户名</label>
                                
                                <s:textfield name="username" class="form-control" value="%{#session.current_user.getUsername}" />
                            </div>
                            
                               <div class="form-group">
                                <label for="">密码</label>
                                <s:password type="password" name="password" class="form-control"   value='#session.current_user.getPassword()' />
                            </div>
                            <div class="form-group">
                                <label for="">再次输入密码</label>
                                <s:password  name='password_agine' class="form-control"  type="password"   value='#session.current_user.getPassword()'/>
                            </div>
                            
                            <div class="form-group">
                                <label for="">邮箱</label>
                                <s:textfield name="email" class="form-control" type="email" value="%{#session.current_user.getEmail}" />
                            </div>
                            <div class="text-right">
                                <input class="btn btn-primary" type="submit" value="保存"></input>
                                <button class="btn btn-danger" data-dismiss="modal" >取消</button>
                               <input class="btn btn-primary" type='reset'></div>
                    </form>
                    </div>
                </div>
            </div>
        </div>     

<div>
<br>
<label><span class="icon-bar brand">你的文章:</span></label>
共有<s:property value="#session.current_user.getPost().size()"/>篇 
<table class="table table-hover">
					<thead>
						<tr>
						    <th>题目</th>
							<th>内容主题</th>
							<th>创作时间</th>
							<th>状态</th>
							<th>发布时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="post" value="#session.current_user.getPost()" >
						<tr class="info">
						   <td><a  href=""><s:property  value="#post.getAuthor_title" /></a> </td>
							<td><a href=""><s:property value="#post.getAuthor_header" /></a></td>
							<td><s:property value="#post.getCreate_time" /></td>
							<td><a href="#"><s:property value="#post.is_pub"/></a></td>
							<td><s:property value="#post.getPub_time"/></td>
							
							 <s:url var="delUrl" action="del_arctle">
                            <s:param name="id" value="#post.getId"/>
                            <s:param name="ac_id" value="#post.arctle_id"/>
                            </s:url>
     
							
							<td><a href="${delUrl}">删除</a>
							 
							 
							 <s:url var="pubUrl" action="update_post">
                            <s:param name="arctle_id" value="#post.arctle_id"/>
                                                                            <s:param name="id" value="#post.id"/>
                            </s:url>
							<a href="${pubUrl}">发布</a>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<script>
      function readyDel(tile){
        return confirm("是否真的删除"+title+"？");
      }
    </script>
<s:debug></s:debug>
</body>
</html>