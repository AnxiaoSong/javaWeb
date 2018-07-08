package interceptor;

import java.util.Map;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import model.UserBean;

public class LoginRequired extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoginRequired() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> session  = invocation.getInvocationContext().getSession();
		UserBean _user=(UserBean)session.get("current_user");
		if(null!=_user)
		  {this.setMessage("hello!" + _user.getUsername());
		   return invocation.invoke();
		  }
		else
			{
			this.setMessage("hi! body, Äã»¹Ã»ÓÐµÇÂ¼");
			invocation.getStack().set("message", this.getMessage());
			
			return "no_login";
	
			}
			}

}
