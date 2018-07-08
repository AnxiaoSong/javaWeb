package action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Post;
import model.UserBean;

public class DoPost extends ActionSupport{
    /**
	 * 
	 */
	private ActionContext ac =ActionContext.getContext();
	private static final long serialVersionUID = 1L;
	List<Post> post_list = new LinkedList<Post>();//查询全部的post
    List<Post> user_list = new LinkedList<Post>(); //用于查询修改;
    int author_id;//作者的ID，可以进行登录作者的文章的修改和查询;
	public DoPost() {
		// TODO Auto-generated constructor stub
	}
	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public List<Post> getPost_list() {
		return post_list;
	}

	public void setPost_list(List<Post> post_list) {
		this.post_list = post_list;
	}
	
	public String update() {
		
		ActionContext ac =ActionContext.getContext();
	    int id =new Integer(ac.getParameters().get("id").toString());
	    Post e= new Post();
	    e.setId(id);
	    e.setIs_pub(true);
	    e.setPub_time(new Date());
	    if(UserDao.UpdatePost(e))
	     	return "success";
	     return "fail";	
	
	}
	
    public String show() {
    	
   
          
    	/*
    	 *从数据库中进行查询所有的post
    	 */
    	    UserBean _user= (UserBean) ac.getSession().get("current_user");
    	    
    	    if(_user!=null)
    	    ac.getActionInvocation().getStack().set("message","hi ! "+ _user.getUsername());
    	    else
    	    	ac.getActionInvocation().getStack().set("message","hi ! boy");
    	  /*  Post post1= new Post();
    	    post1.setArctle_id(1);
    	    post1.setAuthor_name("xiao wang ");
    	    post1.setAuthor_id(1);
    	    post1.setAuthor_title("你好  世界！");
    	    post1.setAuthor_header("hello word!");
    	    post1.setPub_time(new Date());
    	    Post post2= new Post();
    	    post2.setAuthor_name("xiao gao");
    	    post2.setArctle_id(2);
    	    post1.setAuthor_id(2);
    	    post2.setAuthor_title("你好 ！");
    	    post2.setAuthor_header("hello!");
    	    post2.setPub_time(new Date());
    	    post_list.clear();
    	    post_list.add(post1);
    	    post_list.add(post2);
    	    */
    	    
    	    this.post_list.addAll(UserDao.findPost());
    	    ac.getSession().put("post_list", post_list);
    	    
        return  "success";
    } 
    

}
