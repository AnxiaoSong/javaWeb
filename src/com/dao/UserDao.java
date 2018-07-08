package com.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.Arctle;
import model.Comment;
import model.Fullow;
import model.Message;
import model.Post;
import model.UserBean;

public class UserDao {
	
	public static UserBean login(String email, String password) {
		UserBean _user = new UserBean();
		String select_userbean_sql = "select * from userbean where email=? and password = ?";

		Object select_userbean_ob[] = new Object[] { email, password };
		List<Map<String, Object>> list = DBBase.select(select_userbean_sql, select_userbean_ob);
		if (list!= null&&list.size()!=0) {
			_user.setId((int)list.get(list.size()-1).get("id"));
			_user.setEmail(email);
			_user.setPassword(password);
			//_user.setCreate_time((Date)list.get(list.size()-1).get("create_time"));
			_user.setUsername((String) list.get(list.size()-1).get("username"));
			_user.setFace_path((String) list.get(list.size()-1).get("face_path"));
		
	
			
			}

		return _user;
	};
	
	
	
	public static UserBean findUserByID(int id) {
		UserBean _user = new UserBean();
		String select_userbean_sql = "select * from userbean where id=?";
                     
		Object select_userbean_ob[] = new Object[] {id };
		List<Map<String, Object>> list = DBBase.select(select_userbean_sql, select_userbean_ob);
		if (list != null&&list.size()!=0) {
			_user.setId((int)list.get(list.size()-1).get("id"));
			_user.setEmail((String)list.get(list.size()-1).get("email"));
			//_user.setCreate_time((Date)list.get(list.size()-1).get("create_time"));
			_user.setUsername((String) list.get(list.size()-1).get("username"));
			_user.setFace_path((String) list.get(list.size()-1).get("face_path"));
		}
		
		return _user;
	};
	
	
	public static List<Fullow> findFullowByFullowed_id(int id){
		
	List<Fullow> flist =new LinkedList<Fullow>();
	String sql = "select * from fullow  where Fullow_id =?";
	for (Map<String , Object> mp : DBBase.select(sql, new Object[] {id})) {
		
		Fullow e = new Fullow ();
		e.setCreate_time((Date)mp.get("create_time"));
		e.setFullow_id((int)mp.get("fullow_id"));
		e.setId((int)mp.get("id"));
		e.setFullow_name((String)mp.get("fullow_name"));
		e.setFullowers_id((int)mp.get("fullowers_id"));
		e.setFullowers_name((String)mp.get("fullowers_name"));
		e.setIs_fullowed((boolean)mp.get("is_fullowed"));
		
		   flist.add(e);
	}
		return flist;
	
	}
	
	
	

	public static List<Fullow> findFullowByFullowers_id(int id){
		
	List<Fullow> flist =new LinkedList<Fullow>();
	String sql = "select * from fullow  where Fullowers_id =?";
	for (Map<String , Object> mp : DBBase.select(sql, new Object[] {id})) {
		
		Fullow e = new Fullow ();
		e.setCreate_time((Date)mp.get("create_time"));
		e.setFullow_id((int)mp.get("fullow_id"));
		e.setId((int)mp.get("id"));
		e.setFullow_name((String)mp.get("fullow_name"));
		e.setFullowers_id((int)mp.get("fullowers_id"));
		e.setFullowers_name((String)mp.get("fullowers_name"));
		e.setIs_fullowed((boolean)mp.get("is_fullowed"));
		
		   flist.add(e);
	}
		return flist;
	
	}
	
	public static List<Comment> findCommentByRev_id(int rev_user_id) {
		List<Comment> cmlist = new LinkedList<Comment>();
		String select_Comment_sql1 = "select * from comment where rev_user_id=?";// 通过接收者的id
		Object select_comment_ob[] = new Object[] { rev_user_id };
		List<Map<String, Object>> list = DBBase.select(select_Comment_sql1, select_comment_ob);
		if (list != null) {
			for (Map<String, Object> mp : list) {
				Comment cm = new Comment();
				cm.setId(Integer.parseInt(mp.get("id").toString()));
				cm.setRev_user_id(rev_user_id);
				cm.setSend_user_id(Integer.parseInt(mp.get("send_user_name").toString()));
				cm.setArctle_id(Integer.getInteger((String) mp.get("arctle_id")));
				cm.setCommet_size(Integer.parseInt((String) mp.get("")));
				cm.setContext((String) mp.get("context"));
				cm.setCreate_time((Date) mp.get("create_time"));
				cm.setRev_user_name((String) mp.get("rev_user_name"));
				cm.setSend_user_name((String) mp.get("send_user_name"));
				cm.setRoot_id(Integer.parseInt((String) mp.get("root_id")));
				cm.setParent_id(Integer.parseInt((String) mp.get("parent_id")));
				cmlist.add(cm);
			}

		}

		return cmlist;

	}

	public static List<Comment> findCommentByArctle_id(int arctle_id) {

		List<Comment> cmlist = new LinkedList<Comment>();
		String select_Comment_sql1 = "select * from comment where rev_user_id=?";// 通过接收者的id
		Object select_comment_ob[] = new Object[] { arctle_id };
		List<Map<String, Object>> list = DBBase.select(select_Comment_sql1, select_comment_ob);
		if (list != null) {
			for (Map<String, Object> mp : list) {
				Comment cm = new Comment();
				cm.setId(Integer.parseInt(mp.get("id").toString()));
				cm.setRev_user_id((int)mp.get("rev_user_id"));
				cm.setSend_user_id((int)mp.get("send_user_id"));
				cm.setArctle_id( (int)mp.get("arctle_id"));
				cm.setCommet_size((int) mp.get("comment_size"));
				cm.setContext((String) mp.get("context"));
				cm.setCreate_time((Date) mp.get("create_time"));
				cm.setRev_user_name((String) mp.get("rev_user_name"));
				cm.setSend_user_name((String) mp.get("send_user_name"));
				cm.setRoot_id((int)mp.get("root_id"));
				cm.setParent_id((int) mp.get("parent_id"));
				cmlist.add(cm);
			}

		}

		return cmlist;

	}
	public static List<Message>findMessageByRec_id(int rec_id) {
		LinkedList<Message> mslis= new LinkedList<Message>();
	    
		String  select_Comment_sql2="select * from message where rec_id=?";
		
		Object select_comment_ob[]=new Object[] {rec_id};
		
		for (Map<String , Object> mp : DBBase.select(select_Comment_sql2, select_comment_ob))
		{
		     Message e= new Message();
		     String context= (String) mp.get("context");
		     e.setContext(context);
		     Date  date = (Date)mp.get("create_time");
		     e.setCreate_time(date);
		     int id = (int)mp.get("id");
		     e.setId(id);
		     e.setRec_id(rec_id);
		     e.setRec_name((String)mp.get("rec_name"));
		     boolean is_read = (boolean)mp.get("is_read");
		     e.setIs_read(is_read);
		     int send_user_id= (int)mp.get("send_user_id");
		     e.setSend_user_id(send_user_id);
		     String send_user_name=(String)mp.get("send_user_name");
		     e.setSend_user_name(send_user_name);
			mslis.add(e);
		}
		 
			return mslis;
			
	}
	
	public static Arctle findArctleByid(int id){
     List<Arctle> list = new LinkedList<Arctle>();
     String sql= "select * from arctle where  id=? ";
     for (Map<String,Object> mp : DBBase.select(sql,new Object[] {id}))
     {
        Arctle e= new Arctle();
        e.setId((int)mp.get("id"));
        int author_id= (int) mp.get("author_id");
        e.setAuthor_id(author_id);
        String author_name = (String)mp.get("author_name");
        e.setAuthor_name(author_name);
        String context=(String)mp.get("context");
        e.setContext(context);
       String header = (String) mp.get("header");
        e.setHeader(header);
        String  title = (String)mp.get("title");
        e.setTitle(title);
        Date create_time = (Date)mp.get("create_time");
        e.setCreate_time(create_time);
        Date pub_time =(Date)mp.get("pub_time");
        e.setPub_time(pub_time);
        e.setLikes_num((int)mp.get("likes_num"));
        e.setComments(findCommentByArctle_id(id));
    	list.add(e); 
    	return e;
     }
    	
   
  
     
     
     return null;

}
	
	
	
	public static List<Arctle> findArctleByAuthor_id(int id){
	     List<Arctle> list = new LinkedList<Arctle>();
	     String sql= "select * from arctle where  author_id=? ";
	     for (Map<String,Object> mp : DBBase.select(sql,new Object[] {id}))
	     {
	        Arctle e= new Arctle();
	        e.setId((int)mp.get("id"));
	        int author_id= (int) mp.get("author_id");
	        e.setAuthor_id(author_id);
	        String author_name = (String)mp.get("author_name");
	        e.setAuthor_name(author_name);
	        String context=(String)mp.get("context");
	        e.setContext(context);
	       String header = (String) mp.get("header");
	        e.setHeader(header);
	        String  title = (String)mp.get("title");
	        e.setTitle(title);
	        Date create_time = (Date)mp.get("create_time");
	        e.setCreate_time(create_time);
	        Date pub_time =(Date)mp.get("pub_time");
	        e.setPub_time(pub_time);
	        e.setLikes_num((int)mp.get("likes_num"));
	        e.setComments(findCommentByArctle_id((int)mp.get("id")));
	    	 list.add(e); 
	     }
	    	
	     
	     return list;

	}
  public static List<Post>  findPostByAuthor_id(int id) {
	  
	 List<Post> pslist=new  LinkedList<Post>();
      String sql ="select * from  post  where author_id=?";
	 
	 for(Map<String,Object> mp:DBBase.select(sql,new Object[] {id})) {
		 Post e = new Post();
		 int id1 = (int) mp.get("id");
		 e.setId(id1);
		 int arctle_id =(int) mp.get("arctle_id");
		 e.setArctle_id(arctle_id);
		 String author_header=(String )mp.get("author_header");
		 e.setAuthor_header(author_header);
		 String author_title=(String) mp.get("author_title");
         e.setAuthor_title(author_title);
         String author_name =(String) mp.get("author_name");
         e.setAuthor_name(author_name);
         Date  create_time = (Date)mp.get("create_time");
         e.setCreate_time(create_time);
         Date  pub_time =(Date) mp.get("pub_time");
         e.setPub_time(pub_time);
         boolean is_pub = (boolean)mp.get("is_pub");
         e.setIs_pub(is_pub);
         pslist.add(e);
	  }
	  return pslist;
	  
	  
  }
  public static List<Post>  findPost() {
	  
		 List<Post> pslist=new  LinkedList<Post>();
	      String sql ="select * from post where is_pub=true";
		 
		 for(Map<String,Object> mp:DBBase.select(sql,null)) {
			 Post e = new Post();
			 int id1 = (int) mp.get("id");
			 e.setId(id1);
			 e.setAuthor_id((int)mp.get("author_id"));
			 int arctle_id =(int) mp.get("arctle_id");
			 e.setArctle_id(arctle_id);
			 String author_header=(String )mp.get("author_header");
			 e.setAuthor_header(author_header);
			 String author_title=(String) mp.get("author_title");
	         e.setAuthor_title(author_title);
	         String author_name =(String) mp.get("author_name");
	         e.setAuthor_name(author_name);
	         Date  create_time = (Date)mp.get("create_time");
	         e.setCreate_time(create_time);
	         Date  pub_time =(Date) mp.get("pub_time");
	         e.setPub_time(pub_time);
	         boolean is_pub = (boolean)mp.get("is_pub");
	         e.setIs_pub(is_pub);
	        pslist.add(e);
			  
		  }
		  return pslist;
		  
		  
	  }
		
  public static boolean addArctle(Arctle e)
  {
	  String add_arctle_sql="insert into"
	  		+ " Arctle(title,header,context,author_id,author_name,pub_time,create_time,likes_num) "
				+ "values(?,?,?,?,?,?,?,? )";
	return DBBase.updateByparams(add_arctle_sql
			, 
			new Object [] {e.getTitle(),e.getHeader(),e.getContext(),e.getAuthor_id(),e.getAuthor_name(),e.getPub_time(),e.getCreate_time(),e.getLikes_num()}
			
			);		
	  
  };
  public static  boolean addPost(Post e) {
	  String add_post_sql="insert into post(arctle_id,author_id, is_pub,author_name,author_title ,author_header ,create_time,pub_time) "
		      + "values(?,?,?,?,?,?,?,? )";
	  Object[] params = new Object [] {
			  
			  e.getArctle_id(),
			  e.getAuthor_id(),
			  e.isIs_pub(),
			  e.getAuthor_name(),
			  e.getAuthor_title(),
			  e.getAuthor_header(),
			  e.getCreate_time(),
			  e.getPub_time()
			  
		
	  };
	return DBBase.updateByparams(add_post_sql, params );
	 
	  
  }
  
 public static boolean addComment(Comment e) {
	
	 String add_comment_sql="insert into comment(context,ctype,arctle_id,comment_size,send_user_id,send_user_name,rev_user_id,rev_user_name,root_id,parent_id,create_time)"
			 + "values(?,?,?,?,?,?,?,?,?,?,?)";
	 
	 Object[] params = new Object[]{
			 
	   e.getContext(),
	   e.getType(),
	   e.getArctle_id(),
	   e.getCommet_size(),
	   e.getSend_user_id(),
	   e.getSend_user_name(),
	   e.getRev_user_id(),
	   e.getRev_user_name(),
	   e.getRoot_id(),
	   e.getParent_id(),
	   e.getCreate_time()
	 };
	return DBBase.updateByparams(add_comment_sql, params );
	 
 }
 
 
 public static boolean addMessage(Message e) {
	 String add_Message_sql= "insert into Message (send_user_name,send_user_id,rec_id,rec_name,is_read ,context,create_time)"
				+ "values(?,?,?,?,?,?,?)";
	 
	 
	 
	 
	 
	 
	 Object[] params =new Object[] {e.getSend_user_name(),e.getSend_user_id(),e.getRec_id(),e.getRec_name(),e.getIs_read(),e.getContext(),e.getCreate_time()};
	return DBBase.updateByparams(add_Message_sql, params );
	 
 }
 
 public static boolean addFullow(Fullow e)
 {
	 
	 String add_fullow_sql="insert into Fullow(fullow_id,fullowers_id,fullow_name,fullowers_name,create_time,is_fullowed)"
	 		+ "values(?,?,?,?,?,?)";
	 
	 Object[] params = new Object[] {
			e.getFullow_id(),
			e.getFullowers_id(),
			e.getFullow_name(),
			e.getFullowers_name(),
			e.getCreate_time() ,
			e.isIs_fullowed()
			 
	 };
	return DBBase.updateByparams(add_fullow_sql, params );
 }
 public  static boolean addUser(UserBean e) {
	 String add_userbean_sql="insert into userbean( username,password,email,face_path)"
				+  "values(?,?,?,?)";
	 Object[] params = new Object []
			 {
					 e.getUsername(),
					 e.getPassword(),
					 e.getEmail(),
					 e.getFace_path()
			 }
	 ;
	 return DBBase.updateByparams(add_userbean_sql, params);
	 
	 
	 
 } 

 
 public static boolean  delUser(int id){
	 String delete_user_sql="delete from userBean where id =?";
	 
	 return DBBase.updateByparams(delete_user_sql, new Object[] {id});
	  
	 
 }
 public static boolean  delMessage(int id){
	 String delete_message_sql="delete from message where id=?";
	 
	 return DBBase.updateByparams(delete_message_sql, new Object[] {id});
	  
	 
 }
 public static boolean delArctle(int id) {
	 
	 String delete_arctile_sql="delete from arctle arctle where id=?";
	 
	 String del_comment_sql= "delete from  comment where arctle_id=? ";
	 String del_post_sql="delete from post where arctle_id=? ";
	 return DBBase.updateByparams(delete_arctile_sql, new Object[] {id})&&DBBase.updateByparams(del_post_sql, new Object[] {id})&&DBBase.updateByparams(del_comment_sql, new Object[] {id});
 }
 
 
 
 public static boolean delPost(int id) {
	 String delete_post_sql="delete form post where id=?";
	 return DBBase.updateByparams(delete_post_sql, new Object[] {id});
	  
	 
 }
public static boolean delFullow(int id) {
	String delete_fullow_sql="delete from fullow  where id =?";
	 return DBBase.updateByparams(delete_fullow_sql, new Object[] {id});
	
	
} 

public static boolean UpdatePost(Post e) {
	 String update_post_sql="update  Post  set is_pub=?,pub_time=? where  id=? ";
	    Object[] params =new Object[] {e.isIs_pub(),e.getPub_time() ,e.getId()};
	
	return DBBase.updateByparams(update_post_sql, params);}

public static boolean UpdateArctle(Arctle e) {

	    String update_arctle_sql="update arctle set title=?,header=?,context=?,pub_time=?,create_time=?,likes_num=? where id=?";
	    
	    Object[] params =new Object[] {e.getTitle(),e.getHeader(),e.getContext(),e.getPub_time(),e.getCreate_time(),e.getLikes_num(),e.getId()};
	return DBBase.updateByparams(update_arctle_sql, params);}
public static boolean UpdateFullow(Fullow e) {
	    String update_fullow_sql="update fullow set is_fullowed=? where id=?";
	   
	    Object[] params =new Object[] {e.isIs_fullowed(),e.getId()};
	return DBBase.updateByparams(update_fullow_sql, params);
	
	
}
public static boolean UpdateMessage(Message e)
{
	    
	    String update_message_sql="update message set is_read=? where id=? ";
	    Object[] params =new Object[] {e.getIs_read(),e.getId()};	
	    return DBBase.updateByparams(update_message_sql, params);}
public static boolean  UpdateUserBean(UserBean e)
{
	    String update_userbean_sql="update userbean set username=?,password=?,email=?,face_path=? where id=?";
	    Object[] params =new Object[] {e.getUsername(),e.getPassword(),e.getEmail(),e.getFace_path(),e.getId()};
		return DBBase.updateByparams(update_userbean_sql, params );
	
}
public static boolean UpdateArctleLikeNums(int num,int id){
	String update_like_sql="update arctle set likes_num =? where id=?";
	Object[] params =new Object[] {num,id};
	return DBBase.updateByparams(update_like_sql, params);
	
	
}
public static void main(String[] args) {
	
	System.out.println(login("email@qq.com","123"));
	
	
	
	
}

 
}
