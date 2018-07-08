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
      user =UserDao.login(email, password);//�˴����ݿ��л�ȡ user;
      if(user==null)
    	  System.out.println("user  Ϊ ��");
    /**
     * ģ�������Ϣ
     */
    /*
    _user.setUsername("xiao song");
    _user.setEmail("admin");
    _user.setCreate_time(new Date());
    _user.setPassword("123");
    _user.getPost().size();
   
    
  */
  
     /**
      * ģ���½ �߼�;
      */
   System.out.println(user.getEmail());
  if (user.getEmail()!= null&&user.getPassword()!=null)	  
  {   this.setMessage("hi! "+user.getUsername()+" ���Ѿ���½��");
      System.out.println("��¼�ɹ�!");
	  session.put("current_user",user);
	  return "success";
	  
  }
  else {
	  this.setMessage("��¼ʧ�ܣ�");
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
     System.out.println("�˳��ɹ�");
     this.setMessage("�˳��ɹ�");
	 return "success";
     }
     else
     {
    	 this.setMessage("�˳�ʧ��");
    	 System.out.println("�˳�ʧ��");
    	 return "fail";
     }
    	 
	
}

}
