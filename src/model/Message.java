package model;
import java.util.Date;

public class Message {

   private int id;
   private String  send_user_name;//发送消息的名字
   private int   send_user_id;//可以为空
   private int  rec_id;
   private String   rec_name;//接收者的名字
   private  Boolean is_read=false;
   private  String  context;//消息的内容
   private   Date create_time;//消息产生的时间
   public int getId() {
	return id;
}

public int getRec_id() {
	return rec_id;
}

public void setRec_id(int rec_id) {
	this.rec_id = rec_id;
}





public String getRec_name() {
	return rec_name;
}

public void setRec_name(String rec_name) {
	this.rec_name = rec_name;
}

public void setSend_user_id(int send_user_id) {
	this.send_user_id = send_user_id;
}

public Date getCreate_time() {
	return create_time;
}

public void setCreate_time(java.util.Date date) {
	this.create_time = date;
}

public void setId(int id) {
	this.id = id;
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

public Boolean getIs_read() {
	return is_read;
}
public void setIs_read(Boolean is_read) {
	this.is_read = is_read;
}
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}

	public Message() {
		// TODO Auto-generated constructor stub
	}

}
