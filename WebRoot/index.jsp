<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  <body>
  	<form action="/webshop/servlet/ProductServlet" method="post">
  		商品名称：<input type="text" name="name"/><br/>
  		商品价格：<input type="text" name="price"/><br/>
  		商品备注：<input type="text" name="remark"/><br/>
  		<button type="submit">提交</button>
  	</form>
  </body>
</html>
