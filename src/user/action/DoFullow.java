package user.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.Fullow;
import model.Message;
import model.UserBean;

public class DoFullow extends ActionSupport implements ModelDriven<Fullow> {
    /**
	 * 关注的增删改 工作;
	 */
	private static final long serialVersionUID = 1L;
	private Fullow  fullow = new Fullow();
	private  ActionContext ac =ActionContext.getContext();
	public DoFullow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fullow getModel() {
		// TODO Auto-generated method stub
		return fullow;
	}
    public  String  del() {
    	
    int id = new Integer(ac.getParameters().get("id").toString());
       boolean bool=UserDao.delFullow(id);
       if(bool){
    	   
    	   
    	   ac.getValueStack().set("message","取消关注！");
    	   return  "success";
    	   
       }
       else {
    	   
    	   
    	   ac.getValueStack().set("message", "删除失败");;
    	   
    	   return   "fail";
    	   
       }
    }
    public String update () {
    	
    boolean bool=UserDao.UpdateFullow(getModel());
    	if(bool) {
    		ac.getValueStack().set("message","屏蔽关注成功");;
    		return  "success";
    		
    	}
    	else
    	{
    		ac.getValueStack().set("message", "屏蔽关注失败！");
    		return "fail";
    		
    	}
    }
    
    public String add() {
    	UserBean _user =(UserBean)ac.getSession().get("current_user");
    	int id2 = new Integer(ac.getParameters().get("arctle_id").toString());
    	String name =ac.getParameters().get("rec_user_name").toString();
    	getModel().setFullow_name(name);
    	int id =new Integer(ac.getParameters().get("fullow_id").toString());
        getModel().setFullowers_id(_user.getId());
        getModel().setFullowers_name(_user.getUsername());
        getModel().setIs_fullowed(false);
        getModel().setFullow_id(id);
    	getModel().setCreate_time(new Date());
    boolean bool=UserDao.addFullow(getModel());
    	if(bool) {
    		Message e = new Message();
    		e.setRec_id(getModel().getFullow_id());
    		e.setIs_read(false);
    		e.setRec_name(getModel().getFullow_name());
    	    e.setSend_user_id(_user.getId());
    	    e.setSend_user_name(_user.getUsername());
    	    e.setCreate_time(new Date());
    		e.setContext( getModel().getFullowers_name()+"关注了你");
    		UserDao.addMessage(e);
    		int  num= new Integer(ac.getParameters().get("likes_num").toString());
    		UserDao.UpdateArctleLikeNums(num+1, id2);
    		ac.getValueStack().set("message","添加关注成功");;
    		return  "success";
    		
    	}
    	else
    	{   
    		ac.getValueStack().set("message", "添加关注失败！");
    		return "fail";
    		
    	}
    }
    
    
  public  String show(){
	  
	  UserBean _user= (UserBean) ac.getSession().get("current_user");
	  /**
	    * 模拟 关注 列表  
	    */
	  /**
	    Fullow fd1= new Fullow();
	    Fullow fd2 = new Fullow();
	    fd1.setId(0);
	    fd1.setFullow_id(1);
	    fd1.setFullow_name("xiao Wang");
	    fd1.setCreate_time(new Date());
	    fd2.setId(1);
	    fd2.setFullow_id(2);
	    fd2.setFullow_name("xiao Li");
	    fd2.setIs_fullowed(false);
	    fd2.setCreate_time(new Date());
	    List<Fullow>  fd= new LinkedList<Fullow>();
	    fd.add(fd1);
	    fd.add(fd2);
	    _user.getFullowed().clear();
	    _user.getFullowed().addAll(fd);
	    Fullow fe1 = new Fullow();
	    Fullow fe2 = new Fullow();
	     fe1.setCreate_time(new Date());
	     fe1.setId(1);
	     fe1.setFullowers_name("xiao gao");
	     fe2.setFullowers_id(2);
	     fe2.setCreate_time(new Date());
	     fe2.setId(1);
	     fe2.setFullowers_name("xiao song");
	     fe2.setFullowers_id(2);
	     _user.getFullower().clear();
	     _user.getFullower().add(fe2);
	     _user.getFullower().add(fe1);
	     */
	 _user.getFullowed().clear();
	 _user.getFullowed().addAll(UserDao.findFullowByFullowers_id(_user.getId()));
	 _user.getFullower().clear();
	 _user.getFullower().addAll(UserDao.findFullowByFullowed_id(_user.getId()));
	 ac.getActionInvocation().getStack().set("message","hi!  "+_user.getUsername());
	 return "success"; 
	  
  }

}
