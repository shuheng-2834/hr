package com.zhong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhong.vo.Dept;
import com.zhong.vo.Emp;


public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ElServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	Map<Integer,Emp> all = new HashMap<Integer,Emp>();
    	for(int x=0 ;x<10;x++) {
    	Emp emp = new Emp();
    	Dept dept = new Dept();
    	emp.setEmpno(7396+36*x);
    	emp.setEname("史密斯 - "+x);
    	dept.setDeptno(20 + x*10);
    	dept.setDname("财务部");
    	emp.setDept(dept);
    	all.put(x,emp);
    	}
    	request.setAttribute("all", all);	//传递request的范围
    	request.getRequestDispatcher("/show.jsp").forward(request, response);
    	
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
