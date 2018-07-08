package model;

import java.util.Date;

public class Comment {
   private int  id;
   private String  context;
   private   Date create_time;
   private int type; // ���ͣ�0:�ظ���,1:������
   private int arctle_id;//����Id;˵�������µ�ID;
   private int  commet_size;//���ڸ����۵Ĵ�����
   private int  send_user_id;//�����ߵ�ID
   private String  send_user_name;//����������
   private int  rev_user_id; //�����ߵ�ID
   private String  rev_user_name;//�����ߵ�����
   private int    root_id; //���ڵ�
   private int    parent_id; //���ڵ�
   private Comment rev_comment ; //�ظ�������
   /**
    *  ҵ���߼�������һƪ���µ����۶���һ����ʼ������ ID Ϊroot_id
    *  ����һ�����ڵ� parent_id;
    *  comment_size = 1 ��Ҷ�ӽڵ㣻  
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
