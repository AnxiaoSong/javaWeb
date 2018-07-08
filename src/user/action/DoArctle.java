package user.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.Arctle;
import model.Post;
import model.UserBean;

public class DoArctle extends ActionSupport implements ModelDriven<Arctle> {
   /**
	 * 
	 */
private static final long serialVersionUID = 1L;
   private Arctle arctle  = new Arctle();
   private int arctle_id;
  private  ActionContext ac =ActionContext.getContext();
   
   public void set(Arctle arctle) {
	   
	   
	   this.arctle=arctle;
   }

	public DoArctle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Arctle getModel() {
		// TODO Auto-generated method stub
		return arctle;
	}  



	public int getArctle_id() {
		return arctle_id;
	}

	public void setArctle_id(int arctle_id) {
		this.arctle_id = arctle_id;
	}


    public String del() {
    	Map<String, Parameter> pam=ac.getParameters();
    	System.out.println(pam.get("ac_id").toString());
    	this.getModel().setId(new Integer(pam.get("ac_id").toString()));
    	System.out.println(this.getModel().getId());
    	
    	if(UserDao.delArctle(this.getModel().getId()))
    	  return "success";
    	else
		return "fail";
  
    }
    public String add() {
    	System.out.println(ac.getParameters().toString());
    	Map<String, Parameter> pam=ac.getParameters();
    	
    	UserBean _user=(UserBean)ac.getSession().get("current_user");
    	this.getModel().setHeader(pam.get("header").toString());
    	this.getModel().setTitle(pam.get("title").toString());
        this.getModel().setContext(ac.getParameters().get("editormd-html-code").toString());
        this.getModel().setAuthor_id(_user.getId());
        System.out.println(_user.getUsername());
        this.getModel().setAuthor_name(_user.getUsername());
        System.out.println(this.getModel().getAuthor_id());
        this.getModel().setCreate_time(new Date());
    	if (pam.get("submit").toString()=="发布")
    		this.getModel().setPub_time(new Date());
    	if (UserDao.addArctle(this.getModel()))
    	{
    		Post e =new Post();
    		List<Arctle> l = UserDao.findArctleByAuthor_id(_user.getId());
    		System.out.println(l);
            int  id = (int)l.get(l.size()-1).getId();
    		e.setArctle_id(id);
    		e.setAuthor_header(this.getModel().getHeader());
    		e.setAuthor_title(this.getModel().getTitle());
    		e.setCreate_time(new Date());
    		e.setAuthor_id(_user.getId());
    		e.setAuthor_name(_user.getUsername());
    		e.setIs_pub(false);
    		System.out.println(pam.get("submit").toString().equals("发布"));
    		if (pam.get("submit").toString().equals("发布"))
        		{this.getModel().setPub_time(new Date());
    		     e.setIs_pub(true);
    		     }
    		if(UserDao.addPost(e))
    		{
    			ac.getActionInvocation().getStack().set("message","保存成功！");
    		return "success";
    		}
    	
    	}
    	ac.getActionInvocation().getStack().set("message","保存失败！");
    	return "fail";    	
    }
	public String  update() {
		
	   if (UserDao.UpdateArctle(this.getModel()))
		return "success";
	   else 
		   return "fail";
		
	}
	  public String  writeArctle()
	  {
		 ac.getActionInvocation().getStack().set("message","hi ! 开始 你的创作吧！记得保存呦！"); 
		 return "success";
	
	  }

public String  showArctle()
  {
	// if(ActleDao.show(this.arctle.getAuthor_ID())
  ac.getActionInvocation().getStack().set("message","成功获取文章，为他（她）点个赞吧！");
	/*
  String context=
			"			return this;\r\n" + 
			"		}\r\n" + 
			"    };\r\n" + 
			"    \r\n" + 
			"    box.fn.init.prototype = box.fn;\r\n" + 
			"    \r\n" + 
			"    window.box =box;\r\n" + 
			"})();\r\n" + 
			"\r\n" + 
			"var testBox = box();\r\n" + 
			"testBox.add(\"jQuery\").remove(\"jQuery\");\r\n" + 
			"```\r\n" + 
			"\r\n" + 
			"#### HTML 代码 HTML codes\r\n" + 
			"\r\n" + 
			"```html\r\n" + 
			"<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"    <head>\r\n" + 
			"        <mate charest=\"utf-8\" />\r\n" + 
			"        <meta name=\"keywords\" content=\"Editor.md, Markdown, Editor\" />\r\n" + 
			"        <title>Hello world!</title>\r\n" + 
			"        <style type=\"text/css\">\r\n" + 
			"            body{font-size:14px;color:#444;font-family: \"Microsoft Yahei\", Tahoma, \"Hiragino Sans GB\", Arial;background:#fff;}\r\n" + 
			"            ul{list-style: none;}\r\n" + 
			"            img{border:none;vertical-align: middle;}\r\n" + 
			"        </style>\r\n" + 
			"    </head>\r\n" + 
			"    <body>\r\n" + 
			"        <h1 class=\"text-xxl\">Hello world!</h1>\r\n" + 
			"        <p class=\"text-green\">Plain text</p>\r\n" + 
			"    </body>\r\n" + 
			"</html>\r\n" + 
			
			
			"                    \r\n" + 
			"### 绘制表格 Tables\r\n" + 
			"\r\n" + 
			"| 项目        | 价格   |  数量  |\r\n" + 
			"| --------   | -----:  | :----:  |\r\n" + 
			"| 计算机      | $1600   |   5     |\r\n" + 
			"| 手机        |   $12   |   12   |\r\n" + 
			"| 管线        |    $1    |  234  |\r\n" + 
			"                    \r\n" + 
			"First Header  | Second Header\r\n" + 
			"------------- | -------------\r\n" + 
			"Content Cell  | Content Cell\r\n" + 
			"Content Cell  | Content Cell \r\n" + 
			"\r\n" + 
			"| First Header  | Second Header |\r\n" + 
			"| ------------- | ------------- |\r\n" + 
			"| Content Cell  | Content Cell  |\r\n" + 
			"| Content Cell  | Content Cell  |\r\n" + 
			"\r\n" + 
			"| Function name | Description                    |\r\n" + 
			"| ------------- | ------------------------------ |\r\n" + 
			"| `help()`      | Display the help window.       |\r\n" + 
			"| `destroy()`   | **Destroy your computer!**     |\r\n" + 
			"\r\n" + 
			"| Left-Aligned  | Center Aligned  | Right Aligned |\r\n" + 
			"| :------------ |:---------------:| -----:|\r\n" + 
			"| col 3 is      | some wordy text | $1600 |\r\n" + 
			"| col 2 is      | centered        |   $12 |\r\n" + 
			"| zebra stripes | are neat        |    $1 |\r\n" + 
			"\r\n" + 
			"| Item      | Value |\r\n" + 
			"| --------- | -----:|\r\n" + 
			"| Computer  | $1600 |\r\n" + 
			"| Phone     |   $12 |\r\n" + 
			"| Pipe      |    $1 |\r\n" + 
			"                \r\n" + 
			"----\r\n" + 
			"\r\n" + 
			"#### 特殊符号 HTML Entities Codes\r\n" + 
			"\r\n" + 
			"&copy; &  &uml; &trade; &iexcl; &pound;\r\n" + 
			"&amp; &lt; &gt; &yen; &euro; &reg; &plusmn; &para; &sect; &brvbar; &macr; &laquo; &middot; \r\n" + 
			"\r\n" + 
			"X&sup2; Y&sup3; &frac34; &frac14;  &times;  &divide;   &raquo;\r\n" + 
			"\r\n" + 
			"18&ordm;C  &quot;  &apos;\r\n" + 
			"\r\n" + 
			"[========]\r\n" + 
			"\r\n" + 
			"### Emoji表情 :smiley:\r\n" + 
			"\r\n" + 
			"> Blockquotes :star:\r\n" + 
			"\r\n" + 
			"#### GFM task lists & Emoji & fontAwesome icon emoji & editormd logo emoji :editormd-logo-5x:\r\n" + 
			"\r\n" + 
			"- [x] :smiley: @mentions, :smiley: #refs, [links](), **formatting**, and <del>tags</del> supported :editormd-logo:;\r\n" + 
			"- [x] list syntax required (any unordered or ordered list supported) :editormd-logo-3x:;\r\n" + 
			"- [x] [ ] :smiley: this is a complete item :smiley:;\r\n" + 
			"- [ ] []this is an incomplete item [test link](#) :fa-star: @pandao; \r\n" + 
			"- [ ] [ ]this is an incomplete item :fa-star: :fa-gear:;\r\n" + 
			"    - [ ] :smiley: this is an incomplete item [test link](#) :fa-star: :fa-gear:;\r\n" + 
			"    - [ ] :smiley: this is  :fa-star: :fa-gear: an incomplete item [test link](#);\r\n" + 
			" \r\n" + 
			"#### 反斜杠 Escape\r\n" + 
			"\r\n" + 
			"\\*literal asterisks\\*\r\n" + 
			"\r\n" + 
			"[========]\r\n" + 
			"            \r\n" + 
			"### 科学公式 TeX(KaTeX)\r\n" + 
			"\r\n" + 
			"$$E=mc^2$$\r\n" + 
			"\r\n" + 
			"行内的公式$$E=mc^2$$行内的公式，行内的$$E=mc^2$$公式。\r\n" + 
			"\r\n" + 
			"$$x > y$$\r\n" + 
			"\r\n" + 
			"$$\\(\\sqrt{3x-1}+(1+x)^2\\)$$\r\n" + 
			"                    \r\n" + 
			"$$\\sin(\\alpha)^{\\theta}=\\sum_{i=0}^{n}(x^i + \\cos(f))$$\r\n" + 
			"\r\n" + 
			"多行公式：\r\n" + 
			"\r\n" + 
			"```math\r\n" + 
			"\\displaystyle\r\n" + 
			"\\left( \\sum\\_{k=1}^n a\\_k b\\_k \\right)^2\r\n" + 
			"\\leq\r\n" + 
			"\\left( \\sum\\_{k=1}^n a\\_k^2 \\right)\r\n" + 
			"\\left( \\sum\\_{k=1}^n b\\_k^2 \\right)\r\n" + 
			"```\r\n" + 
			"\r\n" + 
			"```katex\r\n" + 
			"\\displaystyle \r\n" + 
			"    \\frac{1}{\r\n" + 
			"        \\Bigl(\\sqrt{\\phi \\sqrt{5}}-\\phi\\Bigr) e^{\r\n" + 
			"        \\frac25 \\pi}} = 1+\\frac{e^{-2\\pi}} {1+\\frac{e^{-4\\pi}} {\r\n" + 
			"        1+\\frac{e^{-6\\pi}}\r\n" + 
			"        {1+\\frac{e^{-8\\pi}}\r\n" + 
			"         {1+\\cdots} }\r\n" + 
			"        } \r\n" + 
			"    }\r\n" + 
			"```\r\n" + 
			"\r\n" + 
			"```latex\r\n" + 
			"f(x) = \\int_{-\\infty}^\\infty\r\n" + 
			"    \\hat f(\\xi)\\,e^{2 \\pi i \\xi x}\r\n" + 
			"    \\,d\\xi\r\n" + 
			"```\r\n" + 
			"\r\n" + 
			"### 分页符 Page break\r\n" + 
			"\r\n" + 
			"> Print Test: Ctrl + P\r\n" + 
			"\r\n" + 
			"[========]\r\n" + 
			"\r\n" + 
			"### 绘制流程图 Flowchart\r\n" + 
			"\r\n" + 
			"```flow\r\n" + 
			"st=>start: 用户登陆\r\n" + 
			"op=>operation: 登陆操作\r\n" + 
			"cond=>condition: 登陆成功 Yes or No?\r\n" + 
			"e=>end: 进入后台\r\n" + 
			"\r\n" + 
			"st->op->cond\r\n" + 
			"cond(yes)->e\r\n" + 
			"cond(no)->op\r\n" + 
			"```\r\n" + 
			"\r\n" + 
			"[========]\r\n" + 
			"                    \r\n" + 
			"### 绘制序列图 Sequence Diagram\r\n" + 
			"                    \r\n" + 
			"```seq\r\n" + 
			"Andrew->China: Says Hello \r\n" + 
			"Note right of China: China thinks\\nabout it \r\n" + 
			"China-->Andrew: How are you? \r\n" + 
			"Andrew->>China: I am good thanks!\r\n" + 
			"```\r\n" + 
			"\r\n" + 
			"### End";
*/
  Map<String, Parameter> pam=ac.getParameters();
	System.out.println(pam.get("arctle_id").toString());
	  this.setArctle(UserDao.findArctleByid(this.getArctle_id()));
	  System.out.println(this.getArctle());
	  System.out.println(UserDao.findArctleByid(this.getArctle_id()));
	 return "success";
	
  }

public Arctle getArctle() {
	return arctle;
}

public void setArctle(Arctle arctle) {
	this.arctle = arctle;
}

	

}
