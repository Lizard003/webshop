<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>商品更新</title>
  </head>
  <body>
  	<form action="/webshop/servlet/ProductServlet" method="post">
  		商品名称：<input type="text" name="name" value="${requestScope.product.name}"/><br/>
  		商品价格：<input type="text" name="price" value="${product.price }"/><br/>
  		商品备注：<input type="text" name="remark" value="${requestScope.product.remark}"/><br/>
  		<button type="submit">提交</button>
  		<input type="hidden" name="type" value="update">
  		<input type="hidden" name="id" value="${requestScope.product.id}">
  	</form>
  </body>
</html>
