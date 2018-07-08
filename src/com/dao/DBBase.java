package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
public class DBBase {
	private static LinkedList<Connection> connlist=new LinkedList<Connection>();

	public DBBase() {
		// TODO Auto-generated constructor stub
	}
    public synchronized static Connection getConnection() {
    	
    	Connection conn =null;
    	if(connlist.size()>0)
    	conn= connlist.remove(0);
    	else {
    		
    	try {
    		
    		Properties p=new Properties();
			p.load(DBBase.class.getResourceAsStream("base.properties"));
			String driver=p.getProperty("driver");
			String url=p.getProperty("url");
		    String user_name=p.getProperty("user_name");
		    String password=p.getProperty("password");
		    String db_name=p.getProperty("db_name");
		    Class.forName(driver);
		    for(int i=0;i<10;i++)
		    {
		    	
		    	Connection conn1=DriverManager.getConnection(url+db_name,user_name,password);
		    	connlist.add(conn1);
		    	
		    }
		    conn=connlist.remove(0);
		    
		   
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			System.out.println("创建连接失败");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println("创建连接失败1");
			e.printStackTrace();
		}
    		
    	}
    	return conn;
    }
    public static void close(ResultSet rs) {
    	try {
			if(rs!=null)
			{
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("结果集关闭失败！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
 
    	
    }
    /**
     *关闭预处理;
     * @param pst
     */
    public  static void close(PreparedStatement pst)
    {
      	
      	
      	
      	if(pst!=null)
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	
      }
    /**
     * 关闭连接
     * @param conn
     */
      public synchronized static void close(Connection conn)
      {
    	  if(conn!=null)
    		  connlist.add(conn);
    	  
    	  
      }
      /***
       * 
       * 关闭结果集  ,连接，预处理
       * @param rs
       * @param conn
       * @param ps
       */
      public static void close(ResultSet rs, Connection conn,PreparedStatement ps)
      {
    	  close(rs);
    	  close(ps);
    	  close(conn);
    	  
    	  
      }
      /**
       *单条语句的 插入 ，跟新，删除;
       * @param sql
       * @param params
       * @return
       */
      public static boolean updateByparams(String sql,Object params[])
      {
    	  boolean flage=false;
    	  Connection con=getConnection();
    	  PreparedStatement ps=null;
    	  try {
			ps=con.prepareStatement(sql);
			if(params!=null)
			{
				
				
			for(int i=0 ;i<params.length;i++)
				
			{
				ps.setObject(i+1, params[i]);
				
			}
			
			}
			int n=ps.executeUpdate();
			if(n>0)
				flage=true;
		    close(null,con ,ps);
		    return flage;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  
		return flage;
    	  
    	  
    	  
      }

      /***
       * 批处理插入，跟新，删除等操作
       * @param sql
       * @param params
       * @return
       */
      public static boolean BacthUpdateByparams(String sql,Object params[][]) {
    	  boolean flage=false;
    	 Connection conn=getConnection();
    	 PreparedStatement ps=null;
    	if(params!=null)
    	{
    		
    try {
		ps=conn.prepareStatement(sql);
		for(int i=0;i<params.length ;i++)
		{	   for(int j=0;j<params[i].length;j++)
	    	   {
	    		ps.setObject(j+1,params[i]);   
	    	
	    	   }
		
	       ps.addBatch();
		}
		ps.executeBatch();
		flage=true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       close(null,conn,ps);
       
    		
    	}
    	  
    	  return flage;
    	  
    	  
    	  
    	  
      }
      
      
      
      /**
       * 查询操作；
     * @throws SQLException 
       * 
       * 
       */
      
   public static List<Map<String,Object>> select(String sql,Object params[]){
	  Connection conn=getConnection();
	  PreparedStatement ps=null;
      ResultSet rs=null;
      ResultSetMetaData rsm = null;
	  List<Map<String,Object>>list=new LinkedList<Map<String ,Object>>();
	  try {
		ps=conn.prepareStatement(sql);
	
      if(params!=null) {
		 
			
			for (int i=0;i<params.length;i++)
			{
			ps.setObject(i+1, params[i]);
				
			}
		  }
		  
		
				rs=ps.executeQuery();
			
				rsm = rs.getMetaData();
		
			int count;
		
				count = rsm.getColumnCount();
			
			while(rs.next()) {
				
				Map<String ,Object>map=new HashMap<String,Object>();
				for(int i=1;i<=count;i++) {
					String label=rsm.getColumnLabel(i).toLowerCase();
					
					map.put(label,rs.getObject(label));
				}
				list.add(map);
			}
			close(rs,conn,ps);
			return list;
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return list;
			
	
		  
	  
	
	}
   /**
    * 获取 不重复的ID号；
    */
   public static String getStringID()
   {
	   String id=null;
	   Date date=new Date();
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmssSSSS");
	   id=sdf.format(date);
	   return id;
	   
	   
   }
   
   public static void main(String[] args) {
	   
	   
	String sql="select * from UserBean";
	System.out.println(select(sql, null));
	//添加的 语句
	String add_arctle_sql="insert into Arctle(title,header,context,author_id,pub_time,create_time,likes_num) "
			+ "values(?,?,?,?,?,?,? )";
	String add_post_sql="insert into post(arctle_id,author_id, is_pub,author_name,author_title ,author_header ,create_time,pub_time) "
	      + "values(?,?,?,?,?,?,?,? )";
	String add_comment_sql="insert into comment(context,ctype,arctle_id,comment_size,send_user_id,send_user_name,rev_user_id,rev_user_name,root_id,parent_id,create_time)"
			 + "values(?,?,?,?,?,?,?,?,?,?,?)";
	String add_fullow_sql="insert into Fullow(fullow_id,fullowers_id,fullow_name,fullowers_name,create_time,is_fullowed)"
			 + "values(?,?,?,?,?,?)";
	String add_Message_sql= "insert into Message (send_user_name,send_user_id,rec_id,rec_name,is_read ,context,create_time)"
			+ "values(?,?,?,?,?,?,?)";
	String add_userbean_sql="insert into userbean( username,password,email,face_path)"
			+  "values(?,?,?,?)";
     
			
			//Object ob[]=new Object[]{"hellw","hello","sssdsd",2,new Date(),new Date(),4};
        // System.out.println(updateByparams(add_post_sql, ob));
	Object add_post_ob[]= new Object[] {1,2,true ,"user1","tile1","header1",new Date(),new Date()};
	Object  add_Message_ob []= new Object[] {};
	Object add_comment_ob[]= new Object[] {"你好 ",0,1,2,1,"user1",1,"rec_name1",1,2,new Date()};
	Object add_fullow_ob[]=new Object[] {1,1,"fullow_name_1","fullowers_name2",new Date(),false};
	Object add_userBean_ob[]=new Object[] {"user1","123","email@qq.com",null};
	//System.out.println(updateByparams(add_userbean_sql, add_userBean_ob));
	//System.out.println(updateByparams(add_comment_sql, add_comment_ob));
	//System.out.println(updateByparams(add_fullow_sql, add_fullow_ob));
	//System.out.println(updateByparams(add_post_sql, add_post_ob));
    String  select_userbean_sql="select * from userbean where email=? and password = ?";
    String  select_Post_sql="select * from  post  where author_id=?";
    String  select_Post_sql1="select * from post where is_pub=true"; //查找所有的Post；
    String  select_Comment_sql1="select * from comment where rev_user_id=?";//通过接收者的id
    String  select_Comment_sql2="select * from comment where arctle_id=?";
    String  select_message_sql2="select * from message where rec_id=?";
    String  select_arctle_sql1="select * from arctle where author_id =?";//通过作者查看文章
    String  select_arctle_sql2="select * from arctle where  id=? ";  //通过 id查看文章
    Object  select_userbean_ob[]=new Object[] {"email@qq.com","123"};
    Object   select_arctle_ob[]=new Object[] {1};
    Object select_comment_ob[]=new Object[] {1};
    System.out.println(select(select_Post_sql1, null));
    //System.out.println(DBBase.select(select_Comment_sql1,select_comment_ob));
	//System.out.println(select(select_userbean_sql,select_userbean_ob));
	//System.out.println(select(select_arctle_sql2,select_arctle_ob));
	System.out.println(select(select_Post_sql,new Object[] {3}));
	String delete_message_sql="delete from message where id=?";
	String delete_arctile_sql="delete from arctle arctle where id=?";
    String delete_post_sql="delete form post where id=?";
    String delete_fullow_sql="delete_from fullow  where id =?";
    String delete_user_sql="delete from userBean where id =?";
    Object delete_user_ob[]= new Object[]{1};
    //System.out.println(updateByparams(delete_user_sql,delete_user_ob));
    String update_post_sql="update  Post  set is_pub=?,author_name=?,author_title=? ,author_header=? ,create_time=?,pub_time=? where  id=? ";
    String update_arctle_sql="update arctle set title=?,header=?,context=?,pub_time=?,create_time=?,likes_num=? where id=?";
    String update_fullow_sql="update fullow set is_fullowed=? where id=?"  ;
    String update_message_sql="update message set is_read=? where id=? ";
    String update_userbean_sql="update userbean set username=?,password=?,email=?,face_path=? where id=?";
    
	   
   }

}
