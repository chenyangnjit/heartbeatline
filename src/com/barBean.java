package com;
import java.io.Serializable;
//import java.sql.Time;
public class barBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9017870797282738011L;
	//private Time  time;
    private int data;
    private int id;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
	
}
