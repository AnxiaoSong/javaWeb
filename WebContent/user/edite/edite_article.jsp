<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
String path = request.getContextPath(); 
// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath
String basePath = request.getScheme()+"://"+request.getServerName()

+":"+request.getServerPort()+path+"/"; 
// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
pageContext.setAttribute("basePath",basePath); 
%> 
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
    
    
    <link rel="stylesheet" href="user/css/style.css" />
        <link rel="stylesheet" href="user/css/editormd.css" />
</head>
<body>
<s:include value="/base.jsp"></s:include>

<!-- Markdown -->
<form  method="post" action="add_arctle">
<ul class="list-group">
<li class="list-group-item layout">
<div align="center">
<label >题目</label>
</div>
<s:textfield name="title" class="form-control" ></s:textfield>
</li>
<li class="list-group-item">
<div align="center">
<label >内容简介</label>
</div>

<s:textfield name="header" class="form-control" ></s:textfield>
</li>
<li class="list-group-item">
 <div id="layout">
            <div id="editormd">              
                <textarea style="display:none;"><s:property value="arctle.getContext"/></textarea>
            </div>
        </div> 
        <script src="user/js/jquery.min.js"></script>
        <script src="user/editormd.js"></script>
        <script type="text/javascript">
            var testEditor;
            
            $(function() {  
            	
            	 editormd.emoji     = {
                         path  : "http://www.emoji-cheat-sheet.com/graphics/emojis/",
                         ext   : ".png"
                     };
         
                     // Twitter Emoji (Twemoji)  graphics files url path    
                     editormd.twemoji = {
                         path : "http://twemoji.maxcdn.com/72x72/",
                         ext  : ".png"
                     };
                     
                testEditor = editormd("editormd",{
                    
                    width  : "80%",
                    height : 600,
                    
                      toc : true,
                    
                    emoji : true,       // Support Github emoji, Twitter Emoji(Twemoji), fontAwesome, Editor.md logo emojis.
                    
                    taskList : true,
                    saveHTMLToTextarea: true,
                    path   : 'user/lib/',
                    onload : function() {
                    }
                });
            });
        </script>
</li>
</ul>
<div class="text-center">
  <input name="submit" class="btn btn-primary" type="submit" value="保存"></input>
 <input name="submit" class="btn btn-primary" type="submit" value="发布"></input>
 </div>
</form>

<s:debug></s:debug>
</body>
</html>