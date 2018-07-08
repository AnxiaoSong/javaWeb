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
	List<Post> post_list = new LinkedList<Post>();//��ѯȫ����post
    List<Post> user_list = new LinkedList<Post>(); //���ڲ�ѯ�޸�;
    int author_id;//���ߵ�ID�����Խ��е�¼���ߵ����µ��޸ĺͲ�ѯ;
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
    	 *�����ݿ��н��в�ѯ���е�post
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
    	    post1.setAuthor_title("���  ���磡");
    	    post1.setAuthor_header("hello word!");
    	    post1.setPub_time(new Date());
    	    Post post2= new Post();
    	    post2.setAuthor_name("xiao gao");
    	    post2.setArctle_id(2);
    	    post1.setAuthor_id(2);
    	    post2.setAuthor_title("��� ��");
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
