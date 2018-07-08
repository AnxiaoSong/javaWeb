package user.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import model.UserBean;
public class DoUserInfo extends ActionSupport implements ModelDriven<UserBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private UserBean user =new UserBean();
	private ActionContext actionContext = ActionContext.getContext();
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public DoUserInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserBean getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public String add() {
		 this.getModel().setCreate_time(new Date());
	     System.out.println(this.getModel().getEmail());
	     
		if (UserDao.addUser(this.getModel()))
		{
		this.setMessage("注册成功");
		return "success";
		}
		
		  else
		  {
		  this.setMessage("注册失败");
		  return  "fail";
		  }
		 
	}
   public String del() {
	   
	   
	   if(UserDao.delUser(this.getModel().getId()))
	   {
	     this.setMessage("注销成功");
		   return "success";
	   }
	  
	    
	    else 
	    {
	    this.setMessage("注销失败");
	    return "fail";
	    }
	   
   }
   public String update() {
	   
	   
	   Map<String, Parameter> pam=actionContext.getParameters();
	   UserBean _user= (UserBean) actionContext.getSession().get("current_user");
	   this.getUser().setId(_user.getId());
	   this.getUser().setEmail(pam.get("email").toString());
	   this.getUser().setPassword(pam.get("password").toString());
	   this.getUser().setUsername(pam.get("username").toString());
	   System.out.println(this.getUser().getPassword());
	   if(UserDao.UpdateUserBean(this.getUser()))
	   {   _user.setEmail(this.getUser().getEmail());
	       _user.setPassword(this.getUser().getPassword());
	       _user.setUsername(this.getUser().getUsername());
		   this.setMessage("修改成功");
		   return "success";
	   }
	     else
	     {
	     this.setMessage("修改失败");
	    return "fail";
	     }
   }

   
  public String showCurrentUserInfo() {
	  
	 //不做任何处理
	ActionContext actionContext =ActionContext.getContext();
	Map<String, Object> session = actionContext.getSession();
	  UserBean _user=(UserBean) session.get("current_user");
	 this.setMessage("hi!"+_user.getUsername());
	 
	 /**
	     * 模拟作者帖子列表
	     */
	 /*
	    Post post1= new Post();
	    post1.setId(0);
	    post1.setAuthor_id(2);
	    post1.setArctle_id(1);
	    post1.setAuthor_title("你好  世界！");
	    post1.setAuthor_header("hello word!");
	    Post post2= new Post();
	    post2.setId(1);
	    post2.setArctle_id(1);
	    post2.setAuthor_id(1);
	    post2.setAuthor_title("你好 ！");
	    post2.setAuthor_header("hello!");
	    _user.getPost().clear();
	    _user.getPost().add(post1);
	    _user.getPost().add(post2);
	  */
	 _user.getPost().clear();
	 _user.getPost().addAll(UserDao.findPostByAuthor_id(_user.getId()));
	 System.out.println(_user.getId()+"&&"+"&&"+_user.getPost());
	  return "success";
  }
  
  public String showUserInfo() {
	  /*
	    Post post1= new Post();
	    user.setEmail("5@q.com");
	    user.setUsername("li li");
	    post1.setId(0);
	    post1.setAuthor_id(2);
	    post1.setArctle_id(2);
	    post1.setAuthor_title("你好  世界！");
	    post1.setAuthor_header("hello word!");
	    Post post2= new Post();
	    post2.setId(1);
	    post2.setArctle_id(1);
	    post1.setAuthor_id(1);
	    post2.setAuthor_title("你好 ！");
	    post2.setAuthor_header("hello!");
	    user.getPost().clear();
	    user.getPost().add(post1);
	    user.getPost().add(post2);
	    System.out.println(user.getId());
	  */
	  ActionContext actionContext =ActionContext.getContext();
	  user=new UserBean();
	   Parameter  pm = actionContext.getParameters().get("id");
	   System.out.println( new Integer(pm.getValue()));
	  user=UserDao.findUserByID( new Integer(pm.getValue()));
	  System.out.println(user);
	  user.getPost().clear();
	  user.getPost().addAll(UserDao.findPostByAuthor_id(user.getId()));
	  System.out.println(user.getPost());
	  return "success";
  }


  
}
