package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserBean {
	private int id ;
	private String username;
	private String password;
	private String email;
	private String face_path;
	private List<Message> user_message=new LinkedList<Message>();//与他相关的消息 
    private List<Post> post =new LinkedList<Post>();//帖子
    private Date create_time;//注册的时间
    private List<Fullow> Fullowed = new LinkedList<Fullow>();// 你关注的人;
    private List<Fullow> Fullower= new  LinkedList<Fullow>(); // 关注你的人;
    
	public List<Fullow> getFullowed() {
		return Fullowed;
	}


	public void setFullowed(List<Fullow> fullowed) {
		Fullowed = fullowed;
	}


	public List<Fullow> getFullower() {
		return Fullower;
	}


	public void setFullower(List<Fullow> fullower) {
		Fullower = fullower;
	}


	public List<Message> getUser_message() {
		return user_message;
	}


	public void setUser_message(List<Message> user_message) {
		this.user_message = user_message;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

  
	public Date getCreate_time() {
		return create_time;
	}


	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFace_path() {
		return face_path;
	}


	public void setFace_path(String face_path) {
		this.face_path = face_path;
	}


	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", create_time=" + create_time + "]";
	}


}
