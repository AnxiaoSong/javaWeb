package model;

import java.util.Date;

public class Comment {
   private int  id;
   private String  context;
   private   Date create_time;
   private int type; // 类型：0:回复型,1:评论型
   private int arctle_id;//文章Id;说评论文章的ID;
   private int  commet_size;//关于该评论的次数；
   private int  send_user_id;//分送者的ID
   private String  send_user_name;//评论者名字
   private int  rev_user_id; //接收者的ID
   private String  rev_user_name;//接收者的名字
   private int    root_id; //根节点
   private int    parent_id; //父节点
   private Comment rev_comment ; //回复的评论
   /**
    *  业务逻辑：对于一篇文章的评论都有一个初始的评论 ID 为root_id
    *  都有一个父节点 parent_id;
    *  comment_size = 1 是叶子节点；  
    */
   public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getArctle_id() {
	return arctle_id;
}
public void setArctle_id(int arctle_id) {
	this.arctle_id = arctle_id;
}
public int getCommet_size() {
	return commet_size;
}
public void setCommet_size(int commet_size) {
	this.commet_size = commet_size;
}
public int getRoot_id() {
	return root_id;
}
public void setRoot_id(int root_id) {
	this.root_id = root_id;
}
public int getParent_id() {
	return parent_id;
}
public void setParent_id(int parent_id) {
	this.parent_id = parent_id;
}
public Comment getRev_comment() {
	return rev_comment;
}
public void setRev_comment(Comment rev_comment) {
	this.rev_comment = rev_comment;
}
public void setSend_user_id(int send_user_id) {
	this.send_user_id = send_user_id;
}
public void setRev_user_id(int rev_user_id) {
	this.rev_user_id = rev_user_id;
}

	public Comment() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
	public String getSend_user_name() {
		return send_user_name;
	}
	public void setSend_user_name(String send_user_name) {
		this.send_user_name = send_user_name;
	}
	public int getSend_user_id() {
		return send_user_id;
	}
	public int getRev_user_id() {
		return rev_user_id;
	}
	public String getRev_user_name() {
		return rev_user_name;
	}
	public void setRev_user_name(String rev_user_name) {
		this.rev_user_name = rev_user_name;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", context=" + context + ", create_time=" + create_time + ", type=" + type
				+ ", arctle_id=" + arctle_id + ", commet_size=" + commet_size + ", send_user_id=" + send_user_id
				+ ", send_user_name=" + send_user_name + ", rev_user_id=" + rev_user_id + ", rev_user_name="
				+ rev_user_name + ", root_id=" + root_id + ", parent_id=" + parent_id + "]";
	}

}
