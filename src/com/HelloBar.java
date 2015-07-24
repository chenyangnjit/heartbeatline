package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import net.sf.json.JSONArray;

@WebServlet(name="helloBar",value="/bar.do")
public class HelloBar extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HelloBar() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	     //创建了一个bardao的对象，barDAO主要是对数据库的连接和对数据库的操作
        barDao bardao=new barDao();
        //调用bardao的select_all()方法把从数据库中读取所有的数据返回的是一个ArrayList，ArrayList里面放的是一个barBean
        ArrayList<barBean> array = (ArrayList<barBean>) bardao.listAll();
        //System.out.println("s"+array);
        //设置返回时的编码格式
        response.setContentType("text/html; charset=utf-8");
        //调用JSONArray.fromObject方法把array中的对象转化为JSON格式的数组
        JSONArray json=JSONArray.fromObject(array);
        //System.out.println(json.toString());
        //返回给前段页面
        PrintWriter out = response.getWriter();  
        out.println(json);  
        out.flush();  
        out.close();   
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
