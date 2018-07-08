package user.action;

import java.util.Date;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.Message;
import model.UserBean;

public class DoMessage extends ActionSupport implements ModelDriven<Message> {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Message  like_message =new Message();
	private  ActionContext ac =ActionContext.getContext();
    private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DoMessage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getModel() {
		// TODO Auto-generated method stub
		return like_message;
	}
	
	public String show(){
	   
	   /**
	     * ģ�� ��Ϣ ģ��
	     */
	    UserBean _user=(UserBean)ac.getSession().get("current_user");
	    
	    //   if (!UserDao.findMessageByRec_id(_user.getId()).isEmpty())
	        {
	        	_user.getUser_message().clear();
	         _user.getUser_message().addAll( UserDao.findMessageByRec_id(_user.getId()));
	   
	        
	        }/* Message message= new Message();
	    message.setSend_user_name("xiao wang");
	    message.setContext("С�� ��ע���㣡");
	    message.setIs_read(false);
	    message.setSend_user_id(2);
	    message.setCreate_time(new Date());
	    Message message1= new Message();
	    message1.setSend_user_name("xiao li");
	    message1.setContext("С�� ��ע���㣡");
	    message1.setIs_read(false);
	    message1.setSend_user_id(2);
	    message1.setCreate_time(new Date());
	    _user.getUser_message().clear();
	    _user.getUser_message().add(message);
	    _user.getUser_message().add(message1);
	    */
	    
	   if(! _user.getUser_message().isEmpty())
	   {
		  this.setMessage("��ȡ��Ϣ�ɹ�");
		return "success";
	   }
	   else
	   {
		   this.setMessage("��ȡ��Ϣʧ��");
		   
		   return "fail";
	   }
		
		
	}
	public String  del(){
		    int id  = new Integer(ac.getParameters().get("id").toString());
		    System.out.println(id);
		    Boolean bool = UserDao.delMessage(id);
			System.out.println(bool);
		   if(bool)
		   {
			  this.setMessage("ɾ����Ϣ�ɹ�");
			return "success";
		   }
		   else
		   {
			   this.setMessage("ɾ����Ϣʧ��");
			   
			   return "fail";
		   }
	}
	
  public  String update() {
	  
	  
	  
	  Boolean bool =UserDao.UpdateMessage(this.getModel());
		
	   if(bool)
	   {
		  this.setMessage("��Ϣ����޸ĳɹ�");
		return "success";
	   }
	   else
	   {
		   this.setMessage("��Ϣʧ���ʧ��");
		   
		   return "fail";
	   }
	  
	  
  }
	

}
