package model;

import java.util.Date;

public class Fullow {
	private  int id;
	private  int fullow_id;
    private  int fullowers_id;
    private  String  fullow_name;
    private  String  fullowers_name;
    private  Date   create_time;
    private   boolean is_fullowed ;
    /*
     * 
     * 关系表类
     * 
     */
    public Fullow() {
		// TODO Auto-generated constructor stub
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFullow_id() {
		return fullow_id;
	}
	public void setFullow_id(int fullow_id) {
		this.fullow_id = fullow_id;
	}
	public int getFullowers_id() {
		return fullowers_id;
	}
	public void setFullowers_id(int fullowers_id) {
		this.fullowers_id = fullowers_id;
	}
	public String getFullow_name() {
		return fullow_name;
	}
	public void setFullow_name(String fullow_name) {
		this.fullow_name = fullow_name;
	}
	public String getFullowers_name() {
		return fullowers_name;
	}
	public void setFullowers_name(String fullowers_name) {
		this.fullowers_name = fullowers_name;
	}
	public boolean isIs_fullowed() {
		return is_fullowed;
	}
	public void setIs_fullowed(boolean is_fullowed) {
		this.is_fullowed = is_fullowed;
	}

}
