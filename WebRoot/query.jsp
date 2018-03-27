<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  <body>
	<form action="/webshop/servlet/ProductServlet" method="get">
		关键字：<input type="text" name="keyword"/>
		<button type="submit">提交</button><br />
		<input type="hidden" name="type" value="query">
 		<!-- 获取数据为:<%=request.getAttribute("proList")%> -->
	</form>
	<table><tr>
		<th>商品序号</th>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>商品日期</th>
		<th>商品备注</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${requestScope.proList }" var="product" varStatus="num">
	<tr>
		<td>${num.count }</td>
		<td>${product.name }</td>
		<td>${product.price }</td>
		<td>${product.date}</td>
		<td>${product.remark }</td>
		<td><a href="/webshop/servlet/ProductServlet?id=${product.id}&type=delete">删除</a>|
		<a href="/webshop/servlet/ProductServlet?id=${product.id}&type=getById">更新</a></td>
	</tr>
  	</c:forEach>
  	</table>
  </body>
</html>
