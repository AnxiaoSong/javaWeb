package model;

import java.util.Date;

public class Post {

 
  private int id;
  private String type;//帖子所属板块
  private int  arctle_id;
  private int   author_id;
  @Override
public String toString() {
	return "Post [id=" + id + ", type=" + type + ", arctle_id=" + arctle_id + ", author_id=" + author_id + ", is_pub="
			+ is_pub + ", author_name=" + author_name + ", author_title=" + author_title + ", author_header="
			+ author_header + ", create_time=" + create_time + ", pub_time=" + pub_time + ", coumment_num="
			+ coumment_num + "]";
}
  private boolean is_pub;
  private  String   author_name;
  private String  author_title;//文章题目
  private String  author_header; //文章的简介
  private Date create_time;
  private Date pub_time;
  private int  coumment_num;
  
public int getCoumment_num() {
	return coumment_num;
}
public void setCoumment_num(int coumment_num) {
	this.coumment_num = coumment_num;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getArctle_id() {
	return arctle_id;
}
public void setArctle_id(int arctle_id) {
	this.arctle_id = arctle_id;
}
public int getAuthor_id() {
	return author_id;
}
public void setAuthor_id(int author_id) {
	this.author_id = author_id;
}
public boolean isIs_pub() {
	return is_pub;
}
public void setIs_pub(boolean is_pub) {
	this.is_pub = is_pub;
}

public String getAuthor_name() {
	return author_name;
}
public void setAuthor_name(String author_name) {
	this.author_name = author_name;
}
public String getAuthor_title() {
	return author_title;
}
public void setAuthor_title(String author_title) {
	this.author_title = author_title;
}

public String getAuthor_header() {
	return author_header;
}
public void setAuthor_header(String author_header) {
	this.author_header = author_header;
}
public Date getCreate_time() {
	return create_time;
}
public void setCreate_time(Date create_time) {
	this.create_time = create_time;
}
public Date getPub_time() {
	return pub_time;
}
public void setPub_time(Date pub_time) {
	this.pub_time = pub_time;
}



}
