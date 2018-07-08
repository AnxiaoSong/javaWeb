package user.action;

import java.util.Date;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.Comment;

public class DoComment extends ActionSupport implements ModelDriven<Comment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comment comment = new Comment ();
	private  ActionContext ac =ActionContext.getContext();
	private  int arctle_id;
	
	public int getArctle_id() {
		return arctle_id;
	}

	public void setArctle_id(int arctle_id) {
		this.arctle_id = arctle_id;
	}

	public DoComment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return comment;
	}
	
	
	public String add() {
		this.getModel().setCreate_time(new Date());
		
		//System.out.println(this.getModel().getSend_user_name());
		/*
		this.getModel().setCreate_time(new Date());
		this.getModel().setArctle_id();
		this.getModel().setParent_id(parent_id);
		this.getModel().setRoot_id(root_id);
		this.getModel().setSend_user_id(send_user_id);
		this.getModel().setRev_user_name(rev_user_name);
		this.getModel().setRev_user_id(id);
		*/
		String name= ac.getParameters().get("send_user_name").toString();
	     System.out.println(name);
		Boolean bool =UserDao.addComment(this.getModel()) ;
		
		System.out.println(bool);
	    if (bool)
	    {
	    	System.out.println(ac.getParameters());
		return  "success";
	    }
	    else 
	    {
	    	System.out.println(ac.getParameters());
	    	return  "fail";
	    }
		
		
	}
	
   public String del() {
	   
	   Boolean bool = false;
	    if (bool)
	    {
	    	ac.getActionInvocation().getStack().set("message","评论添加成功！");
	    	
		return  "success";
	    }
	    else 
	    {
	    	ac.getActionInvocation().getStack().set("message","评论添加成功！"); 
	    	return  "fail";
	    }
	  
	   
	   
   }

}