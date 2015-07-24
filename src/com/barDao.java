package com;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class barDao {
	
	Connection connection;
	public Connection getConnection(){
		try{
			String name="root";
			String password="123456";
			String url="jdbc:mysql://10.10.22.89:3306/zongyuxuan";
			Class.forName("com.mysql.jdbc.Driver");
			connection=(Connection) DriverManager.getConnection(url,name,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	
	public void setConnection(Connection connection){
		this.connection=connection;
	}
	/**
	 * 你按照相序排列一下，用这个函数 LIMIT 0,1 ; 取第一条记录
     * SELECT * FROM 表名 ORDER BY id DESC LIMIT 0,1 ;
     */ 
	public List<barBean> listAll() {
		ArrayList<barBean> list=new ArrayList<barBean>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=(PreparedStatement) this.getConnection().prepareCall("SELECT data,id FROM zong_data ORDER by id desc limit 1"); 
			rs=pstmt.executeQuery();
			while(rs.next()){
				barBean bar=new barBean();
				bar.setId(rs.getInt("id"));
				bar.setData(rs.getInt("data"));
				//bar.setTime(rs.getTime("time"));
				list.add(bar);
				//System.out.println(list.toArray());
				//JSONArray json=JSONArray.fromObject(list);
		        //System.out.println(json.toString());
				/**
		         * 把Map转换成json， 要使用jsonObject对象：
		        Map<String,Object>map=new HashMap<String,Object>();
		        map.put("userId", 10001);
		        map.put("userName", "zzz");
		        map.put("userSex", "男");
		        JSONObject jsonObject=JSONObject.fromObject(map);
		        System.out.println(jsonObject);*/
		        /**
		         * 把List转换成JSON数据
		         
		        List<barBean> data=new ArrayList<barBean>();
		        barBean user=new barBean("aaa",100);
		        list.add(user);
		        JSONArray jsonArray=JSONArray.fromObject(list);
		        System.out.println(jsonArray);
		        */
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	/**public static void main(String[] args) throws SQLException{
		new barDao().listAll();
	}*/
	
}








