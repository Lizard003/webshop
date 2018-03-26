package cn.yd.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.yd.shop.dao.ProductDaoImpl;
import cn.yd.shop.model.Product;

public class ProductServlet extends HttpServlet {

	ProductDaoImpl productDao = new ProductDaoImpl();
	
	public ProductServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(new BigDecimal(request.getParameter("price")));
		product.setRemark(request.getParameter("remark"));
		productDao.save(product);
		
		response.sendRedirect("/webshop/query.jsp");

	}


	public void init() throws ServletException {
		// Put your code here
	}

}
