<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="utf-8" />
        <title>课程设计</title>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="user/css/editormd.preview.css" />
    </head>
    <body>
    <s:include value="/base.jsp"></s:include>
    <div id="layout">
        <ul class="list-group">
           <li class="list-group-item">
           <div class="text-center">
           <h1><s:property value="arctle.getTitle"/></h1>
           </div>
           </li>
           <li  class="list-group-item">
            <div class="text-right">
            <s:property value="arctle.author_id"/>
              <s:url var="readAuthorUrl" action="show_userInfo">
                 <s:param name="id" value="arctle.author_id"/>
                </s:url>
             <s:url var="addfullow" action="add_fullow">
              <s:param name="fullow_id" value="arctle.author_id"></s:param>
              <s:param name="arctle_id" value="arctle.id"/>
              <s:param name="rec_user_name" value="arctle.author_name"/>
              <s:param name="likes_num"  value="arctle.likes_num "/>
             </s:url>
            <a href="${readAuthorUrl}"  role="presentation">作者：<s:property value="arctle.getAuthor_name"/></a>
            <a href="${addfullow}" role="presentation" >关注 <span class="badge"><s:property value="arctle.getLikes_num"/></span></a>
            
            </div>
            </li>
            <li  class="list-group-item ">
            <div id="test-editormd-view2">
            <textarea id="append-test" style="display:none;"><s:property value="arctle.context"/>
           </textarea>       
            </div>
            </li>
            </ul>
        </div>
        <!-- <script src="js/zepto.min.js"></script>jn
		<script>		
			var jQuery = Zepto;  // 为了避免修改flowChart.js和sequence-diagram.js的源码，所以使用Zepto.js时想支持flowChart/sequenceDiagram就得加上这一句
		</script> -->
        <script src="user/js/jquery.min.js"></script>
        <script src="user/lib/marked.min.js"></script>
        <script src="user/lib/prettify.min.js"></script>
        
        <script src="user/lib/raphael.min.js"></script>
        <script src="user/lib/underscore.min.js"></script>
        <script src="user/lib/sequence-diagram.min.js"></script>
        <script src="user/lib/flowchart.min.js"></script>
        <script src="user/lib/jquery.flowchart.min.js"></script>

        <script src="user/editormd.js"></script>
        <script type="text/javascript">
            $(function() {
                var testEditormdView, testEditormdView2;
                
                $.get("test.md", function(markdown) {
                    
				    testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                        markdown        : markdown ,//+ "\r\n" + $("#append-test").text(),
                        //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                        htmlDecode      : "style,script,iframe",  // you can filter tags decode
                        //toc             : false,
                        tocm            : true,    // Using [TOCM]
                        tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
                        //gfm             : false,
                        //tocDropdown     : true,
                        // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                        emoji           : true,
                        taskList        : true,
                        tex             : true,  // 默认不解析
                        flowChart       : true,  // 默认不解析
                        sequenceDiagram : true,  // 默认不解析
                    });
                    
                    //console.log("返回一个 jQuery 实例 =>", testEditormdView);
                    
                    // 获取Markdown源码
                    //console.log(testEditormdView.getMarkdown());
                    
                    //alert(testEditormdView.getMarkdown());
                });
                    
                testEditormdView2 = editormd.markdownToHTML("test-editormd-view2", {
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true,  // 默认不解析
                });
            });
        </script>
 <!-- 评论区 -->
 <div class="text-center">
 <label><span class="icon-bar brand">评论区: 共有<s:property value="comments.size()"/>条</span>
</label>
</div>
<div class ="center-block">
<ul class="list-group">
<s:iterator var="cmt" value="%{arctle.getComments}" >
<li class="list-group-item">
<div class="text-center">
<s:property value="#cmt.getContext"/>
</div>
</li>
<li class="list-group-item">

<div class="text-right">
<a href="#"><s:property value="#cmt.getSend_user_name()"/>
&nbsp;在&nbsp;<s:property value="#cmt.getCreate_time()"/>&nbsp;评论了你
</a>
</div>
</li>
</s:iterator>
</ul>
</div>

<form class="form-group" action="add_Comment" method="post">
<div class="text-center">
<s:if test="#session.current_user==null">
<span class="icon-bar brand"> 发个评论：</span><br>
<div><input  name="send_user_name" class="form-control" type="email" placeholder="你的 Eamil"/>
</div>
</s:if>
<s:else>
<s:hidden name="send_user_id" value="%{#session.current_user.id}"></s:hidden>
<s:hidden name="send_user_name" value="%{#session.current_user.username}"/>
</s:else>
  <span class="icon-bar brand"> 评论内容：</span><br>
<s:textarea  name="context"  class="form-control" placeholder="你的  评论"></s:textarea>
</div>

<div class="text-center">
  <s:hidden name="arctle_id" value="%{arctle.id}"/>
  <s:hidden name="rev_user_id" value="%{arctle. author_id}"/>
  <s:hidden name="rev_user_name" value="%{arctle.author_name}"/>
  <input class="btn btn-primary" type="submit" value="发布"></input>
 <input class="btn btn-primary" type="reset" value="重置"></input>
 </div>      
</form>
      
        <s:debug></s:debug>
    </body>
</html>