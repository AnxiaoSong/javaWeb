<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
 
    <s:if test="<s:property value='title'/>=='课程设计'">
    
    <title> <s:property value="title"/></title>
    </s:if>
    <s:else>
    <title>课程设计</title>
    </s:else>
    
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

  <nav class = "navbar navbar-default" role = "navigation">
        <div class="navbar-header">           
            <a class="navbar-brand">课程设计</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                
                <li><a href="index">网站首页</a></li>
                <li><a href="show_message">消息</a></li>
                <li><a href="show_fullow">关注</a></li>
            </ul>
       
         <ul class="nav navbar-nav navbar-right">
        <li><a href="write_arctle"><span class="glyphicon glyphicon-pencil"></span>写文章</a></li>
        <s:if test="#session.current_user == null">
        <li><a data-toggle="modal" data-target="#register" href="#resgister" ><span class="glyphicon glyphicon-user" ></span> 注册</a></li>
        <li><a data-toggle="modal" data-target="#login"  href="#" ><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
       </s:if>
        <s:else>
         <li><a  href="show_currentUserInfo"><span class="glyphicon glyphicon-user"></span> 个人信息</a></li>
        <li><a   href="login_out"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
        </s:else>
    </ul>
        </div>
     </nav>
    <!-- 注册窗口 -->
    <div id="register" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">注册</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" action="add_user" method='post'>
                            <div class="form-group">
                                <label for="">用户名</label>
                                <input name='username' class="form-control" type="text" placeholder="字母或数字">
                            </div>
                            <div class="form-group">
                                <label for="">密码</label>
                                <input  name='password' class="form-control" type="password" placeholder="至少6位字母或数字">
                            </div>
                            <div class="form-group">
                                <label for="">再次输入密码</label>
                                <input name='password_agine' class="form-control" type="password" placeholder="至少6位字母或数字">
                            </div>
            
                            <div class="form-group">
                                <label for="">邮箱</label>
                                <input name='email' class="form-control" type="email" placeholder="例如:123@123.com">
                            </div>
                            <div class="text-right">
                                <input class="btn btn-primary" type="submit"></input>
                                <button class="btn btn-danger" data-dismiss="modal" >取消</button>
                               <input class="btn btn-primary" type='reset'></div>
                         
                            <a  data-toggle="modal" data-dismiss="modal" data-target="#login">已有账号？点我登录</a>
                    </form>
                    </div>
                </div>
            </div>
        </div>     
    <!-- 登录窗口 -->
    <div id="login" class="modal fade" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">登录</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" action="login" method='post'>
                            <div class="form-group">
                                <label for="">Email</label>
                                <input name='Email' class="form-control" type="email" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="">密码</label>
                                <input name='password' class="form-control" type="password" placeholder="">
                            </div>
                            <div class="text-right">
                                <input class="btn btn-primary" type="submit" value='登录'></input>
                                <button class="btn btn-danger" data-dismiss="modal">取消</button>
                            </div>
                            <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register">还没有账号？点我注册</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- 定 以提示窗口-->  
<s:if test="message!=null">
<div>
    <div class="alert alert-warning">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <s:property value="message"/>
    </div>
    </div>
    </s:if>
</body>