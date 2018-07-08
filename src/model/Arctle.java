package model;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Arctle {
	private  int   id;//���µ�ID
	private String title; //���µ���Ŀ
	private String header ; //���ݼ��
	private String type;  //������������
	private String context; //���µ�����
	private int author_id;//���ߵ�ID
	private String author_name;//���ߵ�����
	private Date pub_time;//����ʱ��
	private Date create_time;//���ߵĴ���ʱ�䣻
	private int likes_num;
	public int getLikes_num() {
		return likes_num;
	}




	public void setLikes_num(int likes_num) {
		this.likes_num = likes_num;
	}



	private List<Comment> comments=new LinkedList<Comment>();//�������µ�����
	public List<Comment> getComments() {
		return comments;
	}




	public Date getPub_time() {
		return pub_time;
	}




	public void setPub_time(Date pub_time) {
		this.pub_time = pub_time;
	}




	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}




	public void setCreate_time(Time create_time) {
		this.create_time = create_time;
	}


	public String getHeader() {
		return header;
	}




	public void setHeader(String header) {
		this.header = header;
	}


	public Arctle() {
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getContext() {
		return context;
	}



	public void setContext(String context) {
		this.context = context;
	}







	public int getAuthor_id() {
		return author_id;
	}




	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}




	public String getAuthor_name() {
		return author_name;
	}



	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}



	public Date getCreate_time() {
		return create_time;
	}



	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}




	@Override
	public String toString() {
		return "Arctle [id=" + id + ", title=" + title + ", header=" + header + ", type=" + type + ", context="
				+ context + ", author_id=" + author_id + ", author_name=" + author_name + ", pub_time=" + pub_time
				+ ", create_time=" + create_time + ", likes_num=" + likes_num + "]";
	}
	

}
