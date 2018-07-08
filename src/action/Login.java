package action;
import java.util.Map;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.UserBean;

public class Login extends ActionSupport  implements ModelDriven<UserBean> {
   /**
	 * 
	 */
private static final long serialVersionUID = -1245930959524173847L;
private String message;
private UserBean user =  new UserBean();
	public Login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserBean getModel() {
		// TODO Auto-generated method stub
		return user;
	}
public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

public String execute() {
	ActionContext actionContext =ActionContext.getContext();
	Map<String, Object> session = actionContext.getSession();
	 String email=this.getModel().getEmail();
	 String password=this.getModel().getPassword();
      user =UserDao.login(email, password);//此处数据库中获取 user;
      if(user==null)
    	  System.out.println("user  为 空");
    /**
     * 模拟基本信息
     */
    /*
    _user.setUsername("xiao song");
    _user.setEmail("admin");
    _user.setCreate_time(new Date());
    _user.setPassword("123");
    _user.getPost().size();
   
    
  */
  
     /**
      * 模拟登陆 逻辑;
      */
   System.out.println(user.getEmail());
  if (user.getEmail()!= null&&user.getPassword()!=null)	  
  {   this.setMessage("hi! "+user.getUsername()+" 你已经着陆！");
      System.out.println("登录成功!");
	  session.put("current_user",user);
	  return "success";
	  
  }
  else {
	  this.setMessage("登录失败！");
	  return "fail";
	  
  }
  
}
public  String loginOut(){
	ActionContext actionContext =ActionContext.getContext();
	Map<String, Object> session = actionContext.getSession();
	
	UserBean _user=(UserBean) session.get("current_user");
     if(_user!=null)
     {
     session.remove("current_user");
     session.remove("post_list");
     System.out.println("退出成功");
     this.setMessage("退出成功");
	 return "success";
     }
     else
     {
    	 this.setMessage("退出失败");
    	 System.out.println("退出失败");
    	 return "fail";
     }
    	 
	
}

}
