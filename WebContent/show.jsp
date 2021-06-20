<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EL表达式</title>
</head>
<body>

<%
	response.setHeader("refresh", "5");
%>
<%
		String uid = request.getParameter("uid");
		if(uid!=null){
			session.setAttribute("userid", uid);
		}
	%>
<h3>${allUsers}</h3>
<!-- 

	该jsp页面被跳转后接收的是一个VO类对象，并且保存在了request属性范围里面，所有可以直接
	利用EL来进行输出
	
 -->
 <table border="1px" width="400px">
 	<thead>
 		<tr>
 			<td>NO.</td>
 			<td>雇员编号</td>
 			<td>雇员姓名</td>
 			<td>部门编号</td>
 			<td>部门名称</td>
 		</tr>
 	</thead>
 	<tbody>
 	<%
 		//此时没有导入VO包，所以List没有设置泛型，所有出现警告信息
 		//
 		Map all = (Map)request.getAttribute("all");
 		Iterator iter = all.entrySet().iterator();
 		while(iter.hasNext()){
 			/**
 				此时没有设置泛型，所以next()方法每次返回的类型都是Object
 				EL是需要属性范围有关的数据才可以输出，而且设置属性的时候数据类型都是Object
 				为了可以让EL与数据有关，必须将数据设置在属性当中，
 			*/
 			Object obj = iter.next();
 			pageContext.setAttribute("entry", obj);
 	%>
 		<tr>
 			<td>${entry.key+1}</td>
 			<td>${entry.value.empno}</td>
 			<td>${entry.value.ename}</td>
 			<td>${entry.value.dept.deptno}</td>
 			<td>${entry.value.dept.dname}</td>
 		</tr>
 	</tbody>
 	<%
 		}
 	%>
 </table>

</body>
</html>